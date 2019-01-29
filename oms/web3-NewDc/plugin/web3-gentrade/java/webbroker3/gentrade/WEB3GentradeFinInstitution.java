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
filename	WEB3GentradeFinInstitution.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Z�@@��(WEB3GentradeFinInstitution.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/02 羐� (���u) �V�K�쐬
*/
package webbroker3.gentrade;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.FinInstitutionDao;
import webbroker3.gentrade.data.FinInstitutionRow;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;

/**
 * (���Z�@@��) <BR>
 * �،���Ђւ̓����p�̋��Z�@@�ցi�����j��\������B<BR>
 */
public class WEB3GentradeFinInstitution implements BusinessObject
{
    
    /**
      * Logger
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeFinInstitution.class);

    /**
     * ���Z�@@�֍s�I�u�W�F�N�g<BR>
     * <BR>
     * �� DDL��莩����������B<BR>
     * �� DB���C�A�E�g�u���Z�@@�փe�[�u���d�l.xls�v�Q�ƁB<BR>
     */
    private FinInstitutionRow finInstitutionRow;

    /**
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �����̏����Ɉ�v������Z�@@�փI�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@�����̒l���L�[�Ƃ��ċ��Z�@@�փe�[�u������������B<BR>
     * <BR>
     * �Q�j�@@�s�I�u�W�F�N�g�Z�b�g<BR>
     * �@@�������ʂ̍s�I�u�W�F�N�g�i���Z�@@��Row�j��<BR>
     * this.���Z�@@��Row�ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h<BR>
     * @@param l_strFinInstitutionCode - ���Z�@@�փR�[�h<BR>
     *  <BR>
     * @@return .WEB3GentradeFinInstitution <BR>
     * @@throws WEB3SystemLayerException <BR>
     * @@roseuid 40EE25870077
     */
    public WEB3GentradeFinInstitution(
        String l_strInstitutionCode,
        String l_strFinInstitutionCode) 
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "WEB3GentradeFinInstitution(String, String)";
        
        try
        {
            this.finInstitutionRow = 
                FinInstitutionDao.findRowByPk(l_strInstitutionCode,l_strFinInstitutionCode);
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
            log.error(STR_METHOD_NAME,l_wse);
            throw l_wse;
        }
    
    }

    /**
     * ���Z�@@�փR�[�h���擾����B
     * @@return java.lang.String
     * @@roseuid 40EE2637025B
     */
    public String getFinInstitutionCode()
    {
        return this.finInstitutionRow.getFinInstitutionCode();
    }

    /**
     * (get���Z�@@�֖��i�����j)<BR>
     * ���Z�@@�֖��i�����j���擾����B<BR>
     * @@return java.lang.String
     * @@roseuid 40EE24620384
     */
    public String getFinInstitutionNameKanji()
    {
        return this.finInstitutionRow.getFinInstitutionNameKanji();
    }

    /**
     * (get���Z�@@�֖��i�J�i�j) <BR>
     * ���Z�@@�֖��i�J�i�j���擾����B<BR>
     * @@return java.lang.String
     * @@roseuid 40EE24CF0346
     */
    public String getFinInstitutionNameKana()
    {
        return this.finInstitutionRow.getFinInstitutionNameKana();
    }

    /**
     * @@return Object
     * @@roseuid 41076906007A
     */
    public Object getDataSourceObject()
    {
        return this.finInstitutionRow;
    }
}
@
