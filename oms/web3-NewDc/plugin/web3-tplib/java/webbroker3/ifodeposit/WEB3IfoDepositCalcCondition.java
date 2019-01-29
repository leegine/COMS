head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.38.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositCalcCondition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �؋����v�Z�����N���X(WEB3IfoDepositCalcCondition.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/10/21 �R�c�@@��i(FLJ) �V�K�쐬
 Revesion History : 2007/06/28 hijikata(SRA) �[��Ή� ���f��No.056�B, No.058, No.059, No.082
 Revesion History : 2007/08/01 hijikata(SRA) �[��Ή� ���f��No.097
 Revision History : 2007/08/13 k.yamashita(SRA)  �[��Ή� U03048,U03049
 Revision History : 2007/10/18 k.yamashita(SRA)  ���捞�v��No.021, ���f��No.117
 
 
 */
package webbroker3.ifodeposit;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranchIndexDealtCond;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchIndexDealtCondParams;
import webbroker3.gentrade.data.BranchIndexDealtCondRow;
import webbroker3.ifodeposit.define.WEB3IfoReservedDateDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.define.WEB3SessionTypeDef;

/**
 * (�؋����v�Z����)<BR>
 * 
 * ��ЁE���X��/�ڋq�ʏ؋����v�Z������\���N���X�B<BR>
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3IfoDepositCalcCondition
{

    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoDepositCalcCondition.class);

    /**
     * (�c�Ɠ�[T-1..T+2])<BR>
     * �c�Ɠ�[T-1..T+2]<BR>
     * T-1�AT+0�AT+1�AT+2�̉c�Ɠ��B<BR>
     */
    private Date[] bizDates;

    /**
     * (�؋����v�Z���)<BR>
     * �؋����v�Z����B
     */
    private int ifoDepositCalcBaseDate;

    /**
     * (�V�K���]�͉\�t���O)<BR>
     * 
     * �Y���ڋq���敨OP�V�K���]�͉\�ł��邩(�]�͒�~�ɂȂ��Ă��Ȃ���)�𔻒肷��t���O
     * �B<BR>
     * �@@�E�]�͉\�̏ꍇ�F�@@true<BR>
     * �@@�E�]�͕s�̏ꍇ�F�@@false<BR>
     */
    private boolean newOpenTradingPowerAvailableFlag = false;

    /**
     * (���A�������؋����v�Z���{�t���O)<BR>
     * ���A�������؋����v�Z�����{���Ă��邩�𔻒肷��t���O�B<BR>
     * �@@�E���{���Ă���ꍇ�F�@@true<BR>
     * �@@�E���{���Ă��Ȃ��ꍇ�F�@@false<BR>
     */
    private boolean realPriceIfoDepositCalcFlag = false;

    /**
     * (�Ȉ�SPAN�v�Z���{�t���O)<BR>
     * �Ȉ�SPAN�v�Z�����{���Ă��邩�𔻒肷��t���O�B<BR>
     * �@@�E���{���Ă���ꍇ�F�@@true <BR>
     * �@@�E���{���Ă��Ȃ��ꍇ�F�@@false<BR>
     */
    private boolean simpleSPANCalcFlag = false;

    /**
     * (SPAN�g���u���t���O)<BR>
     * 
     * PC-SAPN���g���u�������ǂ����𔻒肷��t���O�B<BR>
     * �@@�E�g���u���̏ꍇ�F�@@true<BR>
     * �@@�E����̏ꍇ�F�@@false<BR>
     */
    private boolean SPANTroubleFlag = false;

    /**
     * (�K��؋����ύX�t���O)<BR>
     * <BR>
     * �K��؋������ύX���ꂽ���ǂ����𔻒肷��t���O�B<BR>
     * �E�K��؋������ύX����Ă���ꍇ�F�@@true<BR>
     * �E�K��؋������ύX����Ă��Ȃ��ꍇ�F�@@false<BR>
     */
    private boolean IfoDepositPerUnitChangeFlag = false;

    /**
     * (�؋����s�����[�����M�σt���O)<BR>
     * <BR>
     * T+0�̏؋����s�����[�������M���ꂽ���ǂ����𔻒肷��t���O�B<BR>
     * �E�؋����s�����[�����M�ς̏ꍇ�F�@@true<BR>
     * �E�؋����s�����[�������M�̏ꍇ�F�@@false<BR>
     */
    private boolean IfoDepositMailFlag = false;

    /**
     * (���Z�l�����M�σt���O)<BR>
     * <BR>
     * ���Z�l���񂪎�M���ꂽ���ǂ����𔻒肷��t���O�B<BR>
     * �E���Z�l�����M�ς̏ꍇ�F�@@true<BR>
     * �E���Z�l�����M���ς̏ꍇ�F�@@false<BR>
     */
    private boolean quickReportReceivedFlag = false;

    /**
     * (�[����{�t���O)<BR>
     * <BR>
     * �[����{���邩�ǂ����𔻒肷��t���O�B<BR>
     * �E�[����{�̏ꍇ�F�@@true<BR>
     * �E�[�ꖢ���{�̏ꍇ�F�@@false<BR>
     */
    private boolean eveningSessionFlag = false;

    /**
     * (�؋����s���z��Ǘ��t���O)<BR>
     * <BR>
     * �؋����s���z���Ǘ����邩�ǂ����𔻒肷��t���O�B<BR>
     * �E�Ǘ����Ȃ��ꍇ�F�@@true<BR>
     * �E�Ǘ�����ꍇ�F�@@false<BR>
     */
    private boolean lackChargeNonManagementFlag = false;
    
    /**
     * (�؋���SQ�������|�W�V������v��)<BR>
     * <BR> 
     * SQ���������|�W�V�����Ɍv�シ�邩�𔻒肷��t���O�B<BR>
     * �E�v�シ��ꍇ�F�@@false<BR>
     * �E�v�サ�Ȃ��ꍇ�F�@@true<BR>
     * 
     *  ���v�シ�遁false�A�v�サ�Ȃ���true�Ȃ̂Œ��ӂ��邱��  
     * 
     */
    private boolean ifodepositNonCalcSqProductFlag = false;
    

    /**
     * (SPAN�W��)<BR>
     * 
     * SPAN�W���B
     */
    private double SPANFactor = 0;

    /**
     * SPAN�W�����b�h�B
     */
    private double SPANFactorRed = 0;

    /**
     * �U�֗]�͌W���B
     */
    private double transferPowerFactor = 0;

    /**
     * �K�v�Œ�؋����B
     */
    private double minIfoDeposit = 0;

    /**
     * (�O���؋����s���z)<BR>
     * 
     * �c�Ɠ�[T-1]�ɏ؋����s�����[���Ƃ��đ��M���ꂽ�؋����s���z�B<BR>
     * 
     * �c�Ɠ�[T-1]�ɏ؋����s�����������Ă���ꍇ�̂ݐݒ肳���B<BR>
     * �ȉ��̏ꍇ��0�ƂȂ�B<BR>
     * �E�c�Ɠ�[T-1]�ɏ؋����s�����������Ă��Ȃ��ꍇ<BR>
     * �E�c�Ɠ�[T+0]�̏؋����s�����[�������M�ς̏ꍇ(�����؋����s���z > 0)<BR>
     */
    private double preBizDateIfoDepositLackCharge = 0;

    /**
     * (�����؋����s���z)<BR>
     * 
     * �c�Ɠ�[T+0]�ɏ؋����s�����[���Ƃ��đ��M���ꂽ�؋����s���z�B<BR>
     * 
     * �c�Ɠ�[T+0]�ɏ؋����s�����������Ă���ꍇ�̂ݐݒ肳���B<BR>
     * �ȉ��̏ꍇ��0�ƂȂ�B<BR>
     * �E�c�Ɠ�[T+0]�ɏ؋����s�����������Ă��Ȃ��ꍇ<BR>
     * �E�c�Ɠ�[T+0]�̏؋����s�����[���������M�̏ꍇ<BR>
     */
    private double currentBizDateIfoDepositLackCharge = 0;

    /**
     * ���X�ʏ؋����v�Z�����ꗗ<BR>
     */
    private Map calcConditionPerBranch;
    
    /**
     * �w���ʏ؋����v�Z�����ꗗ<BR>
     */
    private WEB3IfoDepositCalcConditionPerIndex[] calcConditions;

    /**
     * @@roseuid 416120B4006B
     */
    public WEB3IfoDepositCalcCondition()
    {
        calcConditions = new WEB3IfoDepositCalcConditionPerIndex[0];
        calcConditionPerBranch = new TreeMap();
    }

    /**
     * (set�c�Ɠ�[T-1..T+2])<BR>
     * 
     * ����.�c�Ɠ�[T-1..T+2]��this.�c�Ɠ�[T-1..T+2]�ɃZ�b�g����B<BR>
     * @@param l_bizDates - (�c�Ɠ�[T-1..T+2])<BR>
     * 
     * �c�Ɠ�[T-1]<BR>
     * �c�Ɠ�[T+0]<BR>
     * �c�Ɠ�[T+1]<BR>
     * �c�Ɠ�[T+2]<BR>
     * �̔z��B<BR>
     * @@roseuid 41131FC402D7
     */
    public void setBizDates(Date[] l_bizDates)
    {
        bizDates = l_bizDates;
    }

    /**
     * (set�؋����v�Z���)<BR>
     * 
     * ����.�؋����v�Z�����this.�؋����v�Z����ɃZ�b�g����B<BR>
     * @@param l_intBaseDate - (�؋����v�Z���)<BR>
     * 
     * 1�A�܂���2�����ꂩ�̒l�B<BR>
     * @@roseuid 41345EC50051
     */
    public void setIfoDepositCalcBaseDate(int l_intBaseDate)
    {
        ifoDepositCalcBaseDate = l_intBaseDate;
    }

    /**
     * (set�V�K���]�͉\�t���O)<BR>
     * 
     * ����.�V�K���]�͉\�t���O��this.�V�K���]�͉\�t���O�ɃZ�b�g����B<BR>
     * @@param l_newOpenTradingPowerAvailableFlag - (�V�K���]�͉\�t���O)<BR>
     * 
     * �]�͉\�F�@@true<BR>
     * �]�͕s�F�@@false<BR>
     * @@roseuid 41131BCE02F6
     */
    public void setNewOpenTradingPowerAvailableFlag(boolean l_blnNewOpenTradingPowerAvailableFlag)
    {
        newOpenTradingPowerAvailableFlag =
            l_blnNewOpenTradingPowerAvailableFlag;
    }

    /**
     * (set���A�������؋����v�Z���{�t���O)<BR>
     * 
     * ����.���A�������؋����v�Z���{�t���O��this.���A�������؋����v�Z���{�t���O�ɃZ�b�g
     * ����B<BR>
     * @@param l_blnRealCurrentPriceIfoDepositCalcFlag - 
     * (���A�������؋����v�Z���{�t���O)<BR>
     * ���{�F�@@true<BR>
     * �����{�F�@@false<BR>
     * @@roseuid 4111BD2C022B
     */
    public void setRealPriceIfoDepositCalcFlag(boolean l_blnRealCurrentPriceIfoDepositCalcFlag)
    {
        realPriceIfoDepositCalcFlag = l_blnRealCurrentPriceIfoDepositCalcFlag;
    }

    /**
     * (set�Ȉ�SPAN�v�Z���{�t���O)<BR>
     * 
     * ����.�Ȉ�SPAN�v�Z���{�t���O��this.�Ȉ�SPAN�v�Z���{�t���O�ɃZ�b�g����B<BR>
     * @@param l_blnSimpleSPANCalcFlag - (�Ȉ�SPAN�v�Z���{�t���O)<BR>
     * 
     * ���{�F�@@true<BR>
     * �����{�F�@@false<BR>
     * @@roseuid 4111CDEB0141
     */
    public void setSimpleSPANCalcFlag(boolean l_blnSimpleSPANCalcFlag)
    {
        simpleSPANCalcFlag = l_blnSimpleSPANCalcFlag;
    }

    /**
     * (setSPAN�g���u���t���O)<BR>
     * 
     * ����.SPAN�g���u���t���O��this.SPAN�g���u���t���O�ɃZ�b�g����B<BR>
     * @@param l_blnSPANTroubleFlag - (SPAN�g���u���t���O)<BR>
     * 
     * �g���u���̏ꍇ�F�@@true<BR>
     * ����̏ꍇ�F�@@false<BR>
     * @@roseuid 411319B600C4
     */
    public void setSpanTroubleFlag(boolean l_blnSPANTroubleFlag)
    {
        SPANTroubleFlag = l_blnSPANTroubleFlag;
    }

    /**
     * (setSPAN�W��)<BR>
     * 
     * ����.SPAN�W����this.SPAN�W���ɃZ�b�g����B<BR>
     * @@param l_dblSPANFactor - (SPAN�W��)<BR>
     * 
     * SPAN�W���B<BR>
     * @@roseuid 41131B0900A5
     */
    public void setSPANFactor(double l_dblSPANFactor)
    {
        SPANFactor = l_dblSPANFactor;
    }

    /**
     * (setSPAN�W�����b�h)<BR>
     * 
     * ����.SPAN�W�����b�h��this.SPAN�W�����b�h�ɃZ�b�g����B<BR>
     * @@param l_dblSPANFactorRate - SPAN�W�����b�h�B
     * @@roseuid 41131CB302C7
     */
    public void setSPANFactorRed(double l_dblSPANFactorRad)
    {
        SPANFactorRed = l_dblSPANFactorRad;
    }

    /**
     * (set�U�֗]�͌W��)<BR>
     * 
     * ����.�U�֗]�͌W����this.�U�֗]�͌W���ɃZ�b�g����B<BR>
     * @@param l_dblTransferPowerFactor - �U�֗]�͌W���B
     * @@roseuid 41131CEA0066
     */
    public void setTransferPowerFactor(double l_dblTransferPowerFactor)
    {
        transferPowerFactor = l_dblTransferPowerFactor;
    }

    /**
     * (set�K�v�Œ�؋���)<BR>
     * 
     * ����.�K�v�Œ�؋�����this.�K�v�Œ�؋����ɃZ�b�g����B<BR>
     * @@param l_dblMinIfoDeposit - �K�v�Œ�؋����B
     * @@roseuid 41131D3C018F
     */
    public void setMinIfoDeposit(double l_dblMinIfoDeposit)
    {
        minIfoDeposit = l_dblMinIfoDeposit;
    }

    /**
     * (set�O���؋����s���z)<BR>
     * 
     * ����.�O���؋����s���z��this.�O���؋����s���z�ɃZ�b�g����B<BR>
     * @@param l_dblPreBizDateInfoDepositLackCharge - �O���؋����s���z�B
     * @@roseuid 41401DC500C9
     */
    public void setPreBizDateInfoDepositLackCharge(double l_dblPreBizDateInfoDepositLackCharge)
    {
        preBizDateIfoDepositLackCharge = l_dblPreBizDateInfoDepositLackCharge;
    }

    /**
     * (set�����؋����s���z)<BR>
     * 
     * ����.�����؋����s���z��this.�����؋����s���z�ɃZ�b�g����B<BR>
     * @@param l_dblCurrentBizIfoDepositLackCharge - �����؋����s���z�B
     * @@roseuid 4132DF4002AB
     */
    public void setCurrentBizIfoDepositLackCharge(double l_dblCurrentBizIfoDepositLackCharge)
    {
        currentBizDateIfoDepositLackCharge =
            l_dblCurrentBizIfoDepositLackCharge;
    }

    /**
     * (set�w���ʏ؋����v�Z�����ꗗ)<BR>
     * 
     * ����.�w���ʏ؋����v�Z�����ꗗ��this.�w���ʏ؋����v�Z�����ꗗ�ɃZ�b�g����B<BR>
     * @@param l_ifoDepositCalcConditionPerIndexList - (�w���ʏ؋����v�Z�����ꗗ)<BR>
     * 
     * �w���ʏ؋����v�Z�����̈ꗗ�B<BR>
     * @@roseuid 41131DA90316
     */
    public void setIfoDepositCalcPerIndexList(WEB3IfoDepositCalcConditionPerIndex[] l_ifoDepositCalcConditionPerIndexList)
    {
        calcConditions = l_ifoDepositCalcConditionPerIndexList;
    }

    /**
     * (get�c�Ɠ�)<BR>
     * 
     * �w����ɑΉ�����c�Ɠ���ԋp����B<BR>
     * 
     * ����.�w��� == -1�̏ꍇ�Athis.�c�Ɠ�[T-1..T+2][0]��ԋp����B<BR>
     * ����.�w��� == 0�̏ꍇ�Athis.�c�Ɠ�[T-1..T+2][1]��ԋp����B<BR>
     * ����.�w��� == 1�̏ꍇ�Athis.�c�Ɠ�[T-1..T+2][2]��ԋp����B<BR>
     * ����.�w��� == 2�̏ꍇ�Athis.�c�Ɠ�[T-1..T+2][3]��ԋp����B<BR>
     * @@param l_intReservedDate - (�w���)<BR>
     * 
     * -1�A0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return Date
     * @@roseuid 41131F35025A
     */
    public Date getBizDate(int l_intReservedDate)
    {
        if (bizDates == null)
        {
            return null;
        }
        switch (l_intReservedDate)
        {
            case -1 :
                return bizDates[0];
            case 0 :
                return bizDates[1];
            case 1 :
                return bizDates[2];
            case 2 :
                return bizDates[3];
            default :
                return null;
        }
    }

    /**
     * (get�c�Ɠ�[T-1])<BR>
     * 
     * this.�c�Ɠ�[T-1..T+2][0]��ԋp����B<BR>
     * @@return Date
     * @@roseuid 41401EB30157
     */
    public Date getPreBizDate()
    {
        return getBizDate(-1);
    }

    /**
     * (get�c�Ɠ�[T+0])<BR>
     * 
     * this.�c�Ɠ�[T-1..T+2][1]��ԋp����B<BR>
     * @@return Date
     * @@roseuid 41131F84025A
     */
    public Date getCurrentBizDate()
    {
        return getBizDate(0);
    }

    /**
     * (get�c�Ɠ�[T+1])<BR>
     * 
     * this.�c�Ɠ�[T-1..T+2][2]��ԋp����B<BR>
     * @@return Date
     * @@roseuid 41131F840289
     */
    public Date getNextBizDate()
    {
        return getBizDate(1);
    }

    /**
     * (get�c�Ɠ�[T+2])<BR>
     * 
     * this.�c�Ɠ�[T-1..T+2][3]��ԋp����B<BR>
     * @@return Date
     * @@roseuid 41131F840299
     */
    public Date getNext2BizDate()
    {
        return getBizDate(2);
    }

    /**
     * (get�؋����v�Z���)<BR>
     * 
     * this.�؋����v�Z�����ԋp����B<BR>
     * @@return int
     * @@roseuid 41345EC403BC
     */
    public int getIfoDepositBaseDate()
    {
        return ifoDepositCalcBaseDate;
    }

    /**
     * (getSPAN�W��)<BR>
     * 
     * this.SPAN�W���̒l��ԋp����B<BR>
     * @@return double
     * @@roseuid 4113233D020C
     */
    public double getSPANFactor()
    {
        return SPANFactor;
    }

    /**
     * (getSPAN�W�����b�h)<BR>
     * 
     * this.SPAN�W�����b�h�̒l��ԋp����B<BR>
     * @@return double
     * @@roseuid 411323590131
     */
    public double getSPANFactorRed()
    {
        return SPANFactorRed;
    }

    /**
     * (get�K�v�Œ�؋���)<BR>
     * 
     * this.�K�v�Œ�؋����̒l��ԋp����B<BR>
     * @@return double
     * @@roseuid 411323820102
     */
    public double getMinIfoDeposit()
    {
        return minIfoDeposit;
    }

    /**
     * (get�O���؋����s���z)<BR>
     * 
     * this.�O���؋����s���z�̒l��ԋp����B<BR>
     * @@return double
     * @@roseuid 4132DF7D01C0
     */
    public double getPreBizDateIfoDepositLackCharge()
    {
        return preBizDateIfoDepositLackCharge;
    }

    /**
     * (get�����؋����s���z)<BR>
     * 
     * this.�����؋����s���z�̒l��ԋp����B<BR>
     * @@return double
     * @@roseuid 41401E04001E
     */
    public double getCurrentBizDateIfoDepositLackCharge()
    {
        return currentBizDateIfoDepositLackCharge;
    }

    /**
     * (get�U�֗]�͌W��)<BR>
     * 
     * this.�U�֗]�͌W���̒l��ԋp����B<BR>
     * @@return double
     * @@roseuid 4113236B0047
     */
    public double getTransferPowerFactor()
    {
        return transferPowerFactor;
    }

    /**
     * (get�K��؋���)<BR>
     * 
     * �w��̌����Y�����R�[�h�ɑΉ�����K��؋�����ԋp����B<BR>
     * 
     * this.�w���ʏ؋����v�Z�����ꗗ�̗v�f���Ƃ�Loop�������s���A<BR>
     * �w���ʏ؋����v�Z����.�����Y�����R�[�h == ����.�����Y�����R�[�h�ƂȂ�<BR>
     * �w���ʏ؋����v�Z�����̋K��؋�����ԋp����B<BR>
     * @@param l_strUnderlyingProductCode - �����Y�����R�[�h
     * @@return double
     * @@roseuid 4133D0640173
     */
    public double getIfoDepositPerUnit(String l_strUnderlyingProductCode)
    {
        WEB3IfoDepositCalcConditionPerIndex l_indexTypeCondition =
            findCalcConditionPerIndex(l_strUnderlyingProductCode);
        if (l_indexTypeCondition != null)
        {
            return l_indexTypeCondition.getIfoDepositPerUnit();
        } else
        {
            return -1.0;
        }
    }

    /**
     * (get�K��؋������b�h)<BR>
     * 
     * �w��̌����Y�����R�[�h�ɑΉ�����K��؋������b�h��ԋp����B<BR>
     * 
     * this.�w���ʏ؋����v�Z�����ꗗ�̗v�f���Ƃ�Loop�������s���A<BR>
     * �w���ʏ؋����v�Z����.�����Y�����R�[�h == ����.�����Y�����R�[�h�ƂȂ�<BR>
     * �w���ʏ؋����v�Z�����̋K��؋������b�h��ԋp����B<BR>
     * @@param l_strUnderlyingProductCode - �����Y�����R�[�h
     * @@return double
     * @@roseuid 4133D15401A2
     */
    public double getIfoDepositPerUnitRed(String l_strUnderlyingProductCode)
    {
        WEB3IfoDepositCalcConditionPerIndex l_indexTypeCondition =
            findCalcConditionPerIndex(l_strUnderlyingProductCode);
        if (l_indexTypeCondition != null)
        {
            return l_indexTypeCondition.getIfoDepositPerUnitRed();
        } else
        {
            return -1.0;
        }
    }
    
    /**
     * (get�K��؋������؋����s�����m�聄)<BR>
     * 
     * �w��̌����Y�����R�[�h�ɑΉ�����K��؋������؋����s�����m�聄��ԋp����B<BR>
     * 
     * this.�w���ʏ؋����v�Z�����ꗗ�̗v�f���Ƃ�Loop�������s���A<BR>
     * �w���ʏ؋����v�Z����.�����Y�����R�[�h == ����.�����Y�����R�[�h�ƂȂ�<BR>
     * �w���ʏ؋����v�Z����������΁Abreak���ĊY���̋K��؋������؋����s�����m�聄��ԋp����B<BR>
     * break������Loop�������I�������ꍇ(�w��̌����Y�����R�[�h�����{�ΏۊO)�A-1��ԋp����B<BR>
     * 
     * @@param l_strUnderlyingProductCode - �����Y�����R�[�h
     * @@return double
     */
    public double getIfoDepositPerUnitTemp(String l_strUnderlyingProductCode)
    {
        WEB3IfoDepositCalcConditionPerIndex l_indexTypeCondition =
            findCalcConditionPerIndex(l_strUnderlyingProductCode);
        if (l_indexTypeCondition != null)
        {
            return l_indexTypeCondition.getIfoDepositPerUnitTemp();
        } else
        {
            return -1.0;
        }
    }

    /**
     * (get���X�ʏ؋����v�Z����)<BR>
     * 
     * ����.�������ږ��ɑΉ�����������ڒl���}�b�v(this.���X�ʏ؋����v�Z�����j��茟�����ԋp����B<BR> 
     * �������ɊY������l�����݂��Ȃ��ꍇ�A��O��throw����B<BR>
     * 
     * @@param l_strConditionName - (�������ږ�)
     * @@return String
     */
    public String getCalcConditionPerBranch(String l_strConditionName)
    {
        final String STR_METHOD_NAME =
            "getCalcConditionPerBranch(String)";
            
        boolean l_isRegistered = calcConditionPerBranch.containsKey(l_strConditionName);

        String l_strValue = null;
        
        //�����ɊY������l�����݂���ꍇ
        if(l_isRegistered == true)
        {
            l_strValue = (String)calcConditionPerBranch.get(l_strConditionName);
            return l_strValue;
        }
        //�����ɊY������l�����݂��Ȃ��ꍇ
        else
        {
            log.error("���X�ʏ������ږ��ɊY������l�����݂��܂���ł���");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME);
        }

    }

    /**
     * (get�w���ʏ؋����v�Z����)
     * 
     * this.�w���ʏ؋����v�Z������ԋp����B
     * 
     * @@return �w���ʏ؋����v�Z����
     */
    public WEB3IfoDepositCalcConditionPerIndex[] getIfoDepositCalcPerIndexList()
    {
        return calcConditions;
    }

    /**
     * (is�V�K���]�͉\)<BR>
     * 
     * this.�V�K���]�͉\�t���O�̒l��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 4113037E0335
     */
    public boolean isNewOpenTradingPowerAvailable()
    {
        return newOpenTradingPowerAvailableFlag;
    }

    /**
     * (is���A�������؋����v�Z���{)<BR>
     * 
     * this.���A�������؋����v�Z���{�t���O�̒l��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 411322BF0131
     */
    public boolean isRealPriceIfoDepositCalc()
    {
        return realPriceIfoDepositCalcFlag;
    }

    /**
     * (is�Ȉ�SPAN�v�Z���{)<BR>
     * 
     * this.�Ȉ�SPAN�v�Z���{�t���O�̒l��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 411322E601DD
     */
    public boolean isSimpleSPANCalc()
    {
        return simpleSPANCalcFlag;
    }

    /**
     * (isSPAN�g���u��)<BR>
     * 
     * this.SPAN�g���u���t���O�̒l��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 4113230C0056
     */
    public boolean isSPANTrouble()
    {
        return SPANTroubleFlag;
    }

    /**
     * (isSPAN�g�p�\)<BR>
     * 
     * this.is�Ȉ�SPAN�v�Z���{( ) == false�A���A<BR>
     * this.isSPAN�g���u��( ) == false�̏ꍇ�̂݁Atrue��ԋp����B<BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 4121B5890017
     */
    public boolean isSPANUsable()
    {
        if (!isSimpleSPANCalc() && !isSPANTrouble())
        {
            return true;
        } else
        {
            return false;
        }
    }

    /**
     * (calc�c�Ɠ�)<BR>
     * 
     * �c�Ɠ�[T-1..T+2]���Z�o���Athis.�c�Ɠ�[T-1..T+2]�ɃZ�b�g����B<BR>
     * 
     * �V�[�P���X�}<BR>
     * �u(�؋����v�Z����)calc�c�Ɠ��v�Q�ƁB<BR>
     * @@roseuid 411321070008
     */
    public void calcBizDate()
    {

        TradingSystem l_ts = GtlUtils.getTradingSystem();
        Date l_datBaseDate = l_ts.getBizDate();

        try
        {

            Date[] l_datBizDates = new Date[4];
            WEB3GentradeBizDate l_bizDateUtil =
                new WEB3GentradeBizDate(new Timestamp(l_datBaseDate.getTime()));
            for (int i = -1; i < l_datBizDates.length - 1; i++)
            {
                l_datBizDates[i + 1] =
                    new Date(l_bizDateUtil.roll(i).getTime());
            }
            setBizDates(l_datBizDates);

        } 
        catch(WEB3SystemLayerException sle)
        {
            log.error(sle.getMessage(), sle);
            throw new WEB3BaseRuntimeException(sle.getErrorInfo(), sle
                .getErrorMethod(), sle.getErrorMessage(), sle.getException());
        }

    }

    /**
     * (calc�؋����v�Z���) <BR>
     * 
     * ������Ƃ̏؋����v�Z������Z�o���A���g�̃v���p�e�B�ɃZ�b�g����B <BR>
     * 
     * �P�j T+0�̉c�Ɠ����擾����B <BR>
     * this.get�c�Ɠ�[T+0]()���R�[������B <BR>
     * 
     * �Q�j ���������擾����B <BR>
     * ������ԊǗ�.get������()���R�[������B <BR>
     * 
     * �R�j �؋����v�Z������Z�b�g����B(���敨�I�v�V�����̎�n����T+1) <BR>
     * this.set�؋����v�Z���(�؋����v�Z���)���R�[������B <BR>
     * 
     * [�����̐ݒ�] <BR>
     * �؋����v�Z����F <BR>
     * 
     * �R�j-�P�@@�[�ꎞ�ԑ�(������ԊǗ�.get����敪 == "�[��")�̏ꍇ�A2 
     * �R�j-�Q�@@�R�j-1�ȊO�̏ꍇ 
     * -�����������ԑ�(������ԊǗ�.get������ == this.get�c�Ɠ�[T+0])�̏ꍇ�A1 <BR>
     * -�����������ԑ�(������ԊǗ�.get������ != this.get�c�Ɠ�[T+0])�̏ꍇ�A2 <BR>
     * 
     * @@roseuid 41345EC500DD
     */
    public void calcIfoDepositCalcBaseDate()
    {
        try
        {
            // �c�Ɠ�T+0
            Date l_datBaseDate = getCurrentBizDate();
            // ������
            Date l_datOrderBizDate =
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
                
			//�R�j-�P�@@�[�ꎞ�ԑ�(������ԊǗ�.get����敪 == "�[��")�̏ꍇ�A2
            if(WEB3SessionTypeDef.EVENING_SESSION.equals(WEB3GentradeTradingTimeManagement.getSessionType()))
            {
                setIfoDepositCalcBaseDate(WEB3IfoReservedDateDef.T_2);
            }
			else
			{			    			
	            if (WEB3DateUtility.compareToDay(l_datOrderBizDate, l_datBaseDate)
	                == 0)
	            {
	                // �����������ԑсi������ == �c�Ɠ��j�̏ꍇ
	                setIfoDepositCalcBaseDate(WEB3IfoReservedDateDef.T_1);
	            } else
	            {
	                // �����������ԑсi������ != �c�Ɠ��j�̏ꍇ
	                setIfoDepositCalcBaseDate(WEB3IfoReservedDateDef.T_2);
	            }
            }

        }
        catch(WEB3SystemLayerException sle)
        {
            log.error(sle.getMessage(), sle);
            throw new WEB3BaseRuntimeException(sle.getErrorInfo(), sle
                .getErrorMethod(), sle.getErrorMessage(), sle.getException());
        }
    }

    /**
     * WEB3IfoDepositCalcCondition�̕�����\����Ԃ��B
     * 
     * @@see java.lang.Object#toString()
     */
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer();
        l_sb.append("WEB3IfoDepositCalcCondition={");
        l_sb.append("bizDates={");
        for (int i = 0; i < bizDates.length; i++)
        {
            if (i > 0)
            {
                l_sb.append(",");
            }
            l_sb.append("[").append(i).append("]=").append(bizDates[i]);
        }
        l_sb.append("}");
        l_sb.append(",ifoDepositBaseDate=").append(getIfoDepositBaseDate());
        l_sb.append(",isNewOpenTradingPowerAvailable=").append(
            isNewOpenTradingPowerAvailable());
        l_sb.append(",isRealPriceIfoDepositCalc=").append(
            isRealPriceIfoDepositCalc());
        l_sb.append(",isSimpleSPANCalc=").append(isSimpleSPANCalc());
        l_sb.append(",isSPANTrouble=").append(isSPANTrouble());
        l_sb.append(",SPANFactor=").append(getSPANFactor());
        l_sb.append(",SPANFactorRed=").append(getSPANFactorRed());
        l_sb.append(",transferPowerFactor=").append(getTransferPowerFactor());
        l_sb.append(",miniIfoDeposit=").append(getMinIfoDeposit());
        l_sb.append(",preBizDateIfoDepositLackCharge=").append(getPreBizDateIfoDepositLackCharge());
        l_sb.append(",currentBizDateIfoDepositLackCharge=").append(getCurrentBizDateIfoDepositLackCharge());
        l_sb.append(",perIndexCalcConditions={");
        for (int i = 0; i < calcConditions.length; i++)
        {
            if (i > 0)
            {
                l_sb.append(",");
            }
            l_sb.append("[").append(i).append("]=").append(calcConditions[i]);
        }
        l_sb.append("}");
        //�[��Ή��ǉ�����
        l_sb.append(",isIfoDepositMailFlag=").append(isIfoDepositMailFlag());
        l_sb.append(",isQuickReportReceived=").append(isQuickReportReceived());
        l_sb.append(",isLackChargeNonManagement=").append(isLackChargeNonManagement());      
        l_sb.append(",isIfodepositNonCalcSqProductFlag=").append(isIfodepositNonCalcSqProductFlag());
        l_sb.append("}");
        return l_sb.toString();
    }

    /**
     * (create�w���ʏ؋����v�Z�����ꗗ)<BR>
     * 
     * �w���ʏ؋����v�Z�����̈ꗗ���쐬���Athis.�w���ʏ؋����v�Z�����ꗗ�ɃZ�b�g����B<BR>
     * 
     * �V�[�P���X�}<BR>
     * �u�i�؋����v�Z�����jcreate�w���ʏ؋����v�Z�����ꗗ�v�Q�ƁB<BR>
     * 
     * �y�g�pDB�z<BR>
     * �E�i���X�w���ʁj�戵�����e�[�u��<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * 
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@roseuid 411C77200142
     */
    public void createIfoDepositCalcConditionPerIndexList(WEB3GentradeSubAccount l_subAccount)
    {

        final String STR_METHOD_NAME =
            "createIfoDepositCalcConditionPerIndexList(WEB3GentradeSubAccount)";

        Map l_map = new TreeMap();

        /*
         * �،���ЃR�[�h�A���X�R�[�h�ɊY������
         *�i���X�w���ʁj�戵�����I�u�W�F�N�g��S�Ď擾����B
         */ 
        WEB3GentradeBranchIndexDealtCond l_handLingCondBranchIndex[] = null;

        try{

            l_handLingCondBranchIndex = WEB3GentradeBranchIndexDealtCond.getHandlingCondBranchIndexList(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode());

        } catch( WEB3SystemLayerException l_ex ) {

            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                l_ex.getErrorMethod(),
                l_ex.getErrorMessage(),
                l_ex.getException());
        }

        if (l_handLingCondBranchIndex == null || l_handLingCondBranchIndex.length == 0)
        {
            log.error("�i���X�w���ʁj�戵�������ꌏ���擾�ł��܂���ł���");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME);
        }

        BranchIndexDealtCondParams[] l_conds = new BranchIndexDealtCondParams[l_handLingCondBranchIndex.length];

        for(int i = 0; i < l_handLingCondBranchIndex.length; i++)
        {
            l_conds[i] = new BranchIndexDealtCondParams((BranchIndexDealtCondRow)l_handLingCondBranchIndex[i].getDataSourceObject());
        }

        //�擾�����i���X�w���ʁj�戵�������Ƃ�Loop����
        for (int i = 0; i < l_conds.length; i++)
        {
            //�L�[
            String l_strUnderlyingProductCode =
                l_conds[i].getTargetProductCode();
            
            //�i�����Y�����R�[�h�d���`�F�b�N)
            if (!l_map.containsKey(l_strUnderlyingProductCode))
            {
                //�i���X�w���ʁj�戵����.�K��؋����i����+�@@�O���j 
                //  != �i���X�w���ʁj�戵����.�K��؋����i����+�@@�P���j�̏ꍇ
                if (l_conds[i].getIfoDepositPerUnit0()
                    != l_conds[i].getIfoDepositPerUnit1())
                {
                    //�K��؋����ύX�t���O���Z�b�g����B
                    this.setIfoDepositPerUnitChangeFlag(true);
                }

                //�w���ʏ؋����v�Z�����I�u�W�F�N�g�𐶐�
                WEB3IfoDepositCalcConditionPerIndex l_cond =
                    new WEB3IfoDepositCalcConditionPerIndex();

                //�����Y�����R�[�h
                l_cond.setUnderlyingProductCode(l_strUnderlyingProductCode);

                /*
                 * �K��؋���,�K��؋������b�h
                 */
                // 1) this.���Z�l�����M�t���O == true or this.�؋����s�����[�����M�σt���O == true �̏ꍇ
                if ( this.quickReportReceivedFlag || this.isIfoDepositMailFlag() )
                {
                    //�i���X�w���ʁj�戵����.�K��؋����i����+�@@�P���j
                    l_cond.setIfoDepositPerUnit(
                        l_conds[i].getIfoDepositPerUnit1());
                        
                    //�i���X�w���ʁj�戵����.�K��؋������b�h�i����+�@@�P���j
                    l_cond.setIfoDepositPerUnitRed(
                        l_conds[i].getIfoDepositPerUnit1Red());                        
                        
                }
                else
                {
                    //�i���X�w���ʁj�戵����.�K��؋����i����+�@@�O���j
                    l_cond.setIfoDepositPerUnit(
                        l_conds[i].getIfoDepositPerUnit0());
                        
                   //�i���X�w���ʁj�戵����.�K��؋������b�h�i����+�@@�O���j
                    l_cond.setIfoDepositPerUnitRed(
                        l_conds[i].getIfoDepositPerUnit0Red());
                }

                /*
                 * �K��؋���<�؋����s�����m��p>
                 */
                //this.���Z�l�����M�σt���O == true�@@���A
                //this.�؋����s�����[�����M�σt���O == false�̏ꍇ�̂݃Z�b�g
                if (this.quickReportReceivedFlag &&  
                        !this.isIfoDepositMailFlag())
                {
                    //�i���X�w���ʁj�戵����.�K��؋����i����+�@@�O���j
                    l_cond.setIfoDepositPerUnitTemp(
                        l_conds[i].getIfoDepositPerUnit0());
                }

                l_map.put(l_strUnderlyingProductCode, l_cond);
  
            }
        }

        Collection l_col = l_map.values();
        WEB3IfoDepositCalcConditionPerIndex[] l_perIndexConditions =
            (WEB3IfoDepositCalcConditionPerIndex[]) l_col.toArray(
                new WEB3IfoDepositCalcConditionPerIndex[0]);
        setIfoDepositCalcPerIndexList(l_perIndexConditions);

    }
    
    /**
     * (add���X�ʏ؋����v�Z����)<BR>
     * 
     * ����.�������ږ����L�[�Ƃ��Ĉ���.�������ڒl���}�b�v(this.���X�ʏ؋����v�Z�����j�ɃZ�b�g����B<BR>
     * 
     * @@param l_strConditionName - (�������ږ�)
     * @@param l_strConditionValue - (�������ڒl)
     */
    public void addCalcConditionPerBranch(String l_strConditionName, String l_strConditionValue) 
    {
        calcConditionPerBranch.put(l_strConditionName, l_strConditionValue);
    }

    // private methods ---------------------------------------------------------

    /**
     * (find�w���ʏ؋����v�Z����)<BR>
     * 
     * �w�肵�������Y�R�[�h�̎w���ʏ؋����v�Z���������݂���������B<BR>
     * ���݂��Ȃ��ꍇ�́A<code>null</code>��Ԃ��B
     * 
     * @@param l_strUnderlyingProductCode �����Y�R�[�h<BR>
     * @@return �w���ʏ؋����v�Z����<BR>
     */
    private WEB3IfoDepositCalcConditionPerIndex findCalcConditionPerIndex(String l_strUnderlyingProductCode)
    {
        WEB3IfoDepositCalcConditionPerIndex l_indexTypeCondition = null;
        for (int i = 0; i < calcConditions.length; i++)
        {
            if (calcConditions[i]
                .getUnderlyingProductCode()
                .equals(l_strUnderlyingProductCode))
            {
                l_indexTypeCondition = calcConditions[i];
                break;
            }
        }
        return l_indexTypeCondition;
    }

    /**
     * (is�؋����s�����[�����M��)<BR>
     * 
     * this.�؋����s�����[�����M�σt���O�̒l��ԋp����B<BR>
     * @@return boolean
     */
    public boolean isIfoDepositMailFlag()
    {
        return this.IfoDepositMailFlag;
    }

    /**
     * (is���Z�l�����M��)<BR>
     * 
     * this.���Z�l�����M�σt���O�̒l��ԋp����B<BR>
     * @@return boolean
     */
    public boolean isQuickReportReceived()
    {
        return this.quickReportReceivedFlag;
    }

    /**
     * (is�؋����s���z��Ǘ�)<BR>
     * 
     * this.�؋����s���z��Ǘ��t���O�̒l��ԋp����B<BR>
     * @@return boolean
     */
    public boolean isLackChargeNonManagement()
    {
        return this.lackChargeNonManagementFlag;
    }
   
    /**
     * (is�[����{)<BR>
     * 
     * this.�[����{�t���O�̒l��ԋp����B<BR>
     * @@return boolean
     */
    public boolean isEveningSessionEnforcemented()
    {
        return this.eveningSessionFlag;
    }  
   
    /**
     * (is�K��؋����ύX)<BR>
     * 
     * this.�K��؋����ύX�t���O�̒l��ԋp����B<BR>
     * @@return boolean
     */
    public boolean isIfoDepositPerUnitChangeFlag()
    {
        return this.IfoDepositPerUnitChangeFlag;
    }

    /**
     * (is�؋���SQ�������|�W�V������v��)<BR>
     * 
     * this.�؋���SQ�������|�W�V������v��̒l��ԋp����B<BR>
     * @@return boolean
     */
    public boolean isIfodepositNonCalcSqProductFlag()
    {
        return this.ifodepositNonCalcSqProductFlag;
    }

    /**
     * (set�؋����s�����[�����M�σt���O)<BR>
     * 
     * ����.�؋����s�����[�����M�σt���O��this.�؋����s�����[�����M�σt���O�ɃZ�b�g����B<BR>
     * @@param l_ifoDepositMailFlagFlag - (�؋����s�����[�����M�σt���O)<BR>
     */
    public void setIfoDepositMailFlag(boolean l_ifoDepositMailFlagFlag)
    {
        this.IfoDepositMailFlag = l_ifoDepositMailFlagFlag;
    }

    /**
     * (set�K��؋����ύX�t���O)<BR>
     * 
     * ����.�K��؋����ύX�t���O��this.�K��؋����ύX�t���O�ɃZ�b�g����B<BR>
     * @@param l_ifoDepositPerUnitChangeFlag - (�K��؋����ύX�t���O)<BR>
     */
    public void setIfoDepositPerUnitChangeFlag(boolean l_ifoDepositPerUnitChangeFlag)
    {
        IfoDepositPerUnitChangeFlag = l_ifoDepositPerUnitChangeFlag;
    }

    /**
     * (set���Z�l�����M�t���O)<BR>
     * 
     * ����.���Z�l�����M�t���O��this.���Z�l�����M�t���O�ɃZ�b�g����B<BR>
     * @@param l_QuickReportReceivedFlag - (���Z�l�����M�σt���O)<BR>
     */
    public void setQuickReportReceivedFlag(boolean l_QuickReportReceivedFlag)
    {
        this.quickReportReceivedFlag = l_QuickReportReceivedFlag;
    }

    /**
     * (set�[����{�t���O)<BR>
     * 
     * ����.�[����{�t���O��this.�[����{�t���O�ɃZ�b�g����B<BR>
     * @@param l_EveningSessionFlag - (�[����{�t���O)<BR>
     */
    public void setEveningSessionFlag(boolean l_EveningSessionFlag)
    {
        this.eveningSessionFlag = l_EveningSessionFlag;
    }

    /**
     * (set�؋����s���z��Ǘ��t���O)<BR>
     * 
     * ����.�؋����s���z��Ǘ��t���O��this.�؋����s���z��Ǘ��t���O�ɃZ�b�g����B<BR>
     * @@param l_ClosingPriceUpdateFlag - (�����I�l�X�V�t���O)<BR>
     */
    public void setLackChargeNonManagementFlag(boolean l_LackChargeNonManagementFlag)
    {
        this.lackChargeNonManagementFlag = l_LackChargeNonManagementFlag;        
    }
    
    /**
     * (set�؋���SQ�������|�W�V������v��)<BR>
     * 
     * ����.�؋���SQ�������|�W�V������v����Athis.�؋���SQ�������|�W�V������v��ɃZ�b�g����B<BR>
     * @@param l_ifodepositNonCalcSqProductFlag - (�؋���SQ�������|�W�V������v��)<BR>
     */
    public void setIfodepositNonCalcSqProductFlag(boolean l_ifodepositNonCalcSqProductFlag)
    {
        this.ifodepositNonCalcSqProductFlag = l_ifodepositNonCalcSqProductFlag;        
    }

}
@
