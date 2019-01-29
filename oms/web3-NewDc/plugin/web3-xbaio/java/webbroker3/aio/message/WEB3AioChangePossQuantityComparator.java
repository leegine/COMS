head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.54.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioChangePossQuantityComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����Comparator(WEB3AioChangePossQuantityComparator)
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
 * (����Comparator)<BR>
 * ����Comparator�N���X
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioChangePossQuantityComparator implements Comparator 
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioChangePossQuantityComparator.class);    
    
    /**
     * A�F���� <BR>
     * D�F�~��
     */
    private String orderBy;
    
    /**
     * @@roseuid 41B2954C0271
     */
    public WEB3AioChangePossQuantityComparator() 
    {
     
    }
    
    /**
     * (����Comparator)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �����̒l��this.orderBy�ɃZ�b�g����B
     * @@param orderBy - �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B 
     * �@@A�F����  
     * �@@D�F�~��
     * @@roseuid 416283AC03D5
     */
    public WEB3AioChangePossQuantityComparator(String orderBy) 
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
     * ���ʂ̔�r���s���B  <BR>
     * <BR>
     * �P�j�@@������cast <BR>
     * �@@�����̗a��،����ׂP�A�a��،����ׂQ��a��،����׌^��cast����B <BR>
     * <BR>
     * �Q�j�@@��r  <BR>
     * <BR>
     * �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j] <BR>
     * �@@�E�i�a��،����ׂP.���� <�a��،����ׂQ.���ʁj�̏ꍇ�A���̐����i-1�j��ԋp����B <BR>
     * �@@�E�i�a��،����ׂP.���� = �a��،����ׂQ.���ʁj�̏ꍇ�A0��ԋp����B <BR>
     * �@@�E�i�a��،����ׂP.���� > �a��،����ׂQ.���ʁj�̏ꍇ�A���̐����i1�j��ԋp����B <BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j] <BR>
     * �@@�E�i�a��،����ׂP.���� <�a��،����ׂQ.���ʁj�̏ꍇ�A���̐����i1�j��ԋp����B <BR>
     * �@@�E�i�a��،����ׂP.���� = �a��،����ׂQ.���ʁj�̏ꍇ�A0��ԋp����B <BR>
     * �@@�E�i�a��،����ׂP.���� > �a��،����ׂQ.���ʁj�̏ꍇ�A���̐����i-1�j��ԋp����B
     * @@param l_obj1 - �a��،����׃I�u�W�F�N�g
     * @@param l_obj2 - �a��،����׃I�u�W�F�N�g
     * @@return int
     * @@roseuid 41627A470369
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
        //�@@�����̗a��،����ׂP�A�a��،����ׂQ��a��،����׌^��cast����B 
        WEB3AioSecurityTransferUnit l_aioSecurityTransferUnit1;
        WEB3AioSecurityTransferUnit l_aioSecurityTransferUnit2;
        
        if (l_obj1 instanceof WEB3AioSecurityTransferUnit && 
            l_obj2 instanceof WEB3AioSecurityTransferUnit)
        {
            l_aioSecurityTransferUnit1 = 
                (WEB3AioSecurityTransferUnit)l_obj1;
            l_aioSecurityTransferUnit2 =
                (WEB3AioSecurityTransferUnit)l_obj2;        
        }
        else
        {
            log.debug("�a��،����ׂP��a��،����׌^��cast����");
            throw new IllegalArgumentException(
                "�p�����[�^�̗ތ^���s���A�Y������'WEB3AioSecurityTransferUnit'�ތ^");
        }

        //�Q�j�@@��r
        
        //[�����w��̏ꍇ�ithis.orderBy == �h�����h�j]         
        int l_intCompare;
        if (WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            if (l_aioSecurityTransferUnit1.changePossQuantity == null || l_aioSecurityTransferUnit2.changePossQuantity == null)
            {
                if (l_aioSecurityTransferUnit1.changePossQuantity == null && l_aioSecurityTransferUnit2.changePossQuantity != null)
                {
                    l_intCompare = -1;     
                }
                else if (l_aioSecurityTransferUnit1.changePossQuantity == null && l_aioSecurityTransferUnit2.changePossQuantity == null)
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
                long l_lngPossQuantity1 = Long.parseLong(l_aioSecurityTransferUnit1.changePossQuantity);
                long l_lngPossQuantity2 = Long.parseLong(l_aioSecurityTransferUnit2.changePossQuantity);
                
                //�i�a��،����ׂP.���� < �a��،����ׂQ.���ʁj�̏ꍇ�A���̐����i-1�j��ԋp����B 
                if (l_lngPossQuantity1 < l_lngPossQuantity2)
                {
                    l_intCompare = -1;    
                }
                //�i�a��،����ׂP.���� = �a��،����ׂQ.���ʁj�̏ꍇ�A0��ԋp����B 
                else if (l_lngPossQuantity1 == l_lngPossQuantity2)
                {
                    l_intCompare = 0;    
                }
                //�i�a��،����ׂP.���� > �a��،����ׂQ.���ʁj�̏ꍇ�A���̐����i1�j��ԋp����B 
                else
                {
                    l_intCompare = 1;    
                }
            }
        }
        //[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j] 
        else 
        {
            if (l_aioSecurityTransferUnit1.changePossQuantity == null || l_aioSecurityTransferUnit2.changePossQuantity == null)
            {
                if (l_aioSecurityTransferUnit1.changePossQuantity == null && l_aioSecurityTransferUnit2.changePossQuantity != null)
                {
                    l_intCompare = 1;     
                }
                else if (l_aioSecurityTransferUnit1.changePossQuantity == null && l_aioSecurityTransferUnit2.changePossQuantity == null)
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
                long l_lngPossQuantity1 = Long.parseLong(l_aioSecurityTransferUnit1.changePossQuantity);
                long l_lngPossQuantity2 = Long.parseLong(l_aioSecurityTransferUnit2.changePossQuantity);
                
                //�i�a��،����ׂP.���� < �a��،����ׂQ.���ʁj�̏ꍇ�A���̐����i1�j��ԋp����B 
                if (l_lngPossQuantity1 < l_lngPossQuantity2)
                {
                    l_intCompare = 1;    
                }
                //�i�a��،����ׂP.���� = �a��،����ׂQ.���ʁj�̏ꍇ�A0��ԋp����B 
                else if (l_lngPossQuantity1 == l_lngPossQuantity2)
                {
                    l_intCompare = 0;    
                }
                //�i�a��،����ׂP.���� > �a��،����ׂQ.���ʁj�̏ꍇ�A���̐����i-1�j��ԋp����B
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
     * �p�����[�^�̃I�u�W�F�N�g�����̃R���p���[�^�Ɠ��������ǂ����𔻒肷��B<BR> 
     * <BR>
     * �X�[�p�[�N���X��equals���R�[������B
     * @@param l_obj
     * @@return boolean
     * @@roseuid 41627A470388
     */
    public boolean equals(Object l_obj) 
    {
        boolean l_boo = super.equals(l_obj);    
        return l_boo;
    }
}
@
