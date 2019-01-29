head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.31.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminRegistUnitComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ғo�^���Comparator(WEB3AdminMCAdminRegistUnitComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/14  �� �� �@@ (���u) �V�K�쐬
*/
package webbroker3.adminmc;


import java.util.Comparator;
import java.util.Date;

import webbroker3.adminmc.define.WEB3AdminMCAdminRegistUnitDef;
import webbroker3.adminmc.message.WEB3AdminMCAdminRegistUnit;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ǘ��ғo�^���Comparator)<BR>
 * �Ǘ��ғo�^���Comparator<BR>
 */
public class WEB3AdminMCAdminRegistUnitComparator implements Comparator 
{
    /**
     *���O�o�̓��[�e�B���e�B�B
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminRegistUnitComparator.class);
     
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
     * �@@�Ǘ��ғo�^���.���X�R�[�h <BR>
     * �@@�Ǘ��ғo�^���.�Ǘ��҃R�[�h <BR>
     * �@@�Ǘ��ғo�^���.���[���A�h���X <BR>
     * �@@�Ǘ��ғo�^���.�������x���R�[�h<BR>
     * �@@�Ǘ��ғo�^���.�G���[��<BR>
     * �@@�Ǘ��ғo�^���.�X�V����<BR>
     * �@@�Ǘ��ғo�^���.�X�V�҃R�[�h<BR>
     * <BR>
     */
    private String keyItem;
    
    /**
     * @@roseuid 41BE403A0119
     */
    public WEB3AdminMCAdminRegistUnitComparator() 
    {
     
    }
    
    /**
     * (�Ǘ��ғo�^���Comparator)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �����̒l��this.orderBy�C�L�[���ڂɃZ�b�g����B <BR>
     * <BR>
     * @@param l_strOrderBy - (orderBy)<BR>
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B<BR> 
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~�� <BR>
     * <BR>
     * @@param l_strKeyItem - (�L�[����)<BR>
     * �\�[�g�L�[�̃L�[����<BR>
     * <BR>
     * �ȉ��̂����ꂩ�B<BR>
     * <BR>
     * �@@�Ǘ��ғo�^���.���X�R�[�h <BR>
     * �@@�Ǘ��ғo�^���.�Ǘ��҃R�[�h <BR>
     * �@@�Ǘ��ғo�^���.���[���A�h���X <BR>
     * �@@�Ǘ��ғo�^���.�������x���R�[�h<BR>
     * �@@�Ǘ��ғo�^���.�G���[��<BR>
     * �@@�Ǘ��ғo�^���.�X�V����<BR>
     * �@@�Ǘ��ғo�^���.�X�V�҃R�[�h<BR>
     * <BR>
     * @@return webbroker3.adminmc.WEB3AdminMCAdminRegistUnitComparator
     * @@roseuid 41B6879C00B5
     */
    public WEB3AdminMCAdminRegistUnitComparator(String l_strOrderBy, String l_strKeyItem) 
    {
        final String STR_METHOD_NAME = " WEB3AdminMCAdminRegistUnitComparator(String l_strOrderBy, String l_strKeyItem) ";
        log.entering(STR_METHOD_NAME );
        if (l_strOrderBy == null || (!WEB3AscDescDef.ASC.equals(l_strOrderBy) && !WEB3AscDescDef.DESC.equals(l_strOrderBy)))
        {
            String l_strErrorInfo = "�p�����[�^�̒l��'A�F����'�A'D�F�~��'�ȊO�ł��B";
            log.error(STR_METHOD_NAME + l_strErrorInfo);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_strErrorInfo);

        }

