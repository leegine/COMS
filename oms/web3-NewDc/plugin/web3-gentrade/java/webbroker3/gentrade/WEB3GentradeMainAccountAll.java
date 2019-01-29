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
filename	WEB3GentradeMainAccountAll.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ڋq�i�S���X���j(WEB3GentradeMainAccountAll.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/14 ������ (���u) �V�K�쐬
*/
package webbroker3.gentrade;

import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.MainAccountAllRow;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

/**
 * (�ڋq�i�S���X���j) <BR>
 * �ڋq�i�S���X���j�N���X<BR>
 * <BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3GentradeMainAccountAll
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3GentradeMainAccountAll.class);

    /**
     * �ڋqRow�I�u�W�F�N�g�B<BR>
     */
    private MainAccountAllRow mainAccountAllRow;

    /**
     * �R���X�g���N�^<BR>
     * <BR>
     * �ڋq�}�X�^�i�S���X���j�e�[�u�����<BR>
     * �،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h�A�ڋq�R�[�hCD�ɊY������<BR>
     * �ڋq�i�S���X���jRow�I�u�W�F�N�g���擾����<BR>
     * <BR>
     * @@param l_strCompCode �،���ЃR�[�h
     * @@param l_strBrCode ���X�R�[�h
     * @@param l_strCustCode �ڋq�R�[�h
     * @@param l_strCustCodeCD �ڋq�R�[�hCD
     * @@throws WEB3BaseException
     * @@roseuid 403496F0022B
     */
    public WEB3GentradeMainAccountAll(
        String l_strCompCode,
        String l_strBrCode,
        String l_strCustCode,
        String l_strCustCodeCD)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3GentradeMainAccountAll(String, String, String, String)";
        log.entering(STR_METHOD_NAME);
            
        //�ڋq�}�X�^�i�S���X���j�e�[�u����� 
        //�،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h�ɊY������ 
        //�ڋq�i�S���X���jRow�I�u�W�F�N�g���擾����  
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" comp_code = ? ");
        l_sbWhere.append(" and br_code = ? ");
        l_sbWhere.append(" and cust_code = ? ");
        l_sbWhere.append(" and cust_code_cd = ? ");

        Object[] l_obWhere = new Object[]
        {
            l_strCompCode,
            l_strBrCode,
            l_strCustCode,
            l_strCustCodeCD
        };

        List l_lstRecords = null;
        try
        { 
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lstRecords = l_queryProcessor.doFindAllQuery(
                MainAccountAllRow.TYPE,
                l_sbWhere.toString(),
                l_obWhere);
        }
        catch(DataException l_dexp)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dexp.getMessage(),
                l_dexp);
        }

        if (l_lstRecords != null && l_lstRecords.size() != 0)
        {
            this.mainAccountAllRow = (MainAccountAllRow) l_lstRecords.get(0);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �ڋq�}�X�^�i�S���X���j�e�[�u�����<BR>
     * �،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h�ɊY������<BR>
     * �ڋq�i�S���X���jRow�I�u�W�F�N�g���擾����<BR>
     * <BR>
     * @@param l_strCompCode �،���ЃR�[�h
     * @@param l_strBrCode ���X�R�[�h
     * @@param l_strCustCode �ڋq�R�[�h
     * @@throws WEB3BaseException 
     * @@roseuid 403496F000E3
     */
    public WEB3GentradeMainAccountAll(
        String l_strCompCode,
        String l_strBrCode,
        String l_strCustCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3GentradeMainAccountAll(String, String, String)";
        log.entering(STR_METHOD_NAME);
            
        //�ڋq�}�X�^�i�S���X���j�e�[�u����� 
        //�،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h�ɊY������ 
        //�ڋq�i�S���X���jRow�I�u�W�F�N�g���擾����  
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" comp_code = ? ");
        l_sbWhere.append(" and br_code = ? ");
        l_sbWhere.append(" and cust_code = ? ");

        Object[] l_obWhere = new Object[]
        {
            l_strCompCode,
            l_strBrCode,
            l_strCustCode
        };

        List l_lstRecords = null;
        try
        { 
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lstRecords = l_queryProcessor.doFindAllQuery(
                MainAccountAllRow.TYPE,
                l_sbWhere.toString(),
                l_obWhere);
        }
        catch(DataException l_dexp)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dexp.getMessage(),
                l_dexp);
        }

        if (l_lstRecords != null && l_lstRecords.size() != 0)
        {
            this.mainAccountAllRow = (MainAccountAllRow) l_lstRecords.get(0);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�ڋq�i�S���X���j�s)<BR>
     *  <BR>
     * this.�ڋq�i�S���X���jRow�I�u�W�F�N�g��ԋp����B<BR>
     *  <BR>
     * @@return MainAccountAllRow
     */
    public MainAccountAllRow getMainAccountAllRow()
    {
        return this.mainAccountAllRow;
    }
}
@
