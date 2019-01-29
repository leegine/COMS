head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FaqNumberServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �⍇���Ǘ��⍇���R�[�h�̔ԃT�[�r�XImpl(WEB3FaqNumberServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 ����� (���u) �V�K�쐬
*/

package webbroker3.faq.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.faq.data.FaqNumberDao;
import webbroker3.faq.data.FaqNumberParams;
import webbroker3.faq.data.FaqNumberRow;
import webbroker3.faq.service.delegate.WEB3FaqNumberService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�⍇���Ǘ��⍇���R�[�h�̔ԃT�[�r�XImpl)<BR>
 * �⍇���Ǘ��⍇���R�[�h�̔ԃT�[�r�X�����N���X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3FaqNumberServiceImpl implements WEB3FaqNumberService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FaqNumberServiceImpl.class);
           
    /**
     * @@roseuid 41C25C8C008C
     */
    public WEB3FaqNumberServiceImpl() 
    {
     
    }
    
    /**
     * (get�V�K�⍇���R�[�h)<BR>
     * �⍇���R�[�h���̔Ԃ���B<BR>
     * <BR>
     * �⍇���R�[�h�̃R�[�h�̌n�͈ȉ��̒ʂ�B<BR>
     * <BR>
     * �@@�E�P�`�W���ځiindex=0�`7�j�F�@@���t�Z�N�V�����iyyyyMMdd�j<BR>
     * �@@�E�X�`�P�R���ځiindex=8�`12�j�F�@@5���̒ʔ�<BR>
     * �@@�i�،���Ж��C�⍇���R�[�h�R�[�h�e�[�u���ɍŏI�̔Ԓl��ۑ��j<BR>
     * <BR>
     * �P�j�@@���t�Z�N�V�����i�hyyyyMMdd�h�擾�j<BR>
     * �@@�@@TradingSystem.getBizDate() ���hyyyyMMdd�h�Ƀt�H�[�}�b�g����<BR>
     * ��������擾����B<BR>
     * <BR>
     * �Q�j�@@�⍇���R�[�h�e�[�u�����X�V����B<BR>
     * <BR>
     * �@@�⍇���R�[�h�e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�⍇���R�[�h.�،���ЃR�[�h = �،���ЃR�[�h<BR>
     * <BR>
     * �@@�|�Y���f�[�^���Ȃ��ꍇ�́A�ȉ��̒ʂ背�R�[�h��V�K�o�^�iinsert�j���A<BR>
     * ���ʃR�[�h�ő�l��ԋp����B<BR>
     * <BR>
     * �@@�@@�⍇���R�[�h.�،���ЃR�[�h = �،���ЃR�[�h<BR>
     * �@@�@@�⍇���R�[�h.���ʃR�[�h�ő�l = yyyyMMdd + "00001"<BR>
     * �@@�@@�⍇���R�[�h.�쐬���� = TradingSystem.getSystemTimestamp()<BR>
     * �@@�@@�⍇���R�[�h.�X�V���� = TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     * �@@�|�Y���f�[�^������ꍇ�́A�ȉ��̒ʂ背�R�[�h���X�V�iupdate�j���A<BR>
     * �⍇���R�[�h�ő�l��ԋp����B<BR>
     * <BR>
     * �@@�@@�⍇���R�[�h.�⍇���R�[�h�ő�l = yyyyMMdd + �i���P�j<BR>
     * �@@�@@�⍇���R�[�h.�X�V���� = TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     * �@@�@@�i���P�j�@@�⍇���R�[�h�ő�l�i�ʔԕ����j�̌v�Z<BR>
     * <BR>
     * �@@�@@���@@�����l�Ɠ�����̏ꍇ<BR>
     * �@@�@@�@@�P�j�Ŏ擾�������t�Z�N�V����������ƁA�����l�̓��t�Z�N�V����������<BR>
     * �����ꍇ�A<BR>
     * �@@�@@�@@�����l�̒ʔԁ{�P�̐�����5���̕�����ɕҏW����B<BR>
     * <BR>
     * �@@�@@���@@�����l�Ɠ�����łȂ��ꍇ<BR>
     * �@@�@@"00001"��ҏW����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�B<BR>
     * @@return String
     * @@roseuid 41ABF27902F3
     */
    public String getNewFaqNumber(String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getNewFaqNumber(String)";
        log.entering(STR_METHOD_NAME);        
        
        //���t�Z�N�V�����i�hyyyyMMdd�h�擾�j 
        //TradingSystem.getBizDate() ���hyyyyMMdd�h�Ƀt�H�[�}�b�g������������擾����B
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
        String l_strBizDateYMD = WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd");
        
        Timestamp l_tsProcessDate = GtlUtils.getSystemTimestamp();
        List l_lisFaqNumberParams = null;
        FaqNumberParams l_faqNumberParams = null;
        try
        {   
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            try
            {
                //�⍇���R�[�h�e�[�u�����ȉ��̏����Ō�������B
                //[����]
                //�⍇���R�[�h.�،���ЃR�[�h = �،���ЃR�[�h 
                String l_strQuery = " institution_code = ? ";
                Object[] l_objContainer = {l_strInstitutionCode};

                l_lisFaqNumberParams = l_queryProcessor.doFindAllQuery(
                        FaqNumberRow.TYPE, 
                        l_strQuery, 
                        null, 
                        "for update", 
                        l_objContainer);                                
            }
            catch (DataFindException l_ex)
            {
                //�Y���f�[�^���Ȃ��ꍇ
                log.debug("�Y���f�[�^���Ȃ��B[�،���ЃR�[�h] = " + l_strInstitutionCode); 
            }
                        
            if (l_lisFaqNumberParams == null || l_lisFaqNumberParams.isEmpty())
            {
                //�Y���f�[�^���Ȃ��ꍇ�́A�ȉ��̒ʂ背�R�[�h��V�K�o�^�iinsert�j���A
                //���ʃR�[�h�ő�l��ԋp����B
                
                l_faqNumberParams = new FaqNumberParams();
                
                //�⍇���R�[�h.�،���ЃR�[�h = �،���ЃR�[�h
                l_faqNumberParams.setInstitutionCode(l_strInstitutionCode);
                
                //�⍇���R�[�h.���ʃR�[�h�ő�l = yyyyMMdd + "00001"
                l_faqNumberParams.setLastFaqNumber(l_strBizDateYMD + "00001");

                //�⍇���R�[�h.�쐬���� = TradingSystem.getSystemTimestamp()
                l_faqNumberParams.setCreatedTimestamp(l_tsProcessDate);
                 
                //�⍇���R�[�h.�X�V���� = TradingSystem.getSystemTimestamp()
                l_faqNumberParams.setLastUpdatedTimestamp(l_tsProcessDate);
                
                l_queryProcessor.doInsertQuery(l_faqNumberParams);
            }
            else
            {
                l_faqNumberParams = (FaqNumberParams)l_lisFaqNumberParams.get(0);
                
                //�Y���f�[�^������ꍇ�́A�ȉ��̒ʂ背�R�[�h���X�V�iupdate�j���A
                //�⍇���R�[�h�ő�l��ԋp����B                
                DecimalFormat df = new DecimalFormat("00000");
                
                //�⍇���R�[�h�ő�l�i�ʔԕ����j�̌v�Z
                String l_strMaxNumber = null;
                String l_strLastFaqNumber = l_faqNumberParams.getLastFaqNumber();
                
                //���t�Z�N�V����������
                if (l_strLastFaqNumber.length() < 8)
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + "." + STR_METHOD_NAME); 
                }
                String l_strDateYMD = l_strLastFaqNumber.substring(0, 8);
                if (l_strBizDateYMD.equals(l_strDateYMD))
                {
                    // ���@@�����l�Ɠ�����̏ꍇ
                    //�P�j�Ŏ擾�������t�Z�N�V����������ƁA�����l�̓��t�Z�N�V���������񂪓����ꍇ�A
                    //�����l�̒ʔԁ{�P�̐�����5���̕�����ɕҏW����B
                    
                    String l_strNumber = l_strLastFaqNumber.substring(8);
                    int l_intNumber;
                    try
                    {
                        l_intNumber = df.parse(l_strNumber).intValue();
                        l_strMaxNumber = df.format(++l_intNumber);
                    }
                    catch (ParseException e)
                    {
                        log.debug("�⍇���R�[�h�ő�l�i�ʔԕ����j�̃t�H�[�}�b�g�G���[�B[�ʔԕ���] = " + l_strNumber);
                        l_strMaxNumber = "00001";
                    }
                }
                else
                {
                    // ���@@�����l�Ɠ�����łȂ��ꍇ 
                    //"00001"��ҏW����B 
                    l_strMaxNumber = "00001";
                }
                
                l_faqNumberParams = new FaqNumberParams(l_faqNumberParams);
                
                //�⍇���R�[�h.�⍇���R�[�h�ő�l = yyyyMMdd + �ʔԕ���
                l_faqNumberParams.setLastFaqNumber(l_strBizDateYMD + l_strMaxNumber);
                
                //�⍇���R�[�h.�X�V���� = TradingSystem.getSystemTimestamp()
                l_faqNumberParams.setLastUpdatedTimestamp(l_tsProcessDate);
                
                l_queryProcessor.doUpdateQuery(l_faqNumberParams);
            }

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
        
        return l_faqNumberParams.getLastFaqNumber();
    }   

}
@
