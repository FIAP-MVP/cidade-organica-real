package br.com.fiap.cidade.utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;

@Builder
public class Argon2 {
    private static final int SALT_LENGTH = 16;
    private static final int HASH_LENGTH = 32;
    private static final int ITERATIONS = 2;
    private static final int MEMORY = 2;
    private static final int PARALLELISM = 1;

    public static byte[] hash(char[] password, byte[] salt) {
        Argon2BytesGenerator argon2 = new Argon2BytesGenerator();
        Argon2Parameters.Builder builder = new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id);
        builder.withSalt(salt);
        builder.withIterations(ITERATIONS);
        builder.withMemoryPowOfTwo(MEMORY);
        builder.withParallelism(PARALLELISM);
        Argon2Parameters param = builder.build();
        argon2.init(param);
        byte[] hash = new byte[HASH_LENGTH];
        argon2.generateBytes(password, hash);
        return hash;
    }

    public static byte[] getSalt(byte[] hashSalt) {
        byte[] salt = new byte[SALT_LENGTH];
        System.arraycopy(hashSalt, 0, salt, 0, SALT_LENGTH);
        return salt;
    }

    public static byte[] getHash(byte[] hashSalt) {
        byte[] hash = new byte[HASH_LENGTH];
        System.arraycopy(hashSalt, SALT_LENGTH, hash, 0, HASH_LENGTH);
        return hash;
    }

    public static boolean validatePassword(char[] password, byte[] hashSalt) {
        byte[] salt = getSalt(hashSalt);
        byte[] hash = getHash(hashSalt);
        byte[] passwordHash = hash(password, salt);
        return Arrays.equals(passwordHash, hash);
    }
}