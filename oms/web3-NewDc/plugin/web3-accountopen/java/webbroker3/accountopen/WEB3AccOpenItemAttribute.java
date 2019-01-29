head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.31.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenItemAttribute.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݍ��ڑ���(WEB3AccOpenItemAttribute.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 ���� (���u) �V�K�쐬
*/

package webbroker3.accountopen;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.accountopen.data.AccOpenItemAttributeRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����J�ݍ��ڑ���)<BR>
 * �����J�ݍ��ڑ���<BR>
 *                                                              
 * @@author ����
 * @@version 1.0
 */
public class WEB3AccOpenItemAttribute 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
     private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AccOpenItemAttribute.class);

    /**
     * (�����J�ݍ��ڑ���List)<BR>
     * �����J�ݍ��ڑ����s�i�F�����J�ݍ��ڑ���Params�j�̃��X�g<BR>
     * <BR>
     * �� �����J�ݍ��ڑ���Params�N���X��DDL��莩����������B<BR>
     */
    private List accOpenItemAttributeParamsList;
    
    /**
     * (�����J�ݍ��ڑ���)<BR>
     * �R���X�g���N�^�B<BR>
     * �����J�ݍ��ڑ����𐶐�����B<BR>
     * <BR>
     * �ȉ��̏����Ō����J�ݍ��ڑ����e�[�u������������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�����J�ݍ��ڑ���.�،���ЃR�[�h = �،���ЃR�[�h And<BR>
     * �@@�����J�ݍ��ڑ���.���X�R�[�h = ���X�R�[�h And<BR>
     * �@@�����J�ݍ��ڃ}�X�^.�����敪 = �����敪 And<BR>
     * �@@�����J�ݍ��ڑ���.���ڕ����� = ���ڕ�����<BR>
     * <BR>
     * �������ʃ��X�g���v���p�e�B�ɃZ�b�g�����C���X�^���X�𐶐�����B <BR>
     * <BR>
     * �� �����J�ݍ��ڑ���Params�N���X��DDL��莩����������B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strMainAccountType - �����敪
     * 
     * @@param l_strColumnName - ���ڕ�����<BR>
     * <BR>
     * ���u�����J�݌����q�e�[�u���v�̗񕨗����B<BR>
     * 
     * @@return webbroker3.accountopen.WEB3AccOpenItemAttribute
     * @@roseuid 4187518602E1
     */
    public WEB3AccOpenItemAttribute(String l_strInstitutionCode, String l_strBranchCode,
            String l_strMainAccountType, String l_strColumnName) throws WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME = " WEB3AccOpenItemAttribute(String, String, String, String)";
        
        log.entering(STR_METHOD_NAME);
        
        //Q&A:WEB3-ACCOUNTOPEN-A-UT-0022:�R���X�g���N�^�̈����̌^�̕ύX(MainAccountTypeEnum�@@�ˁ@@String)�����肢���܂��B
        
        String l_strWhere = " institution_code = ? and branch_code = ? and account_div = ? and item_symbol_name = ? ";
        
        Object[] l_obj = {l_strInstitutionCode, l_strBranchCode, l_strMainAccountType, l_strColumnName};
        
        try
        {
            this.accOpenItemAttributeParamsList = Processors.getDefaultProcessor().doFindAllQuery(AccOpenItemAttributeRow.TYPE, l_strWhere, l_obj); //DataQueryException, DataNetworkException
            
            if (this.accOpenItemAttributeParamsList == null || this.accOpenItemAttributeParamsList.size() == 0)
            {
                throw new NotFoundException("�������ʂɈ�v����s�����݂��Ȃ�");
            }
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�L���R�[�h�l)<BR>
     * ���ڂ��L���ȃR�[�h�l�ł��邩�̔�����s���B<BR>
     * <BR>
     * �� �L���R�[�h�l�`�F�b�N�ȊO�̗L���l�`�F�b�N�i�����̂݁C���j�́A<BR>
     * �@@�@@�����J�ݍ��ڑ���#validate�L���l()�ɂĎ��{�B<BR>
     * <BR>
     * �P�j�@@�����͂̏ꍇ�i���ڒl == null�j�́Atrue��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�L���R�[�h�l�`�F�b�N<BR>
     * �@@this.attributeValues()�ɂāA�����l�̔z����擾����B<BR>
     * <BR>
     * �@@�擾���������l[]���ɁA���ڒl�ƈ�v����v�f�����݂���ꍇ��true�A<BR>
     * �@@���݂��Ȃ��ꍇ��false��ԋp����B<BR>
     * @@param l_strItemValue - ���ڒl
     * @@return boolean
     * @@roseuid 41874FF2032F
     */
    public boolean validateValidCodeValue(String l_strItemValue) 
    {
        final String STR_METHOD_NAME = " validateValidCodeValue(String)";
        
        log.entering(STR_METHOD_NAME);
        
        if (l_strItemValue == null)
        {
            log.debug("�����͂̏ꍇ�i���ڒl == null�j�́Atrue��ԋp����B");
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        String[] l_strAttributeValues = this.attributeValues();
        
        int l_intLength = l_strAttributeValues.length;
        
        for (int i = 0; i < l_intLength; i++)
        {
            if (l_strItemValue.equals(l_strAttributeValues[i]))
            {
                log.debug("�擾���������l[]���ɁA���ڒl�ƈ�v����v�f�����݂���ꍇ��true��ԋp����B");
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        log.debug("�擾���������l[]���ɁA���ڒl�ƈ�v����v�f�����݂��Ȃ��ꍇ��false��ԋp����B");
        log.exiting(STR_METHOD_NAME);
        return false;
    }
    
    /**
     * (attributeNames)<BR>
     * this.�����J�ݍ��ڑ���List�̊e�s�I�u�W�F�N�g���A���ڑ�������<BR>
     * ���ׂĎ擾���z��ɂĕԋp����B<BR>
     * @@return String[]
     * @@roseuid 418755230301
     */
    public String[] attributeNames() 
    {
        final String STR_METHOD_NAME = " attributeNames()";
        
        log.entering(STR_METHOD_NAME);
        
        int l_intLengh = this.accOpenItemAttributeParamsList.size();
        
        String[] l_strAttributeNames = new String[l_intLengh];
        for (int i = 0; i < l_intLengh; i++)
        {
            l_strAttributeNames[i] = ((AccOpenItemAttributeRow)this.accOpenItemAttributeParamsList.get(i)).getAttributeName();            
        }

        log.exiting(STR_METHOD_NAME);
        
        return l_strAttributeNames;
    }
    
    /**
     * (attributeValues)<BR>
     * this.�����J�ݍ��ڑ���List�̊e�s�I�u�W�F�N�g���A���ڑ����l��<BR>
     * ���ׂĎ擾���z��ɂĕԋp����B<BR>
     * @@return String[]
     * @@roseuid 4187545400BE
     */
    public String[] attributeValues() 
    {
        final String STR_METHOD_NAME = " attributeValues()";
        
        log.entering(STR_METHOD_NAME);
        
        int l_intLengh = this.accOpenItemAttributeParamsList.size();
        
        String[] l_strAttributeValues = new String[l_intLengh];
        for (int i = 0; i < l_intLengh; i++)
        {
            l_strAttributeValues[i] = ((AccOpenItemAttributeRow)this.accOpenItemAttributeParamsList.get(i)).getAttributeValue();            
        }

        log.exiting(STR_METHOD_NAME);
        
        return l_strAttributeValues;
    }
    
    /**
     * (get�����l)<BR>
     * this.�����J�ݍ��ڑ���List�̊e�s�I�u�W�F�N�g���A<BR>
     * �i���ڑ����l == �����̍��ڑ����l�j�ɊY������s�̉����l��ԋp����B<BR>
     * <BR>
     * �i���ڑ����l == �����̍��ڑ����l�j�ɊY������s��2���ȏ゠��ꍇ�́A<BR>
     * �f�[�^�s�����Ɣ��f���B��O���X���[����B<BR>
     * @@param l_strItemAttributeValue - ���ڑ����l
     * @@return Double
     * @@roseuid 418755380264
     */
    public Double getRangeFrom(String l_strItemAttributeValue) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getRangeFrom(String)";
        
        log.entering(STR_METHOD_NAME);
        
        int l_intLengh = this.accOpenItemAttributeParamsList.size();
        
        Double l_rangeFrom = null;
        
        int l_intLineCnt = 0;
        
        AccOpenItemAttributeRow l_itemAttributeRow = null;
        
        for (int i = 0; i < l_intLengh; i++)
        {
            if (this.attributeValues()[i].equals(l_strItemAttributeValue))
            {
                l_itemAttributeRow = (AccOpenItemAttributeRow)this.accOpenItemAttributeParamsList.get(i);
                
                if (!l_itemAttributeRow.getRangeFromIsNull())
                {
                    l_rangeFrom = new Double(l_itemAttributeRow.getRangeFrom());
                }
                
                l_intLineCnt += 1;
            }
        }
        
        if (l_intLineCnt >= 2)
        {
            log.debug(STR_METHOD_NAME + "�f�[�^�s����");
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName()+ STR_METHOD_NAME,
                "�Y������s��2���ȏシ��");
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_rangeFrom;
    }
    
    /**
     * (get����l)<BR>
     * this.�����J�ݍ��ڑ���List�̊e�s�I�u�W�F�N�g���A<BR>
     * �i���ڑ����l == �����̍��ڑ����l�j�ɊY������s�̏���l��ԋp����B<BR>
     * <BR>
     * �i���ڑ����l == �����̍��ڑ����l�j�ɊY������s��2���ȏ゠��ꍇ�́A<BR>
     * �f�[�^�s�����Ɣ��f���B��O���X���[����B<BR>
     * @@param l_strItemAttributeValue - ���ڑ����l
     * @@return Double
     * @@roseuid 418755CE0003
     */
    public Double getRangeTo(String l_strItemAttributeValue) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getRangeTo(String)";
        
        log.entering(STR_METHOD_NAME);
        
        int l_intLengh = this.accOpenItemAttributeParamsList.size();
        
        Double l_rangeFrom = null;
        
        int l_intLineCnt = 0;

        AccOpenItemAttributeRow l_itemAttributeRow = null;
        
        for (int i = 0; i < l_intLengh; i++)
        {
            if (this.attributeValues()[i].equals(l_strItemAttributeValue))
            {
                l_itemAttributeRow = (AccOpenItemAttributeRow)this.accOpenItemAttributeParamsList.get(i);
                
                if (!l_itemAttributeRow.getRangeToIsNull())
                {
                    l_rangeFrom = new Double(l_itemAttributeRow.getRangeTo());
                }
                
                l_intLineCnt += 1;
            }
        }
        
        if (l_intLineCnt >= 2)
        {
            log.debug(STR_METHOD_NAME + "�f�[�^�s����");
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName()+ STR_METHOD_NAME,
                "�Y������s��2���ȏシ��");
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_rangeFrom;
    }
}
@
