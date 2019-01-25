// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ServerConfigParams.java

package com.fitechlabs.xtrade.kernel.data.db;

import com.fitechlabs.dbind.*;
import java.util.Map;

// Referenced classes of package com.fitechlabs.xtrade.kernel.data.db:
//            ServerConfigRow, ServerConfigPK

public class ServerConfigParams extends Params
    implements ServerConfigRow
{

    public RowType getRowType()
    {
        return ServerConfigRow.TYPE;
    }

    public String toString()
    {
        return "{config_title=" + config_title + "," + "config_categ=" + config_categ + "," + "config_name=" + config_name + "," + "config_value=" + config_value + "}";
    }

    public ServerConfigParams()
    {
    }

    public ServerConfigParams(ServerConfigRow p_row)
    {
        config_title = p_row.getConfigTitle();
        config_title_is_set = p_row.getConfigTitleIsSet();
        config_categ = p_row.getConfigCateg();
        config_categ_is_set = p_row.getConfigCategIsSet();
        config_name = p_row.getConfigName();
        config_name_is_set = p_row.getConfigNameIsSet();
        config_value = p_row.getConfigValue();
    }

    public void markAllValuesAsSet()
    {
        super.markAllValuesAsSet();
    }

    public boolean equals(Object other)
    {
        if(!(other instanceof ServerConfigRow))
            return false;
        else
            return fieldsEqual((ServerConfigRow)other);
    }

    public boolean fieldsEqual(ServerConfigRow row)
    {
        if(config_title == null)
        {
            if(row.getConfigTitle() != null)
                return false;
        } else
        if(!config_title.equals(row.getConfigTitle()))
            return false;
        if(config_categ == null)
        {
            if(row.getConfigCateg() != null)
                return false;
        } else
        if(!config_categ.equals(row.getConfigCateg()))
            return false;
        if(config_name == null)
        {
            if(row.getConfigName() != null)
                return false;
        } else
        if(!config_name.equals(row.getConfigName()))
            return false;
        if(config_value == null)
        {
            if(row.getConfigValue() != null)
                return false;
        } else
        if(!config_value.equals(row.getConfigValue()))
            return false;
        return true;
    }

    public int hashCode()
    {
        return (config_title == null ? 0 : config_title.hashCode()) + (config_categ == null ? 0 : config_categ.hashCode()) + (config_name == null ? 0 : config_name.hashCode()) + (config_value == null ? 0 : config_value.hashCode());
    }

    protected void assertValidForInsert()
        throws IllegalArgumentException
    {
        super.assertValidForInsert();
    }

    public Map toInsertMap()
    {
        assertValidForInsert();
        Map map = super.toInsertMap();
        map.put("config_title", config_title);
        map.put("config_categ", config_categ);
        map.put("config_name", config_name);
        if(config_value != null)
            map.put("config_value", config_value);
        map.remove("rowid");
        return map;
    }

    public Map toUpdateMap()
    {
        Map map = super.toUpdateMap();
        map.put("config_value", config_value);
        return map;
    }

    public final String getConfigTitle()
    {
        return config_title;
    }

    public final boolean getConfigTitleIsSet()
    {
        return config_title_is_set;
    }

    public final String getConfigCateg()
    {
        return config_categ;
    }

    public final boolean getConfigCategIsSet()
    {
        return config_categ_is_set;
    }

    public final String getConfigName()
    {
        return config_name;
    }

    public final boolean getConfigNameIsSet()
    {
        return config_name_is_set;
    }

    public final String getConfigValue()
    {
        return config_value;
    }

    public PrimaryKey getPrimaryKey()
    {
        return new ServerConfigPK(config_title, config_categ, config_name);
    }

    public final void setConfigTitle(String p_configTitle)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            config_title = p_configTitle;
            config_title_is_set = true;
            return;
        }
    }

    public final void setConfigCateg(String p_configCateg)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            config_categ = p_configCateg;
            config_categ_is_set = true;
            return;
        }
    }

    public final void setConfigName(String p_configName)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            config_name = p_configName;
            config_name_is_set = true;
            return;
        }
    }

    public final void setConfigValue(String p_configValue)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            config_value = p_configValue;
            return;
        }
    }

    public Object getColumn(String name)
    {
        if(name == null || name.length() <= 0)
            throw new IllegalArgumentException("name cannot be null.");
        switch(name.charAt(0))
        {
        case 99: // 'c'
            if(name.equals("config_title"))
                return config_title;
            if(name.equals("config_categ"))
                return config_categ;
            if(name.equals("config_name"))
                return config_name;
            if(name.equals("config_value"))
                return config_value;
            break;
        }
        throw new IllegalArgumentException("field '" + name + "' not found.");
    }

    public void setColumn(String name, Object value)
    {
        if(!mutable())
            throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
        if(name == null || name.length() <= 0)
            throw new IllegalArgumentException("name cannot be null.");
        switch(name.charAt(0))
        {
        case 99: // 'c'
            if(name.equals("config_title"))
                if(!(value instanceof String))
                {
                    throw new IllegalArgumentException("value for 'config_title' must be String: '" + value + "' is not.");
                } else
                {
                    config_title = (String)value;
                    config_title_is_set = true;
                    return;
                }
            if(name.equals("config_categ"))
                if(!(value instanceof String))
                {
                    throw new IllegalArgumentException("value for 'config_categ' must be String: '" + value + "' is not.");
                } else
                {
                    config_categ = (String)value;
                    config_categ_is_set = true;
                    return;
                }
            if(name.equals("config_name"))
                if(!(value instanceof String))
                {
                    throw new IllegalArgumentException("value for 'config_name' must be String: '" + value + "' is not.");
                } else
                {
                    config_name = (String)value;
                    config_name_is_set = true;
                    return;
                }
            if(name.equals("config_value"))
                if(value != null && !(value instanceof String))
                {
                    throw new IllegalArgumentException("value for 'config_value' must be String: '" + value + "' is not.");
                } else
                {
                    config_value = (String)value;
                    return;
                }
            break;
        }
        throw new IllegalArgumentException("field '" + name + "' not found.");
    }

    public static final String TAGNAME = "row";
    public static final String PTYPE = "server_config";
    public static final RowType TYPE;
    public String config_title;
    public String config_categ;
    public String config_name;
    public String config_value;
    private boolean config_title_is_set;
    private boolean config_categ_is_set;
    private boolean config_name_is_set;

    static 
    {
        TYPE = ServerConfigRow.TYPE;
    }
}
