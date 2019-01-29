head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeCsvDataModel.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : CSV�f�[�^���f��(WEB3GentradeCsvDataModel.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/07/28 �� ��(���u) �V�K�쐬
Revesion History    : 2007/12/21 �� �g(���u) �d�l�ύX ���f��303
Revesion History    : 2007/12/25 �� �g(���u) �d�l�ύX ���f��305
Revesion History    : 2008/01/10 �h �C(���u) �d�l�ύX ���f��307
Revesion History    : 2008/01/26 �h �C(���u) �d�l�ύX ���f��314�A315
*/

package webbroker3.gentrade;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.define.WEB3GentradeDateAnalysisFlagDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (CSV�f�[�^���f��) <BR>
 * �iWEB3CsvDataModel�j <BR>
 * CSV�t�@@�C���f�[�^���f���N���X�B <BR>
 * CSV�_�E�����[�h�^�A�b�v���[�h�ɂĎg�p����B<BR>
 * <BR>
 * [CSV�t�@@�C���t�H�[�}�b�g�ɂ���] <BR>
 * 1�s�ځiindex=0�j�̓L�[�w�b�_�ƔF������B <BR>
 * 2�s�ځiindex=1�j�͍s�w�b�_�i�^�C�g��������j�ƔF������B<BR>
 * �� �A���A�iis�J�����w�b�_�o�� == false�j�̏ꍇ�A<BR>
 * �^�C�g��������Ȃ��Ɣ��肷��B<BR>
 * <BR>
 * �ȍ~�𖾍׍s�ƔF������B<BR>
 * <BR>
 * --- CSV file sample---------------<BR>
 * 2004/06/21 16:00:03,86010,(��)��a�،��O���[�v�{��<BR>
 * ���X�R�[�h,�ڋq�R�[�h,�ڋq��,����<BR>
 * 625,2512211,���c�@@�N��,5000<BR>
 * 624,2412339,�X�c�@@����,5500<BR>
 * 610,2110991,�V���@@�M��,2000<BR>
 * 610,2121400,�H��@@�P��,2500<BR>
 * --- CSV file sample---------------<BR>
 */
public class WEB3GentradeCsvDataModel
{

    /**
     * ���O�o�̓I�u�W�F�N�g�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeCsvDataModel.class);

    /**
     * �iregex�j<BR>
     * ��؂蕶��<BR>
     * CSV�t�@@�C�����̍��ڋ�؂�f���~�b�^���`����B<BR>
     * ���@@�J���}�i�C�j<BR>
     */
    public static String regex = ",";

    /**
     * �ikeyHeader�j<BR>
     * <BR>
     * �L�[�w�b�_�B<BR>
     * CVS�t�@@�C����1�s�ځiindex=0�j�ɏo�͂����f�[�^�̔z��B<BR>
     */
    protected String[] keyHeader;

    /**
     * �icolumnHeader�j<BR>
     * <BR>
     * �J�����w�b�_�B<BR>
     * �e�J�����̍��ڃ��x���Ƒ������`����I�u�W�F�N�g�̔z��B<BR>
     * ���ڃ��x���́A�_�E�����[�h�̏ꍇ<BR>
     * CVS�t�@@�C����2�s�ځiindex=1�j�ɏo�͂����B<BR>
     */
    protected WEB3GentradeCsvColumnModel[] columnHeader;

    /**
     * (���׍s) <BR>
     * �irows�j<BR>
     * <BR>
     * ���׍s�f�[�^�̏W���B<BR>
     * CVS�t�@@�C���̖��׍s�ɏo�͂����f�[�^�s�̏W���B<BR>
     * <BR>
     * ���@@ArrayList�ɃZ�b�g����v�f�i�P���׍s�̃f�[�^���i�[�����j�́A<BR>
     * Object�̔z��i�FObject[]�j�B<BR>
     */
    protected ArrayList rows = new ArrayList();

    /**
     * (CSV�f�[�^���f��) <BR>
     * �iWEB3CsvDataModel�j<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@return webbroker3.gentrade.WEB3CsvDataModel
     * @@roseuid 40E2179203C1
     */
    public WEB3GentradeCsvDataModel()
    {

    }

    /**
     * (is�J�����w�b�_�s�o��) <BR>
     * CSV�t�@@�C���ɃJ�����w�b�_�i�^�C�g��������j�s���o�͂���ꍇ��true�A<BR>
     * �s�v�ȏꍇ��false��ԋp����B<BR>
     * <BR>
     * ���Y�N���X�ł́ADefault�ltrue��ԋp����B<BR>
     * �i�s�v�ȏꍇ�́A�T�u�N���X�ɂ�override����j<BR>
     * @@return boolean
     * @@roseuid 40F5032500D4
     */
    public boolean isColumnHeadOutput()
    {
        return true;
    }

    /**
     * (get���ڒl) <BR>
     * �igetValue�j<BR>
     * �w��̍s�^����A�l��ԋp����B<BR>
     * <BR>
     * �P�j�@@�s�擾<BR>
     * �@@���׍s��������̍s�ԍ��̗v�f���擾���AObject[]�^�ɕϊ�����B<BR>
     * <BR>
     * �Q�j�@@�l�擾<BR>
     * �@@�P�j�Ŏ擾����Object�z����A�����̃J�����������J�����ԍ��̗v�f<BR>
     *   ��ԋp����B<BR>
     * <BR>
     * // Object l_rowValues[] = (Object[])this.���׍s.get(�s�ԍ�);<BR>
     * // return l_rowValues[�J����.get��ԍ�()];<BR>
     * @@param l_intLineNumber - �s�ԍ� <BR>
     * �s�ԍ����w�肷��B <BR>
     * 
     * @@param l_csvColumnModel - �J���� <BR>
     * �J�����i��j���w�肷��B<BR>
     * 
     * @@return Object
     * @@roseuid 40E21AAE0299
     */
    public Object getValue(
        int l_intLineNumber,
        WEB3GentradeCsvColumnModel l_csvColumnModel)
    {
        final String STR_METHOD_NAME = "getValue(int," +
            "WEB3GentradeCsvColumnModel)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�s�擾
        //���׍s��������̍s�ԍ��̗v�f���擾���AObject[]�^�ɕϊ�����B
        Object l_rowValues[] = (Object[])this.rows.get(l_intLineNumber);

        //�P�j�Ŏ擾����Object�z����A�����̃J�����������J�����ԍ��̗v�f��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_rowValues[l_csvColumnModel.getColumnNumber()];
    }

    /**
     * (get���ڒlFrom���׍s) <BR>
     * �igetValueFromTokenline�j<BR>
     * <BR>
     * �����̖��׍s�̎w�����l��ԋp����B<BR>
     * <BR>
     * �P�j�@@���׍s��� <BR>
     * �@@���׍s������.split(CSV�f�[�^���f��.��؂蕶��)�ɂāA<BR>
     *   ��؂蕶�����Ƃ�token[]�ɕ�������B<BR>
     * <BR>
     * �Q�j�@@�l�擾 <BR>
     * �@@�P�j�Ŏ擾����token[]�́A�w���(*1)�Ԗڂ̗v�f���擾���A<BR>
     * �J����.get���ڌ^()�������f�[�^�^�ɂĕԋp����B<BR>
     * <BR>
     * �@@(*1) �w���index�̎擾<BR>
     * �@@�J����.get��ԍ�()<BR>
     * <BR>
     *
     * @@param l_strRows - ���׍s������ <BR>
     * <BR>
     * �� ","����؂蕶���ɂ������׍s������B<BR>
     * 
     * @@param l_csvColumnModel - �J���� <BR>
     * �J�����i��j���w�肷��B<BR>
     * 
     * @@return Object
     * @@throws WEB3SystemLayerException
     * @@roseuid 40F48DAD0312
     * @@see String#split(String)
     */
    public Object getValueFromTokenline(
        String l_strRows,
        WEB3GentradeCsvColumnModel l_csvColumnModel)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getValueFromTokenline(String," +
            "WEB3GentradeCsvColumnModel)";
        log.entering(STR_METHOD_NAME);
        
        if ((l_strRows == null) || (l_strRows.equals("")))
        {
            log.error("���׍s�����񂪕s��");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "."
                    + STR_METHOD_NAME,
                "���׍s������ = null");     
        }
        if ((l_csvColumnModel == null))
        {
            log.error("�J�������s��");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "."
                    + STR_METHOD_NAME,
                "�J�����J�����i��j = null");     
        }
              
        //�P�j�@@���׍s���
        String[] token = l_strRows.split(regex);
        
        //�P�j�Ŏ擾����token[]�́A�w���(*1)�Ԗڂ̗v�f���擾���A
        //    �J����.get���ڌ^()�������f�[�^�^�ɂĕԋp����B
        int l_intColumnModel = l_csvColumnModel.getColumnType();
        try
        {
            //���ڌ^_������
            if (l_intColumnModel == WEB3GentradeCsvColumnModel.STRINGTYPE)
            {
                log.exiting(STR_METHOD_NAME);
                return token[l_csvColumnModel.getColumnNumber()];
            }
            //���ڌ^_���t
            else if (l_intColumnModel == WEB3GentradeCsvColumnModel.DATETYPE)
            {
                DateFormat l_dateFormat = l_csvColumnModel.getDateFormat();
                Date l_datValue = l_dateFormat.parse(token[l_csvColumnModel.getColumnNumber()]);
                log.exiting(STR_METHOD_NAME);
                return l_datValue;
            }
            //���ڌ^_���l�idouble�j
            else if (l_intColumnModel == WEB3GentradeCsvColumnModel.DOUBLETYPE)
            {
                Double l_dblValue = new Double(token[l_csvColumnModel.getColumnNumber()]);
                log.exiting(STR_METHOD_NAME);
                return l_dblValue;
            }
            //���ڌ^_���l�iint�j
            else if (l_intColumnModel == WEB3GentradeCsvColumnModel.INTEGERTYPE)
            {
                Integer l_intValue = Integer.valueOf(token[l_csvColumnModel.getColumnNumber()]);
                log.exiting(STR_METHOD_NAME);
                return l_intValue;
            }
            //���ڌ^_���l
            else if (l_intColumnModel == WEB3GentradeCsvColumnModel.LONGTYPE)
            {
                Long l_lngValue = Long.valueOf(token[l_csvColumnModel.getColumnNumber()]);
                log.exiting(STR_METHOD_NAME);
                return l_lngValue;
            }
            //���ڌ^_���t����
            else if (l_intColumnModel == WEB3GentradeCsvColumnModel.TIMESTAMPTYPE)
            {
                DateFormat l_df = l_csvColumnModel.getDateFormat();
                Timestamp l_tsValue = new Timestamp(l_df.parse(token[l_csvColumnModel.getColumnNumber()]).getTime());
                log.exiting(STR_METHOD_NAME);
                return l_tsValue;
            }
            //token[]�̗v�f�� != this.�J�����w�b�_[]�̗v�f���������łȂ��ꍇ�́A
            //��O���X���[����B
            else
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80022,
                    this.getClass().getName() + "." + STR_METHOD_NAME
                    );  
            }
        }
        //token[index]���Athis.�J�����w�b�_[index].get���ڌ^()��
        //�ϊ��ł��Ȃ��ꍇ�́A��O���X���[����B
        catch (ParseException pex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80023,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                pex.getMessage(),
                pex);
        }
    }

    /**
     * (get���ڒl������) <BR>
     * �igetStringValue�j<BR>
     * �w��̍s�^�J�������A�l�𕶎���^�ɂĕԋp����B<BR>
     * <BR>
     * �P�j�@@�l�擾 <BR>
     * �@@this.get���ڒl()�ɂāA�Y�����ڒl���擾����B<BR>
     * <BR>
     * �@@[this.get���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�����̃J����<BR>
     * <BR>
     * �Q�j�@@�t�H�[�}�b�g����������ԋp<BR>
     * �@@�����̃J����.get���t�t�H�[�}�b�g()�ɂāA���t�t�H�[�}�b�g���擾����B<BR>
     * <BR>
     * �@@�|�i���t�t�H�[�}�b�g == null�j�̏ꍇ�́A<BR>
     * �@@�@@�P�j�Ŏ擾�������ڒl��toString()�ɂĕ�����ɕϊ����ԋp����B<BR>
     * �@@�@@���@@������".0"�̏ꍇ�́A".0"���폜����B<BR>
     * <BR>
     * �@@�|�i���t�t�H�[�}�b�g != null�j�̏ꍇ�́A<BR>
     * �@@�@@�P�j�Ŏ擾�������ڒl��<BR>
     *     �擾�������t�t�H�[�}�b�g�ɂ�format����������ɕϊ����ԋp����B<BR>
     * @@param l_intLineNumber - �s�ԍ� <BR>
     * �s�ԍ����w�肷��B<BR>
     * @@param l_csvColumnModel - �J����<BR>
     * �J�����i��j���w�肷��B<BR>
     * 
     * @@return String
     * @@roseuid 40E2498B0076
     */
    public String getStringValue(
        int l_intLineNumber,
        WEB3GentradeCsvColumnModel l_csvColumnModel)
    {
        final String STR_METHOD_NAME = "getStringValue(int,WEB3GentradeCsvColumnModel)";
        log.entering(STR_METHOD_NAME);
        
        String l_strReturn = null;
        
        //�P�j�@@�l�擾 
        Object l_obj = null;
        l_obj = this.getValue(l_intLineNumber, l_csvColumnModel);
        Date l_dat = null;
        String l_strTemp = null;

        if (l_obj == null)
        {
            l_strTemp = "";
            return l_strTemp;
        }
        if (l_obj instanceof Date)
        {
            l_dat = (Date)l_obj;
        }
        else
        {
            l_strTemp = l_obj.toString();
        }
        
        //���t�t�H�[�}�b�g���擾����
        DateFormat l_dateFormat = l_csvColumnModel.getDateFormat();
        
        //*�i���t�t�H�[�}�b�g == null�j�̏ꍇ�́A<BR>
        //* �P�j�Ŏ擾�������ڒl��toString()�ɂĕ�����ɕϊ����ԋp����B<BR>
        //* ���@@������".0"�̏ꍇ�́A".0"���폜����B
        if (l_dateFormat == null)
        {
            
            if (l_strTemp.length() > 2)
            {
                int l_intLength = l_strTemp.length();
                String l_strSubString = l_strTemp.substring(l_intLength - 2, l_intLength);
                if (".0".equals(l_strSubString))
                {
                    l_strReturn = l_strTemp.substring(0, l_intLength - 2);
                }
                else
                {
                    l_strReturn = l_strTemp;
                }
            }
            else
            {
                l_strReturn = l_strTemp;
            }
        }
        //*�i���t�t�H�[�}�b�g != null�j�̏ꍇ�́A<BR>
        //* �P�j�Ŏ擾�������ڒl��<BR>
        //*  �擾�������t�t�H�[�}�b�g�ɂ�format����������ɕϊ����ԋp����B
        else
        {
            if(l_dat == null)
            {
                return l_strTemp;
            }
            l_strReturn = l_dateFormat.format(l_dat);
            
        }

        log.exiting(STR_METHOD_NAME);
        return l_strReturn;
    }

    /**
     * (getCSV�t�@@�C���s) <BR>
     * �igetCsvFileLines�j<BR>
     * <BR>
     * CSV�t�@@�C���ɏo�͂���f�[�^���A�s���̔z��ɂĕԋp����B<BR>
     * <BR>
     * �P�j�@@ArrayList����<BR>
     * �@@new ArrayList() <BR>
     * <BR>
     * �Q�j�@@�L�[�w�b�_�s�o��<BR>
     *(this.�L�[�w�b�_[] != null�̏ꍇ) <BR>
     *  this.�L�[�w�b�_[]�̊e�v�f����؂蕶��(*1)�ŋ�؂���������ɘA�����A<BR>
     *  ArrayList�ɒǉ�����B<BR>
     *(��L�ȊO�̏ꍇ)<BR>
     *  space������(" ")��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * (*1) ��؂蕶��<BR>
     * CSV�f�[�^���f��.��؂蕶��<BR>
     * <BR>
     * �R�j�@@�w�b�_�s�i�^�C�g��������j�o��<BR>
     * �@@�iis�J�����w�b�_�o��() == true�j�̏ꍇ�̂ݏ������{�B<BR>
     * �@@this.�J�����w�b�_[]�̊e�v�f��荀�ڃ��x�����擾����B<BR>
     * �e���ڃ��x�����A��؂蕶��(*1)�ŋ�؂���������ɘA�����A<BR>
     * ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �S�j�@@���׍s�o��<BR>
     * �@@this.���׍s�i�FArrayList�j�̗v�f�ԍ��irowIndex�j���ɁA<BR>
     *   �ȉ����J��Ԃ��B<BR>
     * �@@// for (int rowIndex = 0; rowIndex &lt; this.���׍s.size(); rowIndex ++) <BR>
     *
     * <BR>
     * �@@�S�|�P�j�@@�e���ڂ̃f�[�^�擾<BR>
     * �@@�@@this.�J�����w�b�_�i�FCSV�J�������f��[]�j�̗v�f���icolumnIndex�j���A<BR>
     * ���ڒl�擾���J��Ԃ��B<BR>
     * �@@�@@// for (int columnIndex = 0; columnIndex &lt; this.�J�����w�b�_.length; columnIndex ++)<BR>
     * �@@�@@this.get���ڒl������()�ɂāA�e�J�����̃f�[�^��������擾����B<BR>
     * <BR>
     * �@@�@@[get���ڒl������()�Ɏw�肷�����]<BR>
     * �@@�@@�s�ԍ��F�@@���׍s�̗v�f�ԍ��irowIndex�j<BR>
     * �@@�@@�J�����F�@@this.�J�����w�b�_[columnIndex]<BR>
     * <BR>
     * �@@�S�|�Q�j�@@�f�[�^�s�쐬<BR>
     * �@@�@@�S�|�P�j�Ŏ擾�����e���ڒl���A��؂蕶��(*1)��<BR>
     * ��؂���������ɘA�����AArrayList�ɒǉ�����B<BR>
     * <BR>
     * �T�j�@@CSV�t�@@�C���s�ԋp<BR>
     * �@@ArrayList��z��ɕϊ��itoArray()�j���A�ԋp����B<BR>
     * @@return String[]
     * @@roseuid 40E21CD901BE
     */
    public String[] getCsvFileLines()
    {
        final String STR_METHOD_NAME = "getCsvFileLines()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@ArrayList����
        ArrayList l_arrayList = new ArrayList();
        
        //�Q�j�@@�L�[�w�b�_�s�o��
        //(this.�L�[�w�b�_[] != null�̏ꍇ) 
        //this.�L�[�w�b�_[]�̊e�v�f����؂蕶��(*1)�ŋ�؂���������ɘA�����AArrayList�ɒǉ�����B
        //(��L�ȊO�̏ꍇ)
        //space������(" ")��ArrayList�ɒǉ�����B
        String l_strkeyHeader = "";
        if (this.keyHeader != null)
        {
            for (int l_intLoop = 0;l_intLoop < this.keyHeader.length;l_intLoop++)
            {
                if ("".equals(l_strkeyHeader))
                {
                    l_strkeyHeader = this.keyHeader[l_intLoop];
                }
                else
                {
                    l_strkeyHeader =
                        l_strkeyHeader + regex + this.keyHeader[l_intLoop];
                }
            }
            l_arrayList.add(l_strkeyHeader);
        }
        else
        {
            l_arrayList.add(" ");
        }
        
        //�R�j�@@�w�b�_�s�i�^�C�g��������j�o��
        if (isColumnHeadOutput() == true)
        {
            String l_strColumnLabel = "";
            for (int l_intLoop = 0; l_intLoop < this.columnHeader.length; l_intLoop++)
            {
                if ("".equals(l_strColumnLabel))
                {
                    l_strColumnLabel = this.columnHeader[l_intLoop].getColumnLabel();
                }
                else
                {
                    l_strColumnLabel = l_strColumnLabel + regex + this.columnHeader[l_intLoop].getColumnLabel();
                }
            }
            
            l_arrayList.add(l_strColumnLabel);
        }
        
        //�S�j�@@���׍s�o��
        int l_intSize = this.rows.size();
        for (int l_intRowIndex = 0; l_intRowIndex < l_intSize; l_intRowIndex++)
        {
            //�S�|�P�j�@@�e���ڂ̃f�[�^�擾
            String l_strAddValue = "";
            for (int l_intColumnIndex = 0; l_intColumnIndex < this.columnHeader.length; l_intColumnIndex++)
            {
                String l_strValue = this.getStringValue(l_intRowIndex,columnHeader[l_intColumnIndex]);
            
                //�S�|�Q�j�@@�f�[�^�s�쐬
                if ("".equals(l_strAddValue))
                {
                    l_strAddValue = l_strValue;
                }
                else
                {
                    l_strAddValue = l_strAddValue + regex + l_strValue;
                }            
            }
            l_arrayList.add(l_strAddValue);
        }

        //�T�j�@@CSV�t�@@�C���s�ԋp
        Object[] l_objs = l_arrayList.toArray();
        String[] l_strReturns = new String[l_objs.length];
        for (int i = 0; i < l_objs.length; i++)
        {
            l_strReturns[i] = (String)l_objs[i];
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strReturns;
    }

    /**
     * (get�J�������f��) <BR>
     * �����̍��ڃ��x���ɊY������J�������A�J�����w�b�_�z������擾����B<BR>
     * <BR>
     * �P�j�@@this.�J�����w�b�_[]�̊e�v�f���A���ڃ��x�����擾����B<BR>
     * �Q�j�@@�擾�������ڃ��x���������̍��ڃ��x���ƈ�v���Ă���v�f��<BR>
     * �ԋp����B<BR>
     * @@param l_strColumnLabel - ���ڃ��x�� <BR>
     * �s�w�b�_�ɏo�͂��鍀�ڃ��x��<BR>
     * 
     * @@return WEB3GentradeCsvColumnModel
     * @@roseuid 40E260AB02B8
     */
    public WEB3GentradeCsvColumnModel getColumnModel(String l_strColumnLabel)
    {
        final String STR_METHOD_NAME = "getColumnModel(String)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeCsvColumnModel l_returnColumnModel = null;
        
        //�P�j�@@this.�J�����w�b�_[]�̊e�v�f���A���ڃ��x�����擾����B
        int l_intLength = this.columnHeader.length;
        for (int l_intLoop = 0; l_intLoop < l_intLength; l_intLoop++)
        {
            //�Q�j�@@�擾�������ڃ��x���������̍��ڃ��x���ƈ�v���Ă���v�f��ԋp����B
            String l_strLabel = this.columnHeader[l_intLoop].getColumnLabel(); 
            if (l_strLabel.equals(l_strColumnLabel))
            {
                l_returnColumnModel = this.columnHeader[l_intLoop];
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_returnColumnModel;
    }

    /**
     * (get���׍s��) <BR>
     * ���׍s�����擾����B<BR>
     * <BR>
     * this.���׍s.size()��ԋp����B<BR>
     * @@return int
     * @@roseuid 40F536E40180
     */
    public int getRowCount()
    {
        //this.���׍s.size()��ԋp����B
        return this.rows.size();
    }

    /**
     * (set���ڒl) <BR>
     * �isetValue�j <BR>
     * <BR>
     * �w��̍s�^�J�����ɒl���Z�b�g����B <BR>
     * <BR>
     * �P�j�@@�s�擾 <BR>
     * �@@���׍s��������̍s�ԍ��̗v�f���擾���AObject[]�^�ɕϊ�����B <BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g <BR>
     * �@@�P�j�Ŏ擾����Object�z��́A�����̃J�����������J�����ԍ��̗v�f��<BR>
     * �����̒l���Z�b�g����B <BR>
     * <BR>
     * // Object l_rowValues[] = (Object[])this.���׍s.get(�s�ԍ�); <BR>
     * // l_rowValues[�J����.get�J�����ԍ�()] = �l; <BR>
     * @@param l_intLineNumber - �s�ԍ� <BR>
     * �s�ԍ����w�肷��B<BR>
     * 
     * @@param l_csvColumnModel - �J���� <BR>
     * �J�����i��j���w�肷��B<BR>
     * 
     * @@param l_objValue - �l <BR>
     * �Z�b�g����l���w�肷��B<BR>
     * @@roseuid 40F252B6036C
     */
    public void setValue(
        int l_intLineNumber,
        WEB3GentradeCsvColumnModel l_csvColumnModel,
        Object l_objValue)
    {
        final String STR_METHOD_NAME = "setValue(int,WEB3GentradeCsvColumnModel," +
            "Object)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�s�擾
        Object l_rowValues[] = (Object[])this.rows.get(l_intLineNumber);
        
        //�P�j�Ŏ擾����Object�z��́A�����̃J�����������J�����ԍ��̗v�f��
        //�����̒l���Z�b�g����B
        l_rowValues[l_csvColumnModel.getColumnNumber()] = l_objValue;
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�J�����w�b�_) <BR>
     * �isetColumnHeader�j<BR>
     * <BR>
     * this.�J�����w�b�_�Ɉ����̒l���Z�b�g����B<BR>
     * @@param l_columnHeader - �J�����w�b�_�B<BR>
     * @@roseuid 40E21B9401FC
     */
    public void setColumnHeader(WEB3GentradeCsvColumnModel[] l_columnHeader)
    {
        //this.�J�����w�b�_�Ɉ����̒l���Z�b�g����B
        this.columnHeader = l_columnHeader;
    }

    /**
     * (set�L�[�w�b�_) <BR>
     * �isetKeyHeader�j<BR>
     * <BR>
     * this.�L�[�w�b�_�Ɉ����̒l���Z�b�g����B<BR>
     * @@param l_strKeyHeader - �L�[�w�b�_ <BR>
     * CSV�t�@@�C���̂P�s�ڂɏo�͂����L�[�w�b�_�B<BR>
     * �L�[�w�b�_���Ȃ��ꍇ�́Anull���w�肷��B<BR>
     * @@roseuid 40E21C4C02C7
     */
    public void setKeyHeader(String[] l_strKeyHeader)
    {
        final String STR_METHOD_NAME = "setKeyHeader(String[])";
        log.entering(STR_METHOD_NAME);   
        
        if (l_strKeyHeader == null)
        {
            log.error("�L�[�w�b�_���s��");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "."
                    + STR_METHOD_NAME,
                 "�L�[�w�b�_ = null");     
        }   
        
        //this.�L�[�w�b�_�Ɉ����̒l���Z�b�g����B
        this.keyHeader = l_strKeyHeader;
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (add���׍s) <BR>
     * �iaddRow�j<BR>
     * ���׍s��ǉ�����B<BR>
     * <BR>
     * �i�v�f�� == �J�����w�b�_���j��Object�^�z���<BR>
     * �������inew Object[this.�J�����w�b�_.length]�j�A<BR>
     * ���׃��X�g�ɒǉ��iadd()�j����B<BR>
     * <BR>
     * �ǉ��������׍s�̗v�f�ԍ��ithis.���׍s.size() - 1�j��ԋp����B<BR>
     * @@return int
     * @@roseuid 40E23A7A02B8
     */
    public int addRow()
    {
        final String STR_METHOD_NAME = "addRow()";
        log.entering(STR_METHOD_NAME);
        
        if(this.columnHeader != null)
        {
            Object l_obj = new Object[this.columnHeader.length];
            this.rows.add(l_obj);
        }
        else
        {
            log.info("�J�����w�b�_(columnHeader) = null�A���׍s��ǉ��Ȃ��B");
        }

        log.exiting(STR_METHOD_NAME);
        return (this.rows.size() -1);
    }

    /**
     * (add���׍s) <BR>
     * �iaddRow�j<BR>
     * <BR>
     * �����̖��׍s�������this.���׍s�ɒǉ�����B<BR>
     * <BR>
     * �P�j���׍s���󕶎���̏ꍇ�i���׍s������@@== null Or ���׍s������ == ""�j�A<BR>
     * �@@�@@-1��ԋp����B<BR>
     * <BR>
     * 2�j���׍s���<BR>
     * �@@���׍s������.split(CSV�f�[�^���f��.��؂蕶��)�ɂāA<BR>
     * ��؂蕶�����Ƃ�token[]�ɕ�������B<BR>
     * <BR>
     * 3�j�@@���׍s����<BR>
     * �@@this.add���׍s()�ɂċ�̖��׍s��ǉ�����B<BR>
     * <BR>
     * 4�j�@@���ڒl�Z�b�g<BR>
     * �@@�P�j�Ŏ擾����token[]�̊e�v�f���A���ꂼ��this.�J�����w�b�_<BR>
     * [�i�Ή�����index�j].get���ڌ^()�������f�[�^�^�ɕϊ�����B<BR>
     * <BR>
     * �@@token[]�̗v�f�� != this.�J�����w�b�_[]�̗v�f���������łȂ��ꍇ�́A<BR>
     * ��O���X���[����B<BR>
     * class    : WEB3SystemLayerException<BR>
     * tag      : SYSTEM_ERROR_80022<BR>
     * �@@token[index]���Athis.�J�����w�b�_[index].get���ڌ^()��<BR>
     * �ϊ��ł��Ȃ��ꍇ�́A��O���X���[����B<BR>
     * class    : WEB3SystemLayerException<BR>
     * tag      : SYSTEM_ERROR_80023<BR>
     * <BR>
     * �@@�^�ϊ������l���Athis.set���ڒl()�ɂĖ��׍s�̎w�荀�ڂɃZ�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@(*1)<BR>
     * �@@�J�����F�@@this.�J�����w�b�_[�i�Ή�����index�j]<BR>
     * <BR>
     * �@@�s�ԍ�(*1)��ԋp����B<BR>
     * <BR>
     * �@@(*1) �s�ԍ�<BR>
     * �@@�Q�j�ŃR�[������this.add���׍s() �̖߂�l<BR>
     * @@param l_rowString - ���׍s������<BR>
     * <BR>
     * �� ","����؂蕶���ɂ������׍s������B<BR>
     * 
     * @@return int
     * @@throws WEB3SystemLayerException
     * @@roseuid 40F4EFBD0354
     */
    public int addRow(String l_rowString) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "addRow(String)";
        log.entering(STR_METHOD_NAME);       
        
        //1)���׍s���󕶎���̏ꍇ�i���׍s������@@== null Or ���׍s������ == ""�j�A
        //-1��ԋp����B
        if ((l_rowString == null) || (l_rowString.equals("")))
        {
                return -1;
        }
        
        //2�j�@@���׍s���
        //���׍s������.split(CSV�f�[�^���f��.��؂蕶��)�ɂāA
        //��؂蕶�����Ƃ�token[]�ɕ�������B
        String[] token = l_rowString.split(regex);
        if(l_rowString.lastIndexOf(regex) == (l_rowString.length() - 1))
        {
            l_rowString = l_rowString + 'a'; 
            token = l_rowString.split(regex);
            token[token.length - 1] = "";
        }
        
        if(token.length  != this.columnHeader.length)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01993,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���׍s������̗v�f�� = " + token.length 
                    + ",�J�����w�b�_[]�̗v�f�� = " + this.columnHeader.length);
        }

        //3�j�@@���׍s����
        int l_intLineNumber = this.addRow();
        
        //4�j�@@���ڒl�Z�b�g
        //�P�j�Ŏ擾����token[]�̊e�v�f���A���ꂼ��this.�J�����w�b�_
        //[�i�Ή�����index�j].get���ڌ^()�������f�[�^�^�ɕϊ�����B
        for (int l_intLoop = 0; l_intLoop < token.length; l_intLoop++)
        {
            if( token[l_intLoop] == null || "".equals(token[l_intLoop]) )
            {
                continue;
            }
            
            String l_strColumnLabel = this.columnHeader[l_intLoop].getColumnLabel();
            int l_intColumnNumber = this.columnHeader[l_intLoop].getColumnNumber();
            int l_intColumnModel = this.columnHeader[l_intLoop].getColumnType();
            DateFormat l_dateFormat = this.columnHeader[l_intLoop].getDateFormat();
            
            //CSV�J�������f��
            WEB3GentradeCsvColumnModel l_csvColumnModel = 
                new WEB3GentradeCsvColumnModel(
                    l_strColumnLabel,
                    l_intColumnNumber,
                    l_intColumnModel,
                    l_dateFormat
                    );

            try
            {
                //���ڌ^_������
                if (l_intColumnModel == WEB3GentradeCsvColumnModel.STRINGTYPE)
                {
                    //�^�ϊ������l���Athis.set���ڒl()�ɂĖ��׍s�̎w�荀�ڂɃZ�b�g����B
                    String l_strValue = token[l_intLoop].toString();
                    this.setValue(l_intLineNumber,l_csvColumnModel,l_strValue);
                }
                //���ڌ^_���t
                else if (l_intColumnModel == WEB3GentradeCsvColumnModel.DATETYPE)
                {
                    //�^�ϊ������l���Athis.set���ڒl()�ɂĖ��׍s�̎w�荀�ڂɃZ�b�g����B
                    Date l_datValue = l_dateFormat.parse(token[l_intLoop]);
                    this.setValue(l_intLineNumber,l_csvColumnModel,l_datValue);
                }
                //���ڌ^_���l�idouble�j
                else if (l_intColumnModel == WEB3GentradeCsvColumnModel.DOUBLETYPE)
                {
                    //�^�ϊ������l���Athis.set���ڒl()�ɂĖ��׍s�̎w�荀�ڂɃZ�b�g����B
                    Double l_dblValue = Double.valueOf(token[l_intLoop]);
                    this.setValue(l_intLineNumber,l_csvColumnModel,l_dblValue);
                }
                //���ڌ^_���l�iint�j
                else if (l_intColumnModel == WEB3GentradeCsvColumnModel.INTEGERTYPE)
                {
                    //�^�ϊ������l���Athis.set���ڒl()�ɂĖ��׍s�̎w�荀�ڂɃZ�b�g����B
                    Integer l_intValue = Integer.valueOf(token[l_intLoop]);
                    this.setValue(l_intLineNumber,l_csvColumnModel,l_intValue);
                }
                //���ڌ^_���l�ilong�j
                else if (l_intColumnModel == WEB3GentradeCsvColumnModel.LONGTYPE)
                {
                    //�^�ϊ������l���Athis.set���ڒl()�ɂĖ��׍s�̎w�荀�ڂɃZ�b�g����B
                    Long l_lngValue = Long.valueOf(token[l_intLoop]);
                    this.setValue(l_intLineNumber,l_csvColumnModel,l_lngValue);
                }
                //���ڌ^_���t����
                else if (l_intColumnModel == WEB3GentradeCsvColumnModel.TIMESTAMPTYPE)
                {
                    //�^�ϊ������l���Athis.set���ڒl()�ɂĖ��׍s�̎w�荀�ڂɃZ�b�g����B
                    DateFormat l_df = l_csvColumnModel.getDateFormat();
                    Timestamp l_tsValue = new Timestamp(l_df.parse(token[l_intLoop]).getTime());
                    this.setValue(l_intLineNumber,l_csvColumnModel,l_tsValue);
                }
                //token[]�̗v�f�� != this.�J�����w�b�_[]�̗v�f���������łȂ��ꍇ�́A
                //��O���X���[����B
                else
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80022,
                        this.getClass().getName() + "." + STR_METHOD_NAME
                        );
                }
            }
            //token[index]���Athis.�J�����w�b�_[index].get���ڌ^()��
            //�ϊ��ł��Ȃ��ꍇ�́A��O���X���[����B
            catch(NumberFormatException nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80023,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    nfe.getMessage(),
                    nfe);
            }
            catch (ParseException pex)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80023,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    pex.getMessage(),
                    pex);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_intLineNumber;
    }

    /**
     * (add���׍s)<BR>
     * �iaddRow�j<BR>
     * <BR>
     * �����̖��׍s�������this.���׍s�ɒǉ�����B<BR>
     * <BR>
     * CSV�f�[�^���ɓ��t�����݂���ꍇ�A�p�����[�^�ɂ��A<BR>
     * ���t��͂������ɍs�����ǂ����𔻒f����B<BR>
     * <BR>
     * �P�j�@@����.���t��̓t���O == "0" or null �̏ꍇ�A<BR>
     * �@@�P-�P�j�@@this.add���׍s(String)���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[�w�肷�����]<BR>
     * �@@�@@�@@���׍s������F����.���׍s������<BR>
     * <BR>
     * �@@�P-�Q�j�@@�P-�P�j�̖߂�l��ԋp����B<BR>
     * <BR>
     * �Q�j�@@����.���t��̓t���O == "1" �̏ꍇ�A<BR>
     * <BR>
     * �@@�Q-�P�j�@@���׍s���󕶎���̏ꍇ�i���׍s������@@== null Or ���׍s������ == ""�j�A<BR>
     * �@@�@@�@@�@@�@@-1��ԋp����B<BR>
     * <BR>
     * �@@�Q-�Q�j�@@���׍s���<BR>
     * �@@�@@�@@�@@�@@���׍s������.split(CSV�f�[�^���f��.��؂蕶��)�ɂāA<BR>
     * �@@�@@�@@�@@�@@��؂蕶�����Ƃ�token[]�ɕ�������B<BR>
     * <BR>
     * �@@�Q-�R�j�@@���׍s����<BR>
     * �@@�@@�@@�@@�@@�@@this.add���׍s()�ɂċ�̖��׍s��ǉ�����B<BR>
     * <BR>
     * �@@�Q-�S�j�@@���ڒl�Z�b�g<BR>
     * �@@�@@�Q-�S-�P�j�@@�Q-�P�j�Ŏ擾����token[]�̊e�v�f���A<BR>
     * �@@�@@�@@���ꂼ��this.�J�����w�b�_[�i�Ή�����index�j].get���ڌ^()�������f�[�^�^�ɕϊ�����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�Etoken[]�̗v�f�� != this.�J�����w�b�_[]�̗v�f���������łȂ��ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@class    : WEB3SystemLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag      : SYSTEM_ERROR_80022<BR>
     * �@@�@@�@@�@@�@@�@@�Etoken[index]���Athis.�J�����w�b�_[index].get���ڌ^()�ɕϊ��ł��Ȃ��ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@��NumberFormatException�������u���l���s���ł��v��O���X���[����B <BR>
     * �@@�@@�@@�@@�@@�@@class    : WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag      : BUSINESS_ERROR_03002<BR>
     * �@@�@@�@@�@@�@@�@@��ParseException�������u���t���s���ł��v��O���X���[����B <BR>
     * �@@�@@�@@�@@�@@�@@class    : WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag      : BUSINESS_ERROR_02994<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�����t�^�A���t���Ԍ^��ϊ�����ꍇ�͈ȉ������{��A<BR>
     * �@@�@@�@@�@@�@@�@@���t/������͂������ɍs���mDateFormat#setLenient(false)����]�B <BR>
     * �@@�@@�@@�@@�@@�@@�@@�Etoken[index]�����l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�u���t���s���ł��v �iBUSINESS_ERROR_02994�j��O���X���[����B <BR>
     * �@@�@@�@@�@@�@@�@@class    : WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag      : BUSINESS_ERROR_02994<BR>
     * �@@�@@�@@�@@�@@�@@�@@�Etoken[index]�̌��������t�t�H�[�}�b�g�p�^�[���̒���<BR>
     * �@@�@@�@@�@@�@@�@@�@@�m((SimpleDateFormat)DateFormat)#toPattern()]�Ɠ������Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�u���t���s���ł��v �iBUSINESS_ERROR_02994�j��O���X���[����B <BR>
     * �@@�@@�@@�@@�@@�@@class    : WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag      : BUSINESS_ERROR_02994<BR>
     * <BR>
     * �@@�@@�Q-�S-�Q�j �^�ϊ������l���Athis.set���ڒl()�ɂĖ��׍s�̎w�荀�ڂɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�@@�s�ԍ��F�@@(*1)<BR>
     * �@@�@@�@@�@@�@@�@@�J�����F�@@this.�J�����w�b�_[�i�Ή�����index�j]<BR>
     * �@@<BR>
     * �@@�Q-�T�j �s�ԍ�(*1)��ԋp����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@(*1) �s�ԍ�<BR>
     * �@@�@@�@@�@@�@@�@@�Q-�R�j�ŃR�[������this.add���׍s() �̖߂�l<BR>
     * <BR>
     * <BR>
     * @@param l_strRows - (���׍s������)<BR>
     * ���׍s������<BR>
     * <BR>
     * �� ","����؂蕶���ɂ������׍s������B<BR>
     * @@param l_strDateAnalysisFlag - (���t��̓t���O)<BR>
     * ���t��̓t���O<BR>
     * <BR>
     * ���t�f�[�^�t�H�[�}�b�g�����݂���ꍇ�A<BR>
     * ���t��͂������ɍs�����ǂ�����ݒ肷��B<BR>
     * <BR>
     * 0�F�����ł͂Ȃ����<BR>
     * 1�F�����ȉ��<BR>
     * @@return int
     * @@throws WEB3BaseException
     */
    public int addRow(String l_strRows, String l_strDateAnalysisFlag) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "addRow(String, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j ����.���t��̓t���O == "0" or null �̏ꍇ�A
        //�P-�P�j this.add���׍s(String)���R�[������B
        if (WEB3GentradeDateAnalysisFlagDivDef.NOT_STRICTLY_ANALYSIS.equals(l_strDateAnalysisFlag)
            || l_strDateAnalysisFlag == null)
        {
            //[�w�肷�����]
            //���׍s������F����.���׍s������
            //�P-�Q�j �P-�P�j�̖߂�l��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return this.addRow(l_strRows);
        }

        //�Q�j ����.���t��̓t���O == "1" �̏ꍇ�A
        int l_intLineNumber = 0;
        if (WEB3GentradeDateAnalysisFlagDivDef.STRICTLY_ANALYSIS.equals(l_strDateAnalysisFlag))
        {
            //�Q-�P�j�@@���׍s���󕶎���̏ꍇ�i���׍s������@@== null Or ���׍s������ == ""�j�A-1��ԋp����B
            if (WEB3StringTypeUtility.isEmpty(l_strRows))
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
            }

            //�Q-�Q�j�@@���׍s���
            //���׍s������.split(CSV�f�[�^���f��.��؂蕶��)�ɂāA��؂蕶�����Ƃ�token[]�ɕ�������B
            String[] token = l_strRows.split(regex);
            if (l_strRows.lastIndexOf(regex) == (l_strRows.length() - 1))
            {
                l_strRows = l_strRows + 'a';
                token = l_strRows.split(regex);
                token[token.length - 1] = "";
            }

            if (token.length != this.columnHeader.length)
            {
                log.debug("CSV�t�@@�C���̗v�f�����s���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01993,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���׍s������̗v�f�� = " + token.length
                    + ",�J�����w�b�_[]�̗v�f�� = " + this.columnHeader.length);
            }

            //�Q-�R�j�@@���׍s����
            //this.add���׍s()�ɂċ�̖��׍s��ǉ�����B
            l_intLineNumber = this.addRow();

            //�Q-�S�j�@@���ڒl�Z�b�g
            //�Q-�S-�P�j �Q-�P�j�Ŏ擾����token[]�̊e�v�f���A���ꂼ��this.�J�����w�b�_[�i�Ή�����index�j].get���ڌ^()�������f�[�^�^�ɕϊ�����B
            //�Etoken[]�̗v�f�� != this.�J�����w�b�_[]�̗v�f���������łȂ��ꍇ�́A��O���X���[����B
            //�Etoken[index]���Athis.�J�����w�b�_[index].get���ڌ^()�ɕϊ��ł��Ȃ��ꍇ�́A��O���X���[����B
            for (int l_intLoop = 0; l_intLoop < token.length; l_intLoop++)
            {
                if (token[l_intLoop] == null || "".equals(token[l_intLoop]))
                {
                    continue;
                }

                String l_strColumnLabel = this.columnHeader[l_intLoop].getColumnLabel();
                int l_intColumnNumber = this.columnHeader[l_intLoop].getColumnNumber();
                int l_intColumnModel = this.columnHeader[l_intLoop].getColumnType();
                DateFormat l_dateFormat = this.columnHeader[l_intLoop].getDateFormat();

                //CSV�J�������f��
                WEB3GentradeCsvColumnModel l_csvColumnModel =
                    new WEB3GentradeCsvColumnModel(
                        l_strColumnLabel,
                        l_intColumnNumber,
                        l_intColumnModel,
                        l_dateFormat
                        );

                try
                {
                    //���ڌ^_������
                    if (l_intColumnModel == WEB3GentradeCsvColumnModel.STRINGTYPE)
                    {
                        //�^�ϊ������l���Athis.set���ڒl()�ɂĖ��׍s�̎w�荀�ڂɃZ�b�g����B
                        String l_strValue = token[l_intLoop].toString();
                        this.setValue(l_intLineNumber, l_csvColumnModel, l_strValue);
                    }
                    //���ڌ^_���t
                    else if (l_intColumnModel == WEB3GentradeCsvColumnModel.DATETYPE)
                    {
                        //�^�ϊ������l���Athis.set���ڒl()�ɂĖ��׍s�̎w�荀�ڂɃZ�b�g����B
                        //�����t�^��ϊ�����ۂ́A���t/������͂������ɍs���iDateFormat#setLenient(false)�����j
                        if (!WEB3StringTypeUtility.isNumber(token[l_intLoop]))
                        {
                            log.debug("���t���s���ł��B");
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_02994,
                                this.getClass().getName() + "." + STR_METHOD_NAME
                                );
                        }
                        if (token[l_intLoop].length() != ((SimpleDateFormat)l_dateFormat).toPattern().length())
                        {
                            log.debug("���t���s���ł��B");
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_02994,
                                this.getClass().getName() + "." + STR_METHOD_NAME
                                );
                        }
                        l_dateFormat.setLenient(false);
                        Date l_datValue = l_dateFormat.parse(token[l_intLoop]);
                        this.setValue(l_intLineNumber, l_csvColumnModel, l_datValue);
                    }
                    //���ڌ^_���l�idouble�j
                    else if (l_intColumnModel == WEB3GentradeCsvColumnModel.DOUBLETYPE)
                    {
                        //�^�ϊ������l���Athis.set���ڒl()�ɂĖ��׍s�̎w�荀�ڂɃZ�b�g����B
                        Double l_dblValue = Double.valueOf(token[l_intLoop]);
                        this.setValue(l_intLineNumber, l_csvColumnModel, l_dblValue);
                    }
                    //���ڌ^_���l�iint�j
                    else if (l_intColumnModel == WEB3GentradeCsvColumnModel.INTEGERTYPE)
                    {
                        //�^�ϊ������l���Athis.set���ڒl()�ɂĖ��׍s�̎w�荀�ڂɃZ�b�g����B
                        Integer l_intValue = Integer.valueOf(token[l_intLoop]);
                        this.setValue(l_intLineNumber, l_csvColumnModel, l_intValue);
                    }
                    //���ڌ^_���l�ilong�j
                    else if (l_intColumnModel == WEB3GentradeCsvColumnModel.LONGTYPE)
                    {
                        //�^�ϊ������l���Athis.set���ڒl()�ɂĖ��׍s�̎w�荀�ڂɃZ�b�g����B
                        Long l_lngValue = Long.valueOf(token[l_intLoop]);
                        this.setValue(l_intLineNumber, l_csvColumnModel, l_lngValue);
                    }
                    //���ڌ^_���t����
                    else if (l_intColumnModel == WEB3GentradeCsvColumnModel.TIMESTAMPTYPE)
                    {
                        //�^�ϊ������l���Athis.set���ڒl()�ɂĖ��׍s�̎w�荀�ڂɃZ�b�g����B
                        DateFormat l_df = l_csvColumnModel.getDateFormat();
                        if (!WEB3StringTypeUtility.isNumber(token[l_intLoop]))
                        {
                            log.debug("���t���s���ł��B");
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_02994,
                                this.getClass().getName() + "." + STR_METHOD_NAME
                                );
                        }
                        if (token[l_intLoop].length() != ((SimpleDateFormat)l_dateFormat).toPattern().length())
                        {
                            log.debug("���t���s���ł��B");
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_02994,
                                this.getClass().getName() + "." + STR_METHOD_NAME
                                );
                        }
                        l_df.setLenient(false);
                        Timestamp l_tsValue = new Timestamp(l_df.parse(token[l_intLoop]).getTime());
                        this.setValue(l_intLineNumber, l_csvColumnModel, l_tsValue);
                    }
                    //token[]�̗v�f�� != this.�J�����w�b�_[]�̗v�f���������łȂ��ꍇ�́A
                    //��O���X���[����B
                    else
                    {
                        log.debug("���׍s������̗v�f���ƃJ�����w�b�_[]�̗v�f���������łȂ��B");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80022,
                            this.getClass().getName() + "." + STR_METHOD_NAME
                            );
                    }
                }
                //token[index]���Athis.�J�����w�b�_[index].get���ڌ^()��
                //�ϊ��ł��Ȃ��ꍇ�́A��O���X���[����B
                catch (NumberFormatException l_ex)
                {
                    log.error("���l���s���ł��B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03002,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (ParseException l_ex)
                {
                    log.error("���t���s���ł��B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02994,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }
        }

        //�Q-�T�j �s�ԍ�(*1)��ԋp����B
        // (*1) �s�ԍ�
        //�Q-�R�j�ŃR�[������this.add���׍s() �̖߂�l
        log.exiting(STR_METHOD_NAME);
        return l_intLineNumber;
    }

    /**
     * (delete���׍s) <BR>
     * �ideleteRow�j <BR>
     * �w��s�ԍ��̖��׍s���폜����B <BR>
     *  <BR>
     * // this.���׍s.remove(�s�ԍ�) <BR>
     * @@param l_intLineNumber - �s�ԍ� <BR>
     * �s�ԍ����w�肷��B<BR>
     * @@roseuid 40F5DAF900C5
     */
    public void deleteRow(int l_intLineNumber)
    {
        //�w��s�ԍ��̖��׍s���폜����B
        this.rows.remove(l_intLineNumber);
    }
}
@