        if (l_strKeyItem == null || 
            (!(WEB3AdminMCAdminRegistUnitDef.BRANCHCODE.equals(l_strKeyItem)) &&
             !(WEB3AdminMCAdminRegistUnitDef.ADMINISTRATORCODE.equals(l_strKeyItem)) &&
             !(WEB3AdminMCAdminRegistUnitDef.MAILADDRESS.equals(l_strKeyItem)) &&
             !(WEB3AdminMCAdminRegistUnitDef.PERMISSIONLEVEL.equals(l_strKeyItem)) &&
             !(WEB3AdminMCAdminRegistUnitDef.ERRORCOUNT.equals(l_strKeyItem)) &&
             !(WEB3AdminMCAdminRegistUnitDef.UPDATETIMESTAMP.equals(l_strKeyItem)) &&
             !(WEB3AdminMCAdminRegistUnitDef.UPDATERCODE.equals(l_strKeyItem))))
        {
            String l_strErrorInfo = "�\�[�g�L�[.�L�[���ڂ̒l���Ǘ��ғo�^���ȊO�̏ꍇ�̃G���[.";
            log.error(STR_METHOD_NAME + l_strErrorInfo);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_strErrorInfo);

        }
  
        this.orderBy = l_strOrderBy;
        this.keyItem = l_strKeyItem;
        
        log.exiting(STR_METHOD_NAME);
    	
     
    }
    
    /**
     * �Ǘ��ғo�^��񍀖ڒl�̔�r���s���B<BR>
     * <BR>
     * �P�j�@@������cast <BR>
     * �@@�����̊Ǘ��ғo�^���P�A�Ǘ��ғo�^���Q���Ǘ��ғo�^���^��cast����B <BR>
     * <BR>
     * �Q�j�@@���ڒl�擾<BR>
     * �@@this.�L�[���ڂ��������ڒl���A�Ǘ��ғo�^���P�A�Ǘ��ғo�^���Q��肻�ꂼ��擾����B<BR>
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
     * @@param l_adminRegistUnit1 - (�Ǘ��ғo�^���P)<BR>
     * �Ǘ��ғo�^���P<BR>
     * @@param l_adminRegistUnit2 - (�Ǘ��ғo�^���Q)<BR>
     * �Ǘ��ғo�^���Q<BR>
     * @@return int
     * @@roseuid 41B6879501DE
     */
    public int compare(Object l_adminRegistUnit1, Object l_adminRegistUnit2) 
    {
        final String STR_METHOD_NAME = " compare(Object l_adminRegistUnit1, Object l_adminRegistUnit2) ";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminMCAdminRegistUnit l_unit1 = null;
        WEB3AdminMCAdminRegistUnit l_unit2 = null;
        
        if (l_adminRegistUnit1 == null || l_adminRegistUnit2 == null)
        {
            log.debug("input parameters are null. ");
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        else if ((l_adminRegistUnit1 instanceof WEB3AdminMCAdminRegistUnit) && (l_adminRegistUnit2 instanceof WEB3AdminMCAdminRegistUnit))
        {
            l_unit1 = (WEB3AdminMCAdminRegistUnit)l_adminRegistUnit1;
            l_unit2 = (WEB3AdminMCAdminRegistUnit)l_adminRegistUnit2;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException(
                "�p�����[�^�̗ތ^���s���A�Y������'WEB3AdminMCAdminRegistUnit'"); 
        }
        
        Object l_keyItem1 = null;
        Object l_keyItem2 = null;        
        
        if (WEB3AdminMCAdminRegistUnitDef.BRANCHCODE.equals(this.keyItem))
        {
            l_keyItem1 = l_unit1.branchCode;
            l_keyItem2 = l_unit2.branchCode;
        }
        else if (WEB3AdminMCAdminRegistUnitDef.ADMINISTRATORCODE.equals(this.keyItem))
        {
            l_keyItem1 = l_unit1.administratorCode;
            l_keyItem2 = l_unit2.administratorCode;
        }
        else if (WEB3AdminMCAdminRegistUnitDef.MAILADDRESS.equals(this.keyItem))
        {
            l_keyItem1 = l_unit1.mailAddress;
            l_keyItem2 = l_unit2.mailAddress;
        }
        else if (WEB3AdminMCAdminRegistUnitDef.PERMISSIONLEVEL.equals(this.keyItem))
        {
            l_keyItem1 = new Integer(l_unit1.permissionLevel);
            l_keyItem2 = new Integer(l_unit2.permissionLevel);
        }
        else if (WEB3AdminMCAdminRegistUnitDef.ERRORCOUNT.equals(this.keyItem))
        {
            if (l_unit1.errorCount == null)
            {
                l_keyItem1 = new Integer(0);
            }
            else
            {
                l_keyItem1 = new Integer(l_unit1.errorCount);
            }
            if (l_unit2.errorCount == null)
            {
                l_keyItem2 = new Integer(0);
            }
            else
            {
                l_keyItem2 = new Integer(l_unit2.errorCount);
            }
        }
        else if (WEB3AdminMCAdminRegistUnitDef.UPDATETIMESTAMP.equals(this.keyItem))
        {
            l_keyItem1 = l_unit1.updateTimeStamp;
            l_keyItem2 = l_unit2.updateTimeStamp;
        }
        else if (WEB3AdminMCAdminRegistUnitDef.UPDATERCODE.equals(this.keyItem))
        {
            l_keyItem1 = l_unit1.updaterCode;
            l_keyItem2 = l_unit2.updaterCode;
        }
        
        int l_intResult;
        
        if (l_keyItem1 == null || l_keyItem2 == null)
        {
               
            if (l_keyItem1 == null && l_keyItem2 == null)
            {
                l_intResult = 0;
            }
            else if (l_keyItem1 == null)
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
            if (compareObj(l_keyItem1, l_keyItem2) < 0)
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
            }
            else if (compareObj(l_keyItem1, l_keyItem2) > 0)
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
     * @@l_intResult boolean
     * @@roseuid 41B6879501E1
     */
    public boolean equals(Object l_obj) 
    {
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
