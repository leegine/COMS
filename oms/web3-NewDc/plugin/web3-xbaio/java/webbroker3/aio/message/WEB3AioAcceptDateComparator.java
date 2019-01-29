head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.17.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioAcceptDateComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��t����Comparator�N���X(WEB3AioAcceptDateComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/14 ���E (���u) �V�K�쐬   
                   2004/10/25 ���z (���u) ���r���[                
*/
package webbroker3.aio.message;

import java.util.Comparator;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (��t����Comparator)<BR>
 * (��t����Comparator�N���X)
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AioAcceptDateComparator implements Comparator 
{
    
    /**
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B <BR>
     * <BR>
     * �@@A�F����  <BR>
     * �@@D�F�~�� <BR>
     */
    private String orderBy;
    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioAcceptDateComparator.class);
    
    /**
     * �icompare�̎����j <BR>
     * <BR>
     * ��t�����̔�r���s���B  <BR>
     * <BR>
     * �P�j�@@������cast <BR>
     * �@@�����̓��o�����ׂP�A���o�����ׂQ����o�����׌^��cast����B <BR>
     * <BR>
     * �Q�j�@@��r  <BR>
     * <BR>
     * �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j] <BR>
     * �@@�E�i���o�����ׂP.��t���� < ���o�����ׂQ.��t�����j��<BR>
     * �ꍇ�A���̐����i-1�j��ԋp����B <BR>
     * �@@�E�i���o�����ׂP.��t���� = ���o�����ׂQ.��t�����j��<BR>
     * �ꍇ�A0��ԋp����B <BR>
     * �@@�E�i���o�����ׂP.��t���� > ���o�����ׂQ.��t�����j��<BR>
     * �ꍇ�A���̐����i1�j��ԋp����B <BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j] <BR>
     * �@@�E�i���o�����ׂP.��t���� < ���o�����ׂQ.��t�����j��<BR>
     * �ꍇ�A���̐����i1�j��ԋp����B <BR>
     * �@@�E�i���o�����ׂP.��t���� = ���o�����ׂQ.��t�����j��<BR>
     * �ꍇ�A0��ԋp����B <BR>
     * �@@�E�i���o�����ׂP.��t���� > ���o�����ׂQ.��t�����j��<BR>
     * �ꍇ�A���̐����i-1�j��ԋp����B <BR>
     * <BR>
     * @@param l_cashTransferDetails01 - (���o�����׃I�u�W�F�N�g�P)
     * @@param l_cashTransferDetails02 - (���o�����׃I�u�W�F�N�g�Q)
     * @@return int
     * @@roseuid 410711C903C6
     */
    public int compare(Object l_cashTransferDetails01, Object l_cashTransferDetails02) 
    {
        final String STR_METHOD_NAME =
            "compare(Object l_cashTransferDetails01, Object l_cashTransferDetails02)";
        log.entering(STR_METHOD_NAME);
        
        if (l_cashTransferDetails01 == null || l_cashTransferDetails02 == null)
        {
            log.debug("l_cashTransferDetails01 or l_cashTransferDetails02 is null ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j�@@������cast 
        //�@@�����̓��o�����ׂP�A���o�����ׂQ����o�����׌^��cast����B
        WEB3AioCashTransUnit l_aioCashTransUnit01 = (WEB3AioCashTransUnit)l_cashTransferDetails01;
        WEB3AioCashTransUnit l_aioCashTransUnit02 = (WEB3AioCashTransUnit)l_cashTransferDetails02;
        
        //�Q�j�@@��r 
        //�@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j] 

        if(WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            if (l_aioCashTransUnit01.receptionDate == null || l_aioCashTransUnit02.receptionDate == null)
            {
                if (l_aioCashTransUnit01.receptionDate == null && l_aioCashTransUnit02.receptionDate != null)
                {
                    log.exiting(STR_METHOD_NAME);
                    return -1;
                }
                else if (l_aioCashTransUnit01.receptionDate == null && l_aioCashTransUnit02.receptionDate == null)
                {
                    log.exiting(STR_METHOD_NAME);
                    return 0;
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return 1;
                }
            }
            else
            {
                //�i���o�����ׂP.��t���� < ���o�����ׂQ.��t�����j�̏ꍇ�A���̐����i-1�j��ԋp����B
                if(WEB3DateUtility.compareToSecond(l_aioCashTransUnit01.receptionDate, l_aioCashTransUnit02.receptionDate) < 0)
                {
                    log.exiting(STR_METHOD_NAME);
                    return -1;
                }
                //�i���o�����ׂP.��t���� = ���o�����ׂQ.��t�����j�̏ꍇ�A0��ԋp����B
                else if(WEB3DateUtility.compareToSecond(l_aioCashTransUnit01.receptionDate, l_aioCashTransUnit02.receptionDate) == 0)
                {
                    log.exiting(STR_METHOD_NAME);
                    return 0;
                }
                //�i���o�����ׂP.��t���� > ���o�����ׂQ.��t�����j�̏ꍇ�A���̐����i1�j��ԋp����B
                else 
                {
                    log.exiting(STR_METHOD_NAME);
                    return 1;
                }
            }            
        }
        //�@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j]
        else if(WEB3AscDescDef.DESC.equals(this.orderBy))
        {
            if (l_aioCashTransUnit01.receptionDate == null || l_aioCashTransUnit02.receptionDate == null)
            {
                if (l_aioCashTransUnit01.receptionDate == null && l_aioCashTransUnit02.receptionDate != null)
                {
                    log.exiting(STR_METHOD_NAME);
                    return 1;
                }
                else if (l_aioCashTransUnit01.receptionDate == null && l_aioCashTransUnit02.receptionDate == null)
                {
                    log.exiting(STR_METHOD_NAME);
                    return 0;
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return -1;
                }
            }
            else
            {
                //�i���o�����ׂP.��t���� < ���o�����ׂQ.��t�����j�̏ꍇ�A���̐����i1�j��ԋp����B
                if(WEB3DateUtility.compareToSecond(l_aioCashTransUnit01.receptionDate, l_aioCashTransUnit02.receptionDate) < 0)
                {
                    log.exiting(STR_METHOD_NAME);
                    return 1;
                }
                //�i���o�����ׂP.��t���� = ���o�����ׂQ.��t�����j�̏ꍇ�A0��ԋp����B
                else if(WEB3DateUtility.compareToSecond(l_aioCashTransUnit01.receptionDate, l_aioCashTransUnit02.receptionDate) == 0)
                {
                    log.exiting(STR_METHOD_NAME);
                    return 0;
                }
                //�i���o�����ׂP.��t���� > ���o�����ׂQ.��t�����j�̏ꍇ�A���̐����i-1�j��ԋp����B
                else 
                {
                    log.exiting(STR_METHOD_NAME);
                    return -1;
                }
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return 0;
    }
    
    /**
     * �iequals�̎����j <BR>
     * <BR>
     * ���g�p�B <BR>
     * false��ԋp����B <BR>
     * @@param l_arg0
     * @@return boolean
     * @@roseuid 410711D1026E
     */
    public boolean equals(Object l_arg0) 
    {
        return super.equals(l_arg0);
    }
    
    /**
     * (��t����Comparator)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �����̒l��this.orderBy�ɃZ�b�g����B <BR>
     * @@param l_strOrderBy - �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B <BR>
     * <BR>
     * �@@A�F����  <BR>
     * �@@D�F�~�� <BR>
     * @@return webbroker3.aio.message.WEB3AioAcceptDateComparator
     * @@roseuid 410711D9025E
     */
    public WEB3AioAcceptDateComparator(String l_strOrderBy) 
    {
        final String STR_METHOD_NAME =
            "WEB3AioAcceptDateComparator(String l_strOrderBy)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strOrderBy == null || (!WEB3AscDescDef.ASC.equals(l_strOrderBy) && !WEB3AscDescDef.DESC.equals(l_strOrderBy)))
        {
            throw new IllegalArgumentException("�p�����[�^�̒l��'A�F����'�A'D�F�~��'�ȊO�ł�");            
        }
        
        this.orderBy = l_strOrderBy;
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
