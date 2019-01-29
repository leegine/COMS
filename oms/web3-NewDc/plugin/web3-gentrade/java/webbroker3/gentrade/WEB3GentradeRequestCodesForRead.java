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
filename	WEB3GentradeRequestCodesForRead.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����Ώۃf�[�^�R�[�h(WEB3GentradeRequestCodesForRead.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/28 羐� (���u) �V�K�쐬
*/
package webbroker3.gentrade;

import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.RequestCodesForReadDao;
import webbroker3.gentrade.data.RequestCodesForReadRow;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;

/**
 * (�����Ώۃf�[�^�R�[�h) <BR>
 * �����Ώۃf�[�^�R�[�h�e�[�u���̂P���R�[�h��\������B<BR>
 * �������[�g�o�b�N�A�b�v�Ή���AP�Ŏg�p����B<BR>
 * <BR>
 * �iDB���C�A�E�g �u�����Ώۃf�[�^�R�[�h�e�[�u���d�l.xls�v�Q�Ɓj<BR>
 */
public class WEB3GentradeRequestCodesForRead implements BusinessObject
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeRequestCodesForRead.class);

    /**
     * �����Ώۃf�[�^�R�[�hRow�I�u�W�F�N�g
     * �iDAO���������N���X�j
     */
    private RequestCodesForReadRow requestCodesForReadRow;

    /**
     * �R���X�g���N�^�B<BR>
     * �����̏����Ɉ�v���鏈���Ώۃf�[�^�R�[�h�I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@�����̒l�ɂď����Ώۃf�[�^�R�[�h�e�[�u������������B<BR>
     * <BR>
     * �Q�j�@@�s�I�u�W�F�N�g�Z�b�g<BR>
     * �@@�������ʂ̍s�I�u�W�F�N�g�i�����Ώۃf�[�^�R�[�hRow�j���A<BR>
     * �@@this.�����Ώۃf�[�^�R�[�hRow�ɃZ�b�g����B<BR>
     * @@param l_strRequestCode - �f�[�^�R�[�h<BR>
     * @@param l_strPType - �|�������t�B�b�N�^�C�v<BR>
     *    �i�������[�g�o�b�N�A�b�v�Ή�AP�̃��b�Z�[�WPTYPE�j<BR>
     * @@return WEB3GentradeRequestCodesForRead
     * @@throws WEB3SystemLayerException
     * @@roseuid 42401BE50205
     */
    public WEB3GentradeRequestCodesForRead(
        String l_strRequestCode,
        String l_strPType)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "WEB3GentradeRequestCodesForRead(String, String)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            this.requestCodesForReadRow =
                RequestCodesForReadDao.findRowByPk(l_strRequestCode,l_strPType);
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �{�I�u�W�F�N�g���C���X�^���X�����A<BR>
     * ������Row�I�u�W�F�N�g���v���p�e�B�ɃZ�b�g����B<BR>
     * @@param l_requestCodesForReadRow - �����Ώۃf�[�^�R�[�hRow�I�u�W�F�N�g
     * @@return WEB3GentradeRequestCodesForRead
     * @@roseuid 42401BE501E5
     */
    public WEB3GentradeRequestCodesForRead(RequestCodesForReadRow l_requestCodesForReadRow)
    {
        final String STR_METHOD_NAME = "WEB3GentradeRequestCodesForRead(RequestCodesForReadRow)";
        if (l_requestCodesForReadRow == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����Ώۃf�[�^�R�[�hRow�I�u�W�F�N�g = null");
        }
        this.requestCodesForReadRow = l_requestCodesForReadRow;
    }

    /**
     * this.�����Ώۃf�[�^�R�[�hRow��ԋp����B
     * @@return java.lang.Object
     * @@roseuid 4240C9F20346
     */
    public java.lang.Object getDataSourceObject()
    {
        return this.requestCodesForReadRow;
    }

    /**
     * (get�����Ώۃf�[�^�R�[�hRow�ꗗ)<BR>
     * �istatic���\�b�h�j<BR>
     * �����Ώۃf�[�^�R�[�h�e�[�u�����A<BR>
     * �w�肳�ꂽ�|�������t�B�b�N�^�C�v�iPTYPE�j�ɑ΂��郌�R�[�h��S�Ď擾���Ԃ�<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@�����Ώۃf�[�^�R�[�h�e�[�u�����A�����̃|�������t�B�b�N�^�C�v��<BR>
     *   �Y�����郌�R�[�h��S���R�[�h�擾����B<BR>
     * <BR>
     * �@@[��������]<BR>
     * �@@�|�������t�B�b�N�^�C�v = �����̃|�������t�B�b�N�^�C�v<BR>
     * <BR>
     * �Q�j�@@�擾�������R�[�h��z��ɂ��ĕԂ��B<BR>
     * �@@�@@�@@���Y�����郌�R�[�h�����݂��Ȃ��ꍇ�́A��O��throw����B<BR>
     * @@param l_strPType - (�|�������t�B�b�N�^�C�v)<BR>
     *     �ΏۃT�[�r�X�̃��b�Z�[�WPTYPE�B<BR>
     * @@return �����Ώۃf�[�^�R�[�hRow[]
     * @@throws WEB3BaseException
     * @@roseuid 424018640221
     */
    public static RequestCodesForReadRow[] getRequestCodesForReadRowList(String l_strPType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getRequestCodesForReadRowList(String)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //�P�j�@@DB����
            //�����Ώۃf�[�^�R�[�h�e�[�u�����A�����̃|�������t�B�b�N�^�C�v��
            //�Y�����郌�R�[�h��S���R�[�h�擾����B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lstRecords =
                l_queryProcessor.doFindAllQuery(
                    RequestCodesForReadRow.TYPE,
                    "ptype = ?",
                    new Object[] { l_strPType });
            
            //�Q�j�@@�擾�������R�[�h��z��ɂ��ĕԂ��B
            //   ���Y�����郌�R�[�h�����݂��Ȃ��ꍇ�́A��O��throw����B
            int l_intSize = l_lstRecords.size();
            if(l_intSize == 0)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    WEB3GentradeRequestCodesForRead.class.getName() + "." + STR_METHOD_NAME,
                    "�����Ώۃf�[�^�R�[�h�e�[�u�����A�w�肳�ꂽ�|�������t�B�b�N�^�C�v�iPTYPE�j�ɑ΂��郌�R�[�h���擾���Ȃ�");
            }
            RequestCodesForReadRow[] l_requestCodesForReadRows = new RequestCodesForReadRow[l_intSize];
            for(int i = 0 ; i < l_intSize; i++)
            {
                l_requestCodesForReadRows[i] = (RequestCodesForReadRow)l_lstRecords.get(i);
            }
            log.exiting(STR_METHOD_NAME);
            return l_requestCodesForReadRows;
            
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeRequestCodesForRead.class.getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }

    /**
     * (get�����Ώۃf�[�^�R�[�h�ꗗ)<BR>
     * �istatic���\�b�h�j<BR>
     * �����Ώۃf�[�^�R�[�h�e�[�u�����A<BR>
     * �w�肳�ꂽ�|�������t�B�b�N�^�C�v�iPTYPE�j�ɑ΂���f�[�^�R�[�h�̈ꗗ��<BR>
     * �擾���A�f�[�^�R�[�h��String�z���Ԃ��B<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@get�����Ώۃf�[�^�R�[�hRow�ꗗ(�����̃|�������t�B�b�N�^�C�v)�����s����B<BR>
     * <BR>
     * �Q�j�@@�P�j�̖߂�l�̏����Ώۃf�[�^�R�[�h�e�[�u��Row�̔z�񂩂�A<BR>
     * �@@�@@�@@�f�[�^�R�[�h�݂̂�String�̔z����쐬���Ԃ��B<BR>
     * <BR>
     * @@param l_strPType - (�|�������t�B�b�N�^�C�v)<BR>
     *     �ΏۃT�[�r�X�̃��b�Z�[�WPTYPE�B<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 424018640231
     */
    public static String[] getRequestCodesForReadList(String l_strPType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getRequestCodesForReadList(String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@DB����
        //get�����Ώۃf�[�^�R�[�hRow�ꗗ(�����̃|�������t�B�b�N�^�C�v)�����s����B
        RequestCodesForReadRow[] l_requestCodesForReadRow = 
            getRequestCodesForReadRowList(l_strPType);
        
        //�Q�j�@@�P�j�̖߂�l�̏����Ώۃf�[�^�R�[�h�e�[�u��Row�̔z�񂩂�A
        //�f�[�^�R�[�h�݂̂�String�̔z����쐬���Ԃ�
        int l_intSize = l_requestCodesForReadRow.length;
        String[] l_strRequestCodes = new String[l_intSize];
        for(int i = 0 ; i < l_intSize; i++)
        {
            l_strRequestCodes[i] = l_requestCodesForReadRow[i].getRequestCode();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strRequestCodes;
    }

    /**
     * (get�����o�H�敪)<BR>
     * this.�����Ώۃf�[�^�R�[�hRow.�����o�H�敪��Ԃ��B<BR>
     * @@return java.lang.String
     * @@roseuid 42401C9D02BA
     */
    public String getSubmitOrderRouteDiv()
    {
        return this.requestCodesForReadRow.getSubmitOrderRouteDiv();
    }
}
@
