// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LicenseTool.java

package com.fitechlabs.xtrade.kernel.license;

import java.io.*;
import java.security.SecureRandom;
import java.text.*;
import java.util.*;

// Referenced classes of package com.fitechlabs.xtrade.kernel.license:
//            LicenseParams, Licensing, LicenseService, KeyPair, 
//            Key, LicenseInfo, License

public final class LicenseTool
{

    private LicenseTool()
    {
    }

    public static void main(String args[])
        throws Exception
    {
        String component = null;
        String licensee = null;
        Date issued = new Date();
        Date expires = null;
        String keypath = null;
        String sqlfile = null;
        String xmlfile = null;
        String propsfile = null;
        boolean append = false;
        Properties attr = null;
        if(args[0].equals("-genkeys") && args.length >= 3 && args.length <= 4)
        {
            System.out.println("generating keys");
            genKeys(args[1], args[2], args.length <= 3 ? null : args[3]);
            return;
        }
        for(int i = 0; i < args.length; i++)
        {
            if(args[i].equals("-component"))
            {
                component = args[++i];
                continue;
            }
            if(args[i].equals("-licensee"))
            {
                licensee = args[++i];
                continue;
            }
            if(args[i].equals("-expires"))
            {
                expires = yyyymmdd.parse(args[++i]);
                continue;
            }
            if(args[i].equals("-days"))
            {
                expires = new Date(System.currentTimeMillis() + (long)Integer.parseInt(args[++i]) * 0x5265c00L);
                continue;
            }
            if(args[i].equals("-keypath"))
            {
                keypath = args[++i];
                continue;
            }
            if(args[i].equals("-sql") || args[i].equals("-outfile"))
            {
                sqlfile = args[++i];
                continue;
            }
            if(args[i].equals("-xml"))
            {
                xmlfile = args[++i];
                continue;
            }
            if(args[i].equals("-props") || args[i].equals("-properties"))
            {
                propsfile = args[++i];
                continue;
            }
            if(args[i].equals("-append"))
            {
                append = true;
                continue;
            }
            if(args[i].equals("-attr") || args[i].equals("-attribute"))
            {
                if(attr == null)
                    attr = new Properties();
                StringTokenizer st = new StringTokenizer(args[++i], "=");
                String name = st.nextToken();
                String valu = st.nextToken();
                valu = dequote(valu);
                attr.put(name, valu);
            } else
            {
                System.err.println(usage);
                System.exit(-1);
            }
        }

        if(component == null || expires == null || keypath == null)
        {
            System.err.println(usage);
            return;
        }
        LicenseService ls = Licensing.getLicenseServiceInstance();
        Key privateKey = ls.keyFromFile(keypath);
        LicenseParams params = new LicenseParams(component, licensee, issued, expires, attr);
        License license = ls.createLicense(params, privateKey);
        if(sqlfile != null)
            toSqlFile(sqlfile, license, append);
        if(xmlfile != null)
            toXmlFile(xmlfile, license, append);
        if(propsfile != null)
            toPropsFile(propsfile, license, append);
    }

    private static void genKeys(String pubFile, String privFile, String textFile)
        throws Exception
    {
        LicenseService ls = Licensing.getLicenseServiceInstance();
        KeyPair kp = ls.genKeyPair();
        kp.getPublicKey().toFile(pubFile);
        System.out.println("public  key written to " + pubFile);
        kp.getPrivateKey().toFile(privFile);
        System.out.println("private key written to " + privFile);
        if(textFile != null)
        {
            String s = kp.getPublicKey().toHexString();
            PrintWriter pw = new PrintWriter(new FileWriter(textFile));
            pw.println("    String publicKeyString = ");
            int n = s.length();
            for(int i = 0; i < n; i += 92)
                pw.println("        \"" + s.substring(i, Math.min(i + 92, n)) + "\" " + (i + 92 >= n ? ";" : "+"));

            pw.close();
            System.out.println("public key String info written to " + textFile);
        }
    }

    private static String toName(License license)
    {
        String comp = license.getComponent();
        Date issue = license.getIssueDate();
        int n = Math.min(comp.length(), 32);
        return "lic-" + comp.substring(0, n) + "-" + (new SimpleDateFormat("yyMMdd")).format(issue) + "-" + (new DecimalFormat("0000")).format((new SecureRandom()).nextInt(10000));
    }

