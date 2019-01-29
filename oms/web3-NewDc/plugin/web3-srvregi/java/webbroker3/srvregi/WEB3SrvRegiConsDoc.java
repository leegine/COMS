head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.39.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiConsDoc.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ӏ�����(WEB3SrvRegiConsDoc.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 ���w�� (���u) �V�K�쐬
*/

package webbroker3.srvregi;

import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.srvregi.data.SrvRegiConsDocParams;
import webbroker3.srvregi.data.SrvRegiConsDocRow;
import webbroker3.util.WEB3LogUtility;


/**
 * (���ӏ�����)<BR>
 * ���ӏ������G���e�B�e�B�N���X<BR>                    
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3SrvRegiConsDoc 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SrvRegiConsDoc.class);
       
    /**
     * (�ő啶����)<BR>
     * �e�[�u����VARCHAR2(4000)�̗�ł���ׁA���̔������w��<BR>
     */
    public static  int MAX_CHARACTER_QUANTITY = 2000;
    
    /**
     * (�،���ЃR�[�h)
     */
    private String institutionCode;
    
    /**
     * (�T�[�r�X�敪)<BR>
     */
    private String srvDiv;
    
    /**
     * (����)<BR>
     */
    private String cons;
    
    /**
     * @@roseuid 416F4A9E007D
     */
    public WEB3SrvRegiConsDoc() 
    {
     
    }
    
    /**
     * (get�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h��Ԃ��B<BR>
     * <BR>
     * this.�،���ЃR�[�h��Ԃ��B<BR>
     * @@return String
     * @@roseuid 41049D5C0010
     */
    public String getInstitutionCode() 
    {
        return this.institutionCode;
    }
    
    /**
     * (get�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪��Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X�敪��Ԃ��B<BR>
     * @@return String
     * @@roseuid 41049D5C002F
     */
    public String getSrvDiv() 
    {
        return this.srvDiv;
    }
    
    /**
     * (get����)<BR>
     * ������Ԃ��B<BR>
     * <BR>
     * this.������ԋp����B<BR>
     * @@return String
     * @@roseuid 40F5164301AC
     */
    public String getCons() 
    {
        return this.cons;
    }
    
    /**
     * (set����)<BR>
     * ���ӏ��̕�����ݒ肷��B<BR>
     * <BR>
     * this.�����Ɉ���.������ݒ肷��B<BR>
     * @@param l_strCons - (����)<BR>
     * ���ӏ��̕���<BR>
     * @@roseuid 40F51680013F
     */
    public void setCons(String l_strCons) 
    {
        this.cons = l_strCons;
    }
    
    /**
     * (save���ӏ�����)<BR>
     * ���ӏ��������f�[�^�x�[�X�ɕۑ�����B(Update)<BR>
     * <BR>
     * 1) �ȉ��̏����œ��ӏ������e�[�u������������B<BR>
     * [��������]<BR>
     * �@@�،���ЃR�[�h=����.�،���ЃR�[�h and<BR>
     * �@@�T�[�r�X�敪=����.�T�[�r�X�敪<BR>
     *  1-1) �������ʁ�0���̏ꍇ�A�z��̐擪�̓��ӏ�����<BR>
     *       Params�I�u�W�F�N�g���擾����B<BR>
     *  1-2) �擾�������ӏ�����Params.get�쐬���t( )���R�[�����A<BR>
     * �@@�@@�@@�쐬���t���擾����B<BR>
     * <BR>
     * 2) �ȉ��̏����ɍ��v���郌�R�[�h���A���ӏ������e�[�u������폜����B<BR>
     * [�폜����]<BR>
     * �@@�،���ЃR�[�h = ����.�،���ЃR�[�h and<BR>
     * �@@�T�[�r�X�敪 = ����.�T�[�r�X�敪<BR>
     * <BR>
     * 2) ����.���ӏ�������null�̏ꍇ�A�������I������B<BR>
     * <BR>
     * 3) ����.���ӏ�����.toCharArray()���R�[�����A�����z����擾����B<BR>
     * <BR>
     * 4) �����z����ő啶�����P�ʂŕ������A���̕������������z��P�ʂ�<BR>
     * �@@��������String�̔z��𐶐�����B<BR>
     * <BR>
     * �@@��j�@@�����z��{'�T','�[','�r','�X','��','�p'}�ōő啶������3�̏ꍇ�A<BR>
     * �@@�@@�@@{'�T','�[','�r','�X'}�A{'��','�p'}�̂Q�̕����z������ƂɂQ��String�I�u�W�F�N�g<BR>
     * �@@�@@�@@�𐶐����A�v�f���Q��String�̔z��ɐݒ肷��B<BR>
     * <BR>
     * 5) ����ȍ~�̏�����String�̔z��̗v�f�����J��Ԃ��B<BR>
     * <BR>
     * 6) ���ӏ�����Params�I�u�W�F�N�g�𐶐����A�ȉ��̒l��ݒ肷��B<BR>
     * �@@�،���ЃR�[�h=����.�،���ЃR�[�h<BR>
     * �@@�T�[�r�X�敪=����.�T�[�r�X�敪<BR>
     * �@@�s�ԍ�=String�z��̗v�f�ԍ��i0�`n�j + 1<BR>
     * �@@����=String�z��[�v�f�ԍ�]<BR>
     * �@@�X�V�҃R�[�h=�Ǘ���.getInstanceFrom���O�C�����( ).get�Ǘ��҃R�[�h( )<BR>
     * �@@�쐬���t=1-2)�Ŏ擾�����쐬���t<BR>
     * �@@�X�V���t=GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l<BR>
     * <BR>
     * 7) ���ӏ�����Params�I�u�W�F�N�g�̏����f�[�^�x�[�X�ɃC���T�[�g����B<BR>
     * <BR>
     * 8) �쐬�������ӏ�����Params�I�u�W�F�N�g�̓��e�ŁA���ӏ������e�[�u����<BR>
     * �@@�X�V�iInsert�j����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@param l_strConsDoc - (���ӏ�����)
     * @@roseuid 413E635D0262
     */
    public void saveConsDoc(String l_strInstitutionCode, String l_strSrvDiv, String l_strConsDoc) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveConsDoc(String l_strInstitutionCode, String l_strSrvDiv, String l_strConsDoc)";
        log.entering(STR_METHOD_NAME);
        
        //1) �ȉ��̏����œ��ӏ������e�[�u������������B
        //[��������]
        //�،���ЃR�[�h=����.�،���ЃR�[�h and 
        //�T�[�r�X�敪=����.�T�[�r�X�敪 
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and srv_div = ? ");
        
        Object[] l_objWhere =
            {
                l_strInstitutionCode,
                l_strSrvDiv
            };
        List l_lisRecords = null;
        SrvRegiConsDocParams l_srvRegiConsDocParams = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                SrvRegiConsDocRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere);//DataNetworkException,DataQueryException

        
            //1-1) �������ʁ�0���̏ꍇ�A�z��̐擪�̓��ӏ�����Params�I�u�W�F�N�g���擾����B
            Timestamp l_tsSystemTime = null;
            if (l_lisRecords.size() == 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3SrvRegiConsDoc.class.getName() + STR_METHOD_NAME);
            }
            else
            {
                l_srvRegiConsDocParams = (SrvRegiConsDocParams)l_lisRecords.get(0);
                //1-2) �擾�������ӏ�����Params.get�쐬���t( )���R�[�����A         
                l_tsSystemTime = l_srvRegiConsDocParams.getCreatedTimestamp();
            }
           
            //2) �ȉ��̏����ɍ��v���郌�R�[�h���A���ӏ������e�[�u������폜����B
            //[�폜����]
            //�،���ЃR�[�h = ����.�،���ЃR�[�h and
            //�T�[�r�X�敪 = ����.�T�[�r�X�敪              
            l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryExceptio
            
            StringBuffer l_sbWhereNew = new StringBuffer();
            l_sbWhereNew.append(" institution_code = ? ");
            l_sbWhereNew.append(" and srv_div = ? ");
    
            Object[] l_objWhereNew =
                {
                    l_strInstitutionCode,
                    l_strSrvDiv
                };
                                          
            QueryProcessor l_queryProcessorNew = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            l_queryProcessorNew.doDeleteAllQuery(SrvRegiConsDocRow.TYPE, l_sbWhereNew.toString(), l_objWhereNew);//DataNetworkException,DataQueryException
            
            //2) ����.���ӏ�������null�̏ꍇ�A�������I������B
            if (l_strConsDoc == null || "".equals(l_strConsDoc.trim()))
            {
                log.exiting(STR_METHOD_NAME);
                return;
            }
           
            //3) ����.���ӏ�����.toCharArray()���R�[�����A�����z����擾����B
            char[] l_chConsDoc = l_strConsDoc.toCharArray();
            
            //4) �����z����ő啶�����P�ʂŕ������A���̕������������z��P�ʂŐ�������String�̔z��𐶐�����B
            int l_intDocLength = 0;
            if (l_chConsDoc.length != 0)
            {
                l_intDocLength = l_chConsDoc.length / MAX_CHARACTER_QUANTITY;
                if (l_chConsDoc.length % MAX_CHARACTER_QUANTITY != 0)
                {
                    l_intDocLength = l_intDocLength + 1;
                }
            }
            
            String[] l_strNewConsDoc = new String[l_intDocLength];
            
            int l_intConsDocLength = l_chConsDoc.length;
            
            for (int i=0; i < l_intDocLength; i++)
            {
                l_strNewConsDoc[i] = ""; 
                for (int j=0; j<MAX_CHARACTER_QUANTITY; j++)
                {
                    int l_intNewConsDocLength = j + i * MAX_CHARACTER_QUANTITY;
                    if (l_intNewConsDocLength < l_intConsDocLength)
                    {
                        l_strNewConsDoc[i] = l_strNewConsDoc[i] + l_chConsDoc[j + i * MAX_CHARACTER_QUANTITY];
                    }
                }
            }
                    
            //5) ����ȍ~�̏�����String�̔z��̗v�f�����J��Ԃ��B
            for (int i = 0; i < l_strNewConsDoc.length; i++)
            {
                //6) ���ӏ�����Params�I�u�W�F�N�g�𐶐����A�ȉ��̒l��ݒ肷��B<BR>
                l_srvRegiConsDocParams = new SrvRegiConsDocParams();
            
                //�،���ЃR�[�h=����.�،���ЃR�[�h
                l_srvRegiConsDocParams.setInstitutionCode(l_strInstitutionCode);
            
                //�T�[�r�X�敪=����.�T�[�r�X�敪
                l_srvRegiConsDocParams.setSrvDiv(l_strSrvDiv);
            
                //�s�ԍ�=String�z��̗v�f�ԍ��i0�`n�j + 1
                l_srvRegiConsDocParams.setLineNumber(i + 1);
            
                //����=String�z��[�v�f�ԍ�]
                l_srvRegiConsDocParams.setLineValue(String.valueOf(l_strNewConsDoc[i]));
            
                //�X�V�҃R�[�h=�Ǘ���.getInstanceFrom���O�C�����( ).get�Ǘ��҃R�[�h( )
                String l_strAdministratorCode = WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode();
                l_srvRegiConsDocParams.setLastUpdater(l_strAdministratorCode);
            
                //�쐬���t=1-2)�Ŏ擾�����쐬���t
                l_srvRegiConsDocParams.setCreatedTimestamp(l_tsSystemTime);
            
                //�X�V���t=GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l
                l_srvRegiConsDocParams.setLastUpdatedTimestamp(GtlUtils.getTradingSystem( ).getSystemTimestamp( ));
            
                //7) ���ӏ�����Params�I�u�W�F�N�g�̏����f�[�^�x�[�X�ɃC���T�[�g����B                      
                //8) �쐬�������ӏ�����Params�I�u�W�F�N�g�̓��e�ŁA���ӏ������e�[�u�����X�V�iInsert�j����B
          
                l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
                l_queryProcessor.doInsertQuery(l_srvRegiConsDocParams);//DataNetworkException,DataQueryException
            }
        
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiConsDoc.class.getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiConsDoc.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiConsDoc.class.getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiConsDoc.class.getName() + STR_METHOD_NAME);
        }
              
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (saveNew���ӏ�����)<BR>
     * ���ӏ��������f�[�^�x�[�X�ɕۑ�����B(Insert)<BR>
     * <BR>
     * 1) ����.���ӏ�������null�̏ꍇ�A�������I������B<BR>
     * <BR>
     * 2) ����.���ӏ�����.toCharArray()���R�[�����A�����z����擾����B<BR>
     * <BR>
     * 3) �����z����ő啶�����P�ʂŕ������A���̕������������z��P�ʂ�<BR>
     * �@@��������String�̔z��𐶐�����B<BR>
     * <BR>
     * �@@��j�@@�����z��{'�T','�[','�r','�X','��','�p'}�ōő啶������4�̏ꍇ�A<BR>
     * �@@�@@�@@{'�T','�[','�r','�X'}�A{'��','�p'}��2�̕����z������Ƃ�2��String�I�u�W�F�N�g<BR>
     * �@@�@@�@@�𐶐����A�v�f��2��String�̔z��ɐݒ肷��B<BR>
     * <BR>
     * 4) ����ȍ~�̏�����String�̔z��̗v�f�����J��Ԃ��B<BR>
     * <BR>
     * 5) ���ӏ�����Params�I�u�W�F�N�g�𐶐����A�ȉ��̒l��ݒ肷��B<BR>
     * �@@�،���ЃR�[�h=����.�،���ЃR�[�h<BR>
     * �@@�T�[�r�X�敪=����.�T�[�r�X�敪<BR>
     * �@@�s�ԍ�=String�z��̗v�f�ԍ��i0�`n�j + 1<BR>
     * �@@����=String�z��[�v�f�ԍ�]<BR>
     * �@@�X�V�҃R�[�h=�Ǘ���.getInstanceFrom���O�C�����( ).get�Ǘ��҃R�[�h( )<BR>
     * �@@�쐬���t=GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l<BR>
     * �@@�X�V���t=GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l<BR>
     * <BR>
     * 6) ���ӏ�����Params�I�u�W�F�N�g�̏����f�[�^�x�[�X�ɃC���T�[�g����B<BR>
     * <BR>
     * 7) �쐬�������ӏ�����Params�I�u�W�F�N�g�̓��e�ŁA���ӏ������e�[�u����<BR>
     * �@@�X�V�iInsert�j����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@param l_strConsDoc - (���ӏ�����)<BR>
     * @@roseuid 413E635D0272
     */
    public void saveNewConsDoc(String l_strInstitutionCode, String l_strSrvDiv, String l_strConsDoc) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveNewConsDoc(String l_strInstitutionCode, String l_strSrvDiv, String l_strConsDoc)";
        log.entering(STR_METHOD_NAME);
        //1) ����.���ӏ�������null�̏ꍇ�A�������I������B
        char[] l_chConsDoc;
        if (l_strConsDoc == null || l_strConsDoc.trim().equals(""))
        {
            return;
        }
        else
        {
            //2) ����.���ӏ�����.toCharArray()���R�[�����A�����z����擾����B
            l_chConsDoc = l_strConsDoc.toCharArray();
            
        }
        
        //3) �����z����ő啶�����P�ʂŕ������A���̕������������z��P�ʂŐ�������String�̔z��𐶐�����B        
        int l_intDocLength = 0;
        if (l_chConsDoc.length != 0)
        {
            l_intDocLength = l_chConsDoc.length / MAX_CHARACTER_QUANTITY;
            if (l_chConsDoc.length % MAX_CHARACTER_QUANTITY != 0)
            {
                l_intDocLength = l_intDocLength + 1;
            }
        }
        
        String[] l_strNewConsDoc = new String[l_intDocLength];
        
        int l_intConsDocLength = l_chConsDoc.length;
        
        for (int i = 0; i < l_intDocLength; i++)
        {
            l_strNewConsDoc[i] = ""; 
            for (int j = 0; j < MAX_CHARACTER_QUANTITY; j++)
            {
                int l_intNewConsDocLength = j + i * MAX_CHARACTER_QUANTITY;
                if (l_intNewConsDocLength < l_intConsDocLength)
                {
                    l_strNewConsDoc[i] = l_strNewConsDoc[i] + l_chConsDoc[j + i * MAX_CHARACTER_QUANTITY];
                }                
            }
        }
        //4) ����ȍ~�̏�����String�̔z��̗v�f�����J��Ԃ��B
        for (int i = 0; i < l_strNewConsDoc.length; i++)
        {
            //5) ���ӏ�����Params�I�u�W�F�N�g�𐶐����A�ȉ��̒l��ݒ肷��B
            SrvRegiConsDocParams l_srvRegiConsDocParams = new SrvRegiConsDocParams();
        
            //�،���ЃR�[�h=����.�،���ЃR�[�h
            l_srvRegiConsDocParams.setInstitutionCode(l_strInstitutionCode);
        
            //�T�[�r�X�敪=����.�T�[�r�X�敪
            l_srvRegiConsDocParams.setSrvDiv(l_strSrvDiv);
        
            //�s�ԍ�=String�z��̗v�f�ԍ��i0�`n�j + 1
            l_srvRegiConsDocParams.setLineNumber(i + 1);
        
            //����=String�z��[�v�f�ԍ�]
            l_srvRegiConsDocParams.setLineValue(l_strNewConsDoc[i]);
        
            //�X�V�҃R�[�h=�Ǘ���.getInstanceFrom���O�C�����( ).get�Ǘ��҃R�[�h( )
            String l_strAdministratorCode = WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode();       
            l_srvRegiConsDocParams.setLastUpdater(l_strAdministratorCode);
        
            //�쐬���t=GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l
            Timestamp l_tsSystemTime = GtlUtils.getTradingSystem( ).getSystemTimestamp();
            l_srvRegiConsDocParams.setCreatedTimestamp(l_tsSystemTime);
        
            //�X�V���t=GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l
            l_srvRegiConsDocParams.setLastUpdatedTimestamp(l_tsSystemTime);
            
            //6) ���ӏ�����Params�I�u�W�F�N�g�̏����f�[�^�x�[�X�ɃC���T�[�g����B
            //7) �쐬�������ӏ�����Params�I�u�W�F�N�g�̓��e�ŁA���ӏ������e�[�u�����X�V�iInsert�j����B
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
                l_queryProcessor.doInsertQuery(l_srvRegiConsDocParams);//DataNetworkException,DataQueryException
            }
            catch (DataNetworkException l_ex)
            {
                //DB�A�N�Z�X�����s�̏ꍇ
                log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME); 
                //��O���X���[����
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                     getClass().getName() + STR_METHOD_NAME,
                     l_ex.getMessage(), l_ex);
            }
            catch (DataQueryException l_ex)
            {
                //DB�A�N�Z�X�����s�̏ꍇ
                log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME); 
                //��O���X���[����
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(), l_ex);
            }
        }
               
        log.exiting(STR_METHOD_NAME);        
    }
}@
