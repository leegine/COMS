head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoAssetGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ۗL���Y�ꗗ�s(WEB3RuitoAssetGroup)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 �� �E (���u) �V�K�쐬
                   2004/12/03 ��O�� (���u) �c�Ή�
*/
package webbroker3.xbruito.message;

import com.fitechlabs.xtrade.kernel.message.Message;
/**
 * �ۗL���Y�ꗗ�s<BR>
 */
public class WEB3RuitoAssetGroup extends Message
{

    /**
     * �����R�[�h<BR>
     */
    public String ruitoProductCode;

    /**
     * ������<BR>
     */
    public String ruitoProductName;

    /**
     * �c��<BR>
     */
    public String ruitoBalance;

    /**
     * ��30���o�ߎc��<BR>
     */
    public String ruito30DayPassBal;

    /**
     * ��30�����o�ߎc��<BR>
     */
    public String ruito30DayNotPassBal;

    /**
     * �M�����Y���ۊz<BR>
     */
    public String estateReserve;

    /**
     * ���\�c��<BR>
     */
    public String ruitoSellPossBalance;

    /**
     * ��񒍕�����<BR>
     */
    public webbroker3.xbruito.message.WEB3RuitoSellOrderUnit[] ruitoSellOrderUnits;

    /**
     * ���i���t�j�\�敪  <BR>
     * null�F����\  <BR>
     * �P�F�V�X�e�������~�G���[  <BR>
     * �Q�F��t���ԃG���[  <BR>
     * �R�F�����~��  <BR>
     * �S�F�ً}��~��  <BR>
     * �T�F�S�����  <BR>
     */
    public String sellPossDiv;

    /**
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40922B76032C
     */
    public WEB3RuitoAssetGroup()
    {
    }
}
@
