head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.19.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOptionsContractUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ʖ���(WEB3FuturesOptionsContractUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 ���o�� (���u) �V�K�쐬
              001: 2004/08/05 ���Ō� (���u) �Ή��o�O U00021
Revesion History : 2007/06/08 ���^�] (���u) �d�l�ύX���f��No.640
*/

package webbroker3.ifo.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (���ʖ���)<BR>
 * ���ʂ̖��ׂ�\���N���X<BR>
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3FuturesOptionsContractUnit extends Message
{
    
    /**
     * ���ʂh�c
     */
    public String id;
    
    /**
     * (����)
     */
    public java.util.Date openDate;
    
    /**
     * (���ʐ�)
     */
    public String contractQuantity;
    
    /**
     * (���P��)
     */
    public String contractPrice;
    
    /**
     * (�������z)
     */
    public String contractExecPrice;
    
    /**
     * (���萔��)<BR>
     * ���萔��(����ō�)<BR>
     */
    public String contractCommission;
    
    /**
     * (���v)
     */
    public String income;
    
    /**
     * (���v(���o�))
     */
    public String incomeCost;
    
    /**
     * (�ԍϐ���)
     */
    public String contractOrderQuantity;
    
    /**
     * (���Ϗ���)
     */
    public String settlePriority;

    /**
     * (�ԍϖ�萔��)
     */
    public String contractExecQuantity;

    /**
     * (����敪)<BR>
     * 1�F�[��i�[����{�����Ђ̗[�ꎞ�ԑт̂ݐݒ�j�@@NULL�F���̑�<BR>
     */
    public String sessionType;

    /**
     * (���ʖ���)
     * �R���X�g���N�^
     * @@roseuid 407F6E8C008C
     */
    public WEB3FuturesOptionsContractUnit() 
    {
        
    }
}
@
