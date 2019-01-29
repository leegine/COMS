head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.38.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqForeignCostRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���������n�萔���o�^�T�[�r�XImpl(WEB3AdminFeqForeignCostRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 �Ջ`�g(���u) �V�K�쐬
                   2005/08/01 ��O��(���u) ���r���[
Revesion History : 2007/01/16 ꎉ� (���u) �d�l�ύXNo.335��Ή�
Revesion History : 2008/11/12 ���m�a (���u) �d�l�ύXNo.494�A�c�a�X�V�d�lNo.101
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.data.ForeignCostParams;
import webbroker3.feq.data.ForeignCostRow;
import webbroker3.feq.define.WEB3FeqCostDivDef;
import webbroker3.feq.define.WEB3FeqRoundDivDef;
import webbroker3.feq.message.WEB3AdminFeqForeignCostRegistCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqForeignCostRegistCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqForeignCostRegistConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqForeignCostRegistConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqForeignCostRegistInputRequest;
import webbroker3.feq.message.WEB3AdminFeqForeignCostRegistInputResponse;
import webbroker3.feq.message.WEB3AdminFeqForeignCostUnit;
import webbroker3.feq.service.delegate.WEB3AdminFeqForeignCostRegistService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeFeqBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (�O���������n�萔���o�^�T�[�r�XImpl)<BR>
 * �O���������n�萔���o�^�T�[�r�X�����N���X<BR>
 * 
 * @@author �Ջ`�g
 * @@version 1.0
 */
public class WEB3AdminFeqForeignCostRegistServiceImpl implements WEB3AdminFeqForeignCostRegistService 
{
    /**
     * �i���O�o�̓��[�e�B���e�B�j�B
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqForeignCostRegistServiceImpl.class);
    
    /**
     * @@roseuid 42CE39F00177
     */
    public WEB3AdminFeqForeignCostRegistServiceImpl() 
    {
     
    }
    
    /**
     * �O���������n�萔���o�^�T�[�r�X�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���R�[������B<BR>
     * <BR>
     *    get���͉��()<BR>
     *    validate�o�^()<BR>
     *    submit�o�^()<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4214982000EC
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^��Null�ł�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GenResponse l_response;

        //���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���R�[������B
        //�|get���͉��()
        if (l_request instanceof WEB3AdminFeqForeignCostRegistInputRequest)
        {
            l_response = 
                getInputScreen((WEB3AdminFeqForeignCostRegistInputRequest) l_request);
        }
        //�|validate�o�^()
        else if (l_request instanceof WEB3AdminFeqForeignCostRegistConfirmRequest)
        {
            l_response = 
                validateRegist((WEB3AdminFeqForeignCostRegistConfirmRequest) l_request);
        }
        //�|submit�o�^()
        else if (l_request instanceof WEB3AdminFeqForeignCostRegistCompleteRequest)
        {
            l_response = 
                submitRegist((WEB3AdminFeqForeignCostRegistCompleteRequest) l_request);
        } 
        else
        {
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
     * �V�[�P���X�}<BR>
     * �u�i�i�ǁj���n�萔���o�^�jget���͉�ʁv �Q��<BR>
     * ======================================================== <BR>
     * �V�[�P���X�}(�u(�O�������T�[�r�X���f��) /<BR>
     * �i�i�ǁj���n�萔���o�^�jget���͉�� <BR>
     *  1.9.1 (*1)�Ő��������s��R�[�h�̔z��Ƀ��N�G�X�g.�s��R�[�h��<BR>
     * �܂܂�ĂȂ��ꍇ�A��O���X���[����B<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00645 <BR>
     * ========================================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return webbroker3.feq.message.WEB3AdminFeqForeignCostRegistInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 42149847004F
     */
    protected WEB3AdminFeqForeignCostRegistInputResponse getInputScreen(
        WEB3AdminFeqForeignCostRegistInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getInputScreen(WEB3AdminFeqForeignCostRegistInputRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 ���N�G�X�g�f�[�^�̐��������`�F�b�N����
        l_request.validate();

        //1.2 ���O�C����񂩂�Ǘ��҃C���X�^���X���擾����
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();

        //1.3 �����`�F�b�N���s���B
        //[����]
        //�@@�\�J�e�S���[�R�[�h�F �@@�\�J�e�S���[�R�[�h.�h�O���i���n�萔���Ǘ��j�h
        //is�X�V�F false
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_LOCATION_COMMISSION_MANAGE, 
            false);

        //1.4 �،���ЃR�[�h���擾����
        String l_strInstitutionCode = l_web3Administrator.getInstitutionCode();

        //get�戵�\�s��
        //�،���ЁF�@@get�،���ЃR�[�h()�̖߂�l
        //�����^�C�v�F�@@ProductTypeEnum.�O������
        String[] l_strPossibleMarkets = WEB3GentradeFeqBranchMarketDealtCond.getHandlingPossibleMarket(
            l_strInstitutionCode, ProductTypeEnum.FOREIGN_EQUITY);
       
        if (l_strPossibleMarkets.length == 0)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�e�[�u���ɊY������f�[�^������܂���B");
        }
        
        //1.6 ��̃��X�g�𐶐�����
        List l_lisMarketCode = new ArrayList();

        //get�戵�\�s��()�̖߂�l�̗v�f����Loop����
        for (int i = 0; i < l_strPossibleMarkets.length; i++)
        {
            //�s��R�[�h�����X�g�ɒǉ�����B 
            //[����]
            //arg0�F get�戵�\�s��()�̖߂�l�̊Y���v�f
            l_lisMarketCode.add(l_strPossibleMarkets[i]);
        }

        //1.8 �s��R�[�h�̔z����擾����
        String[] l_strMarketCodes = new String[l_lisMarketCode.size()];
        l_lisMarketCode.toArray(l_strMarketCodes);

        //1.10 ���X�|���X�f�[�^�𐶐�����
        WEB3AdminFeqForeignCostRegistInputResponse l_response =
            new WEB3AdminFeqForeignCostRegistInputResponse();
        l_response.foreignCostRegist = null;
        l_response.marketList = l_strMarketCodes;

        WEB3AdminFeqForeignCostUnit[] l_feqForeignCostUnits = null;
        //1.9 (*2) ���N�G�X�g.�s��R�[�h != null �̏ꍇ
        if (!WEB3StringTypeUtility.isEmpty(l_request.marketCode)) 
        {
            List l_lisRows = null;
            try
            {
                //1.9.1 (*1)�Ő��������s��R�[�h�̔z��Ƀ��N�G�X�g.�s��R�[�h��
                //�܂܂�ĂȂ��ꍇ�A��O���X���[����B
                // class: WEB3BusinessLayerException
                // tag: BUSINESS_ERROR_00645
                if (!WEB3Toolkit.contain(l_lisMarketCode, l_request.marketCode))
                {
                    log.debug("�s��R�[�h�̔z��Ƀ��N�G�X�g.�s��R�[�h���܂܂�ĂȂ�");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00645, 
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }

                //1.9.2 �N�G���v���Z�b�T���擾����
                QueryProcessor l_processor = Processors.getDefaultProcessor();
                //1.9.3 �C�O���o��}�X�^�e�[�u������A�Y���f�[�^���擾����B
                //[����]
                //Row�^�C�v�F �C�O���o��}�X�^Row.TYPE
                //Where�F "institution_code = ? and market_code = ? and cost_div = ? and side_div = ? "
                String l_strWhere = " institution_code = ? and market_code = ? and cost_div = ? and side_div = ? ";
                //orderBy�F "amount_from, appli_start_date"
                String l_strOrderBy = " amount_from, appli_start_date ";
                //Condition�F null
                //���X�g�F �ȉ��̗v�f�̔z��
                Object[] l_objBinds = new Object[4];
                //   get�،���ЃR�[�h()�̖߂�l
                l_objBinds[0] = l_strInstitutionCode;
                //   ���N�G�X�g.�s��R�[�h
                l_objBinds[1] = l_request.marketCode;
                //   ���N�G�X�g.�R�X�g�敪
                l_objBinds[2] = l_request.costDiv;
                //   ���N�G�X�g.�����敪
                l_objBinds[3] = l_request.dealingType;
                l_lisRows = l_processor.doFindAllQuery(
                    ForeignCostRow.TYPE, 
                    l_strWhere,
                    l_strOrderBy, 
                    null, 
                    l_objBinds);
            }
            catch (DataFindException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    this.getClass().getName() + STR_METHOD_NAME, 
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

            //1.9.4 ��̃��X�g�𐶐�����
            List l_lisForeignCost = new ArrayList();

            if (l_lisRows != null)
            {
                //1.9.5 (*3) �擾�������R�[�h����Loop����
                for (int j = 0; j < l_lisRows.size(); j++) 
                {
                    ForeignCostRow l_foreignCostRow =
                        (ForeignCostRow) l_lisRows.get(j);
                    //1.9.5.1 ���n�萔�����C���X�^���X�𐶐�����
                    WEB3AdminFeqForeignCostUnit l_unit =
                        new WEB3AdminFeqForeignCostUnit();
                    //1.9.5.2 (*4) �v���p�e�B�Z�b�g
                    //(*4) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
                    //�K�p���ԁi���j�F �C�O���o��e�[�u��.�E�v�J�n�N����
                    l_unit.applyStartDate = l_foreignCostRow.getAppliStartDate();
                    //�K�p���ԁi���j�F �C�O���o��e�[�u��.�E�v�I���N����
                    l_unit.applyEndDate = l_foreignCostRow.getAppliEndDate();
                    //������z�i���j�F �C�O���o��e�[�u��.��������iFROM�j
                    l_unit.tradingAmtFrom = 
                        WEB3StringTypeUtility.formatNumber(l_foreignCostRow.getAmountFrom());
                    //������z�i���j�F �C�O���o��e�[�u��.��������iTO�j
                    if (l_foreignCostRow.getAmountToIsNull())
                    {
                        l_unit.tradingAmtTo = null;
                    }
                    else
                    {
                        l_unit.tradingAmtTo =
                            WEB3StringTypeUtility.formatNumber(l_foreignCostRow.getAmountTo());
                    }
                    //�������F �C�O���o��e�[�u��.������
                    l_unit.collectRate = 
                        WEB3StringTypeUtility.formatNumber(l_foreignCostRow.getCommisionRate());
                    //�t�����z�F �C�O���o��e�[�u��.�t�����z
                    l_unit.additionalAmt = 
                        WEB3StringTypeUtility.formatNumber(l_foreignCostRow.getAddAmount());
    
                    //1.9.5.3 ���X�g�ɒǉ�����B
                    //[����]
                    //arg0�F ���n�萔�����I�u�W�F�N�g
                    l_lisForeignCost.add(l_unit);
                }
            }
            
            //1.11 (*5) �v���p�e�B�Z�b�g
            //(*5) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
            //���n�萔�����ꗗ�F ���n�萔�����̔z��(*)
            //�s��R�[�h�ꗗ�F �s��R�[�h�̔z��
            //(*)���N�G�X�g.�s��R�[�h == null �̏ꍇ�́Anull
            //1.9.6 ���n�萔�����̔z����擾����
            l_feqForeignCostUnits = 
                new WEB3AdminFeqForeignCostUnit[l_lisForeignCost.size()];
            l_lisForeignCost.toArray(l_feqForeignCostUnits);
            l_response.foreignCostRegist = l_feqForeignCostUnits;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�o�^)<BR>
     * �o�^���e�̊m�F���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�ǁj���n�萔���o�^�jvalidate�o�^�v �Q��<BR>
     * ======================================================== <BR>
     * �V�[�P���X�}(�u(�O�������T�[�r�X���f��) /<BR>
     * �i�i�ǁj���n�萔���o�^�jvalidate�o�^ <BR> 
     *  1.4 �s�ꂪ�擾�ł��Ȃ������ꍇ�A�s��R�[�h�s���Ƃ���<BR>
     *�@@��O���X���[����B<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00003 <BR>
     * ========================================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqForeignCostRegistConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4214984F01C6
     */
    protected WEB3AdminFeqForeignCostRegistConfirmResponse validateRegist(
        WEB3AdminFeqForeignCostRegistConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateRegist(WEB3AdminFeqForeignCostRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjectManager = 
            (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();

        //1.1 ���N�G�X�g�f�[�^�̐��������`�F�b�N����
        l_request.validate();

        //1.2 ���O�C����񂩂�Ǘ��҃C���X�^���X���擾����
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();

        //1.3 �����`�F�b�N���s���B
        //[����]
        //�@@�\�J�e�S���[�R�[�h�F �@@�\�J�e�S���[�R�[�h.�h�O���i���n�萔���Ǘ��j�h
        //is�X�V�F true
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_LOCATION_COMMISSION_MANAGE, 
            true);

        //1.4 �s��I�u�W�F�N�g���擾����B

        //[����]
        //�،���ЃR�[�h�F �Ǘ���.get�،���ЃR�[�h()�̖߂�l
        //�s��R�[�h�F ���N�G�X�g.�s��R�[�h
        //�s�ꂪ�擾�ł��Ȃ������ꍇ�A�s��R�[�h�s���Ƃ���
        //��O���X���[����B
        try
        {
            l_finObjectManager.getMarket(
                l_web3Administrator.getInstitutionCode(),
                l_request.marketCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�s�ꂪ�擾�ł��Ȃ������ꍇ�A�s��R�[�h�s��", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.5 ���X�|���X�f�[�^�𐶐�����
        WEB3AdminFeqForeignCostRegistConfirmResponse l_response =
            new WEB3AdminFeqForeignCostRegistConfirmResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�o�^)<BR>
     * �o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�ǁj���n�萔���o�^�jsubmit�o�^�v �Q��<BR>
     * ======================================================== <BR>
     * �V�[�P���X�}(�u(�O�������T�[�r�X���f��) /<BR>
     * �i�i�ǁj���n�萔���o�^�jsubmit�o�^ <BR> 
     *  1.5 �s�ꂪ�擾�ł��Ȃ������ꍇ�A�s��R�[�h�s���Ƃ���<BR>
     *�@@��O���X���[����B<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00003 <BR>
     * ========================================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqForeignCostRegistCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 42149854035D
     */
    protected WEB3AdminFeqForeignCostRegistCompleteResponse submitRegist(
        WEB3AdminFeqForeignCostRegistCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitRegist(WEB3AdminFeqForeignCostRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjectManager 
            = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();

        //1.1 validate()
        l_request.validate();

        //1.2 ���O�C����񂩂�Ǘ��҃C���X�^���X���擾����
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();

        //1.3 �����`�F�b�N���s���B
        //[����]
        //�@@�\�J�e�S���[�R�[�h�F �@@�\�J�e�S���[�R�[�h.�h�O���i���n�萔���Ǘ��j�h
        //is�X�V�F true
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_LOCATION_COMMISSION_MANAGE, 
            true);

        //1.4 �p�X���[�h�̃`�F�b�N���s���B

        //[����]
        //�p�X���[�h�F ���N�G�X�g.�Ïؔԍ�
        l_web3Administrator.validateTradingPassword(l_request.password);

        //1.5 �s��I�u�W�F�N�g���擾����B

        //[����]
        //�،���ЃR�[�h�F �Ǘ���.get�،���ЃR�[�h()�̖߂�l
        //�s��R�[�h�F ���N�G�X�g.�s��R�[�h
        //�s�ꂪ�擾�ł��Ȃ������ꍇ�A�s��R�[�h�s���Ƃ���
        //��O���X���[����B
        try 
        {
            l_finObjectManager.getMarket(
                l_web3Administrator.getInstitutionCode(),
                l_request.marketCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�s�ꂪ�擾�ł��Ȃ������ꍇ�A�s��R�[�h�s��", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.6 �N�G���v���Z�b�T���擾����B
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            //1.7 �C�O���o��}�X�^�e�[�u���̃��R�[�h���폜����B

            //[����]
            //Row�^�C�v�F �C�O���o��}�X�^Row.TYPE
            //Where�F "institution_code = ? and market_code = ? and cost_div = ? and side_div = ? "
            String l_strWhere = " institution_code = ? and market_code = ? and cost_div = ? and side_div = ? ";
            //���X�g�F �ȉ��̗v�f�̔z��
            Object[] l_objBinds = new Object[4];
            //   �Ǘ���.�،���ЃR�[�h
            l_objBinds[0] = l_web3Administrator.getInstitutionCode();
            //   ���N�G�X�g.�s��R�[�h
            l_objBinds[1] = l_request.marketCode;
            //   ���N�G�X�g.�R�X�g�敪
            l_objBinds[2] = l_request.costDiv;
            //   ���N�G�X�g.�����敪
            l_objBinds[3] = l_request.dealingType;
            l_processor.doDeleteAllQuery(
                ForeignCostRow.TYPE, 
                l_strWhere, 
                l_objBinds);
        }
        catch (DataFindException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
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

        //1.8 (*1) ���N�G�X�g.���n�萔�����ꗗ�̊e�v�f�ɂ��āALoop����
        for (int i = 0; i < l_request.feqLocalFeeUnit.length; i++) 
        {
            //1.8.1 �C�O���o��}�X�^�e�[�u���ɓo�^����B

            //[����]
            //�Ǘ��ҁF �Ǘ��҃I�u�W�F�N�g
            //���n�萔�����F ���n�萔�����I�u�W�F�N�g
            //�s��R�[�h�F ���N�G�X�g.�s��R�[�h
            //���o��敪�F ���N�G�X�g.�R�X�g�敪
            //�����敪�F ���N�G�X�g.�����敪
            createForeignCostMaster(
                l_web3Administrator,
                l_request.feqLocalFeeUnit[i],
                l_request.marketCode,
                l_request.costDiv,
                l_request.dealingType);
        }

        //1.9 ���X�|���X�f�[�^�𐶐�����
        WEB3AdminFeqForeignCostRegistCompleteResponse l_response =
            new WEB3AdminFeqForeignCostRegistCompleteResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create�C�O���o��}�X�^)<BR>
     * �C�O���o��}�X�^�e�[�u���̃��R�[�h��o�^����B<BR>
     * <BR>
     * �P�j�C�O���o��}�X�^�s�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�C�O���o��}�X�^�s�C���X�^���X�Ƀv���p�e�B���Z�b�g����B<BR>
     * <BR>
     *    ��DB�X�V�d�l�Q��<BR>
     * <BR>
     * �R�jDB�ɓo�^����B<BR><BR>
     * <BR>
     *    WEB3DataAccessUtility.insertRow()���R�[������B<BR>
     * <BR>
     *    [����]<BR>
     *    l_row�F �C�O���o��}�X�^�s�C���X�^���X<BR>
     * 
     *    ��insert�Ɏ��s�����ꍇ�́A�u�o�^�f�[�^�s�����i�d���o�^�s�j�v�̋Ɩ��G���[�Ƃ���B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02183<BR>
     * @@param l_admin - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g<BR>
     * 
     * @@param l_offshoreCommissionInfo - (���n�萔�����)<BR>
     * ���n�萔�����I�u�W�F�N�g<BR>
     * 
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     * 
     * @@param l_strCostDiv - (���o��敪)<BR>
     * ���o��敪<BR>
     * 
     * @@param l_strSideDiv - (�����敪)<BR>
     * �����敪<BR>
     * 
     * @@throws WEB3BaseException
     * @@roseuid 42B236880144
     */
    protected void createForeignCostMaster(
        WEB3Administrator l_admin, 
        WEB3AdminFeqForeignCostUnit l_offshoreCommissionInfo, 
        String l_strMarketCode, 
        String l_strCostDiv,
        String l_strSideDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createForeignCostMaster(WEB3Administrator,"
            + "WEB3AdminFeqForeignCostUnit, String, String, String )";
        log.entering(STR_METHOD_NAME);
        //�C�O���o��}�X�^�e�[�u���̃��R�[�h��o�^����B
        //�P�j�C�O���o��}�X�^�s�C���X�^���X�𐶐�����B
        ForeignCostParams l_foreignCostParams = new ForeignCostParams();
        //�Q�j�C�O���o��}�X�^�s�C���X�^���X�Ƀv���p�e�B���Z�b�g����B
        //   ��DB�X�V�d�l�Q��
        //�،���ЃR�[�h: �Ǘ���.�،���ЃR�[�h
        l_foreignCostParams.setInstitutionCode(l_admin.getInstitutionCode());
        //�s��R�[�h:����.�s��R�[�h
        l_foreignCostParams.setMarketCode(l_strMarketCode);
        //���o��敪:����.���o��敪
        l_foreignCostParams.setCostDiv(l_strCostDiv);
        //�E�v�J�n�N����:���n�萔�����.�K�p���ԁi���j
        l_foreignCostParams.setAppliStartDate(l_offshoreCommissionInfo.applyStartDate);
        //�E�v�I���N����:���n�萔�����.�K�p���ԁi���j
        //���n�萔�����.�K�p���ԁi���j==null�̏ꍇ�́A"9999/12/31"���Z�b�g
        Date l_datApplyEndDate = null;
        if (null == l_offshoreCommissionInfo.applyEndDate) 
        {
           l_datApplyEndDate = WEB3DateUtility.getDate("9999/12/31", "yyyy/MM/dd");
        } 
        else 
        {
            l_datApplyEndDate = l_offshoreCommissionInfo.applyEndDate;
        }
        l_foreignCostParams.setAppliEndDate(l_datApplyEndDate);
        //��������iFROM�j: ���n�萔�����.������z�i���j
        l_foreignCostParams.setAmountFrom(
            Double.parseDouble(l_offshoreCommissionInfo.tradingAmtFrom));
        //��������iTO�j:���n�萔�����.������z�i���j
        //���n�萔�����.������z�i���j==null�̏ꍇ�́A99999999999.99���Z�b�g
        double l_dblTradingAmtTo = 99999999999.99D;
        if (l_offshoreCommissionInfo.tradingAmtTo != null) 
        {
            l_dblTradingAmtTo = 
                Double.parseDouble(l_offshoreCommissionInfo.tradingAmtTo);
        }

        l_foreignCostParams.setAmountTo(l_dblTradingAmtTo);
        //������:���n�萔�����.������
        l_foreignCostParams.setCommisionRate(
            Double.parseDouble(l_offshoreCommissionInfo.collectRate));
        //�t�����z:���n�萔�����.�t�����z
        l_foreignCostParams.setAddAmount(
            Double.parseDouble(l_offshoreCommissionInfo.additionalAmt));
        
        //�s�ꁁ���`�̏ꍇ�ŏ��o��敪��03 or 04�̏ꍇ
        //�i�����_�ȉ��؏グ�@@�ȊO�͏����_��R�ʂ��l�̌ܓ�
        if ((WEB3MarketCodeDef.HONGKONG.equals(l_strMarketCode))
             && (WEB3FeqCostDivDef.FOREIGN_FEE_EXT1.equals(l_strCostDiv)
             || WEB3FeqCostDivDef.FOREIGN_FEE_EXT2.equals(l_strCostDiv)))
        {
            //����������:0
            l_foreignCostParams.setScale(0);
            //�v�Z���ʊۂߕ���: 2�F�h�؏�h
            l_foreignCostParams.setRoundDiv(WEB3FeqRoundDivDef.CUT_UP);
        }
        else
        {
            //����������:2
            l_foreignCostParams.setScale(2);
            //�v�Z���ʊۂߕ���: 0�F�h�l�̌ܓ��h
            l_foreignCostParams.setRoundDiv(WEB3FeqRoundDivDef.ROUND);
        }
        //�X�V�҃R�[�h: �Ǘ���.�Ǘ��҃R�[�h
        l_foreignCostParams.setLastUpdater(l_admin.getAdministratorCode());
        //�쐬���t: �V�X�e���^�C���X�^���v
        l_foreignCostParams.setCreatedTimestamp(
            GtlUtils.getTradingSystem().getSystemTimestamp());
        //�X�V���t: �V�X�e���^�C���X�^���v
        l_foreignCostParams.setLastUpdatedTimestamp(
            GtlUtils.getTradingSystem().getSystemTimestamp());
        //�����敪: ����.�����敪
        l_foreignCostParams.setSideDiv(l_strSideDiv);

        //�R�jDB�ɓo�^����B
        //   WEB3DataAccessUtility.insertRow()���R�[������B
        //
        //   [����]
        //   l_row�F �C�O���o��}�X�^�s�C���X�^���X
        //   ��insert�Ɏ��s�����ꍇ�́A�u�o�^�f�[�^�s�����i�d���o�^�s�j�v�̋Ɩ��G���[�Ƃ���B
        try
        {
            WEB3DataAccessUtility.insertRow(l_foreignCostParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02183, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02183, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
