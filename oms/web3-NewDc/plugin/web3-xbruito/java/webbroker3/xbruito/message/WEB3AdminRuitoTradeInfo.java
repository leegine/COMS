head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminRuitoTradeInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ������ʔ������(WEB3AdminRuitoTradeInfo)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/26 ���E (���u) �V�K�쐬
*/
package webbroker3.xbruito.message;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�ݓ������ʔ������)<BR>
 * �ݓ������ʔ������N���X
 */
public class WEB3AdminRuitoTradeInfo extends Message
{
    /**
     * SerialVersionUID
     */
    public final static long serialVersionUID = 200408031539L;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h
     */
    public String ruitoProductCode;
    
    /**
     * (������)<BR>
     * ������
     */
    public String ruitoProductName;
    
    /**
     * (���t�J�n��)<BR>
     * ���t�J�n��
     */
    public Date buyStartDate;

    /**
     * (���t�I����)<BR>
     * ���t�I����
     */
    public Date buyEndDate;
    
    /**
     * (���J�n��)<BR>
     * ���J�n��
     */
    public Date sellStartDate;
    
    /**
     * (���I����)<BR>
     * ���I����
     */
    public Date sellEndDate;
    
    /**
     * (���t�\�敪)<BR>
     * ���t�\�敪�i�����j<BR>
     * 0�F��~�łȂ��A1�F��~��
     */
    public String curBuyPosDiv;
    
    /**
     * (���\�敪)<BR>
     * ���\�敪�i�����j<BR>
     * 0�F��~�łȂ��A1�F��~��
     */
    public String curSellPosDiv;
    
    /**
     * (���t�\�敪�i����))<BR>
     * ���t�\�敪�i�����j<BR>
     * 0�F��~�łȂ��A1�F��~��
     */
    public String nextBuyPosDiv;
    
    /**
     * (���\�敪�i�����j)<BR>
     * ���\�敪�i�����j<BR>
     * 0�F��~�łȂ��A1�F��~��
     */
    public String nextSellPosDiv;
    
    /**
     * (�ݓ������ʔ������)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40922BD901A5
     */
    public WEB3AdminRuitoTradeInfo()
    {

    }    
}@
