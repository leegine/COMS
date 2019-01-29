head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontOrderRouteChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҕ����o�H�ؑփT�[�r�X�����N���X(WEB3AdminFrontOrderRouteChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  �Ӑ� (���u) �d�l�ύX���f��No.116
Revesion History : 2007/02/17  ���؎q (���u) �����̖��No.002-004
Revesion History : 2007/02/28  �Ӑ� (���u) ���f��No.029,030,050
Revesion History : 2007/02/27  ���؎q (���u) �d�l�ύX���f��No.050-058
Revesion History : 2007/02/27  �Ј��� (���u) �d�l�ύX���f��No.094
Revesion History : 2008/12/05  ���L���E (���u) �d�l�ύX���f��No.148,150
*/
package webbroker3.dirsec.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AllOrderChangeDivDef;
import webbroker3.common.define.WEB3ChangeReqResDivDef;
import webbroker3.common.define.WEB3FrontOrderExchangeCodeDef;
import webbroker3.common.define.WEB3FrontOrderStatusDef;
import webbroker3.common.define.WEB3NoticeTypeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.dirsec.define.WEB3AdminFrontServiceStartDivDef;
import webbroker3.dirsec.define.WEB3AdminFrontSwitchStartDivDef;
import webbroker3.dirsec.define.WEB3AdminFrontSwtichStatusDef;
import webbroker3.dirsec.message.WEB3AdminFrontProcessNumberInfoUnit;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeCommonRequest;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeConfirmResponse;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeSelectRequest;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeSelectResponse;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecFrontOrderCommonService;
import webbroker3.dirsec.service.delegate.WEB3AdminFrontOrderRouteChangeService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.OrderSwitchingParams;
import webbroker3.gentrade.data.OrderSwitchingRow;
import webbroker3.gentrade.data.VirtualServerChangeRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��Ҕ����o�H�ؑփT�[�r�X)<BR>
 * <BR>
 * �Ǘ��Ҕ����o�H�ؑփT�[�r�X�����N���X<BR>
 * <BR>
 * WEB3AdminFrontOrderRouteChangeServiceImpl<BR>
 * <BR>
 * @@author SCS.Sato
 * @@version 1.0
 */
public class WEB3AdminFrontOrderRouteChangeServiceImpl implements WEB3AdminFrontOrderRouteChangeService{
    
    /**
    * Log Variable
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFrontOrderRouteChangeServiceImpl.class);

    
    /**
     * @@roseuid 43016EED0061
     */
    public WEB3AdminFrontOrderRouteChangeServiceImpl() 
    {
    
    }
   
    /**
     * �Ǘ��Ҕ����o�H�ؑփT�[�r�X���s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^���A<BR>
     * �@@[�Ǘ��ҁE�����o�H�֑ؑI�����N�G�X�g�̏ꍇ]<BR>
     * �@@�@@this.get�I�����()���R�[������B<BR>
     * <BR>
     * �@@[�Ǘ��ҁE�����o�H�ؑ֊m�F���N�G�X�g�̏ꍇ]<BR>
     * �@@�@@this.validate�����o�H�ؑ�()���R�[������B<BR>
     * <BR>
     * �@@[�Ǘ��ҁE�����o�H�ؑ֊������N�G�X�g�̏ꍇ]<BR>
     * �@@�@@this.submit�����o�H�ؑ�()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 42D233400217
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3GenResponse l_response = null;
 
        try
        {   
            // get�I����ʂ��R�[������B
            if (l_request instanceof WEB3AdminFrontRouteChangeSelectRequest)
            {
                l_response = getSelectScreen((WEB3AdminFrontRouteChangeSelectRequest) l_request);
            } 
            // validate�����o�H�ؑւ��R�[������B
            else if (l_request instanceof WEB3AdminFrontRouteChangeConfirmRequest)
            {
                l_response = validateChange((WEB3AdminFrontRouteChangeConfirmRequest) l_request);
            } 
            // submit�����o�H�ؑւ��R�[������B
            else if (l_request instanceof WEB3AdminFrontRouteChangeCompleteRequest)
            {
                l_response = submitChange((WEB3AdminFrontRouteChangeCompleteRequest) l_request);
            } 
            else
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "INPUT ���N�G�X�g NOT �Ǘ��Ҕ����o�H�ؑփT�[�r�X���N�G�X�g");
            }
        } 
        catch (NotFoundException l_notFoundException)
        {
            log.error(l_notFoundException.getMessage(), l_notFoundException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_notFoundException.getMessage(),
                l_notFoundException);
        } catch (DataQueryException l_dqex)
        {
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_dqex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqex.toString(),
                l_dqex);

        } catch (DataNetworkException l_dnex)
        {
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_dnex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dnex.toString(),
                l_dnex);
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;            
    }
   
    /**
     * �Ǘ��Ҕ����o�H�֑ؑI����ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����o�H�ؑցjget�I����ʁv�Q�ƁB<BR>
     * @@param ���N�G�X�g�f�[�^ - �Ǘ��ҁE�����o�H�֑ؑI�����N�G�X�g�I�u�W�F�N�g<BR>
     * @@return �Ǘ��ҁE�����o�H�֑ؑI�����X�|���X<BR>
     * @@roseuid 42D233AA019A
     */
    protected WEB3AdminFrontRouteChangeSelectResponse getSelectScreen(WEB3AdminFrontRouteChangeSelectRequest l_request) throws WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME = 
            " getSelectScreen(WEB3AdminFrontRouteChangeSelectRequest  l_request)";
        log.entering(STR_METHOD_NAME);

        // ���O�C�����C���X�^���X
        WEB3Administrator l_administrator = null;
        // �t�����g�����Ǘ����ʃT�[�r�X�C���X�^���X
        WEB3AdminDirSecFrontOrderCommonService l_commonService =
             (WEB3AdminDirSecFrontOrderCommonService)Services.getService(WEB3AdminDirSecFrontOrderCommonService.class);
        // �����������I�u�W�F�N�gList
        List l_lstprocessInfoUnits = new ArrayList();
        // �����������N���X�^�z��
        WEB3AdminFrontProcessNumberInfoUnit[] InfoUnits = null;
        // �����o�H�֑ؑI�����X�|���X
        WEB3AdminFrontRouteChangeSelectResponse l_response = null;
        // ��ʕ\���p�ϊ��s��R�[�h
        String l_dispEditMarketCode = null;
  
        // 1.1.���N�G�X�g�p�����[�^��null�`�F�b�N
        l_request.validate();
        
        // 1.2.���O�C�����C���X�^���X�擾
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // 1.3.validate�����`�F�b�N()
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ADMIN_FRONT_ROUTE_SWITCH,
            false);

        //1.4. isDIR�Ǘ���( )�`�F�b�N DIR�Ǘ��҂łȂ��ꍇ�A��O���X���[����B
        boolean l_blnDir = l_administrator.isDirAdministrator();
        if (!l_blnDir)
        {
            log.error(STR_METHOD_NAME +
                WEB3ErrorCatalog.BUSINESS_ERROR_00857.error_message);
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + STR_METHOD_NAME);            
        }

        // 1.5.�����o�H�֑ؑΏۃ��R�[�h���擾
        List l_switchRoutes = l_commonService.getFrontSwitchRouteTarget(l_request.institutionCode);
        
        // 1.6.�����o�H�֑ؑΏۃ��R�[�h�̃T�C�Y���ALoop�������s���B
        Iterator l_loopObj = l_switchRoutes.iterator();
        
        while(l_loopObj.hasNext()){
            // OrderSwitchingRow�I�u�W�F�N�g�̒��o
            OrderSwitchingRow l_swtichRow = (OrderSwitchingParams)l_loopObj.next();
            
            // �،���ЃR�[�h�擾
            String instCode = l_swtichRow.getInstitutionCode();
            
            // �s��R�[�h�擾
            String l_marCode = l_swtichRow.getMarketCode();
            
            // �����^�C�v�擾
            String l_productType = Integer.toString(l_swtichRow.getProductType().intValue());
            
            // �t�����g�����V�X�e���敪�擾
            String l_sysDiv = l_swtichRow.getFrontOrderSystemCode();
 
            // 1.6.1.�����������N���X�̃I�u�W�F�N�g�𐶐�
            WEB3AdminFrontProcessNumberInfoUnit l_processInfoUnit = new WEB3AdminFrontProcessNumberInfoUnit();
            
            // 1.6.2.�������o�H���擾
            l_commonService.getNowOrderRoute(instCode, l_marCode, l_sysDiv, l_productType, l_processInfoUnit);
            
            // 1.6.3.���z�T�[�o�������擾
            l_commonService.getVitualServerCount(instCode, l_marCode, l_sysDiv, l_productType, l_processInfoUnit);
            
            // 1.6.4.�ؑ֏��������敪���S�������������̏ꍇ�A�O���[���������擾����B
            if(l_request.changeProcessDiv.equals(WEB3AllOrderChangeDivDef.ALL_ORDER_CHANGE)){
                
                if (l_productType.equals(Integer.toString(ProductTypeEnum.EQUITY.intValue())))
                {
                	l_commonService.getGrayOrder(instCode, l_marCode, l_sysDiv, l_processInfoUnit);          
                }
                else if (l_productType.equals(Integer.toString(ProductTypeEnum.IFO.intValue())))
                {
                	l_commonService.getIfoGrayOrder(instCode, l_marCode, l_sysDiv, l_processInfoUnit);
                }
            }
            
            // 1.6.5.�ؑ֎w���f�[�^�������擾
            boolean  l_boolSwitchDiv = getSwitchPointDataCount(instCode, l_marCode, l_sysDiv, l_productType,
                l_processInfoUnit, l_request.changeProcessDiv);

            // 1.6.6.�ؑ֏��������������N���X�Ɋi�[
            this.createSwitchInfo(l_request.changeProcessDiv, l_processInfoUnit, l_boolSwitchDiv);
            
            // 1.6.7.��ʕ\���p�̎s��R�[�h���擾����B
            l_dispEditMarketCode = l_commonService.getFrontOrderMarketCode(l_marCode, l_sysDiv);
            
            // 1.6.8.�����������N���X�I�u�W�F�N�g�ɒl���Z�b�g
            // �ϊ��s��R�[�h
            l_processInfoUnit.convertMarketCode =l_dispEditMarketCode;
            // �s��R�[�h
            l_processInfoUnit.marketCode = l_swtichRow.getMarketCode();
            // �����^�C�v
            l_processInfoUnit.productType = Integer.toString(l_swtichRow.getProductType().intValue());
            // �t�����g�����V�X�e���敪
            l_processInfoUnit.frontOrderSystemCode = l_sysDiv;
            // �ύX�㔭���o�H�敪
            l_processInfoUnit.changedSubmitOrderRouteDiv = null;
            // �����������I�u�W�F�N�g��List�ɒǉ��B
            l_lstprocessInfoUnits.add(l_processInfoUnit);
        }
        // �����������I�u�W�F�N�gList��z��ɕϊ�
        InfoUnits = new WEB3AdminFrontProcessNumberInfoUnit[l_lstprocessInfoUnits.size()];
        
        InfoUnits = (WEB3AdminFrontProcessNumberInfoUnit[])l_lstprocessInfoUnits.toArray(InfoUnits);
        
        // 1.7.���X�|���X�I�u�W�F�N�g����
        l_response = (WEB3AdminFrontRouteChangeSelectResponse) l_request.createResponse();

        // 1.8.�����������I�u�W�F�N�g���i�[        
        l_response.orderRouteInfoUnit = InfoUnits;
        
        // 1.9.���X�|���X��ԋp
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }


    /**
     * �Ǘ��Ҕ����o�H�ؑ֊m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����o�H�ؑցjvalidate�����o�H�ؑցv�Q�ƁB<BR>
     * @@param ���N�G�X�g�f�[�^ - �Ǘ��ҁE�����o�H�ؑ֊m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteComfirmResponse<BR>
     * @@roseuid 42D233AA01B9
     */
    protected WEB3AdminFrontRouteChangeConfirmResponse validateChange(WEB3AdminFrontRouteChangeConfirmRequest l_request)  throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateChange(WEB3AdminFrontRouteChangeConfirmRequest  l_request)";
        log.entering(STR_METHOD_NAME);
        // �ؑ֋N���敪�`�F�b�N�t���O
        boolean l_boolSwitchDiv = false;
        // �t�����g�����Ǘ����ʃT�[�r�X�C���X�^���X
        WEB3AdminDirSecFrontOrderCommonService l_commonService =
             (WEB3AdminDirSecFrontOrderCommonService)Services.getService(WEB3AdminDirSecFrontOrderCommonService.class);
        // �����o�H�ؑ֊m�F���X�|���X
        WEB3AdminFrontRouteChangeConfirmResponse l_response = null;
  
        // 1.1.���N�G�X�g�p�����[�^��null�`�F�b�N
        l_request.validate();
        
        // �،���ЃR�[�h
        String l_institutionCode = l_request.institutionCode;
        // �t�����g����������敪�R�[�h
        String l_frontOrderExcCode = l_commonService.getFrontOrderExchangeCode(l_request.convertMarketCode);
        // �t�����g�����V�X�e���敪
        String l_frontSysDiv = l_commonService.getFrontSystemDiv(l_request.convertMarketCode);
        
        // 1.2.�ؑ֋N���敪�`�F�b�N�����s
        l_boolSwitchDiv = this.validateSwitchBootDiv(l_institutionCode, l_request ,l_commonService);
        
        // validate�ؑ֋N���敪�i�j��false�̏ꍇ�A��O���X���[����B
        if(!l_boolSwitchDiv){
            log.error(STR_METHOD_NAME +
                WEB3ErrorCatalog.BUSINESS_ERROR_02206.error_message);
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02206,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        // SONAR�S��Q�`�F�b�N
        l_commonService.validateSonarCheck(l_institutionCode, l_frontOrderExcCode, l_frontSysDiv, l_request.productType);  

        // 1.5.���X�|���X�I�u�W�F�N�g����
        l_response = (WEB3AdminFrontRouteChangeConfirmResponse) l_request.createResponse();

        // 1.6.���X�|���X��ԋp
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
   
    /**
     * �Ǘ��Ҕ����o�H�ؑ֊����������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����o�H�ؑցjsubmit�����o�H�ؑցv�Q�ƁB<BR>
     * @@param ���N�G�X�g�f�[�^ - �Ǘ��ҁE�����o�H�ؑ֊������N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteCompleteResponse<BR>
     * @@roseuid 42D233AA01D9
     */
    protected WEB3AdminFrontRouteChangeCompleteResponse submitChange(WEB3AdminFrontRouteChangeCompleteRequest l_request) throws WEB3BaseException, DataNetworkException, DataQueryException
    {
        final String STR_METHOD_NAME = 
            "submitChange(WEB3AdminFrontRouteChangeCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        // �t�����g�����Ǘ����ʃT�[�r�X�C���X�^���X
        WEB3AdminDirSecFrontOrderCommonService l_commonService =
             (WEB3AdminDirSecFrontOrderCommonService)Services.getService(WEB3AdminDirSecFrontOrderCommonService.class);
        // �����o�H�ؑ֊������X�|���X
        WEB3AdminFrontRouteChangeCompleteResponse l_response = null;
        
        // 1.1.1.���N�G�X�g�p�����[�^��null�`�F�b�N
        l_request.validate();

        // �T�[�r�X�N���敪
        String l_serviceStartDiv = l_request.serviceStartDiv;
        // ���O�C�����C���X�^���X
        WEB3Administrator l_administrator = null;
        // �،���ЃR�[�h
        String l_institutionCode = l_request.institutionCode;
        // �t�����g����������敪�R�[�h
        String l_frontOrderExcCode = l_commonService.getFrontOrderExchangeCode(l_request.convertMarketCode);
        // �t�����g�����V�X�e���敪
        String l_frontSysDiv = l_commonService.getFrontSystemDiv(l_request.convertMarketCode);
        // �ؑ֋N���敪�`�F�b�N�t���O
        boolean l_boolSwitchDiv = false;
       
        
        // ��t���ԃ`�F�b�N�͎��{���Ȃ�
		// ��t���ԃ`�F�b�N��false�Ȃ�G���[��ԋp
//        if(!l_commonService.isSubmitMarketTrigger(l_institutionCode, l_request.marketCode))
//        {                log.error(STR_METHOD_NAME +
//            WEB3ErrorCatalog.SYSTEM_ERROR_80015.error_message);
//            log.exiting(STR_METHOD_NAME);
//            
//            throw new WEB3BusinessLayerException(
//            WEB3ErrorCatalog.SYSTEM_ERROR_80015,
//            this.getClass().getName() + STR_METHOD_NAME);
//        }

        //������ؑփe�[�u��DEOS���R�[�h���݃`�F�b�N
		if(!l_commonService.isFrontRoute(l_institutionCode, l_request.marketCode, l_frontSysDiv, l_request.productType))
		{                log.error(STR_METHOD_NAME +
			WEB3ErrorCatalog.BUSINESS_ERROR_02216.error_message);
			log.exiting(STR_METHOD_NAME);
            
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02216,
				this.getClass().getName() + "." + STR_METHOD_NAME);         
		}
		
        // 1.1.�T�[�r�X�N���敪���Ǘ��ҋN���̏ꍇ�̂݁A�`�F�b�N�������s���B
        if(l_serviceStartDiv != null && l_serviceStartDiv.equals(WEB3AdminFrontServiceStartDivDef.ADMINISTRATOR_DIV))
        {
            // 1.1.2.���O�C�����C���X�^���X�擾
            l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
            
            // 1.3.validate�����`�F�b�N()
            l_administrator.validateAuthority(WEB3TransactionCategoryDef.ADMIN_FRONT_ROUTE_SWITCH,true);

            //1.4. isDIR�Ǘ���( )�`�F�b�N DIR�Ǘ��҂łȂ��ꍇ�A��O���X���[����B
            boolean l_blnDir = l_administrator.isDirAdministrator();

            if (!l_blnDir)
            {
                log.error(STR_METHOD_NAME +
                    WEB3ErrorCatalog.BUSINESS_ERROR_00857.error_message);
                log.exiting(STR_METHOD_NAME);
            
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                    this.getClass().getName() + STR_METHOD_NAME);            
            }
             
            // 1.1.3.�p�X���[�h�ƍ��`�F�b�N
            l_administrator.validateTradingPassword(l_request.password);
            
            // 1.1.5.�ؑ֋N���敪�`�F�b�N
            l_boolSwitchDiv = this.validateSwitchBootDiv(l_institutionCode, l_request, l_commonService);

            // 1.1.6.�ؑ֋N���敪�`�F�b�N��false�̏ꍇ�A��O���X���[����B
            if(!l_boolSwitchDiv){
            
                log.error(STR_METHOD_NAME +
                    WEB3ErrorCatalog.BUSINESS_ERROR_02206.error_message);
                log.exiting(STR_METHOD_NAME);
            
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02206,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            // 1.1.7.SONAR�S��Q�G���[�̏ꍇ�A��O���X���[����B
            l_commonService.validateSonarCheck(l_institutionCode, l_frontOrderExcCode, l_frontSysDiv, l_request.productType);
        }
        
        // 1.2.�ؑ֏����N��
        l_commonService.executeSwitchTransactionIssue(l_request);
  

        // 1.3.���X�|���X�I�u�W�F�N�g����
        l_response = (WEB3AdminFrontRouteChangeCompleteResponse) l_request.createResponse();

        // 1.4.���X�|���X��ԋp
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
   
    /**
     * ���z�T�[�o�ؑ֎w���f�[�^�̌������J�E���g�A�b�v����B<BR>
     * <BR>
     * �P�j�@@�t�����g�����Ǘ����ʃT�[�r�X.get�ؑ֎w���������R�[�h�i�j�ŉ������R�[�h���擾����B<BR>
     * <BR>
     * �@@[����] 
     * �@@�،���ЃR�[�h�F �p�����[�^.�،���ЃR�[�h 
     * �@@�s��R�[�h�F �p�����[�^.�s��R�[�h 
     * �@@�t�����g�����V�X�e���敪�F �p�����[�^.�t�����g�����V�X�e���敪 
     * �@@�����^�C�v�F �p�����[�^.�����^�C�v 
     * <BR>
     * �Q�j�@@if�@@���z�T�[�o�ؑ֎w�������n�f�[�^���� != �O���̏ꍇ<BR>
     * <BR>
     * �@@�Q�|�P�j�@@set�ؑ֎w�����������i�j�����ŁA���z�T�[�o�ؑ֎w���f�[�^�̌������J�E���g�A�b�v����B<BR>
     * <BR>
     * <BR>
     * �R�j�@@else�@@���z�T�[�o�ؑ֎w�������n�f�[�^���� == �O���̏ꍇ<BR>
     * <BR>
     * �@@�R�|�P�j�@@�t�����g�����Ǘ����ʃT�[�r�X.get�ؑ֎w���v�����R�[�h�i�j�ŗv�����R�[�h���擾����B<BR>
     * <BR>
     * �@@�@@[����] <BR>
     * �@@�@@�،���ЃR�[�h�F �p�����[�^.�،���ЃR�[�h<BR> 
     * �@@�@@�s��R�[�h�F �p�����[�^.�s��R�[�h <BR>
     * �@@�@@�t�����g�����V�X�e���敪�F �p�����[�^.�t�����g�����V�X�e���敪<BR> 
     * �@@�@@�����^�C�v�F �p�����[�^.�����^�C�v <BR>
     * <BR>
     * �@@�R�|�Q�j�@@�R�|�P�j == �O���̏ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * �@@�R�|�R�j�@@�R�|�P�j != <BR>
     * �O���̏ꍇ�Aset�ؑ֎w�����������i�j�����ŁA���z�T�[�o�ؑ֎w���f�[�^�̌������J�E���g�A�b�v����B<BR>
     * <BR>
     * �S�j�@@true��ԋp����B<BR>
     * @@param �،���ЃR�[�h - �،���ЃR�[�h�B<BR>
     * @@param �s��R�[�h - �s��R�[�h�B<BR>
     * @@param �t�����g�����V�X�e���敪 - �t�����g�����V�X�e���敪�B<BR>
     * @@param �����^�C�v - �����^�C�v�B<BR>
     * @@param �����������I�u�W�F�N�g - �����������I�u�W�F�N�g�B<BR>
     * @@param �ؑ֏��������敪 - �ؑ֏��������敪�B<BR>
     * @@return boolean<BR>
     * @@roseuid 42EE0D22009C
     */
    protected boolean getSwitchPointDataCount(String l_institutionCode, String l_marketCode, 
    String l_frontSystemCode, String l_productType, WEB3AdminFrontProcessNumberInfoUnit l_processInfoUnit,
    String l_changeProcessDiv) throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = " getSwitchPointDataCount(String, String, String, String, WEB3AdminFrontProcessNumberInfoUnit, String)";

        log.entering(STR_METHOD_NAME);

        // �����n���R�[�h
        List l_lstresResRcords = null;
        // �v���n���R�[�h
        List l_lstresReqRcords = null;
        
        // �t�����g�����Ǘ����ʃT�[�r�X�C���X�^���X
        WEB3AdminDirSecFrontOrderCommonService l_proCommonService =
             (WEB3AdminDirSecFrontOrderCommonService)Services.getService(WEB3AdminDirSecFrontOrderCommonService.class);
        
        // �����n���R�[�h���擾     
        l_lstresResRcords = l_proCommonService.getSwitchRouteResRcord(l_institutionCode,
            l_marketCode, l_frontSystemCode, l_productType);
        
        if(l_lstresResRcords.size() != 0){
            // �����n���R�[�h���J�E���g�A�b�v
            this.setSwitchPointResCount(l_lstresResRcords, l_processInfoUnit, l_changeProcessDiv);
        }
        else if(l_lstresResRcords.size() == 0)
        {
            // �v���n���R�[�h���擾     
            l_lstresReqRcords = l_proCommonService.getSwitchRouteReqRcord(l_institutionCode,
                l_marketCode, l_frontSystemCode, l_productType);
            
            // �v���n���R�[�h��0���̏ꍇ�Afalse��ԋp
            if(l_lstresReqRcords.size() == 0){
                
                return false; 
            }
            else if(l_lstresReqRcords.size() != 0)
            {
                 // �v���n���R�[�h���J�E���g�A�b�v
                this.setSwitchPointReqCount(l_lstresReqRcords, l_processInfoUnit);
            }
        }
        return true;
    }
   
    /**
     * �ȉ��̏�������A�ؑ֋N���敪�A��ʕ\���p�X�e�[�^�X��ݒ肷��B<BR>
     * <BR>
     * <BR>
     * �P�j�@@�����o�H�ؑ֋敪 == false�̏ꍇ<BR>
     * <BR>
     * �@@�P�|�P�j�@@�ؑ֋N���敪 = "0:�����o�H�ؑ�"<BR>
     * <BR>
     * �@@�P�|�Q�j�@@��ʕ\���p�X�e�[�^�X = "0:������"<BR>
     * <BR>
     * �Q�j�@@�����o�H�ؑ֋敪 == true�̏ꍇ<BR>
     * <BR>
     * �Q�|�P�j�@@�ؑ֏���������"�P�F�S������������"�̏ꍇ�Acheck�S���������i�j���s���B<BR>
     * �@@�@@�@@if( validate�S��������() )�̒l��"false"�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�ؑ֋N���敪 = "9:�ؑ֕s��"<BR>
     * �@@�@@�@@�@@��ʕ\���p�X�e�[�^�X = "9:�ؑ֕s��"<BR>
     * <BR>
     * �Q�|�Q�j�@@if(�O���[�����F�s���t�m�F������ > 0 && �ؑ֏��������敪 = "1:�S������������")<BR>
     * <BR>
     * �@@�@@�@@�ؑ֋N���敪 = "6:�S���������ċN��"<BR>
     * �@@�@@�@@��ʕ\���p�X�e�[�^�X = "1:�ؑ֒�"<BR>
     * <BR>
     * �Q�|�R�j�@@if(�ʔԏƉ�v���F���������� > 0 && �ؑ֏��������敪 = <BR>
     * "0:�ʔԏƉ������")<BR>
     * <BR>
     * �@@�@@�@@�ؑ֋N���敪 = "1:�ʔԏƉ�v���ċN��"<BR>
     * �@@�@@�@@��ʕ\���p�X�e�[�^�X = "1:�ؑ֒�"<BR>
     * <BR>
     * <BR>
     * <BR>
     * �Q�|�S�j�@@if(�@@�ʒm��s�����v���F���������� > 0�@@)<BR>
     * <BR>
     * �@@�@@�@@�ؑ֋N���敪 = "2:�ʒm��s�����v���ċN��"<BR>
     * �@@�@@�@@��ʕ\���p�X�e�[�^�X = "1:�ؑ֒�"<BR>
     * <BR>
     * <BR>
     * �Q�|�T�j�@@if(�@@�ʒm��s�v���F���������� > 0�@@)<BR>
     * <BR>
     * �@@�@@�@@�ؑ֋N���敪 = "3:�ʒm��s�v���ċN��"<BR>
     * �@@�@@�@@��ʕ\���p�X�e�[�^�X = "1:�ؑ֒�"<BR>
     * <BR>
     * <BR>
     * �Q�|�U�j�@@if(�@@�ʒm�đ��v���i��t�n�j�F���������� > 0�@@)<BR>
     * <BR>
     * �@@�@@�@@�ؑ֋N���敪 = "4:�ʒm�đ��v���i��t�n�j�ċN��"<BR>
     * �@@�@@�@@��ʕ\���p�X�e�[�^�X = "1:�ؑ֒�"<BR>
     * <BR>
     * <BR>
     * �Q�|�V�j�@@if(�@@�ʒm�đ��v���i���n�j�F���������� > 0�@@)<BR>
     * <BR>
     * �@@�@@�@@�ؑ֋N���敪 = "5:�ʒm�đ��v���i���n�j�ċN��"<BR>
     * �@@�@@�@@��ʕ\���p�X�e�[�^�X = "1:�ؑ֒�"<BR>
     * <BR>
     * <BR>
     * �Q�|�W�j�@@�Q�|�P�j�`�Q�|�V�j�𖞂����Ȃ��ꍇ<BR>
     * <BR>
     * �@@�ؑ֋N���敪 = "7:�ؑ֏�������"<BR>
     * <BR>
     * �@@��ʕ\���p�X�e�[�^�X = "2:�ؑ֍�"<BR>
     * @@param ���N�G�X�g�I�u�W�F�N�g - �Ǘ��ҁE�����o�H�֑ؑI�����N�G�X�g�B<BR>
     * @@param �����������I�u�W�F�N�g - �����������I�u�W�F�N�g�B<BR>
     * @@param �����o�H�ؑ֋敪 - get�ؑ֎w���f�[�^�����i�j�̖߂�l�B<BR>
     * @@roseuid 42EEF7710138
     */
    protected void createSwitchInfo(String l_proChangeDiv, 
                                        WEB3AdminFrontProcessNumberInfoUnit l_proProcessInfoUnit, boolean l_boolProSwDiv) 
    {
        final String STR_METHOD_NAME = " createSwitchInfo(l_proRequest, l_proProcessInfoUnit, boolean)";

        log.entering(STR_METHOD_NAME);
        
        // ������boolean��false�̏ꍇ�A�����o�H�ؑ֏����͐V�K���s
        if(!l_boolProSwDiv)
        {   
            // �ؑ֋N���敪:0:�����o�H�ؑ�
            l_proProcessInfoUnit.changeStartDiv =WEB3AdminFrontSwitchStartDivDef.FRONT_ORDER_ROUTE_SWITCH;
            // ��ʗp�\���X�e�[�^�X0:������
            l_proProcessInfoUnit.changeStatus = WEB3AdminFrontSwtichStatusDef.NO_DEAL;
        }
        else
        {
            // �ؑ֏��������敪 = "�O:�ʔԏƉ������"�̏ꍇ            
            if(l_proChangeDiv.equals(WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE))
            {
				// �S����������true�̏ꍇ�A�ؑ֕s��ݒ�
				if(validateAllOrderChange(l_proProcessInfoUnit))
				{
					// �ؑ֋N���敪:"9:�ؑ֕s��"
					l_proProcessInfoUnit.changeStartDiv =WEB3AdminFrontSwitchStartDivDef.FRONT_ORDER_NO_CHANGE;
					// ��ʗp�\���X�e�[�^�X"9:�ؑ֕s��"
					l_proProcessInfoUnit.changeStatus = WEB3AdminFrontSwtichStatusDef.DISP_NO_CHANGE;
                    
					return;
				}
				
                // �ʔԏƉ�v���F���������� > 0 �̏ꍇ
                if(Integer.parseInt(l_proProcessInfoUnit.nonProcessNumber1) > 0)
                {
                    //�ؑ֋N���敪"1:�ʔԏƉ�v���ċN��"
                    l_proProcessInfoUnit.changeStartDiv = WEB3AdminFrontSwitchStartDivDef.NUBER_REF_REQ_RESTART; 
                    // ��ʕ\���p�X�e�[�^�X"1:�ؑ֒�"
                    l_proProcessInfoUnit.changeStatus = WEB3AdminFrontSwtichStatusDef.DISP_CHANEGE_IN;
                    
                    return;
                }
            }
			// �ؑ֏��������敪 = "1:�S������������"�̏ꍇ
			if(l_proChangeDiv.equals(WEB3AllOrderChangeDivDef.ALL_ORDER_CHANGE))
			{
				// �S����������false�̏ꍇ�A�ؑ֕s��ݒ�
				if(!validateAllOrderChange(l_proProcessInfoUnit))
				{
					// �ؑ֋N���敪:"9:�ؑ֕s��"
					l_proProcessInfoUnit.changeStartDiv =WEB3AdminFrontSwitchStartDivDef.FRONT_ORDER_NO_CHANGE;
					// ��ʗp�\���X�e�[�^�X"9:�ؑ֕s��"
					l_proProcessInfoUnit.changeStatus = WEB3AdminFrontSwtichStatusDef.DISP_NO_CHANGE;
                    
					return;
				}
			}
            // �Ȍ�A�ʔԏƉ���A�S���������ɂ�����炸���ʏ���
            // �ʒm��s�����v���F���������� > 0 �̏ꍇ�@@
            if(Integer.parseInt(l_proProcessInfoUnit.nonProcessNumber2) > 0)
            {

                //�ؑ֋N���敪"2:�ʒm��s�����v���ċN��" 
                l_proProcessInfoUnit.changeStartDiv = WEB3AdminFrontSwitchStartDivDef.NOTICEAGENCY_REL_REQ_RESTART; 
                // ��ʕ\���p�X�e�[�^�X"1:�ؑ֒�"
                l_proProcessInfoUnit.changeStatus = WEB3AdminFrontSwtichStatusDef.DISP_CHANEGE_IN;
                
				return;
            }
            // �ʒm��s�v���F���������� > 0 �̏ꍇ
            if(Integer.parseInt(l_proProcessInfoUnit.nonProcessNumber3) > 0)
            {
                // �ؑ֋N���敪 "3:�ʒm��s�v���ċN��" 
                l_proProcessInfoUnit.changeStartDiv = WEB3AdminFrontSwitchStartDivDef.NOTICEAGENCY_REQ_RESTART;
                // ��ʕ\���p�X�e�[�^�X = "1:�ؑ֒�" 
                l_proProcessInfoUnit.changeStatus = WEB3AdminFrontSwtichStatusDef.DISP_CHANEGE_IN;
                
				return;
            }
			// �ؑ֏��������敪 = "1:�S������������"�̏ꍇ
			if(l_proChangeDiv.equals(WEB3AllOrderChangeDivDef.ALL_ORDER_CHANGE))
			{
				// �O���[�����F�s���t�m�F�O���� > 0 �̏ꍇ
				if(Integer.parseInt(l_proProcessInfoUnit.beforeNumber) > 0)
				{
					// �ؑ֋N���敪:0:�����o�H�ؑ�
					l_proProcessInfoUnit.changeStartDiv =WEB3AdminFrontSwitchStartDivDef.FRONT_ORDER_ROUTE_SWITCH;
					// ��ʗp�\���X�e�[�^�X0:������
					l_proProcessInfoUnit.changeStatus = WEB3AdminFrontSwtichStatusDef.NO_DEAL;
                       
					return;
				}
				// �u�O���[�����F�s���t�m�F������ > 0�v�܂��́u�O���[�����F�s���t�m�F������ = 0 ���ʒm�đ��v���i��t�n�j�F�����ό��� = 0�v�̏ꍇ
				else if((Integer.parseInt(l_proProcessInfoUnit.inNumber) > 0 ) ||
						(Integer.parseInt(l_proProcessInfoUnit.inNumber) == 0 && Integer.parseInt(l_proProcessInfoUnit.finProcessNumber4) == 0))
				{
					// �ؑ֋N���敪 = "6:�S���������ċN��"
					l_proProcessInfoUnit.changeStartDiv = WEB3AdminFrontSwitchStartDivDef.ALLCORR_REQ_RESTART;
					// ��ʕ\���p�X�e�[�^�X = "1:�ؑ֒�"
					l_proProcessInfoUnit.changeStatus = WEB3AdminFrontSwtichStatusDef.DISP_CHANEGE_IN;
                        
					return;
				}
                                       
			}
            // �ʒm�đ��v���i��t�n�j�F���������� > 0 �̏ꍇ
            if(Integer.parseInt(l_proProcessInfoUnit.nonProcessNumber4) > 0)
            {
                // �ؑ֋N���敪 "4:�ʒm�đ��v���i��t�n�j�ċN��" 
                l_proProcessInfoUnit.changeStartDiv = WEB3AdminFrontSwitchStartDivDef.NOTICERESEND_REQ_ACC_RESTART;
                // ��ʕ\���p�X�e�[�^�X = "1:�ؑ֒�" 
                l_proProcessInfoUnit.changeStatus = WEB3AdminFrontSwtichStatusDef.DISP_CHANEGE_IN;
                
				return;
            }
            // �ʒm�đ��v���i���n�j�F���������� > 0 �̏ꍇ
            if(Integer.parseInt(l_proProcessInfoUnit.nonProcessNumber5) > 0)
            {
                // �ؑ֋N���敪 "5:�ʒm�đ��v���i���n�j�ċN��" 
                l_proProcessInfoUnit.changeStartDiv = WEB3AdminFrontSwitchStartDivDef.NOTICERESEND_REQ_CONT_RESTART;
                // ��ʕ\���p�X�e�[�^�X = "1:�ؑ֒�" 
                l_proProcessInfoUnit.changeStatus = WEB3AdminFrontSwtichStatusDef.DISP_CHANEGE_IN;
                
				return;
            }
            
                // �ؑ֋N���敪 "7:�ؑ֏�������"
                l_proProcessInfoUnit.changeStartDiv = WEB3AdminFrontSwitchStartDivDef.FRONT_ORDER_CHANGE_COMPLETE; 

                // ��ʕ\���p�X�e�[�^�X "2:�ؑ֍�"
                l_proProcessInfoUnit.changeStatus = WEB3AdminFrontSwtichStatusDef.DISP_CHANEGE_END; 
            
        }
    }
   
    /**
     * �S���������ɐؑ։\���`�F�b�N���s���B<BR>
     * <BR>
     * 1) boolean�I�u�W�F�N�g = false�𐶐�����B<BR>
     * <BR>
     * if(�ʔԏƉ�v���F������ + �ʔԏƉ�v���F������ > 0)<BR>
     * <BR>
     * �@@false��ԋp����B<BR>
     * <BR>
     * else<BR>
     * �@@true��ԋp����B<BR>
     * @@param �����������I�u�W�F�N�g - �����������I�u�W�F�N�g�B<BR>
     * @@return boolean<BR>
     * @@roseuid 42EEC82803D0
     */
    private boolean validateAllOrderChange(WEB3AdminFrontProcessNumberInfoUnit l_PriProcessInfoUnit) 
    {
        boolean l_orderChangeDiv = false;
        
        int l_intProInfo = Integer.parseInt(l_PriProcessInfoUnit.nonProcessNumber1) + Integer.parseInt(l_PriProcessInfoUnit.finProcessNumber1);
        
        if(l_intProInfo > 0)
        {
            return  l_orderChangeDiv;
        }
        else
        {
            l_orderChangeDiv = true;
        }

        return l_orderChangeDiv;
    }
    
    /**
     * ���N�G�X�g�œn���ꂽ�ؑ֋N���敪�ƁA���݂̐ؑ֋N���敪�����������`�F�b�N���s���B<BR>
     * <BR>
     * <BR>
     * �P�j�@@�����������I�u�W�F�N�g�̐����B<BR>
     * <BR>
     * �Q�j�@@�t�����g�����Ǘ����ʃT�[�r�X.get�t�����g�����V�X�e���敪�i)�ŁA�t�����g�����V�X�e���敪���擾����B<BR>
     * <BR>
     * �R�j�@@�t�����g�����Ǘ����ʃT�[�r�X.get���z�T�[�o�����i�j�ŁA���z�T�[�o�������擾����B<BR>
     * <BR>
     * �@@[����]<BR> 
     * �@@�،���ЃR�[�h�F �p�����[�^.�،���ЃR�[�h<BR> 
     * �@@�s��R�[�h�F �p�����[�^.���N�G�X�g.�s��R�[�h<BR>
     * �@@�t�����g�����V�X�e���敪�F �Q�j�̃t�����g�����V�X�e���敪<BR> 
     * �@@�����^�C�v�F �p�����[�^.���N�G�X�g.�����^�C�v<BR> 
     * �@@�����������F �P�j�̏����������<BR> 
     * <BR>
     * �S�j�@@if�i�p�����[�^.�ؑ֏����N���敪 = "�P�F�S�������������ؑ�"�j�̏ꍇ�A<BR>
     * <BR>
     * �@@�S�|�P�j�@@�p�����[�^.���N�G�X�g.�����^�C�v��1�F�����̏ꍇ<BR>
     * <BR>
     * �@@�@@�S�|�P�|�P�j�@@�t�����g�����Ǘ����ʃT�[�r�X.get�O���[�����i�j�ŁA�O���[�����������擾����B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F �p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@�@@�s��R�[�h�F �p�����[�^.���N�G�X�g.�s��R�[�h<BR>
     * �@@�@@�@@�t�����g�����V�X�e���敪�F �Q�j�̃t�����g�����V�X�e���敪<BR>
     * �@@�@@�@@�����������F �P�j�̏���������� <BR>
     * <BR>
     * �@@�S�|�Q�j�@@�p�����[�^.���N�G�X�g.�����^�C�v��6�F�敨�I�v�V�����̏ꍇ<BR>
     * <BR>
     * �@@�@@�S�|�Q�|�P�j�@@�t�����g�����Ǘ����ʃT�[�r�X.get�敨OP�O���[�����i�j�ŁA�O���[�����������擾����B<BR>
     * <BR>
     * �@@�@@�@@[����] <BR>
     * �@@�@@�@@�،���ЃR�[�h�F �p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@�@@�s��R�[�h�F �p�����[�^.���N�G�X�g.�s��R�[�h<BR>
     * �@@�@@�@@�t�����g�����V�X�e���敪�F �Q�j�̃t�����g�����V�X�e���敪<BR>
     * �@@�@@�@@�����������F �P�j�̏���������� <BR>
     * <BR>
     * �T�j�@@get�ؑ֎w���f�[�^�����i�j�ŁA���z�T�[�o�ؑ֎w���f�[�^�������擾����B<BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�،���ЃR�[�h�F �p�����[�^.�،���ЃR�[�h<BR>
     * �@@�s��R�[�h�F �p�����[�^.���N�G�X�g.�s��R�[�h<BR>
     * �@@�t�����g�����V�X�e���敪�F �Q�j�̃t�����g�����V�X�e���敪<BR>
     * �@@�����^�C�v�F �p�����[�^.���N�G�X�g.�����^�C�v<BR>
     * �@@�����������F �P�j�̏����������<BR>
     * �@@�ؑ֏��������敪�F �p�����[�^.���N�G�X�g.�ؑ֏��������敪<BR>
     * <BR>
     * �U�j�@@create�ؑ֏��i�j<BR>
     * <BR>
     * �V�j�@@�����������.�ؑ֋N���敪 != null�A<BR>
     * �@@�@@�@@���A�����������.�ؑ֋N���敪 == �p�����[�^.���N�G�X�g.�ؑ֋N���敪�̏ꍇ<BR> 
     * <BR>
     * �@@�V�|�P�j�@@true��ԋp�B<BR>
     * <BR>
     * �@@�V�|�Q�j�@@else�̏ꍇ�Afalse��ԋp�B<BR>
     * @@param �،���ЃR�[�h - �،���ЃR�[�h�B<BR>
     * @@param ���N�G�X�g�I�u�W�F�N�g - �Ǘ��ҁE�����o�H�ؑ֋��ʃ��N�G�X�g�B<BR>
     * @@param �t�����g�����Ǘ����ʃT�[�r�X - ���ʊǗ��T�[�r�X�I�u�W�F�N�g�B<BR>
     * @@return boolean<BR>
     * @@roseuid 42F055BC022E
     */
    private boolean validateSwitchBootDiv(String l_priInstCode, WEB3AdminFrontRouteChangeCommonRequest l_priRequest, WEB3AdminDirSecFrontOrderCommonService l_priCommonObj) throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "validateSwitchBootDiv(String, l_priRequest,l_priProcessInfo)";
        log.entering(STR_METHOD_NAME);
     
        // �߂�l
        boolean l_boolret = false;
        // ���ؑ֋N���敪
        String l_nowChangeStartDiv = null;
        // �t�����g�����V�X�e���敪
        String l_frontSysDiv = null;
        // �ϊ��s��R�[�h
        String l_convertMarketCode = null;
        // �ؑ֏��������敪
        String l_changeProDiv = null;
        // �s��R�[�h
        String l_marketCode = null;
        // �����^�C�v
        String l_strProductType = null;
        
        // ���N�G�X�g�̐ؑ֋N���敪���擾
        String l_paramChangeStartDiv = l_priRequest.changeStartDiv;
        
        // �����������N���X�I�u�W�F�N�g�̐���
        WEB3AdminFrontProcessNumberInfoUnit l_processInfoUnit = new WEB3AdminFrontProcessNumberInfoUnit();
        
        // �ϊ��s��R�[�h���t�B�[���h�ɃZ�b�g
        l_convertMarketCode = l_priRequest.convertMarketCode;
        
        // �s��R�[�h���t�B�[���h�ɃZ�b�g
        l_marketCode = l_priRequest.marketCode;
        
        // �ؑ֏��������敪���t�B�[���h�ɃZ�b�g
        l_changeProDiv = l_priRequest.changeProcessDiv;

        // �����^�C�v
        l_strProductType = l_priRequest.productType;
        
        // �t�����g�����V�X�e���敪���擾
        l_frontSysDiv = l_priCommonObj.getFrontSystemDiv( l_convertMarketCode);
        
        // ���z�T�[�o�������擾
        l_priCommonObj.getVitualServerCount(l_priInstCode, l_marketCode, l_frontSysDiv, l_strProductType, l_processInfoUnit);
        
        // �ؑ֏��������敪���S�������������̏ꍇ�A�O���[���������擾����B
        if(l_changeProDiv.equals(WEB3AllOrderChangeDivDef.ALL_ORDER_CHANGE)){
                
            if (l_strProductType.equals(Integer.toString(ProductTypeEnum.EQUITY.intValue())))
            {
                l_priCommonObj.getGrayOrder(l_priInstCode, l_marketCode, l_frontSysDiv, l_processInfoUnit);          
            }
            else if (l_strProductType.equals(Integer.toString(ProductTypeEnum.IFO.intValue())))
            {
            	l_priCommonObj.getIfoGrayOrder(l_priInstCode, l_marketCode, l_frontSysDiv, l_processInfoUnit);
            }
        }
        // �ؑ֎w���f�[�^�������擾
        boolean  l_boolSwitchDiv = getSwitchPointDataCount(l_priInstCode, l_marketCode,
            l_frontSysDiv, l_strProductType, l_processInfoUnit, l_changeProDiv);

        // �ؑ֏��������������N���X�Ɋi�[
        this.createSwitchInfo(l_priRequest.changeProcessDiv, l_processInfoUnit, l_boolSwitchDiv);
        
        // ���ؑ֋N���敪���擾
        l_nowChangeStartDiv = l_processInfoUnit.changeStartDiv;
        
        // ���ؑ֋N���敪�ƁA�p�����[�^�̐ؑ֋N���敪������̏ꍇ��true���i�[
        if(l_nowChangeStartDiv != null && l_nowChangeStartDiv.equals(l_paramChangeStartDiv))
        {
            l_boolret = true;
        }
    
        return l_boolret;
    }


   
    /**
     * ��Q���z�T�[�o�ؑ֊Ǘ��e�[�u������擾�����ؑ֎w���v���n���R�[�h���J�E���g�A�b�v���A�������i�[����B <BR>
     *  <BR>
     * [�p�����[�^] <BR>
     * �ؑ֎w���v�����R�[�h <BR>
     * �����������N���X�I�u�W�F�N�g <BR>
     *  <BR>
     * �P�j�@@���z�T�[�o�ؑ֎w���f�[�^�̌�����Loop�������s���A�ȉ��̏�������A�ؑ֎w���f�[�^���J�E���g�A�b�v����B <BR>
     *  <BR>
     * �@@�P�|�P�j�@@if�i�ؑ֎w���v�����R�[�h.�ؑ֎w�������敪 = "�ʔԏƉ�v��"�j  <BR>
     *  <BR>
     * �@@�@@�@@"�ʔԏƉ�v���F������"�@@���J�E���g�A�b�v�B  <BR>
     *  <BR>
     * �@@�P�|�Q�j�@@if�i�ؑ֎w���v�����R�[�h.�ؑ֎w�������敪 = "�ʒm��s�����v��"�j  <BR>
     *  <BR>
     * �@@�@@�@@"�ʒm��s�����v���F������"�@@���J�E���g�A�b�v�B  <BR>
     *  <BR>
     * �@@�P�|�R�j�@@if�i�ؑ֎w���v�����R�[�h.�ؑ֎w�������敪 = "�ʒm��s�v��"�j  <BR>
     *  <BR>
     * �@@�@@�@@�P�|�R�|�P�j"�ʒm��s�v���F������"�@@���J�E���g�A�b�v�B  <BR>
     *  <BR>
     * �@@�@@�@@�P�|�R�|�Q�j�@@if�i�ؑ֎w���v�����R�[�h.�t�����g����������敪�R�[�h = "����" or "����"�j  <BR>
     *  <BR>
     * �@@�@@�@@�@@�@@"�ʒm�đ��v���i��t�n�j�F������", "�ʒm�đ��v���i���n�j�F������"���J�E���g�A�b�v�B  <BR>
     *  <BR>
     * �@@�P�|�S�j�@@if�i�ؑ֎w���v�����R�[�h.�ؑ֎w�������敪 = "�ʒm�đ��v��" and  <BR>
     * �@@�@@�@@�@@�@@�@@�ؑ֎w���v�����R�[�h.�ʒm��� = "��t�n"�j  <BR>
     *  <BR>
     * �@@�@@�@@"�ʒm�đ��v���i��t�n�j�F������"�@@���J�E���g�A�b�v�B  <BR>
     *  <BR>
     * �@@�P�|�T�j�@@if�i�ؑ֎w���v�����R�[�h.�ؑ֎w�������敪 = "�ʒm�đ��v��" and  <BR>
     * �@@�@@�@@�@@�@@�@@�ؑ֎w���v�����R�[�h.�ʒm��� = "���n"�j  <BR>
     *  <BR>
     * �@@�@@�@@"�ʒm�đ��v���i���n�j�F������"�@@���J�E���g�A�b�v�B  <BR>
     *  <BR>
     * �Q�j�@@���z�T�[�o�ؑ֎w���f�[�^�́h�����ρh������0��ݒ肷��B  <BR>
     *  <BR>
     * �@@�Q�|�P�j�@@�ʔԏƉ�v���F�����ρ@@=�@@�O  <BR>
     *  <BR>
     * �@@�Q�|�Q�j�@@�ʒm��s�����v���F�����ρ@@=�@@�O  <BR>
     *  <BR>
     * �@@�Q�|�R�j�@@�ʒm��s�v���F�����ρ@@=�@@�O  <BR>
     *  <BR>
     * �@@�Q�|�S�j�@@�ʒm�đ��v���i��t�n�j�F�����ρ@@=�@@�O  <BR>
     *  <BR>
     * �@@�Q�|�T�j�@@�ʒm�đ��v���i���n�j�F�����ρ@@=�@@�O  <BR>
     * @@param �ؑ֎w���v�����R�[�h - <BR>
     * ��Q���z�T�[�o�ؑ֊Ǘ��e�[�u������擾�����ؑ֎w���v���n���R�[�h�B<BR>
     * @@param �����������N���X�I�u�W�F�N�g - �����������N���X�I�u�W�F�N�g�B<BR>
     * @@roseuid 42FC6804011F
     */
    private void setSwitchPointReqCount(List l_priReqList, WEB3AdminFrontProcessNumberInfoUnit l_PriProcessInfoUnit) 
    {
        final String STR_METHOD_NAME = " setSwitchPointReqCount(String, String)";

        log.entering(STR_METHOD_NAME);

        // �ʔԏƉ�v���F������   
        int l_intNumberRefRes_noTran = 0;
        // �ʔԏƉ�v���F������   
        int l_intNumberRefRes_tran = 0;
        // �ʒm��s�����v���F������        
        int l_intNoticeAngRelRes_noTran = 0;
        // �ʒm��s�����v���F������        
        int l_intNoticeAngRelRes_tran = 0;
        // �ʒm��s�v���F������
        int l_intNoticeAngRes_noTran = 0;
        // �ʒm��s�v���F������        
        int l_intNoticeAngRes_tran = 0;
        // �ʒm�đ��v���i��t�n�j�F������
        int l_intNoticeReSenRes_recept_noTran = 0;
        // �ʒm�đ��v���i��t�n�j�F������        
        int l_intNoticeReSenRes_recept_tran = 0;
        // �ʒm�đ��v���i���n�j�F������
        int l_intNoticeReSenRes_cont_noTran = 0;
        // �ʒm�đ��v���i���n�j�F������        
        int l_intNoticeReSenRes_cont_tran = 0;
        
        // ���R�[�h�̃T�C�Y���ALoop����
        Iterator l_iteObj = l_priReqList.iterator();
        
        while(l_iteObj.hasNext()){
            
            // VirtualServerChangeRow�I�u�W�F�N�g�̒��o
            VirtualServerChangeRow l_changeRow = (VirtualServerChangeRow)l_iteObj.next();
            
            // �ؑ֎w�������敪���擾
            String l_resDiv = l_changeRow.getChangeReqResDiv();

            //�t�����g����������敪�R�[�h���擾
            String l_strFrontOrderExchangeCode = l_changeRow.getFrontOrderExchangeCode();

            // �ʒm��ʂ��擾
            String l_noticeType = l_changeRow.getNoticeType();            
            
            // �����敪���擾
            String status = l_changeRow.getStatus();

            // �ʔԏƉ�v���F�������������J�E���g�A�b�v�B
            if(l_resDiv.equals(WEB3ChangeReqResDivDef.SERIAL_NUMBER_REFERENCE_REQUEST))
            {
                l_intNumberRefRes_noTran = l_intNumberRefRes_noTran + 1;                
            }
            // �ʒm��s�����v���F�������������J�E���g�A�b�v�B
            else if(l_resDiv.equals(WEB3ChangeReqResDivDef.NOTICE_AGENCY_RELEASE_REQUEST))
            {
                l_intNoticeAngRelRes_noTran = l_intNoticeAngRelRes_noTran + 1;                
            }
            //if�i�ؑ֎w���v�����R�[�h.�ؑ֎w�������敪 = "�ʒm��s�v��"�j        
            else if(l_resDiv.equals(WEB3ChangeReqResDivDef.NOTICE_AGENCY_REQUEST))
            {
                //�ʒm��s�v���F�������������J�E���g�A�b�v�B
                l_intNoticeAngRes_noTran = l_intNoticeAngRes_noTran + 1;
                //if�i�ؑ֎w���v�����R�[�h.�t�����g����������敪�R�[�h = "����" or "����"�j
                //"�ʒm�đ��v���i��t�n�j�F������", "�ʒm�đ��v���i���n�j�F������"���J�E���g�A�b�v�B
                if(WEB3FrontOrderExchangeCodeDef.TOKYO_STOCK_EXCHANGE.equals(l_strFrontOrderExchangeCode)
                    || WEB3FrontOrderExchangeCodeDef.NAGOYA_STOCK_EXCHANGE.equals(l_strFrontOrderExchangeCode))
                {
                    l_intNoticeReSenRes_recept_noTran = l_intNoticeReSenRes_recept_noTran + 1;
                    l_intNoticeReSenRes_cont_noTran = l_intNoticeReSenRes_cont_noTran + 1;
                }
            }
            // �ʒm�đ��v���i��t�n�j�F�������������J�E���g�A�b�v�B
            else if(l_resDiv.equals(WEB3ChangeReqResDivDef.NOTICE_RESEND_REQUEST) && 
            l_noticeType.equals(WEB3NoticeTypeDef.ACCEPT_TYPE))
            {
                l_intNoticeReSenRes_recept_noTran = l_intNoticeReSenRes_recept_noTran + 1;                
            }
            // �ʒm�đ��v���i���n�j�F�������������J�E���g�A�b�v�B
            else if(l_resDiv.equals(WEB3ChangeReqResDivDef.NOTICE_RESEND_REQUEST) && 
            l_noticeType.equals(WEB3NoticeTypeDef.EXECUTED_TYPE))
            {
                l_intNoticeReSenRes_cont_noTran = l_intNoticeReSenRes_cont_noTran + 1;                
            }
        }
        
        // �����������N���X�I�u�W�F�N�g�Ɋe�������i�[
        l_PriProcessInfoUnit.finProcessNumber1 = Integer.toString(l_intNumberRefRes_tran);
        l_PriProcessInfoUnit.finProcessNumber2 = Integer.toString(l_intNoticeAngRelRes_tran);
        l_PriProcessInfoUnit.finProcessNumber3 = Integer.toString(l_intNoticeAngRes_tran);
        l_PriProcessInfoUnit.finProcessNumber4 = Integer.toString(l_intNoticeReSenRes_recept_tran);
        l_PriProcessInfoUnit.finProcessNumber5 = Integer.toString(l_intNoticeReSenRes_cont_tran);
        l_PriProcessInfoUnit.nonProcessNumber1 = Integer.toString(l_intNumberRefRes_noTran);
        l_PriProcessInfoUnit.nonProcessNumber2 = Integer.toString(l_intNoticeAngRelRes_noTran);
        l_PriProcessInfoUnit.nonProcessNumber3 = Integer.toString(l_intNoticeAngRes_noTran);
        l_PriProcessInfoUnit.nonProcessNumber4 = Integer.toString(l_intNoticeReSenRes_recept_noTran);
        l_PriProcessInfoUnit.nonProcessNumber5 = Integer.toString(l_intNoticeReSenRes_cont_noTran);
    }
   
    /**
     * ��Q���z�T�[�o�ؑ֊Ǘ��e�[�u������擾�����ؑ֎w�������n���R�[�h���J�E���g�A�b�v���A�������i�[����B <BR>
     * <BR>
     * [�p�����[�^] <BR>
     * �ؑ֎w���������R�[�h <BR>
     * �����������N���X�I�u�W�F�N�g <BR>
     * �ؑ֏��������敪 <BR>
     * <BR>
     * �P�j�@@���z�T�[�o�ؑ֎w���f�[�^�̌�����Loop�������s���A�ȉ��̏�������A�ؑ֎w���f�[�^���J�E���g�A�b�v����B <BR>
     * <BR>
     * �@@�P�|�P�j�@@if�i�ؑ֎w���������R�[�h.�ؑ֎w�������敪 = "�ʔԏƉ��" and <BR>
     * �@@�@@�@@�@@�@@�@@�ؑ֎w���������R�[�h.�����敪 = "������"�j <BR>
     * <BR>
     * �@@�@@�@@"�ʔԏƉ���F������"�@@���J�E���g�A�b�v�B <BR>
     * <BR>
     * �@@�P�|�Q�j�@@if�i�ؑ֎w���������R�[�h.�ؑ֎w�������敪 = "�ʒm��s��������" and <BR>
     * �@@�@@�@@�@@�@@�@@�ؑ֎w���������R�[�h.�����敪 = "������"�j <BR>
     * <BR>
     * �@@�@@�@@"�ʒm��s���������F������"�@@���J�E���g�A�b�v�B <BR>
     * <BR>
     * �@@�P�|�R�j�@@if�i�ؑ֎w���������R�[�h.�ؑ֎w�������敪 = "�ʒm��s����" and <BR>
     * �@@�@@�@@�@@�@@�@@�ؑ֎w���������R�[�h.�����敪 = "������"�j <BR>
     * <BR>
     * �@@�@@�@@�P�|�R�|�P�j�@@"�ʒm��s�����F������"�@@���J�E���g�A�b�v�B <BR>
     * <BR>
     * �@@�@@�@@�P�|�R�|�Q�j�@@if�i�ؑ֎w���������R�[�h.�t�����g����������敪�R�[�h = "����" or "����"�j <BR>
     * <BR>
     * �@@�@@�@@�@@�@@"�ʒm�đ������i��t�n�j�F������", "�ʒm�đ������i���n�j�F������"���J�E���g�A�b�v�B <BR>
     * <BR>
     * �@@�P�|�S�j�@@if�i�ؑ֎w���������R�[�h.�ؑ֎w�������敪 = "�ʒm�đ�����" and <BR>
     * �@@�@@�@@�@@�@@�@@�ؑ֎w���������R�[�h.�ʒm��� = "��t�n" and <BR>
     * �@@�@@�@@�@@�@@�@@�ؑ֎w���������R�[�h.�����敪 = "������"�j <BR>
     * <BR>
     * �@@�@@�@@"�ʒm�đ������i��t�n�j�F������"�@@���J�E���g�A�b�v�B <BR>
     * <BR>
     * �@@�P�|�T�j�@@if�i�ؑ֎w���������R�[�h.�ؑ֎w�������敪 = "�ʒm�đ�����" and <BR>
     * �@@�@@�@@�@@�@@�@@�ؑ֎w���������R�[�h.�ʒm��� = "���n" and <BR>
     * �@@�@@�@@�@@�@@�@@�ؑ֎w���������R�[�h.�����敪 = "������"�j <BR>
     * <BR>
     * �@@�@@�@@"�ʒm�đ������i���n�j�F������"�@@���J�E���g�A�b�v�B <BR>
     * <BR>
     * <BR>
     * �Q�j�@@�����������N���X�I�u�W�F�N�g.���z�T�[�o���ƎZ�o�����ؑ֎w���f�[�^�̊e�����ό������疢�����������Z�o����B <BR>
     * <BR>
     * �@@�Q�|�P�j�u�ؑ֏��������敪��"�ʔԏƉ������"�������ό��� > 0�v�̏ꍇ�@@ <BR>
     * �@@�@@�@@�@@�@@�@@�ʔԏƉ�v���F���������� = �����������N���X�I�u�W�F�N�g.���z�T�[�o�� - �ʔԏƉ���F������ <BR>
     * �@@�@@�@@�@@�@@�@@ <BR>
     * �@@�@@�@@�@@�@@�@@��L�ȊO�̏ꍇ <BR>
     * �@@�@@�@@�@@�@@�@@�ʔԏƉ�v���F���������� = 0 <BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�ʒm��s�����v���F���������� = �����������N���X�I�u�W�F�N�g.���z�T�[�o�� - �ʒm��s���������F������ <BR>
     * <BR>
     * �@@�Q�|�R�j�@@�ʒm��s�v���F���������� = �����������N���X�I�u�W�F�N�g.���z�T�[�o�� - �ʒm��s�����F������ <BR>
     * <BR>
     * �@@�Q�|�S�j�@@�ʒm�đ��v���i��t�n�j�F���������� = �����������N���X�I�u�W�F�N�g.���z�T�[�o�� - �ʒm�đ������i��t�n�j <BR>
     * <BR>
�@@   *   �Q�|�T�j�@@�ʒm�đ��v���i���n�j�F���������� = �����������N���X�I�u�W�F�N�g.���z�T�[�o�� - �ʒm�đ������i���n�j<BR>
     * @@param �ؑ֎w���������R�[�h - <BR>
     * ��Q���z�T�[�o�ؑ֊Ǘ��e�[�u������擾���������n���R�[�h�B<BR>
     * @@param �����������N���X�I�u�W�F�N�g - �����������N���X�I�u�W�F�N�g�B<BR>
     * @@param �ؑ֏��������敪 - �ؑ֏��������敪�B<BR>
     * @@roseuid 42FC625D010F
     */
    private void setSwitchPointResCount(List l_priResList, WEB3AdminFrontProcessNumberInfoUnit l_PriProcessInfoUnit, String l_PrichangeProcessDiv) 
    {
        final String STR_METHOD_NAME = " setSwitchPointResCount(String, String)";

        log.entering(STR_METHOD_NAME);

        // �ʔԏƉ���F������   
        int l_intNumberRefRes_noTran = 0;
        // �ʔԏƉ���F������   
        int l_intNumberRefRes_tran = 0;
        // �ʒm��s���������F������        
        int l_intNoticeAngRelRes_noTran = 0;
        // �ʒm��s���������F������        
        int l_intNoticeAngRelRes_tran = 0;
        // �ʒm��s�����F������
        int l_intNoticeAngRes_noTran = 0;
        // �ʒm��s�����F������        
        int l_intNoticeAngRes_tran = 0;
        // �ʒm�đ������i��t�n�j�F������
        int l_intNoticeReSenRes_recept_noTran = 0;
        // �ʒm�đ������i��t�n�j�F������        
        int l_intNoticeReSenRes_recept_tran = 0;
        // �ʒm�đ������i���n�j�F������
        int l_intNoticeReSenRes_cont_noTran = 0;
        // �ʒm�đ������i���n�j�F������        
        int l_intNoticeReSenRes_cont_tran = 0;

        // ���z�T�[�o����
        int l_intVirServerNonTran = Integer.parseInt(l_PriProcessInfoUnit.virtualServerQuantity);
        
        // ���R�[�h�̃T�C�Y���ALoop����
        Iterator l_iteObj = l_priResList.iterator();
        
        while(l_iteObj.hasNext()){
            
            // VirtualServerChangeRow�I�u�W�F�N�g�̒��o
            VirtualServerChangeRow l_changeRow = (VirtualServerChangeRow)l_iteObj.next();
            
            // �ؑ֎w�������敪���擾
            String l_resDiv = l_changeRow.getChangeReqResDiv();

            //�t�����g����������敪�R�[�h���擾
            String l_strFrontOrderExchangeCode = l_changeRow.getFrontOrderExchangeCode();

            // �ʒm��ʂ��擾
            String l_noticeType = l_changeRow.getNoticeType();            
            
            // �����敪���擾
            String status = l_changeRow.getStatus();

            // �ʔԏƉ�v���F�����ό������J�E���g�A�b�v�B
            if(l_resDiv.equals(WEB3ChangeReqResDivDef.SERIAL_NUMBER_REFERENCE_RESPONSE) && status.equals(WEB3FrontOrderStatusDef.SENDED))
            {
                l_intNumberRefRes_tran = l_intNumberRefRes_tran + 1;                
            }
            // �ʒm��s�����v���F�����ό������J�E���g�A�b�v�B
            else if(l_resDiv.equals(WEB3ChangeReqResDivDef.NOTICE_AGENCY_RELEASE_RESPONSE) && status.equals(WEB3FrontOrderStatusDef.SENDED))
            {
                l_intNoticeAngRelRes_tran = l_intNoticeAngRelRes_tran + 1;                
            }
            //if�i�ؑ֎w���������R�[�h.�ؑ֎w�������敪 = "�ʒm��s����" and
            //�ؑ֎w���������R�[�h.�����敪 = "������"�j
            else if(l_resDiv.equals(WEB3ChangeReqResDivDef.NOTICE_AGENCY_RESPONSE) && status.equals(WEB3FrontOrderStatusDef.SENDED))
            {
                //�ʒm��s�����F�����ό������J�E���g�A�b�v�B
                l_intNoticeAngRes_tran = l_intNoticeAngRes_tran + 1;
                //if�i�ؑ֎w���������R�[�h.�t�����g����������敪�R�[�h = "����" or "����"�j
                //"�ʒm�đ������i��t�n�j�F������", "�ʒm�đ������i���n�j�F������"���J�E���g�A�b�v�B
                if(WEB3FrontOrderExchangeCodeDef.TOKYO_STOCK_EXCHANGE.equals(l_strFrontOrderExchangeCode)
                    || WEB3FrontOrderExchangeCodeDef.NAGOYA_STOCK_EXCHANGE.equals(l_strFrontOrderExchangeCode))
                {
                    l_intNoticeReSenRes_recept_tran = l_intNoticeReSenRes_recept_tran + 1;
                    l_intNoticeReSenRes_cont_tran = l_intNoticeReSenRes_cont_tran + 1;
                }
            }
            // �ʒm�đ��v���i��t�n�j�F�����ό������J�E���g�A�b�v�B
            else if(l_resDiv.equals(WEB3ChangeReqResDivDef.NOTICE_RESEND_RESPONSE) && 
            l_noticeType.equals(WEB3NoticeTypeDef.ACCEPT_TYPE) && status.equals(WEB3FrontOrderStatusDef.SENDED))
            {
                l_intNoticeReSenRes_recept_tran = l_intNoticeReSenRes_recept_tran + 1;                
            }
            // �ʒm�đ��v���i���n�j�F�����ό������J�E���g�A�b�v�B
            else if(l_resDiv.equals(WEB3ChangeReqResDivDef.NOTICE_RESEND_RESPONSE) && 
            l_noticeType.equals(WEB3NoticeTypeDef.EXECUTED_TYPE) && status.equals(WEB3FrontOrderStatusDef.SENDED))
            {
                l_intNoticeReSenRes_cont_tran = l_intNoticeReSenRes_cont_tran + 1;                
            }
        }
        // �ʔԏƉ�v��:����������
        // �u�ʔԏƉ�������������ό��� > 0�v�̏ꍇ�̂݁A�J�E���g�B�S�������������ł�0��ݒ�B
        if(l_PrichangeProcessDiv.equals(WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE) && (l_intNumberRefRes_tran > 0))
        {
            l_intNumberRefRes_noTran = l_intVirServerNonTran - l_intNumberRefRes_tran;
        }
        else
        {
            l_intNumberRefRes_noTran = 0;
        }
        // �ʒm��s�����v��:����������
        l_intNoticeAngRelRes_noTran = l_intVirServerNonTran - l_intNoticeAngRelRes_tran;
        // �ʒm��s�v���F����������
        l_intNoticeAngRes_noTran = l_intVirServerNonTran - l_intNoticeAngRes_tran;
        // �ʒm�đ��v���i��t�n�j�F����������
        l_intNoticeReSenRes_recept_noTran = l_intVirServerNonTran - l_intNoticeReSenRes_recept_tran;
        // �ʒm�đ��v���i���n�j�F����������
        l_intNoticeReSenRes_cont_noTran =  l_intVirServerNonTran - l_intNoticeReSenRes_cont_tran;
        
        // �����������N���X�I�u�W�F�N�g�Ɋe�������i�[
        l_PriProcessInfoUnit.finProcessNumber1 = Integer.toString(l_intNumberRefRes_tran);
        l_PriProcessInfoUnit.finProcessNumber2 = Integer.toString(l_intNoticeAngRelRes_tran);
        l_PriProcessInfoUnit.finProcessNumber3 = Integer.toString(l_intNoticeAngRes_tran);
        l_PriProcessInfoUnit.finProcessNumber4 = Integer.toString(l_intNoticeReSenRes_recept_tran);
        l_PriProcessInfoUnit.finProcessNumber5 = Integer.toString(l_intNoticeReSenRes_cont_tran);
        l_PriProcessInfoUnit.nonProcessNumber1 = Integer.toString(l_intNumberRefRes_noTran);
        l_PriProcessInfoUnit.nonProcessNumber2 = Integer.toString(l_intNoticeAngRelRes_noTran);
        l_PriProcessInfoUnit.nonProcessNumber3 = Integer.toString(l_intNoticeAngRes_noTran);
        l_PriProcessInfoUnit.nonProcessNumber4 = Integer.toString(l_intNoticeReSenRes_recept_noTran);
        l_PriProcessInfoUnit.nonProcessNumber5 = Integer.toString(l_intNoticeReSenRes_cont_noTran);
    }

}
@
