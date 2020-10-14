package nl.fontys.customer.api.bean;

import nl.fontys.customer.api.security.jwt.JwtTokenParser;
import nl.fontys.customer.api.security.jwt.TokenParser;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.regex.Pattern;

import static java.nio.charset.StandardCharsets.ISO_8859_1;

@Configuration
public class TokenParserBean {

    private final String publicKeyPath;

    @Autowired
    public TokenParserBean(@Value("${security.authorization.jwt.publickey}") String publicKeyPath) {
        this.publicKeyPath = publicKeyPath;
    }

    @Bean
    public TokenParser getJwtParser() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        File keyFile = new File(this.publicKeyPath);
        PublicKey publicKey = this.loadPublicKeyFromFile(keyFile);
        return new JwtTokenParser(publicKey);
    }

    private PublicKey loadPublicKeyFromFile(File file) throws NoSuchAlgorithmException, IOException,
            InvalidKeySpecException {

        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(new X509EncodedKeySpec(loadPem(file)));
    }

    private byte[] loadPem(File file) throws IOException {
        InputStream inputStream = new FileInputStream(file);
        byte[] keyBytes = this.readAllBytes(inputStream);

        String pem = new String(keyBytes, ISO_8859_1);
        Pattern parse = Pattern.compile("(?m)(?s)^---*BEGIN.*---*$(.*)^---*END.*---*$.*");
        String encoded = parse.matcher(pem).replaceFirst("$1");

        return Base64.decodeBase64(encoded);
    }

    private byte[] readAllBytes(InputStream in) throws IOException {
        ByteArrayOutputStream baos= new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        for (int read=0; read != -1; read = in.read(buf)) { baos.write(buf, 0, read); }
        return baos.toByteArray();
    }
}
