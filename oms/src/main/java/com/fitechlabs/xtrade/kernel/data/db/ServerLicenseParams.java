// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ServerLicenseParams.java

package com.fitechlabs.xtrade.kernel.data.db;

import com.fitechlabs.dbind.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

// Referenced classes of package com.fitechlabs.xtrade.kernel.data.db:
//            ServerLicenseRow, ServerLicensePK

public class ServerLicenseParams extends Params
    implements ServerLicenseRow
{

    public RowType getRowType()
    {
        return ServerLicenseRow.TYPE;
    }

    public String toString()
    {
        return "{license_id=" + license_id + "," + "component=" + component + "," + "licensee=" + licensee + "," + "expire_date=" + expire_date + "," + "license_xml=" + license_xml + "}";
    }

    public ServerLicenseParams()
    {
    }

    public ServerLicenseParams(ServerLicenseRow p_row)
    {
        license_id = p_row.getLicenseId();
        license_id_is_set = p_row.getLicenseIdIsSet();
        component = p_row.getComponent();
        component_is_set = p_row.getComponentIsSet();
        licensee = p_row.getLicensee();
        licensee_is_set = p_row.getLicenseeIsSet();
        expire_date = p_row.getExpireDate();
        expire_date_is_set = p_row.getExpireDateIsSet();
        license_xml = p_row.getLicenseXml();
        license_xml_is_set = p_row.getLicenseXmlIsSet();
    }

    public void markAllValuesAsSet()
    {
        super.markAllValuesAsSet();
        component_is_set = true;
        licensee_is_set = true;
        expire_date_is_set = true;
        license_xml_is_set = true;
    }

    public boolean equals(Object other)
    {
        if(!(other instanceof ServerLicenseRow))
            return false;
        else
            return fieldsEqual((ServerLicenseRow)other);
    }

    public boolean fieldsEqual(ServerLicenseRow row)
    {
        if(license_id == null)
        {
            if(row.getLicenseId() != null)
                return false;
        } else
        if(!license_id.equals(row.getLicenseId()))
            return false;
        if(component == null)
        {
            if(row.getComponent() != null)
                return false;
        } else
        if(!component.equals(row.getComponent()))
            return false;
        if(licensee == null)
        {
            if(row.getLicensee() != null)
                return false;
        } else
        if(!licensee.equals(row.getLicensee()))
            return false;
        if(expire_date == null)
        {
            if(row.getExpireDate() != null)
                return false;
        } else
        if(!expire_date.equals(row.getExpireDate()))
            return false;
        if(license_xml == null)
        {
            if(row.getLicenseXml() != null)
                return false;
        } else
        if(!license_xml.equals(row.getLicenseXml()))
            return false;
        return true;
    }

    public int hashCode()
    {
        return (license_id == null ? 0 : license_id.hashCode()) + (component == null ? 0 : component.hashCode()) + (licensee == null ? 0 : licensee.hashCode()) + (expire_date == null ? 0 : expire_date.hashCode()) + (license_xml == null ? 0 : license_xml.hashCode());
    }

    protected void assertValidForInsert()
        throws IllegalArgumentException
    {
        super.assertValidForInsert();
        if(!component_is_set)
            throw new IllegalArgumentException("non-nullable field 'component' must be set before inserting.");
        if(!licensee_is_set)
            throw new IllegalArgumentException("non-nullable field 'licensee' must be set before inserting.");
        if(!expire_date_is_set)
            throw new IllegalArgumentException("non-nullable field 'expire_date' must be set before inserting.");
        if(!license_xml_is_set)
            throw new IllegalArgumentException("non-nullable field 'license_xml' must be set before inserting.");
        else
            return;
    }

    public Map toInsertMap()
    {
        assertValidForInsert();
        Map map = super.toInsertMap();
        map.put("license_id", license_id);
        map.put("component", component);
        map.put("licensee", licensee);
        map.put("expire_date", expire_date);
        map.put("license_xml", license_xml);
        map.remove("rowid");
        return map;
    }

    public Map toUpdateMap()
    {
        Map map = super.toUpdateMap();
        if(component_is_set)
            map.put("component", component);
        if(licensee_is_set)
            map.put("licensee", licensee);
        if(expire_date_is_set)
            map.put("expire_date", expire_date);
        if(license_xml_is_set)
            map.put("license_xml", license_xml);
        return map;
    }

    public final String getLicenseId()
    {
        return license_id;
    }

    public final boolean getLicenseIdIsSet()
    {
        return license_id_is_set;
    }

    public final String getComponent()
    {
        return component;
    }

    public final boolean getComponentIsSet()
    {
        return component_is_set;
    }

    public final String getLicensee()
    {
        return licensee;
    }

    public final boolean getLicenseeIsSet()
    {
        return licensee_is_set;
    }

    public final Timestamp getExpireDate()
    {
        return expire_date;
    }

    public final boolean getExpireDateIsSet()
    {
        return expire_date_is_set;
    }

    public final String getLicenseXml()
    {
        return license_xml;
    }

    public final boolean getLicenseXmlIsSet()
    {
        return license_xml_is_set;
    }

    public PrimaryKey getPrimaryKey()
    {
        return new ServerLicensePK(license_id);
    }

    public final void setLicenseId(String p_licenseId)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            license_id = p_licenseId;
            license_id_is_set = true;
            return;
        }
    }

    public final void setComponent(String p_component)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            component = p_component;
            component_is_set = true;
            return;
        }
    }

    public final void setLicensee(String p_licensee)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            licensee = p_licensee;
            licensee_is_set = true;
            return;
        }
    }

    public final void setExpireDate(Timestamp p_expireDate)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            expire_date = p_expireDate;
            expire_date_is_set = true;
            return;
        }
    }

    public final void setExpireDate(Date date)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            expire_date = date != null ? new Timestamp(date.getTime()) : null;
            expire_date_is_set = true;
            return;
        }
    }

    public final void setLicenseXml(String p_licenseXml)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            license_xml = p_licenseXml;
            license_xml_is_set = true;
            return;
        }
    }

    public Object getColumn(String name)
    {
        if(name == null || name.length() <= 0)
            throw new IllegalArgumentException("name cannot be null.");
        switch(name.charAt(0))
        {
        default:
            break;

        case 99: // 'c'
            if(name.equals("component"))
                return component;
            break;

        case 101: // 'e'
            if(name.equals("expire_date"))
                return expire_date;
            break;

        case 108: // 'l'
            if(name.equals("license_id"))
                return license_id;
            if(name.equals("licensee"))
                return licensee;
            if(name.equals("license_xml"))
                return license_xml;
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
        default:
            break;

        case 99: // 'c'
            if(name.equals("component"))
                if(!(value instanceof String))
                {
                    throw new IllegalArgumentException("value for 'component' must be String: '" + value + "' is not.");
                } else
                {
                    component = (String)value;
                    component_is_set = true;
                    return;
                }
            break;

        case 101: // 'e'
            if(!name.equals("expire_date"))
                break;
            if(!(value instanceof Timestamp))
            {
                throw new IllegalArgumentException("value for 'expire_date' must be java.sql.Timestamp: '" + value + "' is not.");
            } else
            {
                expire_date = (Timestamp)value;
                expire_date_is_set = true;
                return;
            }

        case 108: // 'l'
            if(name.equals("license_id"))
                if(!(value instanceof String))
                {
                    throw new IllegalArgumentException("value for 'license_id' must be String: '" + value + "' is not.");
                } else
                {
                    license_id = (String)value;
                    license_id_is_set = true;
                    return;
                }
            if(name.equals("licensee"))
                if(!(value instanceof String))
                {
                    throw new IllegalArgumentException("value for 'licensee' must be String: '" + value + "' is not.");
                } else
                {
                    licensee = (String)value;
                    licensee_is_set = true;
                    return;
                }
            if(!name.equals("license_xml"))
                break;
            if(!(value instanceof String))
            {
                throw new IllegalArgumentException("value for 'license_xml' must be String: '" + value + "' is not.");
            } else
            {
                license_xml = (String)value;
                license_xml_is_set = true;
                return;
            }
        }
        throw new IllegalArgumentException("field '" + name + "' not found.");
    }

    public static final String TAGNAME = "row";
    public static final String PTYPE = "server_license";
    public static final RowType TYPE;
    public String license_id;
    public String component;
    public String licensee;
    public Timestamp expire_date;
    public String license_xml;
    private boolean license_id_is_set;
    private boolean component_is_set;
    private boolean licensee_is_set;
    private boolean expire_date_is_set;
    private boolean license_xml_is_set;

    static 
    {
        TYPE = ServerLicenseRow.TYPE;
    }
}
