head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.43.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3IndexQuoteData.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �e��w���̎������N���X(WEB3IndexQuoteData.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/14 �R�c�@@��i(FLJ) �V�K�쐬
*/
package webbroker3.quoteadaptor;

import com.fitechlabs.xtrade.plugin.tc.gentrade.QuoteData;

/**
 * �e��w���̎������<br>
 * �����T�[�r�X�ɂ���Ē񋟂����e��w���̎�������\���C���^�[�t�F�[�X
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public interface WEB3IndexQuoteData extends QuoteData, WEB3QuoteData
{
    
    /**
     * �w���̎�ނ��擾����B
     * 
     * @@return �w���̎��
     */
    public IndexType getIndexType();

}
@
