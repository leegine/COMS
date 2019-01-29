head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.41.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualFrgncalServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M���C�O�s��J�����_�[�o�^�T�[�r�X�@@����(WEB3AdminMutualFrgncalServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/15 �����(���u) �V�K�쐬
                   2004/08/20 ���� (���u) ���r���[
                   2004/12/07 ������ (���u) �c�Ή�
Revesion History : 2009/01/23 ���u�� (���u) �d�l�ύX���f��No.638,640
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3SystemHandlingDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.mf.WEB3AdminMutualFrgncal;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.data.MutualFundFrgncalDao;
import webbroker3.mf.data.MutualFundFrgncalPK;
import webbroker3.mf.data.MutualFundFrgncalParams;
import webbroker3.mf.data.MutualFundFrgncalRow;
import webbroker3.mf.define.WEB3MFBizDateTypeDef;
import webbroker3.mf.define.WEBMFSortConditionDivDef;
import webbroker3.mf.message.WEB3AdminMutualFrgncalCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualFrgncalCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualFrgncalInputRequest;
import webbroker3.mf.message.WEB3AdminMutualFrgncalInputResponse;
import webbroker3.mf.message.WEB3AdminMutualFrgncalReferenceRequest;
import webbroker3.mf.message.WEB3AdminMutualFrgncalReferenceResponse;
import webbroker3.mf.message.WEB3MutualBizDateUnit;
import webbroker3.mf.message.WEB3MutualProductCodeNameUnit;
import webbroker3.mf.service.delegate.WEB3AdminMutualFrgncalService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * �����M���C�O�s��J�����_�[�o�^�T�[�r�X�@@�����N���X
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminMutualFrgncalServiceImpl extends WEB3MutualClientRequestService implements WEB3AdminMutualFrgncalService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualFrgncalServiceImpl.class);
    
    /**
     * @@roseuid 410653B702EE
     */
    public WEB3AdminMutualFrgncalServiceImpl() 
    {
     
    }
    
    /**
     * �����M���C�O�s��J�����_�[�o�^�����{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̂����ꂩ�̃��\�b�h���R�[������B<BR>
     * �|input�C�O�s��J�����_�[( )<BR>
     * �|search�C�O�s��J�����_�[( )<BR>
     * �|submit�C�O�s��J�����_�[( )<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40D80D5A02CC
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String l_strMethodName = "execute(WEB3GenRequest l_request)";
        log.entering(l_strMethodName);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL�I");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName
            );
        }
        // ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̂����ꂩ�̃��\�b�h���R�[������B
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminMutualFrgncalInputRequest)
        {
            // �|input�C�O�s��J�����_�[( )
            l_response = this.inputFrgncal(
                (WEB3AdminMutualFrgncalInputRequest)l_request);
            log.exiting(l_strMethodName);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminMutualFrgncalReferenceRequest)
        {
            // �|search�C�O�s��J�����_�[( )
            l_response = this.searchFrgncal(
                (WEB3AdminMutualFrgncalReferenceRequest)l_request);
            log.exiting(l_strMethodName);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminMutualFrgncalCompleteRequest)
        {
            // �|submit�C�O�s��J�����_�[( )
            l_response = this.submitFrgncal(
                (WEB3AdminMutualFrgncalCompleteRequest)l_request);
            log.exiting(l_strMethodName);
            return l_response;
        }
        else
        {
            // �p�����[�^�l���s��
            log.debug(l_strMethodName + " �p�����[�^�l���s���I");
            log.exiting(l_strMethodName);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + l_strMethodName);
        }       
    }
    
    /**
     * (input�C�O�s��J�����_�[)<BR>
     * �����M���C�O�s��J�����_�[�o�^���͏������s���B <BR>
     * <BR>
     * �V�[�P���X�}�u�i���M�j�C�O�s��J�����_�[���́v�Q��<BR>
     * --------------------------------------------------<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.mf.message.WEB3AdminMutualFrgncalInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 40D8104E0230
     */
    protected WEB3AdminMutualFrgncalInputResponse inputFrgncal(
        WEB3AdminMutualFrgncalInputRequest l_request) 
        throws WEB3BaseException 
    {
        final String l_strMethodName = "inputFrgncal(WEB3AdminMutualFrgncalInputRequest l_request)";
        log.entering(l_strMethodName);
        
        // 1.2�j�Ǘ��҃I�u�W�F�N�g�̎擾
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        // 1.3�j�Ǘ��҂̌����`�F�b�N 
        // [validate����()�Ɏw�肷�����] 
        // �@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.���M�i�J�����_�[�Ǘ��j 
        // is�X�V�F�@@false
        l_admin.validateAuthority(
                WEB3TransactionCategoryDef.ADMIN_MUTUAL_FRGNCAL,
                false);
        
        // 1.4�j�،���ЃI�u�W�F�N�g�̎擾
		Institution l_institution = l_admin.getInstitution();
		// -�،���ЃR�[�h�̎擾
		String l_strInstitutionCode = l_institution.getInstitutionCode();

        //���������̍쐬<*1>
        //<*1>
        //1-1) ���������f�[�^�R���e�i�iString�̔z��j�Ɉȉ���ݒ肷��B
        //1�FWEBBROKER�V�Ŏ�舵��
        //1-2) ��������������ɁA"�V�X�e���戵�敪 = ?"
        String[] l_strSearchCondDataContainers =
            new String[]{WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN};
        String l_strSearchCondCharacterString = " system_handling_div = ? ";

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
        WEB3MutualFundProductManager l_mfProductManager =
            (WEB3MutualFundProductManager)l_tradingModule.getProductManager();

        //get���M�������X�g
        //�A�C�e���̒�`
        //���M�������X�g���擾����
        //����:
        //�،���ЃR�[�h�Fget�،���Ёi�j�DgetInstitutionCode()�̖߂�l
        //��������������F<*1>�ō쐬������������������
        //���������f�[�^�R���e�i�F<*1>�ō쐬�������������f�[�^�R���e�i
        //�\�[�g�����敪�F"�Ǘ��ғ��M���������o�^�Ɖ�"
        List l_lisMutualFundProductList =
            l_mfProductManager.getMutualFundProductList(
                l_strInstitutionCode,
                l_strSearchCondCharacterString,
                l_strSearchCondDataContainers,
                WEBMFSortConditionDivDef.ADMIN_MUTUAL_COND_REF);

		//<�J��Ԃ�����> get���M�������X�g( )�̖߂�l�̌������A�ȉ����J��Ԃ�
        int l_intMutualFundProductListSize = l_lisMutualFundProductList.size();
        List l_lisProductCodeNameUnits = new Vector();
        for (int i = 0; i < l_intMutualFundProductListSize; i++)
        {
          	// 1.6.1�j���M�����R�[�h����()
			WEB3MutualProductCodeNameUnit l_mfProductCodeNameUnit = 
				new WEB3MutualProductCodeNameUnit();

           	//���v���p�e�B�E�Z�b�g��
            //�����������M�����R�[�h���̃I�u�W�F�N�g�Ɉȉ����Z�b�g����B
            //�����R�[�h=get���M�������X�g( )�̖߂�l[n].getProductCode( )�̖߂�l
            //������=get���M�������X�g( )�̖߂�l[n].get������( )�̖߂�l
            //���t�����敪�ꗗ=null
           	l_mfProductCodeNameUnit.mutualProductCode =
                ((MutualFundProductRow)l_lisMutualFundProductList.get(i)).getProductCode();
           	l_mfProductCodeNameUnit.mutualProductName =
                ((MutualFundProductRow)l_lisMutualFundProductList.get(i)).getStandardName();
           	l_mfProductCodeNameUnit.taxTypeList = null;

            // 1.6.4�j�����ꗗ�p�z��ɒǉ�
            l_lisProductCodeNameUnits.add(l_mfProductCodeNameUnit);
        }
        WEB3MutualProductCodeNameUnit[] l_mfProductCodeNameUnits = 
            new WEB3MutualProductCodeNameUnit[l_lisProductCodeNameUnits.size()];
        l_lisProductCodeNameUnits.toArray(l_mfProductCodeNameUnits);

        //�����N���̍쐬
        //���ݓ��t �̓����� ������"YYYYMM"�`���Ŏ擾���Ĕz�񉻂���B
        //���ݓ��t��GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l
        List l_lisTimestamps = new Vector();
        Date l_datSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
        Date l_datNextMonth = WEB3DateUtility.addMonth(l_datSystemTimestamp, 1);
        l_lisTimestamps.add(
            WEB3DateUtility.formatDate(
                l_datSystemTimestamp, WEB3GentradeTimeDef.DATE_FORMAT_YM));
        l_lisTimestamps.add(
            WEB3DateUtility.formatDate(
                l_datNextMonth, WEB3GentradeTimeDef.DATE_FORMAT_YM));
        String[] l_strTimestamps = new String[l_lisTimestamps.size()];
        l_lisTimestamps.toArray(l_strTimestamps);

        //create���X�|���X( )
        //���X�|���X�̐ݒ�
        //�ȉ����Z�b�g����B
        //�E���X�|���X�f�[�^�N���X.���������ꗗ���쐬���������ꗗ�̔z��
        //�E���X�|���X�f�[�^�N���X.�����N���ꗗ���쐬���������N���̔z��
        WEB3AdminMutualFrgncalInputResponse l_response = 
            (WEB3AdminMutualFrgncalInputResponse)l_request.createResponse();
        l_response.mutualProductCodeNames = l_mfProductCodeNameUnits;
        l_response.searchYMList = l_strTimestamps;

        log.exiting(l_strMethodName);
        return l_response;
    }
    
    /**
     * (search�C�O�s��J�����_�[)<BR>
     * �����M���C�O�s��J�����_�[�o�^�Ɖ�����s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i���M�j�C�O�s��J�����_�[�Ɖ�v�Q��<BR>
     * --------------------------------------------------<BR>
     * �P�j�Ǘ��Ҍ����`�F�b�N<BR>
     * �@@�ڍז���<BR>
     * <BR>
     * �Q�j���M�����I�u�W�F�N�g�̎擾<BR>
     * �@@���M�����I�u�W�F�N�g���擾�ł��Ȃ������ꍇ�A��O���X���[����B<BR>
     * �@@�i�w������G���[�j<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00334 <BR>
     * <BR>
     * �R�j�C�O�s��J�����_�[�e�[�u������<BR>
     * �@@�R�|�P�j���������쐬<BR>
     * �@@�@@�@@�@@�����������f�[�^�R���e�i�Ƃ��āA�ȉ��̒l��z�񉻂���B<BR>
     * �@@�@@�@@�@@����������������Ƃ��Ĉȉ��̕������ݒ肷��B<BR>
     * <BR>
     * �@@�R�|�Q�j�C�O�s��J�����_�[.get�x���ꗗ���R�[�����āA<BR>
     * �w������ɍ��v����x���̈ꗗ���擾����B<BR>
     * <BR>
     * �S�jget�x���ꗗ( )�̖߂�l�̌������A�ȉ����J��Ԃ��B<BR>
     * �@@�S�|�P�j�C�O�s��J�����_�[.�x����z�񉻂���B<BR>
     * <BR>
     * �T�j���M�C�O�s��J�����_�[�o�^�Ɖ�N�G�X�g.create���X�|���X()���R�[������<BR>
     * ���X�|���X�f�[�^�𐶐����A�ȉ���ݒ肷��B<BR>
     * �@@�����X�|���X�f�[�^.�J�����_�[�x���ꗗ���쐬�����x���̔z��<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i���M�j�C�O�s��J�����_�[�Ɖ�v): 7 get���M����(Institution, String)<BR>
     * ���M�������擾�ł��Ȃ������ꍇ�A��O�w�����w��G���[�x���X���[�B<BR>
     *  class: WEB3BusinessLayerException<BR>
     *  tag:   BUSINESS_ERROR_00334 <BR>
     * [get���M�����̈���]<BR>
     * �@@�@@�Ǘ��҃I�u�W�F�N�g.get�،���ЃR�[�h( )�̖߂�l<BR>
     * �@@�@@���N�G�X�g�f�[�^.�����R�[�h<BR>
     * =========================================================<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.mf.message.WEB3AdminMutualFrgncalReferenceResponse
     * @@throws WEB3BaseException
     * @@roseuid 40D8104E024F
     */
    protected WEB3AdminMutualFrgncalReferenceResponse searchFrgncal(
        WEB3AdminMutualFrgncalReferenceRequest l_request) 
        throws WEB3BaseException 
    {
        final String l_strMethodName = "searchFrgncal(WEB3AdminMutualFrgncalReferenceRequest l_request)";
        log.entering(l_strMethodName);

		// 1.1�jvalidate()
		l_request.validate();
		
        // 1.3�j�Ǘ��҃I�u�W�F�N�g�̎擾
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        // 1.4�j�Ǘ��҂̌����`�F�b�N
        // [validate����()�Ɏw�肷�����] 
        // �@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.���M�i�J�����_�[�Ǘ��j 
        // is�X�V�F�@@false
        l_admin.validateAuthority(
                WEB3TransactionCategoryDef.ADMIN_MUTUAL_FRGNCAL,
                false);
        
        // 1.5) �،���ЃI�u�W�F�N�g�̎擾
		Institution l_institution = l_admin.getInstitution();
		// -�،���ЃR�[�h�̎擾
        String l_strInstitutionCode = l_institution.getInstitutionCode();

        // -�g�����M�����}�l�[�W���̎擾���s��
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
        WEB3MutualFundProductManager l_mfProductManager = 
            (WEB3MutualFundProductManager)l_tradingModule.getProductManager();

        try
        {
            // 1.6�j���M�����I�u�W�F�N�g�̎擾
            l_mfProductManager.getMutualFundProduct(
            	l_institution, l_request.mutualProductCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�Y�����铊�M�����I�u�W�F�N�g������܂���!"); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00334,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        // 1.7�j�C�O�s��J�����_�[�e�[�u�����������̍쐬
        String[] l_strWhereValues = {l_request.mutualProductCode,l_request.searchYM};
        String l_strWhere = " product_code = ? and to_char(holiday, 'YYYYMM') = ? ";
        
		// 1.8�jget�x���ꗗ( )
        List l_lisHolidays = 
            WEB3AdminMutualFrgncal.getHolidayList(l_strInstitutionCode, l_strWhere, l_strWhereValues);
            
        // -get�x���ꗗ( )�̖߂�l�̌������A�ȉ����J��Ԃ��B
        //  �C�O�s��J�����_�[.�x����z�񉻂���B
        Date[] l_dteHolidays = null;
        List l_lisTempHolidays = new Vector();
        if (l_lisHolidays != null){
            //l_dteHolidays = new Date[l_lisHolidays.size()];
            for (int i = 0; i < l_lisHolidays.size(); i ++)
            {
                MutualFundFrgncalRow l_mfFrgncalRow = 
                    (MutualFundFrgncalRow)l_lisHolidays.get(i);
                if (i != 0)
                {
                    if (WEB3DateUtility.compareToDay(l_mfFrgncalRow.getHoliday(), 
                        ((MutualFundFrgncalRow)l_lisHolidays.get(i-1)).getHoliday()) == 0)
                    {
                        continue;
                    }
                }
                l_lisTempHolidays.add(l_mfFrgncalRow.getHoliday());
            }
            l_dteHolidays = new Date[l_lisTempHolidays.size()];
            l_lisTempHolidays.toArray(l_dteHolidays);
        }
        // 1.9) create���X�|���X()
        WEB3AdminMutualFrgncalReferenceResponse l_response = 
            (WEB3AdminMutualFrgncalReferenceResponse)l_request.createResponse();
            
        // 1.10) ���X�|���X�f�[�^.�J�����_�[�x���ꗗ���쐬�����x���̔z��
        l_response.notBizDateList = l_dteHolidays;
        log.exiting(l_strMethodName);
        return l_response;
    }
    
    /**
     * (submit�C�O�s��J�����_�[)<BR>
     * �����M���C�O�s��J�����_�[�o�^�����������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i���M�j�C�O�s��J�����_�[�����v�Q��<BR>
     * --------------------------------------------------<BR>
     * �P�j�Ǘ��Ҍ����`�F�b�N<BR>
     * �@@�ڍז���<BR>
     * <BR>
     * �Q�j�Ïؔԍ��`�F�b�N<BR>
     * �@@�ڍז���<BR>
     * <BR>
     * �R�j���M�����I�u�W�F�N�g�̎擾<BR>
     * �@@�@@�@@�@@���M�����I�u�W�F�N�g���擾�ł��Ȃ������ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�i�w������G���[�j<BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_00334 <BR>
     * <BR>
     * �S�j���N�G�X�g�f�[�^.�o�^�X�V���̌������A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�S�|�P�j�C�O�s��J�����_�[.is�x��()���R�[������B<BR>
     * <BR>
     * �@@�S�|�Q�j��Lis�x��()�̖߂�l=true�A����<BR>
     *         ���N�G�X�g�f�[�^.�o�^�X�V���[n].�c�Ɠ��敪="�c�Ɠ�"�̏ꍇ<BR>
     * <BR>
     *   �S�|�Q�|�P�j�C�O�s��J�����_�[Dao.findRowByPK( )���R�[�����āA<BR>
     *         �C�O�s��J�����_�[Row�I�u�W�F�N�g���擾����B<BR>
     * �@@�S�|�Q�|�Q�j�C�O�s��J�����_�[Row�I�u�W�F�N�g.getPrimaryKey()���R�[�����āA<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@PrimaryKey�I�u�W�F�N�g���擾�B<BR>
     * �@@�S�|�Q�|�R�jQueryProcessor.doDeleteQuery()���R�[�����A<BR>
     *              �폜���������{����B<BR>
     * <BR>
     * �@@�S�|�R�j���N�G�X�g�f�[�^.�o�^�X�V���[n].�c�Ɠ��敪=<BR>
     *          "��c�Ɠ�"�̏ꍇ<BR>
     * �@@�@@�S�|�R�|�P�jQueryProcessor.doInsertQuery()���R�[�����A<BR>
     *          �o�^���������{����B<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i���M�j�C�O�s��J�����_�[�����v): 8 get���M����(Institution, String)<BR>
     * ���M�������擾�ł��Ȃ������ꍇ�A��O�w�����w��G���[�x���X���[�B<BR>
     *  class: WEB3BusinessLayerException<BR>
     *  tag:   BUSINESS_ERROR_00334 <BR>
     * [get���M�����̈���]<BR>
     * �@@�@@�Ǘ��҃I�u�W�F�N�g.get�،���ЃR�[�h( )�̖߂�l<BR>
     * �@@�@@���N�G�X�g�f�[�^.�����R�[�h<BR>
     * =========================================================<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return webbroker3.mf.message.WEB3AdminMutualFrgncalCompleteResponse
     * @@roseuid 40D8104E027E
     */
    protected WEB3AdminMutualFrgncalCompleteResponse submitFrgncal(
        WEB3AdminMutualFrgncalCompleteRequest l_request) 
        throws WEB3BaseException
    {
        final String l_strMethodName = "submitFrgncal(WEB3AdminMutualFrgncalCompleteRequest l_request)";
        log.entering(l_strMethodName);

		// 1.1) validate()
		l_request.validate();
		
		// 1.3�j�Ǘ��҃I�u�W�F�N�g�̎擾
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        // 1.4) �Ǘ��҂̌����`�F�b�N 
        // [validate����()�Ɏw�肷�����] 
        // �@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.���M�i�J�����_�[�Ǘ��j 
        // is�X�V�F true
        l_admin.validateAuthority(
                WEB3TransactionCategoryDef.ADMIN_MUTUAL_FRGNCAL,
                true);
        
        // 1.5�j�Ïؔԍ��`�F�b�N 
        l_admin.validateTradingPassword(l_request.password);
        
        // 1.6) �،���ЃI�u�W�F�N�g�̎擾
		Institution l_institution = l_admin.getInstitution(); 
        String l_strInstitutionCode = l_institution.getInstitutionCode();

        // -�g�����M�����}�l�[�W���̎擾���s��
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
        WEB3MutualFundProductManager l_mfProductManager = 
            (WEB3MutualFundProductManager)l_tradingModule.getProductManager();

        // 1.7�j���M�����I�u�W�F�N�g�̎擾
        try
        {
            l_mfProductManager.getMutualFundProduct(l_institution, l_request.mutualProductCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�Y�����铊�M�����I�u�W�F�N�g������܂���!"); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00334,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        
        // 1.8�j���N�G�X�g�f�[�^.�o�^�X�V���̌������A�ȉ��̃`�F�b�N���s��
        try
        {
            WEB3MutualBizDateUnit[] l_mfBizDateUnit = l_request.bizDateList;
            String l_strProductCode = l_request.mutualProductCode;
            for (int i = 0; i < l_mfBizDateUnit.length; i ++)
            {
                // 1.8.1�j�C�O�s��J�����_�[.is�x��() 
                WEB3AdminMutualFrgncal l_adminMutualFrgncal = new WEB3AdminMutualFrgncal();
                boolean l_blnIsHolidy = 
                    l_adminMutualFrgncal.isHoliday(
                        l_strInstitutionCode, 
                        l_strProductCode, 
                        new Timestamp(l_mfBizDateUnit[i].bizDate.getTime()));
                        
                // 1.8.2�jgetDefaultProcessor()
				QueryProcessor l_defaultProcessors = Processors.getDefaultProcessor();
                
                // -���N�G�X�g�f�[�^.�o�^�X�V���[n].�c�Ɠ��敪�̎擾
				String l_strBizDateType = l_mfBizDateUnit[i].bizDateType;
				
				// 1.8.3�jis�x���̖߂�l=true�A
				//        �����N�G�X�g�f�[�^.�o�^�X�V���.�c�Ɠ��敪="�c�Ɠ�"�̏ꍇ
				MutualFundFrgncalRow l_mfFrgncalRow = null;
				if (l_blnIsHolidy && WEB3MFBizDateTypeDef.BIZDATE.equals(l_strBizDateType))
				{
					log.debug("is�x��=true���c�Ɠ��敪=�c�Ɠ��̏ꍇ");
					// 1.8.3.1) �C�O�s��J�����_�[Row�I�u�W�F�N�g���擾 
                	try
                	{
                    	l_mfFrgncalRow = MutualFundFrgncalDao.findRowByPk(
                        	l_strInstitutionCode,
                        	l_strProductCode, 
                        	new Timestamp(l_mfBizDateUnit[i].bizDate.getTime()));
                	}
                	catch (DataFindException l_findEx)
                	{
						log.error("�Y������C�O�s��J�����_�[Row�I�u�W�F�N�g������܂���!"); 
						throw new WEB3BusinessLayerException(
							WEB3ErrorCatalog.BUSINESS_ERROR_00334,
							this.getClass().getName() + "." + l_strMethodName,
								l_findEx.getMessage(),
								l_findEx);
                	}

					// 1.8.3.2) getPrimaryKey()
                	MutualFundFrgncalPK l_mfFrgncalPK =
                		(MutualFundFrgncalPK)l_mfFrgncalRow.getPrimaryKey();
                
               		// 1.8.3.3�jQueryProcessor.doDeleteQuery()���R�[�����A�폜���������{
                    l_defaultProcessors.doDeleteQuery(l_mfFrgncalPK);
                    log.debug("�폜����");
                }
                
                // 1.8.4�jis�x���̖߂�l=false�A
                //        �����N�G�X�g�f�[�^.�o�^�X�V���.�c�Ɠ��敪="��c�Ɠ�"�̏ꍇ
                else if (!l_blnIsHolidy && WEB3MFBizDateTypeDef.NOT_BIZDATE.equals(l_strBizDateType))
                {
					log.debug("is�x��=false���c�Ɠ��敪=��c�Ɠ��̏ꍇ");
					// 1.8.4.1) �C�O�s��J�����_�[Row�I�u�W�F�N�g�𐶐����A�v���p�e�B���Z�b�g����B 
					MutualFundFrgncalParams l_mfFrgncalParams = new MutualFundFrgncalParams();
					l_mfFrgncalParams.setInstitutionCode(l_strInstitutionCode);
					l_mfFrgncalParams.setProductCode(l_strProductCode);
					l_mfFrgncalParams.setHoliday(l_mfBizDateUnit[i].bizDate);
					l_mfFrgncalParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
					l_mfFrgncalParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
					l_mfFrgncalParams.markAllValuesAsSet();
					l_mfFrgncalRow = l_mfFrgncalParams;
					
					// 1.8.4.2�jQueryProcessor.doInsertQuery()���R�[�����A�o�^���������{        
                    l_defaultProcessors.doInsertQuery(l_mfFrgncalRow);
					log.debug("�o�^����");					
                }
            }
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���! ", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���!", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        
        // 1.9) create���X�|���X()
        WEB3AdminMutualFrgncalCompleteResponse l_response = 
            (WEB3AdminMutualFrgncalCompleteResponse)l_request.createResponse();
        log.exiting(l_strMethodName);
        return l_response;
    }

}
@
