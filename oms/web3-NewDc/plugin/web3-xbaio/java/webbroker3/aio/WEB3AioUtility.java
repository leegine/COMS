head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.22.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o���̋@@�\���������郆�[�e�B���e�B(WEB3AioUtility)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 ������ (���u) �V�K�쐬
                   2004/12/09 �����(���u)�c�Ή�
*/
package webbroker3.aio;

import java.util.List;

import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (���o���̋@@�\���������郆�[�e�B���e�B)<BR>
 * ���o���̋@@�\���������郆�[�e�B���e�B�N���X
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3AioUtility {

//    /**
//     * (get�����󋵃��b�Z�[�W�R�[�h)
//     * �����ɂ��A�I�����C�������̏ꍇ�A�����󋵂��擾���A�ԋp����
//     * �u�c�a�X�V�d�l\10.���o��\���o���X�e�[�^�X�\���\.xls�v���Q�Ƃ���
//     *
//     * @@param l_strStatus - (���Z�@@�֘A�g���o���󋵃e�[�u��.�����敪)
//     * @@param l_strOrderStatusFlag - (����FLAG�i�����j)
//     * @@param l_strStartStatusFlg - (����FLAG�i���ϊJ�n�j)
//     * @@param l_strResultStatusFlag - (����FLAG�i���ό��ʁj)
//     * @@param l_OrderStatus - (�������)
//     * @@param l_strCancelType - (��������敪)
//     * @@param l_strKeyTableStatus - (���o���`�[��t�L���[�e�[�u��.�����敪)
//     * @@return String
//     */
//    public static String getResult(
//        String l_strStatus,
//        String l_strOrderStatusFlag,
//        String l_strStartStatusFlg,
//        String l_strResultStatusFlag,
//        OrderStatusEnum l_OrderStatus,
//        String l_strCancelType,
//        String l_strKeyTableStatus)
//    {
//        //>>>>>>*******���Z�@@�֘A�g���o���󋵃e�[�u��.�����敪 = �O�F������ Start
//        if (WEB3TransactionStatusDef.NOT_DEAL.equals(l_strStatus))
//        {
//            //����FLAG�i�����j= �O�F������ || ����FLAG�i�����j= �P�F�v����M
//            if (WEB3OrderStatusFlagDef.NOT_DEAL.equals(l_strOrderStatusFlag)
//                || WEB3OrderStatusFlagDef.REPUEST_RECEIPT.equals(
//                    l_strOrderStatusFlag))
//            {
//               //����FLAG�i���ϊJ�n�j= �O�F������ &&  ����FLAG�i���ό��ʁj= �O�F������
//                if (WEB3StartStatusFlgDef.NOT_DEAL.equals(l_strStartStatusFlg)
//                    && WEB3ResultStatusFlagDef.NOT_DEAL.equals(
//                        l_strResultStatusFlag))
//                {
//                    return WEB3AioJudgeResultDef.A;
////                    return "A";
//                }
//                else
//                {
//                    //���̑�....
//                    return WEB3AioJudgeResultDef.C;
////                    return "C";
//                }
//            }
//            else
//            {
//                //����FLAG�i�����j= �Q�F�������M.......... 
//                if (WEB3OrderStatusFlagDef.RESPONSE_SEND.equals(
//                    l_strOrderStatusFlag))
//                {
//                    //(����FLAG�i���ϊJ�n�j= �O�F������ || ����FLAG�i���ϊJ�n�j= �P�F�v����M)
//                    // && ����FLAG�i���ό��ʁj= �O�F������ ....
//                    if ((WEB3StartStatusFlgDef.NOT_DEAL.equals(
//                        l_strStartStatusFlg)
//                            || WEB3StartStatusFlgDef.REPUEST_RECEIPT.equals(
//                                l_strStartStatusFlg))
//                            && WEB3ResultStatusFlagDef.NOT_DEAL.equals(
//                                l_strResultStatusFlag))
//                    {
//                        return WEB3AioJudgeResultDef.A;
////                        return "A";
//                    }
//                    else
//                    {
//                        //(����FLAG�i���ϊJ�n�j= �Q�F�������M........
//                        if (WEB3StartStatusFlgDef.RESPONSE_SEND.equals(
//                            l_strStartStatusFlg))
//                        {
//                            // ����FLAG�i���ό��ʁj= �O�F������ ....
//                            if (WEB3ResultStatusFlagDef.NOT_DEAL.equals(
//                                l_strResultStatusFlag))
//                            {
//                                return WEB3AioJudgeResultDef.P;
////                                return "P";
//                            }
//                            else
//                            {
//                                return WEB3AioJudgeResultDef.B;
////                                return "B";
//                            }
//                        }
//                        else
//                        {
//                            //���̑�....
//                            return WEB3AioJudgeResultDef.C;
////                            return "C";
//                        }
//                    }
//                }
//                else
//                {
//                    //���̑�....
//                    return WEB3AioJudgeResultDef.C;
////                    return "C";
//                }
//            }
//            //>>>>>>*******���Z�@@�֘A�g���o���󋵃e�[�u��.�����敪 = �O�F������ End    
//        }
//        
//        //>>>>>>*******���Z�@@�֘A�g���o���󋵃e�[�u��.�����敪 = �P�F���ϊ��� Start 
//        else
//        {
//            if (WEB3TransactionStatusDef.OK.equals(l_strStatus))
//            {
//                //����FLAG�i�����j= �Q�F�������M && (����FLAG�i���ϊJ�n�j= �Q�F�������M
//                if (WEB3OrderStatusFlagDef.RESPONSE_SEND.equals(
//                    l_strOrderStatusFlag)
//                        && WEB3StartStatusFlgDef.RESPONSE_SEND.equals(
//                            l_strStartStatusFlg))
//                {
//                    //����FLAG�i���ό��ʁj=�P�F�ʒm��M ||
//                    //����FLAG�i���ό��ʁj=�Q�F�������M ||
//                    //����FLAG�i���ό��ʁj= �X�F�]�͍Čv�Z���� ||
//                    //����FLAG�i���ό��ʁj= A�F���ύď�������.......... 
//                   if (WEB3ResultStatusFlagDef.NOTIFY_RECEIPT.equals(
//                            l_strResultStatusFlag)
//                        || WEB3ResultStatusFlagDef.RESPONSE_SEND.equals(
//                           l_strResultStatusFlag)
//                        || WEB3ResultStatusFlagDef.REMAINING_CALCULATION_COMPLETE.equals(
//                            l_strResultStatusFlag)
//                        || WEB3ResultStatusFlagDef.SETTLEMENT_RESEND_PROCESS_COMPLETE.equals(
//                            l_strResultStatusFlag))
//                    {
//                        //������� =�P�F��t�ρi�V�K�����j&&  ��������敪 = �O�F�����l
//                        if (OrderStatusEnum.ACCEPTED.equals(l_OrderStatus)
//                            && WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_strCancelType))
//                        {
//                            //���o���`�[��t�L���[�e�[�u��.�����敪 = �X�F�G���[
//                            if (WEB3StatusDef.DATA_ERROR.equals(l_strKeyTableStatus))
//                            {
//                                return WEB3AioJudgeResultDef.J;
////                                return "J";
//                            }
//                            else
//                            {
//                                return WEB3AioJudgeResultDef.D;
////                                return "D";
//                            }
//                        }
//                        else
//                        {
//                            //������� = �R�F�����ρi�V�K�����j&& ��������敪 = �O�F�����l
//                            if (OrderStatusEnum.ORDERED.equals(l_OrderStatus)
//                                && WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_strCancelType))
//                            {
//                                return WEB3AioJudgeResultDef.E;
////                                return "E";
//                            }
//                            else
//                            {
//                                //������� = �U�F�������s�i�V�K�����j&& ��������敪 = �O�F�����l
//                                if (OrderStatusEnum.NOT_ORDERED.equals(l_OrderStatus)
//                                    && WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_strCancelType))
//                                {
//                                    return WEB3AioJudgeResultDef.F;
////                                    return "F";
//                                }
//                                else
//                                {
//                                    //������� =�P�F��t�ρi�V�K�����j&&  ��������敪 = �O�F�����l
//                                    // && ���o���`�[��t�L���[�e�[�u��.�����敪 = �X�F�G���[
//                                    if (OrderStatusEnum.ACCEPTED.equals(l_OrderStatus)
//                                        && WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_strCancelType)
//                                        && WEB3StatusDef.DATA_ERROR.equals(l_strKeyTableStatus))
//                                    {
//                                        return WEB3AioJudgeResultDef.J;
////                                        return "J";
//                                    }
//                                    else
//                                    {
//                                        //������� = null && ��������敪 = null
//                                        // && ���o���`�[��t�L���[�e�[�u��.�����敪 = null
//                                        if (l_OrderStatus == null
//                                            && l_strCancelType == null
//                                            && l_strKeyTableStatus == null)
//                                        {
//                                            return WEB3AioJudgeResultDef.E;
////                                            return "E";
//                                        }
//                                        else
//                                        {
//                                            //���̑�....
//                                            return WEB3AioJudgeResultDef.B;
////                                            return "B";
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                    else
//                    {
//                        //����FLAG�i���ό��ʁj= �W�F�]�͌v�Z���s.....
//                        if (WEB3ResultStatusFlagDef.REMAINING_CALCULATION_FAIL.equals(
//                            l_strResultStatusFlag))
//                        {
//                            //������� =�P�F��t�ρi�V�K�����j&&  ��������敪 = �O�F�����l
//                            if (OrderStatusEnum.ACCEPTED.equals(l_OrderStatus)
//                                && WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_strCancelType))
//                            {
//                                //���o���`�[��t�L���[�e�[�u��.�����敪 = �X�F�G���[
//                                if (WEB3StatusDef.DATA_ERROR.equals(l_strKeyTableStatus))
//                                {
//                                    return WEB3AioJudgeResultDef.J;
////                                    return "J";
//                                }
//                                else
//                                {
//                                    return WEB3AioJudgeResultDef.K;
////                                    return "K";
//                                }
//                           }
//                            else
//                            {
//                                //������� = �R�F�����ρi�V�K�����j&& ��������敪 = �O�F�����l
//                                if (OrderStatusEnum.ORDERED.equals(l_OrderStatus)
//                                    && WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_strCancelType))
//                                {
//                                    return WEB3AioJudgeResultDef.L;
////                                    return "L";
//                                }
//                                else
//                                {
//                                    //������� = �U�F�������s�i�V�K�����j&& ��������敪 = �O�F�����l
//                                    if (OrderStatusEnum.NOT_ORDERED.equals(l_OrderStatus)
//                                        && WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_strCancelType))
//                                    {
//                                        return WEB3AioJudgeResultDef.M;
////                                        return "M";
//                                    }
//                                    else
//                                    {
//                                        //������� =�P�F��t�ρi�V�K�����j&&  ��������敪 = �O�F�����l
//                                        // && ���o���`�[��t�L���[�e�[�u��.�����敪 = �X�F�G���[                                       
//                                        if (OrderStatusEnum.ACCEPTED.equals(l_OrderStatus)
//                                            && WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_strCancelType)
//                                            && WEB3StatusDef.DATA_ERROR.equals(l_strKeyTableStatus))
//                                        {
//                                            return WEB3AioJudgeResultDef.J;
////                                            return "J";
//                                        }
//                                        else
//                                        {
//                                            //������� = null && ��������敪 = null
//                                            // && ���o���`�[��t�L���[�e�[�u��.�����敪 = null
//                                            if (l_OrderStatus == null
//                                                && l_strCancelType == null
//                                                && l_strKeyTableStatus == null)
//                                            {
//                                                return WEB3AioJudgeResultDef.L;
////                                                return "L";
//                                            }
//                                            else
//                                            {
//                                                //���̑�....
//                                                return WEB3AioJudgeResultDef.B;
////                                                return "B";
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                        else
//                        { 
//                            //���̑�....
//                            return WEB3AioJudgeResultDef.B;
////                            return "B";
//                        }
//                    }
//                }
//                else
//                {
//                    //���̑�....
//                    return WEB3AioJudgeResultDef.B;
////                    return "B";
//                }
//            //>>>>>>*******���Z�@@�֘A�g���o���󋵃e�[�u��.�����敪 = �P�F���ϊ��� End
//            }
//            
//            
//            else
//            {
//                //>>>>>>*******���Z�@@�֘A�g���o���󋵃e�[�u��.�����敪 = �Q�F���ϒ��~ Start
//                if (WEB3TransactionStatusDef.NG.equals(l_strStatus))
//                {
//                    if (WEB3OrderStatusFlagDef.RESPONSE_SEND.equals(
//                        l_strOrderStatusFlag)
//                            && WEB3StartStatusFlgDef.RESPONSE_SEND.equals(
//                                l_strStartStatusFlg)
//                            && WEB3ResultStatusFlagDef.NOTIFY_ERROR_FAIL.equals(
//                                l_strResultStatusFlag))
//                    {
//                        return WEB3AioJudgeResultDef.G;
////                        return "G";
//                    }
//                    else
//                    {
//                        return WEB3AioJudgeResultDef.B;
////                        return "B";
//                    }
//                //>>>>>>*******���Z�@@�֘A�g���o���󋵃e�[�u��.�����敪 = �Q�F���ϒ��~ End
//                }
//                else
//                {
//                    //>>>>>>*******���Z�@@�֘A�g���o���󋵃e�[�u��.�����敪 = �R�F�G���[ Start
//                    if (WEB3TransactionStatusDef.ERROR.equals(l_strStatus))
//                    {
//                        //����FLAG�i�����j= �O�F������  || ����FLAG�i�����j= �P�F�v����M
//                        if (WEB3OrderStatusFlagDef.NOT_DEAL.equals(
//                            l_strOrderStatusFlag)
//                                || WEB3OrderStatusFlagDef.REPUEST_RECEIPT.equals(
//                                    l_strOrderStatusFlag))
//                        {
//                            return WEB3AioJudgeResultDef.C;
////                            return "C";
//                        }
//                        else
//                        {
//                            //����FLAG�i�����j= �Q�F�������M....
//                            if (WEB3OrderStatusFlagDef.RESPONSE_SEND.equals(
//                                l_strOrderStatusFlag))
//                            {
//                                //(����FLAG�i���ϊJ�n�j= �O�F������ || (����FLAG�i���ϊJ�n�j= �P�F�v����M
//                                if (WEB3StartStatusFlgDef.NOT_DEAL.equals(
//                                    l_strStartStatusFlg)
//                                    || WEB3StartStatusFlgDef.REPUEST_RECEIPT.equals(
//                                        l_strStartStatusFlg))
//                                {
//                                    return WEB3AioJudgeResultDef.C;
////                                    return "C";
//                                }
//                                else
//                                {
//                                    //(����FLAG�i���ϊJ�n) = �Q�F�������M
//                                    if (WEB3StartStatusFlgDef.RESPONSE_SEND.equals(
//                                        l_strStartStatusFlg))
//                                    {
//                                        //����FLAG�i���ό��ʁj= �O�F������
//                                        if (WEB3ResultStatusFlagDef.NOTIFY_ERROR_ERROR.equals(
//                                            l_strResultStatusFlag))
//                                        {
//                                            return WEB3AioJudgeResultDef.H;
////                                            return "H";
//                                        }
//                                        else
//                                        {
//                                            //����FLAG�i���ό��ʁj= �U�F�Z�b�V�����G���[�iCOMPLETE)
//                                            if (WEB3ResultStatusFlagDef.SESSION_ERROR_COMPLETE.equals(
//                                                l_strResultStatusFlag))
//                                            {
//                                                return WEB3AioJudgeResultDef.N;
////                                                return "N";
//                                            }
//                                            else
//                                            {
//                                                //����FLAG�i���ό��ʁj= �V�F�Z�b�V�����G���[�iCOMPLETE�ȊO)
//                                                if (WEB3ResultStatusFlagDef.SESSION_ERROR_COMPLETE_EXCEPT.equals(
//                                                    l_strResultStatusFlag))
//                                                {
//                                                    return WEB3AioJudgeResultDef.O;
////                                                    return "O";
//                                                }
//                                                else
//                                                {
//                                                    //���̑�....
//                                                    return WEB3AioJudgeResultDef.B;
////                                                    return "B";
//                                                }
//                                            }
//                                        }
//                                    }
//                                    else
//                                    {
//                                        //(����FLAG�i���ϊJ�n) = �T�F�L�����Z��
//                                        if (WEB3StartStatusFlgDef.CANCEL.equals(
//                                            l_strStartStatusFlg))
//                                        {
//                                            return WEB3AioJudgeResultDef.I;
////                                            return "I";
//                                        }
//                                        else
//                                        {
//                                            //���̑�....
//                                            return WEB3AioJudgeResultDef.C;
////                                            return "C";
//                                        }
//                                    }
//                                }
//                            }
//                            else
//                            {
//                                //���̑�....
//                                return WEB3AioJudgeResultDef.C;
////                                return "C";
//                            }
//                        }
//                        //>>>>>>*******���Z�@@�֘A�g���o���󋵃e�[�u��.�����敪 = �R�F�G���[ End
//                    }
//                    
//                    //>>>>>>*******���̑��F�G���[ Begin
//                    else
//                    {
//                        return WEB3AioJudgeResultDef.J;
////                        return "J";
//                    }
//                    //>>>>>>*******���̑��F�G���[ End
//                }
//            }
//        }
//        
//    }
    
    public static QueryProcessor getProcessor()
        throws WEB3BaseException
    {
        try
        {
            return Processors.getDefaultProcessor();
        }
        catch (DataException l_ex)
        {
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3AioUtility.class.getName() + ".getProcessor()",
                    l_ex.getMessage(),
                    l_ex);
        }

    }
    
    public static List doFindAllQuery(
            RowType l_rowType, String l_strWhere, 
            String l_strOrderBy, String l_strConditions, Object[] l_bindVars,
            ErrorInfo l_errorInfo, String l_strErrormessage)
        throws WEB3BaseException
    {
        try
        {
            return Processors.getDefaultProcessor().doFindAllQuery(
                    l_rowType, l_strWhere, l_strOrderBy, l_strConditions, l_bindVars);
        }
        catch (DataException l_ex)
        {
            log.error(l_strErrormessage);
            throw new WEB3SystemLayerException(
                    l_errorInfo,
                    WEB3AioUtility.class.getName() + ".doFindAllQuery()",
                    l_ex.getMessage(),
                    l_ex);
        }
    }
    
    public static List doFindAllQuery(
            RowType l_rowType, String l_strWhere, String l_strConditions, Object[] l_bindVars,
            ErrorInfo l_errorInfo, String l_strErrormessage)
        throws WEB3BaseException
    {
        try 
        {
            return Processors.getDefaultProcessor().doFindAllQuery(
                    l_rowType, l_strWhere, l_strConditions, l_bindVars);
        }
        catch (DataException l_ex)
        {
            log.error(l_strErrormessage);
            throw new WEB3SystemLayerException(
                    l_errorInfo,
                    WEB3AioUtility.class.getName() + ".doFindAllQuery()",
                    l_ex.getMessage(),
                    l_ex);
        }
    }
    
    public static TradingTimeRow doFindAllQuery(String l_institutionCode, String l_branchCode)
        throws WEB3BaseException
    {
        try
        {
            //processor
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            
            //where 
            StringBuffer l_strBuf = new StringBuffer();
            l_strBuf.append(" institution_code = ? and ");
            l_strBuf.append(" branch_code = ? and ");
            l_strBuf.append(" market_code = ? and ");
            l_strBuf.append(" trading_time_type = ? and ");
            l_strBuf.append(" product_code = ? and ");
            l_strBuf.append(" biz_date_type = ? and ");
            l_strBuf.append(" start_time <= ? and end_time >= ? ");
            
            //value
            //�c�Ɠ��敪
            String l_strBizDateType = 
                WEB3GentradeTradingTimeManagement.getBizDateType(GtlUtils.getSystemTimestamp());
            //date    
            String l_strDate = 
                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "HHmmss");
            
            Object[] l_bindVars = 
                {l_institutionCode,
                    l_branchCode,
                    WEB3MarketCodeDef.DEFAULT,
                    WEB3TradingTimeTypeDef.MARGIN_TRANSFER,
                    WEB3ProductCodeDef.DEFAULT,
                    l_strBizDateType,
                    l_strDate};
                    
            List l_lis = l_processor.doFindAllQuery(
                TradingTimeRow.TYPE,
                l_strBuf.toString(),
                l_bindVars);
                
            TradingTimeRow l_tradingTimeRow = (TradingTimeRow)l_lis.get(0);
                
            return l_tradingTimeRow;
        }
        catch (DataException l_ex)
        {
            log.error("Error in find ������ԃe�[�u��");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AioUtility.class.getName() + ".doFindAllQuery()",
                l_ex.getMessage(),
                l_ex);
        }    
    }
     
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AioUtility.class);
}
@
