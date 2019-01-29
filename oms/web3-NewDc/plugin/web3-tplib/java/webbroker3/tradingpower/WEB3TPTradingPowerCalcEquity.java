head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.56.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerCalcEquity.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Y�]�͏��<�����ڋq>(WEB3TPTradingPowerCalcEquity.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 nakazato(ACT) �V�K�쐬
                   2006/09/11 ���G�� (���u) ���f��No.010 
                   2006/09/11 ���G�� (���u) �v�Z����  No.002-004
Revesion History : 2007/09/24 �И��� (���u) ���f��No.172 �v�Z���� No.008 No.009
                   2007/11/08 inomata (SCS) ���f��No.229 �v�Z���� No.015
Revesion History : 2008/04/01 �����Q (���u) ���f��No.265
Revesion History : 2008/09/10 ������ (���u) �v�Z����No.016-017
Revesion History : 2008/09/10 ���� (���u) ���f��No.299
*/
package webbroker3.tradingpower;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.ProcessManagementDao;
import webbroker3.gentrade.data.ProcessManagementRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.data.TpCalcResultEquityDao;
import webbroker3.tradingpower.data.TpCalcResultEquityDetailDao;
import webbroker3.tradingpower.data.TpCalcResultEquityDetailParams;
import webbroker3.tradingpower.data.TpCalcResultEquityParams;
import webbroker3.tradingpower.define.WEB3TPOrixSecuredLoanLockDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitParams;
import webbroker3.util.WEB3LogUtility;

/**
 * �i���Y�]�͏��<�����ڋq>�j<BR>
 * <BR>
 * �]�͍X�V����<�����ڋq>�����A�e�����\�z���v�Z����N���X<BR>
 * <BR>
 * @@author  nakazato(ACT)
 * @@version 1.0
 */
public class WEB3TPTradingPowerCalcEquity
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPTradingPowerCalcEquity.class);

    /**
     * �i�]�͌v�Z����Params<�����ڋq>�j
     */
    protected TpCalcResultEquityParams calcResultEquity;

    /**
     * �i�]�͌v�Z���ʏڍ�Params<�����ڋq>�j
     */
    protected TpCalcResultEquityDetailParams calcResultDetailEquity;

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
	 * �i���񒍕��o���z�j
	 * 
	 * �����������e.�T�Z���
	 */
	private double thisTimOrderCont = 0.0;

    /**
     * @@roseuid 410DF85D037F
     */
    public WEB3TPTradingPowerCalcEquity()
    {

    }

    /**
     * (�R���X�g���N�^)<BR>
     * �����𑮐��ɃZ�b�g����B<BR>
     * <BR>
     * �P�j����.�]�͌v�Z����<�����ڋq>���A�]�͌v�Z����Params<�����ڋq>�I�u�W�F�N�g���擾���A<BR>
     * �@@this.�]�͌v�Z����Params<�����ڋq>�ɃZ�b�g����B<BR>
     * <BR>
     * �Q�j����.�]�͌v�Z����<�����ڋq>���A�]�͌v�Z���ʏڍ�Params<�����ڋq>�I�u�W�F�N�g���擾���A<BR>
     * �@@this.�]�͌v�Z���ʏڍ�Params<�����ڋq>�ɃZ�b�g����B<BR>
     * <BR>
     * �R�j����.�]�͌v�Z�������Athis.�]�͌v�Z�����ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_calcResult - �i�]�͌v�Z���ʁj
     * @@param l_calcCondition - �i�]�͌v�Z�����j
     */
    public WEB3TPTradingPowerCalcEquity(List l_calcResult, WEB3TPCalcCondition l_calcCondition)
    {
        /*
         * List���]�͌v�Z����<�����ڋq>�I�u�W�F�N�g�A�]�͌v�Z���ʏڍ�<�����ڋq>�I�u�W�F�N�g
         * ���擾���v���p�e�B�ɃZ�b�g����B
         */
        for (Iterator l_iter = l_calcResult.iterator(); l_iter.hasNext();)
        {
            Object l_element = (Object)l_iter.next();

            if (l_element instanceof TpCalcResultEquityParams)
            {
                TpCalcResultEquityParams l_params = (TpCalcResultEquityParams)l_element;
                this.setCalcResultEquity(l_params);
            }
            else if (l_element instanceof TpCalcResultEquityDetailParams)
            {
                TpCalcResultEquityDetailParams l_params = (TpCalcResultEquityDetailParams)l_element;
                this.setCalcResultDetailEquity(l_params);
            }
        }

        //�p�����[�^.�]�͌v�Z�������v���p�e�B�ɃZ�b�g����B
        this.calcCondition = l_calcCondition;
    }

    /**
     * (�R���X�g���N�^)<BR>
     * �����𑮐��ɃZ�b�g����B<BR>
     * <BR>
     * �P�j����.�]�͌v�Z����<�����ڋq>���A�]�͌v�Z����Params<�����ڋq>�I�u�W�F�N�g���擾���A<BR>
     * �@@this.�]�͌v�Z����Params<�����ڋq>�ɃZ�b�g����B<BR>
     * <BR>
     * �Q�j����.�]�͌v�Z����<�����ڋq>���A�]�͌v�Z���ʏڍ�Params<�����ڋq>�I�u�W�F�N�g���擾���A<BR>
     * �@@this.�]�͌v�Z���ʏڍ�Params<�����ڋq>�ɃZ�b�g����B<BR>
     * <BR>
     * �R�j����.�g�p�\���������Athis.�g�p�\�������ɃZ�b�g����<BR>
     * <BR>
     * �S�j����.�]�͌v�Z�������Athis.�]�͌v�Z�����ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_calcResultEquity - �i�]�͌v�Z���ʁj
     * @@param l_actualBalanceInfo - �i�g�p�\�������j
     * @@param l_calcCondition - �i�]�͌v�Z�����j
     */
    public WEB3TPTradingPowerCalcEquity(
        List l_calcResult,
        WEB3TPActualAccountBalanceInfo l_actualBalanceInfo,
        WEB3TPCalcCondition l_calcCondition)
    {
        /*
         * List���]�͌v�Z����<�����ڋq>�I�u�W�F�N�g�A�]�͌v�Z���ʏڍ�<�����ڋq>�I�u�W�F�N�g
         * ���擾���v���p�e�B�ɃZ�b�g����B
         */
        for (Iterator l_iter = l_calcResult.iterator(); l_iter.hasNext();)
        {
            Object l_element = (Object)l_iter.next();

            if (l_element instanceof TpCalcResultEquityParams)
            {
                TpCalcResultEquityParams l_params = (TpCalcResultEquityParams)l_element;
                this.setCalcResultEquity(l_params);
            }
            else if (l_element instanceof TpCalcResultEquityDetailParams)
            {
                TpCalcResultEquityDetailParams l_params = (TpCalcResultEquityDetailParams)l_element;
                this.setCalcResultDetailEquity(l_params);
            }
        }

        //�p�����[�^.�]�͌v�Z�������v���p�e�B�ɃZ�b�g����B
        this.calcCondition = l_calcCondition;

        //�p�����[�^.�g�p�\���������v���p�e�B�ɃZ�b�g����B
        this.actualBalanceInfo = l_actualBalanceInfo;

    }
	/**
	 * (�R���X�g���N�^)<BR>
	 * �����𑮐��ɃZ�b�g����B<BR>
	 * <BR>
	 * �P�jthis(List, �]�͌v�Z����)���R�[������B<BR>
	 * [����]<BR>
	 * List�F ����.�]�͌v�Z����<�����ڋq><BR>
	 * �]�͌v�Z�����F ����.�]�͌v�Z����<BR>
	 * <BR>
	 * �Q�j����.���񒍕��o���z���Athis.���񒍕��o���z�ɃZ�b�g����B<BR>
	 * <BR>
	 * @@param l_calcResult - �i�]�͌v�Z���ʁj
	 * @@param l_calcCondition - �i�]�͌v�Z�����j
	 * @@param l_thisTimOrderCont - �i���񒍕��o���z�j
	 */
	public WEB3TPTradingPowerCalcEquity(
			List l_calcResult, 
			WEB3TPCalcCondition l_calcCondition, 
			double l_thisTimOrderCont)
	{
		this(l_calcResult, l_calcCondition);
		this.thisTimOrderCont = Math.abs(l_thisTimOrderCont);
	}

    /**
     * �iget�]�͌v�Z����Params<�����ڋq>�j<BR>
     * 
     * this.�]�͌v�Z����Params<�����ڋq>��ԋp����B<BR>
     * @@return webbroker3.tradingpower.data.TpCalcResultEquityParams
     * @@roseuid 40FCBE970098
     */
    public TpCalcResultEquityParams getCalcResultEquity()
    {
        //this.�]�͌v�Z����Params��ԋp����B
        return this.calcResultEquity;
    }

    /**
     * �iset�]�͌v�Z����Params<�����ڋq>�j<BR>
     * 
     * �p�����[�^.�]�͌v�Z����Params<�����ڋq>��this.�]�͌v�Z����Params<�����ڋq>�ɃZ�b
     * �g����B<BR>
     * @@param l_calcResultEquity - �i�]�͌v�Z����Params<�����ڋq>�j
     * @@roseuid 40FCBEAC022F
     */
    public void setCalcResultEquity(TpCalcResultEquityParams l_calcResultEquity)
    {
        //�p�����[�^.�]�͌v�Z����Params���v���p�e�B�ɃZ�b�g����B
        this.calcResultEquity = l_calcResultEquity;
    }

    /**
     * �iget�]�͌v�Z���ʏڍ�Params<�����ڋq>�j<BR>
     * 
     * this.�]�͌v�Z���ʏڍ�Params<�����ڋq>��ԋp����B<BR>
     * @@return webbroker3.tradingpower.data.TpCalcResultEquityDetailParams
     */
    public TpCalcResultEquityDetailParams getCalcResultDetailEquity()
    {
        return calcResultDetailEquity;
    }

    /**
     * �iset�]�͌v�Z���ʏڍ�Params<�����ڋq>�j<BR>
     * 
     * �p�����[�^.�]�͌v�Z���ʏڍ�Params<�����ڋq>��this.�]�͌v�Z���ʏڍ�Params<�����ڋq>�ɃZ�b�g����B<BR>
     * @@param l_calcResultDetailEquity - �i�]�͌v�Z���ʏڍ�Params<�����ڋq>�j
     */
    public void setCalcResultDetailEquity(TpCalcResultEquityDetailParams l_calcResultDetailEquity)
    {
        this.calcResultDetailEquity = l_calcResultDetailEquity;
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
     * �iget�]�͌v�Z�����j<BR>
     * 
     * this.�]�͌v�Z������ԋp����B<BR>
     * @@return webbroker3.tradingpower.WEB3TPCalcCondition
     * @@roseuid 40FCBEC100E6
     */
    public WEB3TPCalcCondition getCalcCondition()
    {
        //this.�]�͌v�Z������ԋp����B
        return this.calcCondition;
    }

    /**
     * �iset�]�͌v�Z�����j<BR>
     * 
     * �p�����[�^.�]�͌v�Z������this.�]�͌v�Z�����ɃZ�b�g����B<BR>
     * @@param l_calcCondition - �i�]�͌v�Z�����j
     * @@roseuid 40FCBEC703C5
     */
    public void setCalcCondition(WEB3TPCalcCondition l_calcCondition)
    {
        //�p�����[�^.�]�͌v�Z�������v���p�e�B�ɃZ�b�g����
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
     * ������0�ȏ�5�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�����Ŏw�肳�ꂽ�w���(=n)�́u�a����c���v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n<BR>
     * this.�]�͌v�Z����Params<�����ڋq>.get�a����c���iT+n�j<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B4722D03B1
     */
    public double getAccountBalance(int l_intSpecifiedPoint)
    {
        //�a����c��
        double l_dblAccountBalance;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //�a����c��( T + 0 )���擾����B
                l_dblAccountBalance = this.calcResultEquity.getAccountBalance0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //�a����c��( T + 1 )���擾����B
                l_dblAccountBalance = this.calcResultEquity.getAccountBalance1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //�a����c��( T + 2 )���擾����B
                l_dblAccountBalance = this.calcResultEquity.getAccountBalance2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //�a����c��( T + 3 )���擾����B
                l_dblAccountBalance = this.calcResultEquity.getAccountBalance3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //�a����c��( T + 4 )���擾����B
                l_dblAccountBalance = this.calcResultEquity.getAccountBalance4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //�a����c��( T + 5 )���擾����B
                l_dblAccountBalance = this.calcResultEquity.getAccountBalance5();
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
     * �iget�������ϑ���j<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�������ϑ���v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * ������0�ȏ�5�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�����Ŏw�肳�ꂽ�w���(=n)�́u�������ϑ���v��ԋp����B<BR>
     * �m�ԋp�l�n<BR>
     * this.�]�͌v�Z����Params<�����ڋq>.get�������ϑ���iT+n�j<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B472360305
     */
    public double getTodayExecutedAmount(int l_intSpecifiedPoint)
    {
        //�������ϑ��
        double l_dblTodayExecutedAmount;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //�������ϑ��( T + 0 )���擾����B
                l_dblTodayExecutedAmount = this.calcResultEquity.getTodayExecutedAmount0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //�������ϑ��( T + 1 )���擾����B
                l_dblTodayExecutedAmount = this.calcResultEquity.getTodayExecutedAmount1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //�������ϑ��( T + 2 )���擾����B
                l_dblTodayExecutedAmount = this.calcResultEquity.getTodayExecutedAmount2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //�������ϑ��( T + 3 )���擾����B
                l_dblTodayExecutedAmount = this.calcResultEquity.getTodayExecutedAmount3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //�������ϑ��( T + 4 )���擾����B
                l_dblTodayExecutedAmount = this.calcResultEquity.getTodayExecutedAmount4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //�������ϑ��( T + 5 )���擾����B
                l_dblTodayExecutedAmount = this.calcResultEquity.getTodayExecutedAmount5();
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
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u������������v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * ������0�ȏ�5�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�����Ŏw�肳�ꂽ�w���(=n)�́u������������v��ԋp����B<BR>
     * �m�ԋp�l�n<BR>
     * this.�]�͌v�Z����Params<�����ڋq>.get������������iT+n�j<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B4723E03B1
     */
    public double getTodayUnexecutedAmount(int l_intSpecifiedPoint)
    {
        //�����������
        double l_dblTodayUnexecutedAmount;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //�������ϑ��( T + 0 )���擾����B
                l_dblTodayUnexecutedAmount = this.calcResultEquity.getTodayUnexecutedAmount0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //�������ϑ��( T + 1 )���擾����B
                l_dblTodayUnexecutedAmount = this.calcResultEquity.getTodayUnexecutedAmount1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //�������ϑ��( T + 2 )���擾����B
                l_dblTodayUnexecutedAmount = this.calcResultEquity.getTodayUnexecutedAmount2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //�������ϑ��( T + 3 )���擾����B
                l_dblTodayUnexecutedAmount = this.calcResultEquity.getTodayUnexecutedAmount3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //�������ϑ��( T + 4 )���擾����B
                l_dblTodayUnexecutedAmount = this.calcResultEquity.getTodayUnexecutedAmount4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //�������ϑ��( T + 5 )���擾����B
                l_dblTodayUnexecutedAmount = this.calcResultEquity.getTodayUnexecutedAmount5();
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
     * ������0�ȏ�5�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�����Ŏw�肳�ꂽ�w���(=n)�́u���v��S�����v��ԋp����B<BR>
     *  [�ԋp�l]<BR>
     * this.�]�͌v�Z����Params<�����ڋq>.get���v��S�����iT+n�j<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B4724502F5
     */
    public double getDayTradeRestraint(int l_intSpecifiedPoint)
    {
        //���v��S����
        double l_dblDayTradeRestraint;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //���v��S����( T + 0 )���擾����B
                l_dblDayTradeRestraint = this.calcResultEquity.getDayTradeRestraint0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //���v��S����( T + 1 )���擾����B
                l_dblDayTradeRestraint = this.calcResultEquity.getDayTradeRestraint1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //���v��S����( T + 2 )���擾����B
                l_dblDayTradeRestraint = this.calcResultEquity.getDayTradeRestraint2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //���v��S����( T + 3 )���擾����B
                l_dblDayTradeRestraint = this.calcResultEquity.getDayTradeRestraint3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //���v��S����( T + 4 )���擾����B
                l_dblDayTradeRestraint = this.calcResultEquity.getDayTradeRestraint4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //���v��S����( T + 5 )���擾����B
                l_dblDayTradeRestraint = this.calcResultEquity.getDayTradeRestraint5();
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
     * ������0�ȏ�5�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�����Ŏw�肳�ꂽ�w���(=n)�́u���̑��S�����v��ԋp����B<BR>
     * �m�ԋp�l�n<BR>
     * this.�]�͌v�Z����Params<�����ڋq>.get���̑��S�����iT+n�j<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B472500305
     */
    public double getOtherRestraint(int l_intSpecifiedPoint)
    {
        //���̑��S����
        double l_dblOtherRestraint;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //���̑��S����( T + 0 )���擾����B
                l_dblOtherRestraint = this.calcResultEquity.getOtherRestraint0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //���̑��S����( T + 1 )���擾����B
                l_dblOtherRestraint = this.calcResultEquity.getOtherRestraint1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //���̑��S����( T + 2 )���擾����B
                l_dblOtherRestraint = this.calcResultEquity.getOtherRestraint2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //���̑��S����( T + 3 )���擾����B
                l_dblOtherRestraint = this.calcResultEquity.getOtherRestraint3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //���̑��S����( T + 4 )���擾����B
                l_dblOtherRestraint = this.calcResultEquity.getOtherRestraint4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //���̑��S����( T + 5 )���擾����B
                l_dblOtherRestraint = this.calcResultEquity.getOtherRestraint5();
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
     * �iget�a��،��]���z�j<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�a��،��]���z�v��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B<BR>
     * ������0�ȏ�5�ȉ��łȂ����A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�����Ŏw�肳�ꂽ�w���(=n)�́u�a��،��]���z�v��ԋp����B<BR>
     * �m�ԋp�l�n<BR>
     * this.�]�͌v�Z����Params<�����ڋq>.ge�a��،��]���z�iT+n�j<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B4725602D6
     */
    public double getTrustSecurityAsset(int l_intSpecifiedPoint)
    {
        //�a��،��]���z
        double l_dblTrustSecurityAsset;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //�a��،��]���z( T + 0 )���擾����B
                l_dblTrustSecurityAsset = this.calcResultEquity.getTrustSecurityAsset0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //�a��،��]���z( T + 1 )���擾����B
                l_dblTrustSecurityAsset = this.calcResultEquity.getTrustSecurityAsset1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //�a��،��]���z( T + 2 )���擾����B
                l_dblTrustSecurityAsset = this.calcResultEquity.getTrustSecurityAsset2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //�a��،��]���z( T + 3 )���擾����B
                l_dblTrustSecurityAsset = this.calcResultEquity.getTrustSecurityAsset3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //�a��،��]���z( T + 4 )���擾����B
                l_dblTrustSecurityAsset = this.calcResultEquity.getTrustSecurityAsset4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //�a��،��]���z( T + 5 )���擾����B
                l_dblTrustSecurityAsset = this.calcResultEquity.getTrustSecurityAsset5();
                break;

            default :
                //�G���[���X���[
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getTrustSecurityAsset(int)");
        }

        //�擾�����a��،��]���z��ԋp����B
        return l_dblTrustSecurityAsset;
    }
    
    /**
     * (get�a����S�ۏo���S����)<BR> 
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�a����S�ۏo���S�����v��ԋp����B<BR> 
     * <BR>
     * �P�j�@@�����`�F�b�N���s���B <BR>
     * ������0�ȏ�5�ȉ��łȂ����A0��ԋp����B <BR>
     * <BR>
     * �Q�j�@@�����Ŏw�肳�ꂽ�w���(=n)�́u�a����S�ۏo���S�����v��ԋp����B<BR>
     * <BR>
     * �m�ԋp�l�n <BR>
     * this.�]�͌v�Z����Params<�����ڋq>.ge�a����S�ۏo���S�����iT+n�j<BR>
     * <BR>
     * @@param l_intSpecifiedPoint - (�w���)
     * @@return double
     */
    public double getCashDepositRestraint(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "getCashDepositRestraint(int l_intSpecifiedPoint)";
        log.entering(STR_METHOD_NAME);
        
        //�a����S�ۏo���S����
        double l_dblCashDepositRestraint;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //�a����S�ۏo���S����( T + 0 )���擾����B
                l_dblCashDepositRestraint = this.calcResultEquity.getCashDepositRestraint0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //�a����S�ۏo���S����( T + 1 )���擾����B
                l_dblCashDepositRestraint = this.calcResultEquity.getCashDepositRestraint1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //�a����S�ۏo���S����( T + 2 )���擾����B
                l_dblCashDepositRestraint = this.calcResultEquity.getCashDepositRestraint2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //�a����S�ۏo���S����( T + 3 )���擾����B
                l_dblCashDepositRestraint = this.calcResultEquity.getCashDepositRestraint3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //�a����S�ۏo���S����( T + 4 )���擾����B
                l_dblCashDepositRestraint = this.calcResultEquity.getCashDepositRestraint4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //�a����S�ۏo���S����( T + 5 )���擾����B
                l_dblCashDepositRestraint = this.calcResultEquity.getCashDepositRestraint5();
                break;

            default :
                //�G���[���X���[
                log.error("�p�����[�^�l�s���B");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getCashDepositRestraint(int)");
        }

        //�擾�����a����S�ۏo���S������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_dblCashDepositRestraint;
    }

    /**
     * �icalc�g�p�\�����j<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�g�p�\�����v��ԋp����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B5A85D01BE
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

        //�g�p�\�����i= �a����c�� - �������ϑ�� - ����������� - ���̑��S�����j���v�Z����B
        double l_dblActualAccountBalance =
            this.getAccountBalance(l_intSpecifiedPoint)
                - this.getTodayExecutedAmount(l_intSpecifiedPoint)
                - this.getTodayUnexecutedAmount(l_intSpecifiedPoint)
                - this.getOtherRestraint(l_intSpecifiedPoint);

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
     * @@roseuid 40B5A86A0325
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
        double l_dblDayTradeRestraint;

        //�p�����[�^.�w�����T+0�̎�
        if (l_intSpecifiedPoint == 0)
        {
            //���v��S�����ɁA���v��S����(0)�Ɠ��ʗ��֋����т̑傫��������
            l_dblDayTradeRestraint =
                Math.max(
                    this.getDayTradeRestraint(l_intSpecifiedPoint),
                    this.calcCondition.getSpecialDebitAmount());
        }
        //����ȊO
        else
        {
            //���v��S�����ɁA���v��S����(T+0)����
            l_dblDayTradeRestraint = this.getDayTradeRestraint(l_intSpecifiedPoint);
        }

        //���o�\�����i= �g�p�\���� - ���v��S�����j���v�Z����B
        double l_dblActualPaymentBalance =
            this.calcActualAccountBalance(l_intSpecifiedPoint) - l_dblDayTradeRestraint;

        //�v�Z�������o�\������ԋp����B
        return l_dblActualPaymentBalance;
    }

    /**
     * �icalc�������t�\�z�j<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�������t�\�z�v��ԋp����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B47344021C
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

        //�������t�\�z�i= �g�p�\���� + �a��،��]���z�j���v�Z����B
        double l_dblEquityTradingPower =
            this.calcActualAccountBalance(l_intSpecifiedPoint)
                + this.getTrustSecurityAsset(l_intSpecifiedPoint);

        //�v�Z�����������t�\�z��ԋp����B
        return l_dblEquityTradingPower;
    }

    /**
     * �icalc�������t�\�z<���v��S�����l��>�j<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�������t�\�z<���v��S�����l��>�v��ԋp����B<B
     * R>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B4735B00A5
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

        //�������t�\�z<���v��S����>�i= ���o�\���� + �a��،��]���z�j���v�Z����B
        double l_dblEquityTradingPower =
            this.calcActualPaymentBalance(l_intSpecifiedPoint)
                + this.getTrustSecurityAsset(l_intSpecifiedPoint);

        //�v�Z�����������t�\�z��ԋp����B
        return l_dblEquityTradingPower;
    }

    /**
     * �icalc���̑����i���t�\�z)<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́u���̑����i���t�\�z�v���v�Z����B<BR>
     * this.calc�g�p�\����(int)��ԋp����
     * <BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    public double calcOtherTradingPower(int l_intSpecifiedPoint)
    {
            //�g�p�\������ԋp����B
            return calcActualAccountBalance( l_intSpecifiedPoint );
    }

    /**
     * �icalc���̑����i���t�\�z<���v�S�����l��>)<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́u���̑����i���t�\�z�v���v�Z����B<BR>
     * this.calc���o�\����(int)��ԋp����
     * <BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    public double calcOtherTradingPowerIncDayTrade(int l_intSpecifiedPoint)
    {
            //���o�\������ԋp����B
            return calcActualPaymentBalance( l_intSpecifiedPoint );
    }
    
    /**
     * (calc�o���\�z)<BR> 
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́u�o���\�z�v���v�Z����B <BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
     * <BR>
     * @@param l_intSpecifiedPoint - (�w���)
     * @@return double
     */
    public double calcPaymentTradingPower(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "calcPaymentTradingPower(int)";
        log.entering(STR_METHOD_NAME);
        
        // �E    �g�p�\����(n)  ���Y�]�͏��<�����ڋq>.calc�g�p�\����(n)
        double l_dblActualAccountBalance = 
            this.calcActualAccountBalance(l_intSpecifiedPoint);
        
        // �E    �a����S�ۏo���S����(n) ���Y�]�͏��<�����ڋq>.get�a����S�ۏo���S����(n)
        double l_dblCashDepositRestraint = 
            this.getCashDepositRestraint(l_intSpecifiedPoint);
        
        // �@@�@@�o���\�z(�w���)�����߂�B
        // �o���\�z(n) =�@@�g�p�\����(n)�@@-�@@�a����S�ۏo���S����(n)
        // ��    n�́A�����̎w����B
        double l_dblPaymentTradingPower = 
            l_dblActualAccountBalance - l_dblCashDepositRestraint;
       
        // �A�@@�v�Z�����o���\�z(n)��ԋp����
        log.exiting(STR_METHOD_NAME);
        return l_dblPaymentTradingPower;
    }
    
    /**
     * (calc�o���\�z<���v��S�����l��>) <BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́u�o���\�z�v���v�Z����B <BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB <BR>
     * <BR>
     * @@param l_intSpecifiedPoint - (�w���)
     * @@return double
     */
    public double calcPaymentTradingPowerIncDayTrade(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "calcPaymentTradingPowerIncDayTrade(int)";
        log.entering(STR_METHOD_NAME);
        
        //��   �v�Z���Ɏg�p����e�l�̎擾���@@
        //�E   ���o�\����(n)       �E�E�E���Y�]�͏��<�����ڋq>.calc���o�\����(n)
        double l_dblActualPaymentBalance = 
            this.calcActualPaymentBalance(l_intSpecifiedPoint);
        
        //�E   �a����S�ۏo���S����(n)   �E�E�E���Y�]�͏��<�����ڋq>.get�a����S�ۏo���S����(n)
        double l_dblCashDepositRestraint = 
            this.getCashDepositRestraint(l_intSpecifiedPoint);
        
        //�@@�@@�o���\�z<���v��S�����l��>(�w���)�����߂�B
        //�o���\�z<���v��S�����l��>(n) =�@@���o�\����(n) - �a����S�ۏo���S����(n)
        double l_dblPaymentTradingPowerIncDayTrade = 
            l_dblActualPaymentBalance - l_dblCashDepositRestraint;
        
        //�A�@@�v�Z�����o���\�z<���v��S�����l��>(n)��ԋp����
        log.exiting(STR_METHOD_NAME);
        return l_dblPaymentTradingPowerIncDayTrade;
    }

    /**
     * �icalc�o���\�z�j<BR>
     * 
     * �ŏ��́u�o���\�z�v���v�Z����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
     * @@return WEB3TPCalcResult
     * @@roseuid 40DFEB840029
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
     * <BR>
     * �ŏ��́u���M���t�\�z�v���v�Z����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
     * @@return WEB3TPCalcResult
     * @@roseuid 40DFEB900394
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
     * @@roseuid 40DFEB9E00F4
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

        //���������z(n) = Abs(Min(���o�\����(n), 0))
        double calcDemandamount = Math.min(this.calcActualPaymentBalance(l_intSpecifiedPoint), 0);
        calcDemandamount = Math.abs(calcDemandamount);        

        return calcDemandamount;
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

        //���֋�(n) = Abs(Min(�g�p�\����(n), 0))
        double l_dblDebitAmount = Math.min(this.calcActualAccountBalance(l_intSpecifiedPoint), 0);
        l_dblDebitAmount = Math.abs(l_dblDebitAmount);        

        return l_dblDebitAmount;
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

        //���֋�(n) = Abs(Min(���o�\����(n), 0))
        double l_dblSpecialDebitAmount = Math.min(this.calcActualPaymentBalance(l_intSpecifiedPoint), 0);
        l_dblSpecialDebitAmount = Math.abs(l_dblSpecialDebitAmount);        

        return l_dblSpecialDebitAmount;
    }

    /**
     * �icalc�K�p�������t�\�z�j<BR>
     * 
     * �ŏ��́u�������t�\�z�v���v�Z����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
     * @@return WEB3TPCalcResult
     * @@roseuid 40BFFD9D00EA
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
                //�a��،��]�����̎�
                if (this.calcCondition.isAssetEvalDiv() == true)
                {
                    //�������t�\�z���v�Z����B
                    l_bdCurEquityTradingPower = new BigDecimal(this.calcEquityTradingPower(index));
                }
                //�a��،��]�����łȂ���
                else
                {
                    //�g�p�\�������v�Z����B
                    l_bdCurEquityTradingPower =
                        new BigDecimal(this.calcActualAccountBalance(index));
                }
            }
            //����ȊO
            else
            {
                //�a��،��]�����̎�
                if (this.calcCondition.isAssetEvalDiv() == true)
                {
                    //�������t�\�z<���v��S�����l��>���v�Z����B
                    l_bdCurEquityTradingPower =
                        new BigDecimal(this.calcEquityTradingPowerIncDayTrade(index));
                }
                //�a��،��]�����łȂ���
                else
                {
                    //���o�\�������v�Z����B
                    l_bdCurEquityTradingPower =
                        new BigDecimal(this.calcActualPaymentBalance(index));
                }
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
    
        /*
         * ���o�\�������擾����B
         */
        //���o�\�����i�ŏ��l�����߂����̂�double�^�̍ő�l�������l�ɃZ�b�g�j
        double l_dblActualPaymentBalance = Double.MAX_VALUE;
        //���o�\����(BigDecimal�^)
        BigDecimal l_bdActualPaymentBalance = new BigDecimal(l_dblActualPaymentBalance);
        //�K�p��<���o�\����>
        int l_intActPayAppliedPoint = 0;
    
        //�n�_(�w����͈̔�) = T+0
        int l_intStart = WEB3TPSpecifiedPointDef.T_0;
        //�I�_(�w����͈̔�)
        int l_intEnd;

        //�a��،��]�����ڋq�̏ꍇ
        if(this.calcCondition.isAssetEvalDiv() == true)
        {
            //�I�_(�w����͈̔�) = ������<�����^�M�p>
            l_intEnd = this.calcCondition.getEquityBizDate();
        }
        //�a��،���]�����ڋq�̏ꍇ
        else
        {
            //�I�_(�w����͈̔�) = �]�͌v�Z���<�������t�^�M�p����>-1
            l_intEnd = l_intBasePoint - 1;
        }

        //�w����͈̔́i�n�_�`�I�_�j�Ń��[�v
        for(int index = l_intStart; index <= l_intEnd; index++)
        {
            //���o�\����(����)(BigDecimal�^)
            BigDecimal l_bdCurActualPaymentBalance = new BigDecimal(this
                .calcActualPaymentBalance(index));

            //���o�\����(����)�����o�\������菬������
            if(l_bdCurActualPaymentBalance.compareTo(l_bdActualPaymentBalance) < 0)
            {
                //���o�\�����Ɉ��o�\����(����)��������B
                l_bdActualPaymentBalance = l_bdCurActualPaymentBalance;
                //�K�p���ɍ��Ԃ�������B
                l_intActPayAppliedPoint = index;
            }
        }
    
        /*
         * �擾�����A�������t�\�z�A���o�\������� �ŏI�I�Ȋ������t�\�z���v�Z����B
         */
        //�ma. �������t�\�z < 0�̏ꍇ�n
        if(l_bdEquityTradingPower.compareTo(new BigDecimal(0.0)) < 0)
        {
            //�������t�\�z = -1
            l_bdEquityTradingPower = new BigDecimal(-1.0);
        }

        //�ma. ���o�\�z < 0�̏ꍇ�n
        if(l_bdActualPaymentBalance.compareTo(new BigDecimal(0.0)) < 0)
        {
            //[b.�������t�\�z >= 0 �̏ꍇ]
            if(l_bdEquityTradingPower.compareTo(new BigDecimal(0.0)) >= 0)
            {
                //�������t�\�z = -1
                l_bdEquityTradingPower = new BigDecimal(-1.0);
                //�K�p�� = �K�p��<���o�\����>
                l_intAppliedPoint = l_intActPayAppliedPoint;
            }
            //[b.�������t�\�z < 0 ���� �K�p�� > �K�p��<���o�\����> �̏ꍇ]
            else if(l_bdEquityTradingPower.compareTo(new BigDecimal(0.0)) < 0
                    && l_intAppliedPoint > l_intActPayAppliedPoint)
            {
                //�������t�\�z = -1
                l_bdEquityTradingPower = new BigDecimal(-1.0);
                //�K�p�� = �K�p��<���o�\����>
                l_intAppliedPoint = l_intActPayAppliedPoint;
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
     * @@return WEB3TPCalcResult
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
                //�a��،��]�����̎�
                if (this.calcCondition.isAssetEvalDiv() == true)
                {
                    //�������t�\�z���v�Z����B
                    l_bdCurEquityTradingPower = new BigDecimal(this.calcEquityTradingPower(index));
                }
                //�a��،��]�����łȂ���
                else
                {
                    //�g�p�\�������v�Z����B
                    l_bdCurEquityTradingPower =
                        new BigDecimal(this.calcActualAccountBalance(index));
                }
            }
            //����ȊO
            else
            {
                //�a��،��]�����̎�
                if (this.calcCondition.isAssetEvalDiv() == true)
                {
                    //�������t�\�z<���v��S�����l��>���v�Z����B
                    l_bdCurEquityTradingPower =
                        new BigDecimal(this.calcEquityTradingPowerIncDayTrade(index));
                }
                //�a��،��]�����łȂ���
                else
                {
                    //���o�\�������v�Z����B
                    l_bdCurEquityTradingPower =
                        new BigDecimal(this.calcActualPaymentBalance(index));
                }
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
                //�a��،��]�����̎�
                if (this.calcCondition.isAssetEvalDiv() == true)
                {
                    //�������t�\�z���v�Z����B
                    l_bdCurEquityTradingPower = new BigDecimal(this.calcEquityTradingPower(index));
                }
                //�a��،��]�����łȂ���
                else
                {
                    //�g�p�\�������v�Z����B
                    l_bdCurEquityTradingPower =
                        new BigDecimal(this.calcActualAccountBalance(index));
                }
            }
            //����ȊO
            else
            {
                //�a��،��]�����̎�
                if (this.calcCondition.isAssetEvalDiv() == true)
                {
                    //�������t�\�z<���v��S�����l��>���v�Z����B
                    l_bdCurEquityTradingPower =
                        new BigDecimal(this.calcEquityTradingPowerIncDayTrade(index));
                }
                //�a��،��]�����łȂ���
                else
                {
                    //���o�\�������v�Z����B
                    l_bdCurEquityTradingPower =
                        new BigDecimal(this.calcActualPaymentBalance(index));
                }
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
    
        /*
         * ���o�\�������擾����B
         */
        //���o�\�����i�ŏ��l�����߂����̂�double�^�̍ő�l�������l�ɃZ�b�g�j
        double l_dblActualPaymentBalance = Double.MAX_VALUE;
        //���o�\����(BigDecimal�^)
        BigDecimal l_bdActualPaymentBalance = new BigDecimal(
                l_dblActualPaymentBalance);
        //�K�p��<���o�\����>
        int l_intActPayAppliedPoint = 0;

        //�n�_(�w����͈̔�) = T+0
        int l_intStart = WEB3TPSpecifiedPointDef.T_0;
        //�I�_(�w����͈̔�)
        int l_intEnd;

        //�a��،��]�����ڋq�̏ꍇ
        if(this.calcCondition.isAssetEvalDiv() == true)
        {
            //�I�_(�w����͈̔�) = ������<�����^�M�p>
            l_intEnd = this.calcCondition.getEquityBizDate();
        }
        //�a��،���]�����ڋq�̏ꍇ
        else
        {
            //�I�_(�w����͈̔�) = �]�͌v�Z���<�������t�^�M�p����>-1
            l_intEnd = l_intBasePoint - 1;
        }

        //�w����͈̔́i�n�_�`�I�_�j�Ń��[�v
        for(int index = l_intStart; index <= l_intEnd; index++)
        {
            //���o�\����(����)(BigDecimal�^)
            BigDecimal l_bdCurActualPaymentBalance = new BigDecimal(this
                .calcActualPaymentBalance(index));

            //���o�\����(����)�����o�\������菬������
            if(l_bdCurActualPaymentBalance.compareTo(l_bdActualPaymentBalance) < 0)
            {
                //���o�\�����Ɉ��o�\����(����)��������B
                l_bdActualPaymentBalance = l_bdCurActualPaymentBalance;
                //�K�p���ɍ��Ԃ�������B
                l_intActPayAppliedPoint = index;
            }
        }

        /*
         * �擾�����A�������t�\�z�A���o�\������� �ŏI�I�Ȋ������t�\�z���v�Z����B
         */
        //�ma. �������t�\�z < 0�̏ꍇ�n
        if(l_bdEquityTradingPower.compareTo(new BigDecimal(0.0)) < 0)
        {
            //�������t�\�z = -1
            l_bdEquityTradingPower = new BigDecimal(-1.0);
        }

        //�ma. ���o�\�z < 0�̏ꍇ�n
        if(l_bdActualPaymentBalance.compareTo(new BigDecimal(0.0)) < 0)
        {
            //[b.�������t�\�z >= 0 �̏ꍇ]
            if(l_bdEquityTradingPower.compareTo(new BigDecimal(0.0)) >= 0)
            {
                //�������t�\�z = -1
                l_bdEquityTradingPower = new BigDecimal(-1.0);
                //�K�p�� = �K�p��<���o�\����>
                l_intAppliedPoint = l_intActPayAppliedPoint;
            }
            //[b.�������t�\�z < 0 ���� �K�p�� > �K�p��<���o�\����> �̏ꍇ]
            else if(l_bdEquityTradingPower.compareTo(new BigDecimal(0.0)) < 0
                    && l_intAppliedPoint > l_intActPayAppliedPoint)
            {
                //�������t�\�z = -1
                l_bdEquityTradingPower = new BigDecimal(-1.0);
                //�K�p�� = �K�p��<���o�\����>
                l_intAppliedPoint = l_intActPayAppliedPoint;
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
     * �icalc�K�p�������t�\�z)<BR>
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
     * �|this.�]�͌v�Z����.set�]�͌v�Z���<��������/�M�p����>()���R�[��<BR>
     * <BR>
     * �@@�m�����n<BR>
     * �@@int�F�P�j�̖߂�l<BR>
     *<BR>
     *  �T�j�R�j�̖߂�l��ԋp����B<BR>
     * <BR>
     * @@param l_intBasePoint
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
     * �icalc�K�p���̑����i���t�\�z�j<BR>
     * 
     * �����̒�����ʂɊY������ŏ��́u���̑����i���t�\�z�v���v�Z����B<BR>
     * ����.������ʂ��w�肳��ȂȂ��������i�������=null�j�A�������=OrderTypeEnum.���̑��Ƃ���B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
     * @@param l_orderTypeEnum - �i������ʁj
     * OrderTypeEnum�ɂĒ�`
     * @@return WEB3TPCalcResult
     * @@roseuid 40DBAEA100B2
     */
    public WEB3TPCalcResult calcAppliedOtherTradingPower(OrderTypeEnum l_orderTypeEnum)
    {
        /*
         * �]�͌v�Z�����ݒ肷��B
         */
    
        //�]�͌v�Z���
        int l_intBasePoint;
    
        //�p�����[�^.������ʂ�null�A�؋����U�ցA�ב֕ۏ؋��U�ցA���������U�� �܂��� ��؋��ւ̐U��
        //�܂��� �I���b�N�X�N���W�b�g�ւ̐U�� �܂��́@@CFD�U�֒����i�a�������CFD�����̎�
        if (l_orderTypeEnum == null 
                || l_orderTypeEnum.equals(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN) == true
                || l_orderTypeEnum.equals(OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT) == true
                || l_orderTypeEnum.equals(OrderTypeEnum.TRANSFER_TO_FEQ) == true
                || l_orderTypeEnum.equals(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_DSK) == true
                || l_orderTypeEnum.equals(OrderTypeEnum.TO_ORIX_CREDIT) == true
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
            //�ma. ����.������� =�@@�o�������@@�܂��́@@�؋����ւ̐U�ց@@
            //�܂��́@@�ב֕ۏ؋��ւ̐U�ց@@�܂��́@@�O�������ւ̐U�ց@@�܂��� �I���b�N�X�N���W�b�g�ւ̐U�� 
            //�܂��́@@CFD�U�֒����i�a�������CFD����)�̎��n
            if(l_orderTypeEnum != null
                    && (l_orderTypeEnum.equals(OrderTypeEnum.CASH_OUT) == true
                            || l_orderTypeEnum.equals(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN) == true
                            || l_orderTypeEnum.equals(OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT) == true 
                            || l_orderTypeEnum.equals(OrderTypeEnum.TRANSFER_TO_FEQ) == true
                            || l_orderTypeEnum.equals(OrderTypeEnum.TO_ORIX_CREDIT) == true
                            || l_orderTypeEnum.equals(OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT) == true))
            {
                if (index == l_intBasePoint)
                {
                    //���̑����i���t�\�z(n)���擾����
                    l_bdCurOtherTradingPower = new BigDecimal(this.calcPaymentTradingPower(index));
                }
                //����ȊO
                else
                {
                    //���̑����i���t�\�z<���v��S�����l��>(n)���擾����
                    l_bdCurOtherTradingPower =
                        new BigDecimal(this.calcPaymentTradingPowerIncDayTrade(index));
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
            else
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
        }
    
        /*
         * ���o�\�������擾����B
         */
        //���o�\�����i�ŏ��l�����߂����̂�double�^�̍ő�l�������l�ɃZ�b�g�j
        double l_dblActualPaymentBalance = Double.MAX_VALUE;
        //���o�\����(BigDecimal�^)
        BigDecimal l_bdActualPaymentBalance = new BigDecimal(
                l_dblActualPaymentBalance);
        //�K�p��<���o�\����>
        int l_intActPayAppliedPoint = 0;

        //�w����͈̔́i0�`�]�͌v�Z���<�������t�^�M�p����>-1�j�Ń��[�v
        for (int index = WEB3TPSpecifiedPointDef.T_0; index < l_intBasePoint; index++)
        {
            //���o�\����(����)(BigDecimal�^)
            BigDecimal l_bdCurActualPaymentBalance = new BigDecimal(this
                .calcActualPaymentBalance(index));

            //���o�\����(����)�����o�\������菬������
            if(l_bdCurActualPaymentBalance.compareTo(l_bdActualPaymentBalance) < 0)
            {
                //���o�\�����Ɉ��o�\����(����)��������B
                l_bdActualPaymentBalance = l_bdCurActualPaymentBalance;
                //�K�p���ɍ��Ԃ�������B
                l_intActPayAppliedPoint = index;
            }
        }
        
        /*
         * �擾�����A���̑����i���t�\�z�A���o�\�������
         * �ŏI�I�Ȃ��̑����i���t�\�z���v�Z����B
         */
        //�ma. ���̑����i���t�\�z < 0�̏ꍇ�n
        if(l_bdOtherTradingPower.compareTo(new BigDecimal(0.0)) < 0)
        {
            //���̑����i���t�\�z = -1
            l_bdOtherTradingPower = new BigDecimal(-1.0);
        }

        //�ma. ���o�\�z < 0�̏ꍇ�n
        if(l_bdActualPaymentBalance.compareTo(new BigDecimal(0.0)) < 0)
        {
            //[b.���̑����i���t�\�z >= 0 �̏ꍇ]
            if(l_bdOtherTradingPower.compareTo(new BigDecimal(0.0)) >= 0)
            {
                //���̑����i���t�\�z = -1
                l_bdOtherTradingPower = new BigDecimal(-1.0);
                //�K�p�� = �K�p��<���o�\����>
                l_intAppliedPoint = l_intActPayAppliedPoint;
            }
            //[b.���̑����i���t�\�z < 0 ���� �K�p�� > �K�p��<���o�\����> �̏ꍇ]
            else if(l_bdOtherTradingPower.compareTo(new BigDecimal(0.0)) < 0
                    && l_intAppliedPoint > l_intActPayAppliedPoint)
            {
                //���̑����i���t�\�z = -1
                l_bdOtherTradingPower = new BigDecimal(-1.0);
                //�K�p�� = �K�p��<���o�\����>
                l_intAppliedPoint = l_intActPayAppliedPoint;
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
        //���̑����i���t�]�͒�~�敪 == true ���� ����.������� not in (
        //�o���A�؋����U�ցA�ב֕ۏ؋��U�ցA���������U�ցA��؋��ւ̐U�ցA���b�N�X�N���W�b�g�ցA
        //CFD�U�֒����i�a�������CFD����))�̏ꍇ
        if (this.calcCondition.isOtherTradingStop() == true
            && !(OrderTypeEnum.CASH_OUT.equals(l_orderTypeEnum)
                || OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderTypeEnum)
                || OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)
                || OrderTypeEnum.TRANSFER_TO_FEQ.equals(l_orderTypeEnum)
                || OrderTypeEnum.FROM_DEPOSIT_AMOUNT_DSK.equals(l_orderTypeEnum)
                || OrderTypeEnum.TO_ORIX_CREDIT.equals(l_orderTypeEnum)
                || OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)))
        {
            l_bdOtherTradingPower = new BigDecimal(-1.0);
        }
        //�o���]�͒�~�� ���� ����.�������in (�o���A�؋����U�ցA�ב֕ۏ؋��U�ցA
        //���������U�ցA��؋��ւ̐U�փI�A���b�N�X�N���W�b�g�ւ̐U�ցA
        //CFD�U�֒����i�a�������CFD����))�̏ꍇ
        if (this.calcCondition.isPaymentStop() == true
            && (OrderTypeEnum.CASH_OUT.equals(l_orderTypeEnum)
                || OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderTypeEnum)
                || OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)
                || OrderTypeEnum.TRANSFER_TO_FEQ.equals(l_orderTypeEnum)
                || OrderTypeEnum.FROM_DEPOSIT_AMOUNT_DSK.equals(l_orderTypeEnum)
                || OrderTypeEnum.TO_ORIX_CREDIT.equals(l_orderTypeEnum)
                || OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)))          
        {
            l_bdOtherTradingPower = new BigDecimal(-1.0);
        }
        
        // [�a����S�ۏo����~�敪 == true �@@����
        //����.������� in �i�o���A�؋����ւ̐U�ցA�ב֕ۏ؋��ւ̐U�ցA���������ւ̐U�ցA���b�N�X�N���W�b�g�ցA
        //CFD�U�֒����i�a�������CFD�����j�j�̏ꍇ]
        //���̑����i���t�\�z = -1���Z�b�g����B
        if (this.calcCondition.isCashDepositStopDiv() == true
            && (OrderTypeEnum.CASH_OUT.equals(l_orderTypeEnum)
                || OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderTypeEnum)
                || OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)
                || OrderTypeEnum.TRANSFER_TO_FEQ.equals(l_orderTypeEnum)
                || OrderTypeEnum.TO_ORIX_CREDIT.equals(l_orderTypeEnum)
                || OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)))             
        {
            l_bdOtherTradingPower = new BigDecimal(-1.0);
        }

        //���̑����i���t�\�z<�،��S�ۃ��[���l��>�����߂�B
        //[a.���̑����i���t�\�z >= 0 ����
        //����.������� in �i�o���A�؋����ւ̐U�ցA�ב֕ۏ؋��ւ̐U�ցA���������ւ̐U�ցA��؋��ւ̐U�ցA
        //CFD�U�֒����i�a�������CFD�����j) ����
        //�،��S�ۃ��[�������J�ݍόڋq�i�S�ۃ��[���o���S�������R�[�h�L�j==true �̏ꍇ]
        if (l_bdOtherTradingPower.compareTo(new BigDecimal(0.0)) >= 0
           && (OrderTypeEnum.CASH_OUT.equals(l_orderTypeEnum)
               || OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderTypeEnum)
               || OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)
               || OrderTypeEnum.TRANSFER_TO_FEQ.equals(l_orderTypeEnum)
               || OrderTypeEnum.FROM_DEPOSIT_AMOUNT_DSK.equals(l_orderTypeEnum)
               || OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum))
           && this.calcCondition.isOrixSecuredLoanAccOpenDiv())
        {
            //[b.���z���b�N�t���O == 1 �̏ꍇ]
            if (WEB3TPOrixSecuredLoanLockDef.ORIX_SECURED_LOAN.equals(
                this.calcCondition.getOrixSecuredLoanLockFlag()))
            {
                //���̑����i���t�\�z = -1 ���Z�b�g����B
                l_bdOtherTradingPower = new BigDecimal(-1.0);
            }

            //�I���b�N�X�N���W�b�g��o���\�z
            BigDecimal l_dbOrixCredit = new BigDecimal("0");
            try
            {
                l_dbOrixCredit = new BigDecimal("" + this.calcOrixCreditPaymentPower());
            }
            catch (WEB3SystemLayerException l_ex)
            {
                //�G���[���X���[
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + ".calcAppliedOtherTradingPower(OrderTypeEnum)");
            }
            //[b.�I���b�N�X�N���W�b�g��o���\�z >= 0 �̏ꍇ]
            if (l_dbOrixCredit.compareTo(new BigDecimal("0")) >= 0)
            {
                l_bdOtherTradingPower = l_bdOtherTradingPower.min(l_dbOrixCredit);
            }
            else
            {
                //���̑����i���t�\�z = -1 ���Z�b�g����B
                l_bdOtherTradingPower = new BigDecimal(-1.0);
            }
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
     * �@@�@@�@@�|this.�]�͌v�Z����.get�]�͌v�Z���<���M���t>()���R�[��<BR> 
     * <BR>
     * �@@�@@[a.����.������� == 1001�F�o�� �̏ꍇ] <BR>
     * �@@�@@�@@?this.�]�͌v�Z����.get�]�͌v�Z���<�o��>()���R�[��<BR> 
     * <BR>
     * �@@�@@[a.����.������� == 605�FOP�V�K���� �̏ꍇ] <BR>
     * �@@�@@�@@?this.�]�͌v�Z����.get�]�͌v�Z���<�I�v�V�����V�K����>()���R�[��<BR> 
     * <BR>
     * �@@�@@[a.����.������� == 1007�F�؋����ւ̐U��, 1011:�ב֕ۏ؋��ւ̐U��,<BR>
     * �@@�@@�@@�@@1013:�O�������ւ̐U��,1020:�I���b�N�X�N���W�b�g�ւ̐U��,<BR>
     * �@@�@@�@@�@@1021:CFD�U�֒����i�a�������CFD�����j]<BR>
     * �@@�@@�@@?this.�]�͌v�Z����.get�]�͌v�Z���<���̑����t>()���R�[�� <BR>
     * <BR>
     * �@@�@@[a.�ȊO�̏ꍇ] <BR>
     * �@@�@@�@@?this.�]�͌v�Z����.get�]�͌v�Z���<���̑����t>()���R�[��<BR> 
     * <BR>
     * �Q�j�]�͌v�Z����ɁA����.������Z�b�g����B <BR>
     * �@@�@@[a.����.������� == 201�F���M���t�A203�F���M��W�A204�F���M�抷 �̏ꍇ] <BR>
     * �@@�@@�@@�|this.�]�͌v�Z����.set�]�͌v�Z���<���M���t>()���R�[�� <BR>
     * <BR>
     * �@@�@@[a.����.������� == 1001�F�o�� �̏ꍇ] <BR>
     * �@@�@@�@@?this.�]�͌v�Z����.set�]�͌v�Z���<�o��>()���R�[�� <BR>
     * <BR>
     * �@@�@@[a.����.������� == 605�FOP�V�K���� �̏ꍇ] <BR>
     * �@@�@@�@@?this.�]�͌v�Z����.set�]�͌v�Z���<�I�v�V�����V�K����>()���R�[�� <BR>
     * <BR>
     * �@@�@@[a.����.������� == 1007�F�؋����ւ̐U��, 1011:�ב֕ۏ؋��ւ̐U��,<BR>
     * �@@�@@�@@�@@1013:�O�������ւ̐U��,1020:�I���b�N�X�N���W�b�g�ւ̐U��,<BR>
     * �@@�@@�@@�@@1021:CFD�U�֒����i�a�������CFD�����j] <BR>
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
     * �@@�@@�@@������ʁF����.������� �A���Aa.�ȊO�̏ꍇ�ɂ��Ă�null <BR>
     * <BR>
     * �S�j�ޔ����Ă����A�����߂� <BR>
     * �@@�@@[a.����.������� == 201�F���M���t�A203�F���M��W�A204�F���M�抷 �̏ꍇ] <BR>
     * �@@�@@�@@�|this.�]�͌v�Z����.get�]�͌v�Z���<���M���t>()���R�[�� <BR>
     * <BR>
     * �@@�@@[a.����.������� == 1001�F�o�� �̏ꍇ] <BR>
     * �@@�@@�@@?this.�]�͌v�Z����.get�]�͌v�Z���<�o��>()���R�[�� <BR>
     * <BR>
     * �@@�@@[a.����.������� == 605�FOP�V�K���� �̏ꍇ] <BR>
     * �@@�@@�@@?this.�]�͌v�Z����.get�]�͌v�Z���<�I�v�V�����V�K����>()���R�[�� <BR>
     * <BR>
     * �@@�@@[a.����.������� == 1007�F�؋����ւ̐U��, 1011:�ב֕ۏ؋��ւ̐U��,<BR>
     * �@@�@@�@@�@@1013:�O�������ւ̐U��,1020:�I���b�N�X�N���W�b�g�ւ̐U��,<BR>
     * �@@�@@�@@�@@1021:CFD�U�֒����i�a�������CFD�����j] <BR>
     * �@@�@@�@@-this.�]�͌v�Z����.set�]�͌v�Z���<���̑����t>()���R�[�� <BR>
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
        //����.������� == 1007�F�؋����ւ̐U��, 1011:�ב֕ۏ؋��ւ̐U��,1013:�O�������ւ̐U��,
        //1020:�I���b�N�X�N���W�b�g�ւ̐U��, 1021:CFD�U�֒����i�a�������CFD�����j
        else if (OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderTypeEnum)
                || OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)
                || OrderTypeEnum.TRANSFER_TO_FEQ.equals(l_orderTypeEnum)
                || OrderTypeEnum.TO_ORIX_CREDIT.equals(l_orderTypeEnum)
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
     * (calc�������t�\�z�`�A�������`) <BR>
     * <BR>
     * �ŏ��́u�������t�\�z�`�A�������`�v���v�Z����B <BR>
     * <BR>
     * 1)����������Ƃ����ꍇ�̓K�p�������t�\�z���擾����B <BR>
     * <BR>
     * �@@-this.calc�K�p�������t�\�z(int)���R�[������B <BR>
     * <BR>
     * �@@�@@[����] <BR>
     * �@@�@@�@@int�F����.���<BR> 
     * <BR>
     *   -�������t�\�z���擾����B <BR>
     * <BR>
     * �@@�@@�������t�\�z = �]�͌v�Z����.����\�z <BR>
     * <BR>
     * 2)�������t�\�񒍕���(����)�T�Z��n������W�v����B <BR>
     * <BR>
     * �@@[this.���������\�񒍕��P�ʈꗗ == null  <BR>
     * �@@�@@�܂��� this.���������\�񒍕��P�ʈꗗ.size() == 0 �ꍇ] <BR>
     * <BR>
     * �@@�@@(����)�T�Z��n������v = 0 <BR>
     * <BR>
     * �@@[�ȊO�̏ꍇ] <BR>
     * <BR>
     * �@@�@@-this.���������\�񒍕��P�ʈꗗ���ȉ��̏����Ńt�B���^����B <BR>
     * <BR>
     * �@@�@@�@@[��������]<BR> 
     * �@@�@@�@@�@@������� = '1�F����������'<BR> 
     * �@@�@@�@@�@@�����L����� = �f1�F�I�[�v���f<BR> 
     * <BR>
     * �@@�@@-�t�B���^���ʂ��T�Z��n������W�v����B <BR>
     * <BR>
     * �@@�@@�@@(����)�T�Z��n������v = sum(�����\�񒍕��P��Row.�T�Z��n���) <BR>
     * <BR>
     * 3)�������t�\�z?�A������?��ԋp���� <BR>
     * <BR>
     * �@@�ԋp�l = �������t�\�z - (����)�T�Z��n������v <BR>
     * <BR>
     * @@param l_intBasePoint - (���)
     * @@return double
     */
    public double calcSuccEquityTradingPower(int l_intBasePoint)
    {
        /*
         * ����.�w���������Ƃ����ꍇ�̓K�p�������t�\�z���擾����B
         */
        WEB3TPCalcResult l_result = this.calcAppliedEquityTradingPower(l_intBasePoint);

        //�K�p�������t�\�z
        double l_dblEqTradingPower = l_result.tradingPower;

        /*
         * �������t�\�񒍕���(����)�T�Z��n������W�v����B
         */
        //(����)�T�Z��n������v
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

                    //������� == 1:���������� �̏ꍇ
                    if(OrderTypeEnum.EQUITY_BUY.equals(l_orderType) == true)
                    {
                        //(����)�T�Z��n������v = sum(�����\�񒍕��P��Row.�T�Z��n���)
                        l_dblRevEqTradingPower = l_dblRevEqTradingPower
                                + Math.abs(l_params.getEstimatedPrice());
                    }
                }
            }
        }

        /*
         * �������t�\�z�`�A�������`��ԋp����
         * 
         * �ԋp�l = �������t�\�z - (����)�T�Z��n������v 
         */
        return l_dblEqTradingPower - l_dblRevEqTradingPower;
    }

    /**
     * (static���\�b�h)(find�]�͌v�Z����<�����ڋq>?�����h�c�w��?) <BR>
     * <BR>
     * �P�j�ڋq�I�u�W�F�N�g�𐶐�����B <BR>
     * <BR>
     * �@@?�R���X�g���N�^�AWEB3GentradeMainAccount()�R�[�� <BR>
     * �@@�@@�m�����n <BR>
     * �@@�@@�@@����ID�F����.����ID <BR>
     * <BR>
     * �Q�j"�]�͍Čv�Z�����"���擾����B <BR>
     * <BR>
     * �@@?�v���Z�X�Ǘ��e�[�u�����ȉ��̏����Ō�������B <BR>
     * �@@�@@�m���������n <BR>
     * �@@�@@�@@�v���Z�XID�F"0006:�]�͍Čv�Z�����" <BR>
     * �@@�@@�@@�،���ЃR�[�h:�ڋq�I�u�W�F�N�g.getDataSourceObject().get�،���ЃR�[�h()<BR> 
     * �@@�@@�@@���X�R�[�h�F�ڋq�I�u�W�F�N�g.getDataSourceObject().get���X�R�[�h() <BR>
     * <BR>
     * �@@?"�]�͍Čv�Z�����"���擾����B<BR> 
     * �@@�@@[a.�������� != null �̏ꍇ] <BR>
     * �@@�@@�@@"�]�͍Čv�Z�����" = �v���Z�X�Ǘ�Params.�ŏI�X�V����<BR> 
     * <BR>
     * �@@�@@[a.�������� == null �̏ꍇ]<BR> 
     * �@@�@@�@@"�]�͍Čv�Z�����" = null <BR>
     * <BR>
     * �R�j�ŐV�̗]�͌v�Z����Params�I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �@@?�]�͌v�Z���ʃe�[�u��(�����ڋq)���ȉ��̏����Ō�������B <BR>
     * �@@�@@�m���������n <BR>
     * �@@�@@�@@�ڋqID�F����.����ID<BR> 
     * �@@�@@�@@�쐬���t�F�g�ŐV�̓��t�h<BR> 
     * �@@�@@�@@�i�]�͌v�Z���ʁi�����ڋq�jID�̍~���j<BR>
     * <BR>
     * �@@���������� == null �܂��� ��������.size() == 0 �ꍇ�A<BR>
     * �@@�@@�]�͍Čv�Z�t���[�� <BR>
     * <BR>
     * �S�j�ŐV�̗]�͌v�Z���ʏڍ�Params�I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �@@[a."�]�͍Čv�Z�����" == null �܂��́A <BR>
     * �@@�@@ "�]�͍Čv�Z�����" <  �]�͌v�Z����Params�I�u�W�F�N�g.�쐬���t] <BR>
     * <BR>
     * �@@�@@?�]�͌v�Z���ʏڍ׃e�[�u��(�����ڋq)���ȉ��̏����Ō�������B <BR>
     * �@@�@@�@@�m���������n <BR>
     * �@@�@@�@@�@@�]�͌v�Z���ʁi�����ڋq�jID�F�]�͌v�Z����Params�I�u�W�F�N�g.�]�͌v�Z���ʁi�����ڋq�jID <BR>
     * <BR>
     * �@@�@@���]�͌v�Z���ʏڍ�Params���擾�ł��Ȃ������ꍇ�A��O���X���[����B <BR>
     * <BR>
     * �@@[a.�ȊO�̏ꍇ] <BR>
     * �@@�@@���]�͍Čv�Z�t���[��<BR> 
     * <BR>
     * �T�j�ԋp�l(List�^)��ݒ肵�A�Ăяo�����֕ԋp����B <BR>
     * <BR>
     * �@@[�ԋp�l]<BR> 
     * �@@�@@List�́A0�Ԗڂ̗v�f�F�R�j�Ŏ擾�����A�]�͌v�Z����Params<BR> 
     * �@@�@@List�́A1�Ԗڂ̗v�f�F�S�j�Ŏ擾�����A�]�͌v�Z���ʏڍ�Params<BR> 
     * <BR>
     * �F�]�͍Čv�Z�t���[ <BR>
     * <BR>
     * E-1)�M�p�����J�݋敪���擾����B<BR> 
     * <BR>
     * ?�ڋq�I�u�W�F�N�g.is�M�p�����J��()���R�[��<BR> 
     * �@@[����] <BR>
     * �@@�@@�ٍϋ敪���h�w��Ȃ�<BR> 
     * <BR>
     * �@@���M�p�ڋq�̏ꍇ(is�M�p�����J��==true)�A��O���X���[����B <BR>
     * <BR>
     * E-2)�⏕�����I�u�W�F�N�g���擾����B<BR> 
     * <BR>
     * �@@?�ڋq�I�u�W�F�N�g.getSubAccount()<BR> 
     * �@@�@@[����] <BR>
     * �@@�@@�@@�⏕�����^�C�v�F"�����������(�a���)" <BR>
     * <BR>
     * E-3�j�]�͌v�Z�����I�u�W�F�N�g�𐶐�����B<BR> 
     * <BR>
     * �@@?�]�͌v�Z����.create�]�͌v�Z����<�W��>()���R�[�� <BR>
     * �@@�@@[����] <BR>
     * �@@�@@�@@�⏕�����F�⏕�����I�u�W�F�N�g<BR> 
     * <BR>
     * E-4�j�]�͍X�V�I�u�W�F�N�g�𐶐�����B<BR> 
     * <BR>
     * �@@?�R���X�g���N�^�A�]�͍X�V()���R�[��<BR> 
     * �@@�@@[����] <BR>
     * �@@�@@�@@����ID�F����.����ID<BR> 
     * �@@�@@�@@�M�p�ڋq�t���O�Fis�M�p�����J��()�̖߂�l<BR> 
     * �@@�@@�@@�]�͌v�Z�����Fcreate�]�͌v�Z����()�̖߂�l <BR>
     * �@@�@@�@@���������e�Fnull <BR>
     * <BR>
     * E-5�j�]�͍X�V���e(List)���擾��DB�Ɋi�[����B<BR> 
     * <BR>
     * �@@?�]�͍X�V�I�u�W�F�N�g.calc�]�͍X�V���e<�����ڋq>()���R�[��<BR> 
     * <BR>
     * �@@?�]�͍X�V�I�u�W�F�N�g.save�]�͍X�V���e<�����ڋq>()���R�[�� <BR>
     * �@@�@@[����] <BR>
     * �@@�@@�@@List�F�]�͍X�V�I�u�W�F�N�g.calc�]�͍X�V���e<�����ڋq>()�̖߂�l <BR>
     * <BR>
     * E-7�j�]�͍X�V�I�u�W�F�N�g.calc�]�͍X�V���e<�����ڋq>()�̖߂�l��ԋp����B <BR>
     * <BR>
     * @@param l_lngAccountId - (����ID)
     * @@return List
     */
    public static List findCalcResultEquityParams(long l_lngAccountId)
    {
        final String STR_METHOD_NAME = "WEB3TPTradingPowerCalcEquity.findCalcResultEquityParams(long)";
        log.entering(STR_METHOD_NAME);

        //�]�͌v�Z���ʃe�[�u���i�����ڋq�j�̌�������
        StringBuffer l_strWhere = new StringBuffer();
        l_strWhere.append(" account_id = ? ");
        l_strWhere.append(" and created_timestamp = ");
        l_strWhere.append(" ( ");
        l_strWhere.append("  select max(created_timestamp) ");
        l_strWhere.append("  from tp_calc_result_equity ");
        l_strWhere.append("  where account_id = ? ");
        l_strWhere.append(" ) ");
        
        Object[] l_bindVars = { new Long(l_lngAccountId),
                               new Long(l_lngAccountId) };       

        log.debug(
            "Finding TpCalcResultEquityParams where="
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
            //�]�͌v�Z���ʃe�[�u��<�����ڋq>������
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRow =
                l_qp.doFindAllQuery(
                    TpCalcResultEquityParams.TYPE,
                    l_strWhere.toString(),
                    "calc_result_equity_id desc",
                    null,
                    l_bindVars,
                    new RowType[] {TpCalcResultEquityParams.TYPE});
            
            //�]�͌v�Z���ʂ��������ꂽ�ꍇ
            if(l_lisRow != null && l_lisRow.size() != 0)
            {
                //�]�͌v�Z����Params���擾
                TpCalcResultEquityParams l_resultParams = (TpCalcResultEquityParams)l_lisRow.get(0);

                /*
                 * �ŐV�̗]�͌v�Z���ʏڍ�Params�I�u�W�F�N�g���擾����B 
                 */
                //[a."�]�͍Čv�Z�����" == null �܂��́A 
                //   "�]�͍Čv�Z�����" <  �]�͌v�Z����Params�I�u�W�F�N�g.�쐬���t] 
                if(l_updateTimeStamp == null
                        || l_updateTimeStamp.before(l_resultParams.getCreatedTimestamp()) == true)
                {
                    //�]�͌v�Z���ʏڍ�<�����ڋq>Params���擾
                    TpCalcResultEquityDetailParams l_resultDetailParams = (TpCalcResultEquityDetailParams)TpCalcResultEquityDetailDao.findRowByPk(l_resultParams.getCalcResultEquityId());

                    l_updResults = new ArrayList();
                    l_updResults.add(l_resultParams);
                    l_updResults.add(l_resultDetailParams);

                    //�擾�����]�͍X�V���e(List)��ԋp����B
                    log.exiting(STR_METHOD_NAME);
                    return l_updResults;
                }
            }
            
            log.error("TpCalcResultEquityParams:data is not found");

            //�M�p�����J�݋敪���擾
            boolean l_blnMargin =
                l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

            //�M�p�ڋq�̏ꍇ
            if (l_blnMargin == true)
            {
                //�G���[���X���[
                log.error("illegal Argument");
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

            //�]�͍X�V���e(List)���擾
            l_updResults = l_tpUpd.calcTradingpowerUpdResultEquity(); 
            //�]�͍X�V���e���e�[�u����Insert
            l_tpUpd.saveTradingpowerUpdResultEquity(l_updResults);

            //�擾�����]�͍X�V���e(List)��ԋp����
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
     * �ifind�]�͌v�Z����<�����ڋq>Params�j<BR>
     * (static���\�b�h)<BR>
     * <BR>
     * �P�jDB���Y���ڋq�̍ŐV�̗]�͌v�Z����Params<�����ڋq>���P�s�擾����B<BR>
     * �@@[�Ώۃe�[�u��]<BR>
     * �@@�@@�]�͌v�Z���ʃe�[�u���i�����ڋq�j<BR>
     * <BR>
     * �@@�m���������n<BR>
     * �@@�@@�]�͌v�Z����(�����ڋq)�h�c�F����.�]�͌v�Z���ʂh�c<BR>
     * <BR>
     * �@@�@@���]�͌v�Z����Params���擾�ł��Ȃ������ꍇ�A�G���[���X���[����B<BR>
     * <BR>
     * �Q�jDB���Y���ڋq�̍ŐV�̗]�͌v�Z���ʏڍ�Params<�����ڋq>���P�s�擾����B<BR>
     * <BR>
     * �@@[�Ώۃe�[�u��]<BR>
     * �@@�@@�]�͌v�Z���ʏڍ׃e�[�u���i�����ڋq�j<BR>
     * <BR>
     * �@@�m���������n<BR>
     * �@@�@@�]�͌v�Z����(�����ڋq)ID�F����.�]�͌v�Z���ʂh�c<BR>
     * <BR>
     * �@@�@@���]�͌v�Z���ʏڍ�Params���擾�ł��Ȃ������ꍇ�A�G���[���X���[����B<BR>
     * <BR>
     * �R�jList�ɁA�P�j�Ŏ擾�����]�͌v�Z����Params<�����ڋq>�ƁA<BR>
     *  �Q�j�Ŏ擾�����A�]�͌v�Z���ʏڍ�Params<�����ڋq>���A�i�[���ĕԋp����B<BR>
     * �@@List�́A0�Ԗڂ̗v�f�F�P�j�Ŏ擾�����A�]�͌v�Z����<�����ڋq>Params<BR>
     * �@@List�́A1�Ԗڂ̗v�f�F�Q�j�Ŏ擾�����A�]�͌v�Z���ʏڍ�<�����ڋq>Params<BR>
     * <BR>
     * @@param l_lngCalcResultId - (�]�͌v�Z����ID)<BR>
     * @@return List
     */
    public static List findCalcResultEquityParamsSpecifiedCalcResultId(long l_lngCalcResultId)
    {
        final String STR_METHOD_NAME =
            "WEB3TPTradingPowerCalcEquity.findCalcResultEquityParamsSpecifiedCalcResultId(long)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //�]�͌v�Z���ʃe�[�u��<�����ڋq>������
            TpCalcResultEquityParams l_resultParams =
                (TpCalcResultEquityParams)TpCalcResultEquityDao.findRowByPk(l_lngCalcResultId);

            //�]�͌v�Z���ʏڍ�<�����ڋq>Params���擾
            TpCalcResultEquityDetailParams l_resultDetailParams =
                (TpCalcResultEquityDetailParams)TpCalcResultEquityDetailDao.findRowByPk(
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
     * (calc�I���b�N�X�N���W�b�g��o���\�z)<BR>
     * <BR>
     * �I���b�N�X�N���W�b�g��p�����g�p�\�������v�Z����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]�́j.doc�v�Q�ƁB<BR>
     * <BR>
     * @@return double
     * @@throws WEB3SystemLayerException
     */
    private double calcOrixCreditPaymentPower() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "calcOrixCreditPaymentPower()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        //�g���A�J�E���g�}�l�[�W���擾����
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        //�]�͌v�Z����Params.get����ID()
        long l_lngAccountId = this.calcResultEquity.getAccountId();

        long l_lngSubAccountId = 0;

        try
        {
            //�g���A�J�E���g�}�l�[�W��.get�⏕����(�ڋq����ID,1:�����������(�a���)).getSubAccountId()
            l_lngSubAccountId = l_gentradeAccountManager.getSubAccount(
                l_lngAccountId,
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT).getSubAccountId();
        }
        catch (NotFoundException l_nfe)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_nfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        //�c�Ɠ��iT+0�j
        Date l_datBizDate = this.calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);

        //���o�������P�ʃe�[�u�����������R�[�h���ȉ������Ō������A �����������R�[�h���擾����B
        //[����]
        //����ID = �ڋq����ID
        //�⏕����ID =  �ڋq�⏕����ID
        //������� in (��������,�؋�������̐U��,FX����̐U��,������������̐U��,���̑�����U�ցA
        //CFD�U�֒����iCFD��������a���))
        //������� not in (�V�K�����������s, �������������)
        //������ >= �c�Ɠ��iT+0�j
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" account_id = ? ");
        l_sbWhere.append(" and sub_account_id = ? ");
        l_sbWhere.append(" and order_type in ( ?, ?, ?, ?, ?, ? ) ");
        l_sbWhere.append(" and order_status not in ( ?, ? ) ");
        l_sbWhere.append(" and to_date(biz_date, 'YYYYMMDD') >= ? ");

        Object[] l_bindVars = {
            new Long(l_lngAccountId),
            new Long(l_lngSubAccountId),
            OrderTypeEnum.CASH_IN,
            OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT,
            OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE,
            OrderTypeEnum.TRANSFER_FROM_FEQ,
            OrderTypeEnum.FROM_OTHER_TRANSFER,
            OrderTypeEnum.DEPOSIT_AMOUNT_FROM_CFD,
            OrderStatusEnum.NOT_ORDERED,
            OrderStatusEnum.CANCELLED,
            l_datBizDate};

        QueryProcessor l_qp;
        List l_lisInRow = null;
        try
        {
            l_qp = Processors.getDefaultProcessor();
            l_lisInRow = l_qp.doFindAllQuery(
                AioOrderUnitParams.TYPE,
                l_sbWhere.toString(),
                l_bindVars);
        }
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //���o�������P�ʃe�[�u�����o�����R�[�h���ȉ������Ō������A �����o�����R�[�h���擾����B
        //[����]
        //����ID = �ڋq����ID
        //�⏕����ID =  �ڋq�⏕����ID
        //������� in (�o������,�؋����ւ̐U��,FX�ւ̐U��,���������ւ̐U��,���̑��ւ̐U��,
        //��؋��ւ̐U��,�I���b�N�X�N���W�b�g�ւ̐U�ցACFD�U�֒����i�a�������CFD����))
        //������� not in (�V�K�����������s, �������������)
        //������ >= �c�Ɠ��iT+0�j
        StringBuffer l_sbWhere1 = new StringBuffer();
        l_sbWhere1.append(" account_id = ? ");
        l_sbWhere1.append(" and sub_account_id = ? ");
        l_sbWhere1.append(" and order_type in ( ?, ?, ?, ?, ?, ?, ?, ? ) ");
        l_sbWhere1.append(" and order_status not in ( ?, ? ) ");
        l_sbWhere1.append(" and to_date(biz_date, 'YYYYMMDD') >= ? ");

        Object[] l_bindVars1 = {
            new Long(l_lngAccountId),
            new Long(l_lngSubAccountId),
            OrderTypeEnum.CASH_OUT,
            OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN,
            OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT,
            OrderTypeEnum.TRANSFER_TO_FEQ,
            OrderTypeEnum.TO_OTHER_TRANSFER,
            OrderTypeEnum.FROM_DEPOSIT_AMOUNT_DSK,
            OrderTypeEnum.TO_ORIX_CREDIT,
            OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT,
            OrderStatusEnum.NOT_ORDERED,
            OrderStatusEnum.CANCELLED,
            l_datBizDate};

        List l_lisOutRow = null;
        try
        {
            l_qp = Processors.getDefaultProcessor();
            l_lisOutRow = l_qp.doFindAllQuery(
                AioOrderUnitParams.TYPE,
                l_sbWhere1.toString(),
                l_bindVars1);
        }
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        BigDecimal l_bdInQuantity = new BigDecimal("0");
        BigDecimal l_bdOutQuantity = new BigDecimal("0");
        BigDecimal l_bdTrading = new BigDecimal("0");
        BigDecimal l_bdTotal = new BigDecimal("0");

        if (!l_lisInRow.isEmpty())
        {
            //���������z���v
            int l_intInSize = l_lisInRow.size();
            for (int i = 0; i < l_intInSize; i++)
            {
                AioOrderUnitRow l_inAioOrderUnitRow = (AioOrderUnitRow)l_lisInRow.get(i);
                BigDecimal l_bdInQuantity1 = new BigDecimal("" + l_inAioOrderUnitRow.getQuantity());
                l_bdInQuantity = l_bdInQuantity.add(l_bdInQuantity1);
            }
        }

        if (!l_lisOutRow.isEmpty())
        {
            //�����o���z���v
            int l_intOutSize = l_lisOutRow.size();
            for (int i = 0; i < l_intOutSize; i++)
            {
                AioOrderUnitRow l_outAioOrderUnitRow = (AioOrderUnitRow)l_lisOutRow.get(i);
                BigDecimal l_bdOutQuantity1 = new BigDecimal("" + l_outAioOrderUnitRow.getQuantity());
                l_bdOutQuantity = l_bdOutQuantity.add(l_bdOutQuantity1);
            }
        }

        //�E	�I���b�N�X�N���W�b�g�S�ۃ��[���o���\�z	�E�E�E�]�͌v�Z����.get�S�ۃ��[���o���\�z
        String l_strTradingPower = this.calcCondition.getOrixSecuredLoanPaymentTradingPower();

        // �I���b�N�X�N���W�b�g��p�����g�p�\����
        // = �I���b�N�X�N���W�b�g�S�ۃ��[���o���\�z + ���������z���v - �����o���z���v - ���񒍕��o���z
        if (l_strTradingPower != null)
        {
            l_bdTrading =  new BigDecimal(l_strTradingPower);
        }

        BigDecimal l_thisTimOrderCont =  new BigDecimal(String.valueOf(this.thisTimOrderCont));
        l_bdTotal = l_bdTrading.add(l_bdInQuantity).subtract(l_bdOutQuantity).subtract(l_thisTimOrderCont);

        log.exiting(STR_METHOD_NAME);
        return l_bdTotal.doubleValue();
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
