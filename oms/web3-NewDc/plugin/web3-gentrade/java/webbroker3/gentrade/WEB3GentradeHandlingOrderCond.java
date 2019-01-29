head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeHandlingOrderCond.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �戵�\��������(WEB3GentradeHandlingOrderCond.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/15 羐� (���u) �V�K�쐬
Revesion History : 2004/06/28 ���Ō� (���u) isHandlingPossibleExecCond() ���I�[�o�[���[�h����
Revesion History : 2004/10/10 �Г� (���u) WEB3GentradeHandlingOrderCond(String,
                                          ProductTypeEnum,String,String,String)��ǉ�
Revesion History : 2004/10/10 �Г� (���u) WEB3GentradeHandlingOrderCond(String,
                                          ProductTypeEnum,String,String)���C��
Revesion History : 2004/10/10 �Г� (���u) WEB3GentradeHandlingOrderCond(String,
                                          ProductTypeEnum,String,String)���C��
Revesion History : 2004/10/10 �Г� (���u) getHandlingPriceCond()��ǉ�
Revesion History : 2004/10/10 �Г� (���u) isHandlingPriceCond(String)��ǉ�
Revesion History : 2005/08/15 �Г� (���u) isHandlingPossibleExecCond(FeqExecutionConditionType)��ǉ�
Revesion History : 2007/06/08 �h�C (���u)�y���ʁz�d�l�ύX�E���f��No.234�A235�A241
Revesion History : 2007/06/18 �h�C (���u)�y���ʁz�d�l�ύX�E���f��No.247
Revesion History : 2007/06/29 �h�C (���u)�y���ʁz�d�l�ύX�E���f��No.258
Revesion History : 2007/06/29 �h�C (���u)�y���ʁz�d�l�ύX�E���f��No.257
Revesion History : 2007/11/19 �h�C (���u)�y���ʁz�d�l�ύX�E���f��No.286
Revesion History : 2007/11/27 �h�C (���u)�y���ʁz�d�l�ύX�E���f��No.293
*/
package webbroker3.gentrade;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3CarriedOrderDef;
import webbroker3.common.define.WEB3CarriedOrderLapseDateSpecDef;
import webbroker3.common.define.WEB3DealtDef;
import webbroker3.common.define.WEB3EveningSessionOrderDef;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.EnableOrderConditionDao;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.gentrade.define.WEB3GentradePriceCondDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�戵�\��������) <BR>
 * <BR>
 * �،���Ж��̎戵�\����������\������B <BR>
 * <BR>
 * �iDB���C�A�E�g �u�戵�\���������e�[�u��.xls�v�Q�Ɓj <BR>
 */
public class WEB3GentradeHandlingOrderCond implements BusinessObject
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeHandlingOrderCond.class);

    /**
     * (�戵�\��������Row) <BR>
     * �戵�\���������s�I�u�W�F�N�g <BR>
     * �i��������DAO�N���X�j <BR>
     */
    private EnableOrderConditionRow enableOrderConditionRow;

    /**
     * (����ŏI��) <BR>
     * ����ŏI��<BR>
     */
    private Date tradingEndDate;

    /**
     * �R���X�g���N�^�B <BR>
     * �����̏����Ɉ�v����戵�\���������I�u�W�F�N�g��ԋp����B<BR>
     *  <BR>
     * this.�戵�\��������(�،���ЃR�[�h, �����^�C�v, <BR>
     * �敨�^�I�v�V�����敪, �M�p����敪, �s��R�[�h)��delegate����B<BR>
     *  <BR>
     * ��delegate�������ݒ�d�l�� <BR>
     * �����̎s��R�[�h�ɂ́u0�FDEFAULT�v��ݒ肷��B<BR>
     * ���̑��̈����ɂ́A�����\�b�h�̈��������̂܂ܐݒ肷��B<BR>
     *  <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@param l_productType - (�����^�C�v)
     * @@param l_strFuturesOptionDiv - (�敨�^�I�v�V�����敪) <BR>
     *    0�F DEFAULT�i�敨OP�ȊO�j <BR>
     *    1�F �敨 <BR>
     *    2�F �I�v�V���� <BR>
     * @@param l_strMarginTradingDiv - (�M�p����敪)<BR>
     *    0�F DEFAULT�i�Œ�j<BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 405E4FF8009E
     */
    public WEB3GentradeHandlingOrderCond(
        String l_strInstitutionCode,
        ProductTypeEnum l_productType,
        String l_strFuturesOptionDiv,
        String l_strMarginTradingDiv)
        throws WEB3SystemLayerException
    {   
        this(l_strInstitutionCode,
            l_productType,
            l_strFuturesOptionDiv,
            l_strMarginTradingDiv,
            WEB3MarketCodeDef.DEFAULT);
    }

    /**
     * this.�戵�\��������Row��ԋp����B
     * @@return Object
     * @@roseuid 405E4DE9033E
     */
    public Object getDataSourceObject()
    {
        return this.enableOrderConditionRow;
    }

    /**
     * (�戵�\���s�����擾) <BR>
     * <BR>
     * �戵�\���s�������擾����B <BR>
     * <BR>
     * this.�戵�\��������Row�́i���s�����j���ڂ� <BR>
     * �h�戵�\�h�ɂȂ��Ă��鍀�ڂɊY�����鎷�s�����R�[�h <BR>
     * (*1)��z��ɂĕԋp����B <BR>
     * �A���A�h�������h�͕K���z��Ɋ܂߂�B <BR>
     * <BR>
     * (*1) ���s�����R�[�h <BR>
     * 1�F ������ <BR>
     * 3�F ��t <BR>
     * 4�F ���� <BR>
     * 7�F �s�o���������s <BR>
     * <BR>
     * @@return String[]
     * @@roseuid 405E527501F6
     */
    public String[] getHandlingPossibleExecCond()
    {
        ArrayList l_lstExecConds = new ArrayList();

        //set ������
        l_lstExecConds.add(WEB3ExecutionConditionDef.NO_CONDITION);

        //set ��t
        if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getAtMarketOpen()))
        {
            l_lstExecConds.add(WEB3ExecutionConditionDef.AT_MARKET_OPEN);
        }
        //set ����
        if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getAtMarketClose()))
        {
            l_lstExecConds.add(WEB3ExecutionConditionDef.AT_MARKET_CLOSE);
        }
        //set �s�o���������s
        if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getNoExecAtMartClose()))
        {
            l_lstExecConds.add(WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED);
        }

        int l_intSize = l_lstExecConds.size();
        String[] l_strExecConds = new String[l_intSize];
        for (int i = 0; i < l_intSize; i++)
        {
            l_strExecConds[i] = (String)l_lstExecConds.get(i);
        }
        return l_strExecConds;

    }

    /**
     * (�戵�\���������擾)
     * <BR>
     * �戵�\�����������擾����B <BR>
     * <BR>
     * this.�戵�\��������Row�́i���������j���ڂ� <BR>
     * �h�戵�\�h�ɂȂ��Ă��鍀�ڂɊY�����锭�������R�[�h <BR>
     * (*1)��z��ɂĕԋp����B <BR>
     * �A���A�hDEFAULT�i�����w��Ȃ��j�h�͕K���z��Ɋ܂߂�B <BR>
     * <BR>
     * (*1) ���������R�[�h <BR>
     * 0�FDEFAULT�i�����w��Ȃ��j <BR>
     * 1�F�t�w�l <BR>
     * 2�FW�w�l <BR>
     * <BR>
     * @@return String[]
     * @@roseuid 405E5C35032F
     */
    public String[] getHandlingPossibleOrderCond()
    {
        ArrayList l_lstOrderConds = new ArrayList();

        //set DEFAULT�i�����w��Ȃ��j
        l_lstOrderConds.add(WEB3OrderingConditionDef.DEFAULT);

        //set �t�w�l
        if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getStopOrder()))
        {
            l_lstOrderConds.add(WEB3OrderingConditionDef.STOP_LIMIT_PRICE);
        }
        //set W�w�l
        if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getWLimitOrder()))
        {
            l_lstOrderConds.add(WEB3OrderingConditionDef.W_LIMIT_PRICE);
        }

        int l_intSize = l_lstOrderConds.size();
        String[] l_strOrderConds = new String[l_intSize];
        for (int i = 0; i < l_intSize; i++)
        {
            l_strOrderConds[i] = (String) l_lstOrderConds.get(i);
        }
        return l_strOrderConds;

    }

    /**
     * (�戵�\�����P���敪�擾)<BR>
     * (�����A�����w��)<BR>
     * <BR>
     * [�V�K���i�p�����[�^.is�V�K�� == true�j�̏ꍇ]<BR>
     * �@@�@@-- ����(�p�����[�^.is���� == true) �̏ꍇ --<BR>
     * �@@�@@�@@this.�戵�\��������Row�̐��s�����i�V�K�����j == �h�戵�\�h<BR>
     * �@@�@@�ł���΁A�����P���敪.�h�w�l�h�A�����P���敪.�h���s�h��z��ŕԋp����B<BR>
     * �@@�@@-- ����(�p�����[�^.is���� == false) �̏ꍇ --<BR>
     * �@@�@@�@@this.�戵�\��������Row�̐��s�����i�V�K�����j == �h�戵�\�h<BR>
     * �@@�@@�ł���΁A�����P���敪.�h�w�l�h�A�����P���敪.�h���s�h��z��ŕԋp����B<BR>
     * �@@�@@�ȊO�A�����P���敪.�h�w�l�h���T�C�Y1�̔z��ŕԋp����B<BR>
     * <BR>
     * [�ԍρi�p�����[�^.is�V�K�� == false�j�̏ꍇ]<BR>
     * �@@�@@this.�戵�\��������Row�̐��s�����i�ԍρj == �h�戵�\�h�ł���΁A<BR>
     * �@@�@@�����P���敪.�h�w�l�h�A�����P���敪.�h���s�h��z��ŕԋp����B<BR>
     * �@@�@@�ȊO�A�����P���敪.�h�w�l�h���T�C�Y1�̔z��ŕԋp����B<BR>
     * <BR>
     * (*1) �����P���敪<BR>
     * 0�F���s<BR>
     * 1�F�w�l<BR>
     * <BR>
     * @@param l_isOpenContract �V�K��������ǂ����̔���B<BR>
     *          �V�K���̏ꍇtrue�A�ԍς̏ꍇfalse�B
     * @@param l_isBuyOrder �������ǂ����̔��ʁB<BR>
     *          �����̏ꍇ��true�A�����̏ꍇ��false�B
     * @@return String[]
     */
    public String[] getHandlingPossibleOrderPriceDiv(
        boolean l_isOpenContract,
        boolean l_isBuyOrder)
    {
        final String STR_METHOD_NAME = 
            "getHandlingPossibleOrderPriceDiv(boolean l_blnIsOpenContract, boolean l_blnIsBuyOrder)";
        log.entering(STR_METHOD_NAME + " begin");
                
        String[] l_strOrderPriceDivs = null;
                
        if (l_isOpenContract) //�V�K��         
        {
            if (l_isBuyOrder     //����
                && WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getMartPriceBuyToOpen())) //���s�����i�V�K�����j == �h�戵�\�h
            {
                l_strOrderPriceDivs = 
                    new String[]{WEB3OrderPriceDivDef.LIMIT_PRICE, WEB3OrderPriceDivDef.MARKET_PRICE}; 
            }
            else if (!l_isBuyOrder  //����
                       && WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getMartPriceSellToOpen())) //���s�����i�V�K�����j == �h�戵�\�h
            {
                l_strOrderPriceDivs = 
                    new String[]{WEB3OrderPriceDivDef.LIMIT_PRICE, WEB3OrderPriceDivDef.MARKET_PRICE};
            }
            else
            {
                l_strOrderPriceDivs = 
                    new String[]{WEB3OrderPriceDivDef.LIMIT_PRICE};
            }
                    
        }    
        else    //�ԍ�
        {
            if ( WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getMartPriceOrderSettleCont())) //���s�����i�ԍρj == �h�戵�\
            {
                l_strOrderPriceDivs = 
                    new String[]{WEB3OrderPriceDivDef.LIMIT_PRICE, WEB3OrderPriceDivDef.MARKET_PRICE};                    
            }
            else
            {
                l_strOrderPriceDivs = 
                    new String[]{WEB3OrderPriceDivDef.LIMIT_PRICE};
            }
        }
                
        return l_strOrderPriceDivs;

    }
    
    /**
     * (�戵�\�����P���敪�擾)<BR>
     * (�V�K���w��)<BR>
     * <BR>
     *  �戵�\�����P���敪���擾����B<BR>
     *  <BR>
     * --�V�K���iis�V�K�� == true�j�̏ꍇ  <BR>
     *   this.�戵�\��������Row�̐��s�����i�V�K�����j == �h�戵�\�h�܂��́A<BR> 
     *   this.�戵�\��������Row�̐��s�����i�V�K�����j == �h�戵�\�h <BR>
     *   �ł���΁A�����P���敪.�h�w�l�h�A�����P���敪.�h���s�h��z��ŕԋp����B<BR>
     *   �ȊO�A�����P���敪.�h�w�l�h���T�C�Y1�̔z��ŕԋp����B<BR> 
     * --�ԍρiis�V�K�� == false�j�̏ꍇ <BR>
     *   this.�戵�\��������Row�̐��s�����i�ԍρj == �h�戵�\�h�ł���΁A<BR>
     *   �����P���敪.�h�w�l�h�A�����P���敪.�h���s�h��z��ŕԋp����B <BR>
     *   �ȊO�A�����P���敪.�h�w�l�h���T�C�Y1�̔z��ŕԋp����B<BR> 
     * <BR>
     * (*1) �����P���敪<BR>
     * 0�F���s<BR>
     * 1�F�w�l<BR>
     * <BR>
     * @@param l_isOpenContract - (is�V�K��) <BR>
     *     �V�K��������ǂ����̔���B�V�K���̏ꍇtrue�A�ԍς̏ꍇfalse�B <BR>
     * @@return String[]
     */
    public String[] getHandlingPossibleOrderPriceDiv(
        boolean l_isOpenContract)
    {
        String[] l_strOrderPriceDivs = null;
        
        //�V�K��
        if (l_isOpenContract)          
        {
            //this.�戵�\��������Row�̐��s�����i�V�K�����j == �h�戵�\�h�܂��́A 
            //this.�戵�\��������Row�̐��s�����i�V�K�����j == �h�戵�\�h 
            if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getMartPriceBuyToOpen())
                ||WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getMartPriceSellToOpen()))
            {
                //�����P���敪.�h�w�l�h�A�����P���敪.�h���s�h��z��ŕԋp����
                l_strOrderPriceDivs = 
                    new String[]{WEB3OrderPriceDivDef.LIMIT_PRICE, WEB3OrderPriceDivDef.MARKET_PRICE};               
            }
            else
            {
                //�����P���敪.�h�w�l�h���T�C�Y1�̔z��ŕԋp����
                l_strOrderPriceDivs = 
                    new String[]{WEB3OrderPriceDivDef.LIMIT_PRICE};
            }
        }
        //�ԍ�
        else    
        {
            //this.�戵�\��������Row�̐��s�����i�ԍρj == �h�戵�\�h
            if(WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getMartPriceOrderSettleCont()))
            {
                //�����P���敪.�h�w�l�h�A�����P���敪.�h���s�h��z��ŕԋp����
                l_strOrderPriceDivs = 
                    new String[]{WEB3OrderPriceDivDef.LIMIT_PRICE, WEB3OrderPriceDivDef.MARKET_PRICE};               
            }
            else
            {
                //�����P���敪.�h�w�l�h���T�C�Y1�̔z��ŕԋp����
                l_strOrderPriceDivs = 
                    new String[]{WEB3OrderPriceDivDef.LIMIT_PRICE};
            }
            
        }
        return l_strOrderPriceDivs;
    }

    /**
     * (�戵�\���������敪�擾) <BR>
     * <BR>
     * �P�jthis.�戵�\��������Row�́u�����^�C�v�v���ڂ��h�敨�I�v�V�����h�̏ꍇ <BR>
     * <BR>
     * �@@�@@�@@�P�|�P�j �ԋp���钍�������敪��"��������"��ݒ肷��B <BR>
     * �@@�@@�@@�P�|�Q�j this.is�o����܂Œ����戵�\������ŏI���l����()==true�̏ꍇ <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�ԋp���钍�������敪��"�o����܂Œ���"��ǉ��ݒ肷��B <BR>
     * <BR>
     * �@@�@@�@@�P�|�R�j ������ԊǗ�.is�[�ꎞ�ԑ�() == false�A���A <BR>
     * �@@�@@�@@�@@�@@�@@�@@this.is�[��܂Œ����戵�\<����ŏI���l��>() == true�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�@@�@@�ԋp���钍�������敪�ցh�[��܂Œ����h��ݒ肷��B <BR>
     * <BR>
     * �@@�@@�@@�P�|�S�j �ݒ肵�����������敪��ԋp����B <BR>
     * <BR>
     * <BR>
     * �Q�j�@@�P�j�ȊO�̏ꍇ <BR>
     * <BR>
     * �@@�@@�@@�Q�|�P�j this.�戵�\��������Row�́u�o����܂Œ����v���ڂ� <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�h�戵�s�h�̏ꍇ�h��������h�̂݁A<BR>
     * �ȊO�h��������h�A�h�o����܂Œ����h��ԋp����B <BR>
     * <BR>
     * <BR>
     * (*) ���������敪 <BR>
     * 1�F�������� <BR>
     * 2�F�o����܂Œ��� <BR>
     * 3�F�[��܂Œ���<BR>
     * <BR>
     * @@return String[]
     * @@throws WEB3SystemLayerException
     * @@roseuid 405E5E6D01D7
     */
    public String[] getHandlingPossibleExpirationDateType() throws WEB3SystemLayerException
    {
        ArrayList l_lstExpirationDateTypes = new ArrayList();

        //�P�jthis.�戵�\��������Row�́u�����^�C�v�v���ڂ��h�敨�I�v�V�����h�̏ꍇ
        ProductTypeEnum l_productType = this.enableOrderConditionRow.getProductType();
        if (ProductTypeEnum.IFO.equals(l_productType))
        {
            //�ԋp���钍�������敪��"��������"��ݒ肷��B
            l_lstExpirationDateTypes.add(WEB3OrderExpirationDateTypeDef.DAY_LIMIT);
            //�ԋp���钍�������敪��"�o����܂Œ���"��ǉ��ݒ肷��B
            if (this.isOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering())
            {
                l_lstExpirationDateTypes.add(WEB3OrderExpirationDateTypeDef.CARRIED_ORDER);
            }
            //�ԋp���钍�������敪�ցh�[��܂Œ����h��ݒ肷��B
            if (!WEB3GentradeTradingTimeManagement.isEveningSessionTimeZone()
                && this.isEveningSessionOrderPossibleHandlingTradingEndDateConsidering())
            {
                l_lstExpirationDateTypes.add(WEB3OrderExpirationDateTypeDef.EVENING_SESSION_ORDER);
            }
        }
        //�Q�j�@@�P�j�ȊO�̏ꍇ
        else
        {
            l_lstExpirationDateTypes.add(WEB3OrderExpirationDateTypeDef.DAY_LIMIT);
            if (!WEB3CarriedOrderDef.CAN_NOT_DEALT.equals(this.enableOrderConditionRow.getCarriedOrder()))
            {
                l_lstExpirationDateTypes.add(
                    WEB3OrderExpirationDateTypeDef.CARRIED_ORDER);
            }
        }

        int l_intSize = l_lstExpirationDateTypes.size();
        String[] l_strExpirationDateTypes = new String[l_intSize];
        for (int i = 0; i < l_intSize; i++)
        {
            l_strExpirationDateTypes[i] =
                (String) l_lstExpirationDateTypes.get(i);
        }
        return l_strExpirationDateTypes;

    }

    /**
     * (�戵�\���������敪�ꗗ�擾) <BR>
     * <BR>
     * �戵�\���������敪�ꗗ���擾����B <BR>
     * �����ԑтɊւ�炸���������敪�ꗗ���擾����B <BR>
     * <BR>
     * �P�jthis.�戵�\��������Row�́u�����^�C�v�v���ڂ��h�敨�I�v�V�����h�̏ꍇ <BR>
     * <BR>
     * �@@�@@�@@�P�|�P�j�@@�ԋp���钍�������敪��"��������"��ݒ肷��B <BR>
     * <BR>
     * �@@�@@�@@�P�|�Q�j�@@this.is�o����܂Œ����戵�\������ŏI���l����()==true�̏ꍇ <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�ԋp���钍�������敪��"�o����܂Œ���"��ǉ��ݒ肷��B <BR>
     * <BR>
     * �@@�@@�@@�P�|�R�j�@@this.is�[��܂Œ����戵�\������ŏI���l����() == true�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�ԋp���钍�������敪�ցh�[��܂Œ����h��ݒ肷��B <BR>
     * <BR>
     * �@@�@@�@@�P�|�S�j�@@�ݒ肵�����������敪��ԋp����B <BR>
     * <BR>
     * �Q�j�@@�P�j�ȊO�̏ꍇ <BR>
     * <BR>
     * �@@�@@�@@�Q�|�P�j�@@this.�戵�\��������Row�́u�o����܂Œ����v���ڂ� <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�h�戵�s�h�̏ꍇ�h��������h�̂݁A<BR>
     * �ȊO�h��������h�A�h�o����܂Œ����h��ԋp����B <BR>
     * <BR>
     * @@return String[]
     * @@throws WEB3SystemLayerException
     */
    public String[] getHandlingPossibleExpirationDateTypes() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getHandlingPossibleExpirationDateTypes()";
        log.entering(STR_METHOD_NAME);

        List l_lisExpirationDateTypes = new ArrayList();

        //�P�jthis.�戵�\��������Row�́u�����^�C�v�v���ڂ��h�敨�I�v�V�����h�̏ꍇ
        ProductTypeEnum l_productType = this.enableOrderConditionRow.getProductType();
        if (ProductTypeEnum.IFO.equals(l_productType))
        {
            //�ԋp���钍�������敪��"��������"��ݒ肷��B
            l_lisExpirationDateTypes.add(WEB3OrderExpirationDateTypeDef.DAY_LIMIT);
            if (this.isOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering())
            {
                //�ԋp���钍�������敪��"�o����܂Œ���"��ǉ��ݒ肷��B
                l_lisExpirationDateTypes.add(WEB3OrderExpirationDateTypeDef.CARRIED_ORDER);
            }
            if (this.isEveningSessionOrderPossibleHandlingTradingEndDateConsidering())
            {
                //�ԋp���钍�������敪�ցh�[��܂Œ����h��ݒ肷��B
                l_lisExpirationDateTypes.add(WEB3OrderExpirationDateTypeDef.EVENING_SESSION_ORDER);
            }
        }
        //�Q�j�@@�P�j�ȊO�̏ꍇ
        else
        {
            l_lisExpirationDateTypes.add(WEB3OrderExpirationDateTypeDef.DAY_LIMIT);
            if (!WEB3CarriedOrderDef.CAN_NOT_DEALT.equals(this.enableOrderConditionRow.getCarriedOrder()))
            {
                l_lisExpirationDateTypes.add(WEB3OrderExpirationDateTypeDef.CARRIED_ORDER);
            }
        }

        int l_intSize = l_lisExpirationDateTypes.size();
        String[] l_strExpirationDateTypes = new String[l_intSize];
        for (int i = 0; i < l_intSize; i++)
        {
            l_strExpirationDateTypes[i] = (String)l_lisExpirationDateTypes.get(i);
        }
        log.exiting(STR_METHOD_NAME);
        return l_strExpirationDateTypes;
    }

    /**
     * (get�����������j���ꗗ) <BR>
     * <BR>
     * �o����܂Œ����̗L�������܂ł̂��ׂĂ̋x���i�j���j��z��ŕԋp����B <BR>
     * �i�j���̂݁B�y���͊܂߂Ȃ��j <BR>
     * <BR>
     * �@@�P�j�������w��ۂ̔��� <BR>
     * �@@this.�戵�\��������Row��(�o����܂Œ���)�������w�肪 <BR>
     *   �h1�F�ŏI���̂ݎw��h�̏ꍇ��null��ԋp����B <BR>
     * <BR>
     * �@@�Q�j�������܂ł̋x���擾 <BR>
     * �@@this.get�o����܂Œ����ŏI��()�ɂčŏI�����擾����B <BR>
     * �@@��this.get�o����܂Œ����ŏI��(void)��null���ԋp���ꂽ�ꍇ�́A<BR>
     * �@@���u�o����܂Œ����戵�s�v�̗�O��throw����B     <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag:   BUSINESS_ERROR_00413 <BR>        
     * �@@�i������(*1)�`�ŏI���j�Ԃ̂��ׂĂ̔�c�Ɠ�(*2)��z��ɂĕԋp����B <BR>
     * <BR>
     * (*1) ������ <BR>
     * ������ԊǗ�.get������()�ɂĎ擾�B <BR>
     * <BR>
     * (*2) ��c�Ɠ� <BR>
     * �J�����_�e�[�u�����c�Ɠ��敪==�h��c�Ɠ��h�̍s�̓��t��S�Ď擾����B <BR>
     * �擾�������t�̂����A�i������(*1)�`�ŏI���j�̊��ԂɊ܂܂����́B <BR>
     * <BR>
     * @@return Date[]
     * @@throws WEB3SystemLayerException
     * @@roseuid 405E5F810189
     */
    public Date[] getExpirationDateHoliday() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExpirationDateHoliday()";
        log.entering(STR_METHOD_NAME);

        //�P�j�������w��ۂ̔��� 
        // �@@this.�戵�\��������Row��(�o����܂Œ���)�������w�肪 
        //   �h1�F�ŏI���̂ݎw��h�̏ꍇ��null��ԋp����
        if (WEB3CarriedOrderLapseDateSpecDef.FINAL_DATE_DESIGNATA.equals(this.enableOrderConditionRow.getCarriedOrderLapseDateSpec()))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�Q�j�������܂ł̋x���擾 

        //   this.get�o����܂Œ����ŏI��()�ɂčŏI�����擾����B
        Date l_datEndDay = this.getOrderUntilDeadLineEndDay();
        if(l_datEndDay == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00413,
                this.getClass().getName() + "." + STR_METHOD_NAME);            
        }
        SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat(
            WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        String l_strEndDay = l_format.format(l_datEndDay);

        //�������擾
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        l_format = GtlUtils.getThreadSafeSimpleDateFormat(
            WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        String l_strBizDay = l_format.format(l_datBizDate);

        //�J�����_�e�[�u�����c�Ɠ��敪==�h��c�Ɠ��h�̍s�̓��t��S�Ď擾����
        List l_lisRecords;
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" holiday >= ? ");
        l_sbWhere.append(" and holiday <= ? ");
        l_sbWhere.append(" and biz_date_type = ? ");
        Object[] l_objCalenderWhere = { 
            l_strBizDay,      //������ 
            l_strEndDay,      //�ŏI��
            WEB3BizDateTypeDef.NO_BIZ_DATE }; //��c�Ɠ�
        String l_strOrderBy = " holiday ";
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords =
                l_QueryProcessor.doFindAllQuery(
                CalendarRow.TYPE,
                l_sbWhere.toString(),
                l_strOrderBy,
                null,
                l_objCalenderWhere);

        }
        catch (DataFindException dfe)
        {
            log.error(STR_METHOD_NAME, dfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                dfe.getMessage(),
                dfe);
        }
        catch (DataQueryException dqe)
        {
            log.error(STR_METHOD_NAME, dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                dqe.getMessage(),
                dqe);
        }
        catch (DataNetworkException dne)
        {
            log.error(STR_METHOD_NAME, dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                dne.getMessage(),
                dne);
        }

        int l_intLength = l_lisRecords.size();
        //�����`�F�b�N
        if (l_intLength == 0)
        {
            return null;
        }

        // �i������(*1)�`�ŏI���j�Ԃ̂��ׂĂ̔�c�Ɠ�(*2)��z��ɂĕԋp����
        Date[] l_holidays = new Date[l_intLength];
        CalendarRow l_calendarRow;
        Date l_tmpDate;
        for (int i = 0; i < l_intLength; i++)
        {
            l_calendarRow = (CalendarRow)l_lisRecords.get(i);
            l_tmpDate = new Date(l_calendarRow.getHoliday().getTime());
            l_holidays[i] = WEB3DateUtility.toDay(l_tmpDate);
        }

        log.exiting(STR_METHOD_NAME);
        return l_holidays;

    }

    /**
     * (get�����������j���ꗗ) <BR>
     * <BR>
     * �o����܂Œ����̗L�������܂ł̂��ׂĂ̋x���i�j���j��z��ŕԋp����B <BR>
     * �i�J�n���w��j <BR>
     * �i�j���̂݁B�y���͊܂߂Ȃ��j <BR>
     * <BR>
     * �����́u�o����܂Œ���from���t�v��null�̏ꍇ�́A <BR>
     * this.get�����������j���ꗗ(void)�� <BR>
     * �Ϗ�����B <BR>
     * �����́u�o����܂Œ���from���t�v��null�̏ꍇ�́A�ȉ��̏������s���B <BR>
     * <BR>
     * �@@�P�j�������w��ۂ̔��� <BR>
     * �@@this.�戵�\��������Row��(�o����܂Œ���)�������w�肪 <BR>
     *   �h1�F�ŏI���̂ݎw��h�̏ꍇ��null��ԋp����B <BR>
     * <BR>
     * �@@�Q�j�������܂ł̋x���擾 <BR>
     * �@@�Ethis.get�o����܂Œ����ŏI��(�o����܂Œ���from���t)�ɂāA <BR>
     *   �ŏI�����擾����B <BR>
     * �@@��this.get�o����܂Œ����ŏI��(Date)��null���ԋp���ꂽ�ꍇ�́A<BR>
     * �@@���u�o����܂Œ����戵�s�v�̗�O��throw����B<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag:   BUSINESS_ERROR_00413 <BR>        
     * <BR>
     * �@@�E�i�����́u�o����܂Œ���from���t�v�`�ŏI���j�Ԃ́A <BR>
     * �@@�@@���ׂĂ̔�c�Ɠ�(*)��z��ɂĕԋp����B <BR>
     * <BR>
     * (*) ��c�Ɠ� <BR>
     * �J�����_�e�[�u�����c�Ɠ��敪==�h��c�Ɠ��h�̍s�̓��t��S�Ď擾����B<BR>
     * �擾�������t�̂����A�w��̊��ԂɊ܂܂����́B <BR>
     * <BR>
     * @@param l_datOrderUntilDeadLineFromDate - (�o����܂Œ���from���t) <BR>
     * <BR>
     * �o����܂Œ����̍ŏI�������߂�ۂ̊�ƂȂ�from���t�B <BR>
     * @@return Date[]
     * @@throws WEB3SystemLayerException
     * @@roseuid 4069194302B7
     */
    public Date[] getExpirationDateHoliday(Date l_datOrderUntilDeadLineFromDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExpirationDateHoliday(Date)";
        log.entering(STR_METHOD_NAME);

        //�����́u�o����܂Œ���from���t�v��null�̏ꍇ�́A 
        // this.get�����������j���ꗗ(void)�ɈϏ�����B
        if (l_datOrderUntilDeadLineFromDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            return this.getExpirationDateHoliday();
        }

        //�P�j�������w��ۂ̔��� 
        // �@@this.�戵�\��������Row��(�o����܂Œ���)�������w�肪 
        //   �h1�F�ŏI���̂ݎw��h�̏ꍇ��null��ԋp����
        if (WEB3CarriedOrderLapseDateSpecDef.FINAL_DATE_DESIGNATA.equals(this.enableOrderConditionRow.getCarriedOrderLapseDateSpec()))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�Q�j�������܂ł̋x���擾 

        //   this.get�o����܂Œ����ŏI��()�ɂčŏI�����擾����B
        Date l_datEndDay = this.getOrderUntilDeadLineEndDay(l_datOrderUntilDeadLineFromDate);
        if(l_datEndDay == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00413,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        SimpleDateFormat l_format =
            GtlUtils.getThreadSafeSimpleDateFormat(
                WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        String l_strEndDay = l_format.format(l_datEndDay);
        //����from���t���擾����
        l_format =
            GtlUtils.getThreadSafeSimpleDateFormat(
                WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        String l_strFromDay = l_format.format(l_datOrderUntilDeadLineFromDate);

        //�J�����_�e�[�u�����c�Ɠ��敪==�h��c�Ɠ��h�̍s�̓��t��S�Ď擾����
        List l_lisRecords;
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" holiday >= ? ");
        l_sbWhere.append(" and holiday <= ? ");
        l_sbWhere.append(" and biz_date_type = ? ");
        Object[] l_objCalenderWhere = { 
            l_strFromDay,      //����from���t 
            l_strEndDay,       //�ŏI��
            WEB3BizDateTypeDef.NO_BIZ_DATE }; //��c�Ɠ�
        String l_strOrderBy = " holiday ";
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords =
                l_QueryProcessor.doFindAllQuery(
                    CalendarRow.TYPE,
                    l_sbWhere.toString(),
                    l_strOrderBy,
                    null,
                    l_objCalenderWhere);

        }
        catch (DataFindException dfe)
        {
            log.error(STR_METHOD_NAME, dfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                dfe.getMessage(),
                dfe);
        }
        catch (DataQueryException dqe)
        {
            log.error(STR_METHOD_NAME, dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                dqe.getMessage(),
                dqe);
        }
        catch (DataNetworkException dne)
        {
            log.error(STR_METHOD_NAME, dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                dne.getMessage(),
                dne);
        }

        int l_intLength = l_lisRecords.size();
        //�����`�F�b�N
        if (l_intLength == 0)
        {
            return null;
        }

        // �@@�i������(*1)�`�ŏI���j�Ԃ̂��ׂĂ̔�c�Ɠ�(*2)��z��ɂĕԋp����
        Date[] l_holidays = new Date[l_intLength];
        CalendarRow l_calendarRow;
        Date l_tmpDate;
        for (int i = 0; i < l_intLength; i++)
        {
            l_calendarRow = (CalendarRow)l_lisRecords.get(i);
            l_tmpDate = new Date(l_calendarRow.getHoliday().getTime());
            l_holidays[i] = WEB3DateUtility.toDay(l_tmpDate);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_holidays;
    }

    /**
     * (is�戵�\���s����) <BR>
     * <BR>
     * �w�获�s�������戵�\���𔻒肷��B <BR>
     * <BR>
     * �����̎��s�������A�����Ȃ��iNONE�j�̏ꍇ��true��ԋp����B <BR>
     * 
     * �ȊO�Athis.�戵�\��������Row�́i���s�����j���ڂ� <BR>
     * �����̎��s�����ɑΉ����鍀��(*1)���A�h�戵�\�h�ł����true�A <BR>
     * �ȊO��false��ԋp����B <BR>
     * <BR>
     * (*1) ���s�����Ή����� <BR>
     * LIMIT_PRICE �F�i�����P���敪�j�w�l <BR>
     * MARKET_PRICE �F�i�����P���敪�j���s <BR>
     * AT_MARKET_OPEN �F�i���s�����j��� <BR>
     * AT_MARKET_CLOSE �F�i���s�����j���� <BR>
     * AT_MARKET_CLOSE_NOT_EXECUTED �F�i���s�����j�s�o���������s <BR>
     * <BR>
     * @@param l_executionCondType - ���s����
     * @@return boolean
     * @@roseuid 405E550202F0
     */
    public boolean isHandlingPossibleExecCond(EqTypeExecutionConditionType l_executionCondType)
    {
        boolean l_result = true;

        // AT_MARKET_OPEN �F�i���s�����j���
        if (l_executionCondType.intValue() == EqTypeExecutionConditionType.AT_MARKET_OPEN.intValue())
        {
            if (this.enableOrderConditionRow.getAtMarketOpen().compareTo(WEB3DealtDef.CAN_DEALT) == 0)
            {
                l_result = true;
            }
            else
            {
                l_result = false;
            }
        }
        //AT_MARKET_CLOSE �F�i���s�����j���� 
        if (l_executionCondType.intValue() == EqTypeExecutionConditionType.AT_MARKET_CLOSE.intValue())
        {
            if (this.enableOrderConditionRow.getAtMarketClose().compareTo(WEB3DealtDef.CAN_DEALT) == 0)
            {
                l_result = true;
            }
            else
            {
                l_result = false;
            }
        }
        //AT_MARKET_CLOSE_NOT_EXECUTED �F�i���s�����j�s�o���������s
        if (l_executionCondType.intValue() == EqTypeExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.intValue())
        {
            if (this.enableOrderConditionRow.getNoExecAtMartClose().compareTo(WEB3DealtDef.CAN_DEALT) == 0)
            {
                l_result = true;
            }
            else
            {
                l_result = false;
            }
        }

        return l_result;
    }

    /**
     * (is�戵�\���s����) <BR>
     * <BR>
     * �w�获�s�������戵�\���𔻒肷��B <BR>
     * <BR>
     * �����̎��s�������A�����Ȃ��iNONE�j�̏ꍇ��true��ԋp����B <BR>
     * 
     * �ȊO�Athis.�戵�\��������Row�́i���s�����j���ڂ� <BR>
     * �����̎��s�����ɑΉ����鍀��(*1)���A�h�戵�\�h�ł����true�A <BR>
     * �ȊO��false��ԋp����B <BR>
     * <BR>
     * (*1) ���s�����Ή����� <BR>
     * LIMIT_PRICE �F�i�����P���敪�j�w�l <BR>
     * MARKET_PRICE �F�i�����P���敪�j���s <BR>
     * AT_MARKET_OPEN �F�i���s�����j��� <BR>
     * AT_MARKET_CLOSE �F�i���s�����j���� <BR>
     * AT_MARKET_CLOSE_NOT_EXECUTED �F�i���s�����j�s�o���������s <BR>
     * <BR>
     * @@param l_ifoOrderExecutionCondType - ���s����
     * @@return boolean
     * @@roseuid 405E550202F0
     */
    public boolean isHandlingPossibleExecCond(IfoOrderExecutionConditionType l_ifoOrderExecutionCondType)
    {
        boolean l_result = true;
            
        if (IfoOrderExecutionConditionType.AT_MARKET_OPEN.equals(l_ifoOrderExecutionCondType))
        {
            //AT_MARKET_OPEN �F�i���s�����j���
            l_result = this.isHandlingPossibleExecCond(EqTypeExecutionConditionType.AT_MARKET_OPEN);
        }
        else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE.equals(l_ifoOrderExecutionCondType))
        {
            //AT_MARKET_CLOSE �F�i���s�����j����
            l_result = this.isHandlingPossibleExecCond(EqTypeExecutionConditionType.AT_MARKET_CLOSE);
        }
        else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_ifoOrderExecutionCondType))
        {
            //AT_MARKET_CLOSE_NOT_EXECUTED �F�i���s�����j�s�o���������s
            l_result = this.isHandlingPossibleExecCond(EqTypeExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED);
        }
    
        return l_result;  
    }
        
    /**
     * (is�戵�\��������) <BR>
     * <BR>
     * �w�蔭���������戵�\���𔻒肷��B <BR>
     * <BR>
     * �����̔����������ADEFAULT�i�����w��Ȃ��j�̏ꍇ��true��ԋp����B <BR>
     * <BR>
     * �ȊO�Athis.�戵�\��������Row�́i���������j���ڂ� <BR>
     * �����̔��������ɑΉ����鍀��(*1)���A�h�戵�\�h�ł����true�A <BR>
     * �ȊO��false��ԋp����B <BR>
     * <BR>
     * (*1) ���������Ή����� <BR>
     * �t�w�l�F�@@�i���������j�t�w�l <BR>
     * W�w�l�F�@@�i���������jW�w�l <BR>
     * <BR>
     * @@param l_strOrderCond - (��������) <BR>
     * �@@0�FDEFAULT�i�����w��Ȃ��j�@@1�F�t�w�l�@@2�FW�w�l <BR>
     * @@return boolean
     * @@roseuid 405E59270206
     */
    public boolean isHandlingPossibleOrderCond(String l_strOrderCond)
    {
        boolean l_result = false;

        if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderCond))
        {
            //�����̔����������ADEFAULT�i�����w��Ȃ��j�̏ꍇ��true��ԋp����
            l_result = true;
        }
        else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderCond))
        {
            //�t�w�l
            if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getStopOrder()))
            {
                l_result = true;
            }
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderCond))
        {
            //W�w�l
            if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getWLimitOrder()))
            {
                l_result = true;
            }

        }
        else
        {
            l_result = false;
        }

        return l_result;

    }

    /**
     * (is�o����܂Œ����\��) <BR>
     * �����̎��������o����܂Œ����\�����ǂ����𔻒肷��B
     * <BR>
     * �P�j�@@�����̒������������c�Ɠ��łȂ��ꍇ��false��ԋp����B <BR>
     * <BR>
     * �Q�j�@@this.get�o����܂Œ����������w��ɂčŏI���w��敪���擾����B <BR>
     * <BR>
     * �R�j�@@this.����ŏI����null���ŏI���w��敪���w1�F�ŏI���̂ݎw��x�̏ꍇ <BR>
     * �@@�@@�@@true��ԋp����B <BR>
     * <BR>
     * �S�j�@@this.get�o����܂Œ����ŏI��()�ɂčŏI�����擾����B <BR>
     * �@@�@@�@@null���ԋp���ꂽ�ꍇ�́A�u�o����܂Œ����戵�s�v�̗�O��throw����B <BR>
     * �@@�@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_00413 <BR>
     * <BR>
     * �T�j�@@�w����t���A�i�������`�ŏI���j�͈̔͊O�ł����false��ԋp����B <BR>
     * <BR>
     * �U�j�@@�ŏI���w��敪���w0�F���ԓ����������[�U�w��x�̏ꍇ�Atrue��ԋp����B <BR>
     * �@@�@@�@@�ŏI���w��敪���w1�F�ŏI���̂ݎw��x�̏ꍇ <BR>
     * �@@�@@�@@�w����t���ŏI���t�̏ꍇtrue��ԋp����B <BR>
     * �@@�@@�@@�w����t<>�ŏI���t�̏ꍇfalse��ԋp����B<BR>
     * <BR>
     * @@param l_datOrderExpDate - ����������
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 405E611E0120
     */
    public boolean isOrderUntilDeadLinePossibleDay(Date l_datOrderExpDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isOrderUntilDeadLinePossibleDay(Date)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�����̒������������c�Ɠ��łȂ��ꍇ��false��ԋp����
        Timestamp l_tsExpDate = new Timestamp(l_datOrderExpDate.getTime());
        String l_strBizDateType = 
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tsExpDate);
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //this.get�o����܂Œ����������w��ɂ�
        //�ŏI���w��敪���擾����
        String l_strCarriedOrderLapseDateSpec = getOrderUntilDeadLineExpDay();
        //this.����ŏI����null���ŏI���w��敪���w1�F�ŏI���̂ݎw��x�̏ꍇ
        //true��ԋp����
        if ((this.tradingEndDate != null)
            && (WEB3CarriedOrderLapseDateSpecDef.FINAL_DATE_DESIGNATA.equals(
                l_strCarriedOrderLapseDateSpec)))
        {
            return true;
        }

        SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat(
            "yyyyMMdd");

        //  this.get�o����܂Œ����ŏI��()�ɂčŏI�����擾����B
        String l_strEndDay;
        Date l_datEndDay = this.getOrderUntilDeadLineEndDay();
        if(l_datEndDay == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00413,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        else
        {
            l_strEndDay = l_format.format(l_datEndDay);    
        }
        
        
        //�������擾
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        String l_strBizDay = l_format.format(l_datBizDate);
        
        //�w����t(�����̎�����)
        String l_strOrderExpDay = l_format.format(l_datOrderExpDate);

        //�w����t���A�i�������`�ŏI���j�͈̔͊O�ł����false��ԋp����
        if ((l_strOrderExpDay.compareTo(l_strBizDay) < 0)
            || (l_strOrderExpDay.compareTo(l_strEndDay) > 0))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //�ŏI���w��敪���w0�F���ԓ����������[�U�w��x�̏ꍇ�A
        //true��ԋp����B 
        //�ŏI���w��敪���w1�F�ŏI���̂ݎw��x�̏ꍇ 
        //�w����t���ŏI���t�̏ꍇtrue��ԋp����B 
        //�w����t<>�ŏI���t�̏ꍇfalse��ԋp����B
        boolean l_result = false;
        if (WEB3CarriedOrderLapseDateSpecDef.EXPIRATION_DATE_USER_DES.equals(l_strCarriedOrderLapseDateSpec))
        {
            l_result = true;
        }
        else if (WEB3CarriedOrderLapseDateSpecDef.FINAL_DATE_DESIGNATA.equals(l_strCarriedOrderLapseDateSpec))
        {
            if (l_strOrderExpDay.compareTo(l_strEndDay) == 0)
            {
                l_result = true;
            }
            else
            {
                l_result = false;
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_result;
        
    }
    
    /**
     * (is�o����܂Œ����\��) <BR>
     * �����̎��������o����܂Œ����\�����ǂ����𔻒肷��B<BR>
     * (�����̌���������������N�Z�����o����܂Œ����ŏI�����g�p����B)<BR>
     * <BR>
     * �P�j�@@�����̒������������c�Ɠ��łȂ��ꍇ��false��ԋp����B <BR>
     * <BR>
     * �Q�j�@@this.get�o����܂Œ����������w��ɂčŏI���w��敪���擾����B <BR>
     * <BR>
     * �R�j�@@this.����ŏI����null���ŏI���w��敪���w1�F�ŏI���̂ݎw��x�̏ꍇ <BR>
     * �@@�@@�@@true��ԋp����B <BR>
     * <BR>
     * �S�j�@@this.get�o����܂Œ����ŏI��(����.������������)�ɂčŏI�����擾����B <BR>
     * �@@�@@�@@null���ԋp���ꂽ�ꍇ�́A�u�o����܂Œ����戵�s�v�̗�O��throw����B <BR>
     * �@@�@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_00413 <BR>
     * <BR>
     * �T�j�@@�w����t���A�i����.�������������`�ŏI���j�͈̔͊O�ł����false��ԋp����B <BR>
     * <BR>
     * �U�j�@@�ŏI���w��敪���w0�F���ԓ����������[�U�w��x�̏ꍇ�Atrue��ԋp����B <BR>
     * �@@�@@�@@�ŏI���w��敪���w1�F�ŏI���̂ݎw��x�̏ꍇ <BR>
     * �@@�@@�@@�w����t���ŏI���t�̏ꍇtrue��ԋp����B <BR>
     * �@@�@@�@@�w����t<>�ŏI���t�̏ꍇfalse��ԋp����B <BR>
     * <BR>
     * @@param l_datOrderExpDate - ����������
     * @@param l_datOrderBizDate - �������̔�����
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isOrderUntilDeadLinePossibleDay(
        Date l_datOrderExpDate,
        Date l_datOrderBizDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isOrderUntilDeadLinePossibleDay(Date,Date)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�����̒������������c�Ɠ��łȂ��ꍇ��false��ԋp����
        Timestamp l_tsExpDate = new Timestamp(l_datOrderExpDate.getTime());
        String l_strBizDateType = 
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tsExpDate);
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //�Q�jget�o����܂Œ����������w��ɂčŏI���w��敪���擾����B
        String l_strCarriedOrderLapseDateSpec = getOrderUntilDeadLineExpDay();
        //�R�jthis.����ŏI����null���ŏI���w��敪���w1�F�ŏI���̂ݎw��x�̏ꍇ
        //true��ԋp����
        if ((this.tradingEndDate != null)
            && (WEB3CarriedOrderLapseDateSpecDef.FINAL_DATE_DESIGNATA.equals(
                l_strCarriedOrderLapseDateSpec)))
        {
            return true;
        }

        //�S�jthis.get�o����܂Œ����ŏI��(����.������������)�ɂ� 
        // �ŏI�����擾����Bnull���ԋp���ꂽ�ꍇ�́A
        // �u�o����܂Œ����戵�s�v�̗�O��throw���� 
        Date l_datEndDay = this.getOrderUntilDeadLineEndDay(l_datOrderBizDate);
        if(l_datEndDay == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00413,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat(
            "yyyyMMdd");
        //�w����t(�����̎�����)
        String l_strOrderExpDay = l_format.format(l_datOrderExpDate);
        //�������̔�����
        String l_strBizDay = l_format.format(l_datOrderBizDate);
        //�ŏI��
        String l_strEndDay = l_format.format(l_datEndDay);  
        
        //�T�j�w����t���A�i����.�������������`�ŏI���j�͈̔͊O�� 
        //�����false��ԋp����B
        if ((l_strOrderExpDay.compareTo(l_strBizDay) < 0)
            || (l_strOrderExpDay.compareTo(l_strEndDay) > 0))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //�U�j�ŏI���w��敪���w0�F���ԓ����������[�U�w��x�̏ꍇ�A
        //true��ԋp����B 
        //�ŏI���w��敪���w1�F�ŏI���̂ݎw��x�̏ꍇ 
        //�w����t���ŏI���t�̏ꍇtrue��ԋp����B 
        //�w����t<>�ŏI���t�̏ꍇfalse��ԋp����B
        boolean l_result = false;
        if (l_strCarriedOrderLapseDateSpec.equals(WEB3CarriedOrderLapseDateSpecDef.EXPIRATION_DATE_USER_DES))
        {
            l_result = true;
        }
        else if (l_strCarriedOrderLapseDateSpec.equals(WEB3CarriedOrderLapseDateSpecDef.FINAL_DATE_DESIGNATA))
        {
            if (l_strOrderExpDay.compareTo(l_strEndDay) == 0)
            {
                l_result = true;
            }
            else
            {
                l_result = false;
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_result;
        
    }

    /**
     * (get�o����܂Œ����ŏI��) <BR>
     * �o����܂Œ����\�ȍŏI���t���擾����B <BR>
     * <BR>
     * �@@this.�戵�\��������Row�́u�o����܂Œ����v���ڂ� <BR>
     *   �����L�������ŏI�����擾����B <BR>
     * �@@�擾�������t����c�Ɠ��ł������ꍇ�A <BR>
     *   ���O�̉c�Ɠ��iex. �擾�������t���y�j����������A <BR>
     *   ���̑O���̋��j���j��ԋp����B <BR>
     * <BR>
     * �@@�� �h�戵�\�i�����w��j�h�̏ꍇ�́A <BR>
     *   ������(*1)�`�u�i�o����܂Œ����j�L�������v����Ԃ̉c�Ɠ���ΏۂƂ���B <BR>
     * �@@�� �u�戵�s�v�̏ꍇ�́Anull��ԋp����B <BR>
     * <BR>
     * (*1) ������ <BR>
     * ������ԊǗ�.get������()�ɂĎ擾�B <BR>
     * <BR>
     * @@return Date
     * @@throws WEB3SystemLayerException
     * @@roseuid 405E61B701C5
     */
    public Date getOrderUntilDeadLineEndDay() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getOrderUntilDeadLineEndDay()";
        log.entering(STR_METHOD_NAME);

        //get������
        Date l_datOrderBizDate;

        l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //get�o����܂Œ���
        String l_strCarriedOrder = this.enableOrderConditionRow.getCarriedOrder();
        Calendar l_calender = Calendar.getInstance();
        l_calender.setTime(l_datOrderBizDate);

        int l_intYear = l_calender.get(Calendar.YEAR);
        int l_intMonth = l_calender.get(Calendar.MONTH);
        int l_intDate = l_calender.get(Calendar.DATE);

        //�u�戵�s�v�̏ꍇ�́Anull��ԋp����
        if (WEB3CarriedOrderDef.CAN_NOT_DEALT.equals(l_strCarriedOrder))
        {
            return null;
        }

        //�戵�\�i�T���c�Ɠ��܂�)
        if (WEB3CarriedOrderDef.DEALT_TO_WEEK_END.equals(l_strCarriedOrder))
        {
            if (l_calender.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)
            {
                l_intDate = l_intDate + 6;
            }
            else if (l_calender.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY)
            {
                l_intDate = l_intDate + 5;
            }
            else if (l_calender.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY)
            {
                l_intDate = l_intDate + 4;
            }
            else if (l_calender.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY)
            {
                l_intDate = l_intDate + 3;
            }
            else if (l_calender.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY)
            {
                l_intDate = l_intDate + 2;
            }
            else if (l_calender.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
            {
                l_intDate = l_intDate + 1;
            }
        }

        //�戵�\�i�����c�Ɠ��܂Łj
        if (WEB3CarriedOrderDef.DEALT_TO_MONTH_END.equals(l_strCarriedOrder))
        {
            l_intMonth = l_intMonth + 1;
            l_intDate = 0;
        }

        //�戵�\�i3������j
        if (WEB3CarriedOrderDef.DEALT_TO_THREE_MONTH.equals(l_strCarriedOrder))
        {
            l_intMonth = l_intMonth + 4;
            l_intDate = 0;
        }
        
        //�L�������ŏI�����擾����
        l_calender.set(Calendar.YEAR,l_intYear);
        l_calender.set(Calendar.MONTH,l_intMonth);
        l_calender.set(Calendar.DATE,l_intDate);
        Date l_orderExecEndDay = l_calender.getTime();        

        //�戵�\�i�����w��j �F �L�������ŏI�����擾����
        //�i���j��c�Ɠ��i�y���Ȃǁj�̍l��������ׂ��B
        //�i���j�L�������ŏI���́A���������܂߂ĎZ�o����
        if (WEB3CarriedOrderDef.DEALT_DESIGNATA_DAYS.equals(l_strCarriedOrder))
        {
            //set ������
            Timestamp l_tsOrderBizDate = new Timestamp(l_datOrderBizDate.getTime());
            WEB3GentradeBizDate l_genBizDate = new WEB3GentradeBizDate(l_tsOrderBizDate);
            //get �i�o����܂Œ����j�L������
            int l_intCarriedOrderDayCount = this.enableOrderConditionRow.getCarriedOrderDayCount();
            if (l_intCarriedOrderDayCount > 1)
            {
                l_orderExecEndDay = l_genBizDate.roll(l_intCarriedOrderDayCount - 1);
                log.debug(" ****** [������] �F " + l_tsOrderBizDate);
                log.debug(" ****** [�i�o����܂Œ����j�L������] �F " + l_intCarriedOrderDayCount);
                log.debug(" ****** [�o����܂Œ����ŏI��] �F " + l_orderExecEndDay);
            }
            else
            {
                l_orderExecEndDay = l_datOrderBizDate;
            }
        }
        
        //�擾�������t����c�Ɠ��ł������ꍇ�A���O�̉c�Ɠ���ԋp����B
        String l_strBizDateType =
            WEB3GentradeTradingTimeManagement.getBizDateType(
                new Timestamp(l_orderExecEndDay.getTime()));
        if (l_strBizDateType.compareTo(WEB3BizDateTypeDef.NO_BIZ_DATE) == 0)
        {
            WEB3GentradeBizDate l_dateCalc =
                new WEB3GentradeBizDate(
                    new Timestamp(l_orderExecEndDay.getTime()));
            l_orderExecEndDay = l_dateCalc.roll(-1);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_orderExecEndDay;
    }

    /**
     * (get�o����܂Œ����ŏI��) <BR>
     * �o����܂Œ����\�ȍŏI���t���擾����B <BR>
     * �i�J�n���w��j <BR>
     * <BR>
     * �����́u�o����܂Œ���from���t�v��null�̏ꍇ�́A <BR>
     * this.get�o����܂Œ����ŏI��(void)�ɈϏ�����B <BR>
     * �����́u�o����܂Œ���from���t�v��null�̏ꍇ�́A�ȉ��̏������s���B <BR>
     * <BR>
     * �@@this.�戵�\��������Row�́u�o����܂Œ����v���ڂ� <BR>
     *   �����L�������ŏI�����擾����B <BR>
     * �@@�擾�������t����c�Ɠ��ł������ꍇ�A <BR>
     *   ���O�̉c�Ɠ��iex. �擾�������t���y�j����������A <BR>
     *   ���̑O���̋��j���j��ԋp����B <BR>
     * <BR>
     * �@@�� �h�戵�\�i�����w��j�h�̏ꍇ�́A
     *   �����́u�o����܂Œ���from���t�v�` <BR>
     *   �u�i�o����܂Œ����j�L�������v����Ԃ̉c�Ɠ���ΏۂƂ���B <BR>
     * �@@�� �u�戵�s�v�̏ꍇ�́Anull��ԋp����B <BR>
     * <BR>
     * @@param l_datOrderUntilDeadLineFromDate - (�o����܂Œ���from���t) <BR>
     * <BR>
     * �o����܂Œ����̍ŏI�������߂�ۂ̊�ƂȂ�from���t�B <BR>
     * @@return Date
     * @@throws WEB3SystemLayerException
     * @@roseuid 4069157403C0
     */
    public Date getOrderUntilDeadLineEndDay(Date l_datOrderUntilDeadLineFromDate) 
       throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getOrderUntilDeadLineEndDay(Date)";
        log.entering(STR_METHOD_NAME);

        //�����́u�o����܂Œ���from���t�v��null�̏ꍇ�́A
        //this.get�o����܂Œ����ŏI��(void)�ɈϏ�����B 
        if (l_datOrderUntilDeadLineFromDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            return getOrderUntilDeadLineEndDay();
        }

        //get�o����܂Œ���
        String l_strCarriedOrder =
            this.enableOrderConditionRow.getCarriedOrder();
        Calendar l_calender = Calendar.getInstance();
        l_calender.setTime(l_datOrderUntilDeadLineFromDate);

        int l_intYear = l_calender.get(Calendar.YEAR);
        int l_intMonth = l_calender.get(Calendar.MONTH);
        int l_intDate = l_calender.get(Calendar.DATE);

        //�u�戵�s�v�̏ꍇ�́Anull��ԋp����
        if (WEB3CarriedOrderDef.CAN_NOT_DEALT.equals(l_strCarriedOrder))
        {
            return null;
        }

        //�戵�\�i�T���c�Ɠ��܂�)
        if (WEB3CarriedOrderDef.DEALT_TO_WEEK_END.equals(l_strCarriedOrder))
        {
            if (l_calender.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)
            {
                l_intDate = l_intDate + 6;
            }
            else if (l_calender.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY)
            {
                l_intDate = l_intDate + 5;
            }
            else if (l_calender.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY)
            {
                l_intDate = l_intDate + 4;
            }
            else if (l_calender.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY)
            {
                l_intDate = l_intDate + 3;
            }
            else if (l_calender.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY)
            {
                l_intDate = l_intDate + 2;
            }
            else if (l_calender.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
            {
                l_intDate = l_intDate + 1;
            }
        }

        //�戵�\�i�����c�Ɠ��܂Łj
        if (WEB3CarriedOrderDef.DEALT_TO_MONTH_END.equals(l_strCarriedOrder))
        {
            l_intMonth = l_intMonth + 1;
            l_intDate = 0;
        }

        //�戵�\�i3������j
        if (WEB3CarriedOrderDef.DEALT_TO_THREE_MONTH.equals(l_strCarriedOrder))
        {
            l_intMonth = l_intMonth + 4;
            l_intDate = 0;
        }

        //�L�������ŏI�����擾����
        l_calender.set(Calendar.YEAR,l_intYear);
        l_calender.set(Calendar.MONTH,l_intMonth);
        l_calender.set(Calendar.DATE,l_intDate);
        Date l_orderExecEndDay = l_calender.getTime();
        
        //�戵�\�i�����w��j �F �L�������ŏI�����擾����
        //�i���j��c�Ɠ��i�y���Ȃǁj�̍l��������ׂ��B
        //�i���j�L�������ŏI���́A�o����܂Œ���from���t���܂߂ĎZ�o����
        if (WEB3CarriedOrderDef.DEALT_DESIGNATA_DAYS.equals(l_strCarriedOrder))
        {
            //set �o����܂Œ���from���t
            Timestamp l_tsOrderFromDate = new Timestamp(l_datOrderUntilDeadLineFromDate.getTime());
            WEB3GentradeBizDate l_genBizDate = new WEB3GentradeBizDate(l_tsOrderFromDate);
            //get �i�o����܂Œ����j�L������
            int l_intCarriedOrderDayCount = this.enableOrderConditionRow.getCarriedOrderDayCount();
            if (l_intCarriedOrderDayCount > 1)
            {
                l_orderExecEndDay = l_genBizDate.roll(l_intCarriedOrderDayCount - 1);
                log.debug(" ****** [�o����܂Œ���from���t] �F " + l_tsOrderFromDate);
                log.debug(" ****** [�i�o����܂Œ����j�L������] �F " + l_intCarriedOrderDayCount);
                log.debug(" ****** [�o����܂Œ����ŏI��] �F " + l_orderExecEndDay);
            }
            else
            {
                l_orderExecEndDay = l_datOrderUntilDeadLineFromDate;
            }
        }
        
        //�擾�������t����c�Ɠ��ł������ꍇ�A���O�̉c�Ɠ���ԋp����B
        String l_strBizDateType =
            WEB3GentradeTradingTimeManagement.getBizDateType(
                new Timestamp(l_orderExecEndDay.getTime()));
        if (l_strBizDateType.compareTo(WEB3BizDateTypeDef.NO_BIZ_DATE) == 0)
        {
            WEB3GentradeBizDate l_dateCalc =
                new WEB3GentradeBizDate(
                    new Timestamp(l_orderExecEndDay.getTime()));
            l_orderExecEndDay = l_dateCalc.roll(-1);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_orderExecEndDay;

    }

    /**
     * (get�o����܂Œ����������w��) <BR>
     * <BR>
     * �o����܂Œ����������w����擾����B <BR>
     * <BR>
     * �@@this.�戵�\��������Row�́u�o����܂Œ����������w��v���ڂ� <BR>
     *   �����o����܂Œ����������w����擾����B <BR>
     * �@@�擾�����o����܂Œ����������w���ԋp����B <BR>
     * @@return String
     * @@roseuid 4063BE8802C8
     */
    public String getOrderUntilDeadLineExpDay()
    {
        return this.enableOrderConditionRow.getCarriedOrderLapseDateSpec();
    }

    /**
     * (get�o����܂Œ����J�n��) <BR>
     * <BR>
     * �o����܂Œ����\�ȊJ�n���t���擾����B<BR> 
     * <BR>
     * �Ethis.�戵�\��������Row�́u�o����܂Œ����v���� 
     *   ���u�戵�s�v�̏ꍇ <BR>
     *      �u(�o����܂Œ���)�������w��v���h�ŏI���̂ݎw��h�� <BR> 
     *      �ꍇ�́Athis.get�o����܂Œ����ŏI��(void)�ɈϏ�����B<BR> 
     *      �u(�o����܂Œ���)�������w��v���h�ŏI���̂ݎw��h�� <BR> 
     *      �ꍇ�́A������(*1)��ԋp����B<BR> 
     *  <BR>
     * �E�u�戵�s�v�̏ꍇ�́Anull��ԋp����B<BR> 
     *  <BR>
     * (*1) ������ <BR>
     * ������ԊǗ�.get������()�ɂĎ擾�B <BR>
     * @@return Date
     * @@roseuid 406AAFD2013E
     */
    public Date getOrderUntilDeadLineStartDay() 
    {
        Date l_datStartDate = null;
        try
        {
            //this.�戵�\��������Row�́u�o����܂Œ����v���ځ��u�戵�s�v�̏ꍇ
            if ( ! WEB3CarriedOrderDef.CAN_NOT_DEALT.equals(this.enableOrderConditionRow.getCarriedOrder()))
            {
                String l_strCarriedOrderLapseDateSpec = 
                    this.enableOrderConditionRow.getCarriedOrderLapseDateSpec();
                if(WEB3CarriedOrderLapseDateSpecDef.FINAL_DATE_DESIGNATA.equals(l_strCarriedOrderLapseDateSpec))
                {
                    //�u(�o����܂Œ���)�������w��v���h�ŏI���̂ݎw��h�̏ꍇ�́A
                    //this.get�o����܂Œ����ŏI��(void)�ɈϏ�����B
                    l_datStartDate = this.getOrderUntilDeadLineEndDay();
                }
                else
                {
                    //�u(�o����܂Œ���)�������w��v���h�ŏI���̂ݎw��h�̏ꍇ�́A
                    //������(*1)��ԋp����B
                    l_datStartDate =
                        WEB3GentradeTradingTimeManagement.getOrderBizDate();
                }
            }
            else
            {
                //�u�戵�s�v�̏ꍇ�́Anull��ԋp����
                l_datStartDate = null;
            }
        }
        catch(WEB3SystemLayerException wse)
        {
            log.error("getOrderUntilDeadLineStartDay",wse);  
            l_datStartDate = null;
        }
        
        return l_datStartDate;
    }

    /**
     * (get�o����܂Œ����J�n��) <BR>
     *  <BR>
     * �o����܂Œ����\�ȊJ�n���t���擾����B<BR> 
     * �i�J�n���w��j<BR> 
     *  <BR>
     * �����́u�o����܂Œ���from���t�v��null�̏ꍇ�́A<BR>
     *   this.get�o����܂Œ����J�n��(void)�ɈϏ�����B <BR>
     * �����́u�o����܂Œ���from���t�v��null�̏ꍇ�́A<BR>
     *   �ȉ��̏������s���B<BR> 
     *  <BR>
     * �Ethis.�戵�\��������Row�́u�o����܂Œ����v����<BR>
     *   ���u�戵�s�v�̏ꍇ<BR> 
     *     �u(�o����܂Œ���)�������w��v���h�ŏI���̂ݎw��h��<BR>
     *     �ꍇ�́Athis.get�o����܂Œ����ŏI��(Date)�ɈϏ�����B<BR> 
     *     �u(�o����܂Œ���)�������w��v���h�ŏI���̂ݎw��h��<BR>
     *     �ꍇ�́A���������̂܂ܕԋp����B<BR> 
     *  <BR>
     * �E�u�戵�s�v�̏ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * @@param l_datOrderUntilDeadLineFromDate - (�o����܂Œ���from���t) <BR>
     * <BR>
     * �o����܂Œ����̍ŏI�������߂�ۂ̊�ƂȂ�from���t�B <BR>
     * @@return Date
     * @@throws WEB3SystemLayerException
     * @@roseuid 406AB3580374
     */
    public Date getOrderUntilDeadLineStartDay(Date l_datOrderUntilDeadLineFromDate) 
        throws WEB3SystemLayerException
    {
        //�����́u�o����܂Œ���from���t�v��null�̏ꍇ�́A 
        // this.get�o����܂Œ����J�n��(void)�ɈϏ�����
        if (l_datOrderUntilDeadLineFromDate == null)
        {
            return this.getOrderUntilDeadLineStartDay();
        }
        
        Date l_datStartDate;
        //this.�戵�\��������Row�́u�o����܂Œ����v���ځ��u�戵�s�v�̏ꍇ 
        if ( ! WEB3CarriedOrderDef.CAN_NOT_DEALT.equals(this.enableOrderConditionRow.getCarriedOrder()))
        {
            String l_strCarriedOrderLapseDateSpec = 
                this.enableOrderConditionRow.getCarriedOrderLapseDateSpec();
            if(WEB3CarriedOrderLapseDateSpecDef.FINAL_DATE_DESIGNATA.equals(l_strCarriedOrderLapseDateSpec))
            {
                //�u(�o����܂Œ���)�������w��v���h�ŏI���̂ݎw��h�̏ꍇ�́A
                //this.get�o����܂Œ����ŏI��(Date)�ɈϏ�����
                l_datStartDate = this.getOrderUntilDeadLineEndDay(l_datOrderUntilDeadLineFromDate);
            }
            else
            {
                //�u(�o����܂Œ���)�������w��v���h�ŏI���̂ݎw��h�̏ꍇ�́A
                //���������̂܂ܕԋp����
                l_datStartDate = l_datOrderUntilDeadLineFromDate;
            }
        }
        else
        {
            //�u�戵�s�v�̏ꍇ�́Anull��ԋp����
            l_datStartDate = null;
        }
        
        return l_datStartDate;
    }
    
    /**
     * (get�o����܂Œ����J�n��) <BR>
     *  <BR>
     * �o����܂Œ����\�ȊJ�n���t���擾����B<BR>
     *  �i�����������Ɏg�p�j<BR>
     *  <BR>
     * �Ethis.�戵�\��������Row�́u�o����܂Œ����v���ځ��u�戵�s�v�̏ꍇ<BR>
     *   �u(�o����܂Œ���)�������w��v���h�ŏI���̂ݎw��h�̏ꍇ�́A<BR>
     *   �����́u�o����܂Œ���to���t�v�����̂܂ܕԋp����B<BR>
     *  <BR>
     * �u(�o����܂Œ���)�������w��v���h�ŏI���̂ݎw��h�̏ꍇ�́A<BR>
     *   this.get�o����܂Œ����J�n��(�����́u�o����܂Œ���from���t�v)��<BR>
     *   delegate����B<BR>
     * <BR>
     * �E �u�戵�s�v�̏ꍇ�́Anull��ԋp����B<BR>
     *   <BR>
     * @@param l_orderUntilDeadLineFromDate - (�o����܂Œ���from���t) <BR>
     * @@param l_orderUntilDeadLineToDate - (�o����܂Œ���to���t) <BR>
     * @@return Date
     * @@throws WEB3SystemLayerException
     */
    public Date getOrderUntilDeadLineStartDay(
        Date l_orderUntilDeadLineFromDate,
        Date l_orderUntilDeadLineToDate)
        throws WEB3SystemLayerException
    {
        Date l_datStartDate;
        
        //this.�戵�\��������Row�́u�o����܂Œ����v���ځ��u�戵�s�v�̏ꍇ
        if ( ! WEB3CarriedOrderDef.CAN_NOT_DEALT.equals(this.enableOrderConditionRow.getCarriedOrder()))
        {
            String l_strCarriedOrderLapseDateSpec = 
                this.enableOrderConditionRow.getCarriedOrderLapseDateSpec();
            if(WEB3CarriedOrderLapseDateSpecDef.FINAL_DATE_DESIGNATA.equals(l_strCarriedOrderLapseDateSpec))
            {
                //�u(�o����܂Œ���)�������w��v���h�ŏI���̂ݎw��h�̏ꍇ�́A
                //�����́u�o����܂Œ���to���t�v�����̂܂ܕԋp����B
                l_datStartDate = l_orderUntilDeadLineToDate;
            }
            else
            {
                //�u(�o����܂Œ���)�������w��v���h�ŏI���̂ݎw��h�̏ꍇ�́A
                //this.get�o����܂Œ����J�n��(�����́u�o����܂Œ���from���t�v)��delegate����B
                l_datStartDate = this.getOrderUntilDeadLineStartDay(l_orderUntilDeadLineFromDate);
            }
        }
        else
        {
            //�u�戵�s�v�̏ꍇ�́Anull��ԋp����
            l_datStartDate = null;
        }
        
        return l_datStartDate;
    }

    /**
     * (is�o����܂Œ����戵�\) <BR>
     * <BR>
     * �o����܂Œ������戵�\���𔻒肷��B <BR>
     * <BR>
     * ���u�[��܂Œ����v�ɑΉ��������i�͂��̃��\�b�h�̎g�p�s�� <BR>
     * <BR>
     * �P�j�@@this.�戵�\���������敪�擾( )���R�[�����A<BR>
     * �@@�@@�@@�߂�lList�̗v�f����1�݂̂̏ꍇ��false���A <BR>
     *       �v�f����2�̏ꍇ��true��Ԃ��B <BR>
     * �@@�@@�@@��L�ȊO�̏ꍇ�́A��O��throw����B <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag:   BUSINESS_ERROR_00131 <BR>
     * <BR>
     * (*1) ���������敪 <BR>
     * 1�F�������� <BR>
     * 2�F�o����܂Œ��� <BR>
     * <BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 406B9F07024E
     */
    public boolean isOrderUntilDeadLinePossibleHandling()
        throws WEB3BaseException
        
    {
        final String STR_METHOD_NAME = "isOrderUntilDeadLinePossibleHandling()";
        
        String[] l_strHandingPossibleExpirationDateTypes =
            this.getHandlingPossibleExpirationDateType();
        if (l_strHandingPossibleExpirationDateTypes.length == 1)
        {
            return false;
        }
        else if (l_strHandingPossibleExpirationDateTypes.length == 2)
        {
            return true;
        }
        else
        {
            String l_strMessage  = 
                "this.�戵�\���������敪�擾( )���R�[�����A�߂�lList�̗v�f����1,2�ȊO�̏ꍇ";
            WEB3BusinessLayerException l_wbe = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00131,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_strMessage);
            log.error(STR_METHOD_NAME,l_wbe);
            throw l_wbe;
        }
    }

    /**
     * (is�o����܂Œ����戵�\<����ŏI���l��>) <BR>
     * <BR>
     * ����ŏI�����l�����āA�o����܂Œ������戵�\���𔻒肷��B <BR>
     * <BR>
     * �@@�@@�Ethis.�戵�\��������Row����A���ځu�o����܂Œ����v�̒l���擾����B <BR>
     * <BR>
     * �@@�@@�m�u�o����܂Œ��� == �h�戵�s�h�v�̏ꍇ�n <BR>
     * �@@�@@�@@�@@false��ԋp����B <BR>
     * <BR>
     * �@@�@@�m�u�o����܂Œ��� == �h�戵�\�h�v�̏ꍇ�n <BR>
     * �@@�@@�@@�@@this.����ŏI����null <BR>
     * and this.����ŏI�� <= ������ԊǗ�.get������()�̏ꍇ�A<BR>
     * false��ԋp����B <BR>
     * �@@�@@�@@�@@�ȊO�Atrue��ԋp����B <BR>
     * <BR>
     * @@return boolean
     * @@throws WEB3SystemLayerException
     * @@roseuid 406B9F07024E
     */
    public boolean isOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "isOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering()";
        log.entering(STR_METHOD_NAME);

        //this.�戵�\��������Row����A���ځu�o����܂Œ����v�̒l���擾����B
        String l_strCarriedOrder = this.enableOrderConditionRow.getCarriedOrder();

        //�u�o����܂Œ��� == �h�戵�s�h�v�̏ꍇ�@@false��ԋp����B
        if (WEB3CarriedOrderDef.CAN_NOT_DEALT.equals(l_strCarriedOrder))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        //�u�o����܂Œ��� == �h�戵�\�h�v�̏ꍇ�@@this.����ŏI����null
        //and this.����ŏI�� <= ������ԊǗ�.get������()�̏ꍇ�Afalse��ԋp����B
        //�@@�@@�@@�@@�ȊO�Atrue��ԋp����B
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        if ((this.tradingEndDate != null)
            && (WEB3DateUtility.compare(this.tradingEndDate, l_datBizDate)) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (is�[��܂Œ����戵�\) <BR>
     * <BR>
     * �[��܂Œ����\���ǂ����𔻒肷��B <BR>
     * <BR>
     * �@@�@@�Ethis.�戵�\��������Row�́u�[��܂Œ��� == �h�戵�\�h�v�ł����true��ԋp����B <BR>
     * <BR>
     * �@@�@@�ȊO�̏ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * @@return boolean
     */
    public boolean isEveningSessionOrderPossibleHandling()
    {
        final String STR_METHOD_NAME = "isEveningSessionOrderPossibleHandling()";
        log.entering(STR_METHOD_NAME);

        String l_strEveningSessionOrder =
            this.enableOrderConditionRow.getEveningSessionOrder();
        if (WEB3EveningSessionOrderDef.DEAL_ENABLE.equals(l_strEveningSessionOrder))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (is�[��܂Œ����戵�\<����ŏI���l��>) <BR>
     * <BR>
     * ����ŏI�����l�����āA�[��܂Œ����\���ǂ����𔻒肷��B <BR>
     * <BR>
     * �@@�@@�Ethis.is�[��܂Œ����戵�\()���R�[������B <BR>
     * <BR>
     * �@@�@@�mthis.is�[��܂Œ����戵�\() == false�̏ꍇ�n <BR>
     * �@@�@@�@@�@@false��ԋp����B <BR>
     * <BR>
     * �@@�@@�mthis.is�[��܂Œ����戵�\() == true�̏ꍇ�n <BR>
     * �@@�@@�@@�@@this.����ŏI����null <BR>
     * and this.����ŏI�� <= ������ԊǗ�.get������()�̏ꍇ�A<BR>
     * false��ԋp����B <BR>
     * �@@�@@�@@�@@�ȊO�Atrue��ԋp����B<BR>
     * <BR>
     * @@return boolean
     * @@throws WEB3SystemLayerException
     */
    public boolean isEveningSessionOrderPossibleHandlingTradingEndDateConsidering()
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "isEveningSessionOrderPossibleHandlingTradingEndDateConsidering()";
        log.entering(STR_METHOD_NAME);

        //this.is�[��܂Œ����戵�\()���R�[������B
        boolean l_blnIsEveningSessionOrderPossibleHandling =
            this.isEveningSessionOrderPossibleHandling();

        //�mthis.is�[��܂Œ����戵�\() == false�̏ꍇ�n false��ԋp����B
        if (!l_blnIsEveningSessionOrderPossibleHandling)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        //�mthis.is�[��܂Œ����戵�\() == true�̏ꍇ�n�@@this.����ŏI����null
        //and this.����ŏI�� <= ������ԊǗ�.get������()�̏ꍇ�Afalse��ԋp����B
        //�@@�@@�@@�@@�ȊO�Atrue��ԋp����B
        if ((this.tradingEndDate != null)
            && (WEB3DateUtility.compare(this.tradingEndDate, l_datBizDate)) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
    }

    /**
     * (is���s�����\) <BR>
     * <BR>
     * ���s�����\���ǂ����𔻒肷��B<BR>
     * <BR>
     * �V�K���iis�V�K�� == true�j�̏ꍇ�A<BR>
     *   �����iis���� == true�j�̏ꍇ<BR>
     *    this.�戵�\��������Row�̐��s�����i�V�K�����j == �h�戵�\�h<BR>
     *    �ł���΁Atrue��ԋp����B�ȊO�Afalse��ԋp����B<BR>
     * <BR>
     *   �����iis���� == false�j�̏ꍇ<BR>
     *    this.�戵�\��������Row�̐��s�����i�V�K�����j == �h�戵�\�h<BR>
     *    �ł���΁Atrue��ԋp����B�ȊO�Afalse��ԋp����B<BR>
     * <BR>
     * �ԍρiis�V�K�� == false�j�̏ꍇ�A<BR>
     *    this.�戵�\��������Row�̐��s�����i�ԍρj == �h�戵�\�h<BR>
     *    �ł���΁Atrue��ԋp����B�ȊO�Afalse��ԋp����B<BR>
     * <BR>
     * @@param l_isOpenContract - �i�V�K���j <BR>
     *     �V�K��������ǂ����̔���B <BR>
     *     �V�K���̏ꍇtrue�A�ԍς̏ꍇfalse�B <BR>
     * @@param l_isBuyOrder - �������ǂ����̔��ʁB<BR>
     *     �����̏ꍇ��true�A�����̏ꍇ��false�B<BR>
     * @@return boolean
     * @@roseuid 4076733901A5
     */
    public boolean isMarketOrderPossible(boolean l_isOpenContract, boolean l_isBuyOrder)
    {
        //�V�K���iis�V�K�� == true�j�̏ꍇ�A 
        if (l_isOpenContract)
        {
            //�����iis���� == true�j�̏ꍇ
            //this.�戵�\��������Row�̐��s�����i�V�K�����j == �h�戵�\�h
            //�ł���΁Atrue��ԋp����B�ȊO�Afalse��ԋp����B
            if (l_isBuyOrder)
            {
                if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getMartPriceBuyToOpen()))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                //�����iis���� == false�j�̏ꍇ
                //this.�戵�\��������Row�̐��s�����i�V�K�����j == �h�戵�\�h
                //�ł���΁Atrue��ԋp����B�ȊO�Afalse��ԋp����B
                if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getMartPriceSellToOpen()))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            //�ԍρiis�V�K�� == false�j�̏ꍇ�A 
            //this.�戵�\��������Row�̐��s�����i�ԍρj == �h�戵�\�h�ł���΁A
            //true��ԋp����B �ȊO�Afalse��ԋp����
            if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getMartPriceOrderSettleCont()))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    /**
     * �R���X�g���N�^�B <BR>
     * �����̏����Ɉ�v����戵�\���������I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@�����̒l�ɂĎ戵�\���������e�[�u������������B<BR>
     * <BR>
     * �Q�j�@@�s�I�u�W�F�N�g�Z�b�g<BR>
     * �@@�������ʂ̍s�I�u�W�F�N�g�i�戵�\��������Row�j��<BR>
     * this.�戵�\��������Row�ɃZ�b�g����B<BR>
     * <BR> 
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_productType - (�����^�C�v)<BR>
     * @@param l_strFuturesOptionDiv - (�敨�^�I�v�V�����敪) <BR>
     * @@param l_strMarginTradingDiv - (�M�p����敪)<BR>
     *     0�F DEFAULT�i�Œ�j<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     *    �iWEB3DealtDef�ɂĒ�`�j<BR>
     * @@throws WEB3SystemLayerException<BR>
     * @@roseuid 405E4FF8009E
     */
    public WEB3GentradeHandlingOrderCond(
        String l_strInstitutionCode,
        ProductTypeEnum l_productType,
        String l_strFuturesOptionDiv,
        String l_strMarginTradingDiv,
        String l_strMarketCode)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "WEB3GentradeHandingOrderCond(String,ProductTypeEnum,String,String,String)";
        log.entering(STR_METHOD_NAME);
        
        if(WEB3MarginTradingDivDef.DEFAULT.equals(l_strMarginTradingDiv) == false)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�M�p����敪 �F DEFAULT�i�Œ�j�A�����̐M�p����敪 = " + l_strMarginTradingDiv);
        }
        
        EnableOrderConditionRow l_row = null;
        try
        {
            //�戵�\���������e�[�u��
            l_row = EnableOrderConditionDao.findRowByPk(
                l_strInstitutionCode,
                l_productType,
                l_strFuturesOptionDiv,
                l_strMarginTradingDiv,
                l_strMarketCode);
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        this.enableOrderConditionRow = l_row;

        log.exiting(STR_METHOD_NAME);
    }


    /**
     * (�戵�\�l�i�����擾) <BR>
     * <BR>
     * �戵�\�l�i�������擾����B<BR>
     * <BR>
     * this.�戵�\��������Row�́i�l�i�����j���ڂŁh�戵�\�h��<BR>
     * �Ȃ��Ă��鍀�ڂɊY������l�i�����R�[�h(*1)��z��ɂĕԋp����B<BR>
     * �A���A�hDEFAULT(�����w��Ȃ�)�h�͕K���z��Ɋ܂߂�B<BR>
     * <BR>
     * (*1) �l�i�����R�[�h<BR>
     *  �iWEB3GentradePriceCondDef�ɂĒ�`�j<BR>
     * 0�F�@@DEFAULT(�����w��Ȃ�)<BR>
     * 1�F�@@���ݒl�w�l����<BR>
     * 3�F�@@�D��w�l����<BR>
     * 5�F�@@���s�c���w�l����<BR>
     * 7�F�@@���s�c���������<BR>
     * @@return String[]
     * @@roseuid 4076733901A5
     */
    public String[] getHandlingPriceCond()
    {
        final String STR_METHOD_NAME = "getHandlingPriceCond()";
        log.entering(STR_METHOD_NAME);

        ArrayList l_lstHandlingPriceConds = new ArrayList();

        //�A���A�hDEFAULT(�����w��Ȃ�)�h�͕K���z��Ɋ܂߂�B
        l_lstHandlingPriceConds.add(WEB3GentradePriceCondDef.DEFAULT);
        
        //���ݒl�w�l����
        if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getCurrentPriceOrder()))
        {
            l_lstHandlingPriceConds.add(WEB3GentradePriceCondDef.CURRENT_PRICE_ORDER);
        }
        
        //�D��w�l����
        if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getEaseCurrentPriceOrder()))
        {
            l_lstHandlingPriceConds.add(WEB3GentradePriceCondDef.EASE_CURRENT_PRICE_ORDER);
        }

        //���s�c���w�l����
        if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getMarketPriceRestLimitPrice()))
        {
            l_lstHandlingPriceConds.add(WEB3GentradePriceCondDef.MARKET_PRICE_REST_LIMIT_PRICE);
        }

        //���s�c���������
        if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getMarketPriceRestCancel()))
        {
            l_lstHandlingPriceConds.add(WEB3GentradePriceCondDef.MARKET_PRICE_REST_CANCEL);
        }

        int l_intSize = l_lstHandlingPriceConds.size();
        String[] l_handlingPriceConds = new String[l_intSize];
        for(int i = 0; i < l_intSize; i++)
        {
            l_handlingPriceConds[i] = (String)l_lstHandlingPriceConds.get(i);
        }   
        
        log.exiting(STR_METHOD_NAME);
        return l_handlingPriceConds;
    }

    /**
     * (is�戵�\�l�i����) <BR>
     * <BR>
     * �w��l�i�������戵�\���𔻒肷��B <BR>
     * <BR>
     * �����̒l�i�������A�h0�iDEFAULT(�����w��Ȃ�)�j�h�̏ꍇ��true��ԋp����B<BR>
     * <BR>
     * �ȊO�Athis.�戵�\��������Row�́i�l�i�����j���ڂň����̒l�i������<BR>
     * �Ή����鍀��(*1)���A�h�戵�\�h�ł����true�A�ȊO��false��ԋp����B <BR>
     * <BR>
     * (*1) �l�i�����Ή�����<BR>
     * 1�i���ݒl�w�l�����j�@@ �F�@@�i�l�i�����j���ݒl�w�l<BR>
     * 3�i�D��w�l�����j�@@�@@�@@�F�@@�i�l�i�����j�D��w�l<BR>
     * 5�i���s�c���w�l�����j�F�@@�i�l�i�����j���s�c���w�l<BR>
     * 7�i���s�c����������j�F�@@�i�l�i�����j���s�c�����<BR>
     * <BR>
     * @@param l_strPriceCond - (�l�i����)
     *  �iWEB3GentradePriceCondDef�ɂĒ�`�j<BR>
     * @@return boolean
     * @@roseuid 4076733901A5
     */
    public boolean isHandlingPriceCond(String l_strPriceCond)
    {
        final String STR_METHOD_NAME = "isHandlingPriceCond(String)";
        log.entering(STR_METHOD_NAME);
        
        if (WEB3GentradePriceCondDef.DEFAULT.equals(l_strPriceCond))
        {
            //�h0�iDEFAULT(�����w��Ȃ�)�j�h�̏ꍇ��true��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return true;    
        }
        
        boolean l_isHandlingPriceCond = false;
        
        if (WEB3GentradePriceCondDef.CURRENT_PRICE_ORDER.equals(l_strPriceCond))
        {
            //���ݒl�w�l����    
            if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getCurrentPriceOrder()))
            {
                //�h�戵�\�h�ł����true
                l_isHandlingPriceCond =  true;
            }
        }
        else if (WEB3GentradePriceCondDef.EASE_CURRENT_PRICE_ORDER.equals(l_strPriceCond))
        {
            //�D��w�l����    
            if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getEaseCurrentPriceOrder()))
            {
                //�h�戵�\�h�ł����true
                l_isHandlingPriceCond = true;
            }
        }
        else if (WEB3GentradePriceCondDef.MARKET_PRICE_REST_LIMIT_PRICE.equals(l_strPriceCond))
        {
            //���s�c���w�l����    
            if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getMarketPriceRestLimitPrice()))
            {
                //�h�戵�\�h�ł����true
                l_isHandlingPriceCond = true;
            }
        }
        else if (WEB3GentradePriceCondDef.MARKET_PRICE_REST_CANCEL.equals(l_strPriceCond))
        {
            //���s�c���������
            if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getMarketPriceRestCancel()))
            {
                //�h�戵�\�h�ł����true
                l_isHandlingPriceCond = true;
            }
        }
        else
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�l�i���� = " + l_strPriceCond);
        }

        log.exiting(STR_METHOD_NAME);
        return l_isHandlingPriceCond;
    }

    /**
     * (is�戵�\���s����) <BR>
     * <BR>
     * �w�获�s�������戵�\���𔻒肷��B <BR>
     * <BR>
     * �����̎��s�������A�����Ȃ��iNONE�j�̏ꍇ��true��ԋp����B <BR>
     * <BR>
     * �ȊO�Athis.�戵�\��������Row�́i���s�����j���ڂ� <BR>
     * �����̎��s�����ɑΉ����鍀��(*1)���A�h�戵�\�h�ł����true�A <BR>
     * �ȊO��false��ԋp����B <BR>
     * <BR>
     * (*1) ���s�����Ή����� <BR>
     * LIMIT_PRICE �F�i�����P���敪�j�w�l <BR>
     * MARKET_PRICE �F�i�����P���敪�j���s <BR>
     * AT_MARKET_OPEN �F�i���s�����j��� <BR>
     * AT_MARKET_CLOSE �F�i���s�����j���� <BR>
     * AT_MARKET_CLOSE_NOT_EXECUTED �F�i���s�����j�s�o���������s <BR>
     * <BR>
     * (*)������IfoExecutionConditionType�ɑΉ�����FeqExecutionConditionType�ɕϊ����āA<BR>
     * this.is�戵�\���s�����iEqTypeExecutionConditionType�j�ɏ������Ϗ�����B <BR>
     * <BR>
     * @@param l_executionCondType ���s����
     * @@return boolean
     * @@roseuid 405E550202F0
     */
    public boolean isHandlingPossibleExecCond(FeqExecutionConditionType l_executionCondType)
    {
        boolean l_result = true;
            
        if (FeqExecutionConditionType.AT_MARKET_OPEN.equals(l_executionCondType))
        {
            //AT_MARKET_OPEN �F�i���s�����j���
            l_result = this.isHandlingPossibleExecCond(EqTypeExecutionConditionType.AT_MARKET_OPEN);
        }
        else if (FeqExecutionConditionType.AT_MARKET_CLOSE.equals(l_executionCondType))
        {
            //AT_MARKET_CLOSE �F�i���s�����j����
            l_result = this.isHandlingPossibleExecCond(EqTypeExecutionConditionType.AT_MARKET_CLOSE);
        }
        else if (FeqExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_executionCondType))
        {
            //AT_MARKET_CLOSE_NOT_EXECUTED �F�i���s�����j�s�o���������s
            l_result = this.isHandlingPossibleExecCond(EqTypeExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED);
        }
    
        return l_result;
    }

    /**
     * (set����ŏI��) <BR>
     * <BR>
     * ����ŏI�����Z�b�g����B <BR>
     * <BR>
     * this.����ŏI���Ɉ����̒l���Z�b�g����B <BR>
     * <BR>
     * @@param l_datTradingEndDate ����ŏI��
     */
    public void setTradingEndDate(Date l_datTradingEndDate)
    {
        this.tradingEndDate = l_datTradingEndDate;
    }

    /**
     * (get�o����܂Œ����ŏI��<����ŏI���l��>) <BR>
     * <BR>
     * ����ŏI�����l�������o����܂Œ����\�ȍŏI���t���擾����B  <BR>
     * <BR>
     * �P�j�@@�ȉ��̏����Œ����L�������ŏI�����擾����B <BR>
     * <BR>
     * �@@�P�|�P�j�@@�p�����[�^.��������������null�̏ꍇ <BR>
     * �@@�@@�@@�@@�@@�@@this.get�o����܂Œ����ŏI��(void)���R�[������B <BR>
     * <BR>
     * �@@�P�|�Q�j�@@�p�����[�^.��������������null�̏ꍇ <BR>
     * �@@�@@�@@�@@�@@�@@this.get�o����܂Œ����ŏI��(����.������������)���R�[������B <BR>
     * <BR>
     * �Q�j�@@�P�j�Ŗ߂�l��null�i�o����܂Œ����戵�s�j�̏ꍇ�Anull��ԋp����B <BR>
     * <BR>
     * �R�j�@@��L�ȊO�̏ꍇ <BR>
     * <BR>
     * �@@�R�|�P�j�@@this.����ŏI����null�̏ꍇ�A<BR>
     * �P�j�Ŏ擾���������L�������ŏI����ԋp����B <BR>
     * <BR>
     * �@@�R�|�Q�j�@@��L�ȊO�̏ꍇ�Athis.����ŏI���ƒ����L�������ŏI�����r�� <BR>
     * �@@�@@�@@�@@�@@�@@���������̒l��ԋp����B<BR>
     * <BR>
     * @@param l_datOrderBizDate ������������
     * @@return Date
     * @@throws WEB3SystemLayerException
     */
    public Date getOrderUntilDeadLineEndDayTradingEndDateConsidering(
        Date l_datOrderBizDate) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "getOrderUntilDeadLineEndDayTradingEndDateConsidering(Date)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�����L�������ŏI�����擾����B
        Date l_datOrderExpirationEndDay = null;
        if (l_datOrderBizDate == null)
        {
            l_datOrderExpirationEndDay = this.getOrderUntilDeadLineEndDay();
        }
        else
        {
            l_datOrderExpirationEndDay =
                this.getOrderUntilDeadLineEndDay(l_datOrderBizDate);
        }

        //�Q�j�@@�P�j�Ŗ߂�l��null�i�o����܂Œ����戵�s�j�̏ꍇ�Anull��ԋp����B
        if (l_datOrderExpirationEndDay == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        //�R�|�P�j�@@this.����ŏI����null�̏ꍇ�A
        //�P�j�Ŏ擾���������L�������ŏI����ԋp����B
        else if (this.tradingEndDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            return l_datOrderExpirationEndDay;
        }
        //�R�|�Q�j�@@��L�ȊO�̏ꍇ�Athis.����ŏI���ƒ����L�������ŏI�����r��
        //�@@�@@�@@�@@�@@�@@���������̒l��ԋp����B
        else
        {
            if (WEB3DateUtility.compare(this.tradingEndDate, l_datOrderExpirationEndDay) <= 0)
            {
                log.exiting(STR_METHOD_NAME);
                return this.tradingEndDate;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return l_datOrderExpirationEndDay;
            }
        }
    }

    /**
     * (get�o����܂Œ����J�n��<����ŏI���l��>) <BR>
     * <BR>
     * ����ŏI�����l�������o����܂Œ����J�n�����擾����B  <BR>
     * <BR>
     * �P�j�@@�ȉ��̏����ŏo����܂Œ����J�n�����擾����B  <BR>
     * <BR>
     * �@@�P�|�P�j�@@�p�����[�^.�o����܂Œ����J�n����null�̏ꍇ  <BR>
     * �@@�@@�@@�@@�@@�@@this.get�o����܂Œ����J�n��(void)���R�[������B  <BR>
     * <BR>
     * �@@�P�|�Q�j�@@�p�����[�^.�o����܂Œ����J�n����null�̏ꍇ  <BR>
     * �@@�@@�@@�@@�@@�@@this.get�o����܂Œ����J�n��(�p�����[�^.�o����܂Œ����J�n��)���R�[������B  <BR>
     * <BR>
     * �Q�j�@@�ԋp�l���擾����B <BR>
     * <BR>
     * �@@�Q�|�P�j�@@�P�j�Ŗ߂�l��null�i�o����܂Œ����戵�s�j�̏ꍇ�Anull��ԋp����B  <BR>
     * <BR>
     * �@@�Q�|�Q�j�@@��L�ȊO�̏ꍇ  <BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�P�j�@@this.����ŏI����null�̏ꍇ�A<BR>
     * �P�j�Ŏ擾�����o����܂Œ����J�n����ԋp����B  <BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�Q�j�@@��L�ȊO�̏ꍇ�Athis.����ŏI����<BR>
     * �P�j�Ŏ擾�����o����܂Œ����J�n�����r���ď��������̒l��ԋp����B <BR>
     * <BR>
     * �@@�@@�@@�Q�|�Q�|�Q�|�P�j�@@this.����ŏI�������P�j�Ŏ擾�����o����܂Œ����J�n���̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@this.����ŏI����ԋp����B  <BR>
     * <BR>
     * �@@�@@�@@�Q�|�Q�|�Q�|�Q�j�@@this.����ŏI�����P�j�Ŏ擾�����o����܂Œ����J�n���̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�P�j�Ŏ擾�����o����܂Œ����J�n����ԋp����B <BR>
     * <BR>
     * @@param l_datOrderUntilDeadLineStartDate �o����܂Œ����J�n��
     * @@return Date
     * @@throws WEB3SystemLayerException
     */
    public Date getOrderUntilDeadLineStartDayTradingEndDateConsidering(
        Date l_datOrderUntilDeadLineStartDate) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "getOrderUntilDeadLineStartDayTradingEndDateConsidering(Date)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�o����܂Œ����J�n�����擾����B
        Date l_datOrderUntilDeadLineStartDay = null;
        if (l_datOrderUntilDeadLineStartDate == null)
        {
            l_datOrderUntilDeadLineStartDay = this.getOrderUntilDeadLineStartDay();
        }
        else
        {
            l_datOrderUntilDeadLineStartDay =
                this.getOrderUntilDeadLineStartDay(l_datOrderUntilDeadLineStartDate);
        }

        //�Q�j�@@�P�j�Ŗ߂�l��null�i�o����܂Œ����戵�s�j�̏ꍇ�Anull��ԋp����B
        if (l_datOrderUntilDeadLineStartDay == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        //�Q�|�Q�|�P�j�@@this.����ŏI����null�̏ꍇ�A
        //�P�j�Ŏ擾�����o����܂Œ����J�n����ԋp����B
        else if (this.tradingEndDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            return l_datOrderUntilDeadLineStartDay;
        }
        //�Q�|�Q�|�Q�j�@@��L�ȊO�̏ꍇ�A
        //this.����ŏI���ƂP�j�Ŏ擾�����o����܂Œ����J�n�����r����
        //���������̒l��ԋp����B
        else
        {
            if (WEB3DateUtility.compare(this.tradingEndDate, l_datOrderUntilDeadLineStartDay) <= 0)
            {
                log.exiting(STR_METHOD_NAME);
                return this.tradingEndDate;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return l_datOrderUntilDeadLineStartDay;
            }
        }
    }

    /**
     * (is�o����܂Œ����戵�\<����ŏI���l����>) <BR>
     * <BR>
     * �o����܂Œ������戵�\���𔻒肷��B <BR>
     * <BR>
     * �@@�@@�Ethis.�戵�\��������Row����A���ځu�o����܂Œ����v�̒l���擾����B <BR>
     * <BR>
     * �@@�@@�m�u�o����܂Œ��� == �h�戵�s�h�v�̏ꍇ�n <BR>
     * �@@�@@�@@�@@false��ԋp����B <BR>
     * <BR>
     * �@@�@@�m�u�o����܂Œ��� == �h�戵�\�h�v�̏ꍇ�n <BR>
     * �@@�@@�@@�@@true��ԋp����B <BR>
     * <BR>
     * @@return boolean
     * @@throws WEB3SystemLayerException
     */
    public boolean isOrderUntilDeadLinePossibleNoHandlingTradingEndDateConsidering()
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "isOrderUntilDeadLinePossibleNoHandlingTradingEndDateConsidering()";
        log.entering(STR_METHOD_NAME);

        //this.�戵�\��������Row����A���ځu�o����܂Œ����v�̒l���擾����B
        String l_strCarriedOrder = this.enableOrderConditionRow.getCarriedOrder();

        //�u�o����܂Œ��� == �h�戵�s�h�v�̏ꍇ�@@false��ԋp����B
        if (WEB3CarriedOrderDef.CAN_NOT_DEALT.equals(l_strCarriedOrder))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        //�u�o����܂Œ��� == �h�戵�\�h�v�̏ꍇ�@@true��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return true;
    }
}
@
