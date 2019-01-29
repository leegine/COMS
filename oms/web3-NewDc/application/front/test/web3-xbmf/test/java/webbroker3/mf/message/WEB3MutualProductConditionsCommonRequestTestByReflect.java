head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.57.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualProductConditionsCommonRequestTestByReflect.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/04/26 金傑 (中訊) 新規作成
 */
package webbroker3.mf.message;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3MutualProductConditionsCommonRequestTestByReflect extends TestBaseForMock
{

    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3MutualProductConditionsCommonRequestTestByReflect.class);

    private WEB3MutualProductConditionsCommonInfo mutualProductInfo = null;

    private WEB3MutualProductConditionsCommonRequest l_request = null;

    private boolean l_isSuccessPass = true;

    public WEB3MutualProductConditionsCommonRequestTestByReflect(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        this.mutualProductInfo = new WEB3MutualProductConditionsCommonInfo();
        this.l_request = new WEB3MutualProductConditionsCommonRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        this.mutualProductInfo = null;
        this.l_request = null;

    }

    public void testValidateNotPass()
    {
        final String STR_METHOD_NAME = "testValidateNotPass()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_isSuccessPass = false;
            this.setData();
            l_request.mutualProductInfo = this.mutualProductInfo;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_web3BaseException)
        {
            log.error(l_web3BaseException.getMessage(), l_web3BaseException);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01257, l_web3BaseException.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testValidatePass()
    {
        final String STR_METHOD_NAME = "testValidatePass()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_isSuccessPass = true;
            this.setData();
            l_request.mutualProductInfo = this.mutualProductInfo;
            l_request.validate();
        }
        catch (WEB3BaseException l_web3BaseException)
        {
            l_web3BaseException.printStackTrace();
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * 為字段賦?
     *
     */
    private void setData()
    {
        try
        {
            // 以下l_lisForEmpty設置主要使validate()方法@中一個大的if之前，用到的字段能?通過。
            // 注意只有l_isSuccessPass = false的情況下，才需要設置l_lisForEmpty
            List l_lisForEmpty = new ArrayList();
            l_lisForEmpty.add("web3TreatmentFlag");
            l_lisForEmpty.add("engProductName");
            l_lisForEmpty.add("buySelectable");
            l_lisForEmpty.add("sellSelectable");
            l_lisForEmpty.add("switchingSelectable");
            l_lisForEmpty.add("applySelectable");
            l_lisForEmpty.add("buyStartDate");
            l_lisForEmpty.add("buyEndDate");
            l_lisForEmpty.add("sellSwitchingStartDate");
            l_lisForEmpty.add("sellSwitchingEndDate");
            l_lisForEmpty.add("buyClaimStartDate");
            l_lisForEmpty.add("operationDate");
            l_lisForEmpty.add("applyAbleEndDate");
            l_lisForEmpty.add("buyClaimEndDate");
            l_lisForEmpty.add("applyAbleStartDate");
            String[] l_allKeyToStringForEmpty = (String[])l_lisForEmpty.toArray(new String[l_lisForEmpty.size()]);
            
            // 以下l_hashMapForNotEmpty的設置主要使validate()方法@中一個大的if通過之後
            // 用到的字段能?順利通過。
            HashMap l_hashMapForNotEmpty = new HashMap();
            l_hashMapForNotEmpty.put("web3TreatmentFlag","1");
            l_hashMapForNotEmpty.put("buySelectable","0");
            l_hashMapForNotEmpty.put("sellSelectable","0");
            l_hashMapForNotEmpty.put("switchingSelectable","0");
            l_hashMapForNotEmpty.put("applySelectable","0");
            l_hashMapForNotEmpty.put("todayBuyPossDiv","0");
            l_hashMapForNotEmpty.put("todaySellPossDiv","0");
            l_hashMapForNotEmpty.put("todaySwitchingPossDiv","0");
            l_hashMapForNotEmpty.put("todayApplyPossDiv","0");
            l_hashMapForNotEmpty.put("nextDayBuyPossDiv","0");
            l_hashMapForNotEmpty.put("nextDaySellPossDiv","0");
            l_hashMapForNotEmpty.put("nextDaySwitchingPossDiv","0");
            l_hashMapForNotEmpty.put("nextDayApplyPossDiv","0");
            l_hashMapForNotEmpty.put("nextDayApplyPossDiv","0");
            l_hashMapForNotEmpty.put("orderCloseStartTimeFull","3");
            l_hashMapForNotEmpty.put("orderCloseEndTimeFull","5");
            l_hashMapForNotEmpty.put("orderCloseEndTimeFull","500");
            l_hashMapForNotEmpty.put("orderCloseStartTimeHalf","6");
            l_hashMapForNotEmpty.put("orderCloseEndTimeHalf","8");
            l_hashMapForNotEmpty.put("mutualProductInfo","800");
            l_hashMapForNotEmpty.put("buyRestrictionDiv","0");
            l_hashMapForNotEmpty.put("deliveryVariation","0");
            l_hashMapForNotEmpty.put("unitTypeProductDiv","0");
            l_hashMapForNotEmpty.put("applyCommissionDiv","0");
            
            Arrays.sort(l_allKeyToStringForEmpty);
            Field l_fields[] = mutualProductInfo.getClass().getDeclaredFields();

            for (int i = 0; i < l_fields.length; i++)
            {
                String l_strFieldName = l_fields[i].getName();
                int l_search = Arrays.binarySearch(l_allKeyToStringForEmpty, l_strFieldName);
                
                if (l_fields[i].getType().equals(java.util.Date.class))
                {
                    
                    // not pass
                    if((!this.l_isSuccessPass) && (l_search>=0))
                    {
                        l_fields[i].set(this.mutualProductInfo, null);
                    }
                    // pass
                    else
                    {
                        l_fields[i].set(this.mutualProductInfo, GtlUtils.getSystemTimestamp());
                    }
                }
                else
                {
                    // not pass
                    if((!this.l_isSuccessPass) && (l_search>=0))
                    {
                        l_fields[i].set(this.mutualProductInfo, null);
                    }
                    // not pass
                    else if ((!this.l_isSuccessPass) && 
                            ("mutualProductCode".equals(l_fields[i].getName())))
                    {
                        l_fields[i].set(this.mutualProductInfo, "111");
                    }
                    // not pass
                    else if(!this.l_isSuccessPass)
                    {
                        l_fields[i].set(this.mutualProductInfo, "");
                    }
                    // pass
                    else
                    {
                        if(l_hashMapForNotEmpty.containsKey(l_fields[i].getName()))
                        {
                            l_fields[i].set(this.mutualProductInfo, l_hashMapForNotEmpty.get(l_fields[i].getName()));
                        }
                        else
                        {
                            l_fields[i].set(this.mutualProductInfo, l_fields[i].getName());
                        }
                    }
                }

            }
        }
        catch (Exception l_ex)
        {
            l_ex.printStackTrace();
            fail();
        }
    }
}
@
