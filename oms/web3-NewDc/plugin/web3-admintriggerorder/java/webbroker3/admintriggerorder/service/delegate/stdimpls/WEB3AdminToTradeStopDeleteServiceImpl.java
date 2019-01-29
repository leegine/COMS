head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToTradeStopDeleteServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�戵��~�폜�T�[�r�XImpl(WEB3AdminToTradeStopDeleteServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04�@@�A����(���u) �V�K�쐬
*/
package webbroker3.admintriggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;

import webbroker3.admintriggerorder.WEB3AdminToDataManager;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopDelCompleteRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopDelCompleteResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopDelConfirmRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopDelConfirmResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopInfoUnit;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToTradeStopDeleteService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.triggerorder.base.data.TriggerOrderStopDao;
import webbroker3.triggerorder.base.data.TriggerOrderStopParams;
import webbroker3.triggerorder.base.data.TriggerOrderStopRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (�g���K�[�����Ǘ��ҁE�戵��~�폜�T�[�r�XImpl)<BR>
 * �g���K�[�����Ǘ��ҁE�戵��~�폜�T�[�r�X�����N���X<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public class WEB3AdminToTradeStopDeleteServiceImpl extends WEB3ClientRequestService 
    implements WEB3AdminToTradeStopDeleteService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminToTradeStopDeleteServiceImpl.class);

    /**
     * @@roseuid 4430DB3E032C
     */
    public WEB3AdminToTradeStopDeleteServiceImpl() 
    {
     
    }
    
    /**
     * �戵��~�폜�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * �ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * ���g���K�[�����Ǘ��ҁE�戵��~�폜�m�F���N�G�X�g�̏ꍇ<BR>
     * �@@this.validate�폜()���R�[������B<BR>
     * <BR>
     * ���g���K�[�����Ǘ��ҁE�戵��~�폜�������N�G�X�g�̏ꍇ<BR>
     * �@@this.submit�폜()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4410D2DC01E2
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l�s���B");
        }

        WEB3GenResponse l_response = null;
        // ���g���K�[�����Ǘ��ҁE�戵��~�폜�m�F���N�G�X�g�̏ꍇ
        // �@@this.validate�폜()���R�[������B
        if (l_request instanceof WEB3AdminToTradeStopDelConfirmRequest)
        {
            l_response = this.validateDelete((WEB3AdminToTradeStopDelConfirmRequest) l_request);
        }
        // ���g���K�[�����Ǘ��ҁE�戵��~�폜�������N�G�X�g�̏ꍇ
        //�@@this.submit�폜()���R�[������B
        else if (l_request instanceof WEB3AdminToTradeStopDelCompleteRequest)
        {
            l_response = this.submitDelete((WEB3AdminToTradeStopDelCompleteRequest) l_request);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�폜)<BR>
     * �戵��~�폜�m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�g���K�[�����Ǘ��ҁE�戵��~�폜�T�[�r�X�jvalidate�폜�v�Q�ƁB<BR>
     * ============================================================== <BR>
     *    �V�[�P���X�}�F�u�i�g���K�[�����Ǘ��ҁE�戵��~�폜�T�[�r�X�jvalidate�폜�v<BR>
     *    ��̈ʒu�F<BR>
     *    1.4 (*)���ꎷ�s�����戵��~Row�̎擾<BR>
     *    ���N�G�X�g�f�[�^.���ꎷ�s�����戵��~ID��<BR>
     *    �Y��������ꎷ�s�����戵��~Row���擾����B<BR>
     *    ��Dao�N���X�̃��\�b�h���g�p�B<BR>
     *    �擾�ł��Ȃ������ꍇ�A�Y���f�[�^�Ȃ��̃V�X�e���G���[���X���[����B<BR>
     *    class : WEB3SystemLayerException<BR>
     *    tag : SYSTEM_ERROR_80005<BR>
     * ============================================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �g���K�[�����Ǘ��ҁE�戵��~�폜�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminToTradeStopDelConfirmResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4410D2BF009A
     */
    protected WEB3AdminToTradeStopDelConfirmResponse validateDelete(WEB3AdminToTradeStopDelConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDelete(WEB3AdminToTradeStopDelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.TRIGGER_ORDER_TRADING_STOP, true);

        //1.4 (*)���ꎷ�s�����戵��~Row�̎擾
        //���N�G�X�g�f�[�^.���ꎷ�s�����戵��~ID��
        //�Y��������ꎷ�s�����戵��~Row���擾����B
        //��Dao�N���X�̃��\�b�h���g�p�B
        //�擾�ł��Ȃ������ꍇ�A�Y���f�[�^�Ȃ��̃V�X�e���G���[���X���[����B
        TriggerOrderStopRow l_row = null;
        try
        {
            l_row = TriggerOrderStopDao.findRowByTriggerOrderStopId(Long.parseLong(l_request.triggerTradeStopId));
            if (l_row == null)
            {
                log.debug("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "�e�[�u���ɊY������f�[�^������܂���B");
            }
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.5 get�،����( )
        Institution l_institution = l_admin.getInstitution();
        
        //1.6 create�戵��~���ꗗ(�،����, ���ꎷ�s�����戵��~Row[])
        TriggerOrderStopRow[] l_rows = new TriggerOrderStopRow[1];
        l_rows[0] = l_row;
        WEB3AdminToTradeStopInfoUnit[] l_units = WEB3AdminToDataManager.createTradeStopInfoList(l_institution, l_rows);
        
        //1.7 createResponse( )
        WEB3AdminToTradeStopDelConfirmResponse l_response = 
            (WEB3AdminToTradeStopDelConfirmResponse) l_request.createResponse();
        
        //1.8 (*)�v���p�e�B�Z�b�g
        //�戵��~���    ���@@create�戵��~���ꗗ()�̖߂�l��0�Ԗڂ̗v�f
        l_response.tradeStopInfoUnit = l_units[0];
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit�폜)<BR>
     * �戵��~�폜�����������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�g���K�[�����Ǘ��ҁE�戵��~�폜�T�[�r�X�jsubmit�폜�v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �g���K�[�����Ǘ��ҁE�戵��~�폜�������N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminToTradeStopDelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4410D2BF00F8
     */
    protected WEB3AdminToTradeStopDelCompleteResponse submitDelete(WEB3AdminToTradeStopDelCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitDelete(WEB3AdminToTradeStopDelCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.TRIGGER_ORDER_TRADING_STOP, true);

        //1.4 validate����p�X���[�h(�p�X���[�h : String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.5 delete�戵��~(�Ǘ���, long)
        this.deleteTradeStop(l_admin, Long.parseLong(l_request.triggerTradeStopId));
        
        //1.6 createResponse( )
        WEB3AdminToTradeStopDelCompleteResponse l_response = 
            (WEB3AdminToTradeStopDelCompleteResponse) l_request.createResponse();
        
        //���X�|���X������A���ݎ������Z�b�g����B
        l_response.currentTime = GtlUtils.getSystemTimestamp();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (delete�戵��~)<BR>
     * ������ID�ɊY��������ꎷ�s�����戵��~<BR>
     * �e�[�u���̃��R�[�h���폜����B<BR>
     * ���_���폜<BR>
     * <BR>
     * �P�j�@@�ȉ��̏����ɊY�����郌�R�[�h��update����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���ꎷ�s�����戵��~ID =<BR>
     * �@@�@@�@@�p�����[�^.���ꎷ�s�����戵��~ID<BR>
     * <BR>
     * �@@[�X�V���e]<BR>
     * �@@�@@DB�X�V�d�l<BR>
     * �@@�@@�u�戵��~�폜_���ꎷ�s�����戵��~�e�[�u���d�l.xls�v�Q�ƁB<BR>
     * @@param l_administrator - (�Ǘ���)<BR>
     * �Ǘ���<BR>
     * @@param l_lngTriggerOrderStopId - (���ꎷ�s�����戵��~ID)<BR>
     * ���ꎷ�s�����戵��~ID<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4417D2FE023A
     */
    protected void deleteTradeStop(WEB3Administrator l_administrator, long l_lngTriggerOrderStopId) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " deleteTradeStop(WEB3Administrator, long)";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�@@�ȉ��̏����ɊY�����郌�R�[�h��update����B
        try
        {
            TriggerOrderStopRow l_row = 
                (TriggerOrderStopRow) TriggerOrderStopDao.findRowByTriggerOrderStopId(l_lngTriggerOrderStopId);
            
            if (l_row == null)
            {
                log.debug("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "�e�[�u���ɊY������f�[�^������܂���B");
            }
            
            TriggerOrderStopParams l_params = new TriggerOrderStopParams(l_row);
            //�폜�t���O = 1�F�폜��
            l_params.setDeleteFlag(BooleanEnum.TRUE.intValue());
            //�X�V�҃R�[�h = �Ǘ���.�Ǘ��҃R�[�h
            l_params.setLastUpdater(l_administrator.getAdministratorCode());
            //�X�V���t = ���ݎ���
            l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            Processors.getDefaultProcessor().doUpdateQuery(l_params);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
