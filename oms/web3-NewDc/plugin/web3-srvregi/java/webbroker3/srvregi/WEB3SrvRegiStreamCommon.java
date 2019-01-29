head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.38.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiStreamCommon.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p���A�g����(WEB3SrvRegiStreamCommon.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/05/19 ���g (���u) �V�K�쐬 ���f��No.368,No.374,No.380,No.381,No.383,No.384,No.385,No.390,No.391
Revision History : 2008/06/19 �Ԑi (���u) ���f��No.392
Revision History : 2008/06/20 ���g (���u) ���f��No.393
Revision History : 2008/06/20 ���g (���u) ���f��No.395
Revision History : 2008/06/27 ���g (���u) ���f��No.396
Revision History : 2008/07/18 �g�C�� (���u) ���f��No.398
*/
package webbroker3.srvregi;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductDao;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductPK;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;

import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3BondCategCodeDef;
import webbroker3.common.define.WEB3CurrencyCodeDef;
import webbroker3.common.define.WEB3EffectiveDivDef;
import webbroker3.common.define.WEB3LoginAttributeKeyDef;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3SrvRegiCancelDivDef;
import webbroker3.common.define.WEB3TradingPwdEnvDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.SrvRegiApplicationRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.srvregi.define.WEB3SrvRegiHashCalHowToDivValueDef;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiStartInfoService;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �T�[�r�X���p���A�g����<BR>
 * (���A�g���ʂɊւ��鏈�����s�����\�b�h���W�߂��N���X)<BR>
 *
 * @@author ���g(���u)
 * @@version 1.0
 */
public class WEB3SrvRegiStreamCommon
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiStreamCommon.class);

    /**
     * �T�[�r�X���p���A�g����<BR>
     */
    public WEB3SrvRegiStreamCommon()
    {

    }

    /**
     * (get���c�����X�g)<BR>
     * �u�ۗL���Y�e�[�u���v�Ɓu�������}�X�^�e�[�u���v���������A���c�����X�g�𐶐����ĕԋp����B<BR>
     * <BR>
     * �P�j�ȉ��̏����Łu�ۗL���Y�e�[�u���v����������B<BR>
     * �@@[��������]<BR>
     * �@@�@@����ID = ����.�⏕����.����ID and<BR>
     * �@@�@@�����^�C�v = 2(��)<BR>
     * <BR>
     * �Q�j�߂�l�̐���<BR>
     * �@@2-1�j�u�ۗL���Y�e�[�u���v�̌������ʂ�0���̏ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �@@2-2�j�u�ۗL���Y�e�[�u���v�̌������ʂ�1���ȏ゠��ꍇ�A�ȉ��̏������s���B<BR>
     * <BR>
     * �R�j�����R�[�h�A���t�\�c�����ʁA�擾���z���擾���A���c�����X�g�𐶐�����B<BR>
     * �@@3-1�j�u�ۗL���Y�e�[�u���v���擾�����������A�ȉ��̏������J��Ԃ��B<BR>
     * <BR>
     * �@@3-2�j�������}�X�^�e�[�u���̃��R�[�h���擾�B<BR>
     * �@@�@@�@@[��������]<BR>
     * �@@�@@�@@�������}�X�^�e�[�u��.����ID = �擾�ۗL���Y�e�[�u��.����ID<BR>
     * <BR>
     * �@@3-3�j�擾�����u�������}�X�^�e�[�u���v�̃��R�[�h�̎�ʃR�[�h��<BR>
     * �@@�@@�u�]���ЍE�������g��(1043)�v�ȊO�̏ꍇ�A�ȉ��̏������s���B<BR>
     * <BR>
     * �@@�@@�@@�����R�[�h = �������}�X�^�e�[�u��.�����R�[�h <BR>
     * <BR>
     * �@@�@@���������̏ꍇ�i�@@�����R�[�h�̐擪2���ڂ����l�j<BR>
     * �@@�@@�@@�A���t�\�c������ = �ۗL���Y�e�[�u��.���� �~ 1000<BR>
     * <BR>
     * �@@�@@�@@�B�擾���z = �ۗL���Y�e�[�u��.�뉿�i�뉿�P���v�Z�p�j �~ 1000<BR>
     * <BR>
     * �@@�@@���O�����i�~�����j�̏ꍇ�i�@@�����R�[�h�̐擪2���ڂ� "T0"�j<BR>
     * �@@�@@�@@�A���t�\�c������ = �ۗL���Y�e�[�u��.���� <BR>
     * <BR>
     * �@@�@@�@@�B�擾���z = �ۗL���Y�e�[�u��.�뉿�i�뉿�P���v�Z�p�j<BR>
     * <BR>
     * �@@�@@���O�����i�O�݌��j�i�@@�����R�[�h�̐擪2���ڂ� "T0"�ȊO�̉p���j<BR>
     * �@@�@@�@@�A���t�\�c������ = �ۗL���Y�e�[�u��.����<BR>
     * <BR>
     * �@@�@@�@@�B�擾���z = �ۗL���Y�e�[�u��.���ʁi�뉿�P���v�Z�p�j �~ �ۗL���Y�e�[�u��.���͕뉿�P��<BR>
     * <BR>
     * <BR>
     * �@@3-4�j���c�����X�g�쐬<BR>
     * �@@�@@�@@�����R�[�h�A���t�\�c�����ʁA�擾���z�̏���(*)��؂�ŃZ�b�g����B<BR>
     * �@@�@@�@@�����̏ꍇ�R�����i:)�ŋ�؂�B<BR>
     * �@@�@@�@@���擾���z�͏����_�ȉ���؂�̂ĂăZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@��j�ȉ��Q�̎c���̏ꍇ<BR>
     * �@@�@@�@@�����R�[�h0123456�A����2000�A�擾���z3000000<BR>
     * �@@�@@�@@�����R�[�h0987654�A����5000�A�擾���z5000000<BR>
     * <BR>
     * �@@�@@�@@[ 0123456*2000*3000000:0987654*5000*5000000 ]<BR>
     * <BR>
     * �S�j�����������c�����X�g��ԋp����B<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getBondBalanceList(SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBondBalanceList(SubAccount)";
        log.entering(STR_METHOD_NAME);

        String l_strBondBalanceList = "";
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //�P�j�ȉ��̏����Łu�ۗL���Y�e�[�u���v����������B
            //����ID = ����.�⏕����.����ID and
            //�����^�C�v = 2(��)
            String l_strAssetQuery = "account_id=? and product_type=?";

            List l_lisAssetQuerys = new ArrayList();
            l_lisAssetQuerys.add(new Long(l_subAccount.getAccountId()));
            l_lisAssetQuerys.add(ProductTypeEnum.BOND);

            Object[] l_objAssetQuerys = new Object[l_lisAssetQuerys.size()];
            l_lisAssetQuerys.toArray(l_objAssetQuerys);

            List l_lisAssets = l_queryProcessor.doFindAllQuery(
                AssetRow.TYPE,
                l_strAssetQuery,
                l_objAssetQuerys);

            //�Q�j�߂�l�̐���
            //2-1�j�u�ۗL���Y�e�[�u���v�̌������ʂ�0���̏ꍇ�Anull��ԋp����B
            if (l_lisAssets.isEmpty())
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            //2-2�j�u�ۗL���Y�e�[�u���v�̌������ʂ�1���ȏ゠��ꍇ�A�ȉ��̏������s���B
            else
            {
                //�R�j�����R�[�h�A���t�\�c�����ʁA�擾���z���擾���A���c�����X�g�𐶐�����B
                int l_intAssetRow = l_lisAssets.size();
                for (int i = 0; i < l_intAssetRow; i++)
                {
                    //3-1�j�u�ۗL���Y�e�[�u���v���擾�����������A�ȉ��̏������J��Ԃ��B
                    AssetRow l_assetRow = (AssetRow)l_lisAssets.get(i);

                    //3-2�j�������}�X�^�e�[�u���̃��R�[�h���擾�B
                    //[��������]
                    //�������}�X�^�e�[�u��.����ID = �擾�ۗL���Y�e�[�u��.����ID
                    long l_lngProductId = l_assetRow.getProductId();
                    BondProductPK l_bondProductPK = new BondProductPK(l_lngProductId);
                    BondProductRow l_bondProductRow =
                        BondProductDao.findRowByPk(l_bondProductPK);

                    //3-3�j�擾�����u�������}�X�^�e�[�u���v�̃��R�[�h�̎�ʃR�[�h��
                    //�u�]���ЍE�������g��(1043)�v�ȊO�̏ꍇ�A�ȉ��̏������s���B
                    if (!WEB3BondCategCodeDef.CONVERSION_COMPANY_BOND_WARRANT_BOND.equals(
                        l_bondProductRow.getBondCategCode()))
                    {
                        //�@@�����R�[�h = �������}�X�^�e�[�u��.�����R�[�h
                        String l_strProductCode = l_bondProductRow.getProductCode();
                        String l_strProductCodeSubstring = l_strProductCode.substring(0, 2);
                        //���������̏ꍇ�i�@@�����R�[�h�̐擪2���ڂ����l�j
                        //�A���t�\�c������ = (�ۗL���Y�e�[�u��.���� + �ۗL���Y�e�[�u��.���t�s�\����) �~ 1000
                        BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_assetRow.getQuantity()));        
                        BigDecimal l_bdQuantityCannotSell = new BigDecimal(String.valueOf(l_assetRow.getQuantityCannotSell()));        
                        //���t�\�c������ = �ۗL���Y�e�[�u��.���� + �ۗL���Y�e�[�u��.���t�s�\����
                        BigDecimal l_bdSellPossBalanceQuantity = l_bdQuantity.add(l_bdQuantityCannotSell);
                        //�ۗL���Y�e�[�u��.���ʁi�뉿�P���v�Z�p)
                        BigDecimal l_bdQuantityForBookValue = new BigDecimal(l_assetRow.getQuantityForBookValue());
                        
                        String l_strSellPossBalanceQuantity = "";
                        String l_strQuantityMultiplyInputBookValue = "";
                        //���������̏ꍇ�i�@@�����R�[�h�̐擪2���ڂ����l�j
                        if (WEB3StringTypeUtility.isNumber(l_strProductCodeSubstring))
                        {
                            //�A���t�\�c������ = (�ۗL���Y�e�[�u��.���� + �ۗL���Y�e�[�u��.���t�s�\����) �~ 1000
                            l_strSellPossBalanceQuantity =
                                WEB3StringTypeUtility.formatNumber(
                                    l_bdSellPossBalanceQuantity.multiply(new BigDecimal(1000)).doubleValue());

                            //�B�擾���z = �ۗL���Y�e�[�u��.�뉿�i�뉿�P���v�Z�p�j �~ 1000
                            BigDecimal l_bdBookValue = new BigDecimal(String.valueOf(l_assetRow.getBookValue()));
                            l_strQuantityMultiplyInputBookValue =
                                WEB3StringTypeUtility.formatNumber(l_bdBookValue.multiply(new BigDecimal(1000)).doubleValue());
                        }
                        //���O�����i�~�����j�̏ꍇ�i�@@�����R�[�h�̐擪2���ڂ� "T0"�j
                        else if (WEB3CurrencyCodeDef.T0.equals(l_strProductCodeSubstring))
                        {
                            //�A���t�\�c������ = �ۗL���Y�e�[�u��.���� + �ۗL���Y�e�[�u��.���t�s�\����
                            l_strSellPossBalanceQuantity =
                                WEB3StringTypeUtility.formatNumber(l_bdSellPossBalanceQuantity.doubleValue());

                            //�B�擾���z = �ۗL���Y�e�[�u��.�뉿�i�뉿�P���v�Z�p�j
                            l_strQuantityMultiplyInputBookValue =
                                WEB3StringTypeUtility.formatNumber(l_assetRow.getBookValue());
                        }
                        //���O�����i�O�݌��j�i�@@�����R�[�h�̐擪2���ڂ� "T0"�ȊO�̉p���j
                        else if (!WEB3CurrencyCodeDef.T0.equals(l_strProductCodeSubstring))
                        {
                            //�A���t�\�c������ = �ۗL���Y�e�[�u��.���� + �ۗL���Y�e�[�u��.���t�s�\����
                            l_strSellPossBalanceQuantity =
                                WEB3StringTypeUtility.formatNumber(l_bdSellPossBalanceQuantity.doubleValue());

                            //�B�擾���z = �ۗL���Y�e�[�u��.���ʁi�뉿�P���v�Z�p�j �~ �ۗL���Y�e�[�u��.���͕뉿�P�� 
                            BigDecimal l_bdInputBookValue = new BigDecimal(String.valueOf(l_assetRow.getInputBookValue()));
                            BigDecimal l_bdQuantityMultiplyInputBookValue = l_bdQuantityForBookValue.multiply(l_bdInputBookValue);
                            l_strQuantityMultiplyInputBookValue =
                                WEB3StringTypeUtility.formatNumber(l_bdQuantityMultiplyInputBookValue.doubleValue());
                        }

                        l_strBondBalanceList += l_strProductCode;

                        l_strBondBalanceList += "*" + l_strSellPossBalanceQuantity;

                        //�擾���z�͏����_�ȉ���؂�̂ĂăZ�b�g����
                        l_strQuantityMultiplyInputBookValue = l_strQuantityMultiplyInputBookValue.split("\\.")[0];
                        l_strBondBalanceList += "*" + l_strQuantityMultiplyInputBookValue;

                        if (i != l_intAssetRow - 1)
                        {
                            l_strBondBalanceList += ":";
                        }
                    }
                }
            }
        }
        catch (DataFindException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�T�[�o�Ƃ̒ʐM�Ɏ��s����", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if ((!WEB3StringTypeUtility.isEmptyOrBlank(l_strBondBalanceList))
            && l_strBondBalanceList.lastIndexOf(":") == (l_strBondBalanceList.length() - 1))
        {
            l_strBondBalanceList =
                l_strBondBalanceList.substring(0, l_strBondBalanceList.length() - 1);
        }
        //5�j�����������c�����X�g��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_strBondBalanceList;
    }

    /**
     * (get�]�͎c�����X�g)<BR>
     * T+0�`T+5�̗]�͎c���f�[�^���擾���A�ԋp����B<BR>
     * <BR>
     * 1�j������ԃ��f��.�c�Ɠ��v�Z�𐶐�<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�Ɩ����t�FGtlUtils.getTradingSystem().getBizDate()�̖߂�l�iTimestamp�^�ɕϊ��j<BR>
     * <BR>
     * 2�j�ȉ��̏��������[�v���AT+0�`T+5�̃f�[�^���擾<BR>
     * �@@�@@�E����]�̓T�[�r�X.get�o���\�z�`0�␳�����`()���R�[��<BR>
     * �@@�@@[get�o���\�z()�ɓn������]<BR>
     * �@@�@@�⏕����:����.�⏕����<BR>
     * �@@�@@��n��:�c�Ɠ��v�Z.roll(�j�̖߂�l<BR>
     * �@@�@@[roll(�j�ɓn������]<BR>
     * �@@�@@��j�s + 0�̏ꍇ�F 0<BR>
     * �@@�@@�@@�@@�@@�@@�@@<BR>
     * 3�j�߂�l�̐���<BR>
     * �@@�s�{�O����s�{�T�܂ł��Z�b�g�B��؂蕶���̓R����(:)<BR>
     * <BR>
     * �@@��jT+0 = 0, T+1 = 1000, T+2 = 2000, T+3 = 3000, T+4 = 4000, T+5 = 2500�̏ꍇ<BR>
     * �@@[0:1000:2000:3000:4000:2500]<BR>
     * <BR>
     * 4�j3�j�Ő��������]�͎c���f�[�^��ԋp����B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_tsSystemTimestamp - (���ݓ��t)<BR>
     * ���ݓ��t<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getTradingPowerBalanceList(
        SubAccount l_subAccount
        ) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTradingPowerBalanceList(SubAccount)";
        log.entering(STR_METHOD_NAME);

        String l_strTradingPowerBalanceList = "";
        
        //1�j������ԃ��f��.�c�Ɠ��v�Z�𐶐�
        //�Ɩ����t:GtlUtils.getTradingSystem().getBizDate()�̖߂�l�iTimestamp�^�ɕϊ��j
        Timestamp l_businessdate = new Timestamp(GtlUtils.getTradingSystem().getBizDate().getTime());
        WEB3GentradeBizDate l_gentradeBizDate =
            new WEB3GentradeBizDate(l_businessdate);

        //2�j�ȉ��̏��������[�v���AT+0�`T+5�̃f�[�^���擾
        //�@@�E����]�̓T�[�r�X.get�o���\�z�`0�␳�����`()���R�[��
        //�⏕����:����.�⏕����
        //��n��:�c�Ɠ��v�Z.roll(�j�̖߂�l
        WEB3TPTradingPowerService l_service =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);

        for (int i = 0; i < 6; i++)
        {
            double l_dblPaymentTradingPowerForCheck =
                l_service.getPaymentTradingPowerForCheck(
                    (WEB3GentradeSubAccount)l_subAccount, l_gentradeBizDate.roll(i));

            //3�j�߂�l�̐���
            //�s�{�O����s�{�T�܂ł��Z�b�g�B��؂蕶���̓R����(:)
            l_strTradingPowerBalanceList +=
                WEB3StringTypeUtility.formatNumber(l_dblPaymentTradingPowerForCheck);

            if (i != 5)
            {
                l_strTradingPowerBalanceList += ":";
            }
        }

        //4�j3�j�Ő��������]�͎c���f�[�^��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_strTradingPowerBalanceList;
    }

    /**
     * (get���T�[�r�X���X�g)<BR>
     * ���ݗL���ȃT�[�r�X���擾���A�ԋp����B<BR>
     * <BR>
     * 1�j�ȉ��̏����Łu�T�[�r�X�\���o�^�e�[�u���v����������B<BR>
     * �@@�@@[����] <BR>
     * �@@�@@�،���ЃR�[�h�F����.�،���ЃR�[�h <BR>
     * �@@�@@���X�R�[�h�F����.���X�R�[�h <BR>
     * �@@�@@�����R�[�h�F����.�����R�[�h <BR>
     * �@@�@@�K�p�I����������.���ݓ���<BR>
     * �@@�@@����敪�F0<BR>
     * �@@�@@�L���敪�F0<BR>
     * <BR>
     * 2�j�߂�l�̐���<BR>
     * �@@2-1�j�擾�ł��Ȃ��ꍇ�Anull��ԋp�B<BR>
     * <BR>
     * �@@2-2�j�P���ȏ�擾�o�����ꍇ�A�ȉ��̏������s���B<BR>
     * �@@�@@�R����(:)��؂�Ń��X�g�ɒǉ�����B<BR>
     * �@@�@@����̃T�[�r�X�敪�ɑ΂��ă��R�[�h�������擾�o�����ꍇ�A<BR>
     * �@@�@@�@@���X�g�ɒǉ�����̂͂P���̂�<BR>
     * <BR>
     * �@@�@@��j02�A02�A03�A04�A08�A10�A12�A12�A36�̏ꍇ�B<BR>
     * �@@�@@�@@�@@[02:03:04:08:10:12:36]<BR>
     * �@@�@@<BR>
     * �@@�@@<BR>
     * 3�j2)�Ő����������T�[�r�X���X�g��ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_tsSystemTimestamp - (���ݓ��t)<BR>
     * ���ݓ��t<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getInfoServiceList(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        Timestamp l_tsSystemTimestamp) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInfoServiceList(String, String, String, Timestamp)";
        log.entering(STR_METHOD_NAME);

        String l_strInfoServiceList = null;
        //1�j�ȉ��̏����Łu�T�[�r�X�\���o�^�e�[�u���v����������B
        //�،���ЃR�[�h�F����.�،���ЃR�[�h
        //���X�R�[�h�F����.���X�R�[�h
        //�����R�[�h�F����.�����R�[�h
        //�K�p�I����������.���ݓ���
        //����敪�F0
        //�L���敪�F0
        String l_strQuery = " institution_code = ? and branch_code = ? and account_code = ?"
            + " and appli_end_date >= ? and cancel_div = ? and effective_div = ?";

        String l_strSystemTimestamp = WEB3DateUtility.formatDate(
            l_tsSystemTimestamp,
            WEB3GentradeTimeDef.DATE_FORMAT_YMD);

        Object[] l_objQuerys =  {
            l_strInstitutionCode,
            l_strBranchCode,
            l_strAccountCode,
            WEB3DateUtility.getDate(
                l_strSystemTimestamp, WEB3GentradeTimeDef.DATE_FORMAT_YMD),
            WEB3SrvRegiCancelDivDef.USUAL_DEFAULT,
            WEB3EffectiveDivDef.EFFECTIVE};

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisSrvRegiApplicationRows = l_queryProcessor.doFindAllQuery(
                SrvRegiApplicationRow.TYPE,
                l_strQuery,
                l_objQuerys);

            //2�j�߂�l�̐���
            //2-1�j�擾�ł��Ȃ��ꍇ�Anull��ԋp�B
            if (l_lisSrvRegiApplicationRows.isEmpty())
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            //2-2�j�P���ȏ�擾�o�����ꍇ�A�ȉ��̏������s���B
            //�R����(:)��؂�Ń��X�g�ɒǉ�����B
            //����̃T�[�r�X�敪�ɑ΂��ă��R�[�h�������擾�o�����ꍇ�A���X�g�ɒǉ�����̂͂P���̂�
            //��j02�A02�A03�A04�A08�A10�A12�A12�A36�̏ꍇ�B
            //[02:03:04:08:10:12:36]
            int l_intSrvRegiApplicationRowsSize = l_lisSrvRegiApplicationRows.size();
            LinkedHashSet l_linkedHashSetSrvDivs = new LinkedHashSet();
            for (int i = 0; i < l_intSrvRegiApplicationRowsSize; i++)
            {
                SrvRegiApplicationRow l_srvRegiApplicationRow = (SrvRegiApplicationRow)l_lisSrvRegiApplicationRows.get(i);
                l_linkedHashSetSrvDivs.add(l_srvRegiApplicationRow.getSrvDiv());
            }

            Iterator l_itSrvDivs = l_linkedHashSetSrvDivs.iterator();
            if (l_itSrvDivs.hasNext())
            {
                l_strInfoServiceList = (String)l_itSrvDivs.next();
            }

            while (l_itSrvDivs.hasNext())
            {
                l_strInfoServiceList += ":" + (String)l_itSrvDivs.next();
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�T�[�o�Ƃ̒ʐM�Ɏ��s����", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //3�j2)�Ő����������T�[�r�X���X�g��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_strInfoServiceList;
    }

    /**
     * (get���A�g�p�Í����p�X���[�h)<BR>
     * ���A�g�p�̈Í����p�X���[�h���擾����B<BR>
     * <BR>
     * �P�j����.���҃R�[�h = null�̏ꍇ�A�ȉ��̏������s���B<BR>
     * �@@�@@�@@Web3�p�X���[�h�̎擾<BR>
     * �@@�@@�@@�@@1-1�j�g���A�J�E���g�}�l�[�W��.get�ڋq()���R�[�����A�ڋq�I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�@@�@@�@@[get�ڋq()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�،���ЃR�[�h�Fthis.�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@�@@���X�R�[�h�Fthis.���X�R�[�h<BR>
     * �@@�@@�@@�@@�@@�����R�[�h�Fthis.�ڋq�R�[�h<BR>
     * <BR>
     * �@@�@@�@@�@@1-2�jOpLoginSecurityService���A���O�C���^�C�v�������擾����B<BR>
     * <BR>
     * �@@�@@�@@�@@1-3�j���O�C���^�C�v����.������ = ����p�X���[�h�ݒ�<BR>
     * �@@�@@�@@�@@�@@�@@�iTRADING_PWD_ENV�j�̑����l�ɂ��ȉ��̏����𕪊�<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�������l���h0�FDEFAULT�i����p�X���[�h���ڂ��g�p���Ȃ��j�h�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�ڋq.get���O�C���h�c()���R�[�����A�ڋq�̃��O�C���h�c���擾����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�EOpLoginAdminServiceImpl.getLoginAttributes()���R�[�����A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���O�C���������擾����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[getLoginAttributes()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@arg0�F���O�C���h�c�Fget���O�C���h�c()�̖߂�l<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��L���擾�o���Ȃ��ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�E���O�C������.get()���R�[�����A���O�C��������蕜�����\�p�X���[�h���擾����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[get()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���O�C��������.�������\�p�X���[�h�iWEB3_ENCRYPTED_PASSWORD�j<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�������l�� �h1�F����p�X���[�h�g�p�h�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�ڋq�I�u�W�F�N�g���A����p�X���[�h���擾����B<BR>
     * <BR>
     * �@@�@@�@@�A�p�X���[�h�̕�����<BR>
     * �@@�@@�@@WEB3Crypt.decrypt()���R�[�����A�p�X���[�h�̕��������s���B<BR>
     * �@@�@@�@@[decrypt()�Ɏw�肷�����]<BR>
     * �@@�@@�@@String l_str: 1-3)�ɂĎ擾�����p�X���[�h<BR>
     * <BR>
     * �@@�@@�@@�B�T�[�r�X���p�N�����T�[�r�XImpl.get������t()���R�[�����A<BR>
     * �@@�@@�@@�@@���ݓ��t�ɓƎ��̏C����������B<BR>
     * �@@�@@�@@[get������t()�Ɏw�肷�����]<BR>
     * �@@�@@�@@���ݓ��t�F����.���ݓ��t<BR>
     * <BR>
     * �@@�@@�@@�C�T�[�r�X���Ǘ�.get�T�[�r�X�}�X�^()���R�[�����A<BR>
     * �@@�@@�@@�@@�T�[�r�X�}�X�^�[�I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�@@[get�T�[�r�X�}�X�^()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F����.�،���ЃR�[�h<BR>
     * �@@�@@�@@�T�[�r�X�敪�F����.�T�[�r�X�敪<BR>
     * �@@�@@�@@is�s���b�N�Ffalse<BR>
     * <BR>
     * �@@�@@�@@�D�T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�n�b�V���l�ꗗ()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�E������̔z��Ɉȉ��̏��ŃZ�b�g����B<BR>
     * �@@�@@�@@WEB3Crypt.decrypt()�̖߂�l<BR>
     * �@@�@@�@@�T�[�r�X���p�N�����T�[�r�XImpl.get������t()�̖߂�l�i'YYYYMMDD'�ɕϊ��j<BR>
     * �@@�@@�@@�T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�n�b�V���l�ꗗ()�߂�l<BR>
     * �@@�@@�@@�@@�@@.get�T�[�r�X���p�L�[�i�j�̖߂�l�i�z��̐����Z�b�g����B�j<BR>
     * <BR>
     * �@@�@@�@@�F�n�b�V���v�Z���s���B<BR>
     * �@@�@@�@@WEB3StringTypeUtility.createHashValue()���R�[������B<BR>
     * �@@�@@�@@[createHashValue()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�n�b�V���v�Z�����FSHA-1<BR>
     * �@@�@@�@@�v�Z�ΏہF�E�ɂĐ�������������̔z��B<BR>
     * <BR>
     * �Q�j�߂�l�̕ԋp<BR>
     * �@@2-1)����.���҃R�[�h != null�̏ꍇ�Anull��ԋp����B<BR>
     * �@@2-2)����.���҃R�[�h = null�̏ꍇ�A�n�b�V���v�Z�����p�X���[�h��ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_strServiceDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪<BR>
     * @@param l_tsSystemTimestamp - (���ݓ��t)<BR>
     * ���ݓ��t<BR>
     * @@param l_strTraderCode - (���҃R�[�h)<BR>
     * ���҃R�[�h<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getBondOrgUsedCryptPassword(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strServiceDiv,
        Timestamp l_tsSystemTimestamp,
        String l_strTraderCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getBondOrgUsedCryptPassword(String, String, String, String, Timestamp, String)";
        log.entering(STR_METHOD_NAME);

        if (l_strTraderCode != null)
        {
            //2-1)����.���҃R�[�h != null�̏ꍇ�Anull��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�P�j����.���҃R�[�h = null�̏ꍇ�A�ȉ��̏������s���B
        //�@@Web3�p�X���[�h�̎擾
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        //1-1�j�g���A�J�E���g�}�l�[�W��.get�ڋq()���R�[�����A�ڋq�I�u�W�F�N�g���擾����B
        WEB3GentradeAccountManager l_accMgr =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_gentradeMainAccount =
            l_accMgr.getMainAccount(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode);

        String l_strBondOrgUsedCryptPassword = null;

        //1-2�jOpLoginSecurityService���A���O�C���^�C�v�������擾����B
        OpLoginSecurityService l_securityService = (OpLoginSecurityService)Services.getService(
            OpLoginSecurityService.class);
        OpLoginAdminService l_admService = (OpLoginAdminService)Services.getService(
            OpLoginAdminService.class);
        LoginInfo l_loginInfo = l_securityService.getLoginInfo();
        Map l_mapAttributes = l_admService.getLoginTypeAttributes(l_loginInfo.getLoginTypeId());

        String l_strAttribute =
            (String) l_mapAttributes.get(
                WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV);

        String l_strPassword = null;
        WEB3Crypt l_crypt = new WEB3Crypt();
        if(WEB3TradingPwdEnvDef.DEFAULT.equals(l_strAttribute))
        {
            //�������l���h0�FDEFAULT�i����p�X���[�h���ڂ��g�p���Ȃ��j�h�̏ꍇ
            //�E�ڋq.get���O�C���h�c()���R�[�����A�ڋq�̃��O�C���h�c���擾����B
            long l_lngLoginId = l_gentradeMainAccount.getLoginId();

            OpLoginAdminService l_opLoginAdminService =
                (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
            Map l_mapLoginAttributes = l_opLoginAdminService.getLoginAttributes(l_lngLoginId);

            //��L���擾�o���Ȃ��ꍇ�A��O���X���[����B
            if (l_mapLoginAttributes == null
                || l_mapLoginAttributes.isEmpty())
            {
                log.debug("���O�C���������擾�ł��܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03094,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "���O�C���������擾�ł��܂���B");
            }

            l_strPassword = (String)l_mapLoginAttributes.get(WEB3LoginAttributeKeyDef.PASSWORD);
        }
        else if(WEB3TradingPwdEnvDef.USE_TRADING_PWD.equals(l_strAttribute))
        {
            //�������l�� �h1�F����p�X���[�h�g�p�h�̏ꍇ
            //�ڋq�I�u�W�F�N�g���A����p�X���[�h���擾����B
            l_strPassword = l_gentradeMainAccount.getTradingPassword();
        }

        //�A�p�X���[�h�̕�����
        //WEB3Crypt.decrypt()���R�[�����A�p�X���[�h�̕��������s���B
        //String l_str: 1-3)�ɂĎ擾�����p�X���[�h
        String l_strDecryptPassword = l_crypt.decrypt(l_strPassword);

        //�B�T�[�r�X���p�N�����T�[�r�XImpl.get������t()���R�[�����A���ݓ��t�ɓƎ��̏C����������B
        //���ݓ��t�F����.���ݓ��t
        WEB3SrvRegiStartInfoService l_srvRegiStartInfoService =
            (WEB3SrvRegiStartInfoService)Services.getService(WEB3SrvRegiStartInfoService.class);
        Date l_datControlTimestamp = l_srvRegiStartInfoService.getControlTimestamp(l_tsSystemTimestamp);

        //�C�T�[�r�X���Ǘ�.get�T�[�r�X�}�X�^()���R�[�����A�T�[�r�X�}�X�^�[�I�u�W�F�N�g���擾����B
        //�،���ЃR�[�h�F����.�،���ЃR�[�h
        //�T�[�r�X�敪�F����.�T�[�r�X�敪
        //is�s���b�N�Ffalse
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
            l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_strServiceDiv, false);

        //�D�T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�n�b�V���l�ꗗ()���R�[������B
        WEB3SrvRegiServiceUseKey[] l_srvRegiServiceUseKeys =
            l_srvRegiServiceMaster.getHashList();

        //�E������̔z��Ɉȉ��̏��ŃZ�b�g����B
        //WEB3Crypt.decrypt()�̖߂�l
        //�T�[�r�X���p�N�����T�[�r�XImpl.get������t()�̖߂�l�i'YYYYMMDD'�ɕϊ��j
        //�T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�n�b�V���l�ꗗ()�߂�l.get�T�[�r�X���p�L�[�i�j�̖߂�l�i�z��̐����Z�b�g����B�j
        List l_lisCreateHashValues = new ArrayList();
        l_lisCreateHashValues.add(l_strDecryptPassword);
        l_lisCreateHashValues.add(
            WEB3DateUtility.formatDate(
                l_datControlTimestamp, WEB3GentradeTimeDef.DATE_FORMAT_YMD));

        int l_intSrvRegiServiceUseKeyLength = l_srvRegiServiceUseKeys.length;

        for (int i = 0; i < l_intSrvRegiServiceUseKeyLength; i++)
        {
            l_lisCreateHashValues.add(l_srvRegiServiceUseKeys[i].getSrvUseKey());
        }

        String[] l_strCreateHashValues = new String[l_lisCreateHashValues.size()];
        l_lisCreateHashValues.toArray(l_strCreateHashValues);

        //�F�n�b�V���v�Z���s���B
        //WEB3StringTypeUtility.createHashValue()���R�[������B
        //�n�b�V���v�Z�����FSHA-1
        //�v�Z�ΏہF6�j�ɂĐ�������������B
        l_strBondOrgUsedCryptPassword =
            WEB3StringTypeUtility.createHashValue(
                WEB3SrvRegiHashCalHowToDivValueDef.SHA_1, l_strCreateHashValues);

        //2-2)����.���҃R�[�h = null�̏ꍇ�A�n�b�V���v�Z�����p�X���[�h��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_strBondOrgUsedCryptPassword;
    }

    /**
     * (validate���A�g�]�̓`�F�b�N)<BR>
     * ����]�͎c�����\�����邩�𔻒肷��B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�jvalidate���A�g�]�̓`�F�b�N�v�Q��<BR>
     * <BR>
     * ======================================================== <BR>
     * ��̈ʒu�Fis����t���O( )=false�̏ꍇ�A�]�͎c���G���[�Ƃ��ė�O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@tag�@@: BUSINESS_ERROR_01187 <BR>
     * ======================================================== <BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_trader - (�㗝���͎�)<BR>
     * �㗝���͎�<BR>
     * @@param l_dblUseAmt - (���p����)<BR>
     * ���p����<BR>
     * @@param l_tsDeliveryDate - (��n��)<BR>
     * ��n��<BR>
     * @@param l_tsOrderBizDate - (������)<BR>
     * ������<BR>
     * @@throws WEB3BaseException
     */
    public void validateBondOrgTradingPowerCheck(
        WEB3GentradeSubAccount l_subAccount,
        Trader l_trader,
        double l_dblUseAmt,
        Timestamp l_tsDeliveryDate,
        Timestamp l_tsOrderBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateBondOrgTradingPowerCheck(WEB3GentradeSubAccount, Trader, double, Timestamp, Timestamp)";
        log.entering(STR_METHOD_NAME);

        //�g�����o�������}�l�[�W���擾
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager)l_finApp.getTradingModule(ProductTypeEnum.AIO).getOrderManager();

        //get���iID(�،���� : Institution)
        long l_lngProductId = l_aioOrderManager.getProductId(l_subAccount.getInstitution());

        //���o���������e
        //�㗝���͎� �F����.�㗝���͎҃I�u�W�F�N�g
        //������� �F1001�i�o�������j
        //�U�փ^�C�v �F2�i�o���j
        //���iID �Fget���iID()�̖߂�l
        //���z �F����.���p����
        //�L�q �Fnull
        //�U�֗\��� �F����.��n��
        //���ϋ@@��ID �Fnull
        //����ID �Fnull
        WEB3AioNewOrderSpec l_aioNewOrderSpec =
            new WEB3AioNewOrderSpec(
                l_trader,
                OrderTypeEnum.CASH_OUT,
                AssetTransferTypeEnum.CASH_OUT,
                l_lngProductId,
                l_dblUseAmt,
                null,
                l_tsDeliveryDate,
                null,
                null);

        //�T�[�r�X���p�V�K�����X�V�C���^�Z�v�^
        WEB3SrvRegiNewOrderUpdateInterceptor l_srvRegiNewOrderUpdateInterceptor =
            new WEB3SrvRegiNewOrderUpdateInterceptor();

        //set��n��
        l_srvRegiNewOrderUpdateInterceptor.setDeliveryDate(l_tsDeliveryDate);

        //set������
        l_srvRegiNewOrderUpdateInterceptor.setOrderBizDate(l_tsOrderBizDate);

        //validate����]��(�⏕���� : �⏕����, 
        //�������e�C���^�Z�v�^ : Object[], �������e : Object[], ������� : OrderTypeEnum, �]�͍X�V�t���O : boolean)
        WEB3TPTradingPowerService l_service =
            (WEB3TPTradingPowerService)Services.getService(
                WEB3TPTradingPowerService.class);
        WEB3TPTradingPowerResult l_tradingPowerResult = l_service.validateTradingPower(
            l_subAccount,
            new Object[]{l_srvRegiNewOrderUpdateInterceptor},
            new Object[]{l_aioNewOrderSpec},
            OrderTypeEnum.CASH_OUT,
            false);

        //is����t���O( )
        boolean l_blnResultFlg = l_tradingPowerResult.isResultFlg();

        //is����t���O( )=false�̏ꍇ�A�]�͎c���G���[�Ƃ��ė�O���X���[����B
        if (!l_blnResultFlg)
        {
            log.debug("�]�͎c���G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01187,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�]�͎c���G���[�B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�������R�[�h)<BR>
     * �u�������}�X�^�e�[�u���v���������A<BR>
     * ���A�g���X�|���X�̖����R�[�h�����݂��邩�`�F�b�N����B<BR>
     * <BR>
     * �P�j�ȉ��̏����Łu�������}�X�^�e�[�u���v����������B<BR>
     * �@@doFindAllQuery()���R�[������B<BR>
     * �@@[����] <BR>
     * �@@�@@Row�^�C�v�FBondProductRow.TYPE <BR>
     * �@@�@@Where�F"institution_code = ? and product_code = ?"<BR>
     * �@@�@@���X�g�F�ȉ��̍��ڂ̃��X�g<BR>
     * �@@�@@����.�،���ЃR�[�h<BR>
     * �@@�@@����.�����R�[�h<BR>
     * <BR>
     * �@@[��������]<BR>
     * �@@�@@�،���� = ����.�،���ЃR�[�h and<BR>
     * �@@�@@�����R�[�h = ����.�����R�[�h<BR>
     * <BR>
     * <BR>
     * �Q�j�߂�l�̐���<BR>
     * �@@2-1�j�u�������}�X�^�e�[�u���v�̌������ʂ�0���̏ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * �@@2-2�j�u�������}�X�^�e�[�u���v�̌������ʂ�1���ȏ゠��ꍇ�Atrue��ԋp����B<BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@throws WEB3BaseException
     */
    public boolean validateBondProductCode(
        String l_strProductCode, String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateBondProductCode(String, String)";
        log.entering(STR_METHOD_NAME);

        String l_strQuery = " institution_code = ? and product_code = ? ";

        Object[] l_objQuerys = {l_strInstitutionCode, l_strProductCode};

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisBondProductRows = l_queryProcessor.doFindAllQuery(
                BondProductRow.TYPE,
                l_strQuery,
                l_objQuerys);

            if (l_lisBondProductRows.isEmpty())
            {
                //�u�������}�X�^�e�[�u���v�̌������ʂ�0���̏ꍇ�Afalse��ԋp����B
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�T�[�o�Ƃ̒ʐM�Ɏ��s����", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�u�������}�X�^�e�[�u���v�̌������ʂ�1���ȏ゠��ꍇ�Atrue��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return true;
    }
}

@
