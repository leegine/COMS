// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GenInterfaces.java

package com.fitechlabs.dbind.gen;

import java.io.*;
import java.net.URL;
import java.util.*;

// Referenced classes of package com.fitechlabs.dbind.gen:
//            DataSpec, SqlReader, ExcelCsvReader, DatabaseReader, 
//            TableSpec, SummaryWriter, ExcelCsvWriter, SqlWriter, 
//            CodeWriter, Settings, JavaDocResourceBundleHelper, ImportsReader, 
//            ColumnSpec, WritesPlugin, WritesTriggers

public class GenInterfaces
{

    private static void info(String s)
    {
        System.out.println(s);
    }

    private static void debug(String s1)
    {
    }

    private static void error(String s)
    {
        System.err.println(s);
    }

    public static void main(String args[])
    {
        GenInterfaces generator = new GenInterfaces(args);
        generator.generate();
    }

    public GenInterfaces(String args[])
    {
        int firstArg;
        Exception e;
        databaseParams = null;
        databaseClass = null;
        databaseUser = null;
        databasePassword = null;
        inputSqlFile = null;
        outputSqlFile = null;
        inputCsvFile = null;
        outputCsvFile = null;
        outputInfoFile = null;
        tablesFile = null;
        tablesArg = null;
        summary = false;
        wait = false;
        noejb = false;
        buildType = "";
        buildId = null;
        enums = null;
        processor = null;
        imports = "";
        triggers = null;
        dependencies = null;
        pluginInfoDirectory = null;
        encoding = null;
        databaseParams = null;
        databaseClass = null;
        databaseUser = null;
        databasePassword = null;
        inputSqlFile = null;
        outputSqlFile = null;
        inputCsvFile = null;
        outputCsvFile = null;
        outputInfoFile = null;
        tablesFile = null;
        tablesArg = null;
        summary = false;
        wait = false;
        noejb = false;
        buildType = "";
        buildId = null;
        enums = null;
        processor = "session";
        imports = "";
        triggers = null;
        dependencies = null;
        pluginInfoDirectory = null;
        InputStream is = null;
        firstArg = 0;
        try
        {
            if(args[0] != null && !args[0].startsWith("-"))
            {
                propertiesFile = args[0];
                debug("gendao: Trying to load properties file " + propertiesFile);
                is = new FileInputStream(propertiesFile);
                loadProperties(is);
                firstArg = 1;
            } else
            {
                URL propsUrl = ClassLoader.getSystemResource(propertiesFile);
                if(propsUrl != null)
                {
                    System.out.println("loading " + propertiesFile + " from " + propsUrl.toExternalForm());
                    is = propsUrl.openStream();
                    loadProperties(is);
                }
            }
        }
        // Misplaced declaration of an exception variable
        catch(Exception e)
        {
            System.err.println("gendao: Couldn't load properties: " + e);
        }
        finally
        {
            if(is != null)
                try
                {
                    is.close();
                }
                catch(Exception e) { }
        }
        int i = firstArg;
_L2:
        if(i >= args.length)
            break MISSING_BLOCK_LABEL_1239;
        if(args[i].equalsIgnoreCase("-dev"))
            Settings.setDevPath(args[++i]);
        else
        if(args[i].equalsIgnoreCase("-pkg"))
            Settings.setSubPackageName(args[++i].replace('/', '.'));
        else
        if(args[i].equalsIgnoreCase("-sql"))
        {
            inputSqlFile = args[++i];
            buildType = "sql-";
            buildId = inputSqlFile;
        } else
        if(args[i].equalsIgnoreCase("-w"))
            outputSqlFile = args[++i];
        else
        if(args[i].equalsIgnoreCase("-db"))
        {
            databaseParams = args[++i];
            buildType = "db-";
        } else
        if(args[i].equalsIgnoreCase("-class"))
            databaseClass = args[++i];
        else
        if(args[i].equalsIgnoreCase("-user"))
            databaseUser = args[++i];
        else
        if(args[i].equalsIgnoreCase("-password"))
            databasePassword = args[++i];
        else
        if(args[i].equalsIgnoreCase("-dir"))
        {
            Settings.rootDirectory = args[++i];
            System.out.println("root directory is set to " + Settings.rootDirectory);
        } else
        if(args[i].equalsIgnoreCase("-f"))
        {
            inputCsvFile = args[++i];
            Settings.setBuildIdentifier("csv-" + inputCsvFile.substring(0, inputCsvFile.indexOf(".")));
        } else
        if(args[i].equalsIgnoreCase("-o"))
            outputCsvFile = args[++i];
        else
        if(args[i].equalsIgnoreCase("-s"))
            summary = true;
        else
        if(args[i].equalsIgnoreCase("-bld"))
            buildId = args[++i];
        else
        if(args[i].equalsIgnoreCase("-info"))
            outputInfoFile = args[++i];
        else
        if(args[i].equalsIgnoreCase("-noejb"))
            noejb = true;
        else
        if(args[i].equalsIgnoreCase("-tablesfile"))
            tablesFile = args[++i];
        else
        if(args[i].equalsIgnoreCase("-tables"))
            tablesArg = args[++i];
        else
        if(args[i].equalsIgnoreCase("-dbname"))
            Settings.setDatabaseClassName(args[++i]);
        else
        if(args[i].equalsIgnoreCase("-wait"))
            wait = true;
        else
        if(args[i].equalsIgnoreCase("-processor"))
        {
            processor = args[++i];
            Settings.setProcessor(processor);
        } else
        if(args[i].equalsIgnoreCase("-imports"))
            imports = args[++i];
        else
        if(args[i].equalsIgnoreCase("-triggers"))
            triggers = args[++i];
        else
        if(args[i].equalsIgnoreCase("-dependencies"))
            dependencies = args[++i];
        else
        if(args[i].equalsIgnoreCase("-plugin-info-dir"))
        {
            pluginInfoDirectory = args[++i];
            debug("plugin info directory is set to " + pluginInfoDirectory);
        } else
        {
            System.out.println("unknown argument " + args[i] + "\n" + usage);
            if(wait)
                try
                {
                    System.in.read(new byte[1]);
                }
                catch(Exception e) { }
            return;
        }
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        Settings.setBuildIdentifier(buildType + (buildId == null ? "" : buildId));
        break MISSING_BLOCK_LABEL_1313;
        i;
        error("exception parsing input args, " + i);
        error(usage);
        return;
    }

