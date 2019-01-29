head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityAssetUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������ۗL���Y�ꗗ�Ɖ�ۗL���Y(WEB3EquityAssetUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/10 �A�C��(���u) �V�K�쐬
                 : 2006/08/29 �����F(���u) ���f�� 972
*/

package webbroker3.equity.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * �i���������ۗL���Y�ꗗ�Ɖ�ۗL���Y�j�B<BR>
 * <BR>
 * ���������ۗL���Y�ꗗ�Ɖ�ۗL���Y�@@�f�[�^�N���X
 * @@version 1.0
 */
public class WEB3EquityAssetUnit extends Message
{

    /**
     * �ۗL���YID�i��\�����ځj<BR>
     */
    public String id;

    /**
     * (�����R�[�h)
     */
    public String productCode;

    /**
     * (������)
     */
    public String productName;

    /**
     * (�����敪)<BR>
     * 0�F��ʁ@@1�F����@@5�F�X�g�b�N�I�v�V����<BR>
     */
    public String taxType;

    /**
     * (���t�\����)
     */
    public String sellPossQuantity;

    /**
     * (����������)
     */
    public String orderedQuantity;

    /**
     * (�s��R�[�h�ꗗ)<BR>
     * �����A�戵�s��ꗗ<BR>
     */
    public String[] marketList;

    /**
     * (���t�\�t���O)<BR>
     * true�F���t�\�@@�@@false�F���t�s��<BR>
     */
    public boolean sellPossFlag;

    /**
     * @@roseuid 409F5F4903C1
     */
    public WEB3EquityAssetUnit()
    {

    }
}
@
