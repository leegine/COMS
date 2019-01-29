head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.19.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMExchangeRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҈ב֓o�^�T�[�r�XImpl(WEB3AdminTMExchangeRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/10 ���G�� (���u) �V�K�쐬
*/
package webbroker3.trademanagement.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.data.GenCurrencyDao;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.gentrade.data.GenCurrencyRow;
import webbroker3.trademanagement.define.WEB3AdminTMRateDivDef;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeInfoUnit;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistInputResponse;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMExchangeRegistService;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��҈ב֓o�^�T�[�r�XImpl)<BR>
 * �Ǘ��҈ב֓o�^�T�[�r�X�����N���X<BR>
 * <BR>
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3AdminTMExchangeRegistServiceImpl
    implements WEB3AdminTMExchangeRegistService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMExchangeRegistServiceImpl.class);

    /**
     * �R���X�g���N�^
     */
    public WEB3AdminTMExchangeRegistServiceImpl()
    {

    }

    /**
     * �Ǘ��҈ב֓o�^�T�[�r�X�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���R�[������B<BR>
     * <BR>
     * get���͉��()<BR>
     * validate�ב֓o�^()<BR>
     * submit�ב֓o�^()<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request) ";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        //get���͉��()
        if (l_request instanceof WEB3AdminTMExchangeRegistInputRequest)
        {
            log.debug("get���͉��()");
            l_response = getInputScreen(
                (WEB3AdminTMExchangeRegistInputRequest)l_request);
        }
        //validate�ב֓o�^()
        else if (l_request instanceof WEB3AdminTMExchangeRegistConfirmRequest)
        {
            log.debug("validate�ב֓o�^())");
            l_response = validateExchangeRegist(
                (WEB3AdminTMExchangeRegistConfirmRequest)l_request);
        }
        //submit�ב֓o�^()
        else if (l_request instanceof WEB3AdminTMExchangeRegistCompleteRequest)
        {
            log.debug("submit�ב֓o�^()");
            l_response = submitExchangeRegist(
                (WEB3AdminTMExchangeRegistCompleteRequest)l_request);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get���͉��)<BR>
     * ���͉�ʎ擾�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�Ǘ��҈ב֓o�^�jget���͉�ʁv �Q��<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3AdminTMExchangeRegistInputResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminTMExchangeRegistInputResponse getInputScreen(
        WEB3AdminTMExchangeRegistInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getInputScreen(WEB3AdminTMExchangeRegistInputRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1.getInstanceFrom���O�C�����( )
        //�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        //WEB3SystemLayerException

        //1.2.validate����(String, boolean)
        //�����̃`�F�b�N���s���B
        //[����]
        //�@@�\�J�e�S���R�[�h�F �h��ב֓o�^�h
        //is�X�V�F true
        l_admin.validateAuthority(WEB3TransactionCategoryDef.BASE_FX_RATE_REG, true);

        //1.3.get�،���ЃR�[�h( )
        //�،���ЃR�[�h���擾����B
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //1.4.get�i���ʁj�ʉ�(String)
        //�i���ʁj�ʉ݂̔z����擾����B
        //[����]
        //�،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
        WEB3GentradeCurrency[] l_gentradeCurrencys =
            WEB3GentradeCurrency.getGentradeCurrency(l_strInstitutionCode);

        //1.5.(*)�@@get�i���ʁj�ʉ�()�߂�l�̗v�f����LOOP����
        // ArrayList�I�u�W�F�N�g�̐���
        int l_intCnt = 0;
        if (l_gentradeCurrencys != null && l_gentradeCurrencys.length > 0)
        {
            l_intCnt = l_gentradeCurrencys.length;
        }

        List l_lisArrays = new ArrayList();
        for (int i = 0; i < l_intCnt; i++)
        {
            //1.5.1.�ב֏��( )
            //�C���X�^���X�𐶐�����B
            WEB3AdminTMExchangeInfoUnit l_baseExChangeInfo =
                new WEB3AdminTMExchangeInfoUnit();

            //1.5.2.(*)�@@�v���p�e�B�Z�b�g
            //�ב֏��v���p�e�B�ɒl���Z�b�g����B
            //�ʉ݃R�[�h�F�@@�i���ʁj�ʉ�.�ʉ݃R�[�h
            //���[�g�敪�F�@@�h��בցh
            //���t�בփ��[�g�F�@@�i���ʁj�ʉ�.get���t��בփ��[�g()
            //���t�בփ��[�g�F�@@�i���ʁj�ʉ�.get���t��בփ��[�g()
            //�o�^�����F�@@�i���ʁj�ʉ�.��ב֍X�V���t
            GenCurrencyRow l_genCurrencyRow =
                (GenCurrencyRow)l_gentradeCurrencys[i].getDataSourceObject();

            l_baseExChangeInfo.currencyCode = l_gentradeCurrencys[i].getCurrencyCode();
            l_baseExChangeInfo.rateDiv = WEB3AdminTMRateDivDef.BASE_EXCHANGE;
            l_baseExChangeInfo.sellExchangeRate = WEB3StringTypeUtility.formatNumber(
                l_gentradeCurrencys[i].getSellBaseRate());
            l_baseExChangeInfo.buyExchangeRate = WEB3StringTypeUtility.formatNumber(
                l_gentradeCurrencys[i].getBuyBaseRate());
            l_baseExChangeInfo.registrationTime = l_genCurrencyRow.getRateUpdateTimestamp();
            l_lisArrays.add(l_baseExChangeInfo);

            //1.5.3.�ב֏��( )
            //�C���X�^���X�𐶐�����B
            WEB3AdminTMExchangeInfoUnit l_executedExChangeInfo =
                new WEB3AdminTMExchangeInfoUnit();

            //1.5.4.(*)�@@�v���p�e�B�Z�b�g
            //�ב֏��v���p�e�B�ɒl���Z�b�g����B
            //�ʉ݃R�[�h�F�@@�i���ʁj�ʉ�.�ʉ݃R�[�h
            //���[�g�敪�F�@@�h���בցh
            //���t�בփ��[�g�F�@@�i���ʁj�ʉ�.get���t���בփ��[�g()
            //���t�בփ��[�g�F�@@�i���ʁj�ʉ�.get���t���בփ��[�g()
            //�o�^�����F�@@�i���ʁj�ʉ�.���ב֍X�V���t
            l_executedExChangeInfo.currencyCode = l_gentradeCurrencys[i].getCurrencyCode();
            l_executedExChangeInfo.rateDiv = WEB3AdminTMRateDivDef.EXECUTED_EXCHANGE;
            l_executedExChangeInfo.sellExchangeRate = WEB3StringTypeUtility.formatNumber(
                l_gentradeCurrencys[i].getSellExecRate());
            l_executedExChangeInfo.buyExchangeRate = WEB3StringTypeUtility.formatNumber(
                l_gentradeCurrencys[i].getBuyExecRate());
            l_executedExChangeInfo.registrationTime = l_genCurrencyRow.getExecRateUpdateTimestamp();
            l_lisArrays.add(l_executedExChangeInfo);
        }
        //1.6.createResponse( )
        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminTMExchangeRegistInputResponse l_response =
            (WEB3AdminTMExchangeRegistInputResponse)l_request.createResponse();

        //1.7.(*) �v���p�e�B�Z�b�g
        //���X�|���X�f�[�^�v���p�e�B�ɒl���Z�b�g����B
        //(*) �ȉ��̂Ƃ���ɁA�v���p�e�B�ɃZ�b�g����B

        if (l_intCnt == 0)
        {
            l_response.exchangeInfoUnit = null;
        }
        else
        {
            //�ԋp����WEB3AdminTMExchangeInfoUnit[]�^�z��
            WEB3AdminTMExchangeInfoUnit[] l_exchangeInfos =
                new WEB3AdminTMExchangeInfoUnit[l_lisArrays.size()];

            // List����z��ɕϊ�
            l_lisArrays.toArray(l_exchangeInfos);

            //���X�|���X.�ב֏��ꗗ = ���������ב֏��̔z��
            l_response.exchangeInfoUnit = l_exchangeInfos;
        }
        //1.8.return ���X�|���X
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�ב֓o�^)<BR>
     * �ב֓o�^�m�F���s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�Ǘ��҈ב֓o�^�jvalidate�ב֓o�^�v �Q��<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3AdminTMExchangeRegistConfirmResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminTMExchangeRegistConfirmResponse validateExchangeRegist(
        WEB3AdminTMExchangeRegistConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateExchangeRegist(WEB3AdminTMExchangeRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1.validate( )
        //���N�G�X�g�f�[�^�̃`�F�b�N���s���B
        //1.1.1.validate( )
        //�ב֏��̃`�F�b�N���s���B
        l_request.validate();

        //1.2.getInstanceFrom���O�C�����( )
        //�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        //WEB3SystemLayerException

        //1.3.validate����(String, boolean)�����̃`�F�b�N���s���B
        //[����]
        //�@@�\�J�e�S���R�[�h�F �h��ב֓o�^�h
        //is�X�V�F true
        l_admin.validateAuthority(WEB3TransactionCategoryDef.BASE_FX_RATE_REG, true);

        //1.4.createResponse( )
        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminTMExchangeRegistConfirmResponse l_response =
            (WEB3AdminTMExchangeRegistConfirmResponse)l_request.createResponse();

        //1.5.return ���X�|���X
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�ב֓o�^)<BR>
     * �ב֓o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�Ǘ��҈ב֓o�^�jsubmit�ב֓o�^�v �Q��<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3AdminTMExchangeRegistCompleteResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminTMExchangeRegistCompleteResponse submitExchangeRegist(
        WEB3AdminTMExchangeRegistCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitExchangeRegist(WEB3AdminTMExchangeRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1.validate( )
        //���N�G�X�g�f�[�^�̃`�F�b�N���s���B
        //1.1.1.validate( )
        //�ב֏��̃`�F�b�N���s���B
        l_request.validate();

        //1.2.getInstanceFrom���O�C�����( )
        //�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3.validate����(String, boolean)�����̃`�F�b�N���s���B
        //[����]
        //�@@�\�J�e�S���R�[�h�F �h��ב֓o�^�h
        //is�X�V�F true
        l_admin.validateAuthority(WEB3TransactionCategoryDef.BASE_FX_RATE_REG, true);

        //1.4.validate����p�X���[�h(String)
        //�p�X���[�h�̃`�F�b�N���s���B
        //[����]
        //�p�X���[�h�F�@@���N�G�X�g.�Ïؔԍ�
        l_admin.validateTradingPassword(l_request.password);

        //1.5.(*) ���N�G�X�g�f�[�^.�ב֏��ꗗ[]�̊e�v�f����LOOP����
        //���N�G�X�g�f�[�^.�ב֏��ꗗ[]�̊e�v�f����LOOP����
        WEB3AdminTMExchangeInfoUnit[] l_exchangeInfoUnits = l_request.exchangeInfoUnit;

        int l_intLength = l_exchangeInfoUnits.length;

        for (int i = 0; i < l_intLength; i++)
        {
            //1.5.1.persist�ʉ�(�ב֏��)
            //�i���ʁj�ʉ݃e�[�u���Ɉב֏����X�V����B
            //[����]
            //�ב֏��F�@@���N�G�X�g�f�[�^.�ב֏��ꗗ[index]
            persistCurrency(l_exchangeInfoUnits[i]);
        }

        //1.6.createResponse( )
        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminTMExchangeRegistCompleteResponse l_response =
            (WEB3AdminTMExchangeRegistCompleteResponse)l_request.createResponse();

        //1.7.return ���X�|���X
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (persist�ʉ�)<BR>
     * �i���ʁj�ʉ݃e�[�u���Ɉב֏����X�V����B<BR>
     * <BR>
     * ���@@�בփ��[�g�ɓ��͂��Ȃ��ꍇ�i�ב֏��.���t�בփ��[�g == null && <BR>
     * �@@�@@�ב֏��.���t�בփ��[�g == null�j<BR>
     * �@@�������I������B�ireturn�j<BR>
     * <BR>
     * ���@@�בփ��[�g�ɓ��͂�����ꍇ<BR>
     * �@@�בփ��[�g���i���ʁj�ʉ݃e�[�u���ɍX�V����B<BR>
     * �@@�@@�X�V���e�͈ȉ��̒ʂ�B<BR>
     * <BR>
     * �@@�@@��בցi�ב֏��.���[�g�敪 == �h��בցh�j�̏ꍇ�A<BR>
     * �@@�@@�@@�|�y��Trade�z�⑫����.DB�X�V\\70.���Ǘ���\\11.�ב֓o�^<BR>
     * �@@�@@�@@�@@�u�ב֓o�^_�i���ʁj�ʉ݃e�[�u���d�l.xls#<BR>
     * �@@�@@�@@�@@�@@�ב֓o�^_�i���ʁj�ʉ݃e�[�u�� DB�X�V�i��בցj�v�Q�ƁB<BR>
     * <BR>
     * �@@�@@��בցi�ב֏��.���[�g�敪 == �h���בցh�j�̏ꍇ�A<BR>
     * �@@�@@�@@�|�y��Trade�z�⑫����.DB�X�V\\70.���Ǘ���\\11.�ב֓o�^<BR>
     * �@@�@@�@@�@@�u�ב֓o�^_�i���ʁj�ʉ݃e�[�u���d�l.xls#<BR>
     * �@@�@@�@@�@@�@@�ב֓o�^_�i���ʁj�ʉ݃e�[�u�� DB�X�V�i���בցj�v�Q�ƁB<BR>
     * <BR>
     * �@@�@@�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * @@param l_exchangeInfo - �ב֏��
     * @@throws WEB3BaseException
     */
    private void persistCurrency(WEB3AdminTMExchangeInfoUnit l_exchangeInfo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "persistCurrency(WEB3AdminTMExchangeInfoUnit l_exchangeInfo)";
        log.entering(STR_METHOD_NAME);

        //   �@@�בփ��[�g�ɓ��͂��Ȃ��ꍇ�i�ב֏��.���t�בփ��[�g == null
        // �@@�@@&& �ב֏��.���t�בփ��[�g == null�j
        // �@@�������I������B�ireturn�j
        if (WEB3StringTypeUtility.isEmpty(l_exchangeInfo.sellExchangeRate)
            && WEB3StringTypeUtility.isEmpty(l_exchangeInfo.buyExchangeRate))
        {
            log.debug("�בփ��[�g�ɓ��͂��Ȃ��ꍇ");
            log.exiting(STR_METHOD_NAME);
            return;
        }

        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        //WEB3SystemLayerException
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        String l_strCurrencyCode = l_exchangeInfo.currencyCode;

        log.debug("l_strInstitutionCode = " + l_strInstitutionCode);
        log.debug("l_strCurrencyCode = " + l_strCurrencyCode);

        GenCurrencyParams l_genCurrencyParams = null;
        try
        {
            GenCurrencyRow l_row =
                GenCurrencyDao.findRowByPk(
                    l_strInstitutionCode,
                    l_strCurrencyCode);
            l_genCurrencyParams = new GenCurrencyParams(l_row);
        }
        catch (DataFindException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�ב֏��.���t�בփ��[�g
        double l_dblSellExchangeRate = 0.0D;
        if (!WEB3StringTypeUtility.isEmpty(l_exchangeInfo.sellExchangeRate))
        {
            l_dblSellExchangeRate = Double.parseDouble(l_exchangeInfo.sellExchangeRate);
        }

        //�ב֏��.���t�בփ��[�g
        double l_dblBuyExchangeRate = 0.0D;
        if (!WEB3StringTypeUtility.isEmpty(l_exchangeInfo.buyExchangeRate))
        {
            l_dblBuyExchangeRate = Double.parseDouble(l_exchangeInfo.buyExchangeRate);
        }
        //   �@@�בփ��[�g�ɓ��͂�����ꍇ
        // �@@�בփ��[�g���i���ʁj�ʉ݃e�[�u���ɍX�V����
        // �@@�@@�X�V���e�͈ȉ��̒ʂ�B
        // �@@�@@��בցi�ב֏��.���[�g�敪 == �h��בցh�j�̏ꍇ
        // �@@�@@�@@�|�y��Trade�z�⑫����.DB�X�V\\70.���Ǘ���\\11.�ב֓o�^
        // �@@�@@�@@�@@�u�ב֓o�^_�i���ʁj�ʉ݃e�[�u���d�l.xls#�ב֓o�^_�i���ʁj�ʉ݃e�[�u�� DB�X�V�i��בցj�v�Q�ƁB
        if (WEB3AdminTMRateDivDef.BASE_EXCHANGE.equals(l_exchangeInfo.rateDiv))
        {
            log.debug("��בցi�ב֏��.���[�g�敪 == �h��בցh�j�̏ꍇ");
            //���񔄕t�בփ��[�g
            //�ב֏��.���t�בփ��[�g
            //"�i�ב֏��.���t�בփ��[�g == null�j�̏ꍇ�A�i�����l�j�B
            //�i�ב֏��.���t�בփ��[�g != null�j�̏ꍇ�A
            //�@@�|���͒l�i�ב֏��.���t�בփ��[�g�j���Z�b�g�B"
            //�O�񔄕t�בփ��[�g
            //"�i�ב֏��.���t�בփ��[�g == null�j�̏ꍇ�A�i�����l�j�B
            //�i�ב֏��.���t�בփ��[�g != null�j�̏ꍇ�A
            //�@@�|���񔄕t�בփ��[�g�i���X�V�O�̒l�j���Z�b�g�B"
            if (!WEB3StringTypeUtility.isEmpty(l_exchangeInfo.sellExchangeRate))
            {
                l_genCurrencyParams.setPrevSellRate(
                    l_genCurrencyParams.getCurrentSellRate());
                l_genCurrencyParams.setCurrentSellRate(l_dblSellExchangeRate);
            }

            //���񔃕t�בփ��[�g
            //"�i�ב֏��.���t�בփ��[�g == null�j�̏ꍇ�A�i�����l�j�B
            //�i�ב֏��.���t�בփ��[�g != null�j�̏ꍇ�A
            //�@@�|���͒l�i�ב֏��.���t�בփ��[�g�j���Z�b�g�B"
            //�O�񔃕t�בփ��[�g
            //"�i�ב֏��.���t�בփ��[�g == null�j�̏ꍇ�A�i�����l�j�B
            //�i�ב֏��.���t�בփ��[�g != null�j�̏ꍇ�A
            //�@@�|���񔃕t�בփ��[�g�i���X�V�O�̒l�j���Z�b�g�B"
            if (!WEB3StringTypeUtility.isEmpty(l_exchangeInfo.buyExchangeRate))
            {
                l_genCurrencyParams.setPrevBuyRate(
                    l_genCurrencyParams.getCurrentBuyRate());
                l_genCurrencyParams.setCurrentBuyRate(l_dblBuyExchangeRate);
            }

            //��ב֍X�V�҃R�[�h : �Ǘ��҃R�[�h
            l_genCurrencyParams.setRateLastUpdater(l_administrator.getAdministratorCode());

            //��ב֍X�V���t:���ݓ���
            l_genCurrencyParams.setRateUpdateTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());

            //�X�V�҃R�[�h:�Ǘ��҃R�[�h
            l_genCurrencyParams.setLastUpdater(l_administrator.getAdministratorCode());

            //�X�V���t:���ݓ���
            l_genCurrencyParams.setLastUpdatedTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());

        }
        // �@@�@@��בցi�ב֏��.���[�g�敪 == �h���בցh�j�̏ꍇ�A
        // �@@�@@�@@�|�y��Trade�z�⑫����.DB�X�V\\70.���Ǘ���\\11.�ב֓o�^
        // �@@�@@�@@�@@�u�ב֓o�^_�i���ʁj�ʉ݃e�[�u���d�l.xls#�ב֓o�^_�i���ʁj�ʉ݃e�[�u�� DB�X�V�i���בցj�v�Q��
        else if (WEB3AdminTMRateDivDef.EXECUTED_EXCHANGE.equals(l_exchangeInfo.rateDiv))
        {
            log.debug("��בցi�ב֏��.���[�g�敪 == �h���בցh�j�̏ꍇ");

            //���񔄕t���בփ��[�g
            //"�i�ב֏��.���t�בփ��[�g == null�j�̏ꍇ�A�i�����l�j�B
            //�i�ב֏��.���t�בփ��[�g != null�j�̏ꍇ�A
            //�@@�|���͒l�i�ב֏��.���t�בփ��[�g�j���Z�b�g�B"
            //�O�񔄕t���בփ��[�g
            //"�i�ב֏��.���t�בփ��[�g == null�j�̏ꍇ�A�i�����l�j�B
            //�i�ב֏��.���t�בփ��[�g != null�j�̏ꍇ�A
            //�@@�|���񔄕t���בփ��[�g�i���X�V�O�̒l�j���Z�b�g�B"
            if (!WEB3StringTypeUtility.isEmpty(l_exchangeInfo.sellExchangeRate))
            {
                l_genCurrencyParams.setPrevSellExecRate(
                    l_genCurrencyParams.getCurrentSellExecRate());
                l_genCurrencyParams.setCurrentSellExecRate(l_dblSellExchangeRate);
            }

            //���񔃕t���בփ��[�g
            //"�i�ב֏��.���t�בփ��[�g == null�j�̏ꍇ�A�i�����l�j�B
            //�i�ב֏��.���t�בփ��[�g != null�j�̏ꍇ�A
            //�@@�|���͒l�i�ב֏��.���t�בփ��[�g�j���Z�b�g�B"
            //�O�񔃕t���בփ��[�g
            //"�i�ב֏��.���t�בփ��[�g == null�j�̏ꍇ�A�i�����l�j�B
            //�i�ב֏��.���t�בփ��[�g != null�j�̏ꍇ�A
            //�@@�|���񔃕t���בփ��[�g�i���X�V�O�̒l�j���Z�b�g�B"
            if (!WEB3StringTypeUtility.isEmpty(l_exchangeInfo.buyExchangeRate))
            {
                l_genCurrencyParams.setPrevBuyExecRate(
                    l_genCurrencyParams.getCurrentBuyExecRate());
                l_genCurrencyParams.setCurrentBuyExecRate(l_dblBuyExchangeRate);
            }

            //���ב֍X�V�҃R�[�h : �Ǘ��҃R�[�h
            l_genCurrencyParams.setExecRateLastUpdater(l_administrator.getAdministratorCode());

            //���ב֍X�V���t:���ݓ���
            l_genCurrencyParams.setExecRateUpdateTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());

            //�X�V�҃R�[�h:�Ǘ��҃R�[�h
            l_genCurrencyParams.setLastUpdater(l_administrator.getAdministratorCode());

            //�X�V���t:���ݓ���
            l_genCurrencyParams.setLastUpdatedTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());
        }
        // �@@�@@�ȊO�̏ꍇ�A��O���X���[����B
        else
        {
            log.debug("�ב֏��̃��[�g�敪������`�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02159,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            WEB3DataAccessUtility.updateRow(l_genCurrencyParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return;
    }
}
@