    private void loadProperties(InputStream is)
        throws Exception
    {
        Properties p = new Properties();
        p.load(is);
        String value = p.getProperty("dev");
        if(value != null)
        {
            Settings.setDevPath(value);
            debug("gendao: using properties value dev=" + value);
        }
        value = p.getProperty("pkg");
        if(value != null)
        {
            value = value.replace('/', '.');
            Settings.setSubPackageName(value);
            debug("gendao: using properties value pkg=" + value);
        }
        value = p.getProperty("sql");
        if(value != null)
        {
            inputSqlFile = value;
            buildType = "sql-";
            buildId = inputSqlFile;
            debug("gendao: using properties value sql=" + value);
        }
        value = p.getProperty("tablesfile");
        if(value != null)
        {
            tablesFile = value;
            debug("gendao: using properties value tablesfile=" + value);
        }
        value = p.getProperty("enums");
        if(value != null)
        {
            enums = value;
            debug("gendao: using properties value enums=" + value);
        }
        value = p.getProperty("types");
        if(value != null)
        {
            if(enums == null)
                enums = value;
            else
                enums = enums + " " + value;
            debug("gendao: using properties value types=" + value);
        }
        value = p.getProperty("w");
        if(value != null)
        {
            outputSqlFile = value;
            debug("gendao: using properties value outputSqlFile=" + value);
        }
        value = p.getProperty("class");
        if(value != null)
        {
            databaseClass = value;
            debug("gendao: using properties value databaseClass=" + value);
        }
        value = p.getProperty("db");
        if(value != null)
        {
            databaseParams = value;
            buildType = "db-";
            debug("gendao: using properties value databaseParams=" + value);
        }
        value = p.getProperty("dbname");
        if(value != null)
        {
            Settings.setDatabaseClassName(value);
            debug("gendao: using properties value dbname=" + value);
        }
        value = p.getProperty("processor");
        if(value != null)
        {
            processor = value;
            Settings.setProcessor(value);
            debug("gendao: using properties value processor=" + value);
        }
        value = p.getProperty("imports");
        if(value != null)
        {
            imports = value;
            debug("gendao: using properties value imports=" + value);
        }
        value = p.getProperty("triggers");
        if(value != null)
        {
            triggers = value;
            debug("gendao: using properties value triggers=" + value);
        }
        value = p.getProperty("dependencies");
        if(value != null)
        {
            dependencies = value;
            debug("gendao: using properties value dependencies=" + value);
        }
        value = p.getProperty("javadoc.lang");
        if(value != null)
            JavaDocResourceBundleHelper.setLocale(new Locale(value, ""));
        info("codegen: using Locale " + JavaDocResourceBundleHelper.locale);
        value = p.getProperty("javadoc.encoding");
        if(value != null)
        {
            encoding = value;
            info("codegen: using encoding " + encoding);
        }
    }

