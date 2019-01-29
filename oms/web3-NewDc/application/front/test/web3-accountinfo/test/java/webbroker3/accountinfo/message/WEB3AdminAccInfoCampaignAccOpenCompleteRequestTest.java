head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.38.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5404d9c264d0465;
filename	WEB3AdminAccInfoCampaignAccOpenCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ口座開設条件変更完了ﾘｸｴｽﾄテスト(WEB3AdminAccInfoCampaignAccOpenCompleteRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/2/6  齊珂(中訊) 新規作成
Revision History : 2007/03/20  吉麗ナ(中訊) 修正
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import test.util.TestSpecialClassUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAccInfoCampaignAccOpenCompleteRequestTest extends
    TestBaseForMock
{
    WEB3AdminAccInfoCampaignAccOpenCompleteRequest l_request = null;
    
    public final String CompaignName = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    public final String validCompaignName = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignAccOpenCompleteRequestTest.class);
    
    public WEB3AdminAccInfoCampaignAccOpenCompleteRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        l_request = new WEB3AdminAccInfoCampaignAccOpenCompleteRequest();
        l_request.commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        super.setUp();

    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testValidate301()
    {
        final String STR_METHOD_NAME = "testValidate301()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "3";
            l_request.validate(); 
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02710,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate301>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate302()
    {
        final String STR_METHOD_NAME = "testValidate302()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02718,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate302>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
 
    /**
     * キャンペーン名称のチェック
     * キャンペーン名称が未入力の場合  、『キャンペーン名称未入力エラー』例外をスローする
     *
     */
    public void testValidate303()
    {
        final String STR_METHOD_NAME = "testValidate303()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "1";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02712,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate303>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    /**
     * キャンペーン名称のチェック
     * キャンペーン名称が未入力の場合(="")  、『キャンペーン名称未入力エラー』例外をスローする
     * 
    */
    public void testValidate325()
    {
        final String STR_METHOD_NAME = "testValidate325()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "1";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = "";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02712,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate303>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate304()
    {
        final String STR_METHOD_NAME = "testValidate304()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.CompaignName;
            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
                l_request.commissionCampaignInfo.campaignName.length());
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02709,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate304>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate305()
    {
        final String STR_METHOD_NAME = "testValidate305()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
                l_request.commissionCampaignInfo.campaignName.length());
            
            l_request.commissionCampaignInfo.branchCode = "12";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00834,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate305>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate306()
    {
        final String STR_METHOD_NAME = "testValidate306()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
                l_request.commissionCampaignInfo.campaignName.length());
            
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.traderCode = "123456";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01912,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate306>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate307()
    {
        final String STR_METHOD_NAME = "testValidate307()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
                l_request.commissionCampaignInfo.campaignName.length());
            
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.traderCode = "12345";
            
            l_request.commissionCampaignInfo.targetPeriodFrom = null;
            l_request.commissionCampaignInfo.targetPeriodTo = null;
            
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = null;
            l_request.commissionCampaignInfo.accopenPassPeriodDay = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02713,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate307>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 口座開設日指定が選択された場合（口座開設経過期間（月）!=null && 口座開設経過期間（日）!=null ）
     * 口座開設経過期間（月）が０～１２の整数以外の場合、『対象期間エラー』例外をスローする。
     *
     */
    public void testValidate3081()
    {
        final String STR_METHOD_NAME = "testValidate3081()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
                l_request.commissionCampaignInfo.campaignName.length());
            
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.traderCode = "12345";
            
            //口座開設経過期間（月）が０～１２の整数以外の場合、『対象期間エラー』例外をスローする。
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = "13";
            l_request.commissionCampaignInfo.accopenPassPeriodDay="20";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02715,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate3081>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate3082()
    {
        final String STR_METHOD_NAME = "testValidate3082()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
                l_request.commissionCampaignInfo.campaignName.length());
            
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.traderCode = "12345";
            
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = "-1";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02080,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate3082>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate3091()
    {
        final String STR_METHOD_NAME = "testValidate3091()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
                l_request.commissionCampaignInfo.campaignName.length());
            
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.traderCode = "12345";
            
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = "10";
            l_request.commissionCampaignInfo.accopenPassPeriodDay = "32";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02715,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate3091>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate3092()
    {
        final String STR_METHOD_NAME = "testValidate3092()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
                l_request.commissionCampaignInfo.campaignName.length());
            
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.traderCode = "12345";
            
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = "10";
            l_request.commissionCampaignInfo.accopenPassPeriodDay = "32";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02715,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate3092>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate310()
    {
        final String STR_METHOD_NAME = "testValidate310()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
                l_request.commissionCampaignInfo.campaignName.length());
            
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.traderCode = "12345";
            
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02713,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate310>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 相關代碼變更，已經沒有這部分代碼了。
     *２-５-４） 口座開設日To!=nullの場合、
     *口座開設日Toに口座開設経過期間(月)と口座開設経過期間（日）
     *を加算した日付が過去日付の場合、『対象期間エラー』例外をスローする。
     *改成 口座開設経過期間（月）==０ && 口座開設経過期間（日）==０ の場合、
     */
    public void testValidate311()
    {
        final String STR_METHOD_NAME = "testValidate311()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
                l_request.commissionCampaignInfo.campaignName.length());
            
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.traderCode = "12345";
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = "0";
            l_request.commissionCampaignInfo.accopenPassPeriodDay = "0";
            
            
            l_request.commissionCampaignInfo.accountOpenDateTo = new Date("2005/02/05");
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = "0";
            l_request.commissionCampaignInfo.accopenPassPeriodDay = "0";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02715,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate311>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate312()
    {
        final String STR_METHOD_NAME = "testValidate312()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
                l_request.commissionCampaignInfo.campaignName.length());
            
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.traderCode = "12345";
            
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = "10";
            l_request.commissionCampaignInfo.accopenPassPeriodDay = "30";
            
            
            l_request.commissionCampaignInfo.accountOpenDateTo = new Date("2006/02/05");
            
            l_request.commissionCampaignInfo.targetPeriodFrom = null;
            
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = null;
            l_request.commissionCampaignInfo.accopenPassPeriodDay = null;
            
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02713,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate312>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate313()
    {
        final String STR_METHOD_NAME = "testValidate313()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
                l_request.commissionCampaignInfo.campaignName.length());
            
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.traderCode = "12345";
            
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = "10";
            l_request.commissionCampaignInfo.accopenPassPeriodDay = "30";
            
            
            l_request.commissionCampaignInfo.accountOpenDateTo = new Date("2006/02/05");
            

            
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = null;
            l_request.commissionCampaignInfo.accopenPassPeriodDay = null;
            
            l_request.commissionCampaignInfo.targetPeriodFrom = new Date("2007/02/05");
            l_request.commissionCampaignInfo.targetPeriodTo = new Date("2007/02/04");
            
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02715,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate313>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * ２-６-３） 対象期間Toの日付が過去日付の場合、『対象期間エラー』例外をスローする。<BR>
     * 代碼已經不存在了。
     * 將這個Case修改成口座開設区分のチェック
     * 
     * ２-６-２-１） 口座開設区分が 1:総合口座　@2:信用口座 3:先物OP口座　@4:FX口座　@5:中国株口座　@null 以外の場合
     * 『口座開設区分エラー』例外をスローする。<BR>
     *
     */
    public void testValidate314()
    {
        final String STR_METHOD_NAME = "testValidate314()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
                l_request.commissionCampaignInfo.campaignName.length());
            
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.traderCode = "12345";
            
            l_request.commissionCampaignInfo.accountOpenDateTo = new Date("2006/02/05");
            

            
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = "10";
            l_request.commissionCampaignInfo.accopenPassPeriodDay = "1";
            
            l_request.commissionCampaignInfo.targetPeriodFrom = null;;
            l_request.commissionCampaignInfo.targetPeriodTo = null;
            l_request.commissionCampaignInfo.accountOpenDiv = "6";
            
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02719,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate314>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate315()
    {
        final String STR_METHOD_NAME = "testValidate315()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
                l_request.commissionCampaignInfo.campaignName.length());
            
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.traderCode = "12345";
            
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = "10";
            l_request.commissionCampaignInfo.accopenPassPeriodDay = "30";
            
            
            l_request.commissionCampaignInfo.accountOpenDateTo = new Date("2006/02/06");
            

            
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = null;
            l_request.commissionCampaignInfo.accopenPassPeriodDay = null;
            
            l_request.commissionCampaignInfo.targetPeriodFrom = new Date("2007/02/04");
            
            Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
            int l_intMonth = l_datBizDate.getMonth() + 1;
            l_datBizDate.setMonth(l_intMonth);
            int l_intYear = l_datBizDate.getYear() + 1;
            l_datBizDate.setYear(l_intYear);
            l_request.commissionCampaignInfo.targetPeriodTo = l_datBizDate;
            
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02080,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate315>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate316()
    {
        final String STR_METHOD_NAME = "testValidate316()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
                l_request.commissionCampaignInfo.campaignName.length());
            
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.traderCode = "12345";
            
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = "10";
            l_request.commissionCampaignInfo.accopenPassPeriodDay = "30";
            
            
            l_request.commissionCampaignInfo.accountOpenDateTo = new Date("2006/02/06");
            

            
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = null;
            l_request.commissionCampaignInfo.accopenPassPeriodDay = null;
            
            l_request.commissionCampaignInfo.targetPeriodFrom = new Date("2007/02/03");
            Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
            int l_intMonth = l_datBizDate.getMonth() + 1;
            l_datBizDate.setMonth(l_intMonth);
            int l_intYear = l_datBizDate.getYear() + 1;
            l_datBizDate.setYear(l_intYear);
            l_request.commissionCampaignInfo.targetPeriodTo = l_datBizDate;

            l_request.commissionCampaignInfo.collectRate = "110";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02082,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate316>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 代碼修正，無法@實現２-６-２） 口座開設区分のチェック
     *  口座開設区分が 1:総合口座　@2:信用口座 3:先物OP口座　@4:FX口座　@5:中国株口座　@null 以外の場合、
     *  『口座開設区分エラー』例外をスローする。
     *
     */
//    public void testValidate317()
//    {
//        final String STR_METHOD_NAME = "testValidate317()";
//        log.entering(STR_METHOD_NAME);
//        
//        try
//        {
//            l_request.updateFlag = "0";
//            String[] l_strs = new String[1];
//            l_strs[0] = "01";
//            l_request.commissionCampaignInfo.itemCode = l_strs;
//            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
//            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
//                l_request.commissionCampaignInfo.campaignName.length());
//            
//            l_request.commissionCampaignInfo.branchCode = "123";
//            l_request.commissionCampaignInfo.traderCode = "12345";
//            
//            l_request.commissionCampaignInfo.accopenPassPeriodMonth = "10";
//            l_request.commissionCampaignInfo.accopenPassPeriodDay = "30";
//            
//            
//            l_request.commissionCampaignInfo.accountOpenDateTo = new Date("2006/02/06");
//            
//
//            
//            l_request.commissionCampaignInfo.accopenPassPeriodMonth = null;
//            l_request.commissionCampaignInfo.accopenPassPeriodDay = null;
//            
//            l_request.commissionCampaignInfo.targetPeriodFrom = new Date("2007/02/03");
//            Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
//            int l_intMonth = l_datBizDate.getMonth() + 1;
//            l_datBizDate.setMonth(l_intMonth);
//            int l_intYear = l_datBizDate.getYear() + 1;
//            l_datBizDate.setYear(l_intYear);
//            l_request.commissionCampaignInfo.targetPeriodTo = l_datBizDate;
//
//            l_request.commissionCampaignInfo.collectRate = "100";
//            l_request.commissionCampaignInfo.accountOpenDiv = "6";
//            l_request.validate();
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02719,l_ex.getErrorInfo());
//            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate317>>>>>>>>>>>>>>>test pass !!");
//        } 
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
    
    
    public void testValidate318()
    {
        final String STR_METHOD_NAME = "testValidate318()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
                l_request.commissionCampaignInfo.campaignName.length());
            
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.traderCode = "12345";
            
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = "10";
            l_request.commissionCampaignInfo.accopenPassPeriodDay = "30";
            
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = null;
            l_request.commissionCampaignInfo.accopenPassPeriodDay = null;
            
            l_request.commissionCampaignInfo.targetPeriodFrom = new Date("2007/02/03");
            Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
            int l_intMonth = l_datBizDate.getMonth() + 1;
            l_datBizDate.setMonth(l_intMonth);
            int l_intYear = l_datBizDate.getYear() + 1;
            l_datBizDate.setYear(l_intYear);
            l_request.commissionCampaignInfo.targetPeriodTo = l_datBizDate;

            l_request.commissionCampaignInfo.collectRate = "10";
            l_request.commissionCampaignInfo.accountOpenDiv = "3";
            
            l_request.commissionCampaignInfo.accountOpenDateFrom = new Date("2006/02/07");
            l_request.commissionCampaignInfo.accountOpenDateTo = new Date("2006/02/06");
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02720,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate318>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate319()
    {
        final String STR_METHOD_NAME = "testValidate319()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "0";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
                l_request.commissionCampaignInfo.campaignName.length());
            
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.traderCode = "12345";
            
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = "10";
            l_request.commissionCampaignInfo.accopenPassPeriodDay = "30";
            

            
            l_request.commissionCampaignInfo.targetPeriodTo = new Date("2007/03/06");
            l_request.commissionCampaignInfo.collectRate = "10";
            l_request.commissionCampaignInfo.accountOpenDiv = "3";
            
            l_request.commissionCampaignInfo.accountOpenDateFrom = new Date("2006/02/07");
            l_request.commissionCampaignInfo.accountOpenDateTo = new Date("2006/02/06");
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02720,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate319>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
 
    /**
     * 代碼“口座開設日Toが過去日付の場合、口座開設日エラー』例外をスローする。“
     * 部分刪除，此case取消。
     *
     */
//    public void testValidate320()
//    {
//        final String STR_METHOD_NAME = "testValidate320()";
//        log.entering(STR_METHOD_NAME);
//        
//        try
//        {
//            l_request.updateFlag = "0";
//            String[] l_strs = new String[1];
//            l_strs[0] = "01";
//            l_request.commissionCampaignInfo.itemCode = l_strs;
//            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
//            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
//                l_request.commissionCampaignInfo.campaignName.length());
//            
//            l_request.commissionCampaignInfo.branchCode = "123";
//            l_request.commissionCampaignInfo.traderCode = "12345";
//            
//            l_request.commissionCampaignInfo.accopenPassPeriodMonth = "10";
//            l_request.commissionCampaignInfo.accopenPassPeriodDay = "30";
//            
//
//            
//            l_request.commissionCampaignInfo.targetPeriodTo = new Date("2007/03/06");
//            l_request.commissionCampaignInfo.collectRate = "10";
//            l_request.commissionCampaignInfo.accountOpenDiv = "3";
//            
//            l_request.commissionCampaignInfo.accountOpenDateFrom = new Date("2006/01/06");
//            l_request.commissionCampaignInfo.accountOpenDateTo = new Date("2006/01/06");
//            l_request.validate();
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02720,l_ex.getErrorInfo());
//            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate320>>>>>>>>>>>>>>>test pass !!");
//        } 
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
    
    public void testValidate321()
    {
        final String STR_METHOD_NAME = "testValidate321()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "2";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            l_request.commissionCampaignInfo.campaignId = null;
            l_request.commissionCampaignInfo.registType = "1";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02716,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate321>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testValidate322()
    {
        final String STR_METHOD_NAME = "testValidate322()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "1";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            l_request.commissionCampaignInfo.campaignId = "021";
            
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.traderCode = "12345";
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = null;
            l_request.commissionCampaignInfo.accopenPassPeriodDay = null;
            l_request.commissionCampaignInfo.targetPeriodTo = new Date("2007/03/06");
            l_request.commissionCampaignInfo.collectRate = "10";
            l_request.commissionCampaignInfo.accountOpenDiv = "3";
            l_request.commissionCampaignInfo.targetPeriodFrom = new Date("2007/02/05");
            l_request.commissionCampaignInfo.targetPeriodTo = new Date("2007/02/08");
            l_request.commissionCampaignInfo.accountOpenDateFrom = new Date("2006/02/07");
            l_request.commissionCampaignInfo.accountOpenDateTo = new Date("2006/02/22");
            
            
            l_request.commissionCampaignInfo.registType = "1";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02722,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate322>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate323()
    {
        final String STR_METHOD_NAME = "testValidate323()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.updateFlag = "2";
            l_request.commissionCampaignInfo.campaignId = "012";
            l_request.commissionCampaignInfo.registType = "1";
            String[] l_strs = new String[1];
            l_strs[0] = "01";
            l_request.commissionCampaignInfo.itemCode = l_strs;
            l_request.commissionCampaignInfo.campaignName = this.validCompaignName;
            log.info("l_request.commissionCampaignInfo.campaignName.length === " + 
                l_request.commissionCampaignInfo.campaignName.length());
            
            l_request.commissionCampaignInfo.branchCode = "123";
            l_request.commissionCampaignInfo.traderCode = "12345";
            
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = "10";
            l_request.commissionCampaignInfo.accopenPassPeriodDay = "30";
            

            

            
            l_request.commissionCampaignInfo.accopenPassPeriodMonth = null;
            l_request.commissionCampaignInfo.accopenPassPeriodDay = null;
            
            l_request.commissionCampaignInfo.targetPeriodFrom = new Date("2007/02/05");
            l_request.commissionCampaignInfo.targetPeriodTo = new Date("2007/03/06");
            l_request.commissionCampaignInfo.collectRate = "10";
            l_request.commissionCampaignInfo.accountOpenDiv = "3";
            
            l_request.commissionCampaignInfo.accountOpenDateFrom = new Date("2006/02/01");
            l_request.commissionCampaignInfo.accountOpenDateTo = new Date("2006/02/14");
            l_request.validate();
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate323>>>>>>>>>>>>>>>test pass !!");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidate323>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate324()
    {
        final String STR_METHOD_NAME = "testValidate324()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestSpecialClassUtility.testCreateResponse(WEB3AdminAccInfoCampaignAccOpenCompleteRequest.class);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
    
@
