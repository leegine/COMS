head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.18.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioDealComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Comparator�N���X(WEB3AioDealComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/14 ���E (���u) �V�K�쐬    
                   2004/10/25 ���z (���u) ���r���[               
*/
package webbroker3.aio.message;

import java.util.Comparator;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (���Comparator)<BR>
 * (���Comparator�N���X)
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AioDealComparator implements Comparator 
{
    
    /**
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B <BR>
     * <BR>
     * �@@A�F����  <BR>
     * �@@D�F�~�� <BR>
     */
    private String orderBy;
    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioDealComparator.class);

    /**
     * �icompare�̎����j <BR>
     * <BR>
     * ����̔�r���s���B  <BR>
     * <BR>
     * �P�j�@@������cast <BR>
     * �@@�����̓��o�����ׂP�A���o�����ׂQ����o�����׌^��cast����B <BR>
     * <BR>
     * �Q�j�@@��r  <BR>
     * <BR>
     * �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j] <BR>
     * �@@�E�i���o�����ׂP.��� < ���o�����ׂQ.����j�̏ꍇ�A<BR>
     * ���̐����i-1�j��ԋp����B <BR>
     * �@@�E�i���o�����ׂP.��� = ���o�����ׂQ.����j�̏ꍇ�A<BR>
     * 0��ԋp����B <BR>
     * �@@�E�i���o�����ׂP.��� > ���o�����ׂQ.����j�̏ꍇ�A<BR>
     * ���̐����i1�j��ԋp����B <BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j] <BR>
     * �@@�E�i���o�����ׂP.��� < ���o�����ׂQ.����j�̏ꍇ�A<BR>
     * ���̐����i1�j��ԋp����B <BR>
     * �@@�E�i���o�����ׂP.��� = ���o�����ׂQ.����j�̏ꍇ�A<BR>
     * 0��ԋp����B <BR>
     * �@@�E�i���o�����ׂP.��� > ���o�����ׂQ.����j�̏ꍇ�A<BR>
     * ���̐����i-1�j��ԋp����B <BR>
     * @@param l_cashTransferDetails01 - (���o�����ׂP)<BR>
     * ���o�����׃I�u�W�F�N�g�P
     * @@param l_cashTransferDetails02 - (���o�����ׂQ)<BR>
     * ���o�����׃I�u�W�F�N�g�Q
     * @@return int
     * @@throws WEB3SystemLayerException
     * @@roseuid 4107144F003C
     */
    public int compare(Object l_cashTransferDetails01, Object l_cashTransferDetails02) 
    {
        
        final String STR_METHOD_NAME =
            "execute(WEB3GenRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_cashTransferDetails01 == null || l_cashTransferDetails02 == null)
        {
            log.debug("l_cashTransferDetails01 or l_cashTransferDetails02 is null ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "the parameter method is null");
        }
        
        //�P�j�@@������cast 
        //�@@�����̓��o�����ׂP�A���o�����ׂQ����o�����׌^��cast����B 
        WEB3AioCashTransUnit l_aioCashTransUnit01 = (WEB3AioCashTransUnit)l_cashTransferDetails01;
        WEB3AioCashTransUnit l_aioCashTransUnit02 = (WEB3AioCashTransUnit)l_cashTransferDetails02;
        
        //�Q�j�@@��r
        //�@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j] 
        //(this.pageIndex < 1) ? 1 : this.pageIndex;
        int l_inttradeType01 = (l_aioCashTransUnit01.tradingType == null) ? -1
            : Integer.parseInt(l_aioCashTransUnit01.tradingType);
        int l_inttradeType02 = (l_aioCashTransUnit02.tradingType == null) ? -1
            : Integer.parseInt(l_aioCashTransUnit02.tradingType);
        
        if(WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            // ���o�����ׂP.��� < ���o�����ׂQ.����j�̏ꍇ�A���̐����i-1�j��ԋp����B
            if(l_inttradeType01 < l_inttradeType02)
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
                
            }            
            // (���o�����ׂP.��� = ���o�����ׂQ.����j�̏ꍇ�A0��ԋp����B
            else if(l_inttradeType01 == l_inttradeType02)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
                
            }
            // (���o�����ׂP.��� > ���o�����ׂQ.����j�̏ꍇ�A���̐����i1�j��ԋp����B
            else if(l_inttradeType01 > l_inttradeType02)
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
                
            }
            
        }        
        //�@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j]
        else if(WEB3AscDescDef.DESC.equals(this.orderBy))
        {
            //�i���o�����ׂP.��� < ���o�����ׂQ.����j�̏ꍇ�A���̐����i1�j��ԋp����B
            if(l_inttradeType01 < l_inttradeType02)
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
                
            }
            //�i���o�����ׂP.��� = ���o�����ׂQ.����j�̏ꍇ�A0��ԋp����B
            else if(l_inttradeType01 == l_inttradeType02)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            //�i���o�����ׂP.��� > ���o�����ׂQ.����j�̏ꍇ�A���̐����i-1�j��ԋp����
            else if(l_inttradeType01 > l_inttradeType02)
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
            }
        }
        
        return 0;
    }
    
    /**
     * �iequals�̎����j <BR>
     * <BR>
     * ���g�p�B <BR>
     * false��ԋp����B <BR>
     * @@param l_arg0
     * @@return boolean
     * @@roseuid 4107144F003F
     */
    public boolean equals(Object l_arg0) 
    {
        final String STR_METHOD_NAME = "equals(Object l_obj) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_arg0 instanceof WEB3AioDealComparator)
        {
            WEB3AioDealComparator l_aioDealComparator = 
                (WEB3AioDealComparator)l_arg0;

            if (this.orderBy.equals(l_aioDealComparator.orderBy))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        log.exiting(STR_METHOD_NAME);        
        return false;
    }
    
    /**
     * (���Comparator)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �����̒l��this.orderBy�ɃZ�b�g����B <BR>
     * @@param l_strOrderBy - �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B <BR>
     * <BR>
     * �@@A�F����  <BR>
     * �@@D�F�~�� <BR>
     * @@roseuid 4107144F0041
     */
    public WEB3AioDealComparator(String l_strOrderBy) 
    {
        final String STR_METHOD_NAME =
            "WEB3AioDealComparator(String l_strOrderBy)";
        log.entering(STR_METHOD_NAME);
        
        this.orderBy = l_strOrderBy;
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
