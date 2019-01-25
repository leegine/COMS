// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ServerConfigPK.java

package com.fitechlabs.xtrade.kernel.data.db;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import java.util.StringTokenizer;

// Referenced classes of package com.fitechlabs.xtrade.kernel.data.db:
//            ServerConfigRow

public class ServerConfigPK
    implements PrimaryKey
{

    public RowType getRowType()
    {
        return ServerConfigRow.TYPE;
    }

    public ServerConfigPK()
    {
        m_id = null;
    }

    public ServerConfigPK(String p_configTitle, String p_configCateg, String p_configName)
    {
        m_id = null;
        config_title = p_configTitle;
        config_categ = p_configCateg;
        config_name = p_configName;
    }

    public static ServerConfigPK fromString(String pkValueString)
        throws NumberFormatException
    {
        ServerConfigPK pk = new ServerConfigPK();
        StringTokenizer st = new StringTokenizer(pkValueString, ",");
        pk.config_title = st.nextToken();
        pk.config_categ = st.nextToken();
        pk.config_name = st.nextToken();
        pk.m_id = pkValueString;
        return pk;
    }

    public String toString()
    {
        if(m_id == null)
            m_id = config_title + "," + config_categ + "," + config_name;
        return m_id;
    }

    public boolean equals(Object other)
    {
        if(other == null || !(other instanceof ServerConfigPK))
            return false;
        ServerConfigPK o = (ServerConfigPK)other;
        if(config_title == null)
        {
            if(o.config_title != null)
                return false;
        } else
        if(!config_title.equals(o.config_title))
            return false;
        if(config_categ == null)
        {
            if(o.config_categ != null)
                return false;
        } else
        if(!config_categ.equals(o.config_categ))
            return false;
        if(config_name == null)
        {
            if(o.config_name != null)
                return false;
        } else
        if(!config_name.equals(o.config_name))
            return false;
        return true;
    }

    public int hashCode()
    {
        return (config_title == null ? 0 : config_title.hashCode()) + (config_categ == null ? 0 : config_categ.hashCode()) + (config_name == null ? 0 : config_name.hashCode());
    }

    public static final String TAGNAME = "pk";
    public static final String PTYPE = "server_config";
    public String config_title;
    public String config_categ;
    public String config_name;
    private String m_id;
}
