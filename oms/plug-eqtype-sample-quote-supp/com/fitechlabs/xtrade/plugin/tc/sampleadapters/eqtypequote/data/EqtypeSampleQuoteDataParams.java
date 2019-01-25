// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqtypeSampleQuoteDataParams.java

package com.fitechlabs.xtrade.plugin.tc.sampleadapters.eqtypequote.data;

import com.fitechlabs.dbind.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.sampleadapters.eqtypequote.data:
//            EqtypeSampleQuoteDataRow, EqtypeSampleQuoteDataPK

public class EqtypeSampleQuoteDataParams extends Params
    implements EqtypeSampleQuoteDataRow
{

    public RowType getRowType()
    {
        return EqtypeSampleQuoteDataRow.TYPE;
    }

    public String toString()
    {
        return "{product_code=" + product_code + "," + "market_code=" + market_code + "," + "open_price=" + open_price + "," + "current_price=" + current_price + "," + "bid_price=" + bid_price + "," + "ask_price=" + ask_price + "," + "volume=" + volume + "," + "bid_size=" + bid_size + "," + "ask_size=" + ask_size + "," + "ytd_low_price=" + ytd_low_price + "," + "ytd_high_price=" + ytd_high_price + "," + "last_closing_price=" + last_closing_price + "," + "prev_trading_day_closing_price=" + prev_trading_day_closing_price + "," + "diff=" + diff + "," + "created_timestamp=" + created_timestamp + "," + "last_updated_timestamp=" + last_updated_timestamp + "}";
    }

    public EqtypeSampleQuoteDataParams()
    {
    }

    public EqtypeSampleQuoteDataParams(EqtypeSampleQuoteDataRow p_row)
    {
        product_code = p_row.getProductCode();
        product_code_is_set = p_row.getProductCodeIsSet();
        market_code = p_row.getMarketCode();
        market_code_is_set = p_row.getMarketCodeIsSet();
        if(!p_row.getOpenPriceIsNull())
            open_price = new Double(p_row.getOpenPrice());
        if(!p_row.getCurrentPriceIsNull())
            current_price = new Double(p_row.getCurrentPrice());
        if(!p_row.getBidPriceIsNull())
            bid_price = new Double(p_row.getBidPrice());
        if(!p_row.getAskPriceIsNull())
            ask_price = new Double(p_row.getAskPrice());
        if(!p_row.getVolumeIsNull())
            volume = new Double(p_row.getVolume());
        if(!p_row.getBidSizeIsNull())
            bid_size = new Double(p_row.getBidSize());
        if(!p_row.getAskSizeIsNull())
            ask_size = new Double(p_row.getAskSize());
        if(!p_row.getYtdLowPriceIsNull())
            ytd_low_price = new Double(p_row.getYtdLowPrice());
        if(!p_row.getYtdHighPriceIsNull())
            ytd_high_price = new Double(p_row.getYtdHighPrice());
        if(!p_row.getLastClosingPriceIsNull())
            last_closing_price = new Double(p_row.getLastClosingPrice());
        if(!p_row.getPrevTradingDayClosingPriceIsNull())
            prev_trading_day_closing_price = new Double(p_row.getPrevTradingDayClosingPrice());
        if(!p_row.getDiffIsNull())
            diff = new Double(p_row.getDiff());
        created_timestamp = p_row.getCreatedTimestamp();
        created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
        last_updated_timestamp = p_row.getLastUpdatedTimestamp();
        last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    }

    public void markAllValuesAsSet()
    {
        super.markAllValuesAsSet();
        created_timestamp_is_set = true;
        last_updated_timestamp_is_set = true;
    }

    public boolean equals(Object other)
    {
        if(!(other instanceof EqtypeSampleQuoteDataRow))
            return false;
        else
            return fieldsEqual((EqtypeSampleQuoteDataRow)other);
    }

    public boolean fieldsEqual(EqtypeSampleQuoteDataRow row)
    {
        if(product_code == null)
        {
            if(row.getProductCode() != null)
                return false;
        } else
        if(!product_code.equals(row.getProductCode()))
            return false;
        if(market_code == null)
        {
            if(row.getMarketCode() != null)
                return false;
        } else
        if(!market_code.equals(row.getMarketCode()))
            return false;
        if(open_price == null)
        {
            if(!row.getOpenPriceIsNull())
                return false;
        } else
        if(row.getOpenPriceIsNull() || open_price.doubleValue() != row.getOpenPrice())
            return false;
        if(current_price == null)
        {
            if(!row.getCurrentPriceIsNull())
                return false;
        } else
        if(row.getCurrentPriceIsNull() || current_price.doubleValue() != row.getCurrentPrice())
            return false;
        if(bid_price == null)
        {
            if(!row.getBidPriceIsNull())
                return false;
        } else
        if(row.getBidPriceIsNull() || bid_price.doubleValue() != row.getBidPrice())
            return false;
        if(ask_price == null)
        {
            if(!row.getAskPriceIsNull())
                return false;
        } else
        if(row.getAskPriceIsNull() || ask_price.doubleValue() != row.getAskPrice())
            return false;
        if(volume == null)
        {
            if(!row.getVolumeIsNull())
                return false;
        } else
        if(row.getVolumeIsNull() || volume.doubleValue() != row.getVolume())
            return false;
        if(bid_size == null)
        {
            if(!row.getBidSizeIsNull())
                return false;
        } else
        if(row.getBidSizeIsNull() || bid_size.doubleValue() != row.getBidSize())
            return false;
        if(ask_size == null)
        {
            if(!row.getAskSizeIsNull())
                return false;
        } else
        if(row.getAskSizeIsNull() || ask_size.doubleValue() != row.getAskSize())
            return false;
        if(ytd_low_price == null)
        {
            if(!row.getYtdLowPriceIsNull())
                return false;
        } else
        if(row.getYtdLowPriceIsNull() || ytd_low_price.doubleValue() != row.getYtdLowPrice())
            return false;
        if(ytd_high_price == null)
        {
            if(!row.getYtdHighPriceIsNull())
                return false;
        } else
        if(row.getYtdHighPriceIsNull() || ytd_high_price.doubleValue() != row.getYtdHighPrice())
            return false;
        if(last_closing_price == null)
        {
            if(!row.getLastClosingPriceIsNull())
                return false;
        } else
        if(row.getLastClosingPriceIsNull() || last_closing_price.doubleValue() != row.getLastClosingPrice())
            return false;
        if(prev_trading_day_closing_price == null)
        {
            if(!row.getPrevTradingDayClosingPriceIsNull())
                return false;
        } else
        if(row.getPrevTradingDayClosingPriceIsNull() || prev_trading_day_closing_price.doubleValue() != row.getPrevTradingDayClosingPrice())
            return false;
        if(diff == null)
        {
            if(!row.getDiffIsNull())
                return false;
        } else
        if(row.getDiffIsNull() || diff.doubleValue() != row.getDiff())
            return false;
        if(created_timestamp == null)
        {
            if(row.getCreatedTimestamp() != null)
                return false;
        } else
        if(!created_timestamp.equals(row.getCreatedTimestamp()))
            return false;
        if(last_updated_timestamp == null)
        {
            if(row.getLastUpdatedTimestamp() != null)
                return false;
        } else
        if(!last_updated_timestamp.equals(row.getLastUpdatedTimestamp()))
            return false;
        return true;
    }

    public int hashCode()
    {
        return (product_code == null ? 0 : product_code.hashCode()) + (market_code == null ? 0 : market_code.hashCode()) + (open_price == null ? 0 : open_price.hashCode()) + (current_price == null ? 0 : current_price.hashCode()) + (bid_price == null ? 0 : bid_price.hashCode()) + (ask_price == null ? 0 : ask_price.hashCode()) + (volume == null ? 0 : volume.hashCode()) + (bid_size == null ? 0 : bid_size.hashCode()) + (ask_size == null ? 0 : ask_size.hashCode()) + (ytd_low_price == null ? 0 : ytd_low_price.hashCode()) + (ytd_high_price == null ? 0 : ytd_high_price.hashCode()) + (last_closing_price == null ? 0 : last_closing_price.hashCode()) + (prev_trading_day_closing_price == null ? 0 : prev_trading_day_closing_price.hashCode()) + (diff == null ? 0 : diff.hashCode()) + (created_timestamp == null ? 0 : created_timestamp.hashCode()) + (last_updated_timestamp == null ? 0 : last_updated_timestamp.hashCode());
    }

    protected void assertValidForInsert()
        throws IllegalArgumentException
    {
        super.assertValidForInsert();
        if(!created_timestamp_is_set)
            throw new IllegalArgumentException("non-nullable field 'created_timestamp' must be set before inserting.");
        if(!last_updated_timestamp_is_set)
            throw new IllegalArgumentException("non-nullable field 'last_updated_timestamp' must be set before inserting.");
        else
            return;
    }

    public Map toInsertMap()
    {
        assertValidForInsert();
        Map map = super.toInsertMap();
        map.put("product_code", product_code);
        map.put("market_code", market_code);
        if(open_price != null)
            map.put("open_price", open_price);
        if(current_price != null)
            map.put("current_price", current_price);
        if(bid_price != null)
            map.put("bid_price", bid_price);
        if(ask_price != null)
            map.put("ask_price", ask_price);
        if(volume != null)
            map.put("volume", volume);
        if(bid_size != null)
            map.put("bid_size", bid_size);
        if(ask_size != null)
            map.put("ask_size", ask_size);
        if(ytd_low_price != null)
            map.put("ytd_low_price", ytd_low_price);
        if(ytd_high_price != null)
            map.put("ytd_high_price", ytd_high_price);
        if(last_closing_price != null)
            map.put("last_closing_price", last_closing_price);
        if(prev_trading_day_closing_price != null)
            map.put("prev_trading_day_closing_price", prev_trading_day_closing_price);
        if(diff != null)
            map.put("diff", diff);
        map.put("created_timestamp", created_timestamp);
        map.put("last_updated_timestamp", last_updated_timestamp);
        map.remove("rowid");
        return map;
    }

    public Map toUpdateMap()
    {
        Map map = super.toUpdateMap();
        map.put("open_price", open_price);
        map.put("current_price", current_price);
        map.put("bid_price", bid_price);
        map.put("ask_price", ask_price);
        map.put("volume", volume);
        map.put("bid_size", bid_size);
        map.put("ask_size", ask_size);
        map.put("ytd_low_price", ytd_low_price);
        map.put("ytd_high_price", ytd_high_price);
        map.put("last_closing_price", last_closing_price);
        map.put("prev_trading_day_closing_price", prev_trading_day_closing_price);
        map.put("diff", diff);
        if(created_timestamp_is_set)
            map.put("created_timestamp", created_timestamp);
        if(last_updated_timestamp_is_set)
            map.put("last_updated_timestamp", last_updated_timestamp);
        return map;
    }

    public final String getProductCode()
    {
        return product_code;
    }

    public final boolean getProductCodeIsSet()
    {
        return product_code_is_set;
    }

    public final String getMarketCode()
    {
        return market_code;
    }

    public final boolean getMarketCodeIsSet()
    {
        return market_code_is_set;
    }

    public final double getOpenPrice()
    {
        return open_price != null ? open_price.doubleValue() : 0.0D;
    }

    public final boolean getOpenPriceIsNull()
    {
        return open_price == null;
    }

    public final double getCurrentPrice()
    {
        return current_price != null ? current_price.doubleValue() : 0.0D;
    }

    public final boolean getCurrentPriceIsNull()
    {
        return current_price == null;
    }

    public final double getBidPrice()
    {
        return bid_price != null ? bid_price.doubleValue() : 0.0D;
    }

    public final boolean getBidPriceIsNull()
    {
        return bid_price == null;
    }

    public final double getAskPrice()
    {
        return ask_price != null ? ask_price.doubleValue() : 0.0D;
    }

    public final boolean getAskPriceIsNull()
    {
        return ask_price == null;
    }

    public final double getVolume()
    {
        return volume != null ? volume.doubleValue() : 0.0D;
    }

    public final boolean getVolumeIsNull()
    {
        return volume == null;
    }

    public final double getBidSize()
    {
        return bid_size != null ? bid_size.doubleValue() : 0.0D;
    }

    public final boolean getBidSizeIsNull()
    {
        return bid_size == null;
    }

    public final double getAskSize()
    {
        return ask_size != null ? ask_size.doubleValue() : 0.0D;
    }

    public final boolean getAskSizeIsNull()
    {
        return ask_size == null;
    }

    public final double getYtdLowPrice()
    {
        return ytd_low_price != null ? ytd_low_price.doubleValue() : 0.0D;
    }

    public final boolean getYtdLowPriceIsNull()
    {
        return ytd_low_price == null;
    }

    public final double getYtdHighPrice()
    {
        return ytd_high_price != null ? ytd_high_price.doubleValue() : 0.0D;
    }

    public final boolean getYtdHighPriceIsNull()
    {
        return ytd_high_price == null;
    }

    public final double getLastClosingPrice()
    {
        return last_closing_price != null ? last_closing_price.doubleValue() : 0.0D;
    }

    public final boolean getLastClosingPriceIsNull()
    {
        return last_closing_price == null;
    }

    public final double getPrevTradingDayClosingPrice()
    {
        return prev_trading_day_closing_price != null ? prev_trading_day_closing_price.doubleValue() : 0.0D;
    }

    public final boolean getPrevTradingDayClosingPriceIsNull()
    {
        return prev_trading_day_closing_price == null;
    }

    public final double getDiff()
    {
        return diff != null ? diff.doubleValue() : 0.0D;
    }

    public final boolean getDiffIsNull()
    {
        return diff == null;
    }

    public final Timestamp getCreatedTimestamp()
    {
        return created_timestamp;
    }

    public final boolean getCreatedTimestampIsSet()
    {
        return created_timestamp_is_set;
    }

    public final Timestamp getLastUpdatedTimestamp()
    {
        return last_updated_timestamp;
    }

    public final boolean getLastUpdatedTimestampIsSet()
    {
        return last_updated_timestamp_is_set;
    }

    public PrimaryKey getPrimaryKey()
    {
        return new EqtypeSampleQuoteDataPK(product_code, market_code);
    }

    public final void setProductCode(String p_productCode)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            product_code = p_productCode;
            product_code_is_set = true;
            return;
        }
    }

    public final void setMarketCode(String p_marketCode)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            market_code = p_marketCode;
            market_code_is_set = true;
            return;
        }
    }

    public final void setOpenPrice(double p_openPrice)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            open_price = new Double(p_openPrice);
            return;
        }
    }

    public final void setOpenPrice(Double p_openPrice)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            open_price = p_openPrice;
            return;
        }
    }

    public final void setCurrentPrice(double p_currentPrice)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            current_price = new Double(p_currentPrice);
            return;
        }
    }

    public final void setCurrentPrice(Double p_currentPrice)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            current_price = p_currentPrice;
            return;
        }
    }

    public final void setBidPrice(double p_bidPrice)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            bid_price = new Double(p_bidPrice);
            return;
        }
    }

    public final void setBidPrice(Double p_bidPrice)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            bid_price = p_bidPrice;
            return;
        }
    }

    public final void setAskPrice(double p_askPrice)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            ask_price = new Double(p_askPrice);
            return;
        }
    }

    public final void setAskPrice(Double p_askPrice)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            ask_price = p_askPrice;
            return;
        }
    }

    public final void setVolume(double p_volume)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            volume = new Double(p_volume);
            return;
        }
    }

    public final void setVolume(Double p_volume)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            volume = p_volume;
            return;
        }
    }

    public final void setBidSize(double p_bidSize)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            bid_size = new Double(p_bidSize);
            return;
        }
    }

    public final void setBidSize(Double p_bidSize)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            bid_size = p_bidSize;
            return;
        }
    }

    public final void setAskSize(double p_askSize)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            ask_size = new Double(p_askSize);
            return;
        }
    }

    public final void setAskSize(Double p_askSize)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            ask_size = p_askSize;
            return;
        }
    }

    public final void setYtdLowPrice(double p_ytdLowPrice)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            ytd_low_price = new Double(p_ytdLowPrice);
            return;
        }
    }

    public final void setYtdLowPrice(Double p_ytdLowPrice)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            ytd_low_price = p_ytdLowPrice;
            return;
        }
    }

    public final void setYtdHighPrice(double p_ytdHighPrice)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            ytd_high_price = new Double(p_ytdHighPrice);
            return;
        }
    }

    public final void setYtdHighPrice(Double p_ytdHighPrice)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            ytd_high_price = p_ytdHighPrice;
            return;
        }
    }

    public final void setLastClosingPrice(double p_lastClosingPrice)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            last_closing_price = new Double(p_lastClosingPrice);
            return;
        }
    }

    public final void setLastClosingPrice(Double p_lastClosingPrice)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            last_closing_price = p_lastClosingPrice;
            return;
        }
    }

    public final void setPrevTradingDayClosingPrice(double p_prevTradingDayClosingPrice)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            prev_trading_day_closing_price = new Double(p_prevTradingDayClosingPrice);
            return;
        }
    }

    public final void setPrevTradingDayClosingPrice(Double p_prevTradingDayClosingPrice)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            prev_trading_day_closing_price = p_prevTradingDayClosingPrice;
            return;
        }
    }

    public final void setDiff(double p_diff)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            diff = new Double(p_diff);
            return;
        }
    }

    public final void setDiff(Double p_diff)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            diff = p_diff;
            return;
        }
    }

    public final void setCreatedTimestamp(Timestamp p_createdTimestamp)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            created_timestamp = p_createdTimestamp;
            created_timestamp_is_set = true;
            return;
        }
    }

    public final void setCreatedTimestamp(Date date)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            created_timestamp = date != null ? new Timestamp(date.getTime()) : null;
            created_timestamp_is_set = true;
            return;
        }
    }

    public final void setLastUpdatedTimestamp(Timestamp p_lastUpdatedTimestamp)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            last_updated_timestamp = p_lastUpdatedTimestamp;
            last_updated_timestamp_is_set = true;
            return;
        }
    }

    public final void setLastUpdatedTimestamp(Date date)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            last_updated_timestamp = date != null ? new Timestamp(date.getTime()) : null;
            last_updated_timestamp_is_set = true;
            return;
        }
    }

    public Object getColumn(String name)
    {
        if(name == null || name.length() <= 0)
            throw new IllegalArgumentException("name cannot be null.");
        switch(name.charAt(0))
        {
        case 101: // 'e'
        case 102: // 'f'
        case 103: // 'g'
        case 104: // 'h'
        case 105: // 'i'
        case 106: // 'j'
        case 107: // 'k'
        case 110: // 'n'
        case 113: // 'q'
        case 114: // 'r'
        case 115: // 's'
        case 116: // 't'
        case 117: // 'u'
        case 119: // 'w'
        case 120: // 'x'
        default:
            break;

        case 97: // 'a'
            if(name.equals("ask_price"))
                return ask_price;
            if(name.equals("ask_size"))
                return ask_size;
            break;

        case 98: // 'b'
            if(name.equals("bid_price"))
                return bid_price;
            if(name.equals("bid_size"))
                return bid_size;
            break;

        case 99: // 'c'
            if(name.equals("current_price"))
                return current_price;
            if(name.equals("created_timestamp"))
                return created_timestamp;
            break;

        case 100: // 'd'
            if(name.equals("diff"))
                return diff;
            break;

        case 108: // 'l'
            if(name.equals("last_closing_price"))
                return last_closing_price;
            if(name.equals("last_updated_timestamp"))
                return last_updated_timestamp;
            break;

        case 109: // 'm'
            if(name.equals("market_code"))
                return market_code;
            break;

        case 111: // 'o'
            if(name.equals("open_price"))
                return open_price;
            break;

        case 112: // 'p'
            if(name.equals("product_code"))
                return product_code;
            if(name.equals("prev_trading_day_closing_price"))
                return prev_trading_day_closing_price;
            break;

        case 118: // 'v'
            if(name.equals("volume"))
                return volume;
            break;

        case 121: // 'y'
            if(name.equals("ytd_low_price"))
                return ytd_low_price;
            if(name.equals("ytd_high_price"))
                return ytd_high_price;
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
        case 101: // 'e'
        case 102: // 'f'
        case 103: // 'g'
        case 104: // 'h'
        case 105: // 'i'
        case 106: // 'j'
        case 107: // 'k'
        case 110: // 'n'
        case 113: // 'q'
        case 114: // 'r'
        case 115: // 's'
        case 116: // 't'
        case 117: // 'u'
        case 119: // 'w'
        case 120: // 'x'
        default:
            break;

        case 97: // 'a'
            if(name.equals("ask_price"))
                if(value != null && !(value instanceof Double))
                {
                    throw new IllegalArgumentException("value for 'ask_price' must be Double: '" + value + "' is not.");
                } else
                {
                    ask_price = (Double)value;
                    return;
                }
            if(name.equals("ask_size"))
                if(value != null && !(value instanceof Double))
                {
                    throw new IllegalArgumentException("value for 'ask_size' must be Double: '" + value + "' is not.");
                } else
                {
                    ask_size = (Double)value;
                    return;
                }
            break;

        case 98: // 'b'
            if(name.equals("bid_price"))
                if(value != null && !(value instanceof Double))
                {
                    throw new IllegalArgumentException("value for 'bid_price' must be Double: '" + value + "' is not.");
                } else
                {
                    bid_price = (Double)value;
                    return;
                }
            if(!name.equals("bid_size"))
                break;
            if(value != null && !(value instanceof Double))
            {
                throw new IllegalArgumentException("value for 'bid_size' must be Double: '" + value + "' is not.");
            } else
            {
                bid_size = (Double)value;
                return;
            }

        case 99: // 'c'
            if(name.equals("current_price"))
                if(value != null && !(value instanceof Double))
                {
                    throw new IllegalArgumentException("value for 'current_price' must be Double: '" + value + "' is not.");
                } else
                {
                    current_price = (Double)value;
                    return;
                }
            if(!name.equals("created_timestamp"))
                break;
            if(!(value instanceof Timestamp))
            {
                throw new IllegalArgumentException("value for 'created_timestamp' must be java.sql.Timestamp: '" + value + "' is not.");
            } else
            {
                created_timestamp = (Timestamp)value;
                created_timestamp_is_set = true;
                return;
            }

        case 100: // 'd'
            if(!name.equals("diff"))
                break;
            if(value != null && !(value instanceof Double))
            {
                throw new IllegalArgumentException("value for 'diff' must be Double: '" + value + "' is not.");
            } else
            {
                diff = (Double)value;
                return;
            }

        case 108: // 'l'
            if(name.equals("last_closing_price"))
                if(value != null && !(value instanceof Double))
                {
                    throw new IllegalArgumentException("value for 'last_closing_price' must be Double: '" + value + "' is not.");
                } else
                {
                    last_closing_price = (Double)value;
                    return;
                }
            if(!name.equals("last_updated_timestamp"))
                break;
            if(!(value instanceof Timestamp))
            {
                throw new IllegalArgumentException("value for 'last_updated_timestamp' must be java.sql.Timestamp: '" + value + "' is not.");
            } else
            {
                last_updated_timestamp = (Timestamp)value;
                last_updated_timestamp_is_set = true;
                return;
            }

        case 109: // 'm'
            if(!name.equals("market_code"))
                break;
            if(!(value instanceof String))
            {
                throw new IllegalArgumentException("value for 'market_code' must be String: '" + value + "' is not.");
            } else
            {
                market_code = (String)value;
                market_code_is_set = true;
                return;
            }

        case 111: // 'o'
            if(!name.equals("open_price"))
                break;
            if(value != null && !(value instanceof Double))
            {
                throw new IllegalArgumentException("value for 'open_price' must be Double: '" + value + "' is not.");
            } else
            {
                open_price = (Double)value;
                return;
            }

        case 112: // 'p'
            if(name.equals("product_code"))
                if(!(value instanceof String))
                {
                    throw new IllegalArgumentException("value for 'product_code' must be String: '" + value + "' is not.");
                } else
                {
                    product_code = (String)value;
                    product_code_is_set = true;
                    return;
                }
            if(!name.equals("prev_trading_day_closing_price"))
                break;
            if(value != null && !(value instanceof Double))
            {
                throw new IllegalArgumentException("value for 'prev_trading_day_closing_price' must be Double: '" + value + "' is not.");
            } else
            {
                prev_trading_day_closing_price = (Double)value;
                return;
            }

        case 118: // 'v'
            if(!name.equals("volume"))
                break;
            if(value != null && !(value instanceof Double))
            {
                throw new IllegalArgumentException("value for 'volume' must be Double: '" + value + "' is not.");
            } else
            {
                volume = (Double)value;
                return;
            }

        case 121: // 'y'
            if(name.equals("ytd_low_price"))
                if(value != null && !(value instanceof Double))
                {
                    throw new IllegalArgumentException("value for 'ytd_low_price' must be Double: '" + value + "' is not.");
                } else
                {
                    ytd_low_price = (Double)value;
                    return;
                }
            if(!name.equals("ytd_high_price"))
                break;
            if(value != null && !(value instanceof Double))
            {
                throw new IllegalArgumentException("value for 'ytd_high_price' must be Double: '" + value + "' is not.");
            } else
            {
                ytd_high_price = (Double)value;
                return;
            }
        }
        throw new IllegalArgumentException("field '" + name + "' not found.");
    }

    public static final String TAGNAME = "row";
    public static final String PTYPE = "eqtype_sample_quote_data";
    public static final RowType TYPE;
    public String product_code;
    public String market_code;
    public Double open_price;
    public Double current_price;
    public Double bid_price;
    public Double ask_price;
    public Double volume;
    public Double bid_size;
    public Double ask_size;
    public Double ytd_low_price;
    public Double ytd_high_price;
    public Double last_closing_price;
    public Double prev_trading_day_closing_price;
    public Double diff;
    public Timestamp created_timestamp;
    public Timestamp last_updated_timestamp;
    private boolean product_code_is_set;
    private boolean market_code_is_set;
    private boolean created_timestamp_is_set;
    private boolean last_updated_timestamp_is_set;

    static 
    {
        TYPE = EqtypeSampleQuoteDataRow.TYPE;
    }
}
