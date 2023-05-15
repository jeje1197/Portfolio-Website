import Link from 'next/link';
import React from 'react';

import { Section, SectionText, SectionTitle, SectionSubText } from '../../styles/GlobalComponents';
import Button from '../../styles/GlobalComponents/Button';
import { LeftSection } from './HeroStyles';

const Hero = () => (
  <Section row nopadding>
    <LeftSection>

      <SectionTitle main center>
        Hi,<br/>
        I'm {"<"}Joseph Evans{"/>"}
      </SectionTitle>
      <SectionText>
        <SectionSubText>
          Software developer from the University of California, Santa Cruz with a passion for building efficient, scalable solutions.
        </SectionSubText>
      </SectionText>

      <Button onClick={() => window.location = 'mailTo: Josephev4@gmail.com'}>Contact me!</Button>

    </LeftSection>
  </Section>
);

export default Hero;