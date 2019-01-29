head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeInsider.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  ������(WEB3GentradeInsider.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/23 羐� (���u) �V�K�쐬
*/
package webbroker3.gentrade;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.InsiderDao;
import webbroker3.gentrade.data.InsiderRow;
import webbroker3.util.WEB3LogUtility;

/**
 * ������
 */
public class WEB3GentradeInsider implements BusinessObject
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeInsider.class);

    /**
     * �����ҍs�I�u�W�F�N�g
     * �iDAO���������N���X�j
     */
    private InsiderRow insiderRow;

    /**
     * �R���X�g���N�^<BR>
     * <BR>
     * �{�I�u�W�F�N�g���C���X�^���X�����A<BR>
     * �����̍s�I�u�W�F�N�g���v���p�e�B�ɃZ�b�g����B<BR>
     * @@param l_insiderRow - �����ҍs�I�u�W�F�N�g<BR>
     * <BR>
     * ��DDL��莩����������B<BR>
     * @@return webbroker3.��ЁE�ڋq�G���e�B�e�B.WEB3GentradeInsider
     * @@roseuid 4147AEBB0365
     */
    public WEB3GentradeInsider(InsiderRow l_insiderRow)
    {
        final String STR_METHOD_NAME = "WEB3GentradeInsider(InsiderRow)";
        if (l_insiderRow == null)
        {
            log.error("�����ҍs = null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����ҍs = null");
        }
        this.insiderRow = l_insiderRow;
    }

    /**
     * (getDataSourceObject) <BR>
     * this.������Row��ԋp����B <BR>
     * @@return Object
     * @@roseuid 4147AD8A01CF
     */
    public Object getDataSourceObject()
    {
        return this.insiderRow;
    }

    /**
     * (get�֌W�敪) <BR>
     * this.�����ҍs.�֌W�敪��ԋp����B <BR>
     * @@return java.lang.String
     * @@roseuid 4147AF7B027B
     */
    public String getRelationDiv()
    {
        return this.insiderRow.getRelationDiv();
    }

    /**
     * (get������) <BR>
     * this.�����ҍs.��������ԋp����B<BR>
     * @@return java.lang.String
     * @@roseuid 4147AF82028A
     */
    public String getOfficerName()
    {
        return this.insiderRow.getOfficerName();
    }

    /**
     * (get��E��) <BR>
     * this.�����ҍs.��E����ԋp����B<BR>
     * @@return java.lang.String
     * @@roseuid 4147AF8B0029
     */
    public String getPostName()
    {
        return this.insiderRow.getPostName();
    }

    /**
     * (get������) <BR>
     * �istatic���\�b�h�j <BR>
     *  <BR>
     * �����̌ڋq�ɊY����������҃I�u�W�F�N�g��S�Ď擾����B <BR>
     *  <BR>
     * �P�j DB���� <BR>
     * �@@�����҃e�[�u�����ȉ��̏����Ō�������B <BR>
     * �@@[����] <BR>
     * �@@�،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode() <BR>
     * �@@���X�h�c = �ڋq.getBranch().getBranchId() <BR>
     * �@@�����h�c = �ڋq.getAccountId() <BR>
     *  <BR>
     * �Q�j �I�u�W�F�N�g���� <BR>
     *   �������ʂ̓����ҍs�I�u�W�F�N�g���w�肵�A <BR>
     *   �����҃I�u�W�F�N�g�𐶐�����B<BR>
     * �@@���������I�u�W�F�N�g��z��ŕԋp����B<BR>
     * @@param l_genMainAccount - �ڋq�I�u�W�F�N�g
     * @@return WEB3GentradeInsider[]
     * @@throws WEB3SystemLayerException
     * @@roseuid 4147ADD10190
     */
    public static WEB3GentradeInsider[] getInsider(WEB3GentradeMainAccount l_genMainAccount)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getInsider(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j DB���� 
        //�����҃e�[�u�����ȉ��̏����Ō�������B 
        //[����] 
        //�،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode() 
        //���X�h�c = �ڋq.getBranch().getBranchId() 
        //�����h�c = �ڋq.getAccountId()   
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_id = ? ");
        l_sbWhere.append(" and account_id = ? ");
        Object[] l_objWhere =
            { 
                l_genMainAccount.getInstitution().getInstitutionCode(), //�،���ЃR�[�h
                new Long(l_genMainAccount.getBranch().getBranchId()), //���XID
                new Long(l_genMainAccount.getAccountId()) //�����h�c
            };
        List l_lisRecords;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                InsiderRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere);
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeInsider.class.getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        //�Q�j �I�u�W�F�N�g����
        int l_intSize = l_lisRecords.size();
        WEB3GentradeInsider[] l_gentradeInsiders = 
            new WEB3GentradeInsider[l_intSize];
        for(int i = 0 ; i < l_intSize; i ++)
        {
            l_gentradeInsiders[i] = 
                new WEB3GentradeInsider((InsiderRow)l_lisRecords.get(i));
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_gentradeInsiders;
        
    }

    /**
     * (get������) <BR>
     * �istatic���\�b�h�j<BR>
     * <BR>
     * �����̌ڋq�ɊY����������҃I�u�W�F�N�g��S�Ď擾����B<BR>
     * <BR>
     * �P�j DB����<BR>
     * �@@�����҃e�[�u�����ȉ��̏����Ō�������B<BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode()<BR>
     * �@@���X�h�c = �ڋq.getBranch().getBranchId()<BR>
     * �@@�����h�c = �ڋq.getAccountId()<BR>
     * �@@�����h�c = ����.getProductId()<BR>
     * <BR>
     * �Q�j �I�u�W�F�N�g����<BR>
     * �@@�������ʂ̓����ҍs�I�u�W�F�N�g���w�肵�A<BR>
     *   �����҃I�u�W�F�N�g�𐶐�����B<BR>
     * �@@���������I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_genMainAccount - �ڋq�I�u�W�F�N�g
     * @@param l_product - ���������I�u�W�F�N�g
     * @@return WEB3GentradeInsider
     * @@throws WEB3SystemLayerException
     * @@roseuid 4147AEFB0132
     */
    public static WEB3GentradeInsider getInsider(
        WEB3GentradeMainAccount l_genMainAccount,
        Product l_product)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getInsider(WEB3GentradeMainAccount, Product)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j DB����
        //�����҃e�[�u�����ȉ��̏����Ō�������B
        //[����]
        //�،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode()
        //���X�h�c = �ڋq.getBranch().getBranchId()
        //�����h�c = �ڋq.getAccountId()
        //�����h�c = ����.getProductId()
        InsiderRow l_insiderRow;
        try
        {
            l_insiderRow =
                InsiderDao.findRowByPk(
                    l_genMainAccount.getInstitution().getInstitutionCode(),
                    l_genMainAccount.getBranch().getBranchId(),
                    l_genMainAccount.getAccountId(),
                    l_product.getProductId());
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeInsider.class.getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        //�Q�j �I�u�W�F�N�g����
        log.exiting(STR_METHOD_NAME);
        return new WEB3GentradeInsider(l_insiderRow);
        
    }
    
    /**
     * (get������) <BR>
     * �istatic ���\�b�h�j <BR>
     * �w��ɊY����������҃I�u�W�F�N�g��List���擾����B <BR>
     * �P�j�@@QueryProcessor.doFindAllQuery( )�ɂ��A<BR>
     *  �����ҍs�I�u�W�F�N�g��List���擾����B <BR>
     *  <BR>
     *  [doFindAllQuery()�Ɏw�肷�����] <BR>
     *  rowType�F�@@������Row.TYPE <BR>
     *  where�F�@@�������������� <BR>
     *  orderBy�F�@@�\�[�g���� <BR>
     *  conditions�F�@@null <BR>
     *  bindVars�F�@@���������f�[�^�R���e�i <BR>
     *  <BR>
     * �Q�j�@@�������ʂ̍s�I�u�W�F�N�g�œ����҃I�u�W�F�N�g�𐶐����A<BR>
     *  List�ŕԋp����B<BR>
     * @@param l_strWhere - ��������������
     * @@param l_strBindVars - ���������f�[�^�R���e�i
     * @@param l_strOrderBy - �\�[�g����
     * @@return List
     * @@throws WEB3SystemLayerException
     * @@roseuid 4147AEFB0132
     */
    public static List getInsider(
        String l_strWhere,
        String[] l_strBindVars,
        String l_strOrderBy)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getInsider(String, String[], String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@QueryProcessor.doFindAllQuery( )�ɂ��A
        // �����ҍs�I�u�W�F�N�g��List���擾����B
        List l_lstRecords;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lstRecords = l_queryProcessor.doFindAllQuery(
                InsiderRow.TYPE,
                l_strWhere,
                l_strOrderBy,
                null,
                l_strBindVars);
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeInsider.class.getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        //�Q�j�@@�������ʂ̍s�I�u�W�F�N�g�œ����҃I�u�W�F�N�g�𐶐����A
        //List�ŕԋp����B
        int l_intSize = l_lstRecords.size();
        List l_lstGenInsiders = new ArrayList();
        for(int i = 0 ; i < l_intSize; i ++)
        {
            l_lstGenInsiders.add(new WEB3GentradeInsider((InsiderRow)l_lstRecords.get(i)));
        }

        log.exiting(STR_METHOD_NAME);
        return l_lstGenInsiders;
    }
}
@