    private static void toSqlFile(String filename, License license, boolean append)
        throws IOException
    {
        String xml = license.toXmlString();
        String comp = license.getComponent();
        String licee = license.getLicensee();
        Date issue = license.getIssueDate();
        Date expire = license.getExpirationDate();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        PrintWriter pw = getPW(filename, append);
        pw.println();
        pw.println("--");
        pw.println("-- XTrade Component License");
        pw.println("--           component: " + comp);
        pw.println("--            licensee: " + licee);
        pw.println("--          issue date: " + issue);
        pw.println("--     expiration date: " + expire);
        pw.println("--");
        pw.println("insert into server_license ( license_id, component, licensee, expire_date, license_xml )");
        pw.println("    values (");
        pw.println("        '" + toName(license) + "',");
        pw.println("        '" + comp + "',");
        pw.println("        '" + licee + "',");
        pw.println("        to_date('" + format.format(expire) + "','yyyy-MM-dd'),");
        pw.println("        '" + xml + "');");
        pw.println();
        pw.flush();
        pw.close();
        System.out.println("sql file " + (append ? "created " : "appended") + ": " + filename);
    }

    private static void toXmlFile(String filename, License license, boolean append)
        throws IOException
    {
        String xml = license.toXmlString();
        String comp = license.getComponent();
        String licee = license.getLicensee();
        Date issue = license.getIssueDate();
        Date expire = license.getExpirationDate();
        PrintWriter pw = getPW(filename, append);
        pw.println("<!--");
        pw.println("  -- XTrade Component License");
        pw.println("  --           component: " + comp);
        pw.println("  --            licensee: " + licee);
        pw.println("  --          issue date: " + issue);
        pw.println("  --     expiration date: " + expire);
        pw.println("  -->");
        pw.println();
        pw.println(xml);
        pw.println();
        pw.flush();
        pw.close();
        System.out.println("xml file created : " + filename);
    }

    private static void toPropsFile(String filename, License license, boolean append)
        throws IOException
    {
        String xml = license.toXmlString();
        String comp = license.getComponent();
        String licee = license.getLicensee();
        Date issue = license.getIssueDate();
        Date expire = license.getExpirationDate();
        int n = Math.min(comp.length(), 24);
        String name = comp.substring(0, n) + "-" + (new SimpleDateFormat("yyMMdd")).format(issue) + "-" + (new SecureRandom()).nextInt(10000);
        PrintWriter pw = getPW(filename, append);
        pw.println("#");
        pw.println("# XTrade Component License");
        pw.println("#           component: " + comp);
        pw.println("#            licensee: " + licee);
        pw.println("#          issue date: " + issue);
        pw.println("#     expiration date: " + expire);
        pw.println("#");
        pw.print(toName(license) + "=");
        for(StringTokenizer st = new StringTokenizer(xml, "\n\r"); st.hasMoreTokens(); pw.print("    " + st.nextToken()))
            pw.println("  \\");

        pw.println();
        pw.println();
        pw.flush();
        pw.close();
        System.out.println("properties file  : " + filename);
    }

    private static PrintWriter getPW(String filename, boolean append)
        throws IOException
    {
        int i = filename.lastIndexOf('\\');
        int j = filename.lastIndexOf('/');
        int k = Math.max(i, j);
        if(k >= 0)
        {
            String dirname = filename.substring(0, k);
            File dir = new File(dirname);
            dir.mkdirs();
        }
        FileWriter fw = new FileWriter(filename, append);
        return new PrintWriter(fw);
    }

    private static String dequote(String s)
    {
        if(s.startsWith("'"))
            return s.substring(1, s.length() - 1);
        else
            return s;
    }

    static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw new NoClassDefFoundError(x1.getMessage());
    }

    private static final DateFormat yyyymmdd = new SimpleDateFormat("yyyy-MM-dd");
    private static final int MOD = 92;
    private static final String usage;

    static 
    {
        usage = "usage:\n\ngenerating key pairs:\n\tjava " + (com.fitechlabs.xtrade.kernel.license.LicenseTool.class).getName() + " -genkeys pubfile privfile [javafile]" + "\n\t   pubfile    name of public key file to write" + "\n\t   privfile   name of private key file to write" + "\n\t   textfile   name of text file to generate containing public key as a string" + "\n\ngenerating licenses:" + "\n\tjava " + (com.fitechlabs.xtrade.kernel.license.LicenseTool.class).getName() + " required-params term=param optional-params" + "\n\nrequired-parameters:" + "\n\t   -component component  the component to license" + "\n\t   -licensee  licensee   the name of the licensee" + "\n\t   -keypath   filename   the file containing the private key" + "\nterm-param is either:" + "\n\t   -expires   date       the date on which the license expires in yyyy-mm-dd format" + "\n\t   -days      days       the duration of the license from today in days" + "\noptional-parameters:" + "\n\t   -attr      name=value a name-value attribute pair to apply to the license (may be repeated for more values)" + "\n\t   -sql       sqlfile    a file in which to store the results as SQL" + "\n\t   -xml       xmlfile    a file in which to store the results as XML" + "\n\t   -props     propsfile  a file in which to store the results as properties" + "\n\t   -append               indicates results should be appended to existing outfile'";
    }
}