    public void generate()
    {
        try
        {
            debug("Fitech DAO auto-generator: starting");
            debug("version: '" + Settings.buildVersion() + "'");
            if(outputInfoFile != null)
                writeOutputInfoFile(outputInfoFile);
            DataSpec dataSpec = null;
            Hashtable tables = null;
            Hashtable tableAttributes = null;
            Properties variables = null;
            if(tablesFile != null)
            {
                tables = new Hashtable();
                tableAttributes = new Hashtable();
                variables = getTableNames(tablesFile, tables, tableAttributes);
            } else
            if(propertiesFile != null)
            {
                tables = new Hashtable();
                tableAttributes = new Hashtable();
                variables = getTableNames(propertiesFile, tables, tableAttributes);
            }
            dataSpec = new DataSpec();
            ImportsReader.readImports(dataSpec, imports);
            if(inputSqlFile != null)
            {
                inputSqlFile = Settings.getAbsPath(inputSqlFile);
                debug("reading table data from SQL file " + inputSqlFile);
                (new SqlReader(inputSqlFile)).readDataSpec(dataSpec, tables);
            } else
            if(inputCsvFile != null)
            {
                inputCsvFile = Settings.getAbsPath(inputCsvFile);
                debug("reading table data from CSV file " + inputCsvFile);
                (new ExcelCsvReader(inputCsvFile)).readDataSpec(dataSpec);
            } else
            {
                DatabaseReader dbReader = null;
                if(databaseParams != null)
                {
                    debug("reading table using class = " + databaseClass + ", " + "connect = " + databaseParams + ", " + "user = " + databaseUser + ", " + "password = " + databasePassword);
                    dbReader = new DatabaseReader(databaseClass, databaseParams, databaseUser, databasePassword);
                } else
                {
                    debug("Reading table from database using default parameters");
                    dbReader = new DatabaseReader();
                }
                dbReader.readDataSpec(dataSpec, tables);
            }
            if(dataSpec == null)
            {
                error("null dataSpec, can't continue");
                throw new Exception("null dataSpec");
            }
            if(tables != null)
            {
                TableSpec ts;
                String attributes;
                for(Enumeration tse = dataSpec.getTableSpecElements(); tse.hasMoreElements(); ts.setAttributes(attributes))
                {
                    ts = (TableSpec)tse.nextElement();
                    tables.remove(ts.asHeader());
                    attributes = (String)tableAttributes.get(ts.asHeader());
                }

                if(tables.size() > 0)
                {
                    System.err.println("WARNING: COULD NOT FIND TABLE DEFINITIONS FOR: ");
                    for(Enumeration tne = tables.keys(); tne.hasMoreElements(); System.err.println("\t" + tne.nextElement()));
                }
            }
            try
            {
                if(enums != null)
                {
                    String className;
                    String defaultValue;
                    ColumnSpec colspec;
                    for(StringTokenizer st = new StringTokenizer(enums, " \t\n\r"); st.hasMoreTokens(); colspec.setCustomType(className, defaultValue))
                    {
                        String token = st.nextToken();
                        StringTokenizer spec = new StringTokenizer(token);
                        String tbl = spec.nextToken(".");
                        String fld = spec.nextToken(".:");
                        String rhs = spec.nextToken(": \t\n\r");
                        StringTokenizer t2 = new StringTokenizer(rhs, ";");
                        className = null;
                        defaultValue = null;
                        while(t2.hasMoreTokens()) 
                        {
                            String pair = t2.nextToken();
                            StringTokenizer t3 = new StringTokenizer(pair, "=");
                            String name = t3.nextToken();
                            String valu = t3.nextToken();
                            if("class".equals(name))
                                className = valu;
                            else
                            if("default".equals(name))
                                defaultValue = valu;
                            else
                                throw new Exception("unknown enum spec name: '" + name + "'");
                        }
                        if(className == null)
                            throw new Exception("missing enum spec class");
                        TableSpec tblspec = dataSpec.getTableNamed(tbl);
                        if(tblspec == null)
                            throw new Exception("table name not known: '" + tbl + "'");
                        colspec = tblspec.getColumnByName(fld);
                        if(colspec == null)
                            throw new Exception("table '" + tbl + "' column name not known: '" + fld + "'");
                    }

                }
            }
            catch(Exception e)
            {
                error("Bad 'enum' format: " + e);
            }
            if(summary)
            {
                debug("Summary:");
                (new SummaryWriter()).write(dataSpec);
            }
            if(outputCsvFile != null)
            {
                debug("Writing data specification to CSV file " + outputCsvFile);
                (new ExcelCsvWriter(outputCsvFile)).write(dataSpec);
            }
            if(outputSqlFile != null)
                (new SqlWriter(outputSqlFile)).write(dataSpec);
            if(!noejb)
            {
                (new CodeWriter()).write(dataSpec, encoding);
                WritesPlugin.write(dataSpec, Settings.getDatabaseClassName(), processor, dependencies, pluginInfoDirectory);
            }
            if(triggers != null)
                WritesTriggers.writeTriggers(dataSpec, triggers, variables);
        }
        catch(Exception e)
        {
            System.err.println("GenInterfaces: failed.  " + e);
            e.printStackTrace();
        }
        if(wait)
        {
            debug("press return to continue...");
            try
            {
                System.in.read(new byte[1]);
            }
            catch(Exception e) { }
        }
    }

