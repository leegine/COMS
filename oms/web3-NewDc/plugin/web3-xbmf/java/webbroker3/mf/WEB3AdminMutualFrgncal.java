head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualFrgncal.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�C�O�s��J�����_(WEB3AdminMutualFrgncal)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/05 ���� (���u) �V�K�쐬
Revesion History : 2009/01/23 ���u�� (���u) �d�l�ύX���f��No.639
*/
package webbroker3.mf;
import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.mf.data.MutualFundFrgncalRow;
import webbroker3.util.WEB3LogUtility;

/**
 * ���M�C�O�s��J�����_<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3AdminMutualFrgncal 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminMutualFrgncal.class);

    /**
     * (is�x��)<BR>
     * �w�肳�ꂽ���t���C�O�s��̋x�����̔�����s���B<BR>
     * <BR>
     * �P�j�@@���M�C�O�s��J�����_�e�[�u�����������A�������ɊY�����郌�R�[�h<BR>
     * �@@���Ȃ����`�F�b�N����B<BR>
     * �@@�m���������n<BR>
     * �@@�@@�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * �@@�@@AND �����R�[�h = ����.�����R�[�h<BR>
     * �@@�@@AND �x�� = ����.���t<BR>
     * <BR>
     * �Q�j�@@�Y�����郌�R�[�h�����݂���ꍇ�� true ���A�����łȂ��ꍇ�� false ��<BR>
     * �@@�Ԃ��B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strMutualProductCode - �����R�[�h
     * @@param l_tsBizDate - ���t
     * @@return boolean
     * @@roseuid 40BB0DC90137
     */
    public boolean isHoliday(
        String l_strInstitutionCode,
        String l_strMutualProductCode,
        Timestamp l_tsBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "isHoliday(String l_strInstitutionCode, " +
            "String l_strMutualProductCode, " +
            "Timestamp l_tsBizDate)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strInstitutionCode == null || 
            "".equals(l_strInstitutionCode) || 
            l_strMutualProductCode == null ||
            "".equals(l_strMutualProductCode) ||
            l_tsBizDate == null) 
        {
            log.debug("�p�����[�^Null�o���Ȃ� with" +
                " (�،���ЃR�[�h)l_strInstitutionCode =" + 
                    l_strInstitutionCode + 
                " and (�����R�[�h)l_strMutualProductCode =" + 
                    l_strMutualProductCode + 
                " and (���t)l_tsBizDate =" + 
                    l_tsBizDate);        
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);   
        }
        try
        {
            //�P�j�@@���M�C�O�s��J�����_�e�[�u�����������A
            //�������ɊY�����郌�R�[�h���Ȃ����`�F�b�N����
            //�����Ώۃ��R�[�h�擾
            QueryProcessor l_processorObject = Processors.getDefaultProcessor();
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? "); //�،���ЃR�[�h   
            l_sbWhere.append(" and product_code = ? "); //�����R�[�h
            l_sbWhere.append(" and holiday = ? ");      //���t
            Object[] l_objMutualFrgncalWhere =
                { l_strInstitutionCode, l_strMutualProductCode, l_tsBizDate };
            /*����*/
            List l_lisMFFrgncalRows = 
                l_processorObject.doFindAllQuery(
                    MutualFundFrgncalRow.TYPE,
                    l_sbWhere.toString(),
                    l_objMutualFrgncalWhere); 
                    
            // �Q�j�Y�����郌�R�[�h�����݂���ꍇ�� true ���A
             //�����łȂ��ꍇ�� false ��Ԃ�                 
            if(l_lisMFFrgncalRows.isEmpty())
            {  
                return false;   
            }
        } 
        catch (DataNetworkException l_ex) 
        {
            log.error("�����M�C�O�s��J�����_�e�[�u������������," +
                "DB�ւ̃A�N�Z�X�Ɏ��s���܂���, ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex) 
        {
            log.error("�����M�C�O�s��J�����_�e�[�u������������, " +
                "DB�ւ̃A�N�Z�X�Ɏ��s���܂��� ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return true;        
    }

    /**
     * �istatic method)<BR>
     * (get�x���ꗗ)<BR>
     * <BR>
     * �Y�������̋x�������w�茎�������擾���A�ԋp����B<BR>
     * <BR>
     * �P�j�@@����:���������f�[�^�R���e�i�A��������������ɁA�ȉ��̏�����<BR>
     * �@@�ǉ�����B<BR>
     * <BR>
     * �@@�@@[��������]<BR>
     * �@@�@@�@@�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * �@@�@@[���ёւ��n<BR>
     * �@@�@@�@@�x��<BR>
     * <BR>
     * �Q)�@@���M�C�O�s��J�����_�[�e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �R)�@@�P)�̌������ʂ�ԋp����B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strSearchCondCharacterString - ��������������
     * @@param l_strSearchCondDataContainer - ���������f�[�^�R���e�i
     * @@return List
     * @@roseuid 40D8281502F6
     */
    public static List getHolidayList(
        String l_strInstitutionCode,
        String l_strSearchCondCharacterString,
        String[] l_strSearchCondDataContainer) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getHolidayList(String l_strInstitutionCode," +
            "String l_strSearchCondCharacterString, " +
            "String[ ] l_strSearchCondDataContainer) ";
        if (l_strInstitutionCode == null || l_strInstitutionCode.length() == 0)
        {
            log.debug(" �p�����[�^Null�o���Ȃ�");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminMutualFrgncal" + "." + STR_METHOD_NAME);
        }    
        log.entering(STR_METHOD_NAME);
        List l_lisMFFrgncalRows = null;     //�̌������ʂ�ԋp����
        try 
        {
            //�P�j�@@����:���������f�[�^�R���e�i�A��������������ɁA�ȉ��̏�����
            
            //�����Ώۃ��R�[�h�擾
            QueryProcessor l_processorObject = 
                Processors.getDefaultProcessor();
            
            //�x�����ёւ�s
            String l_strSort = " holiday ";        
          
            if (l_strSearchCondCharacterString  != null &&  
                !"".equals(l_strSearchCondCharacterString)) 
            {
                l_strSearchCondCharacterString =
                    l_strSearchCondCharacterString + " and institution_code = ? ";
            }
            else
            {
                l_strSearchCondCharacterString = "institution_code = ?";   
            
            }
           
            //���ʂ�ԋp����
            String[] l_searchParams = null; 
            int l_intSearchCondDataContainer = 0;
            if (l_strSearchCondDataContainer != null)
            {
                l_intSearchCondDataContainer = 
                    l_strSearchCondDataContainer.length;
                l_searchParams = 
                    new String[l_intSearchCondDataContainer + 1];
                for (int i = 0; i < l_intSearchCondDataContainer; i++)
                {
                    l_searchParams[i] = l_strSearchCondDataContainer[i];
                }
                l_searchParams[l_intSearchCondDataContainer] = l_strInstitutionCode; 
            }
            else
            {
                l_searchParams = new String[1];
                l_searchParams[0] = l_strInstitutionCode;
            }
            //�Q)�@@���M�C�O�s��J�����_�[�e�[�u�����ȉ��̏����Ō�������B
            /*���� Begin*/
            l_lisMFFrgncalRows =
                l_processorObject.doFindAllQuery(
                    MutualFundFrgncalRow.TYPE,
                    l_strSearchCondCharacterString,
                    l_strSort,
                    null,
                    l_searchParams);
        }
        catch (DataNetworkException l_ex) 
        {
            log.error("�����M�C�O�s��J�����_�[������") ;
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AdminMutualFrgncal.class.getName() + "." + STR_METHOD_NAME,   
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex) 
        {
            log.error("�����M�C�O�s��J�����_�[������");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AdminMutualFrgncal.class.getName() + "." + STR_METHOD_NAME,   
                l_ex.getMessage(),
                l_ex);
        }
        
       // �R)�@@�P)�̌������ʂ�ԋp����B
        log.exiting(STR_METHOD_NAME); 
        return l_lisMFFrgncalRows;
    }
}

@
