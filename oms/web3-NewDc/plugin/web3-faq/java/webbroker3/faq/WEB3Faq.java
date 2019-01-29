head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3Faq.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �⍇��(WEB3Faq)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 ����� (���u) �V�K�쐬
*/

package webbroker3.faq;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.faq.data.FaqDao;
import webbroker3.faq.data.FaqParams;
import webbroker3.faq.data.FaqRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (�⍇��)<BR>
 * �⍇��<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3Faq implements BusinessObject 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3Faq.class);
    
    /**
     * (�⍇���s)<BR>
     * �⍇���s�I�u�W�F�N�g<BR>
     * <BR>
     * ���⍇��Params�N���X��DDL��莩����������B<BR>
     */
    private FaqParams faqParams;
    
    /**
     * (�⍇��)<BR>
     * �R���X�g���N�^�B<BR>
     * �⍇���I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �P�j�@@�s�I�u�W�F�N�g�擾<BR>
     * �@@�ȉ��̏����ŁA�⍇���e�[�u������������B<BR>
     * �@@�Y���f�[�^���Ȃ��ꍇ�́A��O���X���[����B<BR>
     *          class: NotFoundException<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�⍇��.�،���ЃR�[�h = �،���ЃR�[�h And<BR>
     * �@@�⍇��.�⍇���R�[�h = �⍇���R�[�h<BR>
     * <BR>
     * �Q�j�@@�⍇���s�v���p�e�B�̃Z�b�g<BR>
     * �@@�������ʂ̍s�I�u�W�F�N�g���I�u�W�F�N�g�̃v���p�e�B�ɃZ�b�g���A�ԋp����B<BR>
     * <BR>
     * �� �⍇��Params�N���X��DDL��莩����������B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strFaqNumber - (�⍇���R�[�h)<BR>
     * �⍇���R�[�h<BR>
     * @@return webbroker3.faq.WEB3Faq
     * @@roseuid 41AC128B0270
     */
    public WEB3Faq(String l_strInstitutionCode, String l_strFaqNumber) throws WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME = " WEB3Faq(String, String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strInstitutionCode == null || l_strFaqNumber == null)
        {
            log.error("parameter is null type");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);          
        }
        
        try
        {
            //�ȉ��̏����ŁA�⍇���e�[�u������������B
            //[����]
            //�⍇��.�،���ЃR�[�h = �،���ЃR�[�h And 
            //�⍇��.�⍇���R�[�h = �⍇���R�[�h 
            FaqParams l_faqParams = (FaqParams)FaqDao.findRowByPk(
                l_strFaqNumber,
                l_strInstitutionCode);
                
            this.faqParams = l_faqParams;
        }
        catch (DataFindException l_ex)
        {
            //�Y���f�[�^���Ȃ��ꍇ�́A��O���X���[����B 
            throw new NotFoundException(
                "�Y���f�[�^�����݂��Ȃ��B" + 
                "�u�،���ЃR�[�h�v = " + l_strInstitutionCode +
                "�u�⍇���R�[�h�v = " + l_strFaqNumber);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (�⍇��)<BR>
     * �R���X�g���N�^�B<BR>
     * �⍇���I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �����̍s�I�u�W�F�N�g�̃v���p�e�B�ɃZ�b�g����B<BR>
     * <BR>
     * �� �⍇��Params�N���X��DDL��莩����������B<BR>
     * @@param l_faqParams - (�⍇���s)<BR>
     * �⍇���s�I�u�W�F�N�g<BR>
     * <BR>
     * ���⍇��Params�N���X��DDL��莩����������B<BR>
     * 
     * @@return webbroker3.faq.WEB3Faq
     * @@roseuid 41AC11DC035A
     */
    public WEB3Faq(FaqParams l_faqParams) 
    {
        this.faqParams = l_faqParams;
    }
    
    /**
     * (�⍇��)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * <BR>
     * this.�⍇���s�ɋ�̃I�u�W�F�N�g�𐶐����Z�b�g����B<BR>
     * @@return webbroker3.faq.WEB3Faq
     * @@roseuid 41AC0A34012E
     */
    public WEB3Faq() 
    {
        this.faqParams = new FaqParams();
    }
    
    /**
     * this.�⍇���Ǘ��s��ԋp����B<BR>
     * @@return Object
     * @@roseuid 41ABF65201BB
     */
    public Object getDataSourceObject() 
    {
        return this.faqParams;
    }
    
    /**
     * (set�،���ЃR�[�h)<BR>
     * this.�⍇���s.�،���ЃR�[�h�ɏ،���ЃR�[�h���Z�b�g����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@roseuid 41ABF87702C4
     */
    public void setInstitutionCode(String l_strInstitutionCode) 
    {
        this.faqParams.setInstitutionCode(l_strInstitutionCode);
    }
    
    /**
     * (set���X�R�[�h)<BR>
     * this.�⍇���s.���X�R�[�h�ɕ��X�R�[�h���Z�b�g����B<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@roseuid 41ABF8BC01CA
     */
    public void setBranchCode(String l_strBranchCode) 
    {
        this.faqParams.setBranchCode(l_strBranchCode);
    }
    
    /**
     * (set�����R�[�h)<BR>
     * this.�⍇���s.�����R�[�h�Ɍ����R�[�h���Z�b�g����B<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@roseuid 41ABF8D1016D
     */
    public void setAccountCode(String l_strAccountCode) 
    {
        this.faqParams.setAccountCode(l_strAccountCode);
    }
    
    /**
     * (set�ڋq���i�����j)<BR>
     * this.�⍇���s.�ڋq���i�����j�Ɍڋq���i�����j���Z�b�g����B<BR>
     * @@param l_strName - (�ڋq���i�����j)<BR>
     * �ڋq���i�����j<BR>
     * @@roseuid 41ABF8F000D0
     */
    public void setName(String l_strName) 
    {
        this.faqParams.setName(l_strName);
    }
    
    /**
     * (set���[���A�h���X)<BR>
     * this.�⍇���s.email�A�h���X�Ƀ��[���A�h���X���Z�b�g����B<BR>
     * @@param l_strEmailAddress - (���[���A�h���X)<BR>
     * ���[���A�h���X<BR>
     * @@roseuid 41ABF9200228
     */
    public void setEmailAddress(String l_strEmailAddress) 
    {
        this.faqParams.setEmailAddress(l_strEmailAddress);
    }
    
    /**
     * (set����)<BR>
     * this.�⍇���s.�����Ɍ������Z�b�g����B<BR>
     * @@param l_strSubject - (����)<BR>
     * ����<BR>
     * @@roseuid 41ABF950015D
     */
    public void setSubject(String l_strSubject) 
    {
        this.faqParams.setSubject(l_strSubject);
    }
    
    /**
     * (set�⍇�����e)<BR>
     * this.�⍇���s.�⍇�����e�ɖ⍇�����e���Z�b�g����B<BR>
     * @@param l_strFaqText - (�⍇�����e)<BR>
     * �⍇�����e<BR>
     * @@roseuid 41ABF978038F
     */
    public void setFaqText(String l_strFaqText) 
    {
        this.faqParams.setFaqText(l_strFaqText);
    }
    
    /**
     * (set�@@�\�h�c)<BR>
     * this.�⍇���s.�@@�\�h�c�ɋ@@�\�h�c���Z�b�g����B<BR>
     * @@param l_strTransactionId - (�@@�\�h�c)<BR>
     * �@@�\�h�c<BR>
     * @@roseuid 41AC169C03C8
     */
    public void setTransactionId(String l_strTransactionId) 
    {
        this.faqParams.setTransactionId(l_strTransactionId);
    }
    
    /**
     * (get�،���ЃR�[�h)<BR>
     * this.�⍇���s.�،���ЃR�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 41ABF9A0013E
     */
    public String getInstitutionCode() 
    {
        return this.faqParams.getInstitutionCode();
    }
    
    /**
     * (get���X�R�[�h)<BR>
     * this.�⍇���s.���X�R�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 41ABF9BE0303
     */
    public String getBranchCode() 
    {
        return this.faqParams.getBranchCode();
    }
    
    /**
     * (get�����R�[�h)<BR>
     * this.�⍇���s.�����R�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 41ABF9CB010F
     */
    public String getAccountCode() 
    {
        return this.faqParams.getAccountCode();
    }
    
    /**
     * (get�ڋq���i�����j)<BR>
     * this.�⍇���s.�ڋq���i�����j��ԋp����B<BR>
     * @@return String
     * @@roseuid 41ABF9D402D4
     */
    public String getName() 
    {
        return this.faqParams.getName();
    }
    
    /**
     * (get���[���A�h���X)<BR>
     * this.�⍇���s.email�A�h���X��ԋp����B<BR>
     * @@return String
     * @@roseuid 41ABF9E80312
     */
    public String getEmailAddress() 
    {
        return this.faqParams.getEmailAddress();
    }
    
    /**
     * (get����)<BR>
     * this.�⍇���s.������ԋp����B<BR>
     * @@return String
     * @@roseuid 41ABF9F601BB
     */
    public String getSubject() 
    {
        return this.faqParams.getSubject();
    }
    
    /**
     * (get�⍇�����e)<BR>
     * this.�⍇���s.�⍇�����e��ԋp����B<BR>
     * @@return String
     * @@roseuid 41ABFA0403CE
     */
    public String getFaqText() 
    {
        return this.faqParams.getFaqText();
    }
    
    /**
     * (get�@@�\�h�c)<BR>
     * this.�⍇���s.�@@�\�h�c��ԋp����B<BR>
     * @@return String
     * @@roseuid 41AC168C0186
     */
    public String getTransactionId() 
    {
        return this.faqParams.getTransactionId();
    }
    
    /**
     * (get�⍇������)<BR>
     * this.�⍇���s.�쐬������ԋp����B<BR>
     * @@return Date
     * @@roseuid 41AC2C1600C2
     */
    public Date getFaqDatetime() 
    {
        return this.faqParams.getCreatedTimestamp();
    }
    
    /**
     * (get�⍇���R�[�h)<BR>
     * this.�⍇���s.�⍇���R�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 41AC369701FB
     */
    public String getFaqNumber() 
    {
        return this.faqParams.getFaqNumber();
    }
    
    /**
     * (saveNew�⍇��)<BR>
     * �⍇���e�[�u�����X�V����B<BR>
     * <BR>
     * �P�j �⍇���s�I�u�W�F�N�g�擾 <BR>
     * �@@getDataSourceObject()�ɂčs�I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �Q�j �X�V�����Z�b�g����B <BR>
     * �@@�⍇���s�̓��̓f�[�^�ȊO�̍��ڂɒl���Z�b�g����B <BR>
     * <BR>
     * �@@�⍇���R�[�h = �⍇���R�[�h<BR>
     * �@@�쐬���� = TradingSystem.getSystemTimestamp()<BR>
     * �@@�X�V���� = TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     * �R�j DB�X�V <BR>
     * �@@�⍇���s�I�u�W�F�N�g�̓��e�ŁA�⍇���e�[�u�����X�V�iinsert�j����B <BR>
     * @@param l_strFaqNumber - (�⍇���R�[�h)<BR>
     * �⍇���R�[�h<BR>
     * @@roseuid 41ABF6880247
     */
    public void saveNewFaq(String l_strFaqNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveNewFaq(String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strFaqNumber == null)
        {
            log.error("parameter is null type");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);          
        }
        
        //�⍇���s�̓��̓f�[�^�ȊO�̍��ڂɒl���Z�b�g����B 
        //�⍇���R�[�h = �⍇���R�[�h
        this.faqParams.setFaqNumber(l_strFaqNumber);
        
        //�쐬���� = TradingSystem.getSystemTimestamp(l_tsProcessDate) 
        Timestamp l_tsProcessDate = GtlUtils.getSystemTimestamp();
        this.faqParams.setCreatedTimestamp(l_tsProcessDate);
        
        //�X�V���� = TradingSystem.getSystemTimestamp(l_tsProcessDate)
        this.faqParams.setLastUpdatedTimestamp(l_tsProcessDate);
        
        log.info("============= faqParams:" + this.faqParams);
        
        //DB�X�V
        //�⍇���s�I�u�W�F�N�g�̓��e�ŁA�⍇���e�[�u�����X�V�iinsert�j����B
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(faqParams);
        }
        catch (DataFindException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�⍇��)<BR>
     * �istatic ���\�b�h�j <BR>
     * �w��ɊY������⍇���I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * QueryProcessor.doFindAllQuery( )�ɂ��A�⍇���s�I�u�W�F�N�g��List���擾����B <BR>
     * <BR>
     * �@@�@@[doFindAllQuery()�Ɏw�肷�����] <BR>
     * �@@�@@rowType�F�@@�⍇��Row.TYPE <BR>
     * �@@�@@where�F�@@�������������� <BR>
     * �@@�@@orderBy�F�@@�\�[�g���� <BR>
     * �@@�@@conditions�F�@@null <BR>
     * �@@�@@bindVars�F�@@���������f�[�^�R���e�i <BR>
     * <BR>
     * �Y���f�[�^���Ȃ��ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �擾���ʂ̊e�s�I�u�W�F�N�g�ɂ��āA�⍇���I�u�W�F�N�g�𐶐����A<BR>
     * �z��ɂĕԋp����B<BR>
     * @@param l_strQueryString - (��������������)<BR>
     * ��������������<BR>
     * @@param l_queryContainer - (���������f�[�^�R���e�i)<BR>
     * ���������f�[�^�R���e�i<BR>
     * @@param l_strSortCond - (�\�[�g����)<BR>
     * �\�[�g����<BR>
     * @@return webbroker3.faq.WEB3Faq[]
     * @@roseuid 41AC228603E7
     */
    public static WEB3Faq[] getFaq(String l_strQueryString, Object[] l_queryContainer, String l_strSortCond)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getFaq(String, String[], String)";
        log.entering(STR_METHOD_NAME);

        if (l_strQueryString == null || l_queryContainer == null)
        {
            log.error("parameter is null type");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                STR_METHOD_NAME);          
        }

        List l_lisRecords = null;
        try
        {
            //QueryProcessor.doFindAllQuery( )�ɂ��A�⍇���s�I�u�W�F�N�g��List���擾����B 
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisRecords = l_queryProcessor.doFindAllQuery(
                FaqRow.TYPE,
                l_strQueryString,
                l_strSortCond,
                null,
                l_queryContainer);
        }
        catch (DataFindException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                WEB3Faq.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Faq.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Faq.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            log.error("�f�[�^���Ȃ�");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                WEB3Faq.class.getName() + STR_METHOD_NAME);
        }
        
        int l_intSize = l_lisRecords.size();
        log.info("l_intSize = " + l_intSize);

        WEB3Faq[] l_faqs = new WEB3Faq[l_intSize];

        for (int i = 0; i < l_intSize; i++)
        {
            l_faqs[i] = new WEB3Faq((FaqParams)l_lisRecords.get(i));
        }

        log.exiting(STR_METHOD_NAME);
        return l_faqs;
    }
}
@
