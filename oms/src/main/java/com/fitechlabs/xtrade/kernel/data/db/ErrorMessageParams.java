// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ErrorMessageParams.java

package com.fitechlabs.xtrade.kernel.data.db;

import com.fitechlabs.dbind.*;
import java.util.Map;

// Referenced classes of package com.fitechlabs.xtrade.kernel.data.db:
//            ErrorMessageRow, ErrorMessagePK

public class ErrorMessageParams extends Params
    implements ErrorMessageRow
{

    public RowType getRowType()
    {
        return ErrorMessageRow.TYPE;
    }

    public String toString()
    {
        return "{error_message_id=" + error_message_id + "," + "error_code=" + error_code + "," + "error_tag=" + error_tag + "," + "localized_text=" + localized_text + "," + "initial_text=" + initial_text + "," + "context_text=" + context_text + "," + "error_class=" + error_class + "}";
    }

    public ErrorMessageParams()
    {
    }

    public ErrorMessageParams(ErrorMessageRow p_row)
    {
        error_message_id = p_row.getErrorMessageId();
        error_message_id_is_set = p_row.getErrorMessageIdIsSet();
        error_code = p_row.getErrorCode();
        error_code_is_set = p_row.getErrorCodeIsSet();
        error_tag = p_row.getErrorTag();
        error_tag_is_set = p_row.getErrorTagIsSet();
        localized_text = p_row.getLocalizedText();
        initial_text = p_row.getInitialText();
        initial_text_is_set = p_row.getInitialTextIsSet();
        context_text = p_row.getContextText();
        error_class = p_row.getErrorClass();
        error_class_is_set = p_row.getErrorClassIsSet();
    }

    public void markAllValuesAsSet()
    {
        super.markAllValuesAsSet();
        error_code_is_set = true;
        error_tag_is_set = true;
        initial_text_is_set = true;
        error_class_is_set = true;
    }

    public boolean equals(Object other)
    {
        if(!(other instanceof ErrorMessageRow))
            return false;
        else
            return fieldsEqual((ErrorMessageRow)other);
    }

    public boolean fieldsEqual(ErrorMessageRow row)
    {
        if(error_message_id != row.getErrorMessageId())
            return false;
        if(error_code == null)
        {
            if(row.getErrorCode() != null)
                return false;
        } else
        if(!error_code.equals(row.getErrorCode()))
            return false;
        if(error_tag == null)
        {
            if(row.getErrorTag() != null)
                return false;
        } else
        if(!error_tag.equals(row.getErrorTag()))
            return false;
        if(localized_text == null)
        {
            if(row.getLocalizedText() != null)
                return false;
        } else
        if(!localized_text.equals(row.getLocalizedText()))
            return false;
        if(initial_text == null)
        {
            if(row.getInitialText() != null)
                return false;
        } else
        if(!initial_text.equals(row.getInitialText()))
            return false;
        if(context_text == null)
        {
            if(row.getContextText() != null)
                return false;
        } else
        if(!context_text.equals(row.getContextText()))
            return false;
        if(error_class == null)
        {
            if(row.getErrorClass() != null)
                return false;
        } else
        if(!error_class.equals(row.getErrorClass()))
            return false;
        return true;
    }

    public int hashCode()
    {
        return (int)error_message_id + (error_code == null ? 0 : error_code.hashCode()) + (error_tag == null ? 0 : error_tag.hashCode()) + (localized_text == null ? 0 : localized_text.hashCode()) + (initial_text == null ? 0 : initial_text.hashCode()) + (context_text == null ? 0 : context_text.hashCode()) + (error_class == null ? 0 : error_class.hashCode());
    }

    protected void assertValidForInsert()
        throws IllegalArgumentException
    {
        super.assertValidForInsert();
        if(!error_code_is_set)
            throw new IllegalArgumentException("non-nullable field 'error_code' must be set before inserting.");
        if(!error_tag_is_set)
            throw new IllegalArgumentException("non-nullable field 'error_tag' must be set before inserting.");
        if(!initial_text_is_set)
            throw new IllegalArgumentException("non-nullable field 'initial_text' must be set before inserting.");
        if(!error_class_is_set)
            throw new IllegalArgumentException("non-nullable field 'error_class' must be set before inserting.");
        else
            return;
    }

    public Map toInsertMap()
    {
        assertValidForInsert();
        Map map = super.toInsertMap();
        map.put("error_message_id", new Long(error_message_id));
        map.put("error_code", error_code);
        map.put("error_tag", error_tag);
        if(localized_text != null)
            map.put("localized_text", localized_text);
        map.put("initial_text", initial_text);
        if(context_text != null)
            map.put("context_text", context_text);
        map.put("error_class", error_class);
        map.remove("rowid");
        return map;
    }

    public Map toUpdateMap()
    {
        Map map = super.toUpdateMap();
        if(error_code_is_set)
            map.put("error_code", error_code);
        if(error_tag_is_set)
            map.put("error_tag", error_tag);
        map.put("localized_text", localized_text);
        if(initial_text_is_set)
            map.put("initial_text", initial_text);
        map.put("context_text", context_text);
        if(error_class_is_set)
            map.put("error_class", error_class);
        return map;
    }

    public final long getErrorMessageId()
    {
        return error_message_id;
    }

    public final boolean getErrorMessageIdIsSet()
    {
        return error_message_id_is_set;
    }

    public final String getErrorCode()
    {
        return error_code;
    }

    public final boolean getErrorCodeIsSet()
    {
        return error_code_is_set;
    }

    public final String getErrorTag()
    {
        return error_tag;
    }

    public final boolean getErrorTagIsSet()
    {
        return error_tag_is_set;
    }

    public final String getLocalizedText()
    {
        return localized_text;
    }

    public final String getInitialText()
    {
        return initial_text;
    }

    public final boolean getInitialTextIsSet()
    {
        return initial_text_is_set;
    }

    public final String getContextText()
    {
        return context_text;
    }

    public final String getErrorClass()
    {
        return error_class;
    }

    public final boolean getErrorClassIsSet()
    {
        return error_class_is_set;
    }

    public PrimaryKey getPrimaryKey()
    {
        return new ErrorMessagePK(error_message_id);
    }

    public final void setErrorMessageId(long p_errorMessageId)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            error_message_id = p_errorMessageId;
            error_message_id_is_set = true;
            return;
        }
    }

    public final void setErrorCode(String p_errorCode)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            error_code = p_errorCode;
            error_code_is_set = true;
            return;
        }
    }

    public final void setErrorTag(String p_errorTag)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            error_tag = p_errorTag;
            error_tag_is_set = true;
            return;
        }
    }

    public final void setLocalizedText(String p_localizedText)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            localized_text = p_localizedText;
            return;
        }
    }

    public final void setInitialText(String p_initialText)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            initial_text = p_initialText;
            initial_text_is_set = true;
            return;
        }
    }

    public final void setContextText(String p_contextText)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            context_text = p_contextText;
            return;
        }
    }

    public final void setErrorClass(String p_errorClass)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            error_class = p_errorClass;
            error_class_is_set = true;
            return;
        }
    }

    public Object getColumn(String name)
    {
        if(name == null || name.length() <= 0)
            throw new IllegalArgumentException("name cannot be null.");
        switch(name.charAt(0))
        {
        case 100: // 'd'
        case 102: // 'f'
        case 103: // 'g'
        case 104: // 'h'
        case 106: // 'j'
        case 107: // 'k'
        default:
            break;

        case 99: // 'c'
            if(name.equals("context_text"))
                return context_text;
            break;

        case 101: // 'e'
            if(name.equals("error_message_id"))
                return new Long(error_message_id);
            if(name.equals("error_code"))
                return error_code;
            if(name.equals("error_tag"))
                return error_tag;
            if(name.equals("error_class"))
                return error_class;
            break;

        case 105: // 'i'
            if(name.equals("initial_text"))
                return initial_text;
            break;

        case 108: // 'l'
            if(name.equals("localized_text"))
                return localized_text;
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
        case 100: // 'd'
        case 102: // 'f'
        case 103: // 'g'
        case 104: // 'h'
        case 106: // 'j'
        case 107: // 'k'
        default:
            break;

        case 99: // 'c'
            if(name.equals("context_text"))
                if(value != null && !(value instanceof String))
                {
                    throw new IllegalArgumentException("value for 'context_text' must be String: '" + value + "' is not.");
                } else
                {
                    context_text = (String)value;
                    return;
                }
            break;

        case 101: // 'e'
            if(name.equals("error_message_id"))
                if(!(value instanceof Long))
                {
                    throw new IllegalArgumentException("value for 'error_message_id' must be Long: '" + value + "' is not.");
                } else
                {
                    error_message_id = ((Long)value).longValue();
                    error_message_id_is_set = true;
                    return;
                }
            if(name.equals("error_code"))
                if(!(value instanceof String))
                {
                    throw new IllegalArgumentException("value for 'error_code' must be String: '" + value + "' is not.");
                } else
                {
                    error_code = (String)value;
                    error_code_is_set = true;
                    return;
                }
            if(name.equals("error_tag"))
                if(!(value instanceof String))
                {
                    throw new IllegalArgumentException("value for 'error_tag' must be String: '" + value + "' is not.");
                } else
                {
                    error_tag = (String)value;
                    error_tag_is_set = true;
                    return;
                }
            if(!name.equals("error_class"))
                break;
            if(!(value instanceof String))
            {
                throw new IllegalArgumentException("value for 'error_class' must be String: '" + value + "' is not.");
            } else
            {
                error_class = (String)value;
                error_class_is_set = true;
                return;
            }

        case 105: // 'i'
            if(!name.equals("initial_text"))
                break;
            if(!(value instanceof String))
            {
                throw new IllegalArgumentException("value for 'initial_text' must be String: '" + value + "' is not.");
            } else
            {
                initial_text = (String)value;
                initial_text_is_set = true;
                return;
            }

        case 108: // 'l'
            if(!name.equals("localized_text"))
                break;
            if(value != null && !(value instanceof String))
            {
                throw new IllegalArgumentException("value for 'localized_text' must be String: '" + value + "' is not.");
            } else
            {
                localized_text = (String)value;
                return;
            }
        }
        throw new IllegalArgumentException("field '" + name + "' not found.");
    }

    public static final String TAGNAME = "row";
    public static final String PTYPE = "error_message";
    public static final RowType TYPE;
    public long error_message_id;
    public String error_code;
    public String error_tag;
    public String localized_text;
    public String initial_text;
    public String context_text;
    public String error_class;
    private boolean error_message_id_is_set;
    private boolean error_code_is_set;
    private boolean error_tag_is_set;
    private boolean initial_text_is_set;
    private boolean error_class_is_set;

    static 
    {
        TYPE = ErrorMessageRow.TYPE;
    }
}
