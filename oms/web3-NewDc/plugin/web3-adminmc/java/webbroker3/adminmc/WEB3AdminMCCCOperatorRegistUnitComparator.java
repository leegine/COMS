head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.31.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorRegistUnitComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name          : CC�I�y���[�^�o�^���Comparator(WEB3AdminMCCCOperatorRegistUnitComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/14 �͌d�� (���u) �V�K�쐬
*/

package webbroker3.adminmc;

import java.util.Comparator;
import java.util.Date;

import webbroker3.adminmc.define.WEB3AdminMCCCOperatorRegistUnitDef;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistUnit;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (CC�I�y���[�^�o�^���Comparator)<BR>
 * CC�I�y���[�^�o�^���Comparator<BR>
 * @@author �͌d�� <BR>
 * @@version 1.0 <BR>
 */
public class WEB3AdminMCCCOperatorRegistUnitComparator implements Comparator 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private  static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCCCOperatorRegistUnitComparator.class);    

    /**
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B <BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~�� <BR>
     * <BR>
     */
    private String orderBy;
    
    /**
     * (�L�[����)<BR>
     * �\�[�g�L�[�̃L�[����<BR>
     * <BR>
     * �ȉ��̂����ꂩ�B<BR>
     * <BR>
     *  CC�I�y���[�^�o�^���.���X�R�[�h <BR>
     *  CC�I�y���[�^�o�^���.�I�y���[�^�R�[�h <BR>
     *  CC�I�y���[�^�o�^���.��s�����敪<BR>
     *  CC�I�y���[�^�o�^���.�����R�[�h<BR>
     *  CC�I�y���[�^�o�^���.�G���[��<BR>
     *  CC�I�y���[�^�o�^���.�X�V����<BR>
     *  CC�I�y���[�^�o�^���.�X�V�҃R�[�h<BR>
     * <BR>
     */
    private String keyItem;
    
    /**
     * @@roseuid 41BE403A004E
     */
    public WEB3AdminMCCCOperatorRegistUnitComparator() 
    {
     
    }
    
    /**
     * (CC�I�y���[�^�o�^���Comparator)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �����̒l��this.orderBy�C�L�[���ڂɃZ�b�g����B <BR>
     * <BR>
     * @@param l_strOrderBy - (orderby)<BR>
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B <BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~�� <BR>
     * <BR>
     * @@param l_strKeyItem - (�L�[����)<BR>
     * �\�[�g�L�[�̃L�[����<BR>
     * <BR>
     * �ȉ��̂����ꂩ�B<BR>
     * <BR>
     *  CC�I�y���[�^�o�^���.���X�R�[�h <BR>
     *  CC�I�y���[�^�o�^���.�I�y���[�^�R�[�h <BR>
     *  CC�I�y���[�^�o�^���.��s�����敪<BR>
     *  CC�I�y���[�^�o�^���.�����R�[�h<BR>
     *  CC�I�y���[�^�o�^���.�G���[��<BR>
     *  CC�I�y���[�^�o�^���.�X�V����<BR>
     *  CC�I�y���[�^�o�^���.�X�V�҃R�[�h<BR>
     * <BR>
     * @@return webbroker3.adminmc.WEB3AdminMCCCOperatorRegistUnitComparator
     * @@roseuid 41B68EAF02CD
     */
    public WEB3AdminMCCCOperatorRegistUnitComparator(String l_strOrderBy, String l_strKeyItem) 
    {
        final  String  STR_METHOD_NAME = " WEB3AdminMCCCOperatorRegistUnitComparator(String l_strOrderBy, String l_strKeyItem)";
        log.entering(STR_METHOD_NAME);
        
        // �����̒l��this.orderBy�C�L�[���ڂɃZ�b�g����B 
        if(l_strOrderBy == null || (!WEB3AscDescDef.ASC.equals(l_strOrderBy) && !WEB3AscDescDef.DESC.equals(l_strOrderBy)))
        {           
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException("�p�����[�^�̒l��'A�F����'�A'D�F�~��'�ȊO�ł�");            
        }
        else if (l_strKeyItem == null ||
            (!WEB3AdminMCCCOperatorRegistUnitDef.BRANCH_CODE.equals(l_strKeyItem)&&
             !WEB3AdminMCCCOperatorRegistUnitDef.OPERATOR_CODE.equals(l_strKeyItem)&&
             !WEB3AdminMCCCOperatorRegistUnitDef.ACCOUNT_ORDER_DIV.equals(l_strKeyItem)&&
             !WEB3AdminMCCCOperatorRegistUnitDef.DEPARTMENT_CODE.equals(l_strKeyItem)&&
             !WEB3AdminMCCCOperatorRegistUnitDef.ERROR_COUNT.equals(l_strKeyItem)&&
             !WEB3AdminMCCCOperatorRegistUnitDef.UPDATE_TIME_STAMP.equals(l_strKeyItem)&&
             !WEB3AdminMCCCOperatorRegistUnitDef.UPDATER_CODE.equals(l_strKeyItem)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException("�p�����[�^�̒l������`�̒l�ł���.");                         
        }
               
        this.orderBy = l_strOrderBy; 
        this.keyItem = l_strKeyItem;  
        
        log.exiting(STR_METHOD_NAME);  
         
    }
    
    /**
     * CC�I�y���[�^�o�^��񍀖ڒl�̔�r���s���B<BR>
     * <BR>
     * �P�j�@@������cast <BR>
     * �@@������CC�I�y���[�^�o�^���P�ACC�I�y���[�^�o�^���Q��CC�I�y���[�^�o�^���^��cast����B <BR>
     * <BR>
     * �Q�j�@@���ڒl�擾<BR>
     * �@@this.�L�[���ڂ��������ڒl���ACC�I�y���[�^�o�^���P�ACC�I�y���[�^�o�^���Q��肻�ꂼ��擾����B<BR>
     * <BR>
     * �R�j�@@���ڒl��r<BR>
     * �@@�Q�j�Ŏ擾�������ڒl�P�A���ڒl�Q�ɂ��� <BR>
     * <BR>
     * �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j] <BR>
     * �@@�E�i���ڒl�P < ���ڒl�Q�j�̏ꍇ�A���̐����i-1�j��ԋp����B <BR>
     * �@@�E�i���ڒl�P = ���ڒl�Q�j�̏ꍇ�A0��ԋp����B <BR>
     * �@@�E�i���ڒl�P > ���ڒl�Q�j�̏ꍇ�A���̐����i1�j��ԋp����B <BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j] <BR>
     * �@@�E�i���ڒl�P < ���ڒl�Q�j�̏ꍇ�A���̐����i1�j��ԋp����B <BR>
     * �@@�E�i���ڒl�P = ���ڒl�Q�j�̏ꍇ�A0��ԋp����B <BR>
     * �@@�E�i���ڒl�P > ���ڒl�Q�j�̏ꍇ�A���̐����i-1�j��ԋp����B <BR>
     * <BR>
     * @@param l_ccOperatorRegistUnit1 - (CC�I�y���[�^�o�^���P)<BR>
     * CC�I�y���[�^�o�^���P<BR>
     * @@param l_ccOperatorRegistUnit2 - (CC�I�y���[�^�o�^���Q)<BR>
     * CC�I�y���[�^�o�^���Q<BR>
     * @@return int
     * @@roseuid 41B68EAF02C8
     */
    public int compare(Object l_ccOperatorRegistUnit1, Object l_ccOperatorRegistUnit2) 
    {
        final  String  STR_METHOD_NAME = " compare(Object l_ccOperatorRegistUnit1, Object l_ccOperatorRegistUnit2) ";
        log.entering(STR_METHOD_NAME);
        
        Object l_item1 = null;
        Object l_item2 = null;
        
        // �P�j�@@������cast 
        // �@@������CC�I�y���[�^�o�^���P�ACC�I�y���[�^�o�^���Q��CC�I�y���[�^�o�^���^��cast����B
        if (l_ccOperatorRegistUnit1 == null || l_ccOperatorRegistUnit2 == null)
        {
            log.debug("input parameters are null. ");
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        else if ((l_ccOperatorRegistUnit1 instanceof WEB3AdminMCCCOperatorRegistUnit)&&(l_ccOperatorRegistUnit2 instanceof WEB3AdminMCCCOperatorRegistUnit))
        {
            WEB3AdminMCCCOperatorRegistUnit l_unit1 = (WEB3AdminMCCCOperatorRegistUnit)l_ccOperatorRegistUnit1;
            WEB3AdminMCCCOperatorRegistUnit l_unit2 = (WEB3AdminMCCCOperatorRegistUnit)l_ccOperatorRegistUnit2;
            
            // �Q�j�@@���ڒl�擾
            // �@@this.�L�[���ڂ��������ڒl���ACC�I�y���[�^�o�^���P�ACC�I�y���[�^�o�^���Q��肻�ꂼ��擾����B
            if (WEB3AdminMCCCOperatorRegistUnitDef.BRANCH_CODE.equals(this.keyItem))
            {
                l_item1 = l_unit1.branchCode;
                l_item2 = l_unit2.branchCode;    
            }
            else if (WEB3AdminMCCCOperatorRegistUnitDef.OPERATOR_CODE.equals(this.keyItem))
            {
                l_item1 = l_unit1.operatorCode;
                l_item2 = l_unit2.operatorCode;                    
            }
            else if (WEB3AdminMCCCOperatorRegistUnitDef.ACCOUNT_ORDER_DIV.equals(this.keyItem))
            {
                l_item1 = l_unit1.accountOrderDiv;
                l_item2 = l_unit2.accountOrderDiv;                
            }
            else if (WEB3AdminMCCCOperatorRegistUnitDef.DEPARTMENT_CODE.equals(this.keyItem))
            {
                l_item1 = l_unit1.departmentCode;
                l_item2 = l_unit2.departmentCode;                                
            }
            else if (WEB3AdminMCCCOperatorRegistUnitDef.ERROR_COUNT.equals(this.keyItem))
            {
                if (l_unit1.errorCount == null)
                {
                    l_item1 = new Integer(0);
                }
                else
                {
                    l_item1 = new Integer(l_unit1.errorCount);
                }
                if (l_unit2.errorCount == null)
                {
                    l_item2 = new Integer(0);
                }
                else
                {
                    l_item2 = new Integer(l_unit2.errorCount);
                }
            }
            else if (WEB3AdminMCCCOperatorRegistUnitDef.UPDATE_TIME_STAMP.equals(this.keyItem))
            {
                l_item1 = l_unit1.updateTimeStamp;
                l_item2 = l_unit2.updateTimeStamp;
            }
            else if (WEB3AdminMCCCOperatorRegistUnitDef.UPDATER_CODE.equals(this.keyItem))
            {
                l_item1 = l_unit1.updaterCode;
                l_item2 = l_unit2.updaterCode;                
            }            
            //WEB3DateUtility
        }
        else
        {
            log.debug("parameter type is wrong. ");
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,STR_METHOD_NAME);    
        }

        int l_intResult;
        
        if (l_item1 == null || l_item2 == null)
        {
               
            if (l_item1 == null && l_item2 == null)
            {
                l_intResult = 0;
            }
            else if (l_item1 == null)
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
            }
            else
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
            }            
                        
        } 
        else
        {
            if (compareObj(l_item1, l_item2) < 0)
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
            }
            else if (compareObj(l_item1, l_item2) > 0)
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
            }
            else
            {
                l_intResult = 0;
            }
                
        }
        return l_intResult;             
    }
    
    /**
     * �iequals�̎����j <BR>
     * <BR>
     * ���g�p�B <BR>
     * false��ԋp����B <BR>
     * <BR>
     * @@param l_obj - (arg0)<BR>
     * @@return boolean
     * @@roseuid 41B68EAF02CB
     */
    public boolean equals(Object l_obj) 
    {
        final  String  STR_METHOD_NAME = " equals(Object l_obj) ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return false;
    }
    
    /**
     * ���Object�̔�r���s���B
     *
     * @@param l_obj1
     * @@param l_obj2
     * @@return 
     */
    private int compareObj(Object l_obj1, Object l_obj2)
    {        
        int l_intResult;
        
        if ((l_obj1 instanceof String) && (l_obj2 instanceof String))
        {
            l_intResult = ((String)l_obj1).compareTo((String)l_obj2);
        }
        else if ((l_obj1 instanceof Date) && (l_obj2 instanceof Date))
        {
            l_intResult = WEB3DateUtility.compareToSecond((Date)l_obj1, (Date)l_obj2);
        }
        else if ((l_obj1 instanceof Integer) && (l_obj2 instanceof Integer))
        {
            l_intResult = ((Integer)l_obj1).compareTo((Integer)l_obj2);
        }
        else
        {
            throw new IllegalArgumentException("[Error]Parameter type is wrong! [l_obj1]=" + l_obj1 + " [l_obj2]" + l_obj2);
        }  
        
        return l_intResult;      
    }
}
@
