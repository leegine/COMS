head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.54.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformDownLoadCSV.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �A�����_�E�����[�hCSV�N���X(WEB3AdminInformDownLoadCSV.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/01/24 ������(���u) �쐬
Revesion History    : 2005/06/12 ������(���u) ���f���ENo.031��Ή�
Revesion History    : 2007/03/09 ������(���u) ���f���ENo.032��Ή�
Revesion History    : 2008/02/21 �g�C��(���u) ���f���ENo.126��Ή�
Revesion History    : 2008/02/29 �đo�g(���u) ����No.007
*/

package webbroker3.inform;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchCodeDef;
import webbroker3.common.define.WEB3CatDelimitterDef;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.inform.data.InformDlFormMasterParams;
import webbroker3.inform.data.InformDlFormMasterRow;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.define.WEB3InformCatDelimitterDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�A�����_�E�����[�hCSV)<BR>
 * �A�����_�E�����[�hCSV�N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3AdminInformDownLoadCSV extends WEB3GentradeCsvDataModel 
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformDownLoadCSV.class);
    
    /**
     * (MAX��������)<BR>
     * 1��̃_�E�����[�h�ł�MAX��������
     */
    public static  int maxDealNumber = 500;
    
    /**
     * (is�J�����w�b�_�s�o��)<BR>
     * �J�����w�b�_�s���o�͂��邩�ǂ����̃t���O<BR>
     *<BR>
     * true�̏ꍇ�A�J�����w�b�_���o�͂���B
     */
    public boolean isColumnHeadOut;

    /**
     * (is�J�����w�b�_�s�o��)<BR>
     * CSV�t�@@�C���ɃJ�����w�b�_�i�^�C�g��������j�s���o�͂���ꍇ��true�A<BR>
     * �v�ȏꍇ��false��ԋp����B<BR>
     *�ioverride���\�b�h�j<BR>
     *<BR>
     * this.is�J�����w�b�_�s�o�� == true �̏ꍇ��true�A<BR>
     * this.is�J�����w�b�_�s�o�� == false �̏ꍇ��false��ԋp����B<BR>
     * @@return boolean
     */
    public boolean isColumnHeadOutput()
    {
        if (this.isColumnHeadOut)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * (create�L�[�w�b�_)<BR>
     * �L�[�w�b�_���Z�b�g����B<BR>
     * <BR>
     * �ȉ��̒ʂ蕶����̔z��𐶐����Aset�L�[�w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B<BR>
     * <BR>
     * [�L�[�w�b�_�z��]<BR>
     * <BR>
     * �| index = 0<BR>
     *   ���ݓ�����"yyyy/MM/dd HH:mm:ss"�̌`����format����������B<BR>
     * <BR>
     * (*) ���ݓ���<BR>
     * TradingSystem.getSystemTimestamp()<BR>
     */
    protected void createKeyHead()
    {
        String[] l_strKeyHeader = new String[1];
        l_strKeyHeader[0] = WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyy/MM/dd HH:mm:ss");
        setKeyHeader(l_strKeyHeader);
    }
    /**
     * @@roseuid 41EE642D035B
     */
    public WEB3AdminInformDownLoadCSV() 
    {
     
    }
    /**
     * (�A�����_�E�����[�hCSV)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �C���X�^���X�𐶐����A�w�b�_�����Z�b�g����B<BR>
     * <BR>
     * �P�jsuper()���R�[�����A�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j����.is�J�����w�b�_�s�o��==true�̏ꍇ�A<BR>
     *     create�L�[�w�b�_()���R�[�����A�L�[�w�b�_���𐶐�����B<BR>
     * <BR>
     * �R�jcreate�J�����w�b�_()���R�[�����A�J�����w�b�_���𐶐�����B<BR>
     * <BR>
     *    [create�J�����w�b�_()�ɃZ�b�g�������]<BR>
     *    �،���ЃR�[�h�F ����.�،���ЃR�[�h<BR>
     *    ���X�R�[�h�F ����.���X�R�[�h<BR>
     *    �A����ʁF ����.�A�����<BR>
     * �S�j����.is�J�����w�b�_�s�o�͂�this.is�J�����w�b�_�s�o�͂ɃZ�b�g����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h
     * @@param l_strInformDiv - (�A�����)<BR>
     * �A�����
     * @@param l_isColumnHeadOutput - (is�J�����w�b�_�s�o��)<BR>
     * �J�����w�b�_�s�o�͗v�ۂ̃t���O
     * @@roseuid 41BEC68A01E1
     */
    public WEB3AdminInformDownLoadCSV(
        String l_strInstitutionCode, 
        String l_strBranchCode, 
        String l_strInformDiv,
        boolean l_isColumnHeadOutput) 
        throws WEB3BaseException
    {
        //�P�jsuper()���R�[�����A�C���X�^���X�𐶐�����B
        super();
        
        //�Q�j����.is�J�����w�b�_�s�o��==true�̏ꍇ�A<BR>
        //    create�L�[�w�b�_()���R�[�����A�L�[�w�b�_���𐶐�����B<BR>
		if (l_isColumnHeadOutput)
	        this.createKeyHead();
        
        //3�jcreate�J�����w�b�_()���R�[�����A�J�����w�b�_���𐶐�����
        this.createColumnHead(
            l_strInstitutionCode,
            l_strBranchCode,
            l_strInformDiv);
            
        //�S�j����.is�J�����w�b�_�s�o�͂�this.is�J�����w�b�_�s�o�͂ɃZ�b�g����B
        this.isColumnHeadOut = l_isColumnHeadOutput;
    }
    
    /**
     * (create�J�����w�b�_)<BR>
     * �J�����w�b�_���Z�b�g����B<BR>
     * <BR>
     * �ȉ��̂Ƃ����CSV�J�������f���̔z��𐶐����A<BR>
     * set�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B<BR>
     * <BR>
     * �P�j�_�E�����[�h�t�H�[���}�X�^�擾<BR>
     *   �ȉ��̏����ŁA�A�����c�k�t�H�[���}�X�^�e�[�u������������B<BR>
     * <BR>
     *   [����]<BR>
     *   �،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     *   ���X�R�[�h = ����.���X�R�[�h<BR>
     *   �A����� = ����.�A�����<BR>
     * <BR>
     *   [�擾��]<BR>
     *   �J�����ԍ� �����i�Fasc�j<BR>
     * <BR>
     *   �Y���s�����݂��Ȃ��ꍇ�͕��X�R�[�h�̏������ȉ��̂悤�ɂ���B<BR>
     *   <BR>
     *   [����]<BR>
     *   ���X�R�[�h = "000"<BR>
     * <BR>
     * �Q�j���ڃw�b�_���� <BR>
     * �P�j�Ŏ擾�����e�v�f���ɁA�ȉ��̒ʂ�A�����J�������f���̔z��𐶐����A<BR>
     *     set�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����
     * <BR>
     *   [�A�����J�������f�� �R���X�g���N�^�̈���]<BR>
     *   ���ڃ��x���F �A�����c�k�t�H�[���}�X�^.���ڃ��x��<BR>
     *   �J�����ԍ��F index<BR>
     *   ���ڌ^�F �A�����c�k�t�H�[���}�X�^.���ڌ^<BR>
     *   ���t�t�H�[�}�b�g�F �A�����c�k�t�H�[���}�X�^.���t�t�H�[�}�b�g<BR>
     *   ���͍��ڕ������F �A�����c�k�t�H�[���}�X�^.���͍��ڕ�����<BR>
     *   �A�����ڃf���~�b�^�F �A�����c�k�t�H�[���}�X�^.�A�����ڃf���~�b�^<BR>
     *   �Z�N�V�����ԍ��F �A�����c�k�t�H�[���}�X�^.�Z�N�V�����ԍ�<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h
     * @@param l_strInformDiv - (�A�����)<BR>
     * �A�����
     * @@roseuid 41BEC7D80230
     */
    protected void createColumnHead(
        String l_strInstitutionCode, 
        String l_strBranchCode, 
        String l_strInformDiv) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createColumnHead()";
        log.entering(STR_METHOD_NAME);
     
        try
        {   
            //�P�j�_�E�����[�h�t�H�[���}�X�^�擾<BR>
            // �ȉ��̏����ŁA�A�����c�k�t�H�[���}�X�^�e�[�u������������B<BR>
            // [����]<BR>
            //    �،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
            //    ���X�R�[�h = ����.���X�R�[�h<BR>
            //    �A����� = ����.�A�����<BR>
            // [�擾��]<BR>
            //    �J�����ԍ� �����i�Fasc�j<BR>
            StringBuffer l_strWhere = new StringBuffer();
            l_strWhere.append(" institution_code = ? ");
            l_strWhere.append(" and branch_code = ? ");
            l_strWhere.append(" and inform_div = ? ");
    
            Object[] l_objWhere = {
                l_strInstitutionCode,
                l_strBranchCode,
                l_strInformDiv};
    
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            List l_lisRecords = l_QueryProcessor.doFindAllQuery(
                InformDlFormMasterRow.TYPE,
                l_strWhere.toString(),
                "column_number asc",
                null,
                l_objWhere);

            int l_intListRecordCnt = 0;
            if (l_lisRecords != null)
            {
                l_intListRecordCnt = l_lisRecords.size();
            }

            if (l_intListRecordCnt == 0)
            {
                log.debug("�Y���s�����݂��Ȃ��ꍇ�͕��X�R�[�h�̏������ȉ��̂悤�ɂ���B");
                // �Y���s�����݂��Ȃ��ꍇ�͕��X�R�[�h�̏������ȉ��̂悤�ɂ���B<BR>
                // [����]<BR>
                //    ���X�R�[�h = "000"<BR>
                Object[] l_objWhereSch = {
                    l_strInstitutionCode,
                    WEB3BranchCodeDef.DEFAULT,
                    l_strInformDiv};

                l_lisRecords = l_QueryProcessor.doFindAllQuery(
                    InformDlFormMasterRow.TYPE,
                    l_strWhere.toString(),
                    "column_number asc",
                    null,
                    l_objWhereSch);

                if (l_lisRecords != null)
                {
                    l_intListRecordCnt = l_lisRecords.size();
                }
            }
            log.debug("�����������R�[�h�� = " + l_intListRecordCnt);

            List l_lisColumnHeader = new ArrayList();
            for (int i = 0; i < l_intListRecordCnt; i++)
            {
                //�Q�j���ڃw�b�_���� <BR>
                InformDlFormMasterRow l_masterRow = (InformDlFormMasterRow)l_lisRecords.get(i);
                InformDlFormMasterParams l_masterParams = new InformDlFormMasterParams(l_masterRow);
                
                SimpleDateFormat l_dateFormat = null;
                if (l_masterParams.getDateFormat() != null)
                {
                    l_dateFormat = new SimpleDateFormat(l_masterParams.getDateFormat());
                }
                
                WEB3AdminInformColumnModel l_columnModel = new WEB3AdminInformColumnModel(
                    l_masterParams.getColumnLabel(),
                    i,
                    l_masterParams.getColumnType(),
                    l_dateFormat,
                    l_masterParams.getInputItemSymbolName(),
                    l_masterParams.getCatDelimitter(),
                    String.valueOf(l_masterParams.getSectionNumber()));

                l_lisColumnHeader.add(l_columnModel);
            }
            
            WEB3GentradeCsvColumnModel[] l_columnHeader = 
                new WEB3GentradeCsvColumnModel[l_intListRecordCnt];
            l_lisColumnHeader.toArray(l_columnHeader);
            setColumnHeader(l_columnHeader);
        }
        catch (DataQueryException l_dqEx)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dqEx);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dqEx.getMessage(),
                l_dqEx);
        }
        catch (DataNetworkException l_dnEx)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dnEx);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dnEx.getMessage(),
                l_dnEx);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set���ڒl)<BR>
     * �f�[�^���f���ɍ��ڒl���Z�b�g����B<BR>
     * <BR>
     * this.�J�����w�b�_[] �̊e�v�f���ɁA�ȉ��̂P�j�`�R�j���J��Ԃ��B<BR>
     * <BR>
     * �P�j���ږ��擾<BR>
     *   �Ethis.�J�����w�b�_[index] ��A�����J�������f���^�ɕϊ�����iCast�j�B<BR>
     *   �Ethis.�J�����w�b�_[index].get���͍��ڕ�����()�ɂāA���ږ����擾����B<BR>
     *   �Ethis.�J�����w�b�_[index].get�A�����ڃf���~�b�^()�ɂāA�A�����ڃf���~�b�^���擾����B<BR>
     *   �Ethis.�J�����w�b�_[index].get�Z�N�V�����ԍ�()�ɂāA�Z�N�V�����ԍ����擾����B<BR>
     * <BR>
     * �Q�j���ڒl�擾<BR>
     * �@@�P�j�Ŏ擾�������ږ��ƈ�v����e��A��Params�̍��ڂ���l���擾����B<BR>
     * �@@�@@�����ږ����ڋq�R�[�h�A���A�e��A��Params.�ڋq�R�[�h��null�łȂ��ꍇ�A<BR>
     * �@@�@@�e��A��Params.�ڋq�R�[�h�̓�6���isubstring(0,6)�j���擾����B<BR>
     * <BR>
     * �R�j�l�Z�b�g<BR>
     * <BR>
     * �R�|�P�j�P���ڂ�蕡���J�������o�͂���ꍇ<BR>
     *   �i�A�����ڃf���~�b�^ != null and �A�����ڃf���~�b�^ != 0�F�Ȃ��j and (�Z�N�V�����ԍ� != null�j�A<BR>
     * �@@�Q�j�Ŏ擾�������ڒl��this.get�A�����ڃf���~�b�^�l()�ɂĕ��������l�̃Z�N�V�����ԍ��Ŏw�肳�ꂽ�v�f��<BR>
     * �@@���ڒl�Ƃ���B<BR>
     * <BR>
     * �@@[get�A�����ڃf���~�b�^�l()�ɃZ�b�g�������]<BR>
     * �@@�A�����ڃf���~�b�^�F �A�����ڃf���~�b�^<BR>
     * <BR>
     * �R�|�Q�j���ڒl�Z�b�g<BR>
     *   this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *   [set���ڒl()�ɃZ�b�g�������]<BR>
     *   �s�ԍ��F �����̍s�ԍ�<BR>
     *   �J�����F this.�J�����w�b�_[index]
     *   �l�F �Q�j�܂��́A�R�|�P�j�Ŏ擾�������ڒl<BR>
     * <BR>
     * @@param l_intPageNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_variousInformParams - (�A�����)<BR>
     * �e��A���s�I�u�W�F�N�g
     * @@roseuid 41BED4E402BC
     */
    public void setItemValue(
        int l_intPageNumber, 
        VariousInformParams l_variousInformParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "setItemValue()";
        log.entering(STR_METHOD_NAME);
        
        // this.�J�����w�b�_[] �̊e�v�f���ɁA�ȉ��̂P�j�`�R�j���J��Ԃ�
        int l_intColumnHeaderLth = this.columnHeader.length;
        log.debug("�J�����w�b�_���x = " + l_intColumnHeaderLth);
        for (int i = 0; i < l_intColumnHeaderLth; i++)
        {
            log.debug("index:" + i);
            //�P�j���ږ��擾
            // this.�J�����w�b�_[index] ��A�����J�������f���^�ɕϊ�����iCast�j�B
            WEB3AdminInformColumnModel l_columnModel = (WEB3AdminInformColumnModel)this.columnHeader[i];

            // this.�J�����w�b�_[index].get���͍��ڕ�����()�ɂāA���ږ����擾����B
            String l_strSymbolName = l_columnModel.getInputItemSymbolName();
            log.debug("���͍��ڕ�����:" + l_strSymbolName);

            // this.�J�����w�b�_[index].get�A�����ڃf���~�b�^()�ɂāA�A�����ڃf���~�b�^���擾����B
            String l_strCatDelimitter = l_columnModel.getCatDelimitter();
            log.debug("�A�����ڃf���~�b�^:" + l_strCatDelimitter);
            
            // this.�J�����w�b�_[index].get�Z�N�V�����ԍ�()�ɂāA�Z�N�V�����ԍ����擾����B
            String l_strSectionNumber = l_columnModel.getSectionNumber();
            log.debug("�Z�N�V�����ԍ�:" + l_strSectionNumber);

            //�Q�j���ڒl�擾<BR>
            //  �P�j�Ŏ擾�������ږ��ƈ�v����e��A��Params�̍��ڂ���l���擾����B<BR>
            //�����ږ����ڋq�R�[�h�A���A�e��A��Params.�ڋq�R�[�h��null�łȂ��ꍇ�A
            //�e��A��Params.�ڋq�R�[�h�̓�6���isubstring(0,6)�j���擾����B
            // ���ڒl
            Object l_objSymbolValue = null;
            if ("account_code".equals(l_strSymbolName))
            {
                if (l_variousInformParams.getAccountCode() != null)
                {
                    l_objSymbolValue = l_variousInformParams.getAccountCode().substring(0, 6);
                }
                else
                {
                    l_objSymbolValue = null;
                }
            }
            else
            {
                l_objSymbolValue = l_variousInformParams.getColumn(l_strSymbolName);
            }

            String l_strSymbolValue = "";
            if (l_objSymbolValue != null && l_objSymbolValue instanceof String)
            {               
                l_strSymbolValue = l_objSymbolValue.toString();
                log.debug("���͍��ڒl�擾:" + l_strSymbolValue);
            }          

            //�R�j�l�Z�b�g<BR>
            // �R�|�P�j�P���ڂ�蕡���J�������o�͂���ꍇ<BR>
            //   �i�A�����ڃf���~�b�^ != null 
            //     and �A�����ڃf���~�b�^ != 0�F�Ȃ��j 
            //     and (�Z�N�V�����ԍ� != null�j<BR>
            if (!("".equals(l_strSymbolValue))
                && l_strCatDelimitter != null
                && !WEB3CatDelimitterDef.WITHOUT.equals(l_strCatDelimitter)
                && l_strSectionNumber != null)
            {
                //  �Q�j�Ŏ擾�������ڒl��this.get�A�����ڃf���~�b�^�l()�ɂĕ��������l�̃Z�N�V�����ԍ��Ŏw�肳�ꂽ�v�f��
                //�@@���ڒl�Ƃ���B
                //�@@[get�A�����ڃf���~�b�^�l()�ɃZ�b�g�������]
                //  �A�����ڃf���~�b�^�F �A�����ڃf���~�b�^
                String l_strDivisionChar = this.getCatDelimitter(l_strCatDelimitter);
                String[] l_strArraySymbolValues = l_strSymbolValue.split(l_strDivisionChar);

                // 0����w�肷��
                int l_intSectionNumber = Integer.parseInt(l_strSectionNumber);
     
                if (l_strArraySymbolValues == null 
                    || l_strArraySymbolValues.length == 0 
                    || l_strArraySymbolValues.length <= l_intSectionNumber)
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                
                // �Z�N�V�����ԍ��Ŏw�肳�ꂽ�v�f�����ڒl�Ƃ���B
                l_strSymbolValue = l_strArraySymbolValues[l_intSectionNumber];
            }
            
            // �R�|�Q�j���ڒl�Z�b�g: this.set���ڒl()�ɂč��ڒl���Z�b�g����B
            // [set���ڒl()�ɃZ�b�g�������]
            //   �s�ԍ��F �����̍s�ԍ�
            //   �J�����F this.�J�����w�b�_[index]
            //   �l�F �Q�j�܂��́A�R�|�P�j�Ŏ擾�������ڒl
            log.debug("���ڒl�Z�b�g�̊J�n LOOP " + i);
            log.debug("�s�ԍ� �F" + l_intPageNumber);
            log.debug("�J���� �F" + this.columnHeader[i]);
            if (!("".equals(l_strSymbolValue)))
            {
                log.debug("�������̒l�F" + l_strSymbolValue);
                this.setValue(
                    l_intPageNumber,
                    this.columnHeader[i],
                    l_strSymbolValue);
            }
            else
            {
                if (l_objSymbolValue != null && l_objSymbolValue instanceof Date)
                {
                    log.debug("���t�̒l�F" + l_objSymbolValue);
                    DateFormat l_dateFormat = this.columnHeader[i].getDateFormat();
                    String l_strAfterFormat = l_dateFormat.format(l_objSymbolValue);
                    if (l_strAfterFormat == null)
                    {
                        l_strAfterFormat = "";
                    }
                    
                    this.setValue(
                        l_intPageNumber,
                        this.columnHeader[i],
                        l_strAfterFormat);                    
                }
                else
                {
                    log.debug("�I�u�W�F�N�g�̒l�F" + l_objSymbolValue);
                    if (l_objSymbolValue == null)
                    {
                        this.setValue(
                        	l_intPageNumber,
                            this.columnHeader[i],
                            "");           
                    }
                    else
                    {
                        this.setValue(
                            l_intPageNumber,
                            this.columnHeader[i],
                            String.valueOf(l_objSymbolValue));                        
                    }

                }
            }
            
            log.debug("���ڒl�Z�b�g�̏I�� LOOP " + i);
        }

        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (getMAX��������)<BR>
     * MAX�����������擾����B<BR>
     * <BR>
     * this.MAX����������ԋp����B
     * @@return int
     * @@roseuid 41C018DB00D8
     */
    public int getMaxDealNumber() 
    {
        return maxDealNumber;
    }

    /**
     * (get�A�����ڃf���~�b�^�l)<BR>
     * �A�����ڃf���~�b�^�l���擾����B<BR>
     * <BR>
     * ����.�A�����ڃf���~�b�^�ɊY������l��ԋp����B<BR>
     * <BR>
     * �P�j�@@����.�A�����ڃf���~�b�^ == 1 �̏ꍇ�A���pSPACE��ԋp�B<BR>
     * <BR>
     * �Q�j�@@����.�A�����ڃf���~�b�^ == 2 �̏ꍇ�A�S�pSPACE��ԋp�B<BR>
     * <BR>
     * �R�j�@@����.�A�����ڃf���~�b�^ == 3 �̏ꍇ�A�n�C�t���i"-"�j��ԋp�B<BR>
     * <BR>
     * �S�j�@@��L�ȊO�̏ꍇ�A�󕶎��i""�j��ԋp�B<BR>
     * <BR>
     * @@param l_strCatDelimitter - (�A�����ڃf���~�b�^)<BR>
     * �A�����ڃf���~�b�^<BR>
     * @@return String
     */
    public String getCatDelimitter(String l_strCatDelimitter)
    {
        final String STR_METHOD_NAME = "getCatDelimitter(String)";
        log.entering(STR_METHOD_NAME);

        //����.�A�����ڃf���~�b�^�ɊY������l��ԋp����B
        //�P�j�@@����.�A�����ڃf���~�b�^ == 1 �̏ꍇ�A���pSPACE��ԋp�B
        if (WEB3CatDelimitterDef.HALF_SPACE.equals(l_strCatDelimitter))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3InformCatDelimitterDef.HALF_SPACE;
        }

        //�Q�j�@@����.�A�����ڃf���~�b�^ == 2 �̏ꍇ�A�S�pSPACE��ԋp�B
        else if (WEB3CatDelimitterDef.FULL_SPACE.equals(l_strCatDelimitter))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3InformCatDelimitterDef.FULL_SPACE;
        }

        //�R�j�@@����.�A�����ڃf���~�b�^ == 3 �̏ꍇ�A�n�C�t���i"-"�j��ԋp�B
        else if (WEB3CatDelimitterDef.HYPHEN.equals(l_strCatDelimitter))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3InformCatDelimitterDef.HYPHEN;
        }

        //�S�j�@@��L�ȊO�̏ꍇ�A�󕶎��i""�j��ԋp�B
        log.exiting(STR_METHOD_NAME);
        return WEB3InformCatDelimitterDef.EMPTY_WORD;
    }
    
}
@
