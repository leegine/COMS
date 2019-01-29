head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.15.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioProductNameComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������Comparator(WEB3AioProductNameComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 ���z (���u) �V�K�쐬
*/

package webbroker3.aio.message;

import java.util.Comparator;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (������Comparator)<BR>
 * ������Comparator�N���X
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioProductNameComparator implements Comparator 
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioProductNameComparator.class);    
    
    /**
     * A�F���� <BR>
     * D�F�~��
     */
    private String orderBy;
    
    /**
     * @@roseuid 41B2954C0167
     */
    public WEB3AioProductNameComparator() 
    {
     
    }
    
    /**
     * (������Comparator)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �����̒l��this.orderBy�ɃZ�b�g����B
     * @@param orderBy - �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B
     * �@@A�F����  
     * �@@D�F�~��
     * @@roseuid 4163CCD40060
     */
    public WEB3AioProductNameComparator(String orderBy) 
    {
        if (orderBy == null || (!WEB3AscDescDef.ASC.equals(orderBy) && !WEB3AscDescDef.DESC.equals(orderBy)))
        {
            throw new IllegalArgumentException("�p�����[�^�̒l��'A�F����'�A'D�F�~��'�ȊO�ł�");            
        }
        
        this.orderBy = orderBy; 
    }
    
    /**
     * �icompare�̎����j <BR>
     * <BR>
     * �������̔�r���s���B  <BR>
     * <BR>
     * �P�j�@@������cast <BR>
     * �@@�����ۗ̕L�������ׂP�A�ۗL�������ׂQ��ۗL�������׌^��cast����B <BR>
     * <BR>
     * �Q�j�@@��r  <BR>
     * <BR>
     * �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j] <BR>
     * �@@�E�i�ۗL�������ׂP.������ <�ۗL�������ׂQ.�������j�̏ꍇ�A<BR>
     *         ���̐����i-1�j��ԋp����B <BR>
     * �@@�E�i�ۗL�������ׂP.������ = �ۗL�������ׂQ.�������j�̏ꍇ�A<BR>
     *          0��ԋp����B <BR>
     * �@@�E�i�ۗL�������ׂP.������ > �ۗL�������ׂQ.�������j�̏ꍇ�A<BR>
     *          ���̐����i1�j��ԋp����B <BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j] <BR>
     * �@@�E�i�ۗL�������ׂP.������ <�ۗL�������ׂQ.�������j�̏ꍇ�A<BR>
     *          ���̐����i1�j��ԋp����B <BR>
     * �@@�E�i�ۗL�������ׂP.������ = �ۗL�������ׂQ.�������j�̏ꍇ�A<BR>
     *           0��ԋp����B <BR>
     * �@@�E�i�ۗL�������ׂP.������ > �ۗL�������ׂQ.�������j�̏ꍇ�A<BR>
     *           ���̐����i-1�j��ԋp����B <BR>
     * @@param l_obj1 - �ۗL�������׃I�u�W�F�N�g
     * @@param l_obj2 - �ۗL�������׃I�u�W�F�N�g
     * @@return int
     * @@roseuid 4163CCD40062
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        String l_strMethodName = "compare(Object l_obj1, Object l_obj2)";
        log.entering(l_strMethodName);
        
        if (l_obj1 == null || l_obj2 == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        //�P�j�@@������cast 
        //�@@�����ۗ̕L�������ׂP�A�ۗL�������ׂQ��ۗL�������׌^��cast����B 
        WEB3AioSecurityTransferProductCodeNameUnit l_aioSecurityTransferUnit1;
        WEB3AioSecurityTransferProductCodeNameUnit l_aioSecurityTransferUnit2;
        
        if (l_obj1 instanceof WEB3AioSecurityTransferProductCodeNameUnit && 
            l_obj2 instanceof WEB3AioSecurityTransferProductCodeNameUnit)
        {
            l_aioSecurityTransferUnit1 = 
                (WEB3AioSecurityTransferProductCodeNameUnit)l_obj1;
            l_aioSecurityTransferUnit2 =
                (WEB3AioSecurityTransferProductCodeNameUnit)l_obj2;        
        }
        else
        {
            log.debug("�ۗL�������ׂ�a��،����׌^��cast����");
            throw new IllegalArgumentException(
                "�p�����[�^�̗ތ^���s���A�Y������'WEB3AioSecurityTransferProductCodeNameUnit'�ތ^");
        }

        //�Q�j�@@��r  
        //[�����w��̏ꍇ�ithis.orderBy == �h�����h�j]         
        int l_intCompare;
        if (WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            if (l_aioSecurityTransferUnit1.productName == null || l_aioSecurityTransferUnit2.productName == null)
            {
                if (l_aioSecurityTransferUnit1.productName == null && l_aioSecurityTransferUnit2.productName != null)
                {
                    l_intCompare = -1;     
                }
                else if (l_aioSecurityTransferUnit1.productName == null && l_aioSecurityTransferUnit2.productName == null)
                {
                    l_intCompare = 0;    
                }
                else 
                {
                    l_intCompare = 1;    
                }
            }
            else
            {
                //�i�ۗL�������ׂP.������ < �ۗL�������ׂQ.�������j�̏ꍇ�A���̐����i-1�j��ԋp����B 
                if (l_aioSecurityTransferUnit1.productName.compareTo(l_aioSecurityTransferUnit2.productName) < 0)
                {
                    l_intCompare = -1;    
                }
                //�i�ۗL�������ׂP.������ = �ۗL�������ׂQ.�������j�̏ꍇ�A0��ԋp����B 
                else if (l_aioSecurityTransferUnit1.productName.compareTo(l_aioSecurityTransferUnit2.productName) == 0)
                {
                    l_intCompare = 0;    
                }
                //�i�ۗL�������ׂP.������ > �ۗL�������ׂQ.�������j�̏ꍇ�A���̐����i1�j��ԋp����B 
                else
                {
                    l_intCompare = 1;    
                }
            }
        }
        //[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j] 
        else 
        {
            if (l_aioSecurityTransferUnit1.productName == null || l_aioSecurityTransferUnit2.productName == null)
            {
                if (l_aioSecurityTransferUnit1.productName == null && l_aioSecurityTransferUnit2.productName != null)
                {
                    l_intCompare = 1;     
                }
                else if (l_aioSecurityTransferUnit1.productName == null && l_aioSecurityTransferUnit2.productName == null)
                {
                    l_intCompare = 0;    
                }
                else 
                {
                    l_intCompare = -1;    
                }
            }
            else
            {
                //�i�ۗL�������ׂP.������ < �ۗL�������ׂQ.�������j�̏ꍇ�A���̐����i1�j��ԋp����B 
                if (l_aioSecurityTransferUnit1.productName.compareTo(l_aioSecurityTransferUnit2.productName) < 0)
                {
                    l_intCompare = 1;    
                }
                //�i�ۗL�������ׂP.������ = �ۗL�������ׂQ.�������j�̏ꍇ�A0��ԋp����B 
                else if (l_aioSecurityTransferUnit1.productName.compareTo(l_aioSecurityTransferUnit2.productName) == 0)
                {
                    l_intCompare = 0;    
                }
                //�i�ۗL�������ׂP.������ > �ۗL�������ׂQ.�������j�̏ꍇ�A���̐����i-1�j��ԋp����B
                else
                {
                    l_intCompare = -1;    
                }    
            } 
        }

        log.exiting(l_strMethodName);
        
        return l_intCompare;
    }
    
    /**
     * �p�����[�^�̃I�u�W�F�N�g�����̃R���p���[�^�Ɠ��������ǂ����𔻒肷��B <BR>
     * <BR>
     * �X�[�p�[�N���X��equals���R�[������B
     * @@param l_obj
     * @@return boolean
     * @@roseuid 4163CCD4006F
     */
    public boolean equals(Object l_obj) 
    {
        return super.equals(l_obj);
    }
}
@
