head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.30.08.31.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	2144d92ea565cae;
filename	WEB3SSLCertificateCoder.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a�����r�W�l�X�E�C�m�x�[�V����
File Name        : WEB3SSLCertificateCoder.java
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2011/03/30 �Ԑi(���u) �V�K�쐬 
*/
package webbroker3.aio;

import java.io.FileInputStream;   
import java.security.KeyStore;   
import java.security.PrivateKey;   
import java.security.PublicKey;   
import java.security.Signature;   
import java.security.cert.Certificate;   
import java.security.cert.CertificateFactory;   
import java.security.cert.X509Certificate;   
import java.util.Date;   
  
import javax.crypto.Cipher;
import javax.net.ssl.KeyManagerFactory;   
import javax.net.ssl.SSLContext;   
import javax.net.ssl.SSLSocketFactory;   
import javax.net.ssl.TrustManagerFactory;   
  
/**
 * WEB3SSLCertificateCoder
 * @@version 1.0
 */  
public class WEB3SSLCertificateCoder extends WEB3SSLCoder
{   
   public static final String KEY_STORE = "JKS"; 
   public static final String X509 = "X.509";
   public static final String SunX509 = "SunX509";
   public static final String SSL = "SSL";
 
   /**
    * KeyStore ���擾����B
    * @@param keyStorePath
    * @@param password
    * @@return KeyStore
    * @@throws Exception
    */  
    private static KeyStore getKeyStore(String keyStorePath, String password) throws Exception
    {   
       FileInputStream l_fileInputStream = new FileInputStream(keyStorePath);
       KeyStore ks = KeyStore.getInstance(KEY_STORE);
       ks.load(l_fileInputStream, password.toCharArray());
       l_fileInputStream.close();
       return ks;
    }

   /**
    * KeyStore����PrivateKey���擾����B
    * @@param keyStorePath  
    * @@param alias  
    * @@param password  
    * @@return  
    * @@throws Exception  
    */  
    private static PrivateKey getPrivateKey(String keyStorePath, String alias, String password) throws Exception
    {
        KeyStore ks = getKeyStore(keyStorePath, password);
        PrivateKey key = (PrivateKey) ks.getKey(alias, password.toCharArray());
        return key;
    }

   /**
    * Certificate���擾����B
    * @@param certificatePath
    * @@return Certificate
    * @@throws Exception
    */  
    private static Certificate getCertificate(String certificatePath) throws Exception
    {   
        CertificateFactory certificateFactory = CertificateFactory.getInstance(X509);
        FileInputStream in = new FileInputStream(certificatePath);
 
        Certificate certificate = certificateFactory.generateCertificate(in);
        in.close();
        return certificate;
    }

   /**
    * Certificate����PublicKey���擾����B
    * @@param certificatePath
    * @@return PublicKey
    * @@throws Exception
    */  
    private static PublicKey getPublicKey(String certificatePath) throws Exception
    {   
        Certificate certificate = getCertificate(certificatePath);
        PublicKey key = certificate.getPublicKey();
        return key;
    }

   /**
    * Certificate���擾����B
    * @@param keyStorePath
    * @@param alias
    * @@param password
    * @@return Certificate
    * @@throws Exception
    */
    private static Certificate getCertificate(String keyStorePath, String alias, String password) throws Exception
    {   
         KeyStore ks = getKeyStore(keyStorePath, password);
         Certificate certificate = ks.getCertificate(alias);

         return certificate;
     }

    /**
     * Encrypt By PrivateKey
     * @@param data
     * @@param keyStorePath
     * @@param alias
     * @@param password
     * @@return byte[]
     * @@throws Exception
     */
     public static byte[] encryptByPrivateKey(byte[] data, String keyStorePath, String alias, String password) throws Exception
     {
         PrivateKey privateKey = getPrivateKey(keyStorePath, alias, password);
         Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
         cipher.init(Cipher.ENCRYPT_MODE, privateKey);
         return cipher.doFinal(data);
     }

    /**
     * Decrypt By PrivateKey
     * @@param data
     * @@param keyStorePath
     * @@param alias
     * @@param password
     * @@return byte[]
     * @@throws Exception
     */
     public static byte[] decryptByPrivateKey(byte[] data, String keyStorePath, String alias, String password) throws Exception
     {
         PrivateKey privateKey = getPrivateKey(keyStorePath, alias, password);
         Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
         cipher.init(Cipher.DECRYPT_MODE, privateKey);
         return cipher.doFinal(data);
     }

    /**
     * Encrypt By PublicKey
     * @@param data
     * @@param certificatePath
     * @@return 
     * @@throws Exception
     */
     public static byte[] encryptByPublicKey(byte[] data, String certificatePath) throws Exception
     {
         PublicKey publicKey = getPublicKey(certificatePath);
         Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
         cipher.init(Cipher.ENCRYPT_MODE, publicKey);
         return cipher.doFinal(data);
     }

    /**
     * Decrypt By PublicKey
     * @@param data
     * @@param certificatePath
     * @@return byte[]
     * @@throws Exception
     */
     public static byte[] decryptByPublicKey(byte[] data, String certificatePath) throws Exception
     {
         PublicKey publicKey = getPublicKey(certificatePath);
         Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
         cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return cipher.doFinal(data);
     }

    /**
     * Certificate����������B
     * @@param certificatePath
     * @@return boolean
     */  
     public static boolean verifyCertificate(String certificatePath)
     {
         return verifyCertificate(new Date(), certificatePath);
     }

    /**  
     * Certificate�������������؂ꂩ��������
     * @@param date
     * @@param certificatePath
     * @@return boolean
     */
     public static boolean verifyCertificate(Date date, String certificatePath)
     {   
         boolean status = true;
         try
         {
             Certificate certificate = getCertificate(certificatePath);
             status = verifyCertificate(date, certificate);
         }
         catch (Exception e)
         {
             status = false;
         }
         return status;
    }

    /**
     * Certificate�������������؂ꂩ��������
     * @@param date
     * @@param certificate
     * @@return boolean
     */
     private static boolean verifyCertificate(Date date, Certificate certificate)
     {
         boolean status = true;
         try
         {
             X509Certificate x509Certificate = (X509Certificate) certificate;
             x509Certificate.checkValidity(date);
         }
         catch (Exception e)
         {
             status = false;
         }
         return status;
    }

    /**
     * �T�C��
     * @@param keyStorePath
     * @@param alias
     * @@param password
     * @@return String
     * @@throws Exception
     */  
     public static String sign(byte[] sign, String keyStorePath, String alias, String password) throws Exception
     {
         X509Certificate x509Certificate = (X509Certificate) getCertificate(keyStorePath, alias, password);
         KeyStore ks = getKeyStore(keyStorePath, password);
         PrivateKey privateKey = (PrivateKey) ks.getKey(alias, password.toCharArray());
         Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
         signature.initSign(privateKey);
         signature.update(sign);
         return encryptBASE64(signature.sign());
     }

    /**
     * �T�C������������B
     * @@param data
     * @@param sign
     * @@param certificatePath
     * @@return boolean
     * @@throws Exception
     */
     public static boolean verify(byte[] data, String sign, String certificatePath) throws Exception
     {
         X509Certificate x509Certificate = (X509Certificate) getCertificate(certificatePath);
         PublicKey publicKey = x509Certificate.getPublicKey();
         Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
         signature.initVerify(publicKey);
         signature.update(data);
         return signature.verify(decryptBASE64(sign));
     }

    /**
     * Certificate���擾����B
     * @@param keyStorePath
     * @@param alias
     * @@param password
     * @@return boolean
     */  
     public static boolean verifyCertificate(Date date, String keyStorePath, String alias, String password)
     {
         boolean status = true;
         try
         {
             Certificate certificate = getCertificate(keyStorePath, alias, password);
             status = verifyCertificate(date, certificate);
         }
         catch (Exception e)
         {
             status = false;
         }
         return status;
     }

    /**
     * Certificate���擾����B
     * @@param keyStorePath
     * @@param alias
     * @@param password
     * @@return boolean
     */
     public static boolean verifyCertificate(String keyStorePath, String alias, String password)
     {
         return verifyCertificate(new Date(), keyStorePath, alias, password);
     }

    /**
     * SSLSocektFactory���擾����B
     * @@param password
     * @@param keyStorePath
     * @@param trustKeyStorePath
     * @@return SSLSocketFactory
     * @@throws Exception
     */
     public static SSLSocketFactory getSSLSocketFactory(String password, String keyStorePath, String trustKeyStorePath) throws Exception
     {
         KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(SunX509);
         KeyStore keyStore = getKeyStore(keyStorePath, password);
         keyManagerFactory.init(keyStore, password.toCharArray());
         TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(SunX509);
         KeyStore trustkeyStore = getKeyStore(trustKeyStorePath, password);
         trustManagerFactory.init(trustkeyStore);

         SSLContext ctx = SSLContext.getInstance(SSL);
         ctx.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
         SSLSocketFactory sf = ctx.getSocketFactory();
         return sf;
     }
}  
@