    private void writeOutputInfoFile(String filename)
        throws IOException
    {
        String dir = filename.substring(0, filename.lastIndexOf("/"));
        (new File(dir)).mkdirs();
        debug("creating info file " + filename);
        PrintWriter pw = new PrintWriter(new FileOutputStream(filename));
        pw.println("# Build version string");
        pw.println("#");
        pw.println("daoBuildVersion=" + Settings.buildVersion());
        pw.println();
        pw.println("# Build number");
        pw.println("#");
        pw.println("buildNumber=" + Settings.buildNumber);
        pw.println();
        pw.println("# The type of input");
        pw.println("#");
        if(inputSqlFile != null)
        {
            pw.println("buildType=sql");
            pw.println();
            pw.println("# The schema file used");
            pw.println("#");
            pw.println("sqlFileName=" + inputSqlFile);
        } else
        if(inputCsvFile != null)
        {
            pw.println("buildType=csv");
            pw.println();
            pw.println("# The schema file used");
            pw.println("#");
            pw.println("csvFileName=" + inputCsvFile);
        } else
        if(databaseParams != null)
        {
            pw.println("buildType=database");
            pw.println();
            pw.println("# The schema file used");
            pw.println("#");
            pw.println("databaseParams=" + databaseParams);
        } else
        {
            pw.println("buildType=default");
        }
        pw.println();
        pw.close();
    }

