head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.32.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderVoucherListCondUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���������������`�[�ꗗ����(WEB3AdminFeqOrderVoucherListCondUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 �s�p (���u) �V�K�쐬
                 : 2005/08/02 ���U (���u) ���r���[
*/

package webbroker3.feq.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�O���������������`�[�ꗗ����)<BR>
 * �O���������������`�[�ꗗ�����N���X
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3AdminFeqOrderVoucherListCondUnit extends Message 
{
    
    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h
     */
    public String marketCode;
    
    /**
     * (�������i���j)<BR>
     * ��ʂɂē��͂��ꂽ�������i���j
     */
    public Date orderBizDateFrom;
    
    /**
     * (�������i���j)<BR>
     * ��ʂɂē��͂��ꂽ�������i���j
     */
    public Date orderBizDateTo;
    
    /**
     * (�O���������������`�[�ꗗ����)<BR>
     * �R���X�g���N�^
     * @@roseuid 420214FC000E
     */
    public WEB3AdminFeqOrderVoucherListCondUnit() 
    {
     
    }
}
@
