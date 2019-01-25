// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LicenseServiceImpl.java

package com.fitechlabs.xtrade.kernel.license.impl;

import com.fitechlabs.xtrade.kernel.license.License;
import com.fitechlabs.xtrade.kernel.license.LicenseInfo;
import com.fitechlabs.xtrade.kernel.license.LicenseService;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.kernel.util.xml.ObjectToXMLConverter;
import com.fitechlabs.xtrade.kernel.util.xml.TagnameResolver;
import com.fitechlabs.xtrade.kernel.util.xml.XMLToObjectConverter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.OutputStream;
import java.net.URL;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

public final class LicenseServiceImpl
    implements LicenseService
{
    private static final class LicenseImpl
        implements License
    {

        public boolean equals(Object o)
        {
            if(o == null || !(o instanceof LicenseImpl))
            {
                return false;
            } else
            {
                LicenseImpl lic = (LicenseImpl)o;
                return m.licensee.equals(lic.getLicensee()) && m.component.equals(lic.getComponent()) && m.issue_date.equals(lic.getIssueDate()) && m.expiration_date.equals(lic.getExpirationDate()) && getAttributes().equals(lic.getAttributes());
            }
        }

        public int hashCode()
        {
            return m.licensee.hashCode() + m.component.hashCode() + m.issue_date.hashCode() + m.expiration_date.hashCode() + getAttributes().hashCode();
        }

        public String toXmlString()
        {
            return ObjectToXMLConverter.toXMLString(m);
            Exception e;
            e;
            throw new InternalError("conversion failure: " + e);
        }

        public final boolean isValid(com.fitechlabs.xtrade.kernel.license.Key publicKey)
        {
            return m.verify(publicKey);
            Exception e;
            e;
            LicenseServiceImpl.log.warn("verification exception: ", e);
            return false;
        }

        public String getComponent()
        {
            return m.component;
        }

        public java.sql.Date getExpirationDate()
        {
            return m.expiration_date;
        }

        public java.sql.Date getIssueDate()
        {
            return m.issue_date;
        }

        public String getLicensee()
        {
            return m.licensee;
        }

        public Properties getAttributes()
        {
            Properties p = new Properties();
            if(m.attr != null)
            {
                for(int i = 0; i < m.attr.length; i++)
                    p.put(m.attr[i].name, m.attr[i].value);

            }
            return p;
        }

        private final LicenseMessage m;

        private LicenseImpl(LicenseMessage m)
        {
            this.m = m;
        }

    }

    public static final class NameValuePair
    {

        public String name;
        public String value;

        public NameValuePair()
        {
        }
    }

    public static final class LicenseMessage
    {

        private void addDigest(com.fitechlabs.xtrade.kernel.license.Key privateKey)
            throws Exception
        {
            String message = toString();
            Signature sig = Signature.getInstance(LicenseServiceImpl.SIGNATURE_ALGORITHM);
            PrivateKey privkey = (PrivateKey)privateKey.getObject();
            sig.initSign(privkey);
            sig.update(message.getBytes("UTF8"));
            byte digest[] = sig.sign();
            key = LicenseServiceImpl.toHexString(digest);
        }

        private boolean verify(com.fitechlabs.xtrade.kernel.license.Key publicKey)
            throws Exception
        {
            String message = toString();
            Signature sig = Signature.getInstance(LicenseServiceImpl.SIGNATURE_ALGORITHM);
            PublicKey pubkey = (PublicKey)publicKey.getObject();
            sig.initVerify(pubkey);
            sig.update(message.getBytes("UTF8"));
            byte digest[] = LicenseServiceImpl.fromHexString(key);
            return sig.verify(digest);
        }

        public String toString()
        {
            TreeMap map = new TreeMap();
            if(attr != null)
            {
                for(int i = 0; i < attr.length; i++)
                    map.put(attr[i].name, attr[i].value);

            }
            return licensee + "|" + issue_date + "|" + expiration_date + "|" + component + "|" + map;
        }

        private void setAttributes(Properties props)
        {
            if(props == null || props.size() == 0)
            {
                attr = null;
            } else
            {
                int i = 0;
                int n = props.size();
                NameValuePair nvp[] = new NameValuePair[n];
                for(Iterator it = props.entrySet().iterator(); it.hasNext();)
                {
                    java.util.Map.Entry e = (java.util.Map.Entry)it.next();
                    NameValuePair p = new NameValuePair();
                    p.name = (String)e.getKey();
                    p.value = (String)e.getValue();
                    nvp[i] = p;
                    i++;
                }

                attr = nvp;
            }
        }

        public static final String TAGNAME = "license";
        public String licensee;
        public String component;
        public java.sql.Date issue_date;
        public java.sql.Date expiration_date;
        public NameValuePair attr[];
        public String key;




        public LicenseMessage()
        {
        }
    }


    public LicenseServiceImpl()
    {
        licenseImplsByComponent = new HashMap();
        allInstalledLicenses = new HashSet();
        unmodifiableAllInstalledLicenses = Collections.unmodifiableCollection(allInstalledLicenses);
        converter = new XMLToObjectConverter(new TagnameResolver() {

            public Class getClass(String contextTag, String tagName)
            {
                if("license".equals(contextTag) && tagName == null)
                    return LicenseServiceImpl.class$com$fitechlabs$xtrade$kernel$license$impl$LicenseServiceImpl$LicenseMessage != null ? LicenseServiceImpl.class$com$fitechlabs$xtrade$kernel$license$impl$LicenseServiceImpl$LicenseMessage : (LicenseServiceImpl.class$com$fitechlabs$xtrade$kernel$license$impl$LicenseServiceImpl$LicenseMessage = LicenseServiceImpl._mthclass$("com.fitechlabs.xtrade.kernel.license.impl.LicenseServiceImpl$LicenseMessage"));
                else
                    return null;
            }

        }
);
    }

    public final com.fitechlabs.xtrade.kernel.license.KeyPair genKeyPair()
    {
        KeyPairGenerator kpg;
        try
        {
            kpg = KeyPairGenerator.getInstance(KEY_PAIR_GEN_ALG);
        }
        catch(NoSuchAlgorithmException e)
        {
            throw new InternalError("no generator for '" + KEY_PAIR_GEN_ALG + "': " + e);
        }
        kpg.initialize(KEY_PAIR_LENGTH);
        KeyPair kp = kpg.generateKeyPair();
        final com.fitechlabs.xtrade.kernel.license.Key pub = keyFromObject(kp.getPublic());
        final com.fitechlabs.xtrade.kernel.license.Key pri = keyFromObject(kp.getPrivate());
        return new com.fitechlabs.xtrade.kernel.license.KeyPair() {

            public com.fitechlabs.xtrade.kernel.license.Key getPublicKey()
            {
                return pub;
            }

            public com.fitechlabs.xtrade.kernel.license.Key getPrivateKey()
            {
                return pri;
            }

            public boolean equals(Object o)
            {
                if(o == null || !(o instanceof com.fitechlabs.xtrade.kernel.license.KeyPair))
                {
                    return false;
                } else
                {
                    com.fitechlabs.xtrade.kernel.license.KeyPair okp = (com.fitechlabs.xtrade.kernel.license.KeyPair)o;
                    return pub.equals(okp.getPublicKey()) && pri.equals(okp.getPrivateKey());
                }
            }

            public int hashCode()
            {
                return pub.hashCode() + pri.hashCode();
            }

        }
;
    }

    public final License createLicense(LicenseInfo info, com.fitechlabs.xtrade.kernel.license.Key privateKey)
    {
        if(info == null)
            throw new IllegalArgumentException("info");
        if(info.getLicensee() == null)
            throw new IllegalArgumentException("licensee cannot be null");
        if(info.getComponent() == null)
            throw new IllegalArgumentException("component cannot be null");
        if(info.getIssueDate() == null)
            throw new IllegalArgumentException("issue date cannot be null");
        if(info.getExpirationDate() == null)
            throw new IllegalArgumentException("expiration date cannot be null");
        LicenseMessage m = new LicenseMessage();
        m.licensee = info.getLicensee();
        m.component = info.getComponent();
        m.issue_date = info.getIssueDate();
        m.expiration_date = info.getExpirationDate();
        m.setAttributes(info.getAttributes());
        try
        {
            m.addDigest(privateKey);
        }
        catch(Exception e)
        {
            throw new InternalError("creation failed: " + e);
        }
        return new LicenseImpl(m);
    }

    public Collection getInstalledLicenses()
    {
        return unmodifiableAllInstalledLicenses;
    }

    public final Collection getValidLicenses(String component, com.fitechlabs.xtrade.kernel.license.Key publicKey)
    {
        Collection perComponent = (Collection)licenseImplsByComponent.get(component);
        if(perComponent == null)
            return new ArrayList();
        Collection c = new ArrayList(perComponent.size());
        Date now = new Date();
        Iterator it = perComponent.iterator();
        do
        {
            if(!it.hasNext())
                break;
            LicenseImpl lic = (LicenseImpl)it.next();
            if(lic.isValid(publicKey) && !now.after(lic.getExpirationDate()) && lic.isValid(publicKey))
                c.add(lic);
        } while(true);
        return c;
    }

    public final Collection getValidAttributeValues(String component, com.fitechlabs.xtrade.kernel.license.Key publicKey, String attributeName)
    {
        Collection c = getValidLicenses(component, publicKey);
        Set set = new HashSet(c.size());
        Iterator it = c.iterator();
        do
        {
            if(!it.hasNext())
                break;
            License lic = (License)it.next();
            Properties attr = lic.getAttributes();
            if(attr != null)
            {
                String valu = attr.getProperty(attributeName);
                if(valu != null)
                    set.add(valu);
            }
        } while(true);
        return set;
    }

    public void installLicense(License license)
        throws IllegalArgumentException
    {
        if(license.getClass() != (com.fitechlabs.xtrade.kernel.license.impl.LicenseServiceImpl$LicenseImpl.class))
            throw new IllegalArgumentException("incompatible license class: " + license);
        allInstalledLicenses.add(license);
        String component = license.getComponent();
        Collection perComponent = (Collection)licenseImplsByComponent.get(component);
        if(perComponent == null)
            synchronized(licenseImplsByComponent)
            {
                if(perComponent == null)
                    licenseImplsByComponent.put(component, perComponent = new HashSet());
            }
        perComponent.add(license);
    }

    public final void installLicense(String xmlString)
        throws Exception
    {
        License lic = licenseFromXmlString(xmlString);
        installLicense(lic);
    }

    public void installLicense(URL url)
        throws Exception
    {
        InputStream is = url.openStream();
        Object o = converter.xmlToObject(is);
        License lic = new LicenseImpl((LicenseMessage)o);
        installLicense(lic);
        is.close();
        break MISSING_BLOCK_LABEL_50;
        Exception exception;
        exception;
        is.close();
        throw exception;
    }

    public License licenseFromXmlString(String xml)
        throws Exception
    {
        Object o = converter.xmlToObject(xml);
        return new LicenseImpl((LicenseMessage)o);
    }

    public com.fitechlabs.xtrade.kernel.license.Key keyFromFile(String filename)
        throws IOException
    {
        FileInputStream istream = new FileInputStream(filename);
        return keyFromInputStream(istream);
    }

    public com.fitechlabs.xtrade.kernel.license.Key keyFromHexString(String string)
        throws IOException
    {
        byte b[] = fromHexString(string);
        return keyFromBytes(b);
    }

    public com.fitechlabs.xtrade.kernel.license.Key keyFromBytes(byte bytes[])
        throws IOException
    {
        InputStream istream = new ByteArrayInputStream(bytes);
        return keyFromInputStream(istream);
    }

    private com.fitechlabs.xtrade.kernel.license.Key keyFromInputStream(InputStream is)
        throws IOException
    {
        Object o;
        ObjectInputStream p = new ObjectInputStream(is);
        o = p.readObject();
        return keyFromObject(o);
        OptionalDataException e;
        e;
        throw new RuntimeException("conversion failure: " + e);
        e;
        throw new RuntimeException("conversion failure: " + e);
    }

    private com.fitechlabs.xtrade.kernel.license.Key keyFromObject(final Object obj)
    {
        if(!(obj instanceof Key))
            throw new IllegalArgumentException("not a java.security.Key: " + obj);
        else
            return new com.fitechlabs.xtrade.kernel.license.Key() {

                public boolean equals(Object other)
                {
                    if(other == null || !(other instanceof com.fitechlabs.xtrade.kernel.license.Key))
                    {
                        return false;
                    } else
                    {
                        com.fitechlabs.xtrade.kernel.license.Key ok = (com.fitechlabs.xtrade.kernel.license.Key)other;
                        return obj.equals(ok.getObject());
                    }
                }

                public int hashCode()
                {
                    return obj.hashCode();
                }

                public Object getObject()
                {
                    return obj;
                }

                public String toHexString()
                    throws IOException
                {
                    return LicenseServiceImpl.toHexString(toBytes());
                }

                public byte[] toBytes()
                    throws IOException
                {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    toOutputStream(baos);
                    return baos.toByteArray();
                }

                public void toFile(String filename)
                    throws IOException
                {
                    toOutputStream(new FileOutputStream(filename));
                }

                private void toOutputStream(OutputStream ostream)
                    throws IOException
                {
                    ObjectOutputStream p = new ObjectOutputStream(ostream);
                    p.writeObject(obj);
                    p.flush();
                }

            }
;
    }

    private static String toHexString(byte b[])
    {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < b.length; i++)
        {
            sb.append(Integer.toHexString(0xf & b[i] >> 4));
            sb.append(Integer.toHexString(0xf & b[i]));
        }

        return sb.toString();
    }

    private static byte[] fromHexString(String s)
    {
        int n = s.length();
        char c[] = new char[n];
        s.getChars(0, n, c, 0);
        byte b[] = new byte[n / 2];
        for(int i = 0; i < n; i += 2)
            b[i / 2] = (byte)((fromHex(c[i]) << 4) + fromHex(c[i + 1]));

        return b;
    }

    private static int fromHex(char c)
    {
        switch(c)
        {
        case 48: // '0'
        case 49: // '1'
        case 50: // '2'
        case 51: // '3'
        case 52: // '4'
        case 53: // '5'
        case 54: // '6'
        case 55: // '7'
        case 56: // '8'
        case 57: // '9'
            return c - 48;

        case 97: // 'a'
        case 98: // 'b'
        case 99: // 'c'
        case 100: // 'd'
        case 101: // 'e'
        case 102: // 'f'
            return -87 + c;

        case 65: // 'A'
        case 66: // 'B'
        case 67: // 'C'
        case 68: // 'D'
        case 69: // 'E'
        case 70: // 'F'
            return -55 + c;

        case 58: // ':'
        case 59: // ';'
        case 60: // '<'
        case 61: // '='
        case 62: // '>'
        case 63: // '?'
        case 64: // '@'
        case 71: // 'G'
        case 72: // 'H'
        case 73: // 'I'
        case 74: // 'J'
        case 75: // 'K'
        case 76: // 'L'
        case 77: // 'M'
        case 78: // 'N'
        case 79: // 'O'
        case 80: // 'P'
        case 81: // 'Q'
        case 82: // 'R'
        case 83: // 'S'
        case 84: // 'T'
        case 85: // 'U'
        case 86: // 'V'
        case 87: // 'W'
        case 88: // 'X'
        case 89: // 'Y'
        case 90: // 'Z'
        case 91: // '['
        case 92: // '\\'
        case 93: // ']'
        case 94: // '^'
        case 95: // '_'
        case 96: // '`'
        default:
            throw new RuntimeException("Illegal character: " + (int)c);
        }
    }

    private static final Logit log;
    private static String KEY_PAIR_GEN_ALG = "DSA";
    private static int KEY_PAIR_LENGTH = 512;
    private static String SIGNATURE_ALGORITHM = "DSA";
    private static String DIGEST_ALGORITHM = "SHA-1";
    private static int TEXT_BLOCK_LENGTH = 128;
    private XMLToObjectConverter converter;
    private Map licenseImplsByComponent;
    private Set allInstalledLicenses;
    private Collection unmodifiableAllInstalledLicenses;
    static Class class$com$fitechlabs$xtrade$kernel$license$impl$LicenseServiceImpl$LicenseMessage; /* synthetic field */

    static 
    {
        log = Logit.getInstance(com.fitechlabs.xtrade.kernel.license.impl.LicenseServiceImpl.class);
    }




}
