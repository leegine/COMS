head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.58.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �|�C���g�����ꗗ�\�[�g�L�[(WEB3PointSortKey.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�|�C���g�����ꗗ�\�[�g�L�[)<BR>
 * �|�C���g�����ꗗ�\�[�g�L�[�N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3PointSortKey extends Message
{
    
    /**
     * (�L�[����)<BR>
     * �L�[����<BR>
     * <BR>
     *   �E���X�R�[�h<BR>
     *   �E�ڋq�R�[�h<BR>
     *   �E�i�i�ԍ�<BR>
     *   �E�i�i��<BR>
     *   �E�\������<BR>
     *   �E��t�敪<BR>
     *   �E�X�V����<BR>
     *   �E��t���[�U<BR>
     *   �E����敪<BR>
     */
    public String keyItem;
    
    /**
     * (����/�~��)<BR>
     * A�F ����<BR>
     * D�F �~��<BR>
     */
    public String ascDesc;
    
    /**
     * @@roseuid 41D1254B030D
     */
    public WEB3PointSortKey() 
    {
     
    }
}
@
