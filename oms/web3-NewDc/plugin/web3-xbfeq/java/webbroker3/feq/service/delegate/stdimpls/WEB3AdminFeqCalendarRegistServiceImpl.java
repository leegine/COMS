head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.39.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqCalendarRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������J�����_�[�o�^�T�[�r�XImpl(WEB3AdminFeqCalendarRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/26 �A�C��(���u) �V�K�쐬
                   2005/08/03 ����(���u) ���r���[  
Revesion History : 2007/01/16 ꎉ� (���u) �d�l�ύXNo.336��Ή�
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.define.WEB3FeqDateDivDef;
import webbroker3.feq.message.WEB3AdminFeqCalendarRegistCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqCalendarRegistCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqCalendarRegistConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqCalendarRegistConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqCalendarRegistInputRequest;
import webbroker3.feq.message.WEB3AdminFeqCalendarRegistInputResponse;
import webbroker3.feq.message.WEB3AdminFeqCalendarSearchCondInputRequest;
import webbroker3.feq.message.WEB3AdminFeqCalendarSearchCondInputResponse;
import webbroker3.feq.message.WEB3AdminFeqLocalCalendarUnit;
import webbroker3.feq.service.delegate.WEB3AdminFeqCalendarRegistService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeFeqBranchMarketDealtCond;
import webbroker3.gentrade.data.FeqCalendarDao;
import webbroker3.gentrade.data.FeqCalendarParams;
import webbroker3.gentrade.data.FeqCalendarRow;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�O�������J�����_�[�o�^�T�[�r�XImpl)<BR>
 * �O�������J�����_�[�o�^�T�[�r�X�����N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminFeqCalendarRegistServiceImpl implements WEB3AdminFeqCalendarRegistService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqCalendarRegistServiceImpl.class);
    
    /**
     * @@roseuid 42CE39EF0213
     */
    public WEB3AdminFeqCalendarRegistServiceImpl() 
    {
     
    }
    
    /**
     * �O�������J�����_�[�o�^�T�[�r�X�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���R�[������B<BR>
     * <BR>
     *   get�����������͉��()<BR>
     *   get���͉��()<BR>
     *   validate�o�^()<BR>
     *   submit�o�^()<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 42107C88013C
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("���N�G�X�g�����w��(null)�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminFeqCalendarSearchCondInputRequest)
        {
            //get�����������͉��()
            l_response = 
                this.getQueryCondInputScreen((WEB3AdminFeqCalendarSearchCondInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqCalendarRegistInputRequest)
        {
            //get���͉��()
            l_response = 
                this.getInputScreen((WEB3AdminFeqCalendarRegistInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqCalendarRegistConfirmRequest)
        {
            //validate�o�^()
            l_response = 
                this.validateRegist((WEB3AdminFeqCalendarRegistConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqCalendarRegistCompleteRequest)
        {
            //submit�o�^()
            l_response = 
                this.submitRegist((WEB3AdminFeqCalendarRegistCompleteRequest)l_request);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�����������͉��)<BR>
     * �J�����_�[�����������͉�ʂɕK�v�ȃf�[�^���擾����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�ǁj�J�����_�[�o�^�jget�����������͉�ʁv �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqCalendarSearchCondInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 42107F790350
     */
    protected WEB3AdminFeqCalendarSearchCondInputResponse getQueryCondInputScreen(
        WEB3AdminFeqCalendarSearchCondInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getQueryCondInputScreen(WEB3AdminFeqCalendarSearchCondInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            String l_strMessage = "�Ǘ��҂̃��O�C����񂪑��݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        //1.2 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_CALENDAR_MANAGE, 
            false);//WEB3BaseException

        //get�戵�\�s��
        //[����]
        // �،���ЁF �Ǘ���.get�،���ЃR�[�h()�̖߂�l
        // �����^�C�v�F�@@ProductTypeEnum.�O������
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        String[] l_strPossibleMarkets = WEB3GentradeFeqBranchMarketDealtCond.getHandlingPossibleMarket(
            l_strInstitutionCode, ProductTypeEnum.FOREIGN_EQUITY);
        
        //1.4 ArrayList()
        ArrayList l_arrayList = new ArrayList();
        
        //1.5 �擾�����s��I�u�W�F�N�g����Loop����
        int l_intCount = 0; 

    	if(l_strPossibleMarkets == null || l_strPossibleMarkets.length == 0)
    	{
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�e�[�u���ɊY������f�[�^������܂���B");
    	}

        l_intCount = l_strPossibleMarkets.length;

        for (int i = 0; i < l_intCount; i++)
        {
            //1.5.1 add(arg0 : Object)
            l_arrayList.add(l_strPossibleMarkets[i]);
        }
        
        //1.6 toArray( )
        String[] l_strMarketCodes = new String[l_arrayList.size()];
        l_arrayList.toArray(l_strMarketCodes);
        
        //1.7 createResponse( )
        WEB3AdminFeqCalendarSearchCondInputResponse l_response = 
            (WEB3AdminFeqCalendarSearchCondInputResponse)l_request.createResponse();
        
        //1.8 �v���p�e�B�Z�b�g
        l_response.marketList = l_strMarketCodes;
                
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���͉��)<BR>
     * �J�����_�[�o�^���͉�ʂɕK�v�ȃf�[�^���擾����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�ǁj�J�����_�[�o�^�jget���͉�ʁv �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqCalendarRegistInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 42107F79036F
     */
    protected WEB3AdminFeqCalendarRegistInputResponse getInputScreen(
        WEB3AdminFeqCalendarRegistInputRequest l_request) throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = 
            " getInputScreen(WEB3AdminFeqCalendarRegistInputRequest )";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("���N�G�X�g�����w��(null)�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }
        
        //1.1 validate( )
        l_request.validate();//WEB3BaseException
        
        //1,2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            String l_strMessage = "�Ǘ��҂̃��O�C����񂪑��݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage);
        }

        //1.3 validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_CALENDAR_MANAGE, false);//WEB3BaseException
        
        //1.4 create�擾�����f�[�^�R���e�i(String, String, String)
        Object[] l_objContainers = this.createGetContainer(
            l_admin.getInstitutionCode(),
            l_request.marketCode,
            l_request.period);
        List l_lisRecord = null;            
        try
        {
            //1.5 getDefaultProcessor( )
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            //1.6 doFindAllQuery(Row�^�C�v : RowType, Where : String, orderBy : String, condition : String, ���X�g : Object[])
            l_lisRecord = l_queryProcessor.doFindAllQuery(
                FeqCalendarRow.TYPE,
                "institution_code = ? and market_code= ? and biz_date >= ? and biz_date <= ? ",
                "biz_date",
                null,
                l_objContainers);
        }
        catch (DataFindException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.7 ArrayList( )
        ArrayList l_arrayList = new ArrayList();
        
        //1.8 �擾�������R�[�h����Loop����
        int l_intCount = 0; 
        if (l_lisRecord != null)
        {
            l_intCount = l_lisRecord.size();
        }
        for (int i = 0; i < l_intCount; i++)
        {
            //1.8.1 ���n�J�����_�[���( )
            WEB3AdminFeqLocalCalendarUnit l_feqLocalCalendarUnit = new WEB3AdminFeqLocalCalendarUnit();
            
            //1.8.2 �v���p�e�B�Z�b�g
            //���t = �O���C�O�s��J�����_�[�e�[�u��.���t
            //���t�敪 = �O���C�O�s��J�����_�[�e�[�u��.�c�Ɠ��敪
            //�X�V�� = �O���C�O�s��J�����_�[�e�[�u��.�X�V���t
            FeqCalendarRow l_feqCalendarRow = (FeqCalendarRow)l_lisRecord.get(i);
            l_feqLocalCalendarUnit.bizDate = WEB3DateUtility.toDay(l_feqCalendarRow.getBizDate());
            l_feqLocalCalendarUnit.bizDateDiv = l_feqCalendarRow.getBizDateType();
            l_feqLocalCalendarUnit.updateDate = l_feqCalendarRow.getLastUpdatedTimestamp();
                
            //1.8.3 add(arg0 : Object)
            l_arrayList.add(l_feqLocalCalendarUnit);
        }
        
        //1.9  toArray( )
        WEB3AdminFeqLocalCalendarUnit[] l_feqLocalCalendarUnits = 
            new WEB3AdminFeqLocalCalendarUnit[l_arrayList.size()];
        l_arrayList.toArray(l_feqLocalCalendarUnits);

        //1.10 createResponse()
        WEB3AdminFeqCalendarRegistInputResponse l_response = 
            (WEB3AdminFeqCalendarRegistInputResponse)l_request.createResponse();
        if (l_response == null)
        {
            log.debug("���X�|���X��null�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���X�|���X��null�ł��B");
        }    

        //1.11 �v���p�e�B�Z�b�g
        l_response.feqLocalCalendarUnit = l_feqLocalCalendarUnits;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�o�^)<BR>
     * �J�����_�[�o�^�̊m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�ǁj�J�����_�[�o�^�jvalidate�o�^�v �Q��<BR>
     * ========================================================<BR>
     *  �V�[�P���X�}(�u(�O�������T�[�r�X���f��) /<BR>
     * �i�ǁj�J�����_�[�o�^�v(�i�ǁj�J�����_�[�o�^�jvalidate�o�^)<BR>
     * �@@�@@:  1.4 <BR> 
     * �@@�@@���N�G�X�g.�J�����_�[���ꗗ == null or<BR> 
     * �@@�@@���N�G�X�g.�J�����_�[���ꗗ.length() == 0<BR> 
     * �@@�@@�̏ꍇ�A��O���X���[����B<BR> 
     * <BR> 
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:  BUSINESS_ERROR_02156<BR>
     * ==========================================================<BR>
     * ==========================================================<BR>
     *  �V�[�P���X�}(�u(�O�������T�[�r�X���f��) /<BR>
     * �i�ǁj�J�����_�[�o�^�v(�i�ǁj�J�����_�[�o�^�jvalidate�o�^)<BR>
     * �@@�@@:  1.5.1 <BR> 
     * �@@�@@�P�j�@@null�`�F�b�N<BR> 
     *    ���t == null or ���t�敪 == null�̏ꍇ�A<BR> 
     *    ��O���X���[����B<BR> 
     * <BR> 
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:  BUSINESS_ERROR_02157<BR>
     * ==========================================================<BR>
     * ==========================================================<BR>
     *  �V�[�P���X�}(�u(�O�������T�[�r�X���f��) /<BR>
     * �i�ǁj�J�����_�[�o�^�v(�i�ǁj�J�����_�[�o�^�jvalidate�o�^)<BR>
     * �@@�@@:  1.5.1 <BR> 
     * �@@�@@�Q�j���t�敪�`�F�b�N<BR> 
     * �@@�@@���t�敪���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR> 
     * �@@�@@0�F��c�Ɠ�<BR> 
     * �@@�@@1�F�c�Ɠ�<BR> 
     * <BR> 
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:  BUSINESS_ERROR_02158<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqCalendarRegistConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 42107F79037F
     */
    protected WEB3AdminFeqCalendarRegistConfirmResponse validateRegist(
        WEB3AdminFeqCalendarRegistConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " validateRegist(WEB3AdminFeqCalendarRegistConfirmRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("���N�G�X�g�����w��(null)�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }
        
        //1.1 validate( )
        l_request.validate();//WEB3BaseException
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            String l_strMessage = "�Ǘ��҂̃��O�C����񂪑��݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_CALENDAR_MANAGE, true);//WEB3BaseException
        
        //1.4 ���N�G�X�g.�J�����_�[���ꗗ == null or
        //  ���N�G�X�g.�J�����_�[���ꗗ.length() == 0
        //  �̏ꍇ�A��O���X���[����B
        if (l_request.feqLocalCalendarUnit == null || l_request.feqLocalCalendarUnit.length == 0)
        {
            String l_strMessage = "�J�����_�[���ꗗ�����݂��܂���B"; 
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02156,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //1.5 �J�����_�[���ꗗ�̊e�v�f�ɂ���Loop����
        int l_intCount = l_request.feqLocalCalendarUnit.length;
        for (int i = 0; i < l_intCount; i++)
        {
            //1.5.1 �ȉ��̃`�F�b�N���s���B
            //�P�jnull�`�F�b�N
            //   ���t == null or ���t�敪 == null�̏ꍇ�A
            //   ��O���X���[����B
            if (l_request.feqLocalCalendarUnit[i].bizDate == null
                || WEB3StringTypeUtility.isEmpty(l_request.feqLocalCalendarUnit[i].bizDateDiv))
            {
                String l_strMessage = "���t�����͓��t�敪��null�ł��B"; 
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02157,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
            
            //�Q�j���t�敪�`�F�b�N
            //   ���t�敪���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
            //   0�F��c�Ɠ�
            //   1�F�c�Ɠ�
            if (!WEB3FeqDateDivDef.NO_BIZ_DATE.equals(l_request.feqLocalCalendarUnit[i].bizDateDiv)
                && !WEB3FeqDateDivDef.BIZ_DATE.equals(l_request.feqLocalCalendarUnit[i].bizDateDiv))
            {
                String l_strMessage = "���t�敪������`�̒l�ł��B�u" 
                    + l_request.feqLocalCalendarUnit[i].bizDateDiv + "�v"; 
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02158,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
        }
        
        //1.6 createResponse()
        WEB3AdminFeqCalendarRegistConfirmResponse l_response = 
            (WEB3AdminFeqCalendarRegistConfirmResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit�o�^)<BR>
     * �J�����_�[�̓o�^�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�ǁj�J�����_�[�o�^�jsubmit�o�^�v �Q��<BR>
     * ========================================================<BR>
     *  �V�[�P���X�}(�u(�O�������T�[�r�X���f��) /<BR>
     * �i�ǁj�J�����_�[�o�^�v(�i�ǁj�J�����_�[�o�^�jsubmit�o�^)<BR>
     * �@@�@@:  1.4 <BR> 
     * �@@�@@���N�G�X�g.�J�����_�[���ꗗ == null or<BR> 
     * �@@�@@���N�G�X�g.�J�����_�[���ꗗ.length() == 0<BR> 
     * �@@�@@�̏ꍇ�A��O���X���[����B<BR> 
     * <BR> 
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:  BUSINESS_ERROR_02156<BR>
     * ==========================================================<BR>
     * ==========================================================<BR>
     *  �V�[�P���X�}(�u(�O�������T�[�r�X���f��) /�i�ǁj�J�����_�[�o�^�v(�i�ǁj�J�����_�[�o�^�jsubmit�o�^)<BR>
     * �@@�@@:  1.6.1 <BR> 
     * �@@�@@�P�j�@@null�`�F�b�N<BR> 
     *    ���t == null or ���t�敪 == null�̏ꍇ�A<BR> 
     *    ��O���X���[����B<BR> 
     * <BR> 
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:  BUSINESS_ERROR_02157<BR>
     * ==========================================================<BR>
     * ==========================================================<BR>
     *  �V�[�P���X�}(�u(�O�������T�[�r�X���f��) /�i�ǁj�J�����_�[�o�^�v(�i�ǁj�J�����_�[�o�^�jsubmit�o�^)<BR>
     * �@@�@@:  1.6.1<BR> 
     * �@@�@@�Q�j���t�敪�`�F�b�N<BR> 
     * �@@�@@���t�敪���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR> 
     * �@@�@@0�F��c�Ɠ�<BR> 
     * �@@�@@1�F�c�Ɠ�<BR> 
     * <BR> 
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:  BUSINESS_ERROR_02158<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqCalendarRegistCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 42107F79039E
     */
    protected WEB3AdminFeqCalendarRegistCompleteResponse submitRegist(
        WEB3AdminFeqCalendarRegistCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " submitRegist(WEB3AdminFeqCalendarRegistCompleteRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("���N�G�X�g�����w��(null)�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }

        //1.1 validate( )
        l_request.validate();//WEB3BaseException
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            String l_strMessage = "�Ǘ��҂̃��O�C����񂪑��݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_CALENDAR_MANAGE, true);//WEB3BaseException
        
        //1.4 ���N�G�X�g.�J�����_�[���ꗗ == null or
        //  ���N�G�X�g.�J�����_�[���ꗗ.length() == 0
        //  �̏ꍇ�A��O���X���[����B
        if (l_request.feqLocalCalendarUnit == null || l_request.feqLocalCalendarUnit.length == 0)
        {
            String l_strMessage = "�J�����_�[���ꗗ�����݂��܂���B"; 
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02156,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //1.5 validate����p�X���[�h(String)
        l_admin.validateTradingPassword(l_request.password);//WEB3BaseException

        //1.6 �J�����_�[���ꗗ�̊e�v�f�ɂ���Loop����
        int l_intCount = l_request.feqLocalCalendarUnit.length;
        for (int i = 0; i < l_intCount; i++)
        {
            //1.6.1 �ȉ��̃`�F�b�N���s���B
            //�P�jnull�`�F�b�N
            //   ���t == null or ���t�敪 == null�̏ꍇ�A
            //   ��O���X���[����B
            if (l_request.feqLocalCalendarUnit[i].bizDate == null
                || WEB3StringTypeUtility.isEmpty(l_request.feqLocalCalendarUnit[i].bizDateDiv))
            {
                String l_strMessage = "���t�����͓��t�敪��null�ł��B"; 
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02157,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
            
            //�Q�j���t�敪�`�F�b�N
            //   ���t�敪���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
            //   0�F��c�Ɠ�
            //   1�F�c�Ɠ�
            if (!WEB3FeqDateDivDef.NO_BIZ_DATE.equals(l_request.feqLocalCalendarUnit[i].bizDateDiv)
                && !WEB3FeqDateDivDef.BIZ_DATE.equals(l_request.feqLocalCalendarUnit[i].bizDateDiv))
            {
                String l_strMessage = "���t�敪������`�̒l�ł��B�u" 
                    + l_request.feqLocalCalendarUnit[i].bizDateDiv + "�v"; 
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02158,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
            
            //1.6.2 update�O���C�O�s��J�����_�[(�Ǘ���, String, ���n�J�����_�[���)
            this.updateFeqForeignMarketCalendar(
                l_admin,
                l_request.marketCode,
                l_request.feqLocalCalendarUnit[i]);//WEB3BaseException
        }

        //1.7 createResponse()
        WEB3AdminFeqCalendarRegistCompleteResponse l_response = 
            (WEB3AdminFeqCalendarRegistCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (create�擾�����f�[�^�R���e�i)<BR>
     * ���N�G�X�g�f�[�^����A�擾�����̃f�[�^�R���e�i�𐶐�����B<BR>
     * <BR>
     * �P�j���ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�،���ЃR�[�h<BR>
     * <BR>
     * ����.�،���ЃR�[�h��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �R�j�s��R�[�h<BR>
     * <BR>
     * ����.���N�G�X�g�f�[�^.�s��R�[�h��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �S�j�N����������<BR>
     * <BR>
     * ����.���N�G�X�g�f�[�^.�N���̍ŏ��̓�(1��)�ƍŌ�̓����Z�o���A<BR>
     * ArrayList�ɒǉ�����B<BR>
     * �����ԕ����́A�����l�i00:00:00�j<BR>
     * <BR>
     * �T�jArrayLis��..toArray()�̖߂�l��ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     * 
     * @@param l_strYearMonth - (�N��)<BR>
     * �N���iYYYYMM�j<BR>
     * 
     * @@return Object[]
     * @@throws WEB3BaseException
     * @@roseuid 421C68BB0175
     */
    protected Object[] createGetContainer(
        String l_strInstitutionCode, 
        String l_strMarketCode, 
        String l_strYearMonth) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " createGetContainer(String , String , String )";
        log.entering(STR_METHOD_NAME);
        
        //�P�j���ArrayList�𐶐�����B
        ArrayList l_arrayList = new ArrayList();
        
        //�Q�j�،���ЃR�[�h        
        //����.�،���ЃR�[�h��ArrayList�ɒǉ�����B
        l_arrayList.add(l_strInstitutionCode);
        
        //�R�j�s��R�[�h        
        //����.���N�G�X�g�f�[�^.�s��R�[�h��ArrayList�ɒǉ�����B
        l_arrayList.add(l_strMarketCode);
        
        //�S�j�N����������        
        //����.���N�G�X�g�f�[�^.�N���̍ŏ��̓�(1��)�ƍŌ�̓����Z�o���A
        //ArrayList�ɒǉ�����B
        //�����ԕ����́A�����l�i00:00:00�j
        Date l_datYearMonth = WEB3DateUtility.getDate(l_strYearMonth, "yyyyMM");
        if (l_datYearMonth == null)
        {
            String l_strMessage = "�N�������t�Ƃ��ėL�肦�Ȃ��l�ł�.�u" + l_strYearMonth + "�v";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        Calendar l_canlendar = Calendar.getInstance();
        l_canlendar.setTime(l_datYearMonth);
        l_canlendar.set(Calendar.DAY_OF_MONTH, 1);
        l_arrayList.add(l_canlendar.getTime());
        l_canlendar.add(Calendar.MONTH, 1);
        l_canlendar.add(Calendar.DAY_OF_MONTH, -1);
        l_arrayList.add(l_canlendar.getTime());
                
        //�T�jArrayLis��..toArray()�̖߂�l��ԋp����B
        Object[] l_objects = new Object[l_arrayList.size()];
        l_arrayList.toArray(l_objects);
        
        log.exiting(STR_METHOD_NAME);
        return l_objects;
    }
    
    /**
     * (update�O���C�O�s��J�����_�[)<BR>
     * �O���C�O�s��J�����_�[�e�[�u�����X�V����B<BR>
     * <BR>
     * �P�j�ȉ��̏����ŊO���C�O�s��J�����_�[�e�[�u�����烌�R�[�h���擾����B<BR>
     * <BR>
     * [����]<BR>
     * �،���ЃR�[�h�F ����.�Ǘ���.get�،���ЃR�[�h()�̖߂�l<BR>
     * �s��R�[�h�F ����.�s��R�[�h<BR>
     * ���t�F ����.�J�����_�[���.���t<BR>
     * <BR>
     * �Q�j���R�[�h���擾�ł����ꍇ�A�ȉ��̏������s���B<BR>
     * <BR>
     * �Q�|�P�j�P�j�Ŏ擾�����s�I�u�W�F�N�g�̃N���[���𐶐�����B<BR>
     * <BR>
     * �Q�|�Q�j�s�I�u�W�F�N�g�̃N���[���Ƀv���p�e�B���Z�b�g����B<BR>
     * <BR>
     *    �c�Ɠ��敪�F ����.�J�����_�[���.���t�敪<BR>
     *    �X�V�҃R�[�h�F ����.�Ǘ���.get�Ǘ��҃R�[�h()�̖߂�l<BR>
     *    �X�V���t�F ���ݎ���<BR>
     * <BR>
     * �Q�|�R�j�O���C�O�s��J�����_�[�e�[�u�����X�V����B<BR>
     * <BR>
     *    WEB3DataAccessUtility.updateRow()���R�[������B<BR>
     * <BR>
     *    [����]<BR>
     *    �O���C�O�s��J�����_�[�s�I�u�W�F�N�g�̃N���[��<BR>
     * <BR>
     * �R�j���R�[�h���擾�ł��Ȃ������ꍇ�A�ȉ��̏������s���B<BR>
     * <BR>
     * �R�|�P�j��̊O���C�O�s��J�����_�[�s�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �R�|�Q�j�s�I�u�W�F�N�g�Ƀv���p�e�B���Z�b�g����B<BR>
     * <BR>
     *    �،���ЃR�[�h�F ����.�Ǘ���.get�،���ЃR�[�h()�̖߂�l<BR>
     *    �s��R�[�h�F ����.�s��R�[�h<BR>
     *    ���t�F ����.�J�����_�[���.���t<BR>
     *    �c�Ɠ��敪�F ����.�J�����_�[���.���t�敪<BR>
     *    �X�V�҃R�[�h�F ����.�Ǘ���.get�Ǘ��҃R�[�h()�̖߂�l<BR>
     *    �쐬���t�F ���ݎ���<BR>
     *    �X�V���t�F ���ݎ���<BR>
     * <BR>
     * �R�|�R�j�O���C�O�s��J�����_�[�s��o�^����B<BR>
     * <BR>
     *    WEB3DataAccessUtility.insertRow()���R�[������B<BR>
     * <BR>
     *    [����]<BR>
     *    �O���C�O�s��J�����_�[�s�I�u�W�F�N�g<BR>
     * @@param l_admin - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g<BR>
     * 
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     * 
     * @@param l_calendarInfo - (�J�����_�[���)<BR>
     * ���n�J�����_�[���I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     * @@roseuid 421C8106017C
     */
    protected void updateFeqForeignMarketCalendar(
        WEB3Administrator l_admin, 
        String l_strMarketCode, 
        WEB3AdminFeqLocalCalendarUnit l_calendarInfo) throws WEB3BaseException   
    {
        final String STR_METHOD_NAME = 
            " updateFeqForeignMarketCalendar(WEB3Administrator , String , WEB3AdminFeqLocalCalendarUnit )";
        log.entering(STR_METHOD_NAME);
        
        if (l_admin == null)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("�p�����[�^�u�Ǘ��ҁv�����w��(null)�ł��B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�u�Ǘ��ҁv�����w��(null)�ł��B");
        }
        if (l_calendarInfo == null)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("�p�����[�^���u���n�J�����_�[���v���w��(null)�ł��B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^���u���n�J�����_�[���v���w��(null)�ł��B");
        }
        
        //�P�j�ȉ��̏����ŊO���C�O�s��J�����_�[�e�[�u�����烌�R�[�h���擾����B
        //[����]
        //�،���ЃR�[�h�F ����.�Ǘ���.get�،���ЃR�[�h()�̖߂�l
        //�s��R�[�h�F ����.�s��R�[�h
        //���t�F ����.�J�����_�[���.���t
        try
        {
            FeqCalendarRow l_reqCalendarRow = 
                FeqCalendarDao.findRowByInstitutionCodeMarketCodeBizDate(
                    l_admin.getInstitutionCode(),
                    l_strMarketCode,
                    new Timestamp(l_calendarInfo.bizDate.getTime()));
        
            Timestamp l_tsCurrentTime = GtlUtils.getSystemTimestamp();
            
            //�Q�j���R�[�h���擾�ł����ꍇ�A�ȉ��̏������s���B
            if (l_reqCalendarRow != null)
            {
                //�Q�|�P�j�P�j�Ŏ擾�����s�I�u�W�F�N�g�̃N���[���𐶐�����B
                FeqCalendarParams l_params = 
                    new FeqCalendarParams(l_reqCalendarRow);           
            
                //�Q�|�Q�j�s�I�u�W�F�N�g�̃N���[���Ƀv���p�e�B���Z�b�g����B
                //   �c�Ɠ��敪�F ����.�J�����_�[���.���t�敪
                //   �X�V�҃R�[�h�F ����.�Ǘ���.get�Ǘ��҃R�[�h()�̖߂�l
                //   �X�V���t�F ���ݎ���
                l_params.setBizDateType(l_calendarInfo.bizDateDiv);
                l_params.setLastUpdater(l_admin.getAdministratorCode());
                l_params.setLastUpdatedTimestamp(l_tsCurrentTime);
            
                //�Q�|�R�j�O���C�O�s��J�����_�[�e�[�u�����X�V����B
                //   WEB3DataAccessUtility.updateRow()���R�[������B
                //   [����]
                //   �O���C�O�s��J�����_�[�s�I�u�W�F�N�g�̃N���[��
                WEB3DataAccessUtility.updateRow(l_params);
            }
            //�R�j���R�[�h���擾�ł��Ȃ������ꍇ�A�ȉ��̏������s���B
            else
            {
                //�R�|�P�j��̊O���C�O�s��J�����_�[�s�I�u�W�F�N�g�𐶐�����B
                FeqCalendarParams l_params = new FeqCalendarParams();           
            
                //�R�|�Q�j�s�I�u�W�F�N�g�Ƀv���p�e�B���Z�b�g����B
            
                //   �،���ЃR�[�h�F ����.�Ǘ���.get�،���ЃR�[�h()�̖߂�l
                //   �s��R�[�h�F ����.�s��R�[�h
                //   ���t�F ����.�J�����_�[���.���t
                //   �c�Ɠ��敪�F ����.�J�����_�[���.���t�敪
                //   �X�V�҃R�[�h�F ����.�Ǘ���.get�Ǘ��҃R�[�h()�̖߂�l
                //   �쐬���t�F ���ݎ���
                //   �X�V���t�F ���ݎ���
                l_params.setInstitutionCode(l_admin.getInstitutionCode());
                l_params.setMarketCode(l_strMarketCode);
                l_params.setBizDate(l_calendarInfo.bizDate);
                l_params.setBizDateType(l_calendarInfo.bizDateDiv);
                l_params.setLastUpdater(l_admin.getAdministratorCode());
                l_params.setCreatedTimestamp(l_tsCurrentTime);
                l_params.setLastUpdatedTimestamp(l_tsCurrentTime);
            
                //�R�|�R�j�O���C�O�s��J�����_�[�s��o�^����B
                //   WEB3DataAccessUtility.insertRow()���R�[������B
                //   [����]
                //   �O���C�O�s��J�����_�[�s�I�u�W�F�N�g
                WEB3DataAccessUtility.insertRow(l_params);
            }
        }
        catch (DataFindException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
}
@
