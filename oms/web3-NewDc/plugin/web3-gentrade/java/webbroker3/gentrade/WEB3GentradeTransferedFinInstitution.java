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
filename	WEB3GentradeTransferedFinInstitution.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �U������Z�@@��(WEB3GentradeTransferedFinInstitution.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20 羐� (���u) �V�K�쐬
*/
package webbroker3.gentrade;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.TransferedFinInstitutionDao;
import webbroker3.gentrade.data.TransferedFinInstitutionRow;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;

/**
 * (�U������Z�@@��) <BR>
 * �ڋq���o���̂��߂Ɏg�p����w�������\������B<BR>
 */
public class WEB3GentradeTransferedFinInstitution implements BusinessObject
{
    /**
      * Logger
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeTransferedFinInstitution.class);

    /**
     * �U������Z�@@�֍s�I�u�W�F�N�g<BR>
     * <BR>
     * �� DDL��莩����������B<BR>
     * �� DB���C�A�E�g�u�U������Z�@@�փe�[�u���d�l.xls�v�Q�ƁB<BR>
     */
    private TransferedFinInstitutionRow transferedFinInstitutionRow;

    /**
     * �R���X�g���N�^<BR>
     * <BR>
     * �����̏����Ɉ�v����U������Z�@@�փI�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@�����̒l���L�[�Ƃ��ĐU������Z�@@�փe�[�u������������B<BR>
     * <BR>
     * �Q�j�@@�s�I�u�W�F�N�g�Z�b�g<BR>
     * �@@�������ʂ̍s�I�u�W�F�N�g�i�U������Z�@@��Row�j��<BR>
     * this.�U������Z�@@��Row�ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - ���X�R�[�h<BR>
     * @@param l_strAccountCode - �ڋq�R�[�h�i�����ԍ��j<BR>
     * @@param l_strDesignateDiv - �w��敪<BR>
     *  <BR>
     * @@return WEB3GentradeTransferedFinInstitution <BR>
     * @@throws WEB3BaseException <BR>
     * @@roseuid 40EE2AD002A9
     */
    public WEB3GentradeTransferedFinInstitution(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strDesignateDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "WEB3GentradeTransferedFinInstitution(String, String, String)";
        
        try
        {
            this.transferedFinInstitutionRow =
                TransferedFinInstitutionDao.findRowByPk(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strDesignateDiv);
        }
        catch(DataFindException dfe)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01937,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                dfe.getMessage(),
                dfe
            );
        }
        catch(DataException de)
        {
            WEB3SystemLayerException l_wse = 
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    de.getMessage(),
                    de
                );
            log.debug(STR_METHOD_NAME,l_wse);
            throw l_wse;
        }

    }

    /**
     * (get��s��) <BR>
     * ��s�����擾����B <BR>
     * @@return String
     * @@roseuid 40EE2C07025B
     */
    public String getFinInstitutionName()
    {
        return this.transferedFinInstitutionRow.getFinInstitutionName();
    }

    /**
     * (get��s�R�[�h) <BR>
     * ��s�R�[�h���擾����B <BR>
     * @@return String
     * @@roseuid 40EE2C07025B
     */
    public String getFinInstitutionCode()
    {
        return this.transferedFinInstitutionRow.getFinInstitutionCode();
    }

    /**
     * (get�x�X��)<BR>
     * �x�X�����擾����B<BR>
     * @@return java.lang.String
     * @@roseuid 40EE2C1D02F8
     */
    public String getFinBranchName()
    {
        return this.transferedFinInstitutionRow.getFinBranchName();
    }

    /**
     * (get�����ԍ�) <BR>
     * �����ԍ����擾����B<BR>
     * @@return java.lang.String
     * @@roseuid 40EE2C2C022C
     */
    public String getFinAccountNo()
    {
        return this.transferedFinInstitutionRow.getFinAccountNo();
    }

    /**
     * (get�a���敪) <BR>
     * �a���敪���擾����B<BR>
     * @@return java.lang.String
     * @@roseuid 40F4CEC602E3
     */
    public String getFinSaveDiv()
    {
        return this.transferedFinInstitutionRow.getFinSaveDiv();
    }
    
    /**
     * (get�x�X�R�[�h) <BR>
     * �x�X�R�[�h���擾����B<BR>
     * @@return java.lang.String
     */
    public String getFinBranchCode()
    {
        return this.transferedFinInstitutionRow.getFinBranchCode();
    }

    /**
     * @@return Object
     * @@roseuid 410768D502C9
     */
    public Object getDataSourceObject()
    {
        return this.transferedFinInstitutionRow;
    }
}
@
