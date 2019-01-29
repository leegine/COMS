head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.54.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformItemProperty.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �e��A�����ڑ���(WEB3InformItemProperty.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/24 䈋� (���u) �V�K�쐬
*/
package webbroker3.inform;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.inform.data.InformCtrlItemAttributeRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (�e��A�����ڑ���)<BR>
 * �e��A�����ڑ����N���X<BR>
 */
public class WEB3InformItemProperty implements BusinessObject
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3InformItemProperty.class);
    /**
     * (�e��A�����ڑ���List)<BR>
     * �e��A�����ڑ����s�I�u�W�F�N�g�i�e��A�����ڑ���Params�j�̃��X�g<BR>
     */
    private List informCtrlItemAttributeList;


    /**
     * (�e��A�����ڑ���)<BR>
     * �R���X�g���N�^<BR>
     * �e��A�����ڑ����C���X�^���X�𐶐�����B <BR>
     * <BR>
     * �P�j�ȉ��̏����ŁA�e��A�����ڑ����e�[�u�����烌�R�[�h���擾����B <BR>
     * <BR>
     * [�擾����] <BR>
     * �،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * ���X�R�[�h = ����.���X�R�[�h<BR>
     * �A����� = ����.�A�����<BR>
     * ���ڕ����� = ����.���ڕ�����<BR>
     * <BR>
     * �Q�j�擾���ʃ��X�g���v���p�e�B�ɃZ�b�g�����C���X�^���X�𐶐�����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * 
     * @@param l_strInformDiv - (�A�����)<BR>
     * �A�����<BR>
     * 
     * @@param l_strItemSymbolName - (���ڕ�����)<BR>
     * ���ڕ�����<BR>
     * @@roseuid 41BD41400351
     */
    public WEB3InformItemProperty(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strInformDiv,
        String l_strItemSymbolName) throws NotFoundException,WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3InformItemProperty";
        log.entering(STR_METHOD_NAME);
        //�P�j�ȉ��̏����ŁA�e��A�����ڑ����e�[�u�����烌�R�[�h���擾����B <BR>
        //
        //[�擾����] 
        //�،���ЃR�[�h = ����.�،���ЃR�[�h
        //���X�R�[�h = ����.���X�R�[�h
        //�A����� = ����.�A�����
        //���ڕ����� = ����.���ڕ�����
        //
        String l_strQuery = "institution_code = ? ";
        l_strQuery += " and branch_code = ?";
        l_strQuery += " and inform_div = ?";
        l_strQuery += " and item_symbol_name = ?";
        
        Object[] l_queryContainer = new Object[] {
            l_strInstitutionCode,
            l_strBranchCode,
            l_strInformDiv,
            l_strItemSymbolName};
        try
        { 
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            List l_lisRecords = l_queryProcessor.doFindAllQuery(            
                InformCtrlItemAttributeRow.TYPE,
                l_strQuery,
                l_queryContainer
                );  
            //�Q�j�擾���ʃ��X�g���v���p�e�B�ɃZ�b�g�����C���X�^���X�𐶐�����B
            log.exiting(STR_METHOD_NAME);
            if (l_lisRecords != null && l_lisRecords.size() != 0)
            {
                this.informCtrlItemAttributeList = new ArrayList();
                for (int i = 0;i < l_lisRecords.size();i++)
                {
                    this.informCtrlItemAttributeList.add(l_lisRecords.get(i));      
                }
            }
            else
            {
                throw new NotFoundException(STR_METHOD_NAME);
            }
     
        }  
        catch (DataQueryException l_e) 
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        } 
        catch (DataNetworkException l_e) 
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

    }

    /**
     * (validate�L���R�[�h�l)<BR>
     * ���ڂ��L���ȃR�[�h�l�ł��邩�̔�����s���B<BR>
     * <BR>
     * �� �L���R�[�h�l�`�F�b�N�ȊO�̗L���l�`�F�b�N�i�����̂݁C���j�́A<BR>
     * �@@�@@�e��A�����ڑ���#validate�L���l()�ɂĎ��{�B<BR>
     * <BR>
     * �P�j�@@�����͂̏ꍇ�i���ڒl == null�j�́Atrue��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�L���R�[�h�l�`�F�b�N<BR>
     * �@@this.attributeValues()�ɂāA�����l�̔z����擾����B<BR>
     * <BR>
     * �@@�擾���������l[]���ɁA���ڒl�ƈ�v����v�f�����݂���ꍇ��true�A<BR>
     * �@@���݂��Ȃ��ꍇ��false��ԋp����B<BR>
     * @@param l_strItemValue - (���ڒl)<BR>
     * ���ڒl<BR>
     * 
     * @@return boolean
     * @@roseuid 41BD3805037F
     */
    public boolean validateEffectiveCodeValue(String l_strItemValue)
    {
        final String STR_METHOD_NAME = "validateEffectiveCodeValue(String l_strItemValue)";
        log.entering(STR_METHOD_NAME);
        //�P�j�@@�����͂̏ꍇ�i���ڒl == null�j�́Atrue��ԋp����B
        //
        if (l_strItemValue == null)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        //�Q�j�@@�L���R�[�h�l�`�F�b�N
        //�@@this.attributeValues()�ɂāA�����l�̔z����擾����B
        //
        //�@@�擾���������l[]���ɁA���ڒl�ƈ�v����v�f�����݂���ꍇ��true�A
        //�@@���݂��Ȃ��ꍇ��false��ԋp����B
        String[] l_strAttributeValues = this.attributeValues();
        if(this.attributeValues() == null)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        for (int i = 0;i < l_strAttributeValues.length;i++)
        {
            if (l_strItemValue.equals(l_strAttributeValues[i]))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * this.�e��A�����ڑ���List�̊e�s�I�u�W�F�N�g���A<BR>
     * ���ڑ����������ׂĎ擾���z��ɂĕԋp����B<BR>
     * @@return String[]
     * @@roseuid 41BD38160091
     */
    public String[] attributeNames()
    {
        final String STR_METHOD_NAME = "attributeNames()";
        log.entering(STR_METHOD_NAME);
        String[] l_strAttributeNames = null;
        if (this.informCtrlItemAttributeList != null)
        {
            int l_size = this.informCtrlItemAttributeList.size();
            l_strAttributeNames = new String[l_size];
            for (int i = 0; i < l_size; i++)
            {
                InformCtrlItemAttributeRow l_informCtrlItemAttribute = 
                    (InformCtrlItemAttributeRow)this.informCtrlItemAttributeList.get(i);
                l_strAttributeNames[i] = l_informCtrlItemAttribute.getAttributeName();
            }

        }

        log.exiting(STR_METHOD_NAME);
        return l_strAttributeNames;
    }

    /**
     * this.�e��A�����ڑ���List�̊e�s�I�u�W�F�N�g���A<BR>
     * ���ڑ����l�����ׂĎ擾���z��ɂĕԋp����B<BR>
     * @@return String[]
     * @@roseuid 41BD382501AB
     */
    public String[] attributeValues()
    {
        final String STR_METHOD_NAME = "attributeValues()";
        log.entering(STR_METHOD_NAME);
        String[] l_strAttributeValues = null;
        if (this.informCtrlItemAttributeList != null)
        {
            int l_intSize = this.informCtrlItemAttributeList.size();
            l_strAttributeValues = new String[l_intSize];
            for (int i = 0; i < l_intSize; i++)
            {
                InformCtrlItemAttributeRow l_informCtrlItemAttribute = 
                    (InformCtrlItemAttributeRow)this.informCtrlItemAttributeList.get(i);
                l_strAttributeValues[i] = l_informCtrlItemAttribute.getAttributeValue();
            }

        }

        log.exiting(STR_METHOD_NAME);
        return l_strAttributeValues;

    }

    /**
     * (get�����l)<BR>
     * this.�e��A�����ڑ���List�̊e�s�I�u�W�F�N�g���A<BR>
     * �i���ڑ����l == �����̍��ڑ����l�j�ɊY������s�̉����l��ԋp����B<BR>
     * <BR>
     * �i���ڑ����l == �����̍��ڑ����l�j�ɊY������s��2���ȏ゠��ꍇ�́A<BR>
     * �@@�f�[�^�s�����Ɣ��f���B��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     * �@@tag: SYSTEM_ERROR_80006<BR>
     * @@param l_strItemPropertyValue - (���ڑ����l)<BR>
     * ���ڑ����l<BR>
     * 
     * @@return Double
     * @@roseuid 41BD3830018B
     */
    public Double getRangeFrom(String l_strItemPropertyValue) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getRangeFrom(String l_strItemPropertyValue)";
        log.entering(STR_METHOD_NAME);
        int l_intNumber = 0;
        int l_intIndex = 0;
        if (this.informCtrlItemAttributeList != null)
        {
            int l_size = this.informCtrlItemAttributeList.size();
            for (int i = 0; i < l_size; i++)
            {
                InformCtrlItemAttributeRow l_informCtrlItemAttribute = 
                    (InformCtrlItemAttributeRow)this.informCtrlItemAttributeList.get(i);
                if (l_informCtrlItemAttribute.getAttributeValue().equals(l_strItemPropertyValue))
                {
                    l_intNumber++;  
                    l_intIndex = i;                  
                }
            }
            if (l_intNumber >= 2)
            {
                log.error("�f�[�^�s����");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                    this.getClass().getName() + STR_METHOD_NAME); 
            }
            else 
            {
                InformCtrlItemAttributeRow l_informCtrlItemAttribute = 
                    (InformCtrlItemAttributeRow)this.informCtrlItemAttributeList.get(l_intIndex);
                log.exiting(STR_METHOD_NAME);
                return new Double(l_informCtrlItemAttribute.getRangeFrom());
            }

        }
        log.exiting(STR_METHOD_NAME);
        return null;

    }

    /**
     * (get����l)<BR>
     * this.�e��A�����ڑ���List�̊e�s�I�u�W�F�N�g���A<BR>
     * �i���ڑ����l == �����̍��ڑ����l�j�ɊY������s�̏���l��ԋp����B<BR>
     * <BR>
     * �i���ڑ����l == �����̍��ڑ����l�j�ɊY������s��2���ȏ゠��ꍇ�́A<BR>
     * �^�s�����Ɣ��f���B��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     * �@@tag: SYSTEM_ERROR_80006<BR>
     * @@param l_strItemPropertyValue - (���ڑ����l)<BR>
     * ���ڑ����l<BR>
     * 
     * @@return Double
     * @@roseuid 41BD39320053
     */
    public Double getRangeTo(String l_strItemPropertyValue) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getRangeTo(String l_strItemPropertyValue)";
        log.entering(STR_METHOD_NAME);
        int l_intNumber = 0;
        int l_intIndex = 0;
        if (this.informCtrlItemAttributeList != null)
        {
            int l_size = this.informCtrlItemAttributeList.size();
            for (int i = 0; i < l_size; i++)
            {
                InformCtrlItemAttributeRow l_informCtrlItemAttribute = 
                    (InformCtrlItemAttributeRow)this.informCtrlItemAttributeList.get(i);
                if (l_informCtrlItemAttribute.getAttributeValue().equals(l_strItemPropertyValue))
                {
                    l_intNumber++;  
                    l_intIndex = i;                  
                }
            }
            if (l_intNumber >= 2)
            {
                log.error("�f�[�^�s����");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                    this.getClass().getName() + STR_METHOD_NAME); 
            }
            else 
            {
                InformCtrlItemAttributeRow l_informCtrlItemAttribute = 
                    (InformCtrlItemAttributeRow)this.informCtrlItemAttributeList.get(l_intIndex);
                log.exiting(STR_METHOD_NAME);
                return new Double(l_informCtrlItemAttribute.getRangeTo());
            }

        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * @@return Object
     * @@roseuid 41EE642D007D
     */
    public Object getDataSourceObject()
    {
        return null;
    }
}
@