    public Properties getTableNames(String fileName, Hashtable tableNames, Hashtable tableAttributes)
    {
        Properties props;
        props = new Properties();
        props.load(new FileInputStream(Settings.getAbsPath(fileName)));
        String prop = props.getProperty("tables");
        if(prop == null)
            debug("No property 'tables' found in file '" + fileName + "'");
        StringTokenizer st = new StringTokenizer(prop, ", \n\t");
        do
        {
            if(!st.hasMoreTokens())
                break;
            StringTokenizer tableSpec = new StringTokenizer(st.nextToken(), ":");
            String tableName = tableSpec.nextToken();
            tableNames.put(tableName, Boolean.TRUE);
            if(tableSpec.hasMoreTokens())
            {
                String attributes = tableSpec.nextToken();
                tableAttributes.put(tableName, attributes);
            }
        } while(true);
        return props;
        Exception ex;
        ex;
        error("Exception getting properties file '" + fileName + "': " + ex);
        ex.printStackTrace(System.err);
        throw new RuntimeException("Exception getting properties file '" + fileName + "': " + ex);
    }

    static String propertiesFile = null;
    private static final boolean DEBUG = false;
    static String usage = "usage: gendao [properties_file] [-dev devpath] [-sql sqlfile] [-w sqlfile] [-db connectString] [-f incsvfile] [-o outcsvfile] [-s] [-t] [-bld string] [-info infofile] [-noejb] [-wait]\n\t-dev\t'devpath' specifies the \"home\" path where files are located\n\t-pkg\t'subpackage' specifies the subpackage to use on output\n\t-sql\t'sqlfile' specifies an input file (or comma-separated list) to parse for input\n\t-w 'sqlfile' specifies an output sql file to write\n\t-db\t'connectString' specify database parameters from which to read the table info\n\t-class\t'class' specifies the jdbc Driver class to load\n\t-user\t'user' specifies a database username to use\n\t-f\t'csvfile' specifies an input file to read in .csv format for the table info\n\t-o\t'csvfile' specifies an output file to write in .csv format after reading\n\t-dir 'dir' specifies a directory relative to which all codegen properties are\n\t\t this parameter affects ONLY data read from a CODEGEN file\n\t-s\tprint a summary of the table info to the screen after reading\n\t-info\toutput version info to file 'infofile'\n\t-noejb\tdon't create any ejb beans source files\n\t-tablesfile\tnames a file containing a 'tables' property listing tables to include\n\t-tables\tnames the tables to include\n\t-dbname\tname to use for the top-level database interface\n\t-processor\tname of the processor to associate with this type\n\t\tdependencies\tother plugin classes to plug first, as a comma-delimited list\n\t-imports\tlist of imports to use to resolve enums, superclasses, and foreign key classes\n\t-triggers\tname of triggers file to generate, if any";
    String databaseParams;
    String databaseClass;
    String databaseUser;
    String databasePassword;
    String inputSqlFile;
    String outputSqlFile;
    String inputCsvFile;
    String outputCsvFile;
    String outputInfoFile;
    String tablesFile;
    String tablesArg;
    boolean summary;
    boolean wait;
    boolean noejb;
    String buildType;
    String buildId;
    String enums;
    String processor;
    String imports;
    String triggers;
    String dependencies;
    String pluginInfoDirectory;
    String encoding;

}
