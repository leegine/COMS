head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.41.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualConditionsReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ғ����M���@@���������o�^�Ɖ�T�[�r�X����(WEB3AdminMutualConditionsReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/18 �����(���u) �V�K�쐬
Revesion History : 2004/08/20 ���� (���u) ���r���[
Revesion History : 2004/12/09 ������ (���u) �c�Ή�
Revesion History : 2007/04/06 ��іQ (���u) ���f�� 544
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.data.MutualFundProductCategoryParams;
import webbroker3.mf.define.WEB3MutualFrgnMmfDisplayDivDef;
import webbroker3.mf.define.WEBMFSortConditionDivDef;
import webbroker3.mf.message.WEB3AdminMutualConditionsRefInputRequest;
import webbroker3.mf.message.WEB3AdminMutualConditionsRefInputResponse;
import webbroker3.mf.message.WEB3AdminMutualConditionsReferenceRequest;
import webbroker3.mf.message.WEB3AdminMutualConditionsReferenceResponse;
import webbroker3.mf.message.WEB3MutualProductCategoryUnit;
import webbroker3.mf.message.WEB3MutualProductConditionsGroup;
import webbroker3.mf.service.delegate.WEB3AdminMutualConditionsReferenceService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �Ǘ��ғ����M���@@���������o�^�Ɖ�T�[�r�X�����N���X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminMutualConditionsReferenceServiceImpl extends WEB3MutualClientRequestService 
    implements WEB3AdminMutualConditionsReferenceService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualConditionsReferenceServiceImpl.class);

    /**
     * @@roseuid 410653B6035B
     */
    public WEB3AdminMutualConditionsReferenceServiceImpl() 
    {
     
    }
    
    /**
     * �Ǘ��ғ����M���@@���������o�^�Ɖ�����{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̂����ꂩ�̃��\�b�h���R�[������B<BR>
     * <BR>
     * ���ݽ�}�u�i���M�j���������o�^�Ɖ�execute�v�Q��<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40E4C0A200C5
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String l_strMethodName = "execute(WEB3GenRequest l_request)";
        log.entering(l_strMethodName);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL����I");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName
            );
        }
        // ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̂����ꂩ�̃��\�b�h���R�[������B
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminMutualConditionsRefInputRequest)
        {
            // �|input���������o�^�Ɖ�()
            l_response = this.inputProductConditionsRegistRef(
                (WEB3AdminMutualConditionsRefInputRequest)l_request);
            log.exiting(l_strMethodName);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminMutualConditionsReferenceRequest)
        {
            // �|search���������o�^()
            l_response = this.searchProductConditionsRegist(
                (WEB3AdminMutualConditionsReferenceRequest)l_request);
            log.exiting(l_strMethodName);
            return l_response;
        }
        else
        {
            // �p�����[�^�l���s��
            log.debug(l_strMethodName + " �p�����[�^�l���s������I");
            log.exiting(l_strMethodName);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + l_strMethodName);
        } 
    }
    
    /**
     * (input���������o�^�Ɖ�)<BR>
     * �Ǘ��ғ����M���@@���������o�^�Ɖ���͏������s���B <BR>
     * <BR>
     * �V�[�P���X�}�u�i���M�j���������o�^�Ɖ���́v�Q��<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return webbroker3.mf.message.WEB3AdminMutualConditionsRefInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 40E4C1560375
     */
    protected WEB3AdminMutualConditionsRefInputResponse inputProductConditionsRegistRef(
        WEB3AdminMutualConditionsRefInputRequest l_request) throws WEB3BaseException 
    {
        final String l_strMethodName = "inputProductConditionsRegistRef(" 
            + "WEB3AdminMutualConditionsRefInputRequest l_request)";
        log.entering(l_strMethodName);

        //�Q�j�Ǘ��Ҍ����`�F�b�N 
        //�@@�Q�|�P�j�Ǘ��҃I�u�W�F�N�g.getInstanceFrom���O�C�����( )���R�[������B
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        //�@@�Q�|�Q�j�Ǘ��҃I�u�W�F�N�g.validate����( )���R�[������B 
        // [validate����()�Ɏw�肷�����] 
        // �@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.���M�i�����Ǘ��j 
        // is�X�V�F�@@false
        l_admin.validateAuthority(
                WEB3TransactionCategoryDef.ADMIN_MUTUAL_CONDITIONS,
                false);
        
        // �R�j�،���ЃR�[�h�̎擾
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        // --------- Start -------------- �g�����M�����}�l�[�W���̎擾���s��
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
        WEB3MutualFundProductManager l_mfProductManager = 
            (WEB3MutualFundProductManager)l_tradingModule.getProductManager();
        // --------- End -------------- �g�����M�����}�l�[�W���̎擾���s��
        // �S�j���M�����J�e�S���[�ꗗ�̎擾����B
        List l_lisMFProductCategorys = 
            l_mfProductManager.getMutualFundProductCategoryList(l_strInstitutionCode);
        WEB3MutualProductCategoryUnit[] l_mfProductCategoryUnits = null;
        if (l_lisMFProductCategorys != null && l_lisMFProductCategorys.size() > 0)
        {
            // �T�j���M�����J�e�S���[�ꗗ�����X�|���X�ɐݒ肷��`���i�c���[�\���̔z��j�ɕϊ�����B        
           l_mfProductCategoryUnits = 
                l_mfProductManager.createMutualFundProductCategoryList(l_lisMFProductCategorys);        
        }
        // �U�j���X�|���X�̐ݒ�
        WEB3AdminMutualConditionsRefInputResponse l_response = 
            (WEB3AdminMutualConditionsRefInputResponse)l_request.createResponse();
        l_response.categoryList = l_mfProductCategoryUnits;
        return l_response;
    }
    
    /**
     * (search���������o�^)<BR>
     * �Ǘ��ғ����M���@@���������o�^�Ɖ�����s���B <BR>
     * <BR>
     * �V�[�P���X�}�u�i���M�j���������o�^�Ɖ���v�Q��<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return webbroker3.mf.message.WEB3AdminMutualConditionsReferenceResponse
     * @@throws WEB3BaseException
     * @@roseuid 40E4C1620171
     */
    protected WEB3AdminMutualConditionsReferenceResponse searchProductConditionsRegist(
        WEB3AdminMutualConditionsReferenceRequest l_request) throws WEB3BaseException 
    {
        final String l_strMethodName = "searchProductConditionsRegist(" 
            + "WEB3AdminMutualConditionsReferenceRequest l_request)";
        log.entering(l_strMethodName);
        
        //�Q�j���̓`�F�b�N 
        //�@@����.���N�G�X�g�f�[�^.validate()���R�[������B 
        l_request.validate();

        //���M�E�O��MMF�\���敪
        //��null�̏ꍇ�A�u2:�����v�Ƃ���
        if (l_request.mutualFrgnMmfDisplayDiv == null)
        {
            l_request.mutualFrgnMmfDisplayDiv =
                WEB3MutualFrgnMmfDisplayDivDef.BOTH;
        }

        //�R�j�Ǘ��Ҍ����`�F�b�N 
        //�@@�R�|�P�j�Ǘ��҃I�u�W�F�N�g.getInstanceFrom���O�C�����( )���R�[������B
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        //�@@�R�|�Q�j�Ǘ��҃I�u�W�F�N�g.validate����( )���R�[������B 
        // [validate����()�Ɏw�肷�����] 
        // �@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.���M�i�����Ǘ��j 
        // is�X�V�F�@@false
        l_admin.validateAuthority(
                WEB3TransactionCategoryDef.ADMIN_MUTUAL_CONDITIONS,
                false);
        
        //�S�j�،���ЃR�[�h�̎擾  
        //�@@�Ǘ��҃I�u�W�F�N�g.get�،���ЃR�[�h( )���R�[������B
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        //�T�j�����ꗗ�̎擾 
        //�@@�T�|�P�jthis.create��������������()���R�[�����A����������������擾�B
        
        String l_strWhere =
            this.createSearchCondCharacterString(
                l_request.mutualProductCode,
                l_request.mutualAssocProductCode,
                l_request.categoryCode,
                l_request.mutualFrgnMmfDisplayDiv);
        //�@@�T�|�Q�jthis.create���������f�[�^�R���e�i()���R�[�����A���������f�[�^�R���e�i���擾�B
        String[] l_strWhereValues =
            this.createSearchCondDataContainer(
                l_request.mutualProductCode,
                l_request.mutualAssocProductCode,
                l_request.categoryCode,
                l_request.mutualFrgnMmfDisplayDiv);
        //�@@�T�|�R�j���M�g�������}�l�[�W��.get���M�������X�g()���R�[�����A�����ꗗ���擾�B
        // --------- Start -------------- �g�����M�����}�l�[�W���̎擾���s��
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
        WEB3MutualFundProductManager l_mfProductManager = 
            (WEB3MutualFundProductManager)l_tradingModule.getProductManager();
        // --------- End -------------- �g�����M�����}�l�[�W���̎擾���s��
        String l_strSortConditionDivDef = 
            WEBMFSortConditionDivDef.ADMIN_MUTUAL_COND_REF;
        List l_lisMFProducts = 
            l_mfProductManager.getMutualFundProductList(
                    l_strInstitutionCode,
                    l_strWhere,
                    l_strWhereValues,
                    l_strSortConditionDivDef);
        
        //�@@�T�|�S�jget���M�������X�g()�̖߂�l���A�O���̏ꍇ�A��O���X���[����B�i���������G���[�j 
        if (l_lisMFProducts == null || l_lisMFProducts.size() == 0)
        {
            log.debug("���������G���[! for l_strInstitutionCode, l_strWhere, l_strWhereValues = " + 
                l_strInstitutionCode + ", " + l_strWhere + ", " + l_strWhereValues);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00383,
                this.getClass().getName() + "." + l_strMethodName,
                "���������G���[");
        }
        //�U�j�����ꗗ�̌������A���M���������o�^�Ɖ�����ꗗ�s�I�u�W�F�N�g�𐶐����A 
        //�@@�f�[�^��ݒ肷��B 
        List l_lisProductConditions = new Vector();
        for (int i = 0; i < l_lisMFProducts.size(); i++)
        {
            MutualFundProductRow l_mfProductRow = (MutualFundProductRow)l_lisMFProducts.get(i);
            WEB3MutualProductConditionsGroup l_mfProductConditionsGroup = 
                new WEB3MutualProductConditionsGroup();
            l_mfProductConditionsGroup.id = Long.toString(l_mfProductRow.getProductId());
            l_mfProductConditionsGroup.mutualProductCode = l_mfProductRow.getProductCode();
            l_mfProductConditionsGroup.mutualAssocProductCode = l_mfProductRow.getMutualassocProductCode();
            l_mfProductConditionsGroup.mutualProductName = l_mfProductRow.getStandardName();
            l_mfProductConditionsGroup.categoryCode = l_mfProductRow.getCategoryCode();
            l_lisProductConditions.add(l_mfProductConditionsGroup);
        }
        WEB3MutualProductConditionsGroup[] productConditions = 
            new WEB3MutualProductConditionsGroup[l_lisProductConditions.size()];
        l_lisProductConditions.toArray(productConditions);
        //�V�j�y�[�W���O���� 
        // ----------------- ���X�|���X�I�u�W�F�N�g�𐶐���
        WEB3AdminMutualConditionsReferenceResponse l_response = 
            (WEB3AdminMutualConditionsReferenceResponse)l_request.createResponse();
        //�@@�V�|�P�j���X�|���X�̈ȉ��̍��ڂ�ݒ肷��B 
        int l_intRequestPageSize = Integer.parseInt(l_request.pageSize);
        int l_intRequestPageIndex = Integer.parseInt(l_request.pageIndex);
        //�@@�����X�|���X.���y�[�W���������ꗗ�̗v�f�������N�G�X�g.�y�[�W���\���s�� 
        //�@@�@@�@@���]�肪�o��ꍇ�́A�{�P�����l��ݒ�B 
        //�@@�@@�@@�������ꗗ�̗v�f����0(�\���Ώۃf�[�^�Ȃ�)�̏ꍇ�A0���Z�b�g�B 
        int l_intTotalPages = productConditions.length / l_intRequestPageSize;
        if (productConditions.length % l_intRequestPageSize != 0)
        {
            l_intTotalPages = l_intTotalPages + 1;
        }
        l_response.totalPages = l_intTotalPages + "";
        //�@@�����X�|���X.�����R�[�h��:�@@�����ꗗ�̗v�f�� 
        l_response.totalRecords = productConditions.length + "";
        //�@@�����X�|���X.�\���y�[�W�ԍ�(�\�������y�[�W�ڂɂ����邩): 
        //�@@�@@�@@�@@�ȉ��̏����ɍ��v����̂ł���΁A���N�G�X�g.�v���y�[�W�ԍ��B 
        //[�����ꗗ�̗v�f�� > (���N�G�X�g.�y�[�W���\���s���~(���N�G�X�g.�v���y�[�W�ԍ�-1) )] 
        //�@@�@@�@@�@@��L�ȊO�̏ꍇ�́A���X�|���X.���y�[�W�������̂܂ܐݒ�B 
        //�@@�@@�@@���������ʂ�PR�w����̗v���y�[�W�ԍ��ɖ����Ȃ��ꍇ�́A�ŏI�y�[�W�� 
        //�@@�@@�@@�@@�Y������f�[�^�����X�|���X�ɐݒ肷��B 
        if (productConditions.length > (l_intRequestPageSize * l_intRequestPageIndex))
        {
            l_response.pageIndex = l_request.pageIndex;
        }
        else
        {
            l_response.pageIndex = l_intTotalPages + "";
        }
        //�@@�V�|�Q�j�ݒ��A���X�|���X.���y�[�W����0 �̏ꍇ�́A���X�|���X.�����ꗗ�s 
        //�@@(���M���������o�^�Ɖ�����ꗗ�s[ ])��null���Z�b�g����O���X���[����B 
        //
        //�W�j�m�肵�������ꗗ�̂����A���X�|���X�ɐݒ肷�閾�ׂ����߂�B 
        //
        //�@@�@@�W�|�P)�@@(���N�G�X�g.�y�[�W���\���s���~(���X�|���X.�\���y�[�W�ԍ�?1)�����A 
        //�@@�@@�@@�@@�m�肵�������ꗗ�̗v�f���X�L�b�v����B 
        int l_intBeginRecordNumber =  l_intRequestPageSize * (Integer.parseInt(l_response.pageIndex) - 1);
        int l_intEndRecordNumber =  l_intRequestPageSize * Integer.parseInt(l_response.pageIndex);
        if (l_intEndRecordNumber > productConditions.length)
        {
            l_intEndRecordNumber = productConditions.length;
        }
        //�@@�@@�W�|�Q)�@@��L�Ō��肵�������ꗗ�̗v�f�ԍ��` 
        //�@@�@@�@@�@@(�����ꗗ�̗v�f�ԍ��{���N�G�X�g.�y�[�W���\���s��) 
        //�@@�@@�@@�@@�܂łɊY����������ꗗ���A���X�|���X�f�[�^.�����ꗗ�s�Z�b�g����B
        List l_lisProducts = new Vector(); 
        for (int i = l_intBeginRecordNumber; i < l_intEndRecordNumber; i++)
        {
            l_lisProducts.add(productConditions[i]);
        }
        WEB3MutualProductConditionsGroup[] l_products = 
            new WEB3MutualProductConditionsGroup[l_lisProducts.size()];
        l_lisProducts.toArray(l_products);
        //�X�j���X�|���X�̐ݒ� 
        //�@@�����X�|���X.�����ꗗ�s���y�[�W���O������s���Ċm�肳���������ꗗ�̔z�� 
        l_response.productConditions = l_products;
        return l_response;
    }
    
    /**
     * (create��������������)<BR>
     * �������������{����ׂ̌���������������쐬���ĕԋp����B<BR>
     * <BR>
     * �P�j����.�����R�[�h�̔���<BR>
     * �@@�P�|�P�j����.�����R�[�h!=null�̏ꍇ�A�ȉ�����������������ɒǉ�����B<BR>
     * �@@�@@�@@�@@�@@" �����R�[�h like '?%' "<BR>
     * <BR>
     * �Q�j����.���M��������R�[�h�̔���<BR>
     * �@@����.���M��������R�[�h!=null�̏ꍇ�A�ȉ������{<BR>
     *  �Q�|�P�j��������������!=null�̏ꍇ�A�ȉ�����������������ɒǉ�����B<BR>
     *�@@�@@      " and  ���M��������R�[�h like '?%'"<BR>
     * <BR>
     * �@@�Q�|�Q�j�ȉ�����������������ɒǉ�����B<BR>
     *     �@@�@@" ���M��������R�[�h like '?%' "<BR>
     * <BR>
     * �R�j����.���M�����J�e�S���[�R�[�h�̔���<BR>
     *    ����.���M�����J�e�S���[�R�[�h!=��null�̏ꍇ�A�ȉ������{<BR>
     * �@@�R�|�P�j��������������!=null�̏ꍇ<BR>
     *         " and ���M�����J�e�S���[�R�[�h "<BR>
     * <BR>
     * �@@�R�|�Q�j�ȉ������{�B<BR>
     *         �g�����M�����}�l�[�W��.get���ʓ��M�����J�e�S���[���X�g()<BR>
     *         [get���ʓ��M�����J�e�S���[���X�g]�ɓn������<BR>
     *         �،���ЃR�[�h���Ǘ���.getInstanceFrom���O�C�����( ).get�،���ЃR�[�h()<BR>
     *         �J�e�S���[�R�[�h������.���M�����J�e�S���[�R�[�h<BR>
     * <BR>
     *  �R�|�R�j"in (?"�̌��ɁA<BR>
     * �@@�@@�@@�@@�i����.���M�����J�e�S���[�R�[�h�ƂR�|�Q�j�̖߂�l�̃J�e�S���[�R�[�h�̑S�����j��(*)��<BR>
     *�@@       ",?"��ǉ����A�Ō��")"��ǉ�����B<BR>
     * �@@�@@�@@�i���F�Ō�A","���P�폜�������")"����鎖<BR>
     * <BR>
     * �S�j����.���M�E�O��MMF�\���敪�̔���<BR>
     * <BR>
     * �S�|�P�j����.���M�E�O��MMF�\���敪��"���M�̂�"�̏ꍇ<BR>
     * <BR>
     * �@@�S�|�P�|�P�j��������������!=null�̏ꍇ�A�ȉ�����������������ɒǉ�����B<BR>
     * �@@�@@�@@�@@�@@�@@" and ���M�^�C�v<>?"<BR>
     * <BR>
     * �@@�S�|�P�|�Q�j��������������=null�̏ꍇ �A�ȉ�����������������ɒǉ�����B<BR>
     * �@@�@@�@@�@@�@@�@@" ���M�^�C�v<>?"<BR>
     * <BR>
     * �S�|�Q�j����.���M�E�O��MMF�\���敪��"�O��MMF�̂�"�̏ꍇ<BR>
     * <BR>
     * �@@�S�|�Q�|�P�j��������������!=null�̏ꍇ�A�ȉ�����������������ɒǉ�����B<BR>
     * �@@�@@�@@�@@�@@�@@" and ���M�^�C�v=?"<BR>
     * <BR>
     * �@@�S�|�Q�|�Q�j��������������=null�̏ꍇ �A�ȉ�����������������ɒǉ�����B<BR>
     * �@@�@@�@@�@@�@@�@@" ���M�^�C�v=?"<BR>
     * <BR>
     * <BR>
     * �T�j������������������^�[������B<BR>
     * <BR>
     * EX�j���ɁA01�Ƃ����J�e�S���[��02�A03�Ƃ����q�J�e�S���[�������Ă����ꍇ�A<BR>
     *   �R�|�R�j�́hin( ?, ?, ? )�h�ƂȂ�B<BR>
     *   03�������œn����A03���q�J�e�S���[�������Ă��Ȃ������ꍇ�A<BR>
     *   �R�|�R�j�́hin ( ? )�h�ƂȂ�B<BR>
     * <BR>
     * (*) ����.���M�����J�e�S���[�R�[�h==null�̏ꍇ�A���Ɋ܂߂Ȃ��B<BR>
     * @@param l_strProductCode - �����R�[�h<BR>
     * @@param l_strMutualAssocProductCode - ���M��������R�[�h<BR>
     * @@param l_strMutualProductCategoryCode - ���M�����J�e�S���[�R�[�h<BR>
     * @@param l_strmutualFrgnMmfDisplayDiv - (���M�E�O��MMF�\���敪)<BR>
     * ���M�E�O��MMF�\���敪<BR>
     * <BR>
     * �\���Ώۂ̖������A���M��O��MMF�Ő؂�ւ��邽�߂̋敪<BR>
     * <BR>
     * 0:���M�̂�<BR>
     * 1:�O��MMF�̂�<BR>
     * 2:����<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 40E508E902C1
     */
    protected String createSearchCondCharacterString(
        String l_strProductCode,
        String l_strMutualAssocProductCode,
        String l_strMutualProductCategoryCode,
        String l_strmutualFrgnMmfDisplayDiv)
        throws WEB3BaseException
    {
        final String l_strMethodName = "createSearchCondCharacterString("
            + "String l_strProductCode,"
            + "String l_strMutualAssocProductCode,"
            + "String l_strMutualProductCategoryCode,"
            + "String l_strmutualFrgnMmfDisplayDiv)";
        log.entering(l_strMethodName);
        
        String l_strWhere = "";
        
        //�P�j����.�����R�[�h�̔���
        // �P�|�P�j����.�����R�[�h!=null�̏ꍇ�A�ȉ�����������������ɒǉ�����B
        //�@@   " �����R�[�h like '?%' 
        if (l_strProductCode != null)
        {
            l_strWhere = l_strWhere + " product_code like ? ||'%' ";
        }
        
        //�Q�j����.���M��������R�[�h�̔���
        // ����.���M��������R�[�h!=null�̏ꍇ�A�ȉ������{
        if (l_strMutualAssocProductCode != null)
        {    
            //�Q�|�P�j��������������!=null�̏ꍇ�A�ȉ�����������������ɒǉ�����B
            //    " and  ���M��������R�[�h like '?%'"   
            if (!"".equals(l_strWhere))
            {
                l_strWhere = l_strWhere + " and mutualassoc_product_code like ? ||'%' ";
            }
            //�Q�|�Q�j�ȉ�����������������ɒǉ�����B
            //    " ���M��������R�[�h like '?%' "
            else
            {
                l_strWhere = l_strWhere + " mutualassoc_product_code like ? ||'%' ";
            }            
        }
        
        // �g�����M�����}�l�[�W���̎擾���s��
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundProductManager l_mfProductManager =
            (WEB3MutualFundProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();
        WEB3Administrator l_admin;
        String l_institutionCode;
        l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        l_institutionCode = l_admin.getInstitutionCode();
        List l_lisLowMutualFundProductList;
        //�R�j����.���M�����J�e�S���[�R�[�h�̔��� 
        //   �g�����M�����}�l�[�W��.get���M�����J�e�S���[���X�g()�̖߂�l���O���̏ꍇ�A�ȉ������{ 
        List l_lisCategoryList = 
            l_mfProductManager.getMutualFundProductCategoryList(l_institutionCode);
        if (l_lisCategoryList != null && l_lisCategoryList.size() > 0)
        {
            if (WEB3StringTypeUtility.isNotEmpty(l_strMutualProductCategoryCode))
            {
                //�R�|�P�j��������������!=null�̏ꍇ " and ���M�����J�e�S���[�R�[�h " 
                if (!"".equals(l_strWhere))
                {
                    l_strWhere = l_strWhere + " and category_code ";         
                }
                else
                {
                    l_strWhere = l_strWhere + " category_code ";
                }

                try
                {
                    //�R�|�Q�j�ȉ������{�B 
                    //�g�����M�����}�l�[�W��.get���ʓ��M�����J�e�S���[���X�g() 
                    //[get���ʓ��M�����J�e�S���[���X�g]�ɓn������ 
                    //�،���ЃR�[�h���Ǘ���.getInstanceFrom���O�C�����( ).get�،���ЃR�[�h() 
                    //�J�e�S���[�R�[�h������.���M�����J�e�S���[�R�[�h 
                    l_lisLowMutualFundProductList =
                        l_mfProductManager.getLowMutualFundProductCategoryList(
                        WEB3Administrator.getInstanceFromLoginInfo().getInstitutionCode(),
                        l_strMutualProductCategoryCode);
                } 
                catch(WEB3BaseException l_be)
                {
                    log.error("���������G���[! for l_strInstitutionCode, l_strMutualProductCategoryCode = " +
                    l_institutionCode + ", " + l_strMutualProductCategoryCode);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00383,
                        this.getClass().getName() + "." + l_strMethodName,
                        l_be.getMessage(),
                        l_be);
                }
            
                //�R�|�R�j"in (?"�̌��ɁA 
                //�i����.���M�����J�e�S���[�R�[�h�ƂR�|�Q�j�̖߂�l�̃J�e�S���[�R�[�h�̑S�����j��(*)�� 
                //",?"��ǉ����A�Ō��")"��ǉ�����B 
                if (l_lisLowMutualFundProductList != null && l_lisLowMutualFundProductList.size() >= 0)
                {   
                    l_strWhere = l_strWhere + "in(";
                    if (l_strMutualProductCategoryCode != null)
                    {
                        l_strWhere = l_strWhere + "?";
                    }
                    for (int i = 0; i < l_lisLowMutualFundProductList.size(); i++)
                    {
                        l_strWhere = l_strWhere + ",?";
                    }
                    l_strWhere = l_strWhere + ")";
                }
            }
        }

        //�S�j����.���M�E�O��MMF�\���敪�̔���
        //�S�|�P�j����.���M�E�O��MMF�\���敪��"���M�̂�"�̏ꍇ
        if (WEB3MutualFrgnMmfDisplayDivDef.MUTUAL_FUND.equals(
            l_strmutualFrgnMmfDisplayDiv))
        {
            //�S�|�P�|�P�j��������������!=null�̏ꍇ�A�ȉ�����������������ɒǉ�����B
            //" and ���M�^�C�v<>?"
            if (!"".equals(l_strWhere))
            {
                l_strWhere = l_strWhere + " and fund_type <> ? ";
            }
            //�S�|�P�|�Q�j��������������=null�̏ꍇ �A�ȉ�����������������ɒǉ�����
            // " ���M�^�C�v<>?"
            else
            {
                l_strWhere = l_strWhere + " fund_type <> ? ";
            }
        }
        //�S�|�Q�j����.���M�E�O��MMF�\���敪��"�O��MMF�̂�"�̏ꍇ
        if (WEB3MutualFrgnMmfDisplayDivDef.FRGN_MMF.equals(
            l_strmutualFrgnMmfDisplayDiv))
        {
            //�S�|�Q�|�P�j��������������!=null�̏ꍇ�A�ȉ�����������������ɒǉ�����
            // " and ���M�^�C�v=?"
            if (!"".equals(l_strWhere))
            {
                l_strWhere = l_strWhere + " and fund_type = ? ";
            }
            //�S�|�Q�|�Q�j��������������=null�̏ꍇ �A�ȉ�����������������ɒǉ�����
            // " ���M�^�C�v=?"
            else
            {
                l_strWhere = l_strWhere + " fund_type = ? ";
            }
        }

        //�S�j������������������^�[������B
        log.exiting(l_strMethodName);
        return l_strWhere;
    }
    
    /**
     * (create���������f�[�^�R���e�i)<BR>
     * �������������{����ׂ̌��������f�[�^�R���e�i���쐬���ĕԋp����B<BR>
     * <BR>
     * �P�j����.�����R�[�h�̔���<BR>
     * �@@�P�|�P�j����.�����R�[�h��null�ȊO�̏ꍇ�A�ȉ������������f�[�^�R���e�i�ɒǉ�����B<BR>
     * �@@�@@�@@�@@�@@����.�����R�[�h<BR>
     * <BR>
     * �Q�j����.���M��������R�[�h�̔���<BR>
     * �@@�Q�|�P�j����.���M��������R�[�h��null�ȊO�̏ꍇ�A<BR>
     * �ȉ������������f�[�^�R���e�i�ɒǉ�����B<BR>
     * �@@�@@�@@�@@�@@���M��������R�[�h<BR>
     * <BR>
     * �R�j����.���M�����J�e�S���[�R�[�h�̔���<BR>
     * �@@�g�����M�����}�l�[�W��.get���M�����J�e�S���[���X�g()�̖߂�l���O���̏ꍇ�A�ȉ������{<BR>
     * �R�|�P�j����.���M�����J�e�S���[�R�[�h�����������f�[�^�R���e�i�ɒǉ�����B<BR>
     * �R�|�Q�j�ȉ������{����B<BR>
     *    �g�����M�����}�l�[�W��.get���ʓ��M�����J�e�S���[���X�g()<BR>
     *    [get���ʓ��M�����J�e�S���[���X�g]�ɓn������<BR>
     *    �،���ЃR�[�h���Ǘ���.getInstanceFrom���O�C�����( ).get�،���ЃR�[�h()<BR>
     *    �J�e�S���[�R�[�h������.���M�����J�e�S���[�R�[�h<BR>
     * <BR>
     * �R�|�R�j����.���M�����J�e�S���[�R�[�h!=null�̏ꍇ<BR>
     * �@@�@@�@@�R-�Q�j�̖߂�l�̐擪�ɁA����.���M�����J�e�S���[�R�[�h��ǉ�����B<BR>
     * <BR>
     * �R�|�S�j �R�|�R�j�̖߂�l�̌������A�ȉ������������f�[�^�R���e�i�ɒǉ�����B<BR>
     * <BR>
     * �S�j����.���M�E�O��MMF�\���敪�̔���<BR>
     * �@@�@@�S�|�P�j����.���M�E�O��MMF�\���敪��"���M�̂�"�܂��� "�O��MMF�̂�"�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�ȉ������������f�[�^�R���e�i�ɒǉ�����B<BR>
     * �@@�@@�@@�@@�@@�@@MutualFundTypeEnum.�O��MMF<BR>
     * <BR>
     * �T�j���������f�[�^�R���e�i�����^�[������B<BR>
     * <BR>
     * @@param l_strProductCode - �����R�[�h<BR>
     * @@param l_strMutualAssocProductCode - ���M��������R�[�h<BR>
     * @@param l_strMutualProductCategoryCode - ���M�����J�e�S���[�R�[�h<BR>
     * @@param l_strmutualFrgnMmfDisplayDiv - (���M�E�O��MMF�\���敪)<BR>
     * ���M�E�O��MMF�\���敪<BR>
     * <BR>
     * �\���Ώۂ̖������A���M��O��MMF�Ő؂�ւ��邽�߂̋敪<BR>
     * <BR>
     * 0:���M�̂�<BR>
     * 1:�O��MMF�̂�<BR>
     * 2:����<BR>
     * <BR>
     * ��null�̏ꍇ�A�u2:�����v�Ƃ���
     * <BR>
     * @@return String[ ]
     * @@throws WEB3BaseException
     * @@roseuid 40E508F2009E
     */
    protected String[ ] createSearchCondDataContainer(
        String l_strProductCode,
        String l_strMutualAssocProductCode,
        String l_strMutualProductCategoryCode,
        String l_strmutualFrgnMmfDisplayDiv)
        throws WEB3BaseException
    {
        final String l_strMethodName = "createSearchCondDataContainer("
            + "String l_strProductCode,"
            + "String l_strMutualAssocProductCode,"
            + "String l_strMutualProductCategoryCode,"
            + "String l_strmutualFrgnMmfDisplayDiv)";
        log.entering(l_strMethodName);
        List l_lisWhereValue = new Vector();
        //�P�j����.�����R�[�h�̔��� 
        //�@@�P�|�P�j����.�����R�[�h��null�ȊO�̏ꍇ�A�ȉ������������f�[�^�R���e�i�ɒǉ�����B 
        //�@@�@@�@@�@@�@@����.�����R�[�h 
        if (l_strProductCode != null)
        {
            l_lisWhereValue.add(l_strProductCode);
        }
        //�Q�j����.���M��������R�[�h�̔��� 
        //�@@�Q�|�P�j����.���M��������R�[�h��null�ȊO�̏ꍇ�A�ȉ������������f�[�^�R���e�i�ɒǉ�����B 
        //�@@�@@�@@�@@�@@���M��������R�[�h 
        if (l_strMutualAssocProductCode != null)
        {
            l_lisWhereValue.add(l_strMutualAssocProductCode);
        }
        
        // �g�����M�����}�l�[�W���̎擾���s��
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundProductManager l_mfProductManager =
            (WEB3MutualFundProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();

        WEB3Administrator l_admin;
        String l_institutionCode;
        l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        l_institutionCode = l_admin.getInstitutionCode();
        //�R�j����.���M�����J�e�S���[�R�[�h�̔��� 
        //�g�����M�����}�l�[�W��.get���M�����J�e�S���[���X�g()�̖߂�l���O���̏ꍇ�A�ȉ������{ 
        List l_lisCategoryList = 
            l_mfProductManager.getMutualFundProductCategoryList(l_institutionCode);
        if (l_lisCategoryList != null && l_lisCategoryList.size() > 0)
        {
            if (l_strMutualProductCategoryCode != null)
            {
                //�R�|�P�j����.���M�����J�e�S���[�R�[�h�����������f�[�^�R���e�i�ɒǉ�����B 
                //�R�|�Q�j�ȉ������{����B 
                // �g�����M�����}�l�[�W��.get���ʓ��M�����J�e�S���[���X�g() 
                // [get���ʓ��M�����J�e�S���[���X�g]�ɓn������ 
                //�،���ЃR�[�h���Ǘ���.getInstanceFrom���O�C�����( ).get�،���ЃR�[�h() 
                //�J�e�S���[�R�[�h������.���M�����J�e�S���[�R�[�h 
                List l_lisLowMutualFundProductList = null;    
                try
                {
                    // �g�����M�����}�l�[�W��.get���ʓ��M�����J�e�S���[���X�g()
                    l_lisLowMutualFundProductList =
                        l_mfProductManager.getLowMutualFundProductCategoryList(
                        WEB3Administrator.getInstanceFromLoginInfo().getInstitutionCode(),
                        l_strMutualProductCategoryCode);
                } 
                catch(WEB3BaseException l_be)
                {
                    log.error("���������G���[! for l_strInstitutionCode, l_strMutualProductCategoryCode = " +
                    l_institutionCode + ", " + l_strMutualProductCategoryCode);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00383,
                        this.getClass().getName() + "." + l_strMethodName,
                        l_be.getMessage(),
                        l_be);
                }    
                //�R�|�R�j����.���M�����J�e�S���[�R�[�h!=null�̏ꍇ 
                    //�R-�Q�j�̖߂�l�̐擪�ɁA����.���M�����J�e�S���[�R�[�h��ǉ�����B 
                l_lisWhereValue.add(l_strMutualProductCategoryCode);
                //�R�|�S�j �R�|�R�j�̖߂�l�̌������A�ȉ������������f�[�^�R���e�i�ɒǉ�����B 
                if (l_lisLowMutualFundProductList != null && l_lisLowMutualFundProductList.size() != 0)
                {
                    for (int i = 0; i < l_lisLowMutualFundProductList.size(); i++)
                    {
                        l_lisWhereValue.add(
                            ((MutualFundProductCategoryParams)
                                l_lisLowMutualFundProductList.get(i)).getCategoryCode());
                    }
                }
            }
        }

        //�S�j����.���M�E�O��MMF�\���敪�̔���
        //�S�|�P�j����.���M�E�O��MMF�\���敪��"���M�̂�"�܂��� "�O��MMF�̂�"�̏ꍇ
        //�ȉ������������f�[�^�R���e�i�ɒǉ�����B
        if (WEB3MutualFrgnMmfDisplayDivDef.MUTUAL_FUND.equals(l_strmutualFrgnMmfDisplayDiv)
            || WEB3MutualFrgnMmfDisplayDivDef.FRGN_MMF.equals(l_strmutualFrgnMmfDisplayDiv))
        {
            //MutualFundTypeEnum.�O��MMF
            l_lisWhereValue.add(MutualFundTypeEnum.FOREIGN_MMF.intValue() + "");
        }

        //�S�j���������f�[�^�R���e�i�����^�[������B
        String[] l_strWhereValues = new String[l_lisWhereValue.size()];
        l_lisWhereValue.toArray(l_strWhereValues);
        log.exiting(l_strMethodName);
        return l_strWhereValues;
    }
}
@
