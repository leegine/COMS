head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.00.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyCommonUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�莞��z���t���ʏ��(WEB3MutualFixedBuyCommonUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/26 ���G�� (���u) �V�K�쐬
Revesion History : 2008/07/14 ���z (���u) �d�l�ύX���f��611
*/
package webbroker3.mf.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;
/**
 * (���M�莞��z���t���ʏ��)<BR>
 * ���M�莞��z���t���ʏ��
 * 
 * @@author ���G��(���u)
 * @@version 1.0 
 */

public class WEB3MutualFixedBuyCommonUnit extends Message  
{

    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String mutualProductCode;
  
    /**
     * (���t���z(���X))<BR>
     * ���t���z(���X)<BR>
     */
    public String monthlyBuyAmount;
   
    /**
     * (���t���z(�ςݑ���))<BR>
     * ���t���z(�ςݑ���)<BR>
     */
    public String increaseBuyAmount;
   
    /**
     * (�d�q���`�F�b�N�t���O)<BR>
     * �d�q���`�F�b�N�t���O<BR> 
     */
    public boolean batoCheckFlag;
   
    /**
     * (��ʃR�[�h)<BR>
     * ��ʃR�[�h<BR> 
     */
    public String typeCode;    

    /**
     * (�ύX�敪)<BR>
     * �ύX�敪<BR>
     */
    public String changeDiv;

    /**
     * (�K�p�J�n�N��)<BR>
     * �K�p�J�n�N��<BR>
     */
    public Date validStartDate;

    /**
     * (�f�t�H���g�R���X�g���N�^)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     */
    public WEB3MutualFixedBuyCommonUnit()
    {
    }
}
@
