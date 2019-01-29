head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.38.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiApplicationRequiredService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �\���v�T�[�r�X(WEB3SrvRegiApplicationRequiredService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 ���w�� (���u) �V�K�쐬
Revesion History : 2007/06/05 �Ј���(���u) �d�l�ύX���f��No.252
*/

package webbroker3.srvregi;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ConditionsValueDivDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.srvregi.data.SrvRegiSetupParams;
import webbroker3.srvregi.data.SrvRegiSetupRow;
import webbroker3.util.WEB3LogUtility;


/**
 * (�\���v�T�[�r�X)<BR>
 * �\���v�T�[�r�X�G���e�B�e�B�N���X<BR>
 *                     
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3SrvRegiApplicationRequiredService implements BusinessObject 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SrvRegiApplicationRequiredService.class);
    
    /**
     * (�\���v�T�[�r�X�s)<BR>
     */
    private SrvRegiSetupParams appliRequiredSrvParams;
    
    /**
     * (�\���v�T�[�r�X)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * ���I�u�W�F�N�g�𐶐���<BR>�A
     * ����.�\���v�T�[�r�XRow��this.�\���v�T�[�r�X�s�ɐݒ肷��B<BR>
     * @@param l_appliRequiredSrvRow - (�\���v�T�[�r�XRow)<BR>
     * @@throws WEB3BaseException
     * @@roseuid 412F0431014F
     */
    protected WEB3SrvRegiApplicationRequiredService(SrvRegiSetupRow l_appliRequiredSrvRow) throws WEB3BaseException 
    {
        this.appliRequiredSrvParams = new SrvRegiSetupParams(l_appliRequiredSrvRow);
    }
    
    /**
     * �igetDataSourceObject�̎����j<BR>
     * <BR>
     * this.�\���v�T�[�r�X�s��ԋp����B<BR>
     * @@return Object
     * @@roseuid 413309690342
     */
    public Object getDataSourceObject() 
    {
        return this.appliRequiredSrvParams;
    }
    
    /**
     * �X�V�s�pParams�̃N���[���s�𐶐����ĕԋp����B<BR>
     * <BR>
     * �@@this.�\���v�T�[�r�X�s���R�s�[���āA�������e��<BR>
     * �ʃC���X�^���X���쐬����iclone�j�B<BR> 
     * �쐬�����R�s�[�����g��this.�\���v�T�[�r�X�s�ɃZ�b�g����B<BR>
     * @@roseuid 413309690390
     */
    public void createForUpdateParams() 
    {
        final String STR_METHOD_NAME = " createForUpdateParams()";
        log.entering(STR_METHOD_NAME);
        
        SrvRegiSetupParams l_srvRegiSetupParams = new SrvRegiSetupParams(this.appliRequiredSrvParams);
        this.appliRequiredSrvParams = l_srvRegiSetupParams;
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (set�E�v)<BR>
     * �E�v�̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�\���v�T�[�r�X�s��null�łȂ��ꍇ�A<BR>
     * �@@this.�\���v�T�[�r�X�s.set�E�v()���R�[������B<BR>
     * [����]<BR>
     * �@@�E�v=����.�E�v<BR>
     * @@param l_strSummary - (�E�v)<BR>
     * @@roseuid 412EEBB402F5
     */
    public void setSummary(String l_strSummary) 
    {
        final String STR_METHOD_NAME = " setSummary(String)";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams != null)
        {
            this.appliRequiredSrvParams.setSummary(l_strSummary);
        }       
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�E�v)<BR>
     * �E�v��Ԃ��B<BR>
     * <BR>
     * 1) this.�\���v�T�[�r�X�s��null�̏ꍇ�Anull��Ԃ��B<BR>
     * <BR>
     * 2) this.�\���v�T�[�r�X�s��null�łȂ��ꍇ�A<BR>
     * �@@this.�\���v�T�[�r�X�s.get�E�v()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 412EEBB40305
     */
    public String getSummary() 
    {
        final String STR_METHOD_NAME = " getSummary()";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {   
            String l_strSummary = this.appliRequiredSrvParams.getSummary();
            
            log.exiting(STR_METHOD_NAME);
            return l_strSummary;
        }
        
    }
    
    /**
     * (set���I�ݒ�)<BR>
     * ���I�ݒ�̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�\���v�T�[�r�X�s��null�łȂ��ꍇ�A<BR>
     * �@@this.�\���v�T�[�r�X�s.set���I�ݒ�()���R�[������B<BR>
     * [����]<BR>
     * �@@���I�ݒ�=����.���I�ݒ�<BR>
     * @@param l_strLotDiv - (���I�ݒ�)<BR>
     * @@roseuid 412EEBB40314
     */
    public void setLotDiv(String l_strLotDiv) 
    {
        final String STR_METHOD_NAME = " setLotDiv(String)";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams != null)
        {
            this.appliRequiredSrvParams.setLotDiv(l_strLotDiv);
        }
        
        log.exiting(STR_METHOD_NAME);             
    }
    
    /**
     * (get���I�ݒ�)<BR>
     * ���I�ݒ��Ԃ��B<BR>
     * <BR>
     * 1) this.�\���v�T�[�r�X�s��null�̏ꍇ�Anull��Ԃ��B<BR>
     * <BR>
     * 2) this.�\���v�T�[�r�X�s��null�łȂ��ꍇ�A<BR>
     * �@@this.�\���v�T�[�r�X�s.get���I�ݒ�()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 412EEBB40324
     */
    public String getLotDiv() 
    {
        final String STR_METHOD_NAME = " getLotDiv()";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            String l_strLotDiv = this.appliRequiredSrvParams.getLotDiv();
            
            log.exiting(STR_METHOD_NAME);
            return l_strLotDiv;
        }
    }
    
    /**
     * (set���p���ԋ敪)<BR>
     * ���p���ԋ敪�̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�\���v�T�[�r�X�s��null�łȂ��ꍇ�A<BR>
     * �@@this.�\���v�T�[�r�X�s.set���p���ԋ敪()���R�[������B<BR>
     * [����]<BR>
     * �@@���p���ԋ敪=����.���p���ԋ敪<BR>
     * @@param l_strTrialPeriodDiv - (���p���ԋ敪)<BR>
     * @@roseuid 412EEBB40334
     */
    public void setTrialPeriodDiv(String l_strTrialPeriodDiv) 
    {
        final String STR_METHOD_NAME = " setTrialPeriodDiv(String)";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams != null)
        {
            this.appliRequiredSrvParams.setTrialPeriodDiv(l_strTrialPeriodDiv);
        } 
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (get���p���ԋ敪)<BR>
     * ���p���ԋ敪��Ԃ��B<BR>
     * <BR>
     * 1) this.�\���v�T�[�r�X�s��null�̏ꍇ�Anull��Ԃ��B<BR>
     * <BR>
     * 2) this.�\���v�T�[�r�X�s��null�łȂ��ꍇ�A<BR>
     * �@@this.�\���v�T�[�r�X�s.get���p���ԋ敪()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 412EEBB40343
     */
    public String getTrialPeriodDiv() 
    {
        final String STR_METHOD_NAME = " getTrialPeriodDiv()";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            String l_strTrialPeriodDiv = this.appliRequiredSrvParams.getTrialPeriodDiv();
            
            log.exiting(STR_METHOD_NAME);
            return l_strTrialPeriodDiv;
        }
    }
    
    /**
     * (set���p����)<BR>
     * ���p���Ԃ̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�\���v�T�[�r�X�s��null�łȂ��ꍇ�A<BR>
     * �@@this.�\���v�T�[�r�X�s.set���p����()���R�[������B<BR>
     * [����]<BR>
     * �@@���p����=����.���p����<BR>
     * @@param l_trialPeriod - (���p����)<BR>
     * @@roseuid 412EEBB40353
     */
    public void setTrialPeriod(Integer l_trialPeriod) 
    {
        final String STR_METHOD_NAME = " setTrialPeriod(Integer)";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams != null)
        {
            this.appliRequiredSrvParams.setTrialPeriod(l_trialPeriod);
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (get���p����)<BR>
     * ���p���Ԃ�Ԃ��B<BR>
     * <BR>
     * 1) this.�\���v�T�[�r�X�s��null�̏ꍇ�Anull��Ԃ��B<BR>
     * <BR>
     * 2) this.�\���v�T�[�r�X�s��null�łȂ��ꍇ�A<BR>
     * �@@this.�\���v�T�[�r�X�s.get���p����()�̖߂�l��Ԃ��B<BR>
     * @@return Integer
     * @@roseuid 412EEBB40372
     */
    public Integer getTrialPeriod() 
    {
        final String STR_METHOD_NAME = " getTrialPeriod()";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            if(this.appliRequiredSrvParams.getTrialPeriodIsNull())
            {
                return null;
            }
            Integer l_trialPeriod = new Integer(this.appliRequiredSrvParams.getTrialPeriod());
            
            log.exiting(STR_METHOD_NAME);
            return l_trialPeriod;
        }
    }
    
    /**
     * (set�\���\���ԁi���j)<BR>
     * �\���\���ԁi���j�̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�\���v�T�[�r�X�s��null�łȂ��ꍇ�A<BR>
     * �@@this.�\���v�T�[�r�X�s.set�\���\���ԁi���j()���R�[������B<BR>
     * [����]<BR>
     * �@@�\���\���ԁi���j=����.�\���\���ԁi���j<BR>
     * @@param l_appliDateFrom - (�\���\���ԁi���j)<BR>
     * @@roseuid 412EEBB40382
     */
    public void setAppliDateFrom(Integer l_appliDateFrom) 
    {
        final String STR_METHOD_NAME = " setAppliDateFrom(Integer)";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams != null)
        {
            this.appliRequiredSrvParams.setAppliDateFrom(l_appliDateFrom);
        } 
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (get�\���\���ԁi���j)<BR>
     * �\���\���ԁi���j��Ԃ��B<BR>
     * <BR>
     * 1) this.�\���v�T�[�r�X�s��null�̏ꍇ�Anull��Ԃ��B<BR>
     * <BR>
     * 2) this.�\���v�T�[�r�X�s��null�łȂ��ꍇ�A<BR>
     * �@@this.�\���v�T�[�r�X�s.get�\���\���ԁi���j()�̖߂�l��Ԃ��B<BR>
     * @@return Integer
     * @@roseuid 412EEBB40391
     */
    public Integer getAppliDateFrom() 
    {
        final String STR_METHOD_NAME = " getAppliDateFrom()";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            if(this.appliRequiredSrvParams.getAppliDateFromIsNull())
            {
                return null;
            }
            Integer l_appliDateFrom= new Integer(this.appliRequiredSrvParams.getAppliDateFrom());
            
            log.exiting(STR_METHOD_NAME);
            return l_appliDateFrom;
        }
    }
    
    /**
     * (set�\���\���ԁi���j)<BR>
     * �\���\���ԁi���j�̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�\���v�T�[�r�X�s��null�łȂ��ꍇ�A<BR>
     * �@@this.�\���v�T�[�r�X�s.set�\���\���ԁi���j()���R�[������B<BR>
     * [����]<BR>
     * �@@�\���\���ԁi���j=����.�\���\���ԁi���j<BR>
     * @@param l_appliDateTo - (�\���\���ԁi���j)<BR>
     * @@roseuid 412EEBB403A1
     */
    public void setAppliDateTo(Integer l_appliDateTo) 
    {
        final String STR_METHOD_NAME = " setAppliDateTo(Integer)";
        log.entering(STR_METHOD_NAME);
         
        if (this.appliRequiredSrvParams != null)
        {
            this.appliRequiredSrvParams.setAppliDateTo(l_appliDateTo);
        }
        
        log.exiting(STR_METHOD_NAME);        
    }
    
    /**
     * (get�\���\���ԁi���j)<BR>
     * �\���\���ԁi���j��Ԃ��B<BR>
     * <BR>
     * 1) this.�\���v�T�[�r�X�s��null�̏ꍇ�Anull��Ԃ��B<BR>
     * <BR>
     * 2) this.�\���v�T�[�r�X�s��null�łȂ��ꍇ�A<BR>
     * �@@this.�\���v�T�[�r�X�s.get�\���\���ԁi���j()�̖߂�l��Ԃ��B<BR>
     * @@return Integer
     * @@roseuid 412EEBB403B1
     */
    public Integer getAppliDateTo() 
    {
        final String STR_METHOD_NAME = " getAppliDateTo()";
        log.entering(STR_METHOD_NAME);
         
        if (this.appliRequiredSrvParams == null)
        {
            log.exiting(STR_METHOD_NAME); 
            return null;
        }
        else
        {
            if(this.appliRequiredSrvParams.getAppliDateToIsNull())
            {
                return null;
            }
            Integer l_appliDateTo = new Integer(this.appliRequiredSrvParams.getAppliDateTo());
            
            log.exiting(STR_METHOD_NAME); 
            return l_appliDateTo;
        }
    }
    
    /**
     * (set�T�[�r�X���e)<BR>
     * �T�[�r�X���e�̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�\���v�T�[�r�X�s��null�łȂ��ꍇ�A<BR>
     * �@@this.�\���v�T�[�r�X�s.set�T�[�r�X���e()���R�[������B<BR>
     * [����]<BR>
     * �@@�T�[�r�X���e=����.�T�[�r�X���e<BR>
     * @@param l_strSrvContents - (�T�[�r�X���e)<BR>
     * @@roseuid 412EEBB403C0
     */
    public void setSrvContents(String l_strSrvContents) 
    {
        final String STR_METHOD_NAME = " setSrvContents(String)";
        log.entering(STR_METHOD_NAME);
         
        if (this.appliRequiredSrvParams != null)
        {
            this.appliRequiredSrvParams.setSrvContents(l_strSrvContents);
        } 
        
        log.exiting(STR_METHOD_NAME);             
    }
    
    /**
     * (get�T�[�r�X���e)<BR>
     * �T�[�r�X���e��Ԃ��B<BR>
     * <BR>
     * 1) this.�\���v�T�[�r�X�s��null�̏ꍇ�Anull��Ԃ��B<BR>
     * <BR>
     * 2) this.�\���v�T�[�r�X�s��null�łȂ��ꍇ�A<BR>
     * �@@this.�\���v�T�[�r�X�s.get�T�[�r�X���e()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 412EEBB50017
     */
    public String getSrvContents() 
    {
        final String STR_METHOD_NAME = " getSrvContents()";
        log.entering(STR_METHOD_NAME);
         
        if (this.appliRequiredSrvParams == null)
        {
            log.exiting(STR_METHOD_NAME); 
            return null;
        }
        else
        {
            String l_strSrvContents = this.appliRequiredSrvParams.getSrvContents();
            
            log.exiting(STR_METHOD_NAME); 
            return l_strSrvContents;
        }        
    }
    
    /**
     * (set�T�[�r�X����URL)<BR>
     * �T�[�r�X����URL�̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�\���v�T�[�r�X�s��null�łȂ��ꍇ�A<BR>
     * �@@this.�\���v�T�[�r�X�s.set�T�[�r�X����URL()���R�[������B<BR>
     * [����]<BR>
     * �@@�@@�T�[�r�X����URL=����.�T�[�r�X����URL<BR>
     * @@param l_strSrvExplanUrl - (�T�[�r�X����URL)<BR>
     * @@roseuid 412EEBB50026
     */
    public void setSrvExplanUrl(String l_strSrvExplanUrl) 
    {
        final String STR_METHOD_NAME = " setSrvExplanUrl(String)";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams != null)
        {
            this.appliRequiredSrvParams.setSrvExplanUrl(l_strSrvExplanUrl);
        }
        
        log.exiting(STR_METHOD_NAME);        
    }
    
    /**
     * (get�T�[�r�X����URL)<BR>
     * �T�[�r�X����URL��Ԃ��B<BR>
     * <BR>
     * 1) this.�\���v�T�[�r�X�s��null�̏ꍇ�Anull��Ԃ��B<BR>
     * <BR>
     * 2) this.�\���v�T�[�r�X�s��null�łȂ��ꍇ�A<BR>
     * �@@this.�\���v�T�[�r�X�s.get�T�[�r�X����URL()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 412EEBB50036
     */
    public String getSrvExplanUrl() 
    {
        final String STR_METHOD_NAME = " getSrvExplanUrl()";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams == null)
        {
            log.exiting(STR_METHOD_NAME);  
            return null;
        }
        else
        {
            String l_strSrvExplanUrl = this.appliRequiredSrvParams.getSrvExplanUrl();
            
            log.exiting(STR_METHOD_NAME);
            return l_strSrvExplanUrl;
        }
    }
    
    /**
     * (set�m�F���[��)<BR>
     * �m�F���[���̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�\���v�T�[�r�X�s��null�łȂ��ꍇ�A<BR>
     * �@@this.�\���v�T�[�r�X�s.set�m�F���[��()���R�[������B<BR>
     * [����]<BR>
     * �@@�m�F���[��=����.�m�F���[��<BR>
     * @@param l_strStartMailDiv - (�m�F���[��)<BR>
     * @@roseuid 412EEBB50046
     */
    public void setStartMailDiv(String l_strStartMailDiv) 
    {
        final String STR_METHOD_NAME = " setStartMailDiv(String)";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams != null)
        {
            this.appliRequiredSrvParams.setStartMailDiv(l_strStartMailDiv);
        }
        
        log.exiting(STR_METHOD_NAME);      
    }
    
    /**
     * (get�m�F���[��)<BR>
     * �m�F���[����Ԃ��B<BR>
     * <BR>
     * 1) this.�\���v�T�[�r�X�s��null�̏ꍇ�Anull��Ԃ��B<BR>
     * <BR>
     * 2) this.�\���v�T�[�r�X�s��null�łȂ��ꍇ�A<BR>
     * �@@this.�\���v�T�[�r�X�s.get�m�F���[��()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 412EEBB50065
     */
    public String getStartMailDiv() 
    {
        final String STR_METHOD_NAME = " getStartMailDiv()";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            String l_strStartMailDiv = this.appliRequiredSrvParams.getStartMailDiv();
            
            log.exiting(STR_METHOD_NAME);
            return l_strStartMailDiv;
        }
    }
    
    /**
     * (set�_��������[��)<BR>
     * �_��������[���̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�\���v�T�[�r�X�s��null�łȂ��ꍇ�A<BR>
     * �@@this.�\���v�T�[�r�X�s.set�_��������[��()���R�[������B<BR>
     * [����]<BR>
     * �@@�@@�_��������[��=����.�_��������[��<BR>
     * @@param l_strEndMailDiv - (�_��������[��)<BR>
     * @@roseuid 412EEBB50074
     */
    public void setEndMailDiv(String l_strEndMailDiv) 
    {
        final String STR_METHOD_NAME = " setEndMailDiv(String)";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams != null)
        {
            this.appliRequiredSrvParams.setEndMailDiv(l_strEndMailDiv);
        } 
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (get�_��������[��)<BR>
     * �_��������[����Ԃ��B<BR>
     * <BR>
     * 1) this.�\���v�T�[�r�X�s��null�̏ꍇ�Anull��Ԃ��B<BR>
     * <BR>
     * 2) this.�\���v�T�[�r�X�s��null�łȂ��ꍇ�A<BR>
     * �@@this.�\���v�T�[�r�X�s.get�_��������[��()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 412EEBB50084
     */
    public String getEndMailDiv() 
    {
        final String STR_METHOD_NAME = " getEndMailDiv()";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            String l_strEndMailDiv = this.appliRequiredSrvParams.getEndMailDiv();
            
            log.exiting(STR_METHOD_NAME);
            return l_strEndMailDiv;
        }
    }
    
    /**
     * (set���[�����M��)<BR>
     * ���[�����M���j�̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�\���v�T�[�r�X�s��null�łȂ��ꍇ�A<BR>
     * �@@this.�\���v�T�[�r�X�s.set���[�����M��()���R�[������B<BR>
     * [����]<BR>
     * �@@�@@���[�����M��=����.���[�����M��<BR>
     * @@param l_sendMailInterval - (���[�����M��)<BR>
     * @@roseuid 412EEBB50094
     */
    public void setSendMailInterval(Integer l_sendMailInterval) 
    {
        final String STR_METHOD_NAME = " setSendMailInterval(Integer)";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams != null)
        {
            this.appliRequiredSrvParams.setSendMailInterval(l_sendMailInterval);
        }
        
        log.exiting(STR_METHOD_NAME);         
    }
    
    /**
     * (get���[�����M��)<BR>
     * ���[�����M����Ԃ��B<BR>
     * <BR>
     * 1) this.�\���v�T�[�r�X�s��null�̏ꍇ�Anull��Ԃ��B<BR>
     * <BR>
     * 2) this.�\���v�T�[�r�X�s��null�łȂ��ꍇ�A<BR>
     * �@@this.�\���v�T�[�r�X�s.get���[�����M��()�̖߂�l��Ԃ��B<BR>
     * @@return Integer
     * @@roseuid 412EEBB500B3
     */
    public Integer getSendMailInterval() 
    {
        final String STR_METHOD_NAME = " getSendMailInterval()";
        log.entering(STR_METHOD_NAME);
        if (this.appliRequiredSrvParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            if(this.appliRequiredSrvParams.getSendMailIntervalIsNull())
            {
                return null;
            }
            
            Integer l_sendMailInterval = new Integer(this.appliRequiredSrvParams.getSendMailInterval());
            
            log.exiting(STR_METHOD_NAME);
            return l_sendMailInterval;
        }
    }
    
    /**
     * (set�d�q�������ݒ�敪)<BR>
     * �d�q�������ݒ�敪�̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�\���v�T�[�r�X�s��null�łȂ��ꍇ<BR>
     *  1-1) ����.�d�q�������ݒ�敪=true�̏ꍇ<BR>
     * �@@this.�\���v�T�[�r�X�s.set�d�q�������ݒ�敪()���R�[������B<BR>
     * [����]<BR>
     * �@@�@@�d�q�������ݒ�敪="�L"<BR>
     * <BR>
     *  1-2) ����.�d�q�������ݒ�敪=false�̏ꍇ<BR>
     * �@@this.�\���v�T�[�r�X�s.set�d�q�������ݒ�敪()���R�[������B<BR>
     * [����]<BR>
     * �@@�@@�d�q�������ݒ�敪="��"<BR>
     * @@param l_blnElectricIssueDiv - (�d�q�������ݒ�敪)<BR>
     * @@roseuid 412EEBB500C3
     */
    public void setElectricIssueDiv(boolean l_blnElectricIssueDiv) 
    {
        final String STR_METHOD_NAME = " setElectricIssueDiv(boolean)";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams != null)
        {
            if (l_blnElectricIssueDiv == true)
            {
                this.appliRequiredSrvParams.setElectricIssueDiv(WEB3ConditionsValueDivDef.HAVE);
            }
            else
            {
                this.appliRequiredSrvParams.setElectricIssueDiv(WEB3ConditionsValueDivDef.HAVE_NOT);
            }
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (is�d�q�������ݒ�)<BR>
     * ���Y�T�[�r�X�̐\�������ɁA�d�q�����ݒ肳��Ă��邩��ԋp����B<BR>
     * �itrue:�ݒ肳��Ă���@@false:�ݒ肳��Ă��Ȃ��j<BR>
     * <BR>
     * 1) this.�\���v�T�[�r�X�s��null�̏ꍇ<BR>
     * �@@false��ԋp����B<BR>
     * <BR>
     * 2) this.�\���v�T�[�r�X�s��null�ł͂Ȃ��ꍇ<BR>
     *  2-1) this.�\���v�T�[�r�X�s.get�d�q�������ݒ�敪( )="��"or"null"�̏ꍇ<BR>
     * �@@�@@�@@false��ԋp����B<BR>
     *  2-2) this.�\���v�T�[�r�X�s.get�d�q�������ݒ�敪( )="�L"�̏ꍇ<BR>
     * �@@�@@�@@true��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 412EEBB501EB
     */
    public boolean isElectricIssue() 
    {
        final String STR_METHOD_NAME = " isElectricIssue()";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
        	//�d�l�ύX�@@NO_199
            if (this.appliRequiredSrvParams.getElectricIssueDiv() == null ||
            	WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.appliRequiredSrvParams.getElectricIssueDiv()))
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        } 
    }
    
    /**
     * (set�񋟌`��)<BR>
     * 1) this.�\���v�T�[�r�X�s��null�łȂ��ꍇ�A<BR> 
�@@   *   this.�\���v�T�[�r�X�s.set�񋟌`��()���R�[������B<BR>
     * [����]<BR>
�@@   * �񋟌`��=����.�񋟌`��<BR>
     * @@param l_strSupplyDiv - (�񋟌`��)<BR>
     */
    public void setSupplyDiv(String l_strSupplyDiv)
    {
        final String STR_METHOD_NAME = " setSupplyDiv(String)";
        log.entering(STR_METHOD_NAME);
        
        if(this.appliRequiredSrvParams != null)
        {
            this.appliRequiredSrvParams.setSupplyDiv(l_strSupplyDiv);
        }
        
        log.exiting(STR_METHOD_NAME);       
    }
    
    /**
     * (get�񋟌`��)<BR>
     * 1) this.�\���v�T�[�r�X�s��null�̏ꍇ�Anull��Ԃ��B<BR> 
     * 2) this.�\���v�T�[�r�X�s��null�łȂ��ꍇ�A<BR> 
�@@   *  this.�\���v�T�[�r�X�s.get�񋟌`��()�̖߂�l��Ԃ��B<BR>
     * @@return String
     */
    public String getSupplyDiv()
    {
        final String STR_METHOD_NAME = " getSupplyDiv()";
        log.entering(STR_METHOD_NAME);
        
        String l_strProvidModel = "";
        
        if(this.appliRequiredSrvParams == null)
        {
            log.exiting(STR_METHOD_NAME);  
            return null;
        }
        else
        {   
            l_strProvidModel = this.appliRequiredSrvParams.getSupplyDiv();
        }
        
        log.exiting(STR_METHOD_NAME);  
        return l_strProvidModel;
    }
    
    /**
     * (set��萔�����v�z)<BR>
     * 1) this.�\���v�T�[�r�X�s��null�łȂ��ꍇ�A<BR> 
�@@   *    this.�\���v�T�[�r�X�s.set��萔�����v�z()���R�[������<BR>�B 
     *    [����]<BR> 
�@@   *    ��萔�����v�z=����.��萔�����v�z<BR> 
     * @@param l_dblMinCommAmt - (��萔�����v�z)<BR>
     */
    public void setMinCommAmt(double l_dblMinCommAmt)
    {
        final String STR_METHOD_NAME = " setMinCommAmt(double)";
        log.entering(STR_METHOD_NAME);
        
        if(this.appliRequiredSrvParams != null)
        {
            this.appliRequiredSrvParams.setMinCommAmt((long)l_dblMinCommAmt);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get��萔�����v�z)<BR>
     * 1) this.�\���v�T�[�r�X�s��null�̏ꍇ�Anull��Ԃ��B<BR> 
     * 2) this.�\���v�T�[�r�X�s��null�łȂ��ꍇ�A<BR> 
�@@   *    this.�\���v�T�[�r�X�s.get��萔�����v�z()�̖߂�l��Ԃ��B<BR>
     * @@return String
     */
    public String getMinCommAmt()
    {
        final String STR_METHOD_NAME = " getMinCommAmt()";
        log.entering(STR_METHOD_NAME);
        
        String l_strMinCommAmt = "";
        if(this.appliRequiredSrvParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            l_strMinCommAmt = String.valueOf(this.appliRequiredSrvParams.getMinCommAmt());
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strMinCommAmt;
    }
    
    
    /**
     * (createNew�\���v�T�[�r�X)<BR>
     * �V�K�ɐ\���v�T�[�r�X�I�u�W�F�N�g�𐶐����ĕԋp����B<BR>
     * <BR>
     * 1) �\���v�T�[�r�XParams�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * 2) �\���v�T�[�r�XParams.set�،���ЃR�[�h( )���R�[������B<BR>
     * [����]<BR>
     * �@@�،���ЃR�[�h=����.�،���ЃR�[�h<BR>
     * <BR>
     * 3) �\���v�T�[�r�XParams.set�T�[�r�X�敪( )���R�[������B<BR>
     * [����]<BR>
     * �@@�T�[�r�X�敪=����.�T�[�r�X�敪<BR>
     * <BR>
     * 4) �\���v�T�[�r�X�̃R���X�g���N�^���R�[�����A��������<BR>
     * �@@�\���v�T�[�r�X�I�u�W�F�N�g��ԋp����B<BR>
     * [����]<BR>
     * �@@�\���v�T�[�r�XRow=���������\���v�T�[�r�XParams<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@return webbroker3.srvregi.WEB3SrvRegiApplicationRequiredService
     * @@roseuid 413E6349033D
     */
    public static WEB3SrvRegiApplicationRequiredService createNewAppliRequiredSrv(String l_strInstitutionCode, String l_strSrvDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createNewAppliRequiredSrv(String l_strInstitutionCode, String l_strSrvDiv)";
        log.entering(STR_METHOD_NAME);
        
        //1) �\���v�T�[�r�XParams�I�u�W�F�N�g�𐶐�����B
        SrvRegiSetupParams l_srvRegiSetupParams = new SrvRegiSetupParams();
        
        //2) �\���v�T�[�r�XParams.set�،���ЃR�[�h( )���R�[������B
        l_srvRegiSetupParams.setInstitutionCode(l_strInstitutionCode);
        
        //3) �\���v�T�[�r�XParams.set�T�[�r�X�敪( )���R�[������B
        l_srvRegiSetupParams.setSrvDiv(l_strSrvDiv);
        
        //4) �\���v�T�[�r�X�̃R���X�g���N�^���R�[�����A���������\���v�T�[�r�X�I�u�W�F�N�g��ԋp����B
        WEB3SrvRegiApplicationRequiredService l_srvRegiApplicationRequiredService = new WEB3SrvRegiApplicationRequiredService(l_srvRegiSetupParams);
        
        log.exiting(STR_METHOD_NAME);
        
        return l_srvRegiApplicationRequiredService;
    }
    
    /**
     * (save�\���v�T�[�r�X)<BR>
     * this.�\���v�T�[�r�X�s�I�u�W�F�N�g��<BR>
     * ���e���f�[�^�x�[�X�ɔ��f������B(Update)<BR>
     * <BR>
     * 1) this.�\���v�T�[�r�X�s�I�u�W�F�N�g�ɁA�ȉ��̒l���Z�b�g����B<BR>
     * �@@�X�V�҃R�[�h=�Ǘ���.getInstanceFrom���O�C�����( ).get�Ǘ��҃R�[�h( )<BR>
     * �@@�X�V���t=GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l<BR>
     * <BR>
     * 2) this.�\���v�T�[�r�X�s�I�u�W�F�N�g�̓��e�ŁA<BR>
     * �@@�\���v�T�[�r�X�e�[�u�����X�V�iUpdate�j����B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 413E6349035C
     */
    public void saveAppliRequiredSrv() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " saveAppliRequiredSrv()";
        log.entering(STR_METHOD_NAME);
        
        //1) this.�\���v�T�[�r�X�s�I�u�W�F�N�g�ɁA�ȉ��̒l���Z�b�g����B
        String l_strAdministratorCode = WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode();
        this.appliRequiredSrvParams.setLastUpdater(l_strAdministratorCode);
        
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem( ).getSystemTimestamp( );
        //�X�V���t
        this.appliRequiredSrvParams.setLastUpdatedTimestamp(l_tsSystemTime);
        
        //2) this.�\���v�T�[�r�X�s�I�u�W�F�N�g�̓��e�ŁA�\���v�T�[�r�X�e�[�u�����X�V�iUpdate�j����B
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            l_queryProcessor.doUpdateQuery(this.appliRequiredSrvParams);//DataNetworkException,DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);    
    }
    
    /**
     * (saveNew�\���v�T�[�r�X)<BR>
     * this.�\���v�T�[�r�X�s�I�u�W�F�N�g��<BR>
     * ���e���f�[�^�x�[�X�ɔ��f������B(Insert)<BR>
     * <BR>
     * 1) this.�\���v�T�[�r�X�s�I�u�W�F�N�g�ɁA�ȉ��̒l���Z�b�g����B<BR>
     * �@@�X�V�҃R�[�h=�Ǘ���.getInstanceFrom���O�C�����( ).get�Ǘ��҃R�[�h( )<BR>
     * �@@�쐬���t=GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l<BR>
     * �@@�X�V���t=GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l<BR>
     * <BR>
     * 2) this.�\���v�T�[�r�X�s�I�u�W�F�N�g�̓��e�ŁA<BR>
     * �@@�\���v�T�[�r�X�e�[�u�����X�V�iInsert�j����B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 413E6349036C
     */
    public void saveNewAppliRequiredSrv() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " saveNewAppliRequiredSrv()";
        log.entering(STR_METHOD_NAME);
        
        //1) this.�\���v�T�[�r�X�s�I�u�W�F�N�g�ɁA�ȉ��̒l���Z�b�g����B
        String l_strAdministratorCode = WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode();
        this.appliRequiredSrvParams.setLastUpdater(l_strAdministratorCode);
        
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem( ).getSystemTimestamp( );
        //�쐬���t
        this.appliRequiredSrvParams.setCreatedTimestamp(l_tsSystemTime);
        //�X�V���t
        this.appliRequiredSrvParams.setLastUpdatedTimestamp(l_tsSystemTime);
        
        //2) this.�\���v�T�[�r�X�s�I�u�W�F�N�g�̓��e�ŁA�\���v�T�[�r�X�e�[�u�����X�V�iInsert�j����B
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            l_queryProcessor.doInsertQuery(this.appliRequiredSrvParams);//DataNetworkException,DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);     
    }

    /**
     * (set�����Ώۊ���)<BR>
     * �����Ώۊ��Ԃ̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�\���v�T�[�r�X�s��null�łȂ��ꍇ�A <BR>
     * this.�\���v�T�[�r�X�s.set�����Ώۊ���()���R�[������B<BR>
     * [����] <BR>
     * �����Ώۊ���=����.�����Ώۊ���<BR>
     * <BR>
     * @@param l_strFreeTargetPeriod - (�����Ώۊ���)<BR>
     */
    public void setFreeTargetPeriod(String l_strFreeTargetPeriod)
    {
        final String STR_METHOD_NAME = " setFreeTargetPeriod(String)";
        log.entering(STR_METHOD_NAME);

        // this.�\���v�T�[�r�X�s��null�łȂ��ꍇ�A
        if (this.appliRequiredSrvParams != null)
        {
        	//����.�����Ώۊ��� == null�̏ꍇ
        	if (l_strFreeTargetPeriod == null)
        	{
        		this.appliRequiredSrvParams.setFreeCoverageLength(null);	
        	}
        	else
        	{
        		this.appliRequiredSrvParams.setFreeCoverageLength(Integer.parseInt(l_strFreeTargetPeriod));	
        	}
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�����Ώۊ���)<BR>
     * �����Ώۊ��Ԃ�Ԃ��B<BR>
     * <BR>
     * 1) this.�\���v�T�[�r�X�s��null�̏ꍇ�Anull��Ԃ��B <BR>
     * <BR>
     * 2) this.�\���v�T�[�r�X�s��null�łȂ��ꍇ�A <BR>
     * this.�\���v�T�[�r�X�s.get�����Ώۊ���()�̖߂�l��Ԃ��B<BR>
     * <BR>
     * @@return String
     */
    public String getFreeTargetPeriod()
    {
        final String STR_METHOD_NAME = " getFreeTargetPeriod()";
        log.entering(STR_METHOD_NAME);

        String l_strFreeTargetPeriod = null;

        //this.�\���v�T�[�r�X�s��null�łȂ��ꍇ
        if (this.appliRequiredSrvParams != null)
        {
            if (!this.appliRequiredSrvParams.getFreeCoverageLengthIsNull())
            {
                int l_intFreeCoverageLength = this.appliRequiredSrvParams.getFreeCoverageLength();
                l_strFreeTargetPeriod = String.valueOf(l_intFreeCoverageLength); 
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_strFreeTargetPeriod;
    }
}
@
