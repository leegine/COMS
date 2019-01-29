head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.55.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerCalcMargin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Y�]�͏��<�M�p�ڋq>(WEB3TPTradingPowerCalcMargin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 nakazato(ACT) �V�K�쐬
                 : 2007/01/22 �Ӂ@@�� (���u) �d�l�ύX �v�Z�����iNo.006 - 007�j
                   2007/01/22 ���؎q (���u) �d�l�ύX �v�Z�����iNo.005�j
                   2007/01/22 ���؎q (���u) �w�E�Ή�
Revesion History : 2008/04/01 �����Q (���u) ���f��No.266
Revesion History : 2008/09/10 ������ (���u) �v�Z����No.018
Revesion History : 2008/09/10 ���� (���u) ���f��No.300
Revesion History : 2008/10/21 �И��� (���u) ���f��No.328 �v�Z����No.019,020
Revesion History : 2008/11/26 ���� (���u) ���f��No.375
*/
package webbroker3.tradingpower;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.ProcessManagementDao;
import webbroker3.gentrade.data.ProcessManagementRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.data.TpCalcResultMarginDao;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailDao;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailParams;
import webbroker3.tradingpower.data.TpCalcResultMarginParams;
import webbroker3.tradingpower.define.WEB3TPActPayCheckDef;
import webbroker3.tradingpower.define.WEB3TPActualReceiptMargincallPowerCheckDef;
import webbroker3.tradingpower.define.WEB3TPAdddepositCheckDef;
import webbroker3.tradingpower.define.WEB3TPMarkToMarketDivDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitParams;
import webbroker3.util.WEB3LogUtility;

/**
 * �i���Y�]�͏��<�M�p�ڋq>�j
 * 
 * �]�͍X�V����<�M�p�ڋq>�����A�e�����\�z���v�Z����N���X
 */
public class WEB3TPTradingPowerCalcMargin
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPTradingPowerCalcMargin.class);

    /**
     * �i�]�͌v�Z����Params<�M�p�ڋq>�j
     */
    protected TpCalcResultMarginParams calcResultMargin;

    /**
     * �i�]�͌v�Z���ʏڍ�Params<�M�p�ڋq>�j
     */
    protected TpCalcResultMarginDetailParams calcResultDetailMargin;

    /**
     * �i�g�p�\�������j
     */
    protected WEB3TPActualAccountBalanceInfo actualBalanceInfo = null;

    /**
     * �i�]�͌v�Z�����j
     */
    protected WEB3TPCalcCondition calcCondition;

    /**
     * �i���������\�񒍕��P�ʈꗗ�j 
     * 
     * ������(T+0)�ȍ~�̊����\�񒍕��P�ʃe�[�u��Row�I�u�W�F�N�g�̃��X�g 
     */
    protected List todaysRsvEqOrderUnits = null;

    /**
     * @@roseuid 410DF85F0091
     */
    public WEB3TPTradingPowerCalcMargin()
    {

    }

    /**
     * �i�R���X�g���N�^�j<BR>
     * <BR>
     * �����𑮐��ɃZ�b�g����B<BR>
     * <BR>
     * �P�j����.�]�͌v�Z����<�M�p�ڋq>���A�]�͌v�Z����Params<�M�p�ڋq>�I�u�W�F�N�g���擾���A<BR>
     * �@@this.�]�͌v�Z����Params<�M�p�ڋq>�ɃZ�b�g����B<BR>
     * <BR>
     * �Q�j����.�]�͌v�Z����<�M�p�ڋq>���A�]�͌v�Z���ʏڍ�Params<�M�p�ڋq>�I�u�W�F�N�g���擾���A<BR>
     * �@@this.�]�͌v�Z���ʏڍ�Params<�M�p�ڋq>�ɃZ�b�g����B<BR>
     * <BR>
     * �R�j����.�]�͌v�Z�������Athis.�]�͌v�Z�����ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_calcResult - �i�]�͌v�Z���ʁj
     * @@param l_calcCondition - �i�]�͌v�Z�����j
     */
    public WEB3TPTradingPowerCalcMargin(List l_calcResult, WEB3TPCalcCondition l_calcCondition)
    {
        /*
         * List���]�͌v�Z����<�M�p�ڋq>�I�u�W�F�N�g�A�]�͌v�Z���ʏڍ�<�M�p�ڋq>�I�u�W�F�N�g
         * ���擾���v���p�e�B�ɃZ�b�g����B
         */
        for (Iterator l_iter = l_calcResult.iterator(); l_iter.hasNext();)
        {
            Object l_element = (Object)l_iter.next();

            if (l_element instanceof TpCalcResultMarginParams)
            {
                TpCalcResultMarginParams l_params = (TpCalcResultMarginParams)l_element;
                this.setCalcResultMargin(l_params);
            }
            else if (l_element instanceof TpCalcResultMarginDetailParams)
            {
                TpCalcResultMarginDetailParams l_params = (TpCalcResultMarginDetailParams)l_element;
                this.setCalcResultDetailMargin(l_params);
            }
        }

        //�p�����[�^.�]�͌v�Z�������v���p�e�B�ɃZ�b�g����B
        this.calcCondition = l_calcCondition;
    }

    /**
     * (�R���X�g���N�^)<BR>
     * <BR>
     * �����𑮐��ɃZ�b�g����B<BR>
     * <BR>
     * �P�j����.�]�͌v�Z����<�M�p�ڋq>���A�]�͌v�Z����Params<�M�p�ڋq>�I�u�W�F�N�g���擾���A<BR>
     * �@@this.�]�͌v�Z����Params<�M�p�ڋq>�ɃZ�b�g����B<BR>
     * <BR>
     * �Q�j����.�]�͌v�Z����<�M�p�ڋq>���A�]�͌v�Z���ʏڍ�Params<�M�p�ڋq>�I�u�W�F�N�g���擾���A<BR>
     * �@@this.�]�͌v�Z���ʏڍ�Params<�M�p�ڋq>�ɃZ�b�g����B<BR>
     * <BR>
     * �R�j����.�g�p�\���������Athis.�g�p�\�������ɃZ�b�g����B<BR>
     * <BR>
     * �S�j����.�]�͌v�Z�������Athis.�]�͌v�Z�����ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_calcResult - �i�]�͌v�Z���ʁj
     * @@param l_actualBalanceInfo - �i�g�p�\�������j
     * @@param l_calcCondition - �i�]�͌v�Z�����j
     */
    public WEB3TPTradingPowerCalcMargin(
        List l_calcResult,
        WEB3TPActualAccountBalanceInfo l_actualBalanceInfo,
        WEB3TPCalcCondition l_calcCondition)
    {
        /*
         * List���]�͌v�Z����<�M�p�ڋq>�I�u�W�F�N�g�A�]�͌v�Z���ʏڍ�<�M�p�ڋq>�I�u�W�F�N�g
         * ���擾���v���p�e�B�ɃZ�b�g����B
         */
        for (Iterator l_iter = l_calcResult.iterator(); l_iter.hasNext();)
        {
            Object l_element = (Object)l_iter.next();

            if (l_element instanceof TpCalcResultMarginParams)
            {
                TpCalcResultMarginParams l_params = (TpCalcResultMarginParams)l_element;
                this.setCalcResultMargin(l_params);
            }
            else if (l_element instanceof TpCalcResultMarginDetailParams)
            {
                TpCalcResultMarginDetailParams l_params = (TpCalcResultMarginDetailParams)l_element;
                this.setCalcResultDetailMargin(l_params);
            }
        }

        //�p�����[�^.�g�p�\���������v���p�e�B�ɃZ�b�g����B
        this.actualBalanceInfo = l_actualBalanceInfo;

        //�p�����[�^.�]�͌v�Z�������v���p�e�B�ɃZ�b�g����B
        this.calcCondition = l_calcCondition;
    }

    /**
     * �iget�]�͌v�Z����Params<�M�p�ڋq><BR>
     * 
     * this.�]�͌v�Z����Params<�M�p�ڋq>��ԋp����B<BR>
     * @@return webbroker3.tradingpower.data.TpCalcResultMarginParams
     * @@roseuid 40FCBFDA029C
     */
    public TpCalcResultMarginParams getCalcResultMargin()
    {
        return this.calcResultMargin;
    }

    /**
     * �iset�]�͌v�Z����Params<�M�p�ڋq><BR>
     * 
     * �p�����[�^.�]�͌v�Z����Params<�M�p�ڋq>��this.�]�͌v�Z����Params<�M�p�ڋq>�ɃZ�b
     * �g����B<BR>
     * @@param l_calcResultMargin - �i�]�͌v�Z����Params<�M�p�ڋq>�j
     * @@roseuid 40FCBFDA02CB
     */
    public void setCalcResultMargin(TpCalcResultMarginParams l_calcResultMargin)
    {
        this.calcResultMargin = l_calcResultMargin;
    }

    /**
     * �iget�]�͌v�Z���ʏڍ�Params<�M�p�ڋq><BR>
     * 
     * this.�]�͌v�Z���ʏڍ�Params<�M�p�ڋq>��ԋp����B<BR>
     * @@return webbroker3.tradingpower.data.TpCalcResultMarginDetailParams
     */
    public TpCalcResultMarginDetailParams getCalcResultDetailMargin()
    {
        return this.calcResultDetailMargin;
    }

    /**
     * �iset�]�͌v�Z���ʏڍ�Params<�M�p�ڋq><BR>
     * 
     * �p�����[�^.�]�͌v�Z���ʏڍ�Params<�M�p�ڋq>��this.�]�͌v�Z���ʏڍ�Params<�M�p�ڋq>�ɃZ�b
     * �g����B<BR>
     * @@param l_calcResultDetailMargin - �i�]�͌v�Z���ʏڍ�Params<�M�p�ڋq>�j
     */
    public void setCalcResultDetailMargin(TpCalcResultMarginDetailParams l_calcResultDetailMargin)
    {
        this.calcResultDetailMargin = l_calcResultDetailMargin;
    }

    /**
     * �iget�g�p�\�������j<BR>
     * 
     * this.�g�p�\��������ԋp����B<BR>
     * @@return webbroker3.tradingpower.ActualAccountBalanceInfo
     */
    public WEB3TPActualAccountBalanceInfo getActualBalanceInfo()
    {
        return this.actualBalanceInfo;
    }

    /**
     * �iset�g�p�\�������j<BR>
     * 
     * �p�����[�^.�g�p�\��������this.�g�p�\�������ɃZ�b�g����B<BR>
     * @@param l_actualBalanceInfo - �i�g�p�\�������j
     */
    public void setActualBalanceInfo(WEB3TPActualAccountBalanceInfo l_actualBalanceInfo)
    {
        this.actualBalanceInfo = l_actualBalanceInfo;
    }

    /**
     * �iget�]�͌v�Z����<BR>
     * 
     * this.�]�͌v�Z������ԋp����B<BR>
     * @@return webbroker3.tradingpower.WEB3TPCalcCondition
     * @@roseuid 40FCBFDA02FA
     */
    public WEB3TPCalcCondition getCalcCondition()
    {
        return this.calcCondition;
    }

    /**
     * �iset�]�͌v�Z�����j<BR>
     * 
     * �p�����[�^.�]�͌v�Z������this.�]�͌v�Z�����ɃZ�b�g����B<BR>
     * @@param l_calcCondition - �i�]�͌v�Z�����j
     * @@roseuid 40FCBFDA0329
     */
    public void setCalcCondition(WEB3TPCalcCondition l_calcCondition)
    {
        this.calcCondition = l_calcCondition;
    }

    /**
     * �iget���������\�񒍕��P�ʈꗗ�j<BR>
     * <BR>
     * this.���������\�񒍕��P�ʈꗗ��ԋp����B<BR>
     * <BR>
     * @@return List
     */
    public List getTodaysRsvEqOrderUnits()
    {
        return this.todaysRsvEqOrderUnits;
    }

    /**
     * �iset���������\�񒍕��P�ʈꗗ�j<BR>
     * <BR>
     * ����.���������\�񒍕��P�ʈꗗ��this.���������\�񒍕��P�ʈꗗ�ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_todaysRsvEqOrderUnits - (���������\�񒍕��P�ʈꗗ)
     */
    public void setTodaysRsvEqOrderUnits(List l_todaysRsvEqOrderUnits)
    {
        this.todaysRsvEqOrderUnits = l_todaysRsvEqOrderUnits;
    }

    /**
     * �iget�a����c���j<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�a����c���v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * n��0�ȏ�5�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�w����́u�a����c���v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * this.�]�͌v�Z����Params<�M�p�ڋq>.get�a����c���iT+n�j<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B409080125
     */
    public double getAccountBalance(int l_intSpecifiedPoint)
    {
        //�a����c��
        double l_dblAccountBalance;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //�a����c��( T + 0 )���擾����B
                l_dblAccountBalance = this.calcResultMargin.getAccountBalance0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //�a����c��( T + 1 )���擾����B
                l_dblAccountBalance = this.calcResultMargin.getAccountBalance1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //�a����c��( T + 2 )���擾����B
                l_dblAccountBalance = this.calcResultMargin.getAccountBalance2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //�a����c��( T + 3 )���擾����B
                l_dblAccountBalance = this.calcResultMargin.getAccountBalance3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //�a����c��( T + 4 )���擾����B
                l_dblAccountBalance = this.calcResultMargin.getAccountBalance4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //�a����c��( T + 5 )���擾����B
                l_dblAccountBalance = this.calcResultMargin.getAccountBalance5();
                break;

            default :
                //�G���[���X���[
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getAccountBalance(int)");
        }

        //�擾�����a����c����ԋp����B
        return l_dblAccountBalance;
    }

    /**
     * �iget�������ϑ��<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�������ϑ���v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * n��0�ȏ�5�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�w����́u�������ϑ���v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * this.�]�͌v�Z����Params<�M�p�ڋq>.get�������ϑ���iT+n�j<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B409630382
     */
    public double getTodayExecutedAmount(int l_intSpecifiedPoint)
    {
        //�������ϑ��
        double l_dblTodayExecutedAmount;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //�������ϑ��( T + 0 )���擾����B
                l_dblTodayExecutedAmount = this.calcResultMargin.getTodayExecutedAmount0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //�������ϑ��( T + 1 )���擾����B
                l_dblTodayExecutedAmount = this.calcResultMargin.getTodayExecutedAmount1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //�������ϑ��( T + 2 )���擾����B
                l_dblTodayExecutedAmount = this.calcResultMargin.getTodayExecutedAmount2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //�������ϑ��( T + 3 )���擾����B
                l_dblTodayExecutedAmount = this.calcResultMargin.getTodayExecutedAmount3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //�������ϑ��( T + 4 )���擾����B
                l_dblTodayExecutedAmount = this.calcResultMargin.getTodayExecutedAmount4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //�������ϑ��( T + 5 )���擾����B
                l_dblTodayExecutedAmount = this.calcResultMargin.getTodayExecutedAmount5();
                break;

            default :
                //�G���[���X���[
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getTodayExecutedAmount(int)");
        }

        //�擾�����������ϑ����ԋp����B
        return l_dblTodayExecutedAmount;
    }

    /**
     * �iget������������j<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u������������v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * n��0�ȏ�5�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�w����́u������������v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * this.�]�͌v�Z����Params<�M�p�ڋq>.get������������iT+n�j<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B409B70294
     */
    public double getTodayUnexecutedAmount(int l_intSpecifiedPoint)
    {
        //�����������
        double l_dblTodayUnexecutedAmount;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //�������ϑ��( T + 0 )���擾����B
                l_dblTodayUnexecutedAmount = this.calcResultMargin.getTodayUnexecutedAmount0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //�������ϑ��( T + 1 )���擾����B
                l_dblTodayUnexecutedAmount = this.calcResultMargin.getTodayUnexecutedAmount1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //�������ϑ��( T + 2 )���擾����B
                l_dblTodayUnexecutedAmount = this.calcResultMargin.getTodayUnexecutedAmount2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //�������ϑ��( T + 3 )���擾����B
                l_dblTodayUnexecutedAmount = this.calcResultMargin.getTodayUnexecutedAmount3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //�������ϑ��( T + 4 )���擾����B
                l_dblTodayUnexecutedAmount = this.calcResultMargin.getTodayUnexecutedAmount4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //�������ϑ��( T + 5 )���擾����B
                l_dblTodayUnexecutedAmount = this.calcResultMargin.getTodayUnexecutedAmount5();
                break;

            default :
                //�G���[���X���[
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getTodayUnexecutedAmount(int)");
        }

        //�擾�����������ϑ����ԋp����B
        return l_dblTodayUnexecutedAmount;
    }

    /**
     * �iget���v��S�����j<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u���v��S�����v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * n��0�ȏ�5�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�w����́u���v��S�����v���v�Z����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * this.�]�͌v�Z����Params<�M�p�ڋq>.get���v��S�����iT+n�j<BR>
     * 
     * �R�j�@@���v��S����(n)��ԋp����B
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B42B930141
     */
    public double getDayTradeRestraint(int l_intSpecifiedPoint)
    {
        //���v��S����
        double l_dblDayTradeRestraint;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //���v��S����( T + 0 )���擾����B
                l_dblDayTradeRestraint = this.calcResultMargin.getDayTradeRestraint0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //���v��S����( T + 1 )���擾����B
                l_dblDayTradeRestraint = this.calcResultMargin.getDayTradeRestraint1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //���v��S����( T + 2 )���擾����B
                l_dblDayTradeRestraint = this.calcResultMargin.getDayTradeRestraint2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //���v��S����( T + 3 )���擾����B
                l_dblDayTradeRestraint = this.calcResultMargin.getDayTradeRestraint3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //���v��S����( T + 4 )���擾����B
                l_dblDayTradeRestraint = this.calcResultMargin.getDayTradeRestraint4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //���v��S����( T + 5 )���擾����B
                l_dblDayTradeRestraint = this.calcResultMargin.getDayTradeRestraint5();
                break;

            default :
                //�G���[���X���[
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getDayTradeRestraint(int)");
        }

        //�擾�������v��S������ԋp����B
        return l_dblDayTradeRestraint;
    }

    /**
     * �iget���̑��S�����j<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u���̑��S�����v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * n��0�ȏ�5�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�w����́u���̑��S�����v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * this.�]�͌v�Z����Params<�M�p�ڋq>.get���̑��S�����iT+n�j<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B4093A02C8
     */
    public double getOtherRestraint(int l_intSpecifiedPoint)
    {
        //���̑��S����
        double l_dblOtherRestraint;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //���̑��S����( T + 0 )���擾����B
                l_dblOtherRestraint = this.calcResultMargin.getOtherRestraint0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //���̑��S����( T + 1 )���擾����B
                l_dblOtherRestraint = this.calcResultMargin.getOtherRestraint1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //���̑��S����( T + 2 )���擾����B
                l_dblOtherRestraint = this.calcResultMargin.getOtherRestraint2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //���̑��S����( T + 3 )���擾����B
                l_dblOtherRestraint = this.calcResultMargin.getOtherRestraint3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //���̑��S����( T + 4 )���擾����B
                l_dblOtherRestraint = this.calcResultMargin.getOtherRestraint4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //���̑��S����( T + 5 )���擾����B
                l_dblOtherRestraint = this.calcResultMargin.getOtherRestraint5();
                break;

            default :
                //�G���[���X���[
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getOtherRestraint(int)");
        }

        //�擾�������̑��S������ԋp����B
        return l_dblOtherRestraint;
    }

    /**
     * �iget��p�،��]���z�j<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u��p�،��]���z�v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * n��0�ȏ�5�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�w����́u��p�،��]���z�v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * this.�]�͌v�Z����Params<�M�p�ڋq>.get��p�،��]���z�iT+n�j<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B4299A0092
     */
    public double getSubstituteSecurityAsset(int l_intSpecifiedPoint)
    {
        //��p�،��]���z
        double l_dblSubstituteSecurityAsset;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //��p�،��]���z( T + 0 )���擾����B
                l_dblSubstituteSecurityAsset = this.calcResultMargin.getSubstituteSecurityAsset0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //��p�،��]���z( T + 1 )���擾����B
                l_dblSubstituteSecurityAsset = this.calcResultMargin.getSubstituteSecurityAsset1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //��p�،��]���z( T + 2 )���擾����B
                l_dblSubstituteSecurityAsset = this.calcResultMargin.getSubstituteSecurityAsset2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //��p�،��]���z( T + 3 )���擾����B
                l_dblSubstituteSecurityAsset = this.calcResultMargin.getSubstituteSecurityAsset3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //��p�،��]���z( T + 4 )���擾����B
                l_dblSubstituteSecurityAsset = this.calcResultMargin.getSubstituteSecurityAsset4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //��p�،��]���z( T + 5 )���擾����B
                l_dblSubstituteSecurityAsset = this.calcResultMargin.getSubstituteSecurityAsset5();
                break;

            default :
                //�G���[���X���[
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getSubstituteSecurityAsset(int)");
        }

        //�擾������p�،��]���z��ԋp����B
        return l_dblSubstituteSecurityAsset;
    }

    /**
     * �iget�����ό��ʑ���j<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�����ό��ʑ���v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * n��0�ȏ�5�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�w����́u�����ό��ʑ���v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * this.�]�͌v�Z����Params<�M�p�ڋq>.get�����ό��ʑ���iT+n�j<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B429BC013E
     */
    public double getContractAmount(int l_intSpecifiedPoint)
    {
        //�����ό��ʑ��
        double l_dblContractAmount;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //�����ό��ʑ��( T + 0 )���擾����B
                l_dblContractAmount = this.calcResultMargin.getContractAmount0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //�����ό��ʑ��( T + 1 )���擾����B
                l_dblContractAmount = this.calcResultMargin.getContractAmount1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //�����ό��ʑ��( T + 2 )���擾����B
                l_dblContractAmount = this.calcResultMargin.getContractAmount2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //�����ό��ʑ��( T + 3 )���擾����B
                l_dblContractAmount = this.calcResultMargin.getContractAmount3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //�����ό��ʑ��( T + 4 )���擾����B
                l_dblContractAmount = this.calcResultMargin.getContractAmount4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //�����ό��ʑ��( T + 5 )���擾����B
                l_dblContractAmount = this.calcResultMargin.getContractAmount5();
                break;

            default :
                //�G���[���X���[
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getContractAmount(int)");
        }

        //�擾���������ό��ʑ����ԋp����B
        return l_dblContractAmount;
    }

    /**
     * �iget���v��ԍρE�������n���ʑ���j<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u���v��ԍρE�������n���ʑ���v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * n��0�ȏ�5�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�w����́u���v��ԍρE�������n���ʑ���v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * this.�]�͌v�Z����Params<�M�p�ڋq>.get���v��ԍρE�������n���ʑ���iT+n�j<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B429BC013E
     */
    public double getDayRepayContractAmount(int l_intSpecifiedPoint)
    {
        //���v��ԍρE�������n���ʑ��
        double l_dblDayRepayContractAmount;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //���v��ԍρE�������n���ʑ��( T + 0 )���擾����B
                l_dblDayRepayContractAmount = this.calcResultMargin.getDayRepayContractAmount0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //���v��ԍρE�������n���ʑ��( T + 1 )���擾����B
                l_dblDayRepayContractAmount = this.calcResultMargin.getDayRepayContractAmount1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //���v��ԍρE�������n���ʑ��( T + 2 )���擾����B
                l_dblDayRepayContractAmount = this.calcResultMargin.getDayRepayContractAmount2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //���v��ԍρE�������n���ʑ��( T + 3 )���擾����B
                l_dblDayRepayContractAmount = this.calcResultMargin.getDayRepayContractAmount3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //���v��ԍρE�������n���ʑ��( T + 4 )���擾����B
                l_dblDayRepayContractAmount = this.calcResultMargin.getDayRepayContractAmount4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //���v��ԍρE�������n���ʑ��( T + 5 )���擾����B
                l_dblDayRepayContractAmount = this.calcResultMargin.getDayRepayContractAmount5();
                break;

            default :
                //�G���[���X���[
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getDayRepayContractAmount(int)");
        }

        //�擾�������v��ԍρE�������n���ʑ����ԋp����B
        return l_dblDayRepayContractAmount;
    }

    /**
     * �iget�����ό��ʑ��<���v��ԍρE�������n���ʑ���l��>�j<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�����ό��ʑ��<���v��ԍρE�������n���ʑ���l��>�v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * n��0�ȏ�5�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�w����́u�����ό��ʑ��<���v��ԍρE�������n���ʑ���l��>�v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * this.�]�͌v�Z����Params<�M�p�ڋq>.get�����ό��ʑ���iT+n�j<BR>
     *  + this.�]�͌v�Z����Params<�M�p�ڋq>.get���v��ԍρE�������n���ʑ���iT+n�j<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B429BC013E
     */
    public double getContractAmountDayRepay(int l_intSpecifiedPoint)
    {
        //�����ό��ʑ��<���v��ԍρE�������n���ʑ���l��>
        double l_dblContractAmountDayPepay;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //�����ό��ʑ��( T + 0 ) + ���v��ԍρE�������n���ʑ��( T + 0 )
                l_dblContractAmountDayPepay =
                    this.calcResultMargin.getContractAmount0()
                        + this.calcResultMargin.getDayRepayContractAmount0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //�����ό��ʑ��( T + 1 ) + ���v��ԍρE�������n���ʑ��( T + 1 )
                l_dblContractAmountDayPepay =
                    this.calcResultMargin.getContractAmount1()
                        + this.calcResultMargin.getDayRepayContractAmount1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //�����ό��ʑ��( T + 2 ) + ���v��ԍρE�������n���ʑ��( T + 2 )
                l_dblContractAmountDayPepay =
                    this.calcResultMargin.getContractAmount2()
                        + this.calcResultMargin.getDayRepayContractAmount2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //�����ό��ʑ��( T + 3 ) + ���v��ԍρE�������n���ʑ��( T + 3 )
                l_dblContractAmountDayPepay =
                    this.calcResultMargin.getContractAmount3()
                        + this.calcResultMargin.getDayRepayContractAmount3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //�����ό��ʑ��( T + 4 ) + ���v��ԍρE�������n���ʑ��( T + 4 )
                l_dblContractAmountDayPepay =
                    this.calcResultMargin.getContractAmount4()
                        + this.calcResultMargin.getDayRepayContractAmount4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //�����ό��ʑ��( T + 5 ) + ���v��ԍρE�������n���ʑ��( T + 5 )
                l_dblContractAmountDayPepay =
                    this.calcResultMargin.getContractAmount5()
                        + this.calcResultMargin.getDayRepayContractAmount5();
                break;

            default :
                //�G���[���X���[
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getContractAmountDayRepay(int)");
        }

        //�擾���������ό��ʑ��<���v��ԍρE�������n���ʑ���l��>��ԋp����B
        return l_dblContractAmountDayPepay;
    }

    /**
     * �iget�K�v�ۏ؋��j<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�K�v�ۏ؋��v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * n��0�ȏ�5�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�w����́u�K�v�ۏ؋��v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * this.�]�͌v�Z����Params<�M�p�ڋq>.get�K�v�ۏ؋��iT+n�j<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B429EB010F
     */
    public double getMarginDeposit(int l_intSpecifiedPoint)
    {
        //�K�v�ۏ؋�
        double l_dblMarginDeposit;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //�K�v�ۏ؋�( T + 0 )���擾����B
                l_dblMarginDeposit = this.calcResultMargin.getMarginDeposit0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //�K�v�ۏ؋�( T + 1 )���擾����B
                l_dblMarginDeposit = this.calcResultMargin.getMarginDeposit1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //�K�v�ۏ؋�( T + 2 )���擾����B
                l_dblMarginDeposit = this.calcResultMargin.getMarginDeposit2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //�K�v�ۏ؋�( T + 3 )���擾����B
                l_dblMarginDeposit = this.calcResultMargin.getMarginDeposit3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //�K�v�ۏ؋�( T + 4 )���擾����B
                l_dblMarginDeposit = this.calcResultMargin.getMarginDeposit4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //�K�v�ۏ؋�( T + 5 )���擾����B
                l_dblMarginDeposit = this.calcResultMargin.getMarginDeposit5();
                break;

            default :
                //�G���[���X���[
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getMarginDeposit(int)");
        }

        //�擾�����K�v�ۏ؋���ԋp����B
        return l_dblMarginDeposit;
    }

    /**
     * �iget�����K�v�ۏ؋��j<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�����K�v�ۏ؋��v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * n��0�ȏ�5�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�w����́u�����K�v�ۏ؋��v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * this.�]�͌v�Z����Params<�M�p�ڋq>.get�����K�v�ۏ؋��iT+n�j<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B42A050267
     */
    public double getCashMarginDeposit(int l_intSpecifiedPoint)
    {
        //�����K�v�ۏ؋�
        double l_dblCashMarginDeposit;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //�����K�v�ۏ؋�( T + 0 )���擾����B
                l_dblCashMarginDeposit = this.calcResultMargin.getCashMarginDeposit0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //�����K�v�ۏ؋�( T + 1 )���擾����B
                l_dblCashMarginDeposit = this.calcResultMargin.getCashMarginDeposit1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //�����K�v�ۏ؋�( T + 2 )���擾����B
                l_dblCashMarginDeposit = this.calcResultMargin.getCashMarginDeposit2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //�����K�v�ۏ؋�( T + 3 )���擾����B
                l_dblCashMarginDeposit = this.calcResultMargin.getCashMarginDeposit3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //�����K�v�ۏ؋�( T + 4 )���擾����B
                l_dblCashMarginDeposit = this.calcResultMargin.getCashMarginDeposit4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //�����K�v�ۏ؋�( T + 5 )���擾����B
                l_dblCashMarginDeposit = this.calcResultMargin.getCashMarginDeposit5();
                break;

            default :
                //�G���[���X���[
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getCashMarginDeposit(int)");
        }

        //�擾���������K�v�ۏ؋���ԋp����B
        return l_dblCashMarginDeposit;
    }

    /**
     * �iget�����ό��ʕ]�����v�j<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�����ό��ʕ]�����v�v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * n��0�ȏ�5�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�w����́u�����ό��ʕ]�����v�v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * this.�]�͌v�Z����Params<�M�p�ڋq>.get�����ό��ʕ]�����v�iT+n�j<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    public double getContractAssetProfitLoss(int l_intSpecifiedPoint)
    {
        //�����ό��ʕ]�����v
        double l_dblContractAssetProfitLoss;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //�����ό��ʕ]�����v( T + 0 )���擾����B
                l_dblContractAssetProfitLoss = this.calcResultMargin.getContractAssetProfitLoss();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //�����ό��ʕ]�����v( T + 1 )���擾����B
                l_dblContractAssetProfitLoss = this.calcResultMargin.getContractAssetProfitLoss1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //�����ό��ʕ]�����v( T + 2 )���擾����B
                l_dblContractAssetProfitLoss = this.calcResultMargin.getContractAssetProfitLoss2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //�����ό��ʕ]�����v( T + 3 )���擾����B
                l_dblContractAssetProfitLoss = this.calcResultMargin.getContractAssetProfitLoss3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //�����ό��ʕ]�����v( T + 4 )���擾����B
                l_dblContractAssetProfitLoss = this.calcResultMargin.getContractAssetProfitLoss4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //�����ό��ʕ]�����v( T + 5 )���擾����B
                l_dblContractAssetProfitLoss = this.calcResultMargin.getContractAssetProfitLoss5();
                break;

            default :
                //�G���[���X���[
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getCashMarginDeposit(int)");
        }

        //�擾���������ό��ʕ]�����v��ԋp����B
        return l_dblContractAssetProfitLoss;
    }
    
    /**
     * �iget����n���ʑ���j<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u����n���ʑ���v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * n��0�ȏ�3�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�w����́u����n���ʑ���v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * this.�]�͌v�Z����Params<�M�p�ڋq>.get����n���ʑ���iT+n�j<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B429CD0229
     */
    public double getUndeliContractAmount(int l_intSpecifiedPoint)
    {
        //����n���ʑ��
        double l_dblUndeliContractAmount;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //����n���ʑ��( T + 0 )���擾����B
                l_dblUndeliContractAmount = this.calcResultMargin.getUndeliContractAmount0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //����n���ʑ��( T + 1 )���擾����B
                l_dblUndeliContractAmount = this.calcResultMargin.getUndeliContractAmount1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //����n���ʑ��( T + 2 )���擾����B
                l_dblUndeliContractAmount = this.calcResultMargin.getUndeliContractAmount2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //����n���ʑ��( T + 3 )���擾����B
                l_dblUndeliContractAmount = this.calcResultMargin.getUndeliContractAmount3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //����n���ʑ����0.0��������
                l_dblUndeliContractAmount = 0.0;
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //����n���ʑ����0.0��������
                l_dblUndeliContractAmount = 0.0;
                break;

            default :
                //�G���[���X���[
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getUndeliContractAmount(int)");
        }

        //�擾��������n���ʑ����ԋp����B
        return l_dblUndeliContractAmount;
    }

    /**
     * �iget����n���ʕK�v�ۏ؋��j<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u����n���ʕK�v�ۏ؋��v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * n��0�ȏ�3�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�w����́u����n���ʕK�v�ۏ؋��v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * this.�]�͌v�Z����Params<�M�p�ڋq>.get����n���ʕK�v�ۏ؋��iT+n�j<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B429F401EA
     */
    public double getUndeliMarginDeposit(int l_intSpecifiedPoint)
    {
        //����n���ʕK�v�ۏ؋�
        double l_dblUndeliMarginDeposit;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //����n���ʕK�v�ۏ؋�( T + 0 )���擾����B
                l_dblUndeliMarginDeposit = this.calcResultMargin.getUndeliMarginDeposit0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //����n���ʕK�v�ۏ؋�( T + 1 )���擾����B
                l_dblUndeliMarginDeposit = this.calcResultMargin.getUndeliMarginDeposit1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //����n���ʕK�v�ۏ؋�( T + 2 )���擾����B
                l_dblUndeliMarginDeposit = this.calcResultMargin.getUndeliMarginDeposit2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //����n���ʕK�v�ۏ؋�( T + 3 )���擾����B
                l_dblUndeliMarginDeposit = this.calcResultMargin.getUndeliMarginDeposit3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //����n���ʕK�v�ۏ؋���0.0��������
                l_dblUndeliMarginDeposit = 0.0;
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //����n���ʕK�v�ۏ؋���0.0��������
                l_dblUndeliMarginDeposit = 0.0;
                break;

            default :
                //�G���[���X���[
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getUndeliMarginDeposit(int)");
        }

        //�擾��������n���ʕK�v�ۏ؋���ԋp����B
        return l_dblUndeliMarginDeposit;
    }

    /**
     * �iget����n���ʌ����K�v�ۏ؋��j<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u����n���ʌ����K�v�ۏ؋��v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * n��0�ȏ�3�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�w����́u����n���ʌ����K�v�ۏ؋��v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * this.�]�͌v�Z����Params<�M�p�ڋq>.get����n���ʌ����K�v�ۏ؋��iT+n�j<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B42A150054
     */
    public double getUndeliCashMarginDeposit(int l_intSpecifiedPoint)
    {
        //����n���ʌ����K�v�ۏ؋�
        double l_dblUndeliCashMarginDeposit;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //����n���ʌ����K�v�ۏ؋�( T + 0 )���擾����B
                l_dblUndeliCashMarginDeposit = this.calcResultMargin.getUndeliCashMarginDeposit0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //����n���ʌ����K�v�ۏ؋�( T + 1 )���擾����B
                l_dblUndeliCashMarginDeposit = this.calcResultMargin.getUndeliCashMarginDeposit1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //����n���ʌ����K�v�ۏ؋�( T + 2 )���擾����B
                l_dblUndeliCashMarginDeposit = this.calcResultMargin.getUndeliCashMarginDeposit2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //����n���ʌ����K�v�ۏ؋�( T + 3 )���擾����B
                l_dblUndeliCashMarginDeposit = this.calcResultMargin.getUndeliCashMarginDeposit3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //����n���ʌ����K�v�ۏ؋���0.0��������
                l_dblUndeliCashMarginDeposit = 0.0;
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //����n���ʌ����K�v�ۏ؋���0.0��������
                l_dblUndeliCashMarginDeposit = 0.0;
                break;

            default :
                //�G���[���X���[
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getUndeliCashMarginDeposit(int)");
        }

        //�擾��������n���ʌ����K�v�ۏ؋���ԋp����B
        return l_dblUndeliCashMarginDeposit;
    }

    /**
     * �iget����n���ʌ��ϑ��j<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u����n���ʌ��ϑ��v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * n��0�ȏ�3�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�w����́u����n���ʌ��ϑ��v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * this.�]�͌v�Z����Params<�M�p�ڋq>.get����n���ʌ��ϑ��iT+n�j<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B42A3200B2
     */
    public double getUndeliContractLoss(int l_intSpecifiedPoint)
    {
        //����n���ʌ��ϑ�
        double l_dblUndeliContractLoss;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //����n���ʌ��ϑ�( T + 0 )���擾����B
                l_dblUndeliContractLoss = this.calcResultMargin.getUndeliContractLoss0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //����n���ʌ��ϑ�( T + 1 )���擾����B
                l_dblUndeliContractLoss = this.calcResultMargin.getUndeliContractLoss1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //����n���ʌ��ϑ�( T + 2 )���擾����B
                l_dblUndeliContractLoss = this.calcResultMargin.getUndeliContractLoss2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //����n���ʌ��ϑ�( T + 3 )���擾����B
                l_dblUndeliContractLoss = this.calcResultMargin.getUndeliContractLoss3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //����n���ʌ��ϑ���0.0��������
                l_dblUndeliContractLoss = 0.0;
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //����n���ʌ��ϑ���0.0��������
                l_dblUndeliContractLoss = 0.0;
                break;

            default :
                //�G���[���X���[
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getUndeliContractLoss(int)");
        }

        //�擾��������n���ʌ��ϑ���ԋp����B
        return l_dblUndeliContractLoss;
    }

    /**
     * �iget����n���ʌ��ωv�j<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u����n���ʌ��ωv�v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * n��0�ȏ�3�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�w����́u����n���ʌ��ωv�v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * this.�]�͌v�Z����Params<�M�p�ڋq>.get����n���ʌ��ωv�iT+n�j<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    public double getUndeliContractProfit(int l_intSpecifiedPoint)
    {
        //����n���ʌ��ωv
        double l_dblUndeliContractProfit;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //����n���ʌ��ωv( T + 0 )���擾����B
                l_dblUndeliContractProfit = this.calcResultMargin.getUndeliContractProfit0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //����n���ʌ��ωv( T + 1 )���擾����B
                l_dblUndeliContractProfit = this.calcResultMargin.getUndeliContractProfit1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //����n���ʌ��ωv( T + 2 )���擾����B
                l_dblUndeliContractProfit = this.calcResultMargin.getUndeliContractProfit2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //����n���ʌ��ωv( T + 3 )���擾����B
                l_dblUndeliContractProfit = this.calcResultMargin.getUndeliContractProfit3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //����n���ʌ��ωv��0.0��������
                l_dblUndeliContractProfit = 0.0;
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //����n���ʌ��ωv��0.0��������
                l_dblUndeliContractProfit = 0.0;
                break;

            default :
                //�G���[���X���[
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getUndeliContractProfit(int)");
        }

        //�擾��������n���ʌ��ωv��ԋp����B
        return l_dblUndeliContractProfit;
    }


    /**
     * (get���ʏ��o��)<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u���ʏ��o��v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * <BR>
     * n��0�ȏ�5�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�w����́u���ʏ��o��v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * this.�]�͌v�Z����Params<�M�p�ڋq>.���ʏ��o��(T+n)<BR>
     * @@return double
     * @@roseuid 40B42A4403CF
     */
    public double getContractTotalCost(int l_intSpecifiedPoint)
    {
        //���ʏ��o��
        double l_dblContractTotalCost = 0;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //���ʏ��o��iT+0�j���擾����B
                l_dblContractTotalCost = this.calcResultMargin.getContractTotalCost();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //���ʏ��o��iT+1�j���擾����B
                l_dblContractTotalCost = this.calcResultMargin.getContractTotalCost1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //���ʏ��o��iT+2�j���擾����B
                l_dblContractTotalCost = this.calcResultMargin.getContractTotalCost2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //���ʏ��o��iT+3�j���擾����B
                l_dblContractTotalCost = this.calcResultMargin.getContractTotalCost3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //���ʏ��o��iT+4�j���擾����B
                l_dblContractTotalCost = this.calcResultMargin.getContractTotalCost4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //���ʏ��o��iT+5�j���擾����B
                l_dblContractTotalCost = this.calcResultMargin.getContractTotalCost5();
                break;

            default :
                //n��0�ȏ�5�ȉ��łȂ����A0��ԋp����B
                l_dblContractTotalCost = 0;
        }

        //�擾�������ʏ��o���ԋp����B
        return l_dblContractTotalCost;
    }

    /**
     * �icalc�����ۏ؋��j<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�����ۏ؋��v��ԋp����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B473DA0181
     */
    public double calcMarginAccountBalance(int l_intSpecifiedPoint)
    {
        //�p�����[�^.�w�����T+0��菬�����@@�܂��́@@T+5���傫����
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcMarginAccountBalance(int)");
        }

        //�����ۏ؋��i= �a����c�� - �������ϑ�� - ����������� - ���̑��S�����j���v�Z����B
        double l_dblMarginAccountBalance =
            this.getAccountBalance(l_intSpecifiedPoint)
                - this.getTodayExecutedAmount(l_intSpecifiedPoint)
                - this.getTodayUnexecutedAmount(l_intSpecifiedPoint)
                - this.getOtherRestraint(l_intSpecifiedPoint);

        //�v�Z���������ۏ؋���ԋp����B
        return l_dblMarginAccountBalance;
    }

    /**
     * �icalc�g�p�\�����j<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�g�p�\�����v��ԋp����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B70C5B00BA
     */
    public double calcActualAccountBalance(int l_intSpecifiedPoint)
    {
        //�p�����[�^.�w�����T+0��菬�����@@�܂��́@@T+5���傫����
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcActualAccountBalance(int)");
        }

        //�g�p�\�����i= �����ۏ؋� - �����K�v�ۏ؋� - ����n���ʌ����K�v�ۏ؋��j���v�Z����B
        double l_dblActualAccountBalance =
            this.calcMarginAccountBalance(l_intSpecifiedPoint)
                - this.getCashMarginDeposit(l_intSpecifiedPoint)
                - this.getUndeliCashMarginDeposit(l_intSpecifiedPoint);

        //this.�g�p�\������� �� null ���A����.�w��� == this.�g�p�\�������.�w�� �̏ꍇ
        if (this.actualBalanceInfo != null
            && this.actualBalanceInfo.specifiedPoint == l_intSpecifiedPoint)
        {
            /*
             * �g�p�\����(n)�@@=MIN(�g�p�\�������.�������ϔ��t�\�z, �g�p�\����(n))
             */
            l_dblActualAccountBalance =
                Math.min(this.actualBalanceInfo.settlementBuyAmount, l_dblActualAccountBalance);
        }

        //�v�Z�����g�p�\������ԋp����B
        return l_dblActualAccountBalance;
    }

    /**
     * �icalc���o�\�����j<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u���o�\�����v��ԋp����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B6A65401D3
     */
    public double calcActualPaymentBalance(int l_intSpecifiedPoint)
    {
        //�p�����[�^.�w�����T+0��菬�����@@�܂��́@@T+5���傫����
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcActualPaymentBalance(int)");
        }

        //���v��S����
        double l_dblDayTradeRestraint = this.getDayTradeRestraint(l_intSpecifiedPoint);

        //�w�����T+0�̎�
        if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_0)
        {
            //���v��S�����ɓ��v��S����(T+0)�Ɠ��ʗ��֋����т̑傫��������
            l_dblDayTradeRestraint =
                Math.max(
                    this.getDayTradeRestraint(l_intSpecifiedPoint),
                    this.calcCondition.getSpecialDebitAmount());
        }
        //����ȊO
        else
        {
            //���v��S�����ɓ��v��S����(�w���)����
            l_dblDayTradeRestraint = this.getDayTradeRestraint(l_intSpecifiedPoint);
        }

        //���o�\�����i= �g�p�\���� - ���v��S�����j���v�Z����B
        double l_dblActualPaymentBalance =
            this.calcActualAccountBalance(l_intSpecifiedPoint) - l_dblDayTradeRestraint;

        //�v�Z�������o�\������ԋp����B
        return l_dblActualPaymentBalance;
    }

    /**
     * �icalc�a��������]�́j<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�a��������]�́v��ԋp����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    public double calcAccountBalanceDemandPower(int l_intSpecifiedPoint)
    {
        //�p�����[�^.�w�����T+0��菬�����@@�܂��́@@T+5���傫����
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcActualPaymentBalance(int)");
        }

        //�a��������]�́i= ���o�\���� + ����n���ʌ����K�v�ۏ؋��j���v�Z����B
        double l_dblAccountBalanceDemandPower = this.calcActualPaymentBalance(l_intSpecifiedPoint)
            + this.getUndeliCashMarginDeposit(l_intSpecifiedPoint);

        //�v�Z�����a��������]�͂�ԋp����B
        return l_dblAccountBalanceDemandPower;
    }
    
    /**
     * �icalc�����ۏ؋��j<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�����ۏ؋��v��ԋp����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B6A57202ED
     */
    public double calcPaidMarginDeposit(int l_intSpecifiedPoint)
    {
        //�p�����[�^.�w�����T+0��菬�����@@�܂��́@@T+5���傫����
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcPaidMarginDeposit(int)");
        }

        //�����ۏ؋��i= �����ۏ؋� + ��p�،��]���z�j���v�Z����B
        double l_dblPaidMarginDeposit =
            this.calcMarginAccountBalance(l_intSpecifiedPoint)
                + this.getSubstituteSecurityAsset(l_intSpecifiedPoint);

        //�v�Z���������ۏ؋���ԋp����B
        return l_dblPaidMarginDeposit;
    }

    /**
     * �icalc����ۏ؋��j<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u����ۏ؋��v��ԋp����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B6A5810156
     */
    public double calcReceiptMarginDeposit(int l_intSpecifiedPoint)
    {
        //�p�����[�^.�w�����T+0��菬�����@@�܂��́@@T+5���傫����
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcReceiptMarginDeposit(int)");
        }

        //����ۏ؋�(n)
        //�i= �����ۏ؋�(n)�@@-�@@ Abs(Min(�����ό��ʕ]�����v(n), 0)) - ���ʏ��o��(n) - ����n���ʌ��ϑ�(n)�j���v�Z����B
        double l_dblReceiptMarginDeposit =
            this.calcPaidMarginDeposit(l_intSpecifiedPoint)
                - Math.abs(Math.min(this.getContractAssetProfitLoss(l_intSpecifiedPoint), 0))
                - getContractTotalCost(l_intSpecifiedPoint)
                - getUndeliContractLoss(l_intSpecifiedPoint);

        //�v�Z��������ۏ؋���ԋp����B
        return l_dblReceiptMarginDeposit;
    }

    /**
     * �icalc�ۏ؋��a�����j<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́u�ۏ؋��a�����v���v�Z����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B6A587003D
     */
    public double calcMarginDepositRate(int l_intSpecifiedPoint)
    {
        //�p�����[�^.�w�����T+0��菬�����@@�܂��́@@T+5���傫����
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcMarginDepositRate(int)");
        }
    
        //�ۏ؋��a����
        double l_dblMarginDepositRate;
    
        //�����ό��ʑ��<���v��ԍρE�������n���ʑ���l��>(BigDecimal�^)
        BigDecimal l_bdContractAmount =
            new BigDecimal(this.getContractAmountDayRepay(l_intSpecifiedPoint));
    
        //�����ό��ʑ��<���v��ԍρE�������n���ʑ���l��>��0���傫����
        if (l_bdContractAmount.compareTo(new BigDecimal(0.0)) > 0)
        {
            //����ۏ؋�(BigDecimal�^)
            BigDecimal l_bdReceiptMarginDeposit =
                new BigDecimal(this.calcReceiptMarginDeposit(l_intSpecifiedPoint));
    
            //�ۏ؋��a�����i= ����ۏ؋�(n) / �����ό��ʑ��(n) �~ 100�j���v�Z����i�����_�ȉ�5���ڂŐ؂�̂āj
            BigDecimal l_bdMarginDepositRate =
                l_bdReceiptMarginDeposit.multiply(new BigDecimal(100.0)).divide(
                    l_bdContractAmount,
                    4,
                    BigDecimal.ROUND_FLOOR);
    
            //�ۏ؋��a�����Ɍv�Z���ʂ�������B
            l_dblMarginDepositRate = l_bdMarginDepositRate.doubleValue();
    
        }
        else
            //����ȊO
            {
            //�ۏ؋��a�����ɐ��̖������������B
            l_dblMarginDepositRate = Double.POSITIVE_INFINITY;
        }
    
        //�v�Z�����ۏ؋��a������ԋp����B
        return l_dblMarginDepositRate;
    }

    /**
     * �icalc�ۏ؋��]�́j<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́u�ۏ؋��]�́v��ԋp����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B6A59502AE
     */
    public double calcMarginPower(int l_intSpecifiedPoint)
    {
        //�p�����[�^.�w�����T+0��菬�����@@�܂��́@@T+5���傫����
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcMarginPower(int)");
        }
    
        //�ۏ؋��]��
        double l_dblMarginPower;
    
        //�����ό��ʑ��<���v��ԍρE�������n���ʑ���l��>(BigDecimal�^)
        BigDecimal l_bdContractAmount =
            new BigDecimal(this.getContractAmountDayRepay(l_intSpecifiedPoint));
    
        //�����ό��ʑ��<���v��ԍρE�������n���ʑ���l��>��0���傫����
        if (l_bdContractAmount.compareTo(new BigDecimal(0.0)) > 0)
        {
            //�����ۏ؋�
            double l_dblPaidMarginDeposit = this.calcPaidMarginDeposit(l_intSpecifiedPoint);
            BigDecimal l_bdPaidMarginDeposit = new BigDecimal(l_dblPaidMarginDeposit);
            //�Œ�K�v�ۏ؋�
            double l_dblMinMarginDeposit = this.calcCondition.getMinMarginDeposit();
            BigDecimal l_bdMinMarginDeposit = new BigDecimal(l_dblMinMarginDeposit);
            //����ۏ؋�
            double l_dblReceiptMarginDeposit = this.calcReceiptMarginDeposit(l_intSpecifiedPoint);
            BigDecimal l_bdReceiptMarginDeposit = new BigDecimal(l_dblReceiptMarginDeposit);
            //�@@��Œ�K�v�ۏ؋�
            double l_dblLegalMinMarginDeposit = this.calcCondition.getLegalMinMarginDeposit();
            BigDecimal l_bdLegalMinMarginDeposit = new BigDecimal(l_dblLegalMinMarginDeposit);
    
            //�����ۏ؋����Œ�K�v�ۏ؋��ȏ�@@���@@����ۏ؋����@@��Œ�K�v�ۏ؋��ȏ�̎�
            if (l_bdPaidMarginDeposit.compareTo(l_bdMinMarginDeposit) >= 0
                && l_bdReceiptMarginDeposit.compareTo(l_bdLegalMinMarginDeposit) >= 0)
            {
                //�ۏ؋��]�́i=����ۏ؋� - �K�v�ۏ؋��j���v�Z����
                l_dblMarginPower =
                    l_dblReceiptMarginDeposit - this.getMarginDeposit(l_intSpecifiedPoint);
            }
            //����ȊO
            else
            {
                //�ۏ؋��]�͇@@�i=�����ۏ؋� - �Œ�K�v�ۏ؋��j���v�Z����
                double l_dblMarginPower1 = l_dblPaidMarginDeposit - l_dblMinMarginDeposit;
    
                //�ۏ؋��]�͇A�i=����ۏ؋�(n) - Max(�K�v�ۏ؋�(n), �@@��Œ�K�v�ۏ؋�)�j���v�Z����B
                double l_dblMarginPower2 =
                    l_dblReceiptMarginDeposit
                        - Math.max(
                            this.getMarginDeposit(l_intSpecifiedPoint),
                            l_dblLegalMinMarginDeposit);
    
                //�ۏ؋��]�͂ɕۏ؋��]�͇@@�ƕۏ؋��]�͇A�̏�����������
                l_dblMarginPower = Math.min(l_dblMarginPower1, l_dblMarginPower2);
            }
        }
        //����ȊO
        else
        {
            l_dblMarginPower = this.calcReceiptMarginDeposit(l_intSpecifiedPoint);
        }
    
        //�v�Z�����ۏ؋��]�͂�ԋp����B
        return l_dblMarginPower;
    }

    /**
    * �icalc�Ǐؗ]�́j<BR>
    * 
    * �����Ŏw�肳�ꂽ�w���(=n)�́u�Ǐؗ]�́v���v�Z����B<BR>
    * <BR>
    * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
    * @@param l_intSpecifiedPoint
    * @@return double
    * @@roseuid 40B6A5A50231
    */
    public double calcMarginCallPower(int l_intSpecifiedPoint)
    {
        //�p�����[�^.�w�����T+0��菬�����@@�܂��́@@T+5���傫����
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcMarginCallPower(int)");
        }
    
        //�Ǐؗ]��
        double l_dblMarginCallPower;
    
        //�����ό��ʑ��<���v��ԍρE�������n���ʑ���l��>(BigDecimal�^)
        BigDecimal l_bdContractAmount =
            new BigDecimal(this.getContractAmountDayRepay(l_intSpecifiedPoint));
    
        //�����ό��ʑ��<���v��ԍρE�������n���ʑ���l��>��0���傫����
        if (l_bdContractAmount.compareTo(new BigDecimal(0.0)) > 0)
        {
            /*
             * �Ǐؗ]��(n) = Min(�����ۏ؋�(n) - �Œ�K�v�ۏ؋�, �����ۏ؋�(n) - �ǏؕK�v�ۏ؋�(n))
             */
    
            //�Ǐؗ]�͇@@�i=�����ۏ؋�(n) - �Œ�K�v�ۏ؋��j���v�Z����
            double l_dblMarginCallPower1 =
                this.calcPaidMarginDeposit(l_intSpecifiedPoint)
                    - this.calcCondition.getMinMarginDeposit();
    
            //�Ǐؗ]�͇A�i=�����ۏ؋�(n) - �ǏؕK�v�ۏ؋�(n)�j���v�Z����
            double l_dblMarginCallPower2 =
                this.calcPaidMarginDeposit(l_intSpecifiedPoint)
                    - this.calcMarginCallDeposit(l_intSpecifiedPoint);
    
            //�Ǐؗ]�͂ɒǏؗ]�͇@@�ƒǏؗ]�͇A�̏���������������
            l_dblMarginCallPower = Math.min(l_dblMarginCallPower1, l_dblMarginCallPower2);
        }
        //����ȊO
        else
        {
            //�����ۏ؋�(n) - �ǏؕK�v�ۏ؋�(n)
            l_dblMarginCallPower =
                this.calcPaidMarginDeposit(l_intSpecifiedPoint)
                    - this.calcMarginCallDeposit(l_intSpecifiedPoint);
        }
    
        //�v�Z�����Ǐؗ]�͂�ԋp����B
        return l_dblMarginCallPower;
    }

    /**
    * �icalc�ۏ؋����o�]�́j<BR>
    * 
    * �����Ŏw�肳�ꂽ�w���(=n)�́u�M�p�����]�́v���v�Z����B<BR>
    * <BR>
    * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
    * @@param l_intSpecifiedPoint
    * @@return double
    * @@roseuid 40B6A5AC0398
    */
    public double calcMarginDrawPower(int l_intSpecifiedPoint)
    {
        //�p�����[�^.�w�����T+0��菬�����@@�܂��́@@T+5���傫����
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcMarginDrawPower(int)");
        }
    
        //�ۏ؋����o�]��(n)
        double l_dblMarginDrawPower = 0;
    
        //���ʑ���i= �����ό��ʑ��<���v��ԍρE�������n���ʑ���l��>(n) + ����n���ʑ��(n)�j���v�Z����
        double l_dblContractAmount =
            this.getContractAmountDayRepay(l_intSpecifiedPoint)
                + this.getUndeliContractAmount(l_intSpecifiedPoint);

        //���ʑ��(BigDecimal�^)
        BigDecimal l_bdContractAmount = new BigDecimal(l_dblContractAmount);

        //���ʑ����0���傫����
        if (l_bdContractAmount.compareTo(new BigDecimal(0.0)) > 0)
        {
            /* 
             * �ۏ؋����o�]�� = Min(�����ۏ؋�(n) - �Œ�K�v�ۏ؋�, �����ۏ؋�(n) - �ۏ؋����o�S����(n)) 
             */
            //�ۏ؋����o�]�͇@@�i=�����ۏ؋�(n) - �Œ�K�v�ۏ؋��j���v�Z����B
            double l_dblMarginDrawPower1 =
                this.calcPaidMarginDeposit(l_intSpecifiedPoint) - this.calcCondition.getMinMarginDeposit();
            
            //�ۏ؋����o�]�͇A�i=�����ۏ؋�(n) - �ۏ؋����o�S����(n)�j���v�Z����B
            double l_dblMarginDrawPower2 =
                this.calcPaidMarginDeposit(l_intSpecifiedPoint)
                    - this.calcMarginDrawDeposit(l_intSpecifiedPoint);
                    
            //�ۏ؋����o�]�͂ɕۏ؋����o�]�͇@@�ƕۏ؋����o�]�͇A�̏���������������
            l_dblMarginDrawPower = Math.min(l_dblMarginDrawPower1, l_dblMarginDrawPower2);
        }
        //���ʑ�������݂��Ȃ���
        else
        {
            //�ۏ؋����o�]��(n) = ����ۏ؋�(n)
            l_dblMarginDrawPower =  this.calcReceiptMarginDeposit(l_intSpecifiedPoint);
        }
    
        //�v�Z�����ۏ؋����o�]�͂�ԋp����
        return l_dblMarginDrawPower;
    }

    /**
     * �icalc�ǏؕK�v�ۏ؋��j<BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́u�ǏؕK�v�ۏ؋��v���v�Z����B
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB 
     * 
     * @@param l_intSpecifiedPoint<BR>
     * @@return double
     */
    public double calcMarginCallDeposit(int l_intSpecifiedPoint)
    {
        //�p�����[�^.�w�����T+0��菬�����@@�܂��́@@T+5���傫����
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcMarginCallDeposit(int)");
        }
    
        //�ǏؕK�v�ۏ؋�(n)
        double l_dblMarginCallDeposit = 0.0;
        //�����ό��ʑ��<���v��ԍρE�������n���ʑ���l��>(n)
        double l_dblContAmt = this.getContractAmountDayRepay(l_intSpecifiedPoint);
    
        //�����ό��ʑ��<���v��ԍρE�������n���ʑ���l��>(n) > 0�̏ꍇ        
        if (l_dblContAmt > 0)
        {
            /*
             *�ǏؕK�v�ۏ؋�(n) = 
             *  �M�p�S����(n) + 
             *           Max(�@@��Œ�K�v�ۏ؋�, �����ό��ʑ��<���v��ԍρE�������n���ʑ���l��>(n) �~�@@�ۏ؋��ێ���)
             */
            l_dblMarginCallDeposit =
                this.calcMarginRestraint(l_intSpecifiedPoint)
                    + Math.max(
                        this.calcCondition.getLegalMinMarginDeposit(),
                        l_dblContAmt * this.calcCondition.getMarginMentenanceRate() / 100);
    
            BigDecimal l_bdMarginCallDeposit = new BigDecimal(Double.toString(l_dblMarginCallDeposit));
    
            l_dblMarginCallDeposit =
                l_bdMarginCallDeposit.setScale(0, BigDecimal.ROUND_DOWN).doubleValue();
    
        }
        //�ȊO(�����ό��ʑ��<���v��ԍρE�������n���ʑ���l��>(n) = 0)�̏ꍇ
        else
        {
            //�ǏؕK�v�ۏ؋�(n) = �M�p�S����(n)
            l_dblMarginCallDeposit = this.calcMarginRestraint(l_intSpecifiedPoint);
        }
    
        return l_dblMarginCallDeposit;
    }

    /**
     * �icalc�ۏ؋����o�S�����j<BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́u�ۏ؋����o�S�����v���v�Z����B
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB 
     * 
     * @@param l_intSpecifiedPoint<BR>
     * @@return double
     */
    public double calcMarginDrawDeposit(int l_intSpecifiedPoint)
    {
        //�p�����[�^.�w�����T+0��菬�����@@�܂��́@@T+5���傫����
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcMarginDrawDeposit(int)");
        }
    
        //�ۏ؋����o�S����(n)
        double l_dblMarginDeposit = 0;
    
        //���ʑ���i= �����ό��ʑ��<���v��ԍρE�������n���ʑ���l��>(n) + ����n���ʑ��(n)�j���v�Z����
        double l_dblContractAmount =
            this.getContractAmountDayRepay(l_intSpecifiedPoint)
                + this.getUndeliContractAmount(l_intSpecifiedPoint);

        //���ʑ��(BigDecimal�^)
        BigDecimal l_bdContractAmount = new BigDecimal(l_dblContractAmount);

        //���ʑ����0���傫����
        if (l_bdContractAmount.compareTo(new BigDecimal(0.0)) > 0)
        {
            //�ۏ؋����o�S���� = �M�p�S����(n) + Max(�K�v�ۏ؋�(n) + ����n���ʕK�v�ۏ؋�(n), �@@��Œ�K�v�ۏ؋�) 
            l_dblMarginDeposit =
                this.calcMarginRestraint(l_intSpecifiedPoint)
                    + Math.max(
                        this.getMarginDeposit(l_intSpecifiedPoint)
                            + this.getUndeliMarginDeposit(l_intSpecifiedPoint),
                        this.calcCondition.getLegalMinMarginDeposit());
        }
        //���ʑ�������݂��Ȃ���
        else
        {
            //�ۏ؋����o�S����(n) = �M�p�S����(n)
            l_dblMarginDeposit =  this.calcMarginRestraint(l_intSpecifiedPoint);
        }
    
        //�v�Z�����ۏ؋����o�]�͂�ԋp����
        return l_dblMarginDeposit;
    }

    /**
     * �icalc�M�p�S�����j<BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́u�M�p�S�����v���v�Z����
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB  
     * 
     * @@param l_intSpecifiedPoint<BR>
     * @@return double
     */
    public double calcMarginRestraint(int l_intSpecifiedPoint)
    {
        //�p�����[�^.�w�����T+0��菬�����@@�܂��́@@T+5���傫����
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcMarginRestraint(int)");
        }
    
        //�M�p�S����(n) = 
        //    ���ʏ��o��(n) + Abs(Min(�����ό��ʕ]�����v(n), 0)) + ����n���ʌ��ϑ�(n)
        double l_dblContLoss = Math.abs(Math.min(this.getContractAssetProfitLoss(l_intSpecifiedPoint), 0));

        double l_dblMarginRest =
            this.getContractTotalCost(l_intSpecifiedPoint)
                + l_dblContLoss
                + this.getUndeliContractLoss(l_intSpecifiedPoint);
    
        return l_dblMarginRest;
    }

    /**
     * �icalc�������t�\�z�j<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�������t�\�z�v��ԋp����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B6A5B602DD
     */
    public double calcEquityTradingPower(int l_intSpecifiedPoint)
    {
        //�p�����[�^.�w�����T+0��菬�����@@�܂��́@@T+5���傫����
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcEquityTradingPower(int)");
        }
    
        //�������t�\�z
        double l_dblEquityTradingPower;
    
        //���ʑ���i= �����ό��ʑ��<���v��ԍρE�������n���ʑ���l��>(n) + ����n���ʑ��(n)�j���v�Z����
        double l_dblContractAmount =
            this.getContractAmountDayRepay(l_intSpecifiedPoint)
                + this.getUndeliContractAmount(l_intSpecifiedPoint);
    
        //���ʑ��(BigDecimal�^)
        BigDecimal l_bdContractAmount = new BigDecimal(l_dblContractAmount);
    
        //���ʑ����0���傫����
        if (l_bdContractAmount.compareTo(new BigDecimal(0.0)) > 0)
        {
            //�ۏ؋����o�]�͂��v�Z����B
            double l_dblMarginDrawPower = this.calcMarginDrawPower(l_intSpecifiedPoint);
            //�ۏ؋����o�]��(BigDecimal�^)
            BigDecimal l_bdMarginDrawPower = new BigDecimal(l_dblMarginDrawPower);
    
            //�ۏ؋����o�]�͂��A0�ȏ�@@���@@�p�����[�^.�w������A�]�͌v�Z���<�������t/�M�p����>�ȏ�̎�
            if (l_bdMarginDrawPower.compareTo(new BigDecimal(0.0)) >= 0
                && l_intSpecifiedPoint >= this.calcCondition.getEquityBasePoint())
            {
    
                //�]�͌v�Z��p�|��(BigDecimal�^)
                BigDecimal l_bdSubstituteRate =
                    new BigDecimal(this.calcCondition.getSubstituteRate());
    
                //�ۏ؋����o�]�� / (1 - (�]�͌v�Z��p�|�� / 100) ���v�Z����
                l_bdMarginDrawPower =
                    l_bdMarginDrawPower.divide(
                        BigDecimal.valueOf(1).subtract(
                            l_bdSubstituteRate.divide(
                                BigDecimal.valueOf(100),
                                10,
                                BigDecimal.ROUND_HALF_EVEN)),
                        10,
                        BigDecimal.ROUND_HALF_EVEN);
    
                //�������t�\�z�ɁA�ۏ؋����o�]�͂Ǝg�p�\����(n)�̏�����������
                l_dblEquityTradingPower =
                    Math.min(
                        l_bdMarginDrawPower.doubleValue(),
                        this.calcActualAccountBalance(l_intSpecifiedPoint));
    
                //�����\�z�Ɍv�Z���ʂ����i�����_�ȉ��؎̂āj
                l_dblEquityTradingPower = Math.floor(l_dblEquityTradingPower);
            }
            //����ȊO
            else
            {
                //�������t�\�z�ɁA�ۏ؋����o�]�͂Ǝg�p�\����(n)�̏�����������
                l_dblEquityTradingPower =
                    Math.min(
                        l_dblMarginDrawPower,
                        this.calcActualAccountBalance(l_intSpecifiedPoint));
            }
        }
        //����ȊO
        else
        {
            //�������t�\�z�ɁA�g�p�\����(n)����
            l_dblEquityTradingPower = this.calcActualAccountBalance(l_intSpecifiedPoint);
        }
    
        //�v�Z�����������t�\�z��ԋp����B 
        return l_dblEquityTradingPower;
    }

    /**
     * �icalc�������t�\�z<���v��S�����l��><BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�������t�\�z<���v��S�����l��>�v��ԋp����B<B
     * R>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B6A5C0009B
     */
    public double calcEquityTradingPowerIncDayTrade(int l_intSpecifiedPoint)
    {
        //�p�����[�^.�w�����T+0��菬�����@@�܂��́@@T+5���傫����
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcEquityTradingPowerIncDayTrade(int)");
        }

        //�������t�\�z
        double l_dblEquityTradingPower;

        //���ʑ���i= �����ό��ʑ��<���v��ԍρE�������n���ʑ���l��>(n) + ����n���ʑ��(n)�j���v�Z����
        double l_dblContractAmount =
            this.getContractAmountDayRepay(l_intSpecifiedPoint)
                + this.getUndeliContractAmount(l_intSpecifiedPoint);

        //���ʑ��(BigDecimal�^)
        BigDecimal l_bdContractAmount = new BigDecimal(l_dblContractAmount);

        //���ʑ����0���傫����
        if (l_bdContractAmount.compareTo(new BigDecimal(0.0)) > 0)
        {
            //�ۏ؋����o�]�͂��v�Z����B
            double l_dblMarginDrawPower = this.calcMarginDrawPower(l_intSpecifiedPoint);
            //�ۏ؋����o�]��(BigDecimal�^)
            BigDecimal l_bdMarginDrawPower = new BigDecimal(l_dblMarginDrawPower);

            //�ۏ؋����o�]�͂��A0�ȏ�@@���@@�p�����[�^.�w������A�]�͌v�Z���<�������t/�M�p����>�ȏ�̎�
            if (l_bdMarginDrawPower.compareTo(new BigDecimal(0.0)) >= 0
                && l_intSpecifiedPoint >= this.calcCondition.getEquityBasePoint())
            {

                //�]�͌v�Z��p�|��(BigDecimal�^)
                BigDecimal l_bdSubstituteRate =
                    new BigDecimal(this.calcCondition.getSubstituteRate());

                //�ۏ؋����o�]�� / (1 - (�]�͌v�Z��p�|�� / 100) ) ���v�Z����
                l_bdMarginDrawPower =
                    l_bdMarginDrawPower.divide(
                        BigDecimal.valueOf(1).subtract(
                            l_bdSubstituteRate.divide(
                                BigDecimal.valueOf(100),
                                10,
                                BigDecimal.ROUND_HALF_EVEN)),
                        10,
                        BigDecimal.ROUND_HALF_EVEN);

                //�������t�\�z�ɁA�ۏ؋����o�]�͂ƈ��o�\����(n)�̏�����������
                l_dblEquityTradingPower =
                    Math.min(
                        l_bdMarginDrawPower.doubleValue(),
                        this.calcActualPaymentBalance(l_intSpecifiedPoint));

                //�����\�z�Ɍv�Z���ʂ����i�����_�ȉ��؎̂āj
                l_dblEquityTradingPower = Math.floor(l_dblEquityTradingPower);
            }
            //����ȊO
            else
            {
                //�������t�\�z�ɁA�ۏ؋����o�]�͂ƈ��o�\����(n)�̏�����������
                l_dblEquityTradingPower =
                    Math.min(
                        l_dblMarginDrawPower,
                        this.calcActualPaymentBalance(l_intSpecifiedPoint));
            }
        }
        //����ȊO
        else
        {
            //�������t�\�z�ɁA���o�\����(n)����
            l_dblEquityTradingPower = this.calcActualPaymentBalance(l_intSpecifiedPoint);
        }

        //�v�Z�����������t�\�z��ԋp����B
        return l_dblEquityTradingPower;
    }

    /**
     * �icalc�M�p�V�K���\�z�j<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́u�M�p�V�K���\�z�v���v�Z����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B6A59E0108
     */
    public double calcMarginTradingPower(int l_intSpecifiedPoint)
    {
        //�p�����[�^.�w�����T+0��菬�����@@�܂��́@@T+5���傫����
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcMarginTradingPower(int)");
        }

        //�M�p�V�K���\�z
        double l_dblMarginTradingPower;

        //�����ۏ؋�
        BigDecimal l_bdPaidMarginDeposit =
            new BigDecimal(this.calcPaidMarginDeposit(l_intSpecifiedPoint));
        //�Œ�K�v�ۏ؋�
        BigDecimal l_bdMinMarginDeposit = new BigDecimal(this.calcCondition.getMinMarginDeposit());
        //����ۏ؋�
        BigDecimal l_bdReceiptMarginDeposit =
            new BigDecimal(this.calcReceiptMarginDeposit(l_intSpecifiedPoint));
        //�@@��Œ�K�v�ۏ؋�
        BigDecimal l_bdLegalMinMarginDeposit =
            new BigDecimal(this.calcCondition.getLegalMinMarginDeposit());

        //�ۏ؋��]��
        double l_dblMarginPower = this.calcMarginPower(l_intSpecifiedPoint);
        BigDecimal l_bdMarginPower = new BigDecimal(l_dblMarginPower);

        //�����ۏ؋����Œ�K�v�ۏ؋��ȏ�@@���@@����ۏ؋����@@��Œ�K�v�ۏ؋��ȏ�̎�
        if (l_bdPaidMarginDeposit.compareTo(l_bdMinMarginDeposit) >= 0
            && l_bdReceiptMarginDeposit.compareTo(l_bdLegalMinMarginDeposit) >= 0)
        {
            //�ۏ؋��]�͂�0���傫����
            if (l_bdMarginPower.compareTo(new BigDecimal(0.0)) > 0)
            {
                //�M�p�V�K���\�z(BigDecimal�^)
                BigDecimal l_bdMarginTradingPower;
                //�ۏ؋���
                BigDecimal l_bdMarginDepositRate =
                    new BigDecimal(this.calcCondition.getMarginDepositRate());

                //�����ۏ؋�����0���傫����
                if (this.calcCondition.getCashMarginDepositRate() > 0)
                {
                    //�M�p�V�K���\�z�@@�i = �ۏ؋��]�� / �ۏ؋��� / 100�j���v�Z����B
                    BigDecimal l_bdMarginTradingPower1 =
                        l_bdMarginPower.divide(
                            l_bdMarginDepositRate.divide(
                                new BigDecimal(100.0),
                                10,
                                BigDecimal.ROUND_HALF_EVEN),
                            10,
                            BigDecimal.ROUND_HALF_EVEN);

                    //�a��������]��
                    BigDecimal l_bdActualPaymentBalance =
                        new BigDecimal(
                            this.calcAccountBalanceDemandPower(l_intSpecifiedPoint));

                    //�����ۏ؋���
                    BigDecimal l_bdCashMarginDepositRate =
                        new BigDecimal(this.calcCondition.getCashMarginDepositRate());

                    //�M�p�V�K���\�z�A�i = �a��������]�� / �����ۏ؋��� / 100�j
                    BigDecimal l_bdMarginTradingPower2 =
                        l_bdActualPaymentBalance.divide(
                            l_bdCashMarginDepositRate.divide(
                                new BigDecimal(100.0),
                                10,
                                BigDecimal.ROUND_HALF_EVEN),
                            10,
                            BigDecimal.ROUND_HALF_EVEN);

                    //�M�p�V�K���\�z�ɐM�p�V�K���\�z�@@�ƐM�p�V�K���\�z�A�̏�����������
                    l_bdMarginTradingPower = l_bdMarginTradingPower1.min(l_bdMarginTradingPower2);

                    //�M�p�V�K���\�z�ɑ������i�����_�ȉ��؎̂āj
                    l_dblMarginTradingPower = Math.floor(l_bdMarginTradingPower.doubleValue());
                }
                //����ȊO
                else
                {
                    //�M�p�V�K���\�z�i = �ۏ؋��]�� / �ۏ؋��� / 100�j���v�Z����B
                    l_bdMarginTradingPower =
                        l_bdMarginPower.divide(
                            l_bdMarginDepositRate.divide(
                                new BigDecimal(100.0),
                                10,
                                BigDecimal.ROUND_HALF_EVEN),
                            10,
                            BigDecimal.ROUND_HALF_EVEN);

                    //�M�p�V�K���\�z�ɑ������i�����_�ȉ��؎̂āj
                    l_dblMarginTradingPower = Math.floor(l_bdMarginTradingPower.doubleValue());
                }
            }
            //����ȊO
            else
            {
                //�M�p�V�K���\�z�ɕۏ؋��]�͂���
                l_dblMarginTradingPower = l_dblMarginPower;
            }
        }
        //����ȊO
        else
        {
            //�M�p�V�K���\�z�ɕۏ؋��]�͂�0�̏�����������
            l_dblMarginTradingPower = Math.min(l_dblMarginPower, 0.0);
        }

        //�v�Z�����M�p�V�K���\�z��ԋp����
        return l_dblMarginTradingPower;
    }

    /**
     * �icalc�M�p�����\�z�j<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́u�M�p�����]�́v���v�Z����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B6A5EF00BA
     */
    public double calcActualReceiptTradingPower(int l_intSpecifiedPoint)
    {
        //�p�����[�^.�w�����T+0��菬�����@@�܂��́@@T+5���傫����
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcActualReceiptTradingPower(int)");
        }

        // �g�M�p�����]�͂̒Ǐؗ]�̓`�F�b�N�h���擾����
        String l_strInstBranCalcCondition = 
            this.calcCondition.getInstBranCalcCondition(
                WEB3TPCalcCondition.ACTUALRECEIPT_MARGINCALLPOWER_CHECK);

        // �Ǐؗ]��(n)���v�Z����
        double l_dblMarginCallPower = this.calcMarginCallPower(l_intSpecifiedPoint);

        //�M�p�����\�z
        double l_dblActualReceiptTradingPower;

        //�����ό��ʑ��<���v��ԍρE�������n���ʑ���l��>(BigDecimal�^)
        BigDecimal l_bdContractAmount =
            new BigDecimal(this.getContractAmountDayRepay(l_intSpecifiedPoint));

        // �Ǐؗ]�� (n) < 0�ꍇ ���� �M�p�����]�͂̒Ǐؗ]�̓`�F�b�N = 1
        if (l_dblMarginCallPower < 0 && 
            WEB3TPActualReceiptMargincallPowerCheckDef.EXECUTE.equals(l_strInstBranCalcCondition))
        {
            l_dblActualReceiptTradingPower = 0;
        }
        else if (l_bdContractAmount.compareTo(new BigDecimal(0.0)) > 0)
        {
        //�����ό��ʑ��<���v��ԍρE�������n���ʑ���l��>��0���傫����

            /*
             * �M�p�����\�z�@@���v�Z����B
             * �ˌ�����A�ۏ؋�����A��p�،��ւ̐U��ւ����l�����Ȃ�
             * 
             * �i�v�Z���j
             * �@@�M�p�����\�z�@@ = Min((�����ۏ؋�(n) - �Œ�K�v�ۏ؋�), (����ۏ؋�(n) - �@@��Œ�K�v�ۏ؋�))
             */

            //�v�Z�@@�i=�����ۏ؋�(n) - �Œ�K�v�ۏ؋��j
            double l_dblTmp1 =
                this.calcPaidMarginDeposit(l_intSpecifiedPoint)
                    - this.calcCondition.getMinMarginDeposit();
            //�v�Z�A�i=����ۏ؋�(n) - �@@��Œ�K�v�ۏ؋��j
            double l_dblTmp2 =
                this.calcReceiptMarginDeposit(l_intSpecifiedPoint)
                    - this.calcCondition.getLegalMinMarginDeposit();

            //�M�p�����\�z�@@�Ɍv�Z�@@�ƌv�Z�A�̏�����������
            double l_dblActualReceiptTradingPower1 = Math.min(l_dblTmp1, l_dblTmp2);
            BigDecimal l_bdActualReceiptTradingPower1 =
                new BigDecimal(l_dblActualReceiptTradingPower1);

            /*
             * �i�]�͌v�Z��p�|��+�ۏ؋��ێ����j���v�Z����B
             */
            int l_intTotalRate =
                this.calcCondition.getSubstituteRate()
                    + this.calcCondition.getMarginMentenanceRate();

            //�M�p�����\�z�@@��0��菬������
            if (l_bdActualReceiptTradingPower1.compareTo(new BigDecimal(0.0)) < 0)
            {
                /*
                 * �M�p�����\�z���v�Z����B
                 * �ˑ�p�،��ւ̐U��ւ���l�����Ȃ������\�z�̌v�Z���s��
                 * 
                 * �i�v�Z���j
                 * �@@�M�p�����\�z = Min(�M�p�����\�z�@@, ����ۏ؋�(n) - �����ό��ʑ��(n) * �ۏ؋��ێ���, �g�p�\����(n))
                 */

                /*
                 * �M�p�����\�z�A�i= ����ۏ؋�(n) - �����ό��ʑ��(n) * (�ۏ؋��ێ��� / 100) �j���v�Z����B
                 */

                //�ۏ؋��ێ���
                BigDecimal l_bdMarginMentenanceRate =
                    new BigDecimal(this.calcCondition.getMarginMentenanceRate());

                //�v�Z�@@�i= �����ό��ʑ��(n) * �ۏ؋��ێ��� / 100�j
                BigDecimal l_bdTmp1 =
                    l_bdContractAmount.multiply(l_bdMarginMentenanceRate).divide(
                        new BigDecimal(100.0),
                        10,
                        BigDecimal.ROUND_HALF_EVEN);

                //�M�p�����\�z�A�i= ����ۏ؋�(n) - �v�Z�@@�j
                double l_dblActualReceiptTradingPower2 =
                    this.calcReceiptMarginDeposit(l_intSpecifiedPoint) - l_bdTmp1.doubleValue();

                /*
                 * �M�p�����\�z�ɁA�M�p�����\�z�@@�A�v�Z�A�̌��ʁA�g�p�\����(n)�̓��A�ŏ��̒l��������B 
                 */
                l_dblActualReceiptTradingPower =
                    Math.min(l_dblActualReceiptTradingPower1, l_dblActualReceiptTradingPower2);

                l_dblActualReceiptTradingPower =
                    Math.min(
                        l_dblActualReceiptTradingPower,
                        this.calcActualAccountBalance(l_intSpecifiedPoint));

                //�����_�ȉ���؂�̂Ă�B
                l_dblActualReceiptTradingPower = Math.floor(l_dblActualReceiptTradingPower);
            }
            //(�M�p�����\�z�@@��0�ȏ�@@���@@�i�]�͌v�Z��p�|��+�ۏ؋��ێ����j��100�i���j��菬������
            else if (
                l_bdActualReceiptTradingPower1.compareTo(new BigDecimal(0.0)) >= 0
                    && l_intTotalRate < 100)
            {
                /*
                 * �M�p�����\�z���v�Z����B
                 * �ˈێ�������A��p�،��ւ̐U��ւ���l�����������\�z�̌v�Z���s��
                 * 
                 * �i�v�Z���j
                 * �@@�M�p�����\�z(n) = Min(�M�p�����\�z�@@ / (1 - (�]�͌v�Z��p�|�� / 100)),
                 *              (����ۏ؋�(n)  - �K�v�ۏ؋�(n)) / (1 - ( (�]�͌v�Z��p�|�� + �ۏ؋��ێ���) / 100) ),�g�p�\����(n))
                 */

                /*
                 * �M�p�����\�z�A�i= �M�p�����\�z�@@ / (1 - (�]�͌v�Z��p�|�� / 100))�j���v�Z����B
                 */

                //�]�͌v�Z��p�|��
                BigDecimal l_bdSubstituteRate =
                    new BigDecimal(this.calcCondition.getSubstituteRate());

                //�v�Z�@@�i = �M�p�����\�z�@@ / (1 - �]�͌v�Z��p�|��) / 100�j             
                BigDecimal l_bdTmp1 =
                    l_bdActualReceiptTradingPower1.divide(
                        BigDecimal.valueOf(1).subtract(
                            l_bdSubstituteRate.divide(
                                new BigDecimal(100.0),
                                10,
                                BigDecimal.ROUND_HALF_EVEN)),
                        10,
                        BigDecimal.ROUND_HALF_EVEN);

                //�M�p�����\�z�A
                double l_dblActualReceiptTradingPower2 = l_bdTmp1.doubleValue();

                /*
                 * �M�p�����\�z�B�i= (����ۏ؋�(n)  - �K�v�ۏ؋�(n)) / (1 - ( (�]�͌v�Z��p�|�� + �ۏ؋��ێ���) / 100) )�j���v�Z����B
                 */

                //�v�Z�@@�i= ����ۏ؋�(n) - �K�v�ۏ؋�(n)�j
                l_dblTmp1 =
                    this.calcReceiptMarginDeposit(l_intSpecifiedPoint)
                        - this.getMarginDeposit(l_intSpecifiedPoint);
                l_bdTmp1 = new BigDecimal(l_dblTmp1);

                //(�]�͌v�Z��p�|�� + �ۏ؋��ێ���)
                BigDecimal l_bdTotalRate = new BigDecimal(l_intTotalRate);

                //�v�Z�A�i= �v�Z�@@ / 1 - (�]�͌v�Z��p�|�� + �ۏ؋��ێ���) / 100�j
                BigDecimal l_bdTmp2 =
                    l_bdTmp1.divide(
                        BigDecimal.valueOf(1).subtract(
                            l_bdTotalRate.divide(
                                new BigDecimal(100.0),
                                10,
                                BigDecimal.ROUND_HALF_EVEN)),
                        10,
                        BigDecimal.ROUND_HALF_EVEN);

                //�M�p�����\�z�B
                double l_dblActualReceiptTradingPower3 = l_bdTmp2.doubleValue();

                /*
                 * �M�p�����\�z�ɁA�M�p�����\�z�A�A�M�p�����\�z�B�A�g�p�\����(n)�̓��A�ŏ��̒l��������B
                 */
                l_dblActualReceiptTradingPower =
                    Math.min(l_dblActualReceiptTradingPower2, l_dblActualReceiptTradingPower3);

                l_dblActualReceiptTradingPower =
                    Math.min(
                        l_dblActualReceiptTradingPower,
                        this.calcActualAccountBalance(l_intSpecifiedPoint));

                //�����_�ȉ���؂�̂Ă�B
                l_dblActualReceiptTradingPower = Math.floor(l_dblActualReceiptTradingPower);
            }
            //����ȊO
            else
            {
                /*
                 * �M�p�����\�z���v�Z����B
                 * �ˈێ���������l�������A��p�،��ւ̐U��ւ����l�����������\�z�̌v�Z���s��
                 * 
                 * �i�v�Z���j
                 * �@@�M�p�����\�z = Min(�M�p�����\�z�@@ / (1 - (�]�͌v�Z��p�|�� / 100) ), �g�p�\����(n))
                 */

                /*
                 * �M�p�����\�z�A�i= �M�p�����\�z�@@ / (1 - (�]�͌v�Z��p�|�� / 100))�j���v�Z����B
                 */

                //�]�͌v�Z��p�|��
                BigDecimal l_bdSubstituteRate =
                    new BigDecimal(this.calcCondition.getSubstituteRate());

                //�v�Z�@@�i= �M�p�����\�z�@@ / ( 1 - (�]�͌v�Z��p�|�� / 100))              
                BigDecimal l_bdTmp1 =
                    l_bdActualReceiptTradingPower1.divide(
                        BigDecimal.valueOf(1).subtract(
                            l_bdSubstituteRate.divide(
                                new BigDecimal(100.0),
                                10,
                                BigDecimal.ROUND_HALF_EVEN)),
                        10,
                        BigDecimal.ROUND_HALF_EVEN);

                //�M�p�����\�z�A
                double l_dblActualReceiptTradingPower2 = l_bdTmp1.doubleValue();

                /*
                 * �M�p�����\�z�ɁA�M�p�����\�z�A�A�g�p�\����(n)�̓��A�ŏ��̒l��������B
                 */
                l_dblActualReceiptTradingPower =
                    Math.min(
                        l_dblActualReceiptTradingPower2,
                        this.calcActualAccountBalance(l_intSpecifiedPoint));

                //�����_�ȉ���؂�̂Ă�B
                l_dblActualReceiptTradingPower = Math.floor(l_dblActualReceiptTradingPower);
            }
        }
        //����ȊO
        else
        {
            //�M�p�����\�z(n) = �g�p�\����(n)
            l_dblActualReceiptTradingPower = this.calcActualAccountBalance(l_intSpecifiedPoint);
        }

        //�v�Z�����M�p�����\�z��ԋp����B
        return l_dblActualReceiptTradingPower;
    }

    /**
     * �icalc�M�p�����\�z<���v��S�����l��>�j<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́u�M�p�����]��<���v��S�����l��>�v���v�Z����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B6A5F9035A
     */
    public double calcActualReceiptTradingPowerIncDayTrade(int l_intSpecifiedPoint)
    {
        //�p�����[�^.�w�����T+0��菬�����@@�܂��́@@T+5���傫����
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcActualReceiptTradingPowerIncDayTrade(int)");
        }

        // �g�M�p�����]�͂̒Ǐؗ]�̓`�F�b�N�h���擾����
        String l_strInstBranCalcCondition = 
            this.calcCondition.getInstBranCalcCondition(
                WEB3TPCalcCondition.ACTUALRECEIPT_MARGINCALLPOWER_CHECK);

        // �Ǐؗ]��(n)���v�Z����
        double l_dblMarginCallPower = this.calcMarginCallPower(l_intSpecifiedPoint);

        //�M�p�����\�z
        double l_dblActualReceiptTradingPower;

        //�����ό��ʑ��<���v��ԍρE�������n���ʑ���l��>(BigDecimal�^)
        BigDecimal l_bdContractAmount =
            new BigDecimal(this.getContractAmountDayRepay(l_intSpecifiedPoint));

        // �Ǐؗ]�� (n) < 0�ꍇ ���� �M�p�����]�͂̒Ǐؗ]�̓`�F�b�N = 1
        if (l_dblMarginCallPower < 0 &&
            WEB3TPActualReceiptMargincallPowerCheckDef.EXECUTE.equals(l_strInstBranCalcCondition))
        {
            l_dblActualReceiptTradingPower = 0;
        }
        else if (l_bdContractAmount.compareTo(new BigDecimal(0.0)) > 0)
        {
        //�����ό��ʑ��<���v��ԍρE�������n���ʑ���l��>��0���傫����

            /*
             * �M�p�����\�z�@@���v�Z����B
             * �ˌ�����A�ۏ؋�����A��p�،��ւ̐U��ւ����l�����Ȃ�
             * 
             * �i�v�Z���j
             * �@@�M�p�����\�z�@@ = Min((�����ۏ؋�(n) - �Œ�K�v�ۏ؋�), (����ۏ؋�(n) - �@@��Œ�K�v�ۏ؋�))
             */

            //�v�Z�@@�i=�����ۏ؋�(n) - �Œ�K�v�ۏ؋��j
            double l_dblTmp1 =
                this.calcPaidMarginDeposit(l_intSpecifiedPoint)
                    - this.calcCondition.getMinMarginDeposit();
            //�v�Z�A�i=����ۏ؋�(n) - �@@��Œ�K�v�ۏ؋��j
            double l_dblTmp2 =
                this.calcReceiptMarginDeposit(l_intSpecifiedPoint)
                    - this.calcCondition.getLegalMinMarginDeposit();

            //�M�p�����\�z�@@�Ɍv�Z�@@�ƌv�Z�A�̏�����������
            double l_dblActualReceiptTradingPower1 = Math.min(l_dblTmp1, l_dblTmp2);
            BigDecimal l_bdActualReceiptTradingPower1 =
                new BigDecimal(l_dblActualReceiptTradingPower1);

            /*
             * �i�]�͌v�Z��p�|��+�ۏ؋��ێ����j���v�Z����B
             */
            int l_intTotalRate =
                this.calcCondition.getSubstituteRate()
                    + this.calcCondition.getMarginMentenanceRate();

            //�M�p�����\�z�@@��0��菬������
            if (l_bdActualReceiptTradingPower1.compareTo(new BigDecimal(0.0)) < 0)
            {
                /*
                 * �M�p�����\�z���v�Z����B
                 * �ˑ�p�،��ւ̐U��ւ���l�����Ȃ������\�z�̌v�Z���s��
                 * 
                 * �i�v�Z���j
                 * �@@�M�p�����\�z = Min(�M�p�����\�z�@@, ����ۏ؋�(n) - �����ό��ʑ��(n) * �ۏ؋��ێ���, ���o�\����(n))
                 */

                /*
                 * �M�p�����\�z�A�i= ����ۏ؋�(n) - �����ό��ʑ��(n) * (�ۏ؋��ێ��� / 100) �j���v�Z����B
                 */

                //�ۏ؋��ێ���
                BigDecimal l_bdMarginMentenanceRate =
                    new BigDecimal(this.calcCondition.getMarginMentenanceRate());

                //�v�Z�@@�i= �����ό��ʑ��(n) * �ۏ؋��ێ��� / 100�j
                BigDecimal l_bdTmp1 =
                    l_bdContractAmount.multiply(
                        l_bdMarginMentenanceRate.divide(
                            new BigDecimal(100.0),
                            10,
                            BigDecimal.ROUND_HALF_EVEN));

                //�M�p�����\�z�A�i= ����ۏ؋�(n) - �v�Z�@@�j
                double l_dblActualReceiptTradingPower2 =
                    this.calcReceiptMarginDeposit(l_intSpecifiedPoint) - l_bdTmp1.doubleValue();

                /*
                 * �M�p�����\�z�ɁA�M�p�����\�z�@@�A�v�Z�A�̌��ʁA���o�\����(n)�̓��A�ŏ��̒l��������B 
                 */
                l_dblActualReceiptTradingPower =
                    Math.min(l_dblActualReceiptTradingPower1, l_dblActualReceiptTradingPower2);

                l_dblActualReceiptTradingPower =
                    Math.min(
                        l_dblActualReceiptTradingPower,
                        this.calcActualPaymentBalance(l_intSpecifiedPoint));

                //�����_�ȉ���؂�̂Ă�B
                l_dblActualReceiptTradingPower = Math.floor(l_dblActualReceiptTradingPower);
            }
            //(�M�p�����\�z�@@��0�ȏ�@@���@@�i�]�͌v�Z��p�|��+�ۏ؋��ێ����j��100�i���j��菬������
            else if (l_intTotalRate < 100)
            {
                /*
                 * �M�p�����\�z���v�Z����B
                 * �ˈێ�������A��p�،��ւ̐U��ւ���l�����������\�z�̌v�Z���s��
                 * 
                 * �i�v�Z���j
                 * �@@�M�p�����\�z(n) = Min(�M�p�����\�z�@@ / (1 - (�]�͌v�Z��p�|�� / 100)),
                 *              (����ۏ؋�(n)  - �K�v�ۏ؋�(n)) / (1 - ( (�]�͌v�Z��p�|�� + �ۏ؋��ێ���) / 100) ),
                 *                                                                                                          ���o�\����(n))
                 */

                /*
                 * �M�p�����\�z�A�i= �M�p�����\�z�@@ / (1 - (�]�͌v�Z��p�|�� / 100))�j���v�Z����B
                 */

                //�]�͌v�Z��p�|��
                BigDecimal l_bdSubstituteRate =
                    new BigDecimal(this.calcCondition.getSubstituteRate());

                //�v�Z�@@�i= �M�p�����\�z�@@ / (1 - (�]�͌v�Z��p�|�� / 100)�j              
                BigDecimal l_bdTmp1 =
                    l_bdActualReceiptTradingPower1.divide(
                        BigDecimal.valueOf(1).subtract(
                            l_bdSubstituteRate.divide(
                                new BigDecimal(100.0),
                                10,
                                BigDecimal.ROUND_HALF_EVEN)),
                        10,
                        BigDecimal.ROUND_HALF_EVEN);

                //�M�p�����\�z�A
                double l_dblActualReceiptTradingPower2 = l_bdTmp1.doubleValue();

                /*
                 * �M�p�����\�z�B�i = (����ۏ؋�(n) - �K�v�ۏ؋�(n)) / (1 - ( (�]�͌v�Z��p�|�� + �ۏ؋��ێ���) / 100) )�j���v�Z����B
                 */

                //�v�Z�@@�i= ����ۏ؋�(n) - �K�v�ۏ؋�(n)�j
                l_dblTmp1 =
                    this.calcReceiptMarginDeposit(l_intSpecifiedPoint)
                        - this.getMarginDeposit(l_intSpecifiedPoint);
                l_bdTmp1 = new BigDecimal(l_dblTmp1);

                //(�]�͌v�Z��p�|�� + �ۏ؋��ێ���)
                BigDecimal l_bdTotalRate = new BigDecimal(l_intTotalRate);

                //�v�Z�A�i= �v�Z�@@ / 1 - ((�]�͌v�Z��p�|�� + �ۏ؋��ێ���) / 100))
                BigDecimal l_bdTmp2 =
                    l_bdTmp1.divide(
                        BigDecimal.valueOf(1).subtract(
                            l_bdTotalRate.divide(
                                new BigDecimal(100.0),
                                10,
                                BigDecimal.ROUND_HALF_EVEN)),
                        10,
                        BigDecimal.ROUND_HALF_EVEN);

                //�M�p�����\�z�B
                double l_dblActualReceiptTradingPower3 = l_bdTmp2.doubleValue();

                /*
                 * �M�p�����\�z�ɁA�M�p�����\�z�A�A�M�p�����\�z�B�A���o�\����(n)�̓��A�ŏ��̒l��������B
                 */
                l_dblActualReceiptTradingPower =
                    Math.min(l_dblActualReceiptTradingPower2, l_dblActualReceiptTradingPower3);

                l_dblActualReceiptTradingPower =
                    Math.min(
                        l_dblActualReceiptTradingPower,
                        this.calcActualPaymentBalance(l_intSpecifiedPoint));

                //�����_�ȉ���؂�̂Ă�B
                l_dblActualReceiptTradingPower = Math.floor(l_dblActualReceiptTradingPower);
            }
            //����ȊO
            else
            {
                /*
                 * �M�p�����\�z���v�Z����B
                 * �ˈێ���������l�������A��p�،��ւ̐U��ւ����l�����������\�z�̌v�Z���s��
                 * 
                 * �i�v�Z���j
                 * �@@�M�p�����\�z = Min(�M�p�����\�z�@@ / (1 - (�]�͌v�Z��p�|�� / 100) ), ���o�\����(n))
                 */

                /*
                 * �M�p�����\�z�A�i= �M�p�����\�z�@@ / (1 - (�]�͌v�Z��p�|�� / 100))�j���v�Z����B
                 */

                //�]�͌v�Z��p�|��
                BigDecimal l_bdSubstituteRate =
                    new BigDecimal(this.calcCondition.getSubstituteRate());

                //�v�Z�@@�i= �M�p�����\�z�@@ / 1 - (�]�͌v�Z��p�|�� / 100))              
                BigDecimal l_bdTmp1 =
                    l_bdActualReceiptTradingPower1.divide(
                        BigDecimal.valueOf(1).subtract(
                            l_bdSubstituteRate.divide(
                                new BigDecimal(100.0),
                                10,
                                BigDecimal.ROUND_HALF_EVEN)),
                        10,
                        BigDecimal.ROUND_HALF_EVEN);

                //�M�p�����\�z�A
                double l_dblActualReceiptTradingPower2 = l_bdTmp1.doubleValue();

                /*
                 * �M�p�����\�z�ɁA�M�p�����\�z�A�A���o�\����(n)�̓��A�ŏ��̒l��������B
                 */
                l_dblActualReceiptTradingPower =
                    Math.min(
                        l_dblActualReceiptTradingPower2,
                        this.calcActualPaymentBalance(l_intSpecifiedPoint));

                //�����_�ȉ���؂�̂Ă�B
                l_dblActualReceiptTradingPower = Math.floor(l_dblActualReceiptTradingPower);
            }
        }
        //����ȊO
        else
        {
            //�M�p�����\�z(n) = ���o�\����(n)
            l_dblActualReceiptTradingPower = this.calcActualPaymentBalance(l_intSpecifiedPoint);
        }

        //�v�Z�����M�p�����\�z��ԋp����B
        return l_dblActualReceiptTradingPower;
    }

    /**
     * �icalc���̑����i���t�\�z�j<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́u���̑����i���t�\�z�v���v�Z����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B6A608001E
     */
    public double calcOtherTradingPower(int l_intSpecifiedPoint)
    {
        //�p�����[�^.�w�����T+0��菬�����@@�܂��́@@T+5���傫����
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcOtherTradingPower(int)");
        }

        //���̑����i���t�\�z(n)�@@=�@@Min(�ۏ؋����o�]��(n), �g�p�\����(n))
        double l_dblOtherTradingPower =
            Math.min(this.calcMarginDrawPower(l_intSpecifiedPoint), this.calcActualAccountBalance(l_intSpecifiedPoint));

        //�v�Z�������̑����i���t�\�z��ԋp����B
        return l_dblOtherTradingPower;
    }

    /**
     * �icalc���̑����i���t�\�z<���v��S�����l��>�j<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́u���̑����i���t�\�z<���v��S�����l��>�v���v�Z����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    public double calcOtherTradingPowerIncDayTrade(int l_intSpecifiedPoint)
    {
        //�p�����[�^.�w�����T+0��菬�����@@�܂��́@@T+5���傫����
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcOtherTradingPowerIncDayTrade(int)");
        }
    
        //���̑����i���t�\�z(n) =�@@Min(�ۏ؋����o�]��(n), ���o�\����(n))
        double l_dblOtherTradingPower =
            Math.min(
                this.calcMarginDrawPower(l_intSpecifiedPoint),
                this.calcActualPaymentBalance(l_intSpecifiedPoint));

        //�v�Z�������̑����i���t�\�z��ԋp����B
        return l_dblOtherTradingPower;
    }

    /**
     * �icalc�o���\�z�j<BR>
     * 
     * �ŏ��́u�o���\�z�v���v�Z����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
     * @@return WEB3TPCalcResult
     * @@roseuid 40DFE9D30029
     */
    public WEB3TPCalcResult calcPaymentTradingPower()
    {
        //�o���\�z���擾����
        WEB3TPCalcResult l_calcResult = this.calcAppliedOtherTradingPower(OrderTypeEnum.CASH_OUT);

        //�o���\�z��ԋp����B
        return l_calcResult;
    }

    /**
     * �icalc���M���t�\�z�j<BR>
     * 
     * �ŏ��́u���M���t�\�z�v���v�Z����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
     * @@return WEB3TPCalcResult
     * @@roseuid 40DFEAB302F8
     */
    public WEB3TPCalcResult calcFundTradingPower()
    {
        //���M���t�\�z���擾����
        WEB3TPCalcResult l_calcResult = this.calcAppliedOtherTradingPower(OrderTypeEnum.MF_BUY);

        //���M���t�\�z��ԋp����B
        return l_calcResult;
    }

    /**
     * �icalc�I�v�V�����V�K�����\�z�j<BR>
     * 
     * �ŏ��́u�I�v�V�����V�K�����\�z�v���v�Z����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
     * @@return WEB3TPCalcResult
     * @@roseuid 40DFEAC203C3
     */
    public WEB3TPCalcResult calcOptionTradingPower()
    {
        //�I�v�V�����V�K���\�z���擾����
        WEB3TPCalcResult l_calcResult =
            this.calcAppliedOtherTradingPower(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);

        //�I�v�V�����V�K���\�z��ԋp����B
        return l_calcResult;
    }

    /**
     * �icalc���������z�j<BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́u���������z�v���v�Z����B<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q��
     * 
     * @@param l_intSpecifiedPoint<BR>
     * @@return double
     */
    public double calcDemandamount(int l_intSpecifiedPoint)
    {
        //�p�����[�^.�w�����T+0��菬�����@@�܂��́@@T+5���傫����
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcDemandamount(int)");
        }
    
        //���������z(n) = Abs(Min(�a��������]��(n),�Ǐؗ]��(n),0))
        double l_dblDemandAmt =
            Math.min(
                this.calcAccountBalanceDemandPower(l_intSpecifiedPoint),
                this.calcMarginCallPower(l_intSpecifiedPoint));
        l_dblDemandAmt = Math.abs(Math.min(l_dblDemandAmt, 0));
    
        return l_dblDemandAmt;
    }

    /**
     * �icalc���֋��j<BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́u���֋��v���v�Z����B<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q��
     * 
     * @@param l_intSpecifiedPoint<BR>
     * @@return double
     */
    public double calcDebitAmount(int l_intSpecifiedPoint)
    {
        //�p�����[�^.�w�����T+0��菬�����@@�܂��́@@T+5���傫����
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcDemandamount(int)");
        }
    
        //���֋�(n) = Abs(Min((�g�p�\����(n) + ����n���ʌ����K�v�ۏ؋�(n)),0))
        double l_dblDebitAmt = this.calcActualAccountBalance(l_intSpecifiedPoint)
                + this.getUndeliCashMarginDeposit(l_intSpecifiedPoint);
        l_dblDebitAmt = Math.abs(Math.min(l_dblDebitAmt, 0));

        return l_dblDebitAmt;
    }

    /**
     * �icalc���ʗ��֋��j<BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́u���ʗ��֋��v���v�Z����B<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q��
     * 
     * @@param l_intSpecifiedPoint<BR>
     * @@return double
     */
    public double calcSpecialDebitAmount(int l_intSpecifiedPoint)
    {
        //�p�����[�^.�w�����T+0��菬�����@@�܂��́@@T+5���傫����
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcDemandamount(int)");
        }
    
        //���֋�(n) = Abs(Min(�a��������]��(n),0))
        double l_dblSpecialDebitAmt = Math.abs(Math.min(
                this.calcAccountBalanceDemandPower(l_intSpecifiedPoint),
                0));

        return l_dblSpecialDebitAmt;
    }

    /**
     * �icalc�ۏ؋��ێ��]�́j<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�A�ۏ؋����́u�ۏ؋��ێ��]�́v��ԋp����B<BR>
     * <BR>
     * @@param l_intSpecifiedPoint
     * @@param l_marginDepositRate
     * @@return double
     */
    public double calcMarginMaintenancePower(int l_intSpecifiedPoint, int l_marginDepositRate)
    {
        //�p�����[�^.�w�����T+0��菬�����@@�܂��́@@T+5���傫����
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcMarginMaintenancePower(int, int)");
        }
    
        //�ۏ؋��ێ��]��
        double l_dblMarginMaintenancePower;
    
        //����ۏ؋�
        double l_dblReceiptMarginDeposit = calcReceiptMarginDeposit(l_intSpecifiedPoint);
        
        //�����ό��ʑ��<���v��ԍρE�������n���ʑ���l��>
        double l_dblContractAmountDayPepay = getContractAmountDayRepay(l_intSpecifiedPoint);
    
        //�ۏ؋��ێ��]�� =  ����ۏ؋� - ( �����ό��ʑ��<���v��ԍρE�������n���ʑ���l��> * �ۏ؋��� / 100 )
        l_dblMarginMaintenancePower =
            l_dblReceiptMarginDeposit - (l_dblContractAmountDayPepay * l_marginDepositRate / 100);
    
        return l_dblMarginMaintenancePower;
    }

    /**
     * �icalc�K�p�ۏ؋��a�����j<BR>
     * 
     * �ŏ��́u�ۏ؋��a�����v���v�Z����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
     * @@return WEB3TPCalcResult
     * @@roseuid 40DA90AC006C
     */
    public WEB3TPCalcResult calcAppliedMarginDepositRate()
    {
        /*
         * �ۏ؋��a�������擾����B
         */
    
        //�ۏ؋��a�����i�ŏ��l�����߂����̂�double�^�̍ő�l�������l�ɃZ�b�g�j
        double l_dblMarginDepositRate = Double.MAX_VALUE;
        //�ۏ؋��a����(BigDecimal�^)
        BigDecimal l_bdMarginDepositRate = new BigDecimal(l_dblMarginDepositRate);
        
        //�K�p�V�K���\�z�����߂�(�V�K���\�z�̓K�p���Ɠ������Ƃ邽��)
        WEB3TPCalcResult l_appliedMarginTpResult = this.calcAppliedMarginTradingPower();
        
        //�K�p�����擾����
        int l_intAppliedPoint = l_appliedMarginTpResult.appliedPoint;
        
        //calc�ۏ؋��a����(�K�p���̗a���������߂�)
        double l_dblTradingPower = this.calcMarginDepositRate(l_intAppliedPoint);
        
        /*
         * �]�͌v�Z���ʃI�u�W�F�N�g�Ɍv�Z���ʂ��Z�b�g���ԋp����B
         */
    
        //�]�͌v�Z���ʂɌv�Z���ʂ�������B
        WEB3TPCalcResult l_calcResult = new WEB3TPCalcResult();
        l_calcResult.appliedPoint = l_intAppliedPoint;
        l_calcResult.tradingPower = l_dblTradingPower;
        l_calcResult.orderTypeEnum = null;
    
        //�]�͌v�Z���ʂ�ԋp����B
        return l_calcResult;
    }

    /**
     * �icalc�K�p�ۏ؋��a�����j<BR>
     * <BR>
     * ����.����ȍ~�ŏ��́u�ۏ؋��a�����v���v�Z����B<BR>
     * <BR>
     * �P�j�]�͌v�Z���<�M�p�V�K��>��ޔ�����B<BR>
     * �@@�|this.�]�͌v�Z����.get�]�͌v�Z���<�M�p�V�K��>()���R�[��<BR>
     * <BR>
     * �Q�j�]�͌v�Z���<�M�p�V�K��>�ɁA����.������Z�b�g����B<BR>
     * �@@�|this.�]�͌v�Z����.set�]�͌v�Z���<�M�p�V�K��>()���R�[��<BR>
     * <BR>
     * �@@�m�����n<BR>
     * �@@�@@int�F����.���<BR>
     * <BR>
     * �R�j����.����ȍ~�A�ŏ��̕ۏ؋��a�������v�Z����<BR>
     * �@@�|this.calc�K�p�ۏ؋��a����()���R�[��<BR>
     * <BR>
     * �S�j�ޔ����Ă����A�����߂�<BR>
     * �@@�|this.�]�͌v�Z����.set�]�͌v�Z���<�M�p�V�K��>()���R�[��<BR>
     * <BR>
     * �@@�m�����n<BR>
     * �@@�@@int�F�P�j�̖߂�l<BR>
     * <BR>
     * �T�j�R�j�̖߂�l��ԋp����B<BR>
     * <BR>
     * @@param l_intBasePoint<BR>
     * @@return WEB3TPCalcResult
     */
    public WEB3TPCalcResult calcAppliedMarginDepositRate(int l_intBasePoint)
    {
        //����.�����T+0��菬������
        if (l_intBasePoint < WEB3TPSpecifiedPointDef.T_0)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcAppliedMarginDepositRate(int)");
        }
        
        //�]�͌v�Z���<�M�p�V�K��>��ޔ�����B
        int l_intOriginalBasePoint = this.calcCondition.getMarginBasePoint();

        /*
         * �]�͌v�Z���<�M�p�V�K��>�ɁA����.������Z�b�g����
         */
        //����.�����T+5���傫���ꍇ
        if(l_intBasePoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //T+5���Z�b�g
            this.calcCondition.setMarginBasePoint(WEB3TPSpecifiedPointDef.T_5);
        }
        //�ȊO�̏ꍇ
        else
        {
            //����.������Z�b�g
            this.calcCondition.setMarginBasePoint(l_intBasePoint);
        }

        //����.����ȍ~�A�ŏ��̕ۏ؋��a�������v�Z����
        WEB3TPCalcResult l_result = this.calcAppliedMarginDepositRate();

        //�ޔ����Ă����A�����߂��B
        this.calcCondition.setMarginBasePoint(l_intOriginalBasePoint);

        //�v�Z���ʂ�ԋp
        return l_result;
    }

    /**
     * �icalc�K�p�������t�\�z�j<BR>
     * 
     * �ŏ��́u�������t�\�z�v���v�Z����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
     * @@return WEB3TPCalcResult
     * @@roseuid 40BFFEA201B5
     */
    public WEB3TPCalcResult calcAppliedEquityTradingPower()
    {
        /*
         * �]�͌v�Z���<����/�M�p����>�ȍ~��
         * �ŏ��̊������t�\�z�ƓK�p�����擾����B
         */
        //�������t�\�z�i�ŏ��l�����߂����̂�double�^�̍ő�l�������l�ɃZ�b�g�j
        double l_dblEquityTradingPower = Double.MAX_VALUE;
        //�������t�\�z(BigDecimal�^)
        BigDecimal l_bdEquityTradingPower = new BigDecimal(l_dblEquityTradingPower);
        //�K�p��
        int l_intAppliedPoint = 0;
    
        //�]�͌v�Z���<�������t�^�M�p����>
        int l_intBasePoint = this.calcCondition.getEquityBasePoint();
    
        //�w����͈̔́i�]�͌v�Z���<�������t�^�M�p����>�`T+5�j�Ń��[�v
        for (int index = l_intBasePoint; index <= WEB3TPSpecifiedPointDef.T_5; index++)
        {
            //�������t�\�z(����)(BigDecimal�^)
            BigDecimal l_bdCurEquityTradingPower;
    
            //���Ԃ��]�͌v�Z���<�������t�^�M�p����>�Ɠ�������            
            if (index == l_intBasePoint)
            {
                //�������t�\�z���v�Z����B
                l_bdCurEquityTradingPower = new BigDecimal(this.calcEquityTradingPower(index));
            }
            //����ȊO
            else
            {
                //�������t�\�z<���v��S�����l��>���v�Z����B
                l_bdCurEquityTradingPower =
                    new BigDecimal(this.calcEquityTradingPowerIncDayTrade(index));
            }
    
            //�������t�\�z(����)���������t�\�z��菬������
            if (l_bdCurEquityTradingPower.compareTo(l_bdEquityTradingPower) < 0)
            {
                //�������t�\�z�Ɋ������t�\�z(����)��������B
                l_bdEquityTradingPower = l_bdCurEquityTradingPower;
                //�K�p���ɍ��Ԃ�������B
                l_intAppliedPoint = index;
            }
        }

        //�ma. �������t�\�z�@@< 0�̏ꍇ�n
        if (l_bdEquityTradingPower.compareTo(new BigDecimal(0.0)) < 0)
        {
            l_bdEquityTradingPower = new BigDecimal(-1.0);
        }
        

        /*
         * �a��������]�̓`�F�b�N
         */
        //�a��������]�́i�ŏ��l�����߂����̂�double�^�̍ő�l�������l�ɃZ�b�g�j
        double l_dblActualPaymentBalance = Double.MAX_VALUE;
        //�a��������]��(BigDecimal�^)
        BigDecimal l_bdActualPaymentBalance = new BigDecimal(l_dblActualPaymentBalance);
        //�K�p��<�a��������]��>
        int l_intActPayAppliedPoint = 0;

        //�w����͈̔́iT+0�`�]�͌v�Z���<�������t�^�M�p����>-1�j�Ń��[�v
        for(int index = WEB3TPSpecifiedPointDef.T_0; index < l_intBasePoint; index++)
        {
            //�a��������]��(����)(BigDecimal�^)
            BigDecimal l_bdCurActualPaymentBalance = new BigDecimal(this
                .calcAccountBalanceDemandPower(index));

            //�a��������]��(����)���a��������]�͂�菬������
            if(l_bdCurActualPaymentBalance.compareTo(l_bdActualPaymentBalance) < 0)
            {
                //�a��������]�͂ɗa��������]��(����)��������B
                l_bdActualPaymentBalance = l_bdCurActualPaymentBalance;
                //�K�p���ɍ��Ԃ�������B
                l_intActPayAppliedPoint = index;
            }
        }

        //�ma. �a��������]�� < 0�̏ꍇ�n
        if(l_bdActualPaymentBalance.compareTo(new BigDecimal(0.0)) < 0)
        {
            //[b.�������t�\�z >= 0 �̏ꍇ]
            if(l_bdEquityTradingPower.compareTo(new BigDecimal(0.0)) >= 0)
            {
                //�������t�\�z = -1
                l_bdEquityTradingPower = new BigDecimal(-1.0);
                //�K�p�� = �K�p��<�a��������]��>
                l_intAppliedPoint = l_intActPayAppliedPoint;
            }
            //[b.�������t�\�z < 0 ���� �K�p�� > �K�p��<�a��������]��> �̏ꍇ]
            else if(l_bdEquityTradingPower.compareTo(new BigDecimal(0.0)) < 0
                    && l_intAppliedPoint > l_intActPayAppliedPoint)
            {
                //�������t�\�z = -1
                l_bdEquityTradingPower = new BigDecimal(-1.0);
                //�K�p�� = �K�p��<�a��������]��>
                l_intAppliedPoint = l_intActPayAppliedPoint;
            }
        }
        
        /*
         * �Ǐؗ]�̓`�F�b�N
         */
        //������<�����^�M�p>
        int l_intBizDate = this.calcCondition.getEquityBizDate();

        //�Ǐؗ]��<�K�p�\�z�`�F�b�N�p>���擾
        WEB3TPCalcResult l_marginCallPower = this.calcMarginCallPowerForCheck(
                OrderTypeEnum.EQUITY_BUY, l_intBizDate, l_intBasePoint);

        //�ma. �Ǐؗ]�� < 0�̏ꍇ�n
        if(l_marginCallPower != null && l_marginCallPower.tradingPower < 0)
        {
            //[b.�������t�\�z >= 0 �̏ꍇ]
            if(l_bdEquityTradingPower.compareTo(new BigDecimal(0.0)) >= 0)
            {
                //�������t�\�z = -1
                l_bdEquityTradingPower = new BigDecimal(-1.0);
                //�K�p�� = �K�p��<�Ǐؗ]��>
                l_intAppliedPoint = l_marginCallPower.appliedPoint;
            }
            //[b.�������t�\�z < 0 ���� �K�p�� > �K�p��<�Ǐؗ]��> �̏ꍇ]
            else if(l_bdEquityTradingPower.compareTo(new BigDecimal(0.0)) < 0
                    && l_intAppliedPoint > l_marginCallPower.appliedPoint)
            {
                //�������t�\�z = -1
                l_bdEquityTradingPower = new BigDecimal(-1.0);
                //�K�p�� = �K�p��<�Ǐؗ]��>
                l_intAppliedPoint = l_marginCallPower.appliedPoint;
            }
        }
    
        /*
         * �����~���`�F�b�N
         */
        //�����~�敪 == true �܂��́A���̑����i���t�]�͋敪 == true �̏ꍇ
        if(this.calcCondition.isTradingStop() == true
                || this.calcCondition.isOtherTradingStop() == true)
        {
            l_bdEquityTradingPower = new BigDecimal(-1.0);
        }

        /*
         * �]�͌v�Z���ʃI�u�W�F�N�g�Ɍv�Z���ʂ��Z�b�g���ԋp����B
         */
        //�]�͌v�Z���ʂɌv�Z���ʂ�������B
        WEB3TPCalcResult l_calcResult = new WEB3TPCalcResult();
        l_calcResult.appliedPoint = l_intAppliedPoint;
        l_calcResult.tradingPower = l_bdEquityTradingPower.doubleValue();
        l_calcResult.orderTypeEnum = OrderTypeEnum.EQUITY_BUY;
    
        //�]�͌v�Z���ʂ�ԋp����B
        return l_calcResult;
    }

    /**
     * �icalc�K�p�������t�\�z<���������Ώۖ���>�j<BR>
     * <BR>
     * �ŏ��́u�������t�\�z�v���v�Z����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
     */
    public WEB3TPCalcResult calcAppliedEquityTradingPowerTodayDepFund()
    {
        /*
         * �]�͌v�Z���<����/�M�p����>�ȍ~��
         * �ŏ��̊������t�\�z�ƓK�p�����擾����B
         */
        //�������t�\�z�i�ŏ��l�����߂����̂�double�^�̍ő�l�������l�ɃZ�b�g�j
        double l_dblEquityTradingPower = Double.MAX_VALUE;
        //�������t�\�z(BigDecimal�^)
        BigDecimal l_bdEquityTradingPower = new BigDecimal(l_dblEquityTradingPower);
        //�K�p��
        int l_intAppliedPoint = 0;
    
        //�]�͌v�Z���<�������t�^�M�p����>
        int l_intBasePoint = this.calcCondition.getEquityBasePoint();
    
        //�w����͈̔́iT+0�`�]�͌v�Z���<�������t�^�M�p����>-1�j�Ń��[�v
        for (int index = WEB3TPSpecifiedPointDef.T_0; index <= l_intBasePoint-1; index++)
        {
            //�������t�\�z(����)(BigDecimal�^)
            BigDecimal l_bdCurEquityTradingPower;
    
            //���Ԃ�T+0�Ɠ�������            
            if (index == WEB3TPSpecifiedPointDef.T_0)
            {
                //�������t�\�z���v�Z����B
                l_bdCurEquityTradingPower = new BigDecimal(this.calcEquityTradingPower(index));
            }
            //����ȊO
            else
            {
                //�������t�\�z<���v��S�����l��>���v�Z����B
                l_bdCurEquityTradingPower =
                    new BigDecimal(this.calcEquityTradingPowerIncDayTrade(index));
            }
    
            //�������t�\�z(����)���������t�\�z��菬������
            if (l_bdCurEquityTradingPower.compareTo(l_bdEquityTradingPower) < 0)
            {
                //�������t�\�z�Ɋ������t�\�z(����)��������B
                l_bdEquityTradingPower = l_bdCurEquityTradingPower;
                //�K�p���ɍ��Ԃ�������B
                l_intAppliedPoint = index;
            }
        }
    
        //�w����͈̔́i�]�͌v�Z���<�������t�^�M�p����>�`T+5�j�Ń��[�v
        for (int index = l_intBasePoint; index <= WEB3TPSpecifiedPointDef.T_5; index++)
        {
            //�������t�\�z(����)(BigDecimal�^)
            BigDecimal l_bdCurEquityTradingPower;
    
            //���Ԃ��]�͌v�Z���<�������t�^�M�p����>�Ɠ�������            
            if (index == l_intBasePoint)
            {
                //�������t�\�z���v�Z����B
                l_bdCurEquityTradingPower = new BigDecimal(this.calcEquityTradingPower(index));
            }
            //����ȊO
            else
            {
                //�������t�\�z<���v��S�����l��>���v�Z����B
                l_bdCurEquityTradingPower =
                    new BigDecimal(this.calcEquityTradingPowerIncDayTrade(index));
            }
    
            //�������t�\�z(����)���������t�\�z��菬������
            if (l_bdCurEquityTradingPower.compareTo(l_bdEquityTradingPower) < 0)
            {
                //�������t�\�z�Ɋ������t�\�z(����)��������B
                l_bdEquityTradingPower = l_bdCurEquityTradingPower;
                //�K�p���ɍ��Ԃ�������B
                l_intAppliedPoint = index;
            }
        }
    
        //�ma. �������t�\�z�@@< 0�̏ꍇ�n
        if (l_bdEquityTradingPower.compareTo(new BigDecimal(0.0)) < 0)
        {
            l_bdEquityTradingPower = new BigDecimal(-1.0);
        }
        

        /*
         * �a��������]�̓`�F�b�N
         */
        //�a��������]�́i�ŏ��l�����߂����̂�double�^�̍ő�l�������l�ɃZ�b�g�j
        double l_dblActualPaymentBalance = Double.MAX_VALUE;
        //�a��������]��(BigDecimal�^)
        BigDecimal l_bdActualPaymentBalance = new BigDecimal(l_dblActualPaymentBalance);
        //�K�p��<�a��������]��>
        int l_intActPayAppliedPoint = 0;

        //�w����͈̔́iT+0�`�]�͌v�Z���<�������t�^�M�p����>-1�j�Ń��[�v
        for(int index = WEB3TPSpecifiedPointDef.T_0; index < l_intBasePoint; index++)
        {
            //�a��������]��(����)(BigDecimal�^)
            BigDecimal l_bdCurActualPaymentBalance = new BigDecimal(this
                .calcAccountBalanceDemandPower(index));

            //�a��������]��(����)���a��������]�͂�菬������
            if(l_bdCurActualPaymentBalance.compareTo(l_bdActualPaymentBalance) < 0)
            {
                //�a��������]�͂ɗa��������]��(����)��������B
                l_bdActualPaymentBalance = l_bdCurActualPaymentBalance;
                //�K�p���ɍ��Ԃ�������B
                l_intActPayAppliedPoint = index;
            }
        }

        //�ma. �a��������]�� < 0�̏ꍇ�n
        if(l_bdActualPaymentBalance.compareTo(new BigDecimal(0.0)) < 0)
        {
            //[b.�������t�\�z >= 0 �̏ꍇ]
            if(l_bdEquityTradingPower.compareTo(new BigDecimal(0.0)) >= 0)
            {
                //�������t�\�z = -1
                l_bdEquityTradingPower = new BigDecimal(-1.0);
                //�K�p�� = �K�p��<�a��������]��>
                l_intAppliedPoint = l_intActPayAppliedPoint;
            }
            //[b.�������t�\�z < 0 ���� �K�p�� > �K�p��<�a��������]��> �̏ꍇ]
            else if(l_bdEquityTradingPower.compareTo(new BigDecimal(0.0)) < 0
                    && l_intAppliedPoint > l_intActPayAppliedPoint)
            {
                //�������t�\�z = -1
                l_bdEquityTradingPower = new BigDecimal(-1.0);
                //�K�p�� = �K�p��<�a��������]��>
                l_intAppliedPoint = l_intActPayAppliedPoint;
            }
        }
        
        /*
         * �Ǐؗ]�̓`�F�b�N
         */
        //������<�����^�M�p>
        int l_intBizDate = this.calcCondition.getEquityBizDate();

        //�Ǐؗ]��<�K�p�\�z�`�F�b�N�p>���擾
        WEB3TPCalcResult l_marginCallPower = this.calcMarginCallPowerForCheck(
                OrderTypeEnum.EQUITY_BUY, l_intBizDate, l_intBasePoint);

        //�ma. �Ǐؗ]�� < 0�̏ꍇ�n
        if(l_marginCallPower != null && l_marginCallPower.tradingPower < 0)
        {
            //[b.�������t�\�z >= 0 �̏ꍇ]
            if(l_bdEquityTradingPower.compareTo(new BigDecimal(0.0)) >= 0)
            {
                //�������t�\�z = -1
                l_bdEquityTradingPower = new BigDecimal(-1.0);
                //�K�p�� = �K�p��<�Ǐؗ]��>
                l_intAppliedPoint = l_marginCallPower.appliedPoint;
            }
            //[b.�������t�\�z < 0 ���� �K�p�� > �K�p��<�Ǐؗ]��> �̏ꍇ]
            else if(l_bdEquityTradingPower.compareTo(new BigDecimal(0.0)) < 0
                    && l_intAppliedPoint > l_marginCallPower.appliedPoint)
            {
                //�������t�\�z = -1
                l_bdEquityTradingPower = new BigDecimal(-1.0);
                //�K�p�� = �K�p��<�Ǐؗ]��>
                l_intAppliedPoint = l_marginCallPower.appliedPoint;
            }
        }
    
        /*
         * �����~���`�F�b�N
         */
        //�����~�敪 == true �܂��́A���̑����i���t�]�͋敪 == true �̏ꍇ
        if(this.calcCondition.isTradingStop() == true
                || this.calcCondition.isOtherTradingStop() == true)
        {
            l_bdEquityTradingPower = new BigDecimal(-1.0);
        }

        /*
         * �]�͌v�Z���ʃI�u�W�F�N�g�Ɍv�Z���ʂ��Z�b�g���ԋp����B
         */
        //�]�͌v�Z���ʂɌv�Z���ʂ�������B
        WEB3TPCalcResult l_calcResult = new WEB3TPCalcResult();
        l_calcResult.appliedPoint = l_intAppliedPoint;
        l_calcResult.tradingPower = l_bdEquityTradingPower.doubleValue();
        l_calcResult.orderTypeEnum = OrderTypeEnum.EQUITY_BUY;
    
        //�]�͌v�Z���ʂ�ԋp����B
        return l_calcResult;
    }

    /**
     * �icalc�K�p�������t�\�z�j<BR>
     * <BR>
     * ����.����ȍ~�ŏ��́u�������t�\�z�v���v�Z����B<BR>
     * <BR>
     * �P�j�]�͌v�Z���<��������/�M�p����>��ޔ�����B<BR>
     * �@@�|this.�]�͌v�Z����.get�]�͌v�Z���<��������/�M�p����>()���R�[��<BR>
     * <BR>
     * �Q�j�]�͌v�Z���<��������/�M�p����>�ɁA����.������Z�b�g����B<BR>
     * �@@�|this.�]�͌v�Z����.set�]�͌v�Z���<��������/�M�p����>()���R�[��<BR>
     * <BR>
     * �@@�m�����n<BR>
     * �@@�@@int�F����.���<BR>
     * <BR>
     * �R�j����.����ȍ~�A�ŏ��̊������t�\�z���v�Z����<BR>
     * �@@�|this.calc�K�p�������t�\�z()���R�[��<BR>
     * <BR>
     * �S�j�ޔ����Ă����A�����߂�<BR>
     * �@@�|this.�]�͌v�Z����.set�]�͌v�Z���<��������/�M�p����>()���R�[��<BR>
     * <BR>
     * �@@�m�����n<BR>
     * �@@�@@int�F�P�j�̖߂�l<BR>
     * <BR>
     * �T�j�R�j�̖߂�l��ԋp����B<BR>
     * <BR>
     * @@param l_intBasePoint<BR>
     * @@return WEB3TPCalcResult
     */
    public WEB3TPCalcResult calcAppliedEquityTradingPower(int l_intBasePoint)
    {
        //����.�����T+0��菬������
        if (l_intBasePoint < WEB3TPSpecifiedPointDef.T_0)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcAppliedEquityTradingPower(int)");
        }
        
        //�]�͌v�Z���<��������/�M�p����>��ޔ�����B
        int l_intOriginalBasePoint = this.calcCondition.getEquityBasePoint();

        /*
         * �]�͌v�Z���<��������/�M�p����>�ɁA����.������Z�b�g����
         */
        //����.�����T+5���傫���ꍇ
        if(l_intBasePoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //T+5���Z�b�g
            this.calcCondition.setEquityBasePoint(WEB3TPSpecifiedPointDef.T_5);
        }
        //�ȊO�̏ꍇ
        else
        {
            //����.������Z�b�g
            this.calcCondition.setEquityBasePoint(l_intBasePoint);
        }

        //����.����ȍ~�A�ŏ��̊������t�\�z���v�Z����
        WEB3TPCalcResult l_result = this.calcAppliedEquityTradingPower();

        //�ޔ����Ă����A�����߂��B
        this.calcCondition.setEquityBasePoint(l_intOriginalBasePoint);

        //�v�Z���ʂ�ԋp
        return l_result;
    }

    /**
     * �icalc�K�p�M�p�V�K���\�z�j<BR>
     * 
     * �ŏ��́u�M�p�V�K���\�z�v���v�Z����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
     * @@return WEB3TPCalcResult
     * @@roseuid 40BFFA8B03D2
     */
    public WEB3TPCalcResult calcAppliedMarginTradingPower()
    {
        /*
         * �M�p�V�K���\�z���擾����B
         */
        //�M�p�V�K���\�z�i�ŏ��l�����߂����̂�double�^�̍ő�l�������l�ɃZ�b�g�j
        double l_dblMarginTradingPower = Double.MAX_VALUE;
        //�M�p�V�K���\�z(BigDecimal�^)
        BigDecimal l_bdMarginTradingPower = new BigDecimal(l_dblMarginTradingPower);
        //�K�p��
        int l_intAppliedPoint = 0;

        //��� = �]�͌v�Z���<�M�p�V�K��>
        int l_intBasePoint = this.calcCondition.getMarginBasePoint();

        //�w����͈̔́i�]�͌v�Z���<�M�p�V�K��>�`T+5�j�Ń��[�v
        for (int index = l_intBasePoint; index <= WEB3TPSpecifiedPointDef.T_5; index++)
        {
            //�M�p�V�K���\�z(����)(BigDecimal�^)
            BigDecimal l_bdCurMarginTradingPower =
                new BigDecimal(this.calcMarginTradingPower(index));
    
            //�M�p�V�K���\�z(����)���M�p�V�K���\�z��菬������
            if (l_bdCurMarginTradingPower.compareTo(l_bdMarginTradingPower) < 0)
            {
                //�M�p�V�K���\�z�ɐM�p�V�K���\�z(����)����
                l_bdMarginTradingPower = l_bdCurMarginTradingPower;
                //�K�p���ɍ��Ԃ���
                l_intAppliedPoint = index;
            }
        }
    
        /*
         * �M�p�V�K���\�z�𑍌��ʑ������l�͈͓̔��ɒ�������
         */
        //�����ʏ���l�i=�����ʑ������l - �����ό��ʑ��(�K�p��)�j���v�Z����B
        BigDecimal l_bdMaxContPrice =
            new BigDecimal(
                this.calcCondition.getMaxContPrice()
                    - this.getContractAmount(l_intAppliedPoint));
    
        //�M�p�V�K���\�z�ɐM�p�V�K���\�z�Ƒ����ʏ���l�̏�����������
        l_bdMarginTradingPower = l_bdMarginTradingPower.min(l_bdMaxContPrice);
    
        /*
         * �����ۏ؋��A����ۏ؋������ꂼ���l�𖞂����Ă��邩�ǂ����̔��ʂ��s��
         */
        //�]�͌v�Z���<�M�p�V�K��> >= T+1 �ꍇ
        if (this.calcCondition.getMarginBasePoint() >= WEB3TPSpecifiedPointDef.T_1)
        {

            //�����ۏ؋�(�]�͌v�Z���<������>)
            BigDecimal l_bdPaidMarginDeposit =
                new BigDecimal(
                    this.calcPaidMarginDeposit(this.calcCondition.getEquityBizDate()));

            //�Œ�K�v�ۏ؋�
            BigDecimal l_bdMinMarginDeposit =
                new BigDecimal(this.calcCondition.getMinMarginDeposit());

            //����ۏ؋�(�]�͌v�Z���<������>)
            BigDecimal l_bdReceiptMarginDeposit =
                new BigDecimal(
                    this.calcReceiptMarginDeposit(this.calcCondition.getEquityBizDate()));

            //�@@��Œ�K�v�ۏ؋�
            BigDecimal l_bdLegalMinMarginDeposit =
                new BigDecimal(this.calcCondition.getLegalMinMarginDeposit());

            //�����ۏ؋����Œ�K�v�ۏ؋���菬�����@@�܂��́@@����ۏ؋����@@��Œ�K�v�ۏ؋���菬������
            if (l_bdPaidMarginDeposit.compareTo(l_bdMinMarginDeposit) < 0
                || l_bdReceiptMarginDeposit.compareTo(l_bdLegalMinMarginDeposit) < 0)
            {
                //�M�p�V�K���\�z��-1����
                l_bdMarginTradingPower = new BigDecimal(-1.0);
                //�K�p�� = �K�p��<�M�p�V�K��>
                l_intAppliedPoint = this.calcCondition.getEquityBizDate();
            }
        }
    
        //�M�p�V�K���\�z��0��菬������
        if (l_bdMarginTradingPower.compareTo(new BigDecimal(0.0)) < 0)
        {
            //�M�p�V�K���\�z��-1����
            l_bdMarginTradingPower = new BigDecimal(-1.0);
        }

        /*
         * �a��������]�̓`�F�b�N
         */
        //������<�����^�M�p>
        int l_intBizDate = this.calcCondition.getEquityBizDate();

        //�a��������]��<�K�p�\�z�`�F�b�N�p>���擾
        WEB3TPCalcResult l_marginActPay = this
            .calcAccountBalanceDemandPowerForCheck(
                    OrderTypeEnum.MARGIN_LONG, l_intBizDate, l_intBasePoint);

        //�ma.�a��������]�� < 0�̏ꍇ�n
        if(l_marginActPay != null && l_marginActPay.tradingPower < 0)
        {
            //[b.�M�p�V�K���\�z >= 0 �̏ꍇ]
            if(l_bdMarginTradingPower.compareTo(new BigDecimal(0.0)) >= 0)
            {
                //�M�p�V�K���\�z = -1
                l_bdMarginTradingPower = new BigDecimal(-1.0);
                //�K�p�� = �K�p��<�a��������]��>
                l_intAppliedPoint = l_marginActPay.appliedPoint;
            }
            //[b.�M�p�V�K���\�z < 0 ���� �K�p�� > �K�p��<�a��������]��> �̏ꍇ]
            else if(l_bdMarginTradingPower.compareTo(new BigDecimal(0.0)) < 0
                    && l_intAppliedPoint > l_marginActPay.appliedPoint)
            {
                //�M�p�V�K���\�z = -1
                l_bdMarginTradingPower = new BigDecimal(-1.0);
                //�K�p�� = �K�p��<�a��������]��>
                l_intAppliedPoint = l_marginActPay.appliedPoint;
            }
        }

        /*
         * �Ǐؗ]�̓`�F�b�N
         */
        //�Ǐؗ]��<�K�p�\�z�`�F�b�N�p>���擾
        WEB3TPCalcResult l_marginCallPower = this.calcMarginCallPowerForCheck(
                OrderTypeEnum.MARGIN_LONG, l_intBizDate, l_intBasePoint);

        //�ma. �Ǐؗ]�� < 0�̏ꍇ�n
        if(l_marginCallPower != null && l_marginCallPower.tradingPower < 0)
        {
            //[b.�M�p�V�K���\�z >= 0 �̏ꍇ]
            if(l_bdMarginTradingPower.compareTo(new BigDecimal(0.0)) >= 0)
            {
                //�M�p�V�K���\�z = -1
                l_bdMarginTradingPower = new BigDecimal(-1.0);
                //�K�p�� = �K�p��<�Ǐؗ]��>
                l_intAppliedPoint = l_marginCallPower.appliedPoint;
            }
            //[b.�M�p�V�K���\�z < 0 ���� �K�p�� > �K�p��<�Ǐؗ]��> �̏ꍇ]
            else if(l_bdMarginTradingPower.compareTo(new BigDecimal(0.0)) < 0
                    && l_intAppliedPoint > l_marginCallPower.appliedPoint)
            {
                //�M�p�V�K���\�z = -1
                l_bdMarginTradingPower = new BigDecimal(-1.0);
                //�K�p�� = �K�p��<�Ǐؗ]��>
                l_intAppliedPoint = l_marginCallPower.appliedPoint;
            }
        }
        
        /*
         * �����~���`�F�b�N
         */
        //�����~�敪 == true �܂��́A�M�p�V�K���]�͋敪 == true�@@�̏ꍇ
        if(this.calcCondition.isTradingStop() == true
                || this.calcCondition.isMarginOpenPositionStop() == true)
        {
            //�M�p�V�K���\�z��-1����
            l_bdMarginTradingPower = new BigDecimal(-1.0);
        }
        
        /*
         * �]�͌v�Z���ʃI�u�W�F�N�g�Ɍv�Z���ʂ��Z�b�g���ԋp����B
         */
        //�]�͌v�Z���ʂɌv�Z���ʂ�������B
        WEB3TPCalcResult l_calcResult = new WEB3TPCalcResult();
        l_calcResult.appliedPoint = l_intAppliedPoint;
        l_calcResult.tradingPower = l_bdMarginTradingPower.doubleValue();
        l_calcResult.orderTypeEnum = OrderTypeEnum.MARGIN_LONG;
    
        //�]�͌v�Z���ʂ�ԋp����B
        return l_calcResult;
    }

    /**
     * �icalc�K�p�M�p�V�K���\�z�j<BR>
     * <BR>
     * ����.����ȍ~�ŏ��́u�M�p�V�K���\�z�v���v�Z����B<BR>
     * <BR>
     * �P�j�]�͌v�Z���<�M�p�V�K��>��ޔ�����B<BR>
     * �@@�|this.�]�͌v�Z����.get�]�͌v�Z���<�M�p�V�K��>()���R�[��<BR>
     * <BR>
     * �Q�j�]�͌v�Z���<�M�p�V�K��>�ɁA����.������Z�b�g����B<BR>
     * �@@�|this.�]�͌v�Z����.set�]�͌v�Z���<�M�p�V�K��>()���R�[��<BR>
     * <BR>
     * �@@�m�����n<BR>
     * �@@�@@int�F����.���<BR>
     * <BR>
     * �R�j����.����ȍ~�A�ŏ��̐M�p�V�K���\�z���v�Z����<BR>
     * �@@�|this.calc�K�p�M�p�V�K���\�z()���R�[��<BR>
     * <BR>
     * �S�j�ޔ����Ă����A�����߂�<BR>
     * �@@�|this.�]�͌v�Z����.set�]�͌v�Z���<�M�p�V�K��>()���R�[��<BR>
     * <BR>
     * �@@�m�����n<BR>
     * �@@�@@int�F�P�j�̖߂�l<BR>
     * <BR>
     * �T�j�R�j�̖߂�l��ԋp����B<BR>
     * <BR>
     * @@param l_intBasePoint<BR>
     * @@return WEB3TPCalcResult
     */
    public WEB3TPCalcResult calcAppliedMarginTradingPower(int l_intBasePoint)
    {
        //����.�����T+0��菬������
        if (l_intBasePoint < WEB3TPSpecifiedPointDef.T_0)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcAppliedMarginTradingPower(int)");
        }
        
        //�]�͌v�Z���<�M�p�V�K��>��ޔ�����B
        int l_intOriginalBasePoint = this.calcCondition.getMarginBasePoint();

        /*
         * �]�͌v�Z���<�M�p�V�K��>�ɁA����.������Z�b�g����
         */
        //����.�����T+5���傫���ꍇ
        if(l_intBasePoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //T+5���Z�b�g
            this.calcCondition.setMarginBasePoint(WEB3TPSpecifiedPointDef.T_5);
        }
        //�ȊO�̏ꍇ
        else
        {
            //����.������Z�b�g
            this.calcCondition.setMarginBasePoint(l_intBasePoint);
        }

        //����.����ȍ~�A�ŏ��̐M�p�V�K�����t�\�z���v�Z����
        WEB3TPCalcResult l_result = this.calcAppliedMarginTradingPower();

        //�ޔ����Ă����A�����߂��B
        this.calcCondition.setMarginBasePoint(l_intOriginalBasePoint);

        //�v�Z���ʂ�ԋp
        return l_result;
    }

    /**
     * (calc�K�p�M�p�V�K���\�z<���S�ۋK������>)<BR>
     * ����.����ȍ~�ŏ��́u(���S��)�K���M�p�V�K���\�z�v���v�Z����B<BR>
     * <BR>
     * �P�j(���X)�ۏ؋���/(���X)�����ۏ؋�����ޔ�����B<BR>
     * �@@�|this.�]�͌v�Z����.get�ۏ؋���()���R�[��<BR>
     * �@@�|this.�]�͌v�Z����.get�����ۏ؋���()���R�[��<BR>
     * <BR>
     * �Q�j�w�肳�ꂽ�ۏ؋���/�����ۏ؋�����]�͌v�Z�����ɃZ�b�g����B<BR>
     * �@@�|this.�]�͌v�Z����.set�ۏ؋���(����.�ۏ؋���:int)���R�[��<BR>
     * �@@�|this.�]�͌v�Z����.set�����ۏ؋���(����.�����ۏ؋���:int)���R�[��<BR>
     * <BR>
     * �R�j�ŏ��̐M�p�V�K���\�z���v�Z����<BR>
     * �@@�|this.calc�K�p�M�p�V�K���\�z()���R�[��<BR>
     * <BR>
     * �S�j�ޔ����Ă����A(���X)�ۏ؋���/(���X)�����ۏ؋�����߂�<BR>
     * �@@�|this.�]�͌v�Z����.set�ۏ؋���(�P�j��(���X)�ۏ؋���:int)���R�[��<BR>
     * �@@�|this.�]�͌v�Z����.set�����ۏ؋���(�P�j��(���X)�����ۏ؋���:int)���R�[��<BR>
     * <BR>
     * �T�j�R�j�̖߂�l��ԋp����B<BR>
     * <BR>
     * @@param l_intMarginDepRate
     * @@param l_intCashMarginDepRate
     * @@return WEB3TPCalcResult
     */
    public WEB3TPCalcResult calcAppliedMarginTradingPowerIncDeposit(
        int l_intMarginDepRate,
        int l_intCashMarginDepRate)
    {
        /*
         * (���X)�ۏ؋���/(���X)�����ۏ؋�����ޔ�����
         */
        //(���X)�ۏ؋���
        int l_intOriginalMarginDepRate = this.calcCondition.getMarginDepositRate();
        //(���X)�����ۏ؋���
        int l_intOriginalCashMarginDepRate = this.calcCondition.getCashMarginDepositRate();

        /*
         * �w�肳�ꂽ�ۏ؋���/�����ۏ؋�����]�͌v�Z�����ɃZ�b�g����B
         */
        this.calcCondition.setMarginDepositRate(l_intMarginDepRate);
        this.calcCondition.setCashMarginDepositRate(l_intCashMarginDepRate);

        /*
         * �ŏ��̐M�p�V�K���\�z���v�Z����
         */
        WEB3TPCalcResult l_result = this.calcAppliedMarginTradingPower();

        /*
         * �ޔ����Ă����A(���X)�ۏ؋���/(���X)�����ۏ؋�����߂�
         */
        this.calcCondition.setMarginDepositRate(l_intOriginalMarginDepRate);
        this.calcCondition.setCashMarginDepositRate(l_intOriginalCashMarginDepRate);

        //�v�Z���ʂ�ԋp
        return l_result;
    }
    
    /**
     * �icalc�K�p�M�p�����\�z�j<BR>
     * 
     * �ŏ��́u�M�p�����\�z�v���v�Z����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
     * @@return WEB3TPCalcResult
     * @@roseuid 40C4365C031C
     * 
     */
    public WEB3TPCalcResult calcAppliedActualReceiptTradingPower()
    {
        /*
         * �M�p�����\�z���擾����B
         */
        //�M�p�����\�z�i�ŏ��l�����߂����̂�double�^�̍ő�l�������l�ɃZ�b�g�j
        double l_dblActualReceiptTradingPower = Double.MAX_VALUE;
        //�M�p�����\�z(BigDecimal�^)
        BigDecimal l_bdActualReceiptTradingPower = new BigDecimal(l_dblActualReceiptTradingPower);
        //�K�p��
        int l_intAppliedPoint = 0;
    
        //�]�͌v�Z���<�������t/�M�p����>
        int l_intBasePoint = this.calcCondition.getEquityBasePoint();
    
        //�w����͈̔́i�]�͌v�Z���<�������t�^�M�p����>�`T+5�j�Ń��[�v
        for (int index = l_intBasePoint; index <= WEB3TPSpecifiedPointDef.T_5; index++)
        {
            //�M�p�����\�z(����)(BigDecimal�^)
            BigDecimal l_bdCurActualReceiptTradingPower;
    
            //���Ԃ��]�͌v�Z���<�������t�^�M�p����>�Ɠ�������            
            if (index == l_intBasePoint)
            {
                //�M�p�����\�z���擾
                l_bdCurActualReceiptTradingPower =
                    new BigDecimal(this.calcActualReceiptTradingPower(index));
            }
            //����ȊO
            else
            {
                //�M�p�����\�z<���v��S�����l��>���擾
                l_bdCurActualReceiptTradingPower =
                    new BigDecimal(this.calcActualReceiptTradingPowerIncDayTrade(index));
            }
    
            //�M�p�����\�z(����)���M�p�����\�z��菬������
            if (l_bdCurActualReceiptTradingPower.compareTo(l_bdActualReceiptTradingPower) < 0)
            {
                //�M�p�����\�z�ɐM�p�����\�z(����)����
                l_bdActualReceiptTradingPower = l_bdCurActualReceiptTradingPower;
                //�K�p���ɍ��Ԃ���
                l_intAppliedPoint = index;
            }
        }
    
        //�ma. �M�p�����\�z�@@< 0�̏ꍇ�n
        if (l_bdActualReceiptTradingPower.compareTo(new BigDecimal(0.0)) < 0)
        {
            l_bdActualReceiptTradingPower = new BigDecimal(-1.0);
        }

        /*
         * �a��������]�̓`�F�b�N
         */
        //�a��������]�́i�ŏ��l�����߂����̂�double�^�̍ő�l�������l�ɃZ�b�g�j
        double l_dblActualPaymentBalance = Double.MAX_VALUE;
        //�a��������]��(BigDecimal�^)
        BigDecimal l_bdActualPaymentBalance = new BigDecimal(
                l_dblActualPaymentBalance);
        //�K�p��<�a��������]��>
        int l_intActPayAppliedPoint = 0;

        //�w����͈̔́iT+0�`�]�͌v�Z���<�������t�^�M�p����>-1�j�Ń��[�v
        for(int index = WEB3TPSpecifiedPointDef.T_0; index < l_intBasePoint; index++)
        {
            //�a��������]��(����)(BigDecimal�^)
            BigDecimal l_bdCurActualPaymentBalance = new BigDecimal(this
                .calcAccountBalanceDemandPower(index));

            //�a��������]��(����)���a��������]�͂�菬������
            if(l_bdCurActualPaymentBalance.compareTo(l_bdActualPaymentBalance) < 0)
            {
                //�a��������]�͂ɗa��������]��(����)��������B
                l_bdActualPaymentBalance = l_bdCurActualPaymentBalance;
                //�K�p���ɍ��Ԃ�������B
                l_intActPayAppliedPoint = index;
            }
        }

        //�ma. �a��������]�� < 0�̏ꍇ�n
        if(l_bdActualPaymentBalance.compareTo(new BigDecimal(0.0)) < 0)
        {
            //[b.�M�p�����\�z >= 0 �̏ꍇ]
            if(l_bdActualReceiptTradingPower.compareTo(new BigDecimal(0.0)) >= 0)
            {
                //�M�p�����\�z = -1
                l_bdActualReceiptTradingPower = new BigDecimal(-1.0);
                //�K�p�� = �K�p��<�a��������]��>
                l_intAppliedPoint = l_intActPayAppliedPoint;
            }
            //[b.�M�p�����\�z < 0 ���� �K�p�� > �K�p��<�a��������]��> �̏ꍇ]
            else if(l_bdActualReceiptTradingPower.compareTo(new BigDecimal(0.0)) < 0
                    && l_intAppliedPoint > l_intActPayAppliedPoint)
            {
                //�M�p�����\�z = -1
                l_bdActualReceiptTradingPower = new BigDecimal(-1.0);
                //�K�p�� = �K�p��<�a��������]��>
                l_intAppliedPoint = l_intActPayAppliedPoint;
            }
        }
        
        /*
         * �Ǐؗ]�̓`�F�b�N
         */
        //������<�����^�M�p>
        int l_intBizDate = this.calcCondition.getEquityBizDate();

        //�Ǐؗ]��<�K�p�\�z�`�F�b�N�p>���擾
        WEB3TPCalcResult l_marginCallPower = this.calcMarginCallPowerForCheck(
                OrderTypeEnum.SWAP_MARGIN_LONG, l_intBizDate, l_intBasePoint);

        //�ma. �Ǐؗ]�� < 0�̏ꍇ�n
        if(l_marginCallPower != null && l_marginCallPower.tradingPower < 0)
        {
            //[b.�M�p�����\�z >= 0 �̏ꍇ]
            if(l_bdActualReceiptTradingPower.compareTo(new BigDecimal(0.0)) >= 0)
            {
                //�M�p�����\�z = -1
                l_bdActualReceiptTradingPower = new BigDecimal(-1.0);
                //�K�p�� = �K�p��<�Ǐؗ]��>
                l_intAppliedPoint = l_marginCallPower.appliedPoint;
            }
            //[b.�M�p�����\�z < 0 ���� �K�p�� > �K�p��<�Ǐؗ]��> �̏ꍇ]
            else if(l_bdActualReceiptTradingPower.compareTo(new BigDecimal(0.0)) < 0
                    && l_intAppliedPoint > l_marginCallPower.appliedPoint)
            {
                //�M�p�����\�z = -1
                l_bdActualReceiptTradingPower = new BigDecimal(-1.0);
                //�K�p�� = �K�p��<�Ǐؗ]��>
                l_intAppliedPoint = l_marginCallPower.appliedPoint;
            }
        }
    
        /*
         * �����~���`�F�b�N
         */
        //�����~�敪 == true �܂��́A���̑����i���t�]�͋敪 == true�@@�̏ꍇ
        if(this.calcCondition.isTradingStop() == true
                || this.calcCondition.isOtherTradingStop() == true)
        {
            //�M�p�����\�z��-1����
            l_bdActualReceiptTradingPower = new BigDecimal(-1.0);
        }

        /*
         * �]�͌v�Z���ʃI�u�W�F�N�g�Ɍv�Z���ʂ��Z�b�g���ԋp����B
         */
        //�]�͌v�Z���ʂɌv�Z���ʂ�������B
        WEB3TPCalcResult l_calcResult = new WEB3TPCalcResult();
        l_calcResult.appliedPoint = l_intAppliedPoint;
        l_calcResult.tradingPower = l_bdActualReceiptTradingPower.doubleValue();
        l_calcResult.orderTypeEnum = OrderTypeEnum.SWAP_MARGIN_LONG;
    
        //�]�͌v�Z���ʂ�ԋp����B
        return l_calcResult;
    }

    /**
     * �icalc�K�p�M�p�����\�z�j<BR>
     * <BR>
     * ����.����ȍ~�ŏ��́u�M�p�����\�z�v���v�Z����B<BR>
     * <BR>
     * �P�j�]�͌v�Z���<��������/�M�p����>��ޔ�����B<BR>
     * �@@�|this.�]�͌v�Z����.get�]�͌v�Z���<��������/�M�p����>()���R�[��<BR>
     * <BR>
     * �Q�j�]�͌v�Z���<��������/�M�p����>�ɁA����.������Z�b�g����B<BR>
     * �@@�|this.�]�͌v�Z����.set�]�͌v�Z���<��������/�M�p����>()���R�[��<BR>
     * <BR>
     * �@@�m�����n<BR>
     * �@@�@@int�F����.���<BR>
     * <BR>
     * �R�j����.����ȍ~�A�ŏ��̐M�p�����\�z���v�Z����<BR>
     * �@@�|this.calc�K�p�M�p�����\�z()���R�[��<BR>
     * <BR>
     * �S�j�ޔ����Ă����A�����߂�<BR>
     * �@@�|this.�]�͌v�Z����.set�]�͌v�Z���<��������/�M�p����>()���R�[��<BR>
     * <BR>
     * �@@�m�����n<BR>
     * �@@�@@int�F�P�j�̖߂�l<BR>
     * <BR>
     * �T�j�R�j�̖߂�l��ԋp����B<BR>
     * <BR> 
     * @@param l_intBasePoint<BR>
     * @@return WEB3TPCalcResult
     */
    public WEB3TPCalcResult calcAppliedActualReceiptTradingPower(int l_intBasePoint)
    {
        //����.�����T+0��菬������
        if (l_intBasePoint < WEB3TPSpecifiedPointDef.T_0)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcAppliedActualReceiptTradingPower(int)");
        }
        
        //�]�͌v�Z���<��������/�M�p����>��ޔ�����B
        int l_intOriginalBasePoint = this.calcCondition.getEquityBasePoint();

        /*
         * �]�͌v�Z���<��������/�M�p����>�ɁA����.������Z�b�g����
         */
        //����.�����T+5���傫���ꍇ
        if(l_intBasePoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //T+5���Z�b�g
            this.calcCondition.setEquityBasePoint(WEB3TPSpecifiedPointDef.T_5);
        }
        //�ȊO�̏ꍇ
        else
        {
            //����.������Z�b�g
            this.calcCondition.setEquityBasePoint(l_intBasePoint);
        }

        //����.����ȍ~�A�ŏ��̐M�p�������t�\�z���v�Z����
        WEB3TPCalcResult l_result = this.calcAppliedActualReceiptTradingPower();

        //�ޔ����Ă����A�����߂��B
        this.calcCondition.setEquityBasePoint(l_intOriginalBasePoint);

        //�v�Z���ʂ�ԋp
        return l_result;
    }

    /**
     * �icalc�K�p���̑����i���t�\�z�j<BR>
     * 
     * ������ʂŎw�肳�ꂽ�ŏ��́u���̑����i���t�\�z�v���v�Z����B<BR>
     * ������ʂ��w�肳��Ȃ��������i�������=null�j�A�������=OrderTypeEnum.���̑��Ƃ�
     * ��B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
     * @@param l_orderTypeEnum - �i������ʁj
     * OrderTypeEnum�ɂĒ�`
     * @@return WEB3TPCalcResult
     * @@roseuid 40CD5AC9001E
     */
    public WEB3TPCalcResult calcAppliedOtherTradingPower(OrderTypeEnum l_orderTypeEnum)
    {
        /*
         * �]�͌v�Z�����ݒ肷��B
         */
        //�]�͌v�Z���
        int l_intBasePoint;
    
        //�p�����[�^.������ʂ�null�A�؋����ւ̐U�ցA�ב֕ۏ؋��ւ̐U�ցA
        //�O�������ւ̐U�ցA�܂��́@@CFD�U�֒����i�a�������CFD����) �̎�
        if (l_orderTypeEnum == null
                || l_orderTypeEnum.equals(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN) == true
                || l_orderTypeEnum.equals(OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT) == true
                || l_orderTypeEnum.equals(OrderTypeEnum.TRANSFER_TO_FEQ) == true
                || l_orderTypeEnum.equals(OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT) == true)
        {
            //�]�͌v�Z����ɗ]�͌v�Z���<���̑����t>����
            l_intBasePoint = this.calcCondition.getOtherBasePoint();
        }
        //�p�����[�^.������ʂ����M�����̎�        
        else if (l_orderTypeEnum.equals(OrderTypeEnum.MF_BUY) == true
                  || l_orderTypeEnum.equals(OrderTypeEnum.MF_RECRUIT) == true
                  || l_orderTypeEnum.equals(OrderTypeEnum.MF_SWITCHING) == true)
        {
            //�]�͌v�Z����ɗ]�͌v�Z���<���M>����
            l_intBasePoint = this.calcCondition.getFundBasePoint();
        }
        //�p�����[�^.������ʂ��o�������̎�
        else if (l_orderTypeEnum.equals(OrderTypeEnum.CASH_OUT) == true)
        {
            //�]�͌v�Z����ɗ]�͌v�Z���<�o��>����
            l_intBasePoint = this.calcCondition.getPaymentBasePoint();
        }
        //�p�����[�^.������ʂ��I�v�V�����V�K�����̎�
        else if (l_orderTypeEnum.equals(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN) == true)
        {
            //�]�͌v�Z����ɗ]�͌v�Z���<�I�v�V�����V�K����>����
            l_intBasePoint = this.calcCondition.getOptionBasePoint();
        }
        //����ȊO
        else
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcOtherTradingPower(OrderTypeEnum)");
        }
    
        /*
         * ���̑����i���t�\�z�Ƃ��̓K�p�����擾����B
         */
        //���̑����i���t�\�z�i�ŏ��l�����߂����̂�double�^�̍ő�l�������l�ɃZ�b�g�j
        double l_dblOtherTradingPower = Double.MAX_VALUE;
        //���̑����i���t�\�z(BigDecimal�^)
        BigDecimal l_bdOtherTradingPower = new BigDecimal(l_dblOtherTradingPower);
        //�K�p��
        int l_intAppliedPoint = 0;
    
        //���̑����i���t�\�z(����)(BigDecimal�^)
        BigDecimal l_bdCurOtherTradingPower;
    
        //�w����͈̔́i�]�͌v�Z����`5�j�Ń��[�v
        for (int index = l_intBasePoint; index <= WEB3TPSpecifiedPointDef.T_5; index++)
        {
            //���Ԃ��]�͌v�Z����Ɠ�������            
            if (index == l_intBasePoint)
            {
                //���̑����i���t�\�z(n)���擾����
                l_bdCurOtherTradingPower = new BigDecimal(this.calcOtherTradingPower(index));
            }
            //����ȊO
            else
            {
                //���̑����i���t�\�z<���v��S�����l��>(n)���擾����
                l_bdCurOtherTradingPower =
                    new BigDecimal(this.calcOtherTradingPowerIncDayTrade(index));
            }
    
            //���̑����i���t�\�z(����)�����̑����i���t�\�z��菬������
            if (l_bdCurOtherTradingPower.compareTo(l_bdOtherTradingPower) < 0)
            {
                //���̑����i���t�\�z�ɂ��̑����i���t�\�z(����)��������B
                l_bdOtherTradingPower = l_bdCurOtherTradingPower;
                //�K�p���ɍ��Ԃ�������B
                l_intAppliedPoint = index;
            }
        }
    
        //�ma. ���̑����i���t�\�z�@@< 0�̏ꍇ�n
        if (l_bdOtherTradingPower.compareTo(new BigDecimal(0.0)) < 0)
        {
            l_bdOtherTradingPower = new BigDecimal(-1.0);
        }

        /*
         * �a��������]�̓`�F�b�N
         */
        //�a��������]�́i�ŏ��l�����߂����̂�double�^�̍ő�l�������l�ɃZ�b�g�j
        double l_dblActualPaymentBalance = Double.MAX_VALUE;
        //�a��������]��(BigDecimal�^)
        BigDecimal l_bdActualPaymentBalance = new BigDecimal(l_dblActualPaymentBalance);
        //�K�p��<�a��������]��>
        int l_intActPayAppliedPoint = 0;

        //�w����͈̔́iT+0�`�]�͌v�Z���-1�j�Ń��[�v
        for(int index = WEB3TPSpecifiedPointDef.T_0; index < l_intBasePoint; index++)
        {
            //�a��������]��(����)(BigDecimal�^)
            BigDecimal l_bdCurActualPaymentBalance = new BigDecimal(this
                .calcAccountBalanceDemandPower(index));

            //�a��������]��(����)���a��������]�͂�菬������
            if(l_bdCurActualPaymentBalance.compareTo(l_bdActualPaymentBalance) < 0)
            {
                //�a��������]�͂ɗa��������]��(����)��������B
                l_bdActualPaymentBalance = l_bdCurActualPaymentBalance;
                //�K�p���ɍ��Ԃ�������B
                l_intActPayAppliedPoint = index;
            }
        }

        //�ma.�a����������]�� < 0�̏ꍇ�n
        if(l_bdActualPaymentBalance.compareTo(new BigDecimal(0.0)) < 0)
        {
            //[b.���̑����i���t�\�z >= 0 �̏ꍇ]
            if(l_bdOtherTradingPower.compareTo(new BigDecimal(0.0)) >= 0)
            {
                //���̑����i���t�\�z = -1
                l_bdOtherTradingPower = new BigDecimal(-1.0);
                //�K�p�� = �K�p��<�a��������]��>
                l_intAppliedPoint = l_intActPayAppliedPoint;
            }
            //[b.���̑����i���t�\�z < 0 ���� �K�p�� > �K�p��<�a��������]��> �̏ꍇ]
            else if(l_bdOtherTradingPower.compareTo(new BigDecimal(0.0)) < 0
                    && l_intAppliedPoint > l_intActPayAppliedPoint)
            {
                //���̑����i���t�\�z = -1
                l_bdOtherTradingPower = new BigDecimal(-1.0);
                //�K�p�� = �K�p��<�a��������]��>
                l_intAppliedPoint = l_intActPayAppliedPoint;
            }
        }
        
        /*
         * �Ǐؗ]�̓`�F�b�N
         */
        //������<�����^�M�p>
        int l_intBizDate = this.calcCondition.getEquityBizDate();

        //�Ǐؗ]��<�K�p�\�z�`�F�b�N�p>���擾
        WEB3TPCalcResult l_marginCallPower = this.calcMarginCallPowerForCheck(
                l_orderTypeEnum, l_intBizDate, l_intBasePoint);

        //�ma. �Ǐؗ]�� < 0�̏ꍇ�n
        if(l_marginCallPower != null && l_marginCallPower.tradingPower < 0)
        {
            //[b.���̑����i���t�\�z >= 0 �̏ꍇ]
            if(l_bdOtherTradingPower.compareTo(new BigDecimal(0.0)) >= 0)
            {
                //���̑����i���t�\�z = -1
                l_bdOtherTradingPower = new BigDecimal(-1.0);
                //�K�p�� = �K�p��<�Ǐؗ]��>
                l_intAppliedPoint = l_marginCallPower.appliedPoint;
            }
            //[b.���̑����i���t�\�z < 0 ���� �K�p�� > �K�p��<�Ǐؗ]��> �̏ꍇ]
            else if(l_bdOtherTradingPower.compareTo(new BigDecimal(0.0)) < 0
                    && l_intAppliedPoint > l_marginCallPower.appliedPoint)
            {
                //���̑����i���t�\�z = -1
                l_bdOtherTradingPower = new BigDecimal(-1.0);
                //�K�p�� = �K�p��<�Ǐؗ]��>
                l_intAppliedPoint = l_marginCallPower.appliedPoint;
            }
        }

        /*
         * �����~���`�F�b�N
         */
        //�����~�敪 == true �̏ꍇ
        if(this.calcCondition.isTradingStop() == true)
        {
            l_bdOtherTradingPower = new BigDecimal(-1.0);
        }
        //���̑����i���t�]�͒�~�敪 == true ���� ����.������� not in (�o���A�؋����U�ցA�ב֕ۏ؋��U�ցA
        //���������U�ցACFD�U�֒����i�a�������CFD����))�̏ꍇ
        if (this.calcCondition.isOtherTradingStop() == true
                && !(OrderTypeEnum.CASH_OUT.equals(l_orderTypeEnum)
                        || OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderTypeEnum)
                        || OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)
                        || OrderTypeEnum.TRANSFER_TO_FEQ.equals(l_orderTypeEnum)
                        || OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)))
        {
            l_bdOtherTradingPower = new BigDecimal(-1.0);
        }
        //�o���]�͒�~�� ���� ����.�������in (�o���A�؋����U�ցA�ב֕ۏ؋��U�ցA
        //���������U�ցACFD�U�֒����i�a�������CFD����))�̏ꍇ
        if (this.calcCondition.isPaymentStop() == true
            && (OrderTypeEnum.CASH_OUT.equals(l_orderTypeEnum)
            || OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderTypeEnum)
            || OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)
            || OrderTypeEnum.TRANSFER_TO_FEQ.equals(l_orderTypeEnum)
            || OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)))
        {
            l_bdOtherTradingPower = new BigDecimal(-1.0);
        }

        /*
         * �]�͌v�Z���ʃI�u�W�F�N�g�Ɍv�Z���ʂ��Z�b�g���ԋp����B
         */
        //�]�͌v�Z���ʂɌv�Z���ʂ�������B
        WEB3TPCalcResult l_calcResult = new WEB3TPCalcResult();
        l_calcResult.appliedPoint = l_intAppliedPoint;
        l_calcResult.tradingPower = l_bdOtherTradingPower.doubleValue();
    
        //�p�����[�^.������ʂ�null�̎���
        if (l_orderTypeEnum == null)
        {
            //OrderTypeEnum.UNDEFINED��������B
            l_calcResult.orderTypeEnum = OrderTypeEnum.UNDEFINED;
        }
        //����ȊO
        else
        {
            //�p�����[�^.������ʂ�������B
            l_calcResult.orderTypeEnum = l_orderTypeEnum;
        }
    
        //�]�͌v�Z���ʂ�ԋp����B
        return l_calcResult;
    }

    /**
     * (calc�K�p���̑����i���t�\�z) 
     * <BR>
     * ����.����ȍ~�ŏ��́u���̑����i���t�\�z�v���v�Z����B <BR>
     * <BR>
     * �P�j�]�͌v�Z�����ޔ�����B <BR>
     * �@@�@@[a.����.������� == 201�F���M���t�A203�F���M��W�A204�F���M�抷 �̏ꍇ] <BR>
     * �@@�@@�@@?this.�]�͌v�Z����.get�]�͌v�Z���<���M>()���R�[��<BR> 
     * <BR>
     * �@@�@@[a.����.������� == 1001�F�o�� �̏ꍇ] <BR>
     * �@@�@@�@@?this.�]�͌v�Z����.get�]�͌v�Z���<�o��>()���R�[��<BR> 
     * <BR>
     * �@@�@@[a.����.������� == 605�FOP�V�K���� �̏ꍇ] <BR>
     * �@@�@@�@@?this.�]�͌v�Z����.get�]�͌v�Z���<�I�v�V�����V�K����>()���R�[��<BR> 
     * <BR>
     * �@@�@@[a.����.������� == 1007�F�؋����ւ̐U��, 1011:�ב֕ۏ؋��ւ̐U��,<BR>
     * �@@�@@�@@�@@1013:�O�������ւ̐U��, 1021:CFD�U�֒����i�a�������CFD�����j] <BR>
     * �@@�@@�@@?this.�]�͌v�Z����.get�]�͌v�Z���<���̑����t>()���R�[�� <BR>
     * <BR>
     * �@@�@@[a.�ȊO�̏ꍇ] <BR>
     * �@@�@@�@@?this.�]�͌v�Z����.get�]�͌v�Z���<���̑����t>()���R�[��<BR> 
     * <BR>
     * �Q�j�]�͌v�Z����ɁA����.������Z�b�g����B <BR>
     * �@@�@@[a.����.������� == 201�F���M���t�A203�F���M��W�A204�F���M�抷 �̏ꍇ] <BR>
     * �@@�@@�@@?this.�]�͌v�Z����.set�]�͌v�Z���<���M>()���R�[�� <BR>
     * <BR>
     * �@@�@@[a.����.������� == 1001�F�o�� �̏ꍇ] <BR>
     * �@@�@@�@@?this.�]�͌v�Z����.set�]�͌v�Z���<�o��>()���R�[�� <BR>
     * <BR>
     * �@@�@@[a.����.������� == 605�FOP�V�K���� �̏ꍇ] <BR>
     * �@@�@@�@@?this.�]�͌v�Z����.set�]�͌v�Z���<�I�v�V�����V�K����>()���R�[�� <BR>
     * <BR>
     * �@@�@@[a.����.������� == 1007�F�؋����ւ̐U��, 1011:�ב֕ۏ؋��ւ̐U��,<BR>
     * �@@�@@�@@�@@1013:�O�������ւ̐U��, 1021:CFD�U�֒����i�a�������CFD�����j] <BR>
     * �@@�@@�@@?this.�]�͌v�Z����.get�]�͌v�Z���<���̑����t>()���R�[�� <BR>
     * <BR>
     * �@@�@@[a.�ȊO�̏ꍇ] <BR>
     * �@@�@@�@@?this.�]�͌v�Z����.set�]�͌v�Z���<���̑����t>()���R�[�� <BR>
     * <BR>
     * �@@�@@�i���j <BR>
     * �@@�@@[����] <BR>
     * �@@�@@�@@int�F����.��� <BR>
     * <BR>
     * �R�j����.����ȍ~�A�ŏ��̂��̑����v�Z���� <BR>
     * �@@?this.calc�K�p���̑����i���t�\�z()���R�[�� <BR>
     * <BR>
     * �@@�@@�m�����n <BR>
     * �@@�@@�@@������ʁF����.������� <BR>
     * <BR>
     * �S�j�ޔ����Ă����A�����߂� <BR>
     * �@@�@@[a.����.������� == 201�F���M���t�A203�F���M��W�A204�F���M�抷 �̏ꍇ] <BR>
     * �@@�@@�@@?this.�]�͌v�Z����.get�]�͌v�Z���<���M>()���R�[�� <BR>
     * <BR>
     * �@@�@@[a.����.������� == 1001�F�o�� �̏ꍇ] <BR>
     * �@@�@@�@@?this.�]�͌v�Z����.get�]�͌v�Z���<�o��>()���R�[�� <BR>
     * <BR>
     * �@@�@@[a.����.������� == 605�FOP�V�K���� �̏ꍇ] <BR>
     * �@@�@@�@@?this.�]�͌v�Z����.get�]�͌v�Z���<�I�v�V�����V�K����>()���R�[�� <BR>
     * <BR>
     * �@@�@@[a.����.������� == 1007�F�؋����ւ̐U��, 1011:�ב֕ۏ؋��ւ̐U��,<BR>
     * �@@�@@�@@�@@1013:�O�������ւ̐U��, 1021�FCFD�U�֒����i�a���������CFD����)] <BR>
     * �@@�@@�@@-this.�]�͌v�Z����.get�]�͌v�Z���<���̑����t>()���R�[�� <BR>
     * <BR>
     * �@@�@@[a.�ȊO�̏ꍇ] <BR>
     * �@@�@@�@@?this.�]�͌v�Z����.get�]�͌v�Z���<���̑����t>()���R�[�� <BR>
     * <BR>
     * �@@�@@�i���j <BR>
     * �@@�@@�m�����n <BR>
     * �@@�@@�@@int�F�P�j�̖߂�l <BR>
     * <BR>
     * �T�j�R�j�̖߂�l��ԋp����B<BR>
     * <BR>
     * 
     * @@param l_orderTypeEnum - �i������ʁj
     * OrderTypeEnum�ɂĒ�`
     * @@param l_intBasePoint
     * @@return WEB3TPCalcResult
     */
    public WEB3TPCalcResult calcAppliedOtherTradingPower(
            OrderTypeEnum l_orderTypeEnum, int l_intBasePoint)
    {
        //����.�����T+0��菬������
        if (l_intBasePoint < WEB3TPSpecifiedPointDef.T_0)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcAppliedOtherTradingPower(int)");
        }
        
        //���
        int l_intNewBasePoint = l_intBasePoint;
        //�����T+5���傫���ꍇ
        if(l_intNewBasePoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //T+5���Z�b�g
            l_intNewBasePoint = WEB3TPSpecifiedPointDef.T_5;
        }
        
        //�I���W�i�����
        int l_intOriginalBasePoint;        
        //�]�͌v�Z����
        WEB3TPCalcResult l_result = null;
        
        //����.������� == 201�F���M���t�A203�F���M��W�A204�F���M�抷 �̏ꍇ
        if(OrderTypeEnum.MF_BUY.equals(l_orderTypeEnum)
           || OrderTypeEnum.MF_RECRUIT.equals(l_orderTypeEnum)
           || OrderTypeEnum.MF_SWITCHING.equals(l_orderTypeEnum))
        {
            //�]�͌v�Z�����ޔ�
            l_intOriginalBasePoint = this.calcCondition.getFundBasePoint();
            //������Z�b�g
            this.calcCondition.setFundBasePoint(l_intNewBasePoint);
            //����.����ȍ~�A�ŏ��̂��̑����i���t�\�z���v�Z
            l_result = this.calcAppliedOtherTradingPower(l_orderTypeEnum);
            //�ޔ����Ă����A�����߂��B
            this.calcCondition.setFundBasePoint(l_intOriginalBasePoint);
        }
        //����.������� == 1001�F�o�� �̏ꍇ
        else if(OrderTypeEnum.CASH_OUT.equals(l_orderTypeEnum))
        {
            //�]�͌v�Z�����ޔ�
            l_intOriginalBasePoint = this.calcCondition.getPaymentBasePoint();
            //������Z�b�g
            this.calcCondition.setPaymentBasePoint(l_intNewBasePoint);
            //����.����ȍ~�A�ŏ��̂��̑����i���t�\�z���v�Z
            l_result = this.calcAppliedOtherTradingPower(l_orderTypeEnum);
            //�ޔ����Ă����A�����߂��B
            this.calcCondition.setPaymentBasePoint(l_intOriginalBasePoint);
        }
        //����.������� == 605�FOP�V�K���� 
        else if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_orderTypeEnum))
        {
            //�]�͌v�Z�����ޔ�
            l_intOriginalBasePoint = this.calcCondition.getOptionBasePoint();
            //������Z�b�g
            this.calcCondition.setOptionBasePoint(l_intNewBasePoint);
            //����.����ȍ~�A�ŏ��̂��̑����i���t�\�z���v�Z
            l_result = this.calcAppliedOtherTradingPower(l_orderTypeEnum);
            //�ޔ����Ă����A�����߂��B
            this.calcCondition.setOptionBasePoint(l_intOriginalBasePoint);
        }
        //����.������� == 1007�F�؋����ւ̐U��, 1011:�ב֕ۏ؋��ւ̐U��,
        //1013:�O�������ւ̐U��, 1021:CFD�U�֒����i�a�������CFD�����j
        else if (OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderTypeEnum)
                || OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)
                || OrderTypeEnum.TRANSFER_TO_FEQ.equals(l_orderTypeEnum)
                || OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum))
        {
            //�]�͌v�Z�����ޔ�
            l_intOriginalBasePoint = this.calcCondition.getOtherBasePoint();
            //������Z�b�g
            this.calcCondition.setOtherBasePoint(l_intNewBasePoint);
            //����.����ȍ~�A�ŏ��̂��̑����i���t�\�z���v�Z
            l_result = this.calcAppliedOtherTradingPower(l_orderTypeEnum);
            //�ޔ����Ă����A�����߂��B
            this.calcCondition.setOtherBasePoint(l_intOriginalBasePoint);
        }
        //�ȊO�̏ꍇ
        else
        {
            //�]�͌v�Z�����ޔ�
            l_intOriginalBasePoint = this.calcCondition.getOtherBasePoint();
            //������Z�b�g
            this.calcCondition.setOtherBasePoint(l_intNewBasePoint);
            //����.����ȍ~�A�ŏ��̂��̑����i���t�\�z���v�Z
            l_result = this.calcAppliedOtherTradingPower(null);
            //�ޔ����Ă����A�����߂��B
            this.calcCondition.setOtherBasePoint(l_intOriginalBasePoint);
        }

        //�v�Z���ʂ�ԋp
        return l_result;
    }

    /**
     * (calc�a��������]��<�K�p�\�z�`�F�b�N�p>)
     * 
     * �K�p�\�z�v�Z�̃`�F�b�N���ɗp����a��������]�͂��v�Z����B
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB
     * 
     * @@param l_orderTypeEnum - �i������ʁj
     * @@param l_intBizDate - �i�������j
     * @@param l_intDeliDate - �i��n���j
     * @@return WEB3TPCalcResult
     */
    public WEB3TPCalcResult calcAccountBalanceDemandPowerForCheck(
            OrderTypeEnum l_orderTypeEnum, int l_intBizDate, int l_intDeliDate)
    {
        /*
         * �g�a��������]�̓`�F�b�N���@@�h���擾����B
         */
        //�a��������]�̓`�F�b�N���@@
        String l_strInstBranCalcCond = null;

        //[a.����.������� == 3:�V�K�������� �܂��� 4:�V�K�������� �̏ꍇ]
        if(OrderTypeEnum.MARGIN_LONG.equals(l_orderTypeEnum) == true
                || OrderTypeEnum.MARGIN_SHORT.equals(l_orderTypeEnum) == true)
        {
            l_strInstBranCalcCond = this.calcCondition
                .getInstBranCalcCondition(WEB3TPCalcCondition.MARGINOPEN_ACTPAY_CHECK);
        }
        //[a.�ȊO�̏ꍇ]
        else
        {
            //null��ԋp����B
            return null;
        }
        
        /*
         * �g�a��������]�̓`�F�b�N���@@�h�ʂɁA�K�p�\�z�`�F�b�N�p�̗a��������]�͂ƓK�p�����v�Z����B
         */
        //�n�_(�a��������]�̓`�F�b�N�͈�)
        int l_intStart = 0;
        //�I�_(�a��������]�̓`�F�b�N�͈�)
        int l_intEnd = 0;
        
        //[a.�h�a��������]�̓`�F�b�N���@@�h == 1:FROM_T0_UNTIL_BIZ_DATE �̏ꍇ]
        if(WEB3TPActPayCheckDef.FROM_T0_UNTIL_BIZ_DATE
            .equals(l_strInstBranCalcCond) == true)
        {
            //�n�_ = T+0
            l_intStart = WEB3TPSpecifiedPointDef.T_0;
            //�I�_ = ������
            l_intEnd = l_intBizDate;
        }
        //[a.�h�a��������]�̓`�F�b�N���@@�h == 2:FROM_T0_UNTIL_PRE_BIZ_DATE �̏ꍇ]
        else if(WEB3TPActPayCheckDef.FROM_T0_UNTIL_PRE_BIZ_DATE
            .equals(l_strInstBranCalcCond) == true)
        {
            //�n�_ = T+0
            l_intStart = WEB3TPSpecifiedPointDef.T_0;
            //�I�_ = ������-1
            l_intEnd = l_intBizDate-1;
        }
        //[a.�ȊO �̏ꍇ]
        else
        {
            //null��ԋp����B
            return null;
        }

        //�n�_ > �I�_�̏ꍇ
        if(l_intStart > l_intEnd)
        {
            //null��ԋp����B
            return null;
        }
        
        //�a��������]�́i�ŏ��l�����߂����̂�double�^�̍ő�l�������l�ɃZ�b�g�j
        double l_dblActualPaymentBalance = Double.MAX_VALUE;
        //�a��������]��(BigDecimal�^)
        BigDecimal l_bdActualPaymentBalance = new BigDecimal(l_dblActualPaymentBalance);
        //�K�p��
        int l_intAppliedPoint = 0;
    
        //�w����͈̔́i�n�_�`�I�_�j�Ń��[�v
        for (int index = l_intStart; index <= l_intEnd; index++)
        {
            //�a��������]��(����)(BigDecimal�^)
            BigDecimal l_bdCurActualPaymentBalance =
                new BigDecimal(this.calcAccountBalanceDemandPower(index));
    
            //�a��������]��(����)���a��������]�͂�菬������
            if (l_bdCurActualPaymentBalance.compareTo(l_bdActualPaymentBalance) < 0)
            {
                //�a��������]�͂ɗa��������]��(����)����
                l_bdActualPaymentBalance = l_bdCurActualPaymentBalance;
                //�K�p���ɍ��Ԃ���
                l_intAppliedPoint = index;
            }
        }

        /*
         * �]�͌v�Z���ʃI�u�W�F�N�g�Ɍv�Z���ʂ��Z�b�g���ԋp����B
         */
        //�]�͌v�Z���ʂɌv�Z���ʂ�������B
        WEB3TPCalcResult l_calcResult = new WEB3TPCalcResult();
        l_calcResult.appliedPoint = l_intAppliedPoint;
        l_calcResult.tradingPower = l_bdActualPaymentBalance.doubleValue();
        l_calcResult.orderTypeEnum = l_orderTypeEnum;
        
        return l_calcResult;
    }
    
    /**
     * (calc�Ǐؗ]��<�K�p�\�z�`�F�b�N�p>)
     * 
     * �K�p�\�z�v�Z�̃`�F�b�N���ɗp����Ǐؗ]�͂��v�Z����B
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB
     * 
     * @@param l_orderTypeEnum - �i������ʁj
     * @@param l_intBizDate - �i�������j
     * @@param l_intDeliDate - �i��n���j
     * @@return WEB3TPCalcResult
     */
    public WEB3TPCalcResult calcMarginCallPowerForCheck(
            OrderTypeEnum l_orderTypeEnum, int l_intBizDate, int l_intDeliDate)
    {
        /*
         * �g�Ǐؗ]�̓`�F�b�N���@@�h���擾����B
         */
        //�Ǐؗ]�̓`�F�b�N���@@
        String l_strInstBranCalcCond = null;

        //[a.����.������� == 1:�����������̏ꍇ]
        if(OrderTypeEnum.EQUITY_BUY.equals(l_orderTypeEnum) == true)
        {
            l_strInstBranCalcCond = this.calcCondition
                .getInstBranCalcCondition(WEB3TPCalcCondition.EQUITYBUY_ADDDEPOSIT_CHECK);
        }
        //[a.����.������� == 3:�V�K�������� �܂��� 4:�V�K�������� �̏ꍇ]
        else if(OrderTypeEnum.MARGIN_LONG.equals(l_orderTypeEnum) == true
                || OrderTypeEnum.MARGIN_SHORT.equals(l_orderTypeEnum) == true)
        {
            l_strInstBranCalcCond = this.calcCondition
                .getInstBranCalcCondition(WEB3TPCalcCondition.MARGINOPEN_ADDDEPOSIT_CHECK);
        }
        //[a.����.������� == 7:���������̏ꍇ]
        else if(OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderTypeEnum) == true)
        {
            l_strInstBranCalcCond = this.calcCondition
                .getInstBranCalcCondition(WEB3TPCalcCondition.MARGINSWAPLONG_ADDDEPOSIT_CHECK);
        }
        //[a.�ȊO�̏ꍇ]
        else
        {
            l_strInstBranCalcCond = this.calcCondition
                .getInstBranCalcCondition(WEB3TPCalcCondition.OTHERBUY_ADDDEPOSIT_CHECK);
        }
        
        /*
         * �g�Ǐؗ]�̓`�F�b�N���@@�h�ʂɁA�K�p�\�z�`�F�b�N�p�̒Ǐؗ]�͂ƓK�p�����v�Z����B
         */
        //�n�_(�Ǐؗ]�̓`�F�b�N�͈�)
        int l_intStart = 0;
        //�I�_(�Ǐؗ]�̓`�F�b�N�͈�)
        int l_intEnd = 0;
        
        //[a.�h�Ǐؗ]�̓`�F�b�N���@@�h == 1:FROM_BIZ_DATE_UNTIL_PRE_DELIVERY_DATE �̏ꍇ]
        if(WEB3TPAdddepositCheckDef.FROM_BIZ_DATE_UNTIL_PRE_DELIVERY_DATE
            .equals(l_strInstBranCalcCond) == true)
        {
            //�n�_ = ������
            l_intStart = l_intBizDate;
            //�I�_ = ��n��-1
            l_intEnd = l_intDeliDate - 1;
        }
        //[a.�h�Ǐؗ]�̓`�F�b�N���@@�h == 2:ON_BIZ_DATE �̏ꍇ]
        else if(WEB3TPAdddepositCheckDef.ON_BIZ_DATE
            .equals(l_strInstBranCalcCond) == true)
        {
            //�n�_ = ������
            l_intStart = l_intBizDate;
            //�I�_ = ������
            l_intEnd = l_intBizDate;
        }
        //[a.�ȊO �̏ꍇ]
        else
        {
            //null��ԋp����B
            return null;
        }

        //�n�_ > �I�_�̏ꍇ
        if(l_intStart > l_intEnd)
        {
            //null��ԋp����B
            return null;
        }
        
        //�Ǐؗ]�́i�ŏ��l�����߂����̂�double�^�̍ő�l�������l�ɃZ�b�g�j
        double l_dblMarginCallPower = Double.MAX_VALUE;
        //�Ǐؗ]��(BigDecimal�^)
        BigDecimal l_bdMarginCallPower = new BigDecimal(l_dblMarginCallPower);
        //�K�p��
        int l_intAppliedPoint = 0;
    
        //�w����͈̔́i�n�_�`�I�_�j�Ń��[�v
        for (int index = l_intStart; index <= l_intEnd; index++)
        {
            //�Ǐؗ]��(����)(BigDecimal�^)
            BigDecimal l_bdCurMarginCallPower =
                new BigDecimal(this.calcMarginCallPower(index));
    
            //�Ǐؗ]��(����)���Ǐؗ]�͂�菬������
            if (l_bdCurMarginCallPower.compareTo(l_bdMarginCallPower) < 0)
            {
                //�Ǐؗ]�͂ɒǏؗ]��(����)����
                l_bdMarginCallPower = l_bdCurMarginCallPower;
                //�K�p���ɍ��Ԃ���
                l_intAppliedPoint = index;
            }
        }

        /*
         * �]�͌v�Z���ʃI�u�W�F�N�g�Ɍv�Z���ʂ��Z�b�g���ԋp����B
         */
        //�]�͌v�Z���ʂɌv�Z���ʂ�������B
        WEB3TPCalcResult l_calcResult = new WEB3TPCalcResult();
        l_calcResult.appliedPoint = l_intAppliedPoint;
        l_calcResult.tradingPower = l_bdMarginCallPower.doubleValue();
        l_calcResult.orderTypeEnum = l_orderTypeEnum;
        
        return l_calcResult;
    }

    /**
     * (calc�������t�\�z�`�A�������`) <BR>
     * <BR>
     * �ŏ��́u�������t�\�z�`�A�������`�v���v�Z����B <BR>
     * <BR>
     * 1)����������Ƃ����ꍇ�̓K�p�������t�\�z���擾����B<BR>
     * <BR>
     * �@@-this.calc�K�p�������t�\�z(int)���R�[������B <BR>
     * <BR>
     * �@@�@@[����] <BR>
     * �@@�@@�@@int�F����.��� <BR>
     * <BR>
     *   -�������t�\�z���擾����B <BR>
     * <BR>
     * �@@�@@�������t�\�z = �]�͌v�Z����.����\�z <BR>
     * <BR>
     * 2)�������t�E�M�p�����\�񒍕���(�����E����)�T�Z��n������W�v����B <BR>
     * <BR>
     * �@@[this.���������\�񒍕��P�ʈꗗ == null  <BR>
     * �@@�@@�܂��� this.���������\�񒍕��P�ʈꗗ.size() == 0 �ꍇ] <BR>
     * <BR>
     * �@@�@@(�����E����)�T�Z��n������v = 0 <BR>
     * <BR>
     * �@@[�ȊO�̏ꍇ] <BR>
     * <BR>
     * �@@�@@-this.���������\�񒍕��P�ʈꗗ���ȉ��̏����Ńt�B���^����B<BR> 
     * <BR>
     * �@@�@@�@@[��������] <BR>
     * �@@�@@�@@�@@������� IN ('1�F����������', '7�F��������') <BR>
     * �@@�@@�@@�@@�����L����� = �f1�F�I�[�v���f <BR>
     * <BR>
     * �@@�@@-�t�B���^���ʂ��T�Z��n������W�v����B<BR> 
     * <BR>
     * �@@�@@�@@(�����E����)�T�Z��n������v = sum(�����\�񒍕��P��Row.�T�Z��n���) <BR>
     * <BR>
     * 3)�������t�\�z�`�A�������`��ԋp���� <BR>
     * <BR>
     * �@@�ԋp�l = �������t�\�z - (�����E����)�T�Z��n������v <BR>
     * <BR>
     * @@param l_intBasePoint - (���)
     * @@return double
     */
    public double calcSuccEquityTradingPower(int l_intBasePoint)
    {
        /*
         * ����������Ƃ����ꍇ�̓K�p�������t�\�z���擾����B
         */
        WEB3TPCalcResult l_result = this.calcAppliedEquityTradingPower(l_intBasePoint);

        //�K�p�������t�\�z
        double l_dblEqTradingPower = l_result.tradingPower;

        /*
         * �������t�E�M�p�����\�񒍕���(�����E����)�T�Z��n������W�v����B 
         */
        //(�����E����)�T�Z��n������v
        double l_dblRevEqTradingPower = 0;
        //���������\�񒍕��P�ʈꗗ
        List l_lisRsvEqOrders = this.getTodaysRsvEqOrderUnits();

        //���������\�񒍕��P�ʈꗗ�����݂���ꍇ
        if(l_lisRsvEqOrders != null && l_lisRsvEqOrders.isEmpty() == false)
        {
            for(Iterator iter = l_lisRsvEqOrders.iterator(); iter.hasNext();)
            {
                RsvEqOrderUnitParams l_params = (RsvEqOrderUnitParams)iter.next();

                //�����L����� == 1:�I�[�v�� �̏ꍇ
                if(OrderOpenStatusEnum.OPEN.equals(l_params.getOrderOpenStatus()) == true)
                {
                    //�������
                    OrderTypeEnum l_orderType = l_params.getOrderType();

                    //������� == 1:���������� �܂��́A7�F�������� �̏ꍇ
                    if(OrderTypeEnum.EQUITY_BUY.equals(l_orderType) == true
                            || OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderType) == true)
                    {
                        //(�����E����)�T�Z��n������v = sum(�����\�񒍕��P��Row.�T�Z��n���)
                        l_dblRevEqTradingPower = l_dblRevEqTradingPower
                                + Math.abs(l_params.getEstimatedPrice());
                    }
                }
            }
        }

        /*
         * �������t�\�z�`�A�������`��ԋp����
         * 
         * �ԋp�l = �������t�\�z - (�����E����)�T�Z��n������v
         */
        return l_dblEqTradingPower - l_dblRevEqTradingPower;
    }

    /**
     * (calc�M�p�V�K���\�z�`�A�������`) <BR>
     * <BR>
     * �ŏ��́u�M�p�V�K���\�z�`�A�������`�v���v�Z����B <BR>
     * <BR>
     * 1)�K�p�M�p�V�K���\�z���擾����B <BR>
     * <BR>
     * �@@-this.calc�K�p�M�p�V�K���\�z()���R�[������B <BR>
     * <BR>
     * �@@-�M�p�V�K���\�z���擾����B <BR>
     * <BR>
     * �@@�@@�M�p�V�K���\�z = �]�͌v�Z����.����\�z <BR>
     * <BR>
     * 2)�������t�E�M�p�����\�񒍕���(�����E����)�T�Z��n������W�v����B <BR>
     * <BR>
     * �@@[this.���������\�񒍕��P�ʈꗗ == null  <BR>
     * �@@�@@�܂��� this.���������\�񒍕��P�ʈꗗ.size() == 0 �ꍇ] <BR>
     * <BR>
     * �@@�@@(�����E����)�T�Z��n������v = 0 <BR>
     * <BR>
     * �@@[�ȊO�̏ꍇ] <BR>
     * �@@�@@-this.���������\�񒍕��P�ʈꗗ���ȉ��̏����Ńt�B���^����B <BR>
     * <BR>
     * �@@�@@�@@[��������] <BR>
     * �@@�@@�@@�@@������� IN ('1�F����������', '7�F��������') <BR>
     * �@@�@@�@@�@@�����L����� = �f1�F�I�[�v���f <BR>
     * <BR>
     * �@@�@@-�t�B���^���ʂ��T�Z��n������W�v����B <BR>
     * <BR>
     * �@@�@@�@@(�����E����)�T�Z��n������v = sum(�����\�񒍕��P��Row.�T�Z��n���) <BR>
     * <BR>
     * 3)�M�p�V�K���\�񒍕���(�V�K��)�T�Z��n������W�v����B<BR> 
     * <BR>
     * �@@[this.���������\�񒍕��P�ʈꗗ == null  <BR>
     * �@@�@@�܂��� this.���������\�񒍕��P�ʈꗗ.size() == 0 �ꍇ] <BR>
     * <BR>
     * �@@�@@(�V�K��)�T�Z��n����T�Z��n������v = 0 <BR>
     * <BR>
     * �@@[�ȊO�̏ꍇ] <BR>
     * �@@�@@-this.���������\�񒍕��P�ʈꗗ���ȉ��̏����Ńt�B���^����B <BR>
     * <BR>
     * �@@�@@�@@[��������] <BR>
     * �@@�@@�@@�@@������� IN ('3�F�V�K��������', '4�F�V�K��������') <BR>
     * �@@�@@�@@�@@�����L����� = �f1�F�I�[�v���f <BR>
     * <BR>
     * �@@�@@-�t�B���^���ʂ��T�Z��n������W�v����B<BR> 
     * <BR>
     * �@@�@@�@@(�V�K��)�T�Z��n������v = sum(�����\�񒍕��P��Row.�T�Z��n���) <BR>
     * <BR>
     * 4)�M�p�V�K���\�z�`�A�������`��ԋp���� <BR>
     * <BR>
     * �@@�ԋp�l = �M�p�V�K���\�z  <BR>
     * �@@�@@�@@�@@�@@ - (�V�K��)�T�Z��n������v <BR> 
     * �@@�@@�@@�@@�@@ - ( (�����E����)�T�Z��n������v * ( 1 - (��p�|�� / 100) ) ) / (�ۏ؋��� / 100) <BR>
     * <BR>
     * �@@����p�|�� = this.�]�͌v�Z����.get�]�͌v�Z��p�|��() <BR>
     * �@@���ۏ؋��� = this.�]�͌v�Z����.get�ۏ؋���() <BR>
     * <BR>
     * @@return double
     */
    public double calcSuccMarginTradingPower()
    {
        /*
         * �K�p�M�p�V�K���\�z���擾����B
         */
        WEB3TPCalcResult l_result = this.calcAppliedMarginTradingPower();

        //�K�p�M�p�V�K���\�z
        double l_dblMarginTradingPower = l_result.tradingPower;

        /*
         * �������t�E�M�p�����\�񒍕���(�����E����)�T�Z��n������W�v����B 
         * �M�p�V�K���\�񒍕���(�V�K��)�T�Z��n������W�v����B 
         */
        //(�����E����)�T�Z��n������v
        double l_dblRevEqTradingPower = 0;
        //(�V�K��)�T�Z��n������v
        double l_dblRevMarginTradingPower = 0;
        //���������\�񒍕��P�ʈꗗ
        List l_lisRsvEqOrders = this.getTodaysRsvEqOrderUnits();

        //���������\�񒍕��P�ʈꗗ�����݂���ꍇ
        if(l_lisRsvEqOrders != null && l_lisRsvEqOrders.isEmpty() == false)
        {
            for(Iterator iter = l_lisRsvEqOrders.iterator(); iter.hasNext();)
            {
                RsvEqOrderUnitParams l_params = (RsvEqOrderUnitParams)iter.next();

                //�����L����� == 1:�I�[�v�� �̏ꍇ
                if(OrderOpenStatusEnum.OPEN.equals(l_params.getOrderOpenStatus()) == true)
                {
                    //�������
                    OrderTypeEnum l_orderType = l_params.getOrderType();

                    //������� == 1:���������� �܂��́A7�F�������� �̏ꍇ
                    if(OrderTypeEnum.EQUITY_BUY.equals(l_orderType) == true
                            || OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderType) == true)
                    {
                        //(�����E����)�T�Z��n������v = sum(�����\�񒍕��P��Row.�T�Z��n���)
                        l_dblRevEqTradingPower = l_dblRevEqTradingPower
                                + Math.abs(l_params.getEstimatedPrice());
                    }
                    //������� == 3�F�V�K���������@@�܂��́A4�F�V�K�������� �̏ꍇ
                    else if(OrderTypeEnum.MARGIN_LONG.equals(l_orderType) == true
                            || OrderTypeEnum.MARGIN_SHORT.equals(l_orderType) == true)
                    {
                        //(�V�K��)�T�Z��n������v = sum(�����\�񒍕��P��Row.�T�Z��n���)
                        l_dblRevMarginTradingPower = l_dblRevMarginTradingPower
                                + Math.abs(l_params.getEstimatedPrice());
                    }
                }
            }
        }

        /*
         * �M�p�V�K���\�z�`�A�������`��ԋp���� 
         * 
         * �ԋp�l = �M�p�V�K���\�z  
         * �@@�@@�@@�@@ - (�V�K��)�T�Z��n������v  
         * �@@�@@�@@�@@ - ( (�����E����)�T�Z��n������v * ( 1 - (��p�|�� / 100) ) ) / (�ۏ؋��� / 100) 
         */
        //��p�|��
        int l_intSubRate = this.calcCondition.getSubstituteRate();
        //�ۏ؋���
        int l_intMarginDepRate = this.calcCondition.getMarginDepositRate();

        //�M�p�V�K���\�z
        BigDecimal l_bdMarginTradingPower = new BigDecimal(Double.toString(l_dblMarginTradingPower));
        //(�V�K��)�T�Z��n������v
        BigDecimal l_bdRevMarginTradingPower = new BigDecimal(
                Double.toString(l_dblRevMarginTradingPower));
        //(�����E����)�T�Z��n������v
        BigDecimal l_bdRevEqTradingPower = new BigDecimal(Double.toString(l_dblRevEqTradingPower));

        //�ԋp�l
        BigDecimal l_bdReturn = null;
        l_bdReturn = l_bdRevEqTradingPower.multiply(new BigDecimal(
                Integer.toString(100 - l_intSubRate)));
        l_bdReturn = l_bdReturn.divide(
                new BigDecimal(Integer.toString(l_intMarginDepRate)),
                10,
                BigDecimal.ROUND_HALF_EVEN);
        l_bdReturn = l_bdMarginTradingPower.subtract(l_bdRevMarginTradingPower).subtract(l_bdReturn);

        //�����_�ȉ��؎̂�
        return Math.floor(l_bdReturn.doubleValue());
    }

    /**
     * (calc�M�p�����\�z�`�A�������`) <BR>
     * <BR>
     * �ŏ��́u�M�p�����\�z�`�A�������`�v���v�Z����B <BR>
     * <BR>
     * 1)����������Ƃ����ꍇ�̓K�p�M�p�����\�z���擾����B <BR>
     * <BR>
     * �@@-this.calc�K�p�M�p�����\�z(int)���R�[������B <BR>
     * <BR>
     * �@@�@@[����] <BR>
     * �@@�@@�@@int�F����.��� <BR>
     * <BR>
     *   -�M�p�����\�z���擾����B <BR>
     * <BR>
     * �@@�@@�M�p�����\�z = �]�͌v�Z����.����\�z <BR>
     * <BR>
     * 2)�������t�E�M�p�����\�񒍕���(�����E����)�T�Z��n������W�v����B <BR>
     * <BR>
     * �@@[this.���������\�񒍕��P�ʈꗗ == null  <BR>
     * �@@�@@�܂��� this.���������\�񒍕��P�ʈꗗ.size() == 0 �ꍇ] <BR>
     * <BR>
     * �@@�@@(�����E����)�T�Z��n������v = 0 <BR>
     * <BR>
     * �@@[�ȊO�̏ꍇ] <BR>
     * �@@�@@-this.���������\�񒍕��P�ʈꗗ���ȉ��̏����Ńt�B���^����B <BR>
     * <BR>
     * �@@�@@�@@[��������] <BR>
     * �@@�@@�@@�@@������� IN ('1�F����������', '7�F��������') <BR>
     * �@@�@@�@@�@@�����L����� = �f1�F�I�[�v���f 
     * <BR>
     * �@@�@@-�t�B���^���ʂ��T�Z��n������W�v����B <BR>
     * <BR>
     * �@@�@@�@@(�����E����)�T�Z��n������v = sum(�����\�񒍕��P��Row.�T�Z��n���) <BR>
     * <BR>
     * 3)�M�p�V�K���\�񒍕���(�V�K��)�T�Z��n������W�v����B <BR>
     * <BR>
     * �@@[this.���������\�񒍕��P�ʈꗗ == null  <BR>
     * �@@�@@�܂��� this.���������\�񒍕��P�ʈꗗ.size() == 0 �ꍇ] <BR>
     * <BR>
     * �@@�@@(�V��)�T�Z��n����T�Z��n������v = 0 <BR>
     * <BR>
     * �@@[�ȊO�̏ꍇ] <BR>
     * �@@�@@-this.���������\�񒍕��P�ʈꗗ���ȉ��̏����Ńt�B���^����B <BR>
     * <BR>
     * �@@�@@�@@[��������] <BR>
     * �@@�@@�@@�@@������� IN ('3�F�V�K��������', '4�F�V�K��������') <BR>
     * �@@�@@�@@�@@�����L����� = �f1�F�I�[�v���f 
     * <BR>
     * �@@�@@-�t�B���^���ʂ��T�Z��n������W�v����B <BR>
     * <BR>
     * �@@�@@�@@(�V�K��)�T�Z��n������v = sum(�����\�񒍕��P��Row.�T�Z��n���) <BR>
     * <BR>
     * <BR>
     * 4)�M�p�����\�z�`�A�������`��ԋp���� <BR>
     * <BR>
     * �@@�ԋp�l = �M�p�V�K���\�z   <BR>
     * �@@�@@�@@�@@�@@ - ( (�V�K��)�T�Z��n������v * (�ۏ؋��� / 100))  <BR>
     * �@@�@@�@@�@@�@@ - (�����E����)�T�Z��n������v <BR>
     * <BR>
     * �@@���ۏ؋��� = this.�]�͌v�Z����.get�ۏ؋���<BR>
     *  <BR>
     * @@param l_intBasePoint - (���)
     * @@return double
     */
    public double calcSuccActualReceiptTradingPower(int l_intBasePoint)
    {
        /*
         * ����������Ƃ����ꍇ�̓K�p�M�p�����\�z���擾����B
         */
        WEB3TPCalcResult l_result = this.calcAppliedActualReceiptTradingPower(l_intBasePoint);

        //�K�p�M�p�����\�z
        double l_dblActualReceiptTradingPower = l_result.tradingPower;

        /*
         * �������t�E�M�p�����\�񒍕���(�����E����)�T�Z��n������W�v����B 
         * �M�p�V�K���\�񒍕���(�V�K��)�T�Z��n������W�v����B 
         */
        //(�����E����)�T�Z��n������v
        double l_dblRevEqTradingPower = 0;
        //(�V�K��)�T�Z��n������v
        double l_dblRevMarginTradingPower = 0;
        //���������\�񒍕��P�ʈꗗ
        List l_lisRsvEqOrders = this.getTodaysRsvEqOrderUnits();

        //���������\�񒍕��P�ʈꗗ�����݂���ꍇ
        if(l_lisRsvEqOrders != null && l_lisRsvEqOrders.isEmpty() == false)
        {
            for(Iterator iter = l_lisRsvEqOrders.iterator(); iter.hasNext();)
            {
                RsvEqOrderUnitParams l_params = (RsvEqOrderUnitParams)iter.next();

                //�����L����� == 1:�I�[�v�� �̏ꍇ
                if(OrderOpenStatusEnum.OPEN.equals(l_params.getOrderOpenStatus()) == true)
                {
                    //�������
                    OrderTypeEnum l_orderType = l_params.getOrderType();

                    //������� == 1:���������� �܂��́A7�F�������� �̏ꍇ
                    if(OrderTypeEnum.EQUITY_BUY.equals(l_orderType) == true
                            || OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderType) == true)
                    {
                        //(�����E����)�T�Z��n������v = sum(�����\�񒍕��P��Row.�T�Z��n���)
                        l_dblRevEqTradingPower = l_dblRevEqTradingPower
                                + Math.abs(l_params.getEstimatedPrice());
                    }
                    //������� == 3�F�V�K���������@@�܂��́A4�F�V�K�������� �̏ꍇ
                    else if(OrderTypeEnum.MARGIN_LONG.equals(l_orderType) == true
                            || OrderTypeEnum.MARGIN_SHORT.equals(l_orderType) == true)
                    {
                        //(�V�K��)�T�Z��n������v = sum(�����\�񒍕��P��Row.�T�Z��n���)
                        l_dblRevMarginTradingPower = l_dblRevMarginTradingPower
                                + Math.abs(l_params.getEstimatedPrice());
                    }
                }
            }
        }

        /*
         * �M�p�����\�z�`�A�������`��ԋp���� 
         * 
         * �ԋp�l = �M�p�����\�z   
         * �@@�@@�@@�@@ - ( (�V�K��)�T�Z��n������v * (�ۏ؋��� / 100))   
         * �@@�@@�@@�@@ - (�����E����)�T�Z��n������v 
         */
        //�ۏ؋���
        int l_intMarginDepRate = this.calcCondition.getMarginDepositRate();

        //�M�p�����\�z
        BigDecimal l_bdActualReceiptTradingPower = new BigDecimal(
                Double.toString(l_dblActualReceiptTradingPower));
        //(�V�K��)�T�Z��n������v
        BigDecimal l_bdRevMarginTradingPower = new BigDecimal(
                Double.toString(l_dblRevMarginTradingPower));
        //(�����E����)�T�Z��n������v
        BigDecimal l_bdRevEqTradingPower = new BigDecimal(Double.toString(l_dblRevEqTradingPower));

        //�ԋp�l
        BigDecimal l_bdReturn = null;
        l_bdReturn = l_bdRevMarginTradingPower.multiply(new BigDecimal(
                Integer.toString(l_intMarginDepRate)));
        l_bdReturn = l_bdReturn.divide(new BigDecimal("100"), 10, BigDecimal.ROUND_HALF_EVEN);
        l_bdReturn = l_bdActualReceiptTradingPower.subtract(l_bdReturn).subtract(
                l_bdRevEqTradingPower);

        //�����_�ȉ��؎̂�
        return Math.floor(l_bdReturn.doubleValue());
    }
    
    /**
     * (static���\�b�h)(find�]�͌v�Z����<�M�p�ڋq>�`�����h�c�w��`)<BR>
     * <BR>
     * �P�j�ڋq�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@�|�R���X�g���N�^�AWEB3GentradeMainAccount()�R�[��<BR>
     * �@@�@@�m�����n<BR>
     * �@@�@@�@@����ID�F����.����ID<BR>
     * <BR>
     * �Q�j"�]�͍Čv�Z�����"���擾����B<BR>
     * <BR>
     * �@@�|�v���Z�X�Ǘ��e�[�u�����ȉ��̏����Ō�������B<BR>
     * �@@�@@�m���������n<BR>
     * �@@�@@�@@�v���Z�XID�F"0006:�]�͍Čv�Z�����"<BR>
     * �@@�@@�@@�،���ЃR�[�h:�ڋq�I�u�W�F�N�g.getDataSourceObject().get�،���ЃR�[�h()<BR>
     * �@@�@@�@@���X�R�[�h�F�ڋq�I�u�W�F�N�g.getDataSourceObject().get���X�R�[�h()<BR>
     * <BR>
     * �@@�|"�]�͍Čv�Z�����"���擾����B<BR>
     * �@@�@@[a.�������� != null �̏ꍇ]<BR>
     * �@@�@@�@@"�]�͍Čv�Z�����" = �v���Z�X�Ǘ�Params.�ŏI�X�V����<BR>
     * <BR>
     * �@@�@@[a.�������� == null �̏ꍇ]<BR>
     * �@@�@@�@@"�]�͍Čv�Z�����" = null<BR>
     * <BR>
     * �R�j�ŐV�̗]�͌v�Z����Params�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�|�]�͌v�Z���ʃe�[�u��(�M�p�ڋq)���ȉ��̏����Ō�������B<BR>
     * �@@�@@�m���������n<BR>
     * �@@�@@�@@�ڋqID�F����.����ID<BR>
     * �@@�@@�@@�쐬���t�F�g�ŐV�̓��t�h<BR>
     * �@@�@@�@@�l�􂢋敪�F0:�ʏ�<BR>
     * �@@�@@�@@�i�]�͌v�Z���ʁi�M�p�ڋq�jID�̍~���j<BR>
     * <BR>
     * �@@���������� == null �܂��� ��������.size() == 0 �ꍇ�A<BR>
     * �@@�@@�]�͍Čv�Z�t���[��<BR>
     * <BR>
     * �S�j�ŐV�̗]�͌v�Z���ʏڍ�Params�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@[a."�]�͍Čv�Z�����" == null �܂��́A<BR>
     * �@@�@@ "�]�͍Čv�Z�����" <  �]�͌v�Z����Params�I�u�W�F�N�g.�쐬���t]<BR>
     * <BR>
     * �@@�@@�|�]�͌v�Z���ʏڍ׃e�[�u��(�M�p�ڋq)���ȉ��̏����Ō�������B<BR>
     * �@@�@@�@@�m���������n<BR>
     * �@@�@@�@@�@@�]�͌v�Z���ʁi�M�p�ڋq�jID�F�]�͌v�Z����Params�I�u�W�F�N�g.�]�͌v�Z���ʁi�M�p�ڋq�jID<BR>
     * <BR>
     * �@@�@@���]�͌v�Z���ʏڍ�Params���擾�ł��Ȃ������ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@[a.�ȊO�̏ꍇ]<BR>
     * <BR>
     * �@@�@@���]�͍Čv�Z�t���[��<BR>
     * <BR>
     * �T�j�ԋp�l(List�^)��ݒ肵�A�Ăяo�����֕ԋp����B<BR>
     * <BR>
     * �@@[�ԋp�l]<BR>
     * �@@�@@List�́A0�Ԗڂ̗v�f�F�R�j�Ŏ擾�����A�]�͌v�Z����Params<BR>
     * �@@�@@List�́A1�Ԗڂ̗v�f�F�S�j�Ŏ擾�����A�]�͌v�Z���ʏڍ�Params<BR>
     * <BR>
     * <BR>
     * �F�]�͍Čv�Z�t���[<BR>
     * <BR>
     * E-1)�M�p�����J�݋敪���擾����B<BR>
     * <BR>
     * �|�ڋq�I�u�W�F�N�g.is�M�p�����J��()���R�[��<BR>
     * �@@[����]<BR>
     * �@@�@@�ٍϋ敪���h�w��Ȃ�<BR>
     * <BR>
     * �@@���M�p�ڋq�̏ꍇ(is�M�p�����J��==false)�A��O���X���[����B<BR>
     * <BR>
     * E-2)�⏕�����I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�|�ڋq�I�u�W�F�N�g.getSubAccount()<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�⏕�����^�C�v�F"�����������(�a���)"<BR>
     * <BR>
     * E-3�j�]�͌v�Z�����I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@�|�]�͌v�Z����.create�]�͌v�Z����<�W��>()���R�[��<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�⏕�����F�⏕�����I�u�W�F�N�g<BR>
     * <BR>
     * E-4�j�]�͍X�V�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@�|�R���X�g���N�^�A�]�͍X�V()���R�[��<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@����ID�F����.����ID<BR>
     * �@@�@@�@@�M�p�ڋq�t���O�Fis�M�p�����J��()�̖߂�l<BR>
     * �@@�@@�@@�]�͌v�Z�����Fcreate�]�͌v�Z����()�̖߂�l<BR>
     * �@@�@@�@@���������e�Fnull<BR>
     * <BR>
     * E-5�j�]�͍X�V���e(List)���擾��DB�Ɋi�[����B<BR>
     * <BR>
     * �@@�|�]�͍X�V�I�u�W�F�N�g.calc�]�͍X�V���e<�M�p�ڋq>()���R�[��<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�l�􂢋敪�F"0:�ʏ�"<BR>
     * <BR>
     * �@@�|�]�͍X�V�I�u�W�F�N�g.save�]�͍X�V���e<�M�p�ڋq>()���R�[��<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@List�F�]�͍X�V�I�u�W�F�N�g.calc�]�͍X�V���e<�M�p�ڋq>()�̖߂�l<BR>
     * <BR>
     * E-7�j�]�͍X�V�I�u�W�F�N�g.calc�]�͍X�V���e<�M�p�ڋq>()�̖߂�l��ԋp����B<BR>
     * <BR>
     * @@param l_lngAccountId - (����ID)
     * @@return List
     */
    public static List findCalcResultMarginParams(long l_lngAccountId)
    {
        final String STR_METHOD_NAME = "WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams()";
        log.entering(STR_METHOD_NAME);

        //�]�͌v�Z���ʃe�[�u���i�M�p�ڋq�j�̌�������
        StringBuffer l_strWhere = new StringBuffer();
        l_strWhere.append(" account_id = ? ");
        l_strWhere.append(" and created_timestamp = ");
        l_strWhere.append(" ( ");
        l_strWhere.append("  select max(created_timestamp) ");
        l_strWhere.append("  from tp_calc_result_margin ");
        l_strWhere.append("  where account_id = ? ");
        l_strWhere.append("  and mark_to_market_div = ? ");
        l_strWhere.append(" ) ");

        Object[] l_bindVars = { new Long(l_lngAccountId),
                               new Long(l_lngAccountId),
                               WEB3TPMarkToMarketDivDef.NORMAL };       

        log.debug(
                "Finding TpCalcResultMarginParams where="
                    + l_strWhere.toString()
                    + ", bindVars="
                    + objectsToString(l_bindVars));

        //�]�͍X�V���e(List)
        List l_updResults = null;

        try
        {
            /*
             * �ڋq�I�u�W�F�N�g�𐶐�����B 
             */
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_lngAccountId);

            /*
             * "�]�͍Čv�Z�����"���擾����B 
             */
            //"�]�͍Čv�Z�����"
            Timestamp l_updateTimeStamp = null;

            //��ЃR�[�h�A���X�R�[�h���擾
            MainAccountRow l_accountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
            String l_strInstCode = l_accountRow.getInstitutionCode();
            String l_strBranCode = l_accountRow.getBranchCode();

            //�v���Z�XID(0006:�]�͍Čv�Z�����)
            String l_strProcessId = "0006";

            //�v���Z�X�Ǘ��e�[�u��������
            ProcessManagementRow l_processRow = ProcessManagementDao.findRowByProcessIdInstitutionCodeBranchCode(
                    l_strProcessId,
                    l_strInstCode,
                    l_strBranCode);

            if(l_processRow != null)
            {
                l_updateTimeStamp = l_processRow.getLastUpdatedTimestamp();
            }
            
            /*
             * �ŐV�̗]�͌v�Z����Params�I�u�W�F�N�g���擾����B 
             */
            //�]�͌v�Z���ʃe�[�u��<�M�p�ڋq>������
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRow =
                l_qp.doFindAllQuery(
                    TpCalcResultMarginParams.TYPE,
                    l_strWhere.toString(),
                    "calc_result_margin_id desc",
                    null,
                    l_bindVars,
                    new RowType[] {TpCalcResultMarginParams.TYPE});

            //�]�͌v�Z���ʂ��������ꂽ�ꍇ
            if(l_lisRow != null && l_lisRow.size() != 0)
            {
                //�]�͌v�Z����Params���擾
                TpCalcResultMarginParams l_resultParams = (TpCalcResultMarginParams)l_lisRow.get(0);

                /*
                 * �ŐV�̗]�͌v�Z���ʏڍ�Params�I�u�W�F�N�g���擾����B 
                 */
                //[a."�]�͍Čv�Z�����" == null �܂��́A 
                //   "�]�͍Čv�Z�����" <  �]�͌v�Z����Params�I�u�W�F�N�g.�쐬���t] 
                if(l_updateTimeStamp == null
                        || l_updateTimeStamp.before(l_resultParams.getCreatedTimestamp()) == true)
                {
                    //�]�͌v�Z���ʏڍ�Params�I�u�W�F�N�g���擾
                    TpCalcResultMarginDetailParams l_resultDetailParams =
                        (TpCalcResultMarginDetailParams)TpCalcResultMarginDetailDao.findRowByPk(
                            l_resultParams.getCalcResultMarginId());

                    l_updResults = new ArrayList();
                    l_updResults.add(l_resultParams);
                    l_updResults.add(l_resultDetailParams);

                    //�擾�����]�͍X�V���e(List)��ԋp����B
                    log.exiting(STR_METHOD_NAME);
                    return l_updResults;
                }
            }
                
            log.error("TpCalcResultMarginParams:data is not found");

            //�M�p�����J�݋敪���擾
            boolean l_blnMargin =
                l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

            //�����ڋq�̏ꍇ
            if (l_blnMargin == false)
            {
                //�G���[���X���[����
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    STR_METHOD_NAME);
            }

            //�⏕����<�����������(�a���)>�I�u�W�F�N�g���擾����B
            SubAccount l_subAccount =
                l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

            //�]�͌v�Z�����I�u�W�F�N�g�𐶐�����B
            WEB3TPCalcCondition l_calcCond =
                WEB3TPCalcCondition.createCalcConditionStandard(
                    new WEB3GentradeSubAccount(
                        (SubAccountRow)l_subAccount.getDataSourceObject()));

            //�]�͍X�V�I�u�W�F�N�g�𐶐�����B
            WEB3TPTradingPowerUpd l_tpUpd =
                new WEB3TPTradingPowerUpd(l_lngAccountId, l_blnMargin, l_calcCond, null);

            /*
             * �]�͌v�Z����Params�I�u�W�F�N�g���擾
             */
            l_updResults = l_tpUpd.calcTradingpowerUpdResultMargin(WEB3TPMarkToMarketDivDef.NORMAL); 
            //�]�͍X�V���e���e�[�u����Insert
            l_tpUpd.saveTradingpowerUpdResultMargin(l_updResults);

            //�擾�����]�͌v�Z����Params<�M�p�ڋq>�I�u�W�F�N�g��ԋp����
            log.exiting(STR_METHOD_NAME);
            return l_updResults;
        }
        catch (NotFoundException nfe)
        {
            log.error(nfe.getMessage(), nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                nfe.getMessage(),
                nfe);
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }

    /**
     * �ifind�]�͌v�Z����<�M�p�ڋq>Params�j<BR>
     * (static���\�b�h)<BR>
     * <BR>
     * �P�jDB���Y���ڋq�̍ŐV�̗]�͌v�Z����Params<�M�p�ڋq>���P�s�擾����B<BR>
     * <BR>
     * �@@[�Ώۃe�[�u��]<BR>
     * �@@�@@�]�͌v�Z���ʃe�[�u���i�M�p�ڋq�j<BR>
     * <BR>
     * �@@�m���������n<BR>
     * �@@�@@�]�͌v�Z����(�M�p�ڋq)�h�c�F����.�]�͌v�Z���ʂh�c<BR>
     * <BR>
     * �@@�@@���]�͌v�Z����Params���擾�ł��Ȃ������ꍇ�A�G���[���X���[����B<BR>
     * <BR>
     * �Q�jDB���Y���ڋq�̍ŐV�̗]�͌v�Z���ʏڍ�Params<�M�p�ڋq>���P�s�擾����B<BR>
     * <BR>
     * �@@[�Ώۃe�[�u��]<BR>
     * �@@�@@�]�͌v�Z���ʏڍ׃e�[�u���i�M�p�ڋq�j<BR>
     * <BR>
     * �@@�m���������n<BR>
     * �@@�@@�]�͌v�Z����(�M�p�ڋq)ID�F����.�]�͌v�Z���ʂh�c<BR>
     * <BR>
     * �@@���]�͌v�Z���ʏڍ�Params���擾�ł��Ȃ������ꍇ�A�G���[���X���[����B<BR>
     * <BR>
     * �R�jList�ɁA�P�j�Ŏ擾�����]�͌v�Z����Params<�M�p�ڋq>�ƁA<BR>
     *  �Q�j�Ŏ擾�����A�]�͌v�Z���ʏڍ�Params<�M�p�ڋq>���A�i�[���ĕԋp����B<BR>
     * <BR>
     * �@@�@@List�́A0�Ԗڂ̗v�f�F�P�j�Ŏ擾�����A�]�͌v�Z����<�M�p�ڋq>Params<BR>
     * �@@�@@List�́A1�Ԗڂ̗v�f�F�Q�j�Ŏ擾�����A�]�͌v�Z���ʏڍ�<�M�p�ڋq>Params<BR>
     * <BR>
     * @@param l_lngCalcResultId - (�]�͌v�Z����ID)
     * @@return List
     */
    public static List findCalcResultMarginParamsSpecifiedCalcResultId(long l_lngCalcResultId)
    {
        final String STR_METHOD_NAME =
            "WEB3TPTradingPowerCalcMargin.findCalcResultMarginParamsSpecifiedCalcResultId(long)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //�]�͌v�Z���ʃe�[�u��<�M�p�ڋq>������
            TpCalcResultMarginParams l_resultParams =
                (TpCalcResultMarginParams)TpCalcResultMarginDao.findRowByPk(l_lngCalcResultId);

            //�]�͌v�Z���ʏڍ�<�M�p�ڋq>Params���擾
            TpCalcResultMarginDetailParams l_resultDetailParams =
                (TpCalcResultMarginDetailParams)TpCalcResultMarginDetailDao.findRowByPk(
            l_lngCalcResultId);

            List l_updResults = new ArrayList();
            l_updResults.add(l_resultParams);
            l_updResults.add(l_resultDetailParams);

            //�擾�����]�͍X�V���e(List)��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return l_updResults;
        }
        catch (DataFindException dfe)
        {
            log.error(dfe.getMessage(), dfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                dfe.getMessage(),
                dfe);
        }
        catch (DataQueryException dqe)
        {
            log.error(dqe.getMessage(), dqe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                dqe.getMessage(),
                dqe);
        }
        catch (DataNetworkException dne)
        {
            log.error(dne.getMessage(), dne);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                dne.getMessage(),
                dne);
        }
    }

    /**
     * �iget���萔���j<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u���萔���v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * n��0�ȏ�5�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�w����́u���萔���v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * this.�]�͌v�Z���ʏڍ�Params<�M�p�ڋq>.���萔��(T+n)<BR>
     * @@param l_intSpecifiedPoint - (�w���)
     * @@return double
     */
    public double getSetupFee(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "getSetupFee(int)";
        log.entering(STR_METHOD_NAME);

        //���萔��
        double l_dblSetupFee;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //���萔��( T + 0 )���擾����B
                l_dblSetupFee = this.calcResultDetailMargin.getSetupFee();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //���萔��( T + 1 )���擾����B
                l_dblSetupFee = this.calcResultDetailMargin.getSetupFee1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //���萔��( T + 2 )���擾����B
                l_dblSetupFee = this.calcResultDetailMargin.getSetupFee2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //���萔��( T + 3 )���擾����B
                l_dblSetupFee = this.calcResultDetailMargin.getSetupFee3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //���萔��( T + 4 )���擾����B
                l_dblSetupFee = this.calcResultDetailMargin.getSetupFee4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //���萔��( T + 5 )���擾����B
                l_dblSetupFee = this.calcResultDetailMargin.getSetupFee5();
                break;

            default :
                //n��0�ȏ�5�ȉ��łȂ����A0��ԋp����B
                l_dblSetupFee = 0;
        }

        log.exiting(STR_METHOD_NAME);
        //�擾�������萔����ԋp����B
        return l_dblSetupFee;
    }

    /**
     * �iget�����E�t�������j<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�����E�t�������v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * n��0�ȏ�5�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�w����́u�����E�t�������v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * this.�]�͌v�Z���ʏڍ�Params<�M�p�ڋq>.�����E�t������(T+n)<BR>
     * @@param l_intSpecifiedPoint - (�w���)
     * @@return double
     */
    public double getContractInterestLoss(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "getContractInterestLoss(int)";
        log.entering(STR_METHOD_NAME);

        //�����E�t������
        double l_dblContractInterestLoss;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //�����E�t������( T + 0 )���擾����B
                l_dblContractInterestLoss = this.calcResultDetailMargin.getContractInterestLoss();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //�����E�t������( T + 1 )���擾����B
                l_dblContractInterestLoss = this.calcResultDetailMargin.getContractInterestLoss1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //�����E�t������( T + 2 )���擾����B
                l_dblContractInterestLoss = this.calcResultDetailMargin.getContractInterestLoss2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //�����E�t������( T + 3 )���擾����B
                l_dblContractInterestLoss = this.calcResultDetailMargin.getContractInterestLoss3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //�����E�t������( T + 4 )���擾����B
                l_dblContractInterestLoss = this.calcResultDetailMargin.getContractInterestLoss4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //�����E�t������( T + 5 )���擾����B
                l_dblContractInterestLoss = this.calcResultDetailMargin.getContractInterestLoss5();
                break;

            default :
                //n��0�ȏ�5�ȉ��łȂ����A0��ԋp����B
                l_dblContractInterestLoss = 0;
        }

        log.exiting(STR_METHOD_NAME);
        //�擾���������E�t��������ԋp����B
        return l_dblContractInterestLoss;
    }

    /**
     * �iget�����E�t�����v�j<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�����E�t�����v�v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * n��0�ȏ�5�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�w����́u�����E�t�����v�v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * this.�]�͌v�Z���ʏڍ�Params<�M�p�ڋq>.�����E�t�����v(T+n)<BR>
     * @@param l_intSpecifiedPoint - (�w���)
     * @@return double
     */
    public double getContractInterestProfit(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "getContractInterestProfit(int)";
        log.entering(STR_METHOD_NAME);

        //�����E�t�����v
        double l_dblContractInterestProfit;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //�����E�t�����v( T + 0 )���擾����B
                l_dblContractInterestProfit = this.calcResultDetailMargin.getContractInterestProfit();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //�����E�t�����v( T + 1 )���擾����B
                l_dblContractInterestProfit = this.calcResultDetailMargin.getContractInterestProfit1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //�����E�t�����v( T + 2 )���擾����B
                l_dblContractInterestProfit = this.calcResultDetailMargin.getContractInterestProfit2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //�����E�t�����v( T + 3 )���擾����B
                l_dblContractInterestProfit = this.calcResultDetailMargin.getContractInterestProfit3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //�����E�t�����v( T + 4 )���擾����B
                l_dblContractInterestProfit = this.calcResultDetailMargin.getContractInterestProfit4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //�����E�t�����v( T + 5 )���擾����B
                l_dblContractInterestProfit = this.calcResultDetailMargin.getContractInterestProfit5();
                break;

            default :
                //n��0�ȏ�5�ȉ��łȂ����A0��ԋp����B
                l_dblContractInterestProfit = 0;
        }

        log.exiting(STR_METHOD_NAME);
        //�擾���������E�t�����v��ԋp����B
        return l_dblContractInterestProfit;
    }

    /**
     * �iget���̑����ʏ��o��j<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u���̑����ʏ��o��v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * n��0�ȏ�5�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�w����́u���̑����ʏ��o��v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * this.�]�͌v�Z���ʏڍ�Params<�M�p�ڋq>.���̑����ʏ��o��(T+n)<BR>
     * @@param l_intSpecifiedPoint - (�w���)
     * @@return double
     */
    public double getContractOtherCost(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "getContractOtherCost(int)";
        log.entering(STR_METHOD_NAME);

        //���̑����ʏ��o��
        double l_dblContractOtherCost;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //���̑����ʏ��o��( T + 0 )���擾����B
                l_dblContractOtherCost = this.calcResultDetailMargin.getContractOtherCost();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //���̑����ʏ��o��( T + 1 )���擾����B
                l_dblContractOtherCost = this.calcResultDetailMargin.getContractOtherCost1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //���̑����ʏ��o��( T + 2 )���擾����B
                l_dblContractOtherCost = this.calcResultDetailMargin.getContractOtherCost2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //���̑����ʏ��o��( T + 3 )���擾����B
                l_dblContractOtherCost = this.calcResultDetailMargin.getContractOtherCost3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //���̑����ʏ��o��( T + 4 )���擾����B
                l_dblContractOtherCost = this.calcResultDetailMargin.getContractOtherCost4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //���̑����ʏ��o��( T + 5 )���擾����B
                l_dblContractOtherCost = this.calcResultDetailMargin.getContractOtherCost5();
                break;

            default :
                //n��0�ȏ�5�ȉ��łȂ����A0��ԋp����B
                l_dblContractOtherCost = 0;
        }

        log.exiting(STR_METHOD_NAME);
        //�擾�������̑����ʏ��o���ԋp����B
        return l_dblContractOtherCost;
    }

    private static String objectsToString(Object[] l_objBindVars)
    {

        StringBuffer l_sb = new StringBuffer();
        if (l_objBindVars != null)
        {
            for (int i = 0; i < l_objBindVars.length; i++)
            {
                if (i > 0)
                {
                    l_sb.append(",");
                }
                l_sb.append("[").append(i).append("]=");
                l_sb.append(String.valueOf(l_objBindVars[i]));
            }
        }
        return l_sb.toString();
    }
}
@
