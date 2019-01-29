head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.30.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenRegistCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݐ\��CSV(WEB3AdminAccOpenRegistCsv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 �A�C��(���u) �V�K�쐬
*/

package webbroker3.accountopen;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.accountopen.data.AccOpenDlFormMasterRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CatDelimitterDef;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (�����J�ݐ\��CSV)<BR>
 * �����J�ݐ\��CSV<BR>
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminAccOpenRegistCsv extends WEB3GentradeCsvDataModel 
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminAccOpenRegistCsv.class);
    
    /**
     * @@roseuid 41B45E6F01E4
     */
    public WEB3AdminAccOpenRegistCsv() 
    {
     
    }
    
    /**
     * (�����J�ݐ\��CSV)<BR>
     * �R���X�g���N�^�B <BR>
     * <BR>
     * �C���X�^���X�𐶐����A�w�b�_�����Z�b�g����B <BR>
     * �@@�|super()�ɂăC���X�^���X�𐶐�����B <BR>
     * �@@�|create�L�[�w�b�_()���R�[�����A�L�[�w�b�_�����쐬����B <BR>
     * �@@�|create�J�����w�b�_(�،���ЃR�[�h�C�����敪)���R�[�����A<BR>
     * �J�����w�b�_�����쐬����B <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strAccountDiv - �����敪<BR>
     * <BR>
     * 0�F�l�A�J�E���g�@@1�F�@@�l�A�J�E���g<BR>
     * 
     * @@return webbroker3.accountopen.WEB3AdminAccOpenRegistCsv
     * @@roseuid 41A14A2203B4
     */
    public WEB3AdminAccOpenRegistCsv(String l_strInstitutionCode, String l_strAccountDiv)
        throws WEB3BaseException 
    {
        super();
        this.createKeyHeader();
        this.createColumnHeader(l_strInstitutionCode, l_strAccountDiv);
    }
    
    /**
     * (create�J�����w�b�_)<BR>
     * CSV�J�������f���̔z��𐶐����Aset�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B <BR>
     * <BR>
     * �P�j�@@�_�E�����[�h�t�H�[���}�X�^�擾<BR>
     * �@@�ȉ��̏����ŁA�����J�ݐ\���c�k�t�H�[���}�X�^�e�[�u������������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�����J�ݐ\���c�k�t�H�[���}�X�^.�،���ЃR�[�h = �،���ЃR�[�h And<BR>
     * �@@�����J�ݐ\���c�k�t�H�[���}�X�^.�����敪 = �����敪<BR>
     * <BR>
     * �@@[�擾��]<BR>
     * �@@�J�����ԍ� �����i�Fasc�j<BR>
     * <BR>
     * �@@�Y���s�����݂��Ȃ��ꍇ�͗�O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01321<BR>
     * <BR>
     * �Q�j�@@���ڃw�b�_����<BR>
     * �@@�P�j�Ŏ擾�����e�v�f���ɁA�ȉ��̒ʂ�����J�ݐ\���J�������f���̔z��𐶐���<BR>
     * set�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B <BR>
     * <BR>
     * �@@[�����J�ݐ\���J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�����J�ݐ\���c�k�t�H�[���}�X�^.���ڃ��x��<BR>
     * �@@�J�����ԍ��F index<BR>
     * �@@���ڌ^�F�@@�����J�ݐ\���c�k�t�H�[���}�X�^.���ڌ^<BR>
     * �@@���t�t�H�[�}�b�g�F�@@�����J�ݐ\���c�k�t�H�[���}�X�^.���t�t�H�[�}�b�g<BR>
     * �@@���͍��ڕ������F�@@�����J�ݐ\���c�k�t�H�[���}�X�^.���͍��ڕ�����<BR>
     * �@@�A�����ڃf���~�b�^�F�@@�����J�ݐ\���c�k�t�H�[���}�X�^.�A�����ڃf���~�b�^<BR>
     * �@@�Z�N�V�����ԍ��F�@@�����J�ݐ\���c�k�t�H�[���}�X�^.�Z�N�V�����ԍ�<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strAccountDiv - �����敪<BR>
     * <BR>
     * 0�F�l�A�J�E���g�@@1�F�@@�l�A�J�E���g<BR>
     * @@roseuid 41A14A220385
     */
    protected void createColumnHeader(String l_strInstitutionCode, String l_strAccountDiv)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createColumnHeader(String, String)";    
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //�P�j�@@�_�E�����[�h�t�H�[���}�X�^�擾
            List l_lisRecord = Processors.getDefaultProcessor().doFindAllQuery(
                AccOpenDlFormMasterRow.TYPE,
                "institution_code = ? AND account_div = ?",
                "column_number asc",
                null,
                new Object[]{l_strInstitutionCode, l_strAccountDiv});//DataException
            int l_intRecordCount = l_lisRecord.size();    
                
            if (l_intRecordCount == 0)
            {
                String l_strErrorMessage = 
                    "�_�E�����[�h�t�H�[���}�X�^�̎擾�Ɏ��s���܂����BinstitutionCode = " 
                    + l_strInstitutionCode 
                    + ",accountDiv = " 
                    + l_strAccountDiv;
                log.debug(l_strErrorMessage);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01321,
                    getClass().getName() + STR_METHOD_NAME,
                    l_strErrorMessage); 
            }
        
            //�Q�j�@@���ڃw�b�_����
            log.debug("l_intRecordCount = " + l_intRecordCount);
            WEB3AdminAccOpenRegistCsvColumnModel[] l_models = new WEB3AdminAccOpenRegistCsvColumnModel[l_intRecordCount];
            for (int i = 0; i < l_intRecordCount; i++)
            {
                log.debug("�Q�j�@@���ڃw�b�_����");
                AccOpenDlFormMasterRow l_row = (AccOpenDlFormMasterRow)l_lisRecord.get(i);
                SimpleDateFormat l_dateFormat = null;
                if (l_row.getDateFormat() != null)
                {
                    l_dateFormat = new SimpleDateFormat(l_row.getDateFormat());
                }               
                
                WEB3AdminAccOpenRegistCsvColumnModel l_model = 
                    new WEB3AdminAccOpenRegistCsvColumnModel(
                        l_row.getColumnLabel(), 
                        i, 
                        l_row.getColumnType(), 
                        l_dateFormat, 
                        l_row.getInputItemSymbolName(),
                        l_row.getCatDelimitter(),
                        Integer.toString(l_row.getSectionNumber()));
                l_models[i] = l_model;
            }
            
            this.setColumnHeader(l_models);
        }
        catch (DataException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (create�L�[�w�b�_)<BR>
     * �L�[�w�b�_���Z�b�g����B <BR>
     * <BR>
     * �@@�ȉ��̒ʂ蕶����̔z��𐶐����Aset�L�[�w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B <BR>
     * <BR>
     * [�L�[�w�b�_�z��] <BR>
     * <BR>
     * �|�@@index = 0 <BR>
     * �@@���ݓ�����"yyyy/MM/dd HH:mm:ss"�̌`����format����������B <BR>
     * <BR>
     * (*1) ���ݓ��� <BR>
     * TradingSystem.getSystemTimestamp()<BR>
     * @@roseuid 41A14A220394
     */
    protected void createKeyHeader() 
    {
        final String STR_METHOD_NAME = " createKeyHeader()";    
        log.entering(STR_METHOD_NAME);
        
        //index 0
        String[] l_strKeyHeaders = new String[1];
        Timestamp l_tsCurrentTime = GtlUtils.getTradingSystem( ).getSystemTimestamp();
        l_strKeyHeaders[0] = WEB3DateUtility.formatDate(l_tsCurrentTime, "yyyy/MM/dd HH:mm:ss");        
        
        log.debug("l_strKeyHeaders = " + l_strKeyHeaders[0]);
        this.setKeyHeader(l_strKeyHeaders);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set���ڒl)<BR>
     * �f�[�^���f���ɍ��ڒl���Z�b�g����B<BR>
     * <BR>
     * this.�J�����w�b�_[]�@@�̊e�v�f���ɁA�ȉ��̂P�j�`�R�j���J��Ԃ��iLOOP�j�B<BR>
     * <BR>
     * �P�j�@@���͍��ږ��擾<BR>
     * �@@this.�J�����w�b�_[index]�������J�ݐ\���J�������f���^�ɕϊ�����iCast�j�B<BR>
     * �@@this.�J�����w�b�_[index].get���͍��ڕ�����()�ɂāA���͍��ږ����擾����B<BR>
     * �@@this.�J�����w�b�_[index].get�A�����ڃf���~�b�^()�ɂāA�A�����ڃf���~�b�^��<BR>
     * �擾����B<BR>
     * �@@this.�J�����w�b�_[index].get�Z�N�V�����ԍ�()�ɂāA�Z�N�V�����ԍ����擾����B<BR>
     * <BR>
     * �Q�j�@@���͍��ڒl�擾<BR>
     * �@@�����J�݌����q.get���ڒl()�ɂāA�����J�݌����q�̊Y�����ڒl��<BR>
     * �擾����B<BR>
     * <BR>
     * �@@[get���ڒl()�Ɏw�肷�����]<BR>
     * �@@�񕨗����F�@@�P�j�Ŏ擾�������͍��ږ�<BR>
     * <BR>
     * �R�j�@@�l�Z�b�g <BR>
     * <BR>
     * �@@�R�|�P�j�@@�P���ڂ�蕡���J�������o�͂���ꍇ<BR>
     * �@@�@@�P���ڂ�蕡���J�������o�͂���ꍇ<BR>
     * �@@�@@�i�A�����ڃf���~�b�^ != null and �A�����ڃf���~�b�^ != 0�F�Ȃ��j and <BR>
     * (�Z�N�V�����ԍ� != null�j�A<BR>
     * <BR>
     *   �R�|�P�|�P�j�@@�A�����ڃf���~�b�^���S�F�X�֔ԍ��̏ꍇ <BR>
     *    [�Z�N�V�����ԍ����f0�f�̏ꍇ] <BR>
     *      �Q�j�Ŏ擾�������͍��ڒl�̏�3�����擾����B(substring(0,3)) <BR>
     *    [�Z�N�V�����ԍ����f1�f�̏ꍇ] <BR>
     *      �Q�j�Ŏ擾�������͍��ڒl�̉�4�����擾����B(substring(3,7)) <BR>
     *   �R�|�P�|�Q�j�@@�A�����ڃf���~�b�^���S�F�X�֔ԍ��ȊO�̏ꍇ <BR>
     * �@@�@@�Q�j�Ŏ擾�������͍��ڒl��A�����ڃf���~�b�^�ɂĕ����iSplit�j�����l��<BR>
     * �Z�N�V�����ԍ��Ŏw�肳�ꂽ�v�f����͍��ڒl�Ƃ���B<BR>
     * <BR>
     * �@@�R�|�Q�j�@@���ڒl�Z�b�g<BR>
     * �@@�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@�@@[set���ڒl()�Ɏw�肷�����] <BR>
     * �@@�@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�@@�J�����F�@@this.�J�����w�b�_[index]<BR>
     * �@@�@@�l�F�@@'"' + �Q�j�܂��́A�R�|�P�|�P�j�܂��́A�R�|�P�|�Q�j�Ŏ擾�������ڒl + '"' <BR>
     * <BR>
     * �@@�@@�����ڒl�̓_�u���N�I�[�g�i�h�j�ň͂ށB<BR>
     * @@param l_intLineNumber - �s�ԍ�
     * @@param l_accOpenExpAccountOpen - �����J�݌����q
     * @@roseuid 41A151DA01FE
     */
    public void setValue(int l_intLineNumber, WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setValue(int, WEB3AccOpenExpAccountOpen)";    
        log.entering(STR_METHOD_NAME);
        
        int l_intColHeaderLen = this.columnHeader.length;
        log.debug("l_intColHeaderLen = " + l_intColHeaderLen);
        for (int i = 0; i < l_intColHeaderLen; i++)
        {
            log.debug("�P�j�@@���͍��ږ��擾");
            //�P�j�@@���͍��ږ��擾
            WEB3AdminAccOpenRegistCsvColumnModel l_model = 
                (WEB3AdminAccOpenRegistCsvColumnModel)this.columnHeader[i];
            log.debug("**************i = " + i);
            String l_strInputItemSymbolName = l_model.getInputItemSymbolName();
            String l_strCatDelimitter = l_model.getCatDelimitter();
            String l_strSectionNo = l_model.getSectionNo();
                
            //�Q�j�@@���͍��ڒl�擾
            Object l_obj = l_accOpenExpAccountOpen.getItemValue(l_strInputItemSymbolName);
            String l_strValue = null;
            
            //�R�j�@@�l�Z�b�g 
            //�@@�R�|�P�j�@@�P���ڂ�蕡���J�������o�͂���ꍇ
            //�@@�@@�P���ڂ�蕡���J�������o�͂���ꍇ
            // �i�A�����ڃf���~�b�^ != null and �A�����ڃf���~�b�^ != 0�F�Ȃ��j and (�Z�N�V�����ԍ� != null�j 
            if (l_obj != null
                && l_strCatDelimitter != null
                && !WEB3CatDelimitterDef.WITHOUT.equals(l_strCatDelimitter)
                && l_strSectionNo != null)
            {
                l_strValue = l_obj.toString();
                log.debug("l_strValue = " + l_strValue);

                log.debug("�P���ڂ�蕡���J�������o�͂���ꍇ");

                // �R�|�P�|�P�j  �A�����ڃf���~�b�^���S�F�X�֔ԍ��̏ꍇ 
                //  [�Z�N�V�����ԍ����f0�f�̏ꍇ] 
                //  �Q�j�Ŏ擾�������͍��ڒl�̏�3�����擾����B(substring(0,3)) 
                //  [�Z�N�V�����ԍ����f1�f�̏ꍇ] 
                //  �Q�j�Ŏ擾�������͍��ڒl�̉�4�����擾����B(substring(3,7))
                if (WEB3CatDelimitterDef.ZIP_CODE.equals(l_strCatDelimitter))
                {
                    final String STR_NO0 = "0";
                    final String STR_NO1 = "1";

                    if (STR_NO0.equals(l_strSectionNo))
                    {                        
                        l_strValue = l_strValue.substring(0, 3);
                    }
                    else if (STR_NO1.equals(l_strSectionNo))
                    {
                        l_strValue = l_strValue.substring(3, 7);
                    }
                }
                // �R�|�P�|�Q�j�@@�A�����ڃf���~�b�^���S�F�X�֔ԍ��ȊO�̏ꍇ 
                //  �Q�j�Ŏ擾�������͍��ڒl��A�����ڃf���~�b�^�ɂĕ����iSplit�j
                //  �����l�̃Z�N�V�����ԍ��Ŏw�肳�ꂽ�v�f����͍��ڒl�Ƃ���
                else
                {
                    String[] l_strSplits = new String[] { " ", "�@@", "-" };
                    String[] l_strCatDelimitters =
                        new String[] {
                            WEB3CatDelimitterDef.HALF_SPACE,
                            WEB3CatDelimitterDef.FULL_SPACE,
                            WEB3CatDelimitterDef.HYPHEN };
                    String l_strSplit = null;
                    for (int j = 0; j < l_strCatDelimitters.length; j++)
                    {
                        if (l_strCatDelimitters[j].equals(l_strCatDelimitter))
                        {
                            l_strSplit = l_strSplits[j];
                            break;
                        }
                    }
                    if (l_strSplit == null)
                    {
                        String l_strMessage =
                            "�P���ڂ�蕡���J�������o�͂���ꍇ,�f�[�^�G���[! "
                                + "data = "
                                + l_strValue
                                + "; delimitter = "
                                + l_strCatDelimitter
                                + "; section no ="
                                + l_strSectionNo;
                        log.debug(l_strMessage);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                            this.getClass().getName() + STR_METHOD_NAME,
                            l_strMessage);
                    }

                    String[] l_strValues = l_strValue.split(l_strSplit);

                    int l_intSectionNo = Integer.parseInt(l_strSectionNo);

                    if (l_strValues == null || l_strValues.length == 0 || l_strValues.length <= l_intSectionNo)
                    {
                        String l_strMessage =
                            "�P���ڂ�蕡���J�������o�͂���ꍇ,�f�[�^�G���[! "
                                + "data = "
                                + l_strValue
                                + "; delimitter = "
                                + l_strCatDelimitter
                                + "; section no ="
                                + l_strSectionNo;
                        log.debug(l_strMessage);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                            this.getClass().getName() + STR_METHOD_NAME,
                            l_strMessage);
                    }
                    l_strValue = l_strValues[l_intSectionNo];
                }
            }
            
            //�@@�R�|�Q�j�@@���ڒl�Z�b�g<BR>
            log.debug("l_strValue = " + l_strValue);
            if (l_strValue == null)
            {
                if((l_obj instanceof String)
                    && l_model.getDateFormat() != null)
                {
                    Date l_dat = WEB3DateUtility.getDate((String)l_obj,"yyMMdd");
                    this.setValue(l_intLineNumber, l_model, l_dat); 
                }
                else if(l_obj instanceof BooleanEnum)
                {
                    Integer l_int = new Integer(((BooleanEnum)l_obj).intValue());
                    this.setValue(l_intLineNumber, l_model, l_int);
                }
                else
                {
                    this.setValue(l_intLineNumber, l_model, l_obj);            
                }
            }
            else
            {
                this.setValue(l_intLineNumber, l_model, l_strValue);            
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
